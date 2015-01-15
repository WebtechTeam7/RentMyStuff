package models;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import play.db.DB;

public class Model {

	private static Model instance;

	private final String user = "CREATE TABLE User( "
			+ "UserID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "Firstname TEXT NOT NULL," + "Lastname TEXT NOT NULL,"
			+ "Email TEXT NOT NULL UNIQUE," + "Password TEXT NOT NULL" + ");";

	private final String advert = "CREATE TABLE Advert("
			+ "AdvertID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "AdvertUserID INTEGER NOT NULL," + "Kind TEXT NOT NULL,"
			+ "Date TEXT NOT NULL,"
			+ "Category TEXT NOT NULL," + "Description TEXT NOT NULL,"
			+ "FOREIGN KEY (AdvertUserID) REFERENCES User(USERID)" + ");";
	private final String address = "CREATE TABLE Address("
			+ "AddressID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "Street TEXT NOT NULL," + "Postcode TEXT NOT NULL,"
			+ "City TEXT NOT NULL," + "Country TEXT NOT NULL" + ");";

	private final String advertAddress = "CREATE TABLE AdvertAddress("
			+ "Address INTEGER NOT NULL," + "Advert INTEGER NOT NULL,"
			+ "FOREIGN KEY (Address) REFERENCES Address(AddressID),"
			+ "FOREIGN KEY (Advert) REFERENCES Advert(AdvertID),"
			+ "PRIMARY KEY (Address, Advert)" + ");";

	private Model() {
		connection = DB.getConnection();
	}

	public static Model getInstance() {
		if (Model.instance == null) {
			Model.instance = new Model();
		}
		return Model.instance;
	}

	private Connection connection;
	private List<User> userList = new ArrayList<User>();
	private List<Advert> advertList = new ArrayList<Advert>();
	private List<Advert> userAdvertList = new ArrayList<Advert>();

