package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

public class Model {

	private static Model instance;

	private Model() {
	}

	public static Model getInstance() {
		if (Model.instance == null) {
			Model.instance = new Model();
		}
		return Model.instance;
	}

	private Connection connection = DB.getConnection();
	private List<User> userList = new ArrayList<User>();
	private List<Advert> advertList = new ArrayList<Advert>();
	private List<Advert> userAdvertList = new ArrayList<Advert>();

	public List<User> getUserList() {

		try {
			// Get all Users from the database
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

	public void deleteUser(int userId) {
		try {
			String statement = "DELETE FROM User WHERE UserID = ?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			String id = String.valueOf(userId);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setUserList(List<User> userList) {
		Model.getInstance().userList = userList;
	}

	public List<Advert> getAdvertList() {

		advertList.clear();

		try {
			String statement = "SELECT * FROM Advert";
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
				advertList.add(advert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return advertList;
	}
	
	public List<Advert> getAdvertList(String category){
		advertList.clear();
		
		try {
			String statement = "SELECT * FROM Advert WHERE Category = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setString(1, category);
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()){
				Advert advert = new Advert();
				advert.setKind(resultset.getString("Kind"));
				advert.setCategory(resultset.getString("Category"));
				advert.setUser(getUserById(resultset.getString("AdvertUserID")));
				advert.setDescription(resultset.getString("Description"));
				advert.setId(resultset.getInt("AdvertID"));
				advertList.add(advert);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return advertList;
	}

	public List<Advert> getUserAdvertList(int userId) {

		userAdvertList.clear();

		try {
			String statement = "SELECT * FROM Advert WHERE AdvertUserID = ?";
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
				userAdvertList.add(advert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userAdvertList;
	}

	public void createAdvert(String optradio, String kategorie, String comment,
			User user) {
		try {
			String statement = "INSERT INTO Advert (AdvertUserID, Kind, Category, Description)"
					+ "VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(statement);
			preparedStatement.setInt(1, getUserIdByEmail(user));
			preparedStatement.setString(2, optradio);
			preparedStatement.setString(3, kategorie);
			preparedStatement.setString(4, comment);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAdvert(int id, int userID) {
		boolean allowed = false;
		try {
			allowed = checkAuthorization(id, userID);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (allowed) {
			try {
				String statement = "DELETE FROM Advert WHERE AdvertID = ?";
				PreparedStatement preparedStatement = connection
						.prepareStatement(statement);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("User not authorized");
		}
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

}
