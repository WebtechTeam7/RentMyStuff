package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import play.db.DB;

public class Model extends OurObserver {

	private static Model instance;
	private boolean dbExist;
	private final String createUserStatement = "CREATE TABLE User( "
			+ "UserID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "Firstname TEXT NOT NULL," + "Lastname TEXT NOT NULL,"
			+ "Email TEXT NOT NULL UNIQUE," + "Password TEXT NOT NULL" + ");";

	private final String createAdvertStatement = "CREATE TABLE Advert("
			+ "AdvertID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "AdvertUserID INTEGER NOT NULL," + "Kind TEXT NOT NULL,"
			+ "Date TEXT NOT NULL," + "Category TEXT NOT NULL,"
			+ "Description TEXT NOT NULL,"
			+ "FOREIGN KEY (AdvertUserID) REFERENCES User(USERID)" + ");";
	private final String createAddressStatement = "CREATE TABLE Address("
			+ "AddressID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "Street TEXT NOT NULL," + "Postcode TEXT NOT NULL,"
			+ "City TEXT NOT NULL," + "Country TEXT NOT NULL" + ");";

	private final String createAdvertAddressStatement = "CREATE TABLE AdvertAddress("
			+ "Address INTEGER NOT NULL,"
			+ "Advert INTEGER NOT NULL,"
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

		userList.clear();
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
		String statement = "INSERT INTO User(Firstname, Lastname, Email, Password)"
				+ "VALUES(?,?,?,?)";
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.execute();
			System.out.println("Folgender User wurde in DB erstelle: "
					+ firstname + " " + lastname);
		} catch (SQLException e) {
			System.out.println("User: " + firstname + " " + lastname
					+ " konnte nicht erstellt werden!!");
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
		User user = new User();
		user.setEmail(email);
		int id = 0;
		try {
			id = getUserIdByEmail(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		user.setUserID(id);

		List<Advert> userAdvert = getUserAdvertList(id);
		for (Advert advert : userAdvert) {
			deleteAdvert(advert.getId(), user.getUserID());
			System.out.println("Loscht Anzeige des Users: "
					+ advert.getCategory());
		}
		if (id != 0) {

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
	}

	public void setUserList(List<User> userList) {
		Model.getInstance().userList = userList;
	}

	public void setUserAdvertList(List<Advert> userAdvertList) {
		Model.getInstance().userAdvertList = userAdvertList;
	}

	/**
	 * 
	 * @param id
	 * @return user passend zur ID
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

	/**
	 * 
	 * @param advertID
	 * @return die zur advertID gehoerige Adresse
	 */
	private Address getAddress(int advertID) {

		String statement = "SELECT ad.Street, ad.Postcode, ad.City, ad.Country, aa.Address FROM Address ad, AdvertAddress aa WHERE aa.Advert = ? AND aa.Address = ad.AddressID";
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
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
			System.out.println("Folgendes Angebot wurde erstellt "
					+ user.getFirstname() + "  " + street);
		} catch (SQLException e) {
			System.out.println("Fehler beim erstellen");
			e.printStackTrace();
		}

		// if(advert!=null){
		// notify(advert);
		// } else {
		// System.out.println("Advert == null in createAdvert-Method");
		// }
	}

	public static void setAdvertList(List<Advert> advertList) {
		Model.getInstance().advertList = advertList;
	}

	private void advertAddress(int addressID, int advertID) throws SQLException {
		String statement = "INSERT INTO AdvertAddress(Address, Advert)"
				+ "VALUES(?,?)";
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(statement);
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

	/**
	 * Erzeugt eine neue Anzeige in der Datenbank.
	 * 
	 * @param user
	 * @param optradio
	 * @param kategorie
	 * @param comment
	 * @return die ID der Anzeige
	 * @throws SQLException
	 */

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

	/**
	 * 
	 * @return das Datum an dem die Anzeige erstellt wurde
	 */
	private String createCurrentDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.MEDIUM);
		System.out.println(df.format(calendar.getTime()));
		return df.format(calendar.getTime());
	}

	/**
	 * Loescht die Anzeige aus der Datenbank
	 * 
	 * @param id
	 * @param userID
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
				String statement1 = "DELETE FROM Advert WHERE AdvertID = ?";
				PreparedStatement preparedStatement1 = connection
						.prepareStatement(statement1);
				preparedStatement1.setInt(1, id);
				preparedStatement1.executeUpdate();

				// delete AdvertAddress
				String statement2 = "DELETE FROM AdvertAddress WHERE Advert = ?";
				PreparedStatement preparedStatement2 = connection
						.prepareStatement(statement2);
				preparedStatement2.setInt(1, id);
				preparedStatement2.executeUpdate();

				// delete Address
				String statement3 = "DELETE FROM Address WHERE AddressID = ?";
				PreparedStatement preparedStatement3 = connection
						.prepareStatement(statement3);
				preparedStatement3.setInt(1, addressID);
				preparedStatement3.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("User not authorized");
		}
	}

	/**
	 * 
	 * @param id
	 * @return die ID der Addresse
	 * @throws SQLException
	 */
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

	/**
	 * 
	 * @param id
	 * @param userID
	 * @return true wenn User seine eigenen Angebote/Gesuche löscht
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

	/**
	 * Create our Database
	 */
	public void initDatabase() {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM User");
			PreparedStatement preparedStatement2 = connection
					.prepareStatement("SELECT * FROM Advert");
			PreparedStatement preparedStatement3 = connection
					.prepareStatement("SELECT * FROM Address");
			PreparedStatement preparedStatement4 = connection
					.prepareStatement("SELECT * FROM AdvertAddress");
			preparedStatement.execute();
			preparedStatement2.execute();
			preparedStatement3.execute();
			preparedStatement4.execute();
			System.out.println(dbExist);
			dbExist = true;
		} catch (SQLException e) {
			dbExist = false;
		}

		if (!dbExist) {
			System.out.println("Call initTables()");
			initTables(createUserStatement);
			initTables(createAdvertStatement);
			initTables(createAddressStatement);
			initTables(createAdvertAddressStatement);
			createDummyData();
			dbExist = true;
		} else {
			System.out.println("Database already exists!");
		}
	}

	/**
	 * Generate DummyData
	 */
	private void createDummyData() {
		User user = new User();
		user.setFirstname("User");
		user.setLastname("User");
		user.setEmail("user.user@gmail.com");
		user.setPassword(BCrypt.hashpw("user", BCrypt.gensalt()));
		createUser(user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPassword());

		System.out.println("Dummy user Created: " + user.getEmail());

		createAdvert("Gesuch", "Gartengeräte", "Ich suche einen Rasenmäher",
				user, "Brauneggerstrasse 55", "78462", "Konstanz",
				"Deutschland");
		createAdvert("Angebot", "Fahrzeuge",
				"Ich biete meinen VW Bus an. Wenn jemand umziehen möchte",
				user, "Rheingutstrasse 4", "78462", "Konstanz", "Deutschland");
		System.out.println("Dummy advert created:");
	}

	/**
	 * Create the tables
	 * 
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