	/**
	 * 
	 * @return alle User aus der Datenbank
	 */
	public List<User> getUserList() {

		try {

			String statement = "SELECT * FROM User";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);

			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				User user = new User();
				user.setFirstname(resultset.getString("Firstname"));
				user.setLastname(resultset.getString("Lastname"));
				user.setPassword(resultset.getString("Password"));
				user.setEmail(resultset.getString("Email"));
				user.setUserID(resultset.getInt("UserID"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	/**
	 * Legt einen neuen User in der Datenbank an.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * 
	 */
	public void createUser(String firstname, String lastname, String email,
			String password) {
		try {
			String statement = "INSERT INTO User(Firstname, Lastname, Email, Password)"
					+ "VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loescht den User aus der Datenbank
	 * 
	 * @param email
	 * @param password
	 */
	public void deleteUser(String email, String password) {
		try {
			String statement = "DELETE FROM User WHERE Email = ? AND Password = ?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setUserList(List<User> userList) {
		Model.getInstance().userList = userList;
	}

	/**
	 * 
	 * @return alle Angebote/Gesuche
	 */

	public List<Advert> getAdvertList() {

		advertList.clear();

		try {
			String statement = "SELECT * FROM Advert Order BY AdvertId DESC";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				Advert advert = new Advert();
				advert.setKind(resultset.getString("Kind"));
				advert.setCategory(resultset.getString("Category"));
				advert.setUser(getUserById(resultset.getString("AdvertUserID")));
				advert.setDescription(resultset.getString("Description"));
				advert.setId(resultset.getInt("AdvertID"));
				advert.setAddress(getAddress(advert.getId()));
				advert.setDate(resultset.getString("Date"));
				advertList.add(advert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return advertList;
	}

	/**
	 * 
	 * @param category
	 * @return alle Angebote/Gesuche mit der uebergebenen Kategorie
	 */
	public List<Advert> getAdvertList(String category) {
		advertList.clear();

		try {
			String statement = "SELECT * FROM Advert WHERE Category = ? Order BY AdvertId DESC";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setString(1, category);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				Advert advert = new Advert();
				advert.setKind(resultset.getString("Kind"));
				advert.setCategory(resultset.getString("Category"));
				advert.setUser(getUserById(resultset.getString("AdvertUserID")));
				advert.setDescription(resultset.getString("Description"));
				advert.setId(resultset.getInt("AdvertID"));
				advert.setAddress(getAddress(advert.getId()));
				advert.setDate(resultset.getString("Date"));
				advertList.add(advert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return advertList;
	}

	private Address getAddress(int advertID) throws SQLException {

		String statement = "SELECT ad.Street, ad.Postcode, ad.City, ad.Country, aa.Address FROM Address ad, AdvertAddress aa WHERE aa.Advert = ? AND aa.Address = ad.AddressID";
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setInt(1, advertID);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Address address = new Address();
			address.setStreet(resultSet.getString("Street"));
			address.setPostcode(resultSet.getString("Postcode"));
			address.setCity(resultSet.getString("City"));
			address.setCountry(resultSet.getString("Country"));
			return address;
		}

		return null;
	}

	/**
	 * 
	 * @param userId
	 * @return dem User zugehoerige Angebote/Gesuche
	 */
	public List<Advert> getUserAdvertList(int userId) {

		userAdvertList.clear();

		try {
			String statement = "SELECT * FROM Advert WHERE AdvertUserID = ? Order BY AdvertId DESC";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setInt(1, userId);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				Advert advert = new Advert();
				advert.setKind(resultset.getString("Kind"));
				advert.setCategory(resultset.getString("Category"));
				advert.setUser(getUserById(resultset.getString("AdvertUserID")));
				advert.setDescription(resultset.getString("Description"));
				advert.setId(resultset.getInt("AdvertID"));
				advert.setDate(resultset.getString("Date"));
				userAdvertList.add(advert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userAdvertList;
	}

	/**
	 * Erzeugt eine neue Anzeige in der Datenbank
	 * 
	 * @param optradio
	 * @param kategorie
	 * @param comment
	 * @param user
	 * 
	 */
	public void createAdvert(String optradio, String kategorie, String comment,
			User user, String street, String postcode, String city,
			String country) {

		try {
			int addressID = address(street, postcode, city, country);
			int advertID = advert(user, optradio, kategorie, comment);
			advertAddress(addressID, advertID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void advertAddress(int addressID, int advertID) throws SQLException {
		String statement = "INSERT INTO AdvertAddress(Address, Advert)"
				+ "VALUES(?,?)";
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setInt(1, addressID);
		preparedStatement.setInt(2, advertID);
		preparedStatement.executeUpdate();

	}

	private int address(String street, String postcode, String city,
			String country) throws SQLException {
		String statement = "INSERT INTO ADDRESS (Street, Postcode, City, Country)"
				+ "VALUES(?,?,?,?)";
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setString(1, street);
		preparedStatement.setString(2, postcode);
		preparedStatement.setString(3, city);
		preparedStatement.setString(4, country);
		preparedStatement.executeUpdate();

		// now return the ID
		int id;
		statement = "SELECT * FROM Address ORDER BY AddressID DESC LIMIT 1";
		preparedStatement = connection.prepareStatement(statement);
		ResultSet resultset = preparedStatement.executeQuery();
		while (resultset.next()) {
			id = resultset.getInt("AddressID");
			return id;
		}
		return 0;

	}

	private int advert(User user, String optradio, String kategorie,
			String comment) throws SQLException {
		String statement = "INSERT INTO Advert (AdvertUserID, Date, Kind, Category, Description)"
				+ "VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setInt(1, getUserIdByEmail(user));
		preparedStatement.setString(2, createCurrentDate());
		preparedStatement.setString(3, optradio);
		preparedStatement.setString(4, kategorie);
		preparedStatement.setString(5, comment);
		preparedStatement.executeUpdate();

		// now return the ID
		int id;
		statement = "SELECT * FROM Advert ORDER BY AdvertID DESC LIMIT 1";
		preparedStatement = connection.prepareStatement(statement);
		ResultSet resultset = preparedStatement.executeQuery();
		while (resultset.next()) {
			id = resultset.getInt("AdvertID");
			return id;
		}
		return 0;

	}

	private String createCurrentDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
		System.out.println(df.format(calendar.getTime()));
		return df.format(calendar.getTime());
	}

	/**
	 * Loescht die Anzeige aus der Datenbank
	 * 
	 * @param id
	 * @param userID
	 * @author Jan
	 * 
	 */
	public void deleteAdvert(int id, int userID) {
		boolean allowed = false;
		try {
			allowed = checkAuthorization(id, userID);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (allowed) {
			try {
				int addressID = getAddressID(id);
				// delete the advert
				String statement = "DELETE FROM Advert WHERE AdvertID = ?";
				PreparedStatement preparedStatement = connection
						.prepareStatement(statement);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();

				// delete AdvertAddress
				statement = "DELETE FROM AdvertAddress WHERE Advert = ?";
				preparedStatement = connection.prepareStatement(statement);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();

				// delete Address
				statement = "DELETE FROM Address WHERE AddressID = ?";
				preparedStatement = connection.prepareStatement(statement);
				preparedStatement.setInt(1, addressID);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("User not authorized");
		}
	}

	private int getAddressID(int id) throws SQLException {
		String statement = "SELECT Address FROM AdvertAddress WHERE Advert = ?";
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setInt(1, id);
		ResultSet resultset = preparedStatement.executeQuery();
		while (resultset.next()) {
			id = resultset.getInt("Address");
			return id;
		}
		return 0;
	}

	public static void setAdvertList(List<Advert> advertList) {
		Model.getInstance().advertList = advertList;
	}

	/**
	 * 
	 * @param id
	 * @return user passend zur ID
	 * @author Jan
	 * 
	 */
	public User getUserById(String id) {
		PreparedStatement preparedStatement;
		try {
			String statement = "SELECT * FROM User WHERE UserId = ?";
			preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setString(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			User user;
			while (resultset.next()) {
				user = new User();
				user.setEmail(resultset.getString("Email"));
				user.setFirstname(resultset.getString("Firstname"));
				user.setLastname(resultset.getString("Lastname"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param user
	 * @return UserID wird über die E-Mail Adresse gefunden
	 * @throws SQLException
	 * @author Jan
	 */

	public int getUserIdByEmail(User user) throws SQLException {
		String statement = "SELECT UserID FROM User WHERE Email = ?";
		int id = 0;
		String email = user.getEmail();
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setString(1, email);
		ResultSet resultset = preparedStatement.executeQuery();
		while (resultset.next()) {
			id = resultset.getInt("UserId");
		}
		return id;
	}

	/**
	 * 
	 * @param id
	 * @param userID
	 * @return true wenn User seine eigenen Angebote/Gesuche löscht
	 * @author Jan
	 * @throws SQLException
	 */
	public boolean checkAuthorization(int id, int userID) throws SQLException {

		String statement = "SELECT * FROM Advert WHERE AdvertId = ? AND AdvertUserID = ?";
		PreparedStatement preparedStatement = connection
				.prepareStatement(statement);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, userID);
		ResultSet resultset = preparedStatement.executeQuery();
		if (resultset != null) {
			return true;
		} else {
			return false;
		}

	}

	public void setUserAdvertList(List<Advert> userAdvertList) {
		Model.getInstance().userAdvertList = userAdvertList;
	}

	/**
	 * Create our Database
	 */
	public void initDatabase() {
		File file = new File("@routes.Assets.at('')");
		if (!file.exists()) {
			System.out.println("Call initTables()");
			initTables(user);
			initTables(advert);
			initTables(address);
			initTables(advertAddress);
		} else {
			System.out.println("Database already exists!");
		}
	}

	/**
	 * Create the tables
	 * @param tablename
	 */
	private void initTables(String tablename) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(tablename);
			preparedStatement.executeUpdate();
			System.out.println("Create " + tablename + " in given Database");
		} catch (SQLException e) {
			System.out.println("tables already exist");
		}
	}

}
