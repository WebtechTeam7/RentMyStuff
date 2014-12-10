package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

public class Model {

	public Model() {
		
	}

	private static Connection connection = DB.getConnection();
	private static List<User> userList = new ArrayList<User>();
	private static List<Advert> advertList = new ArrayList<Advert>();


	public static List<User> getUserList() {

		try {
			//Get all Users from the database
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User");
			
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				User user = new User();
				user.setFirstname(resultset.getString("Firstname"));
				user.setLastname(resultset.getString("Lastname"));
				user.setPassword(resultset.getString("Password"));
				user.setEmail(resultset.getString("Email"));
				userList.add(user);
				System.out.println(user.getFullName());
			}
		} catch (SQLException e) {
			System.out.println("Datenbankabfrage failed");
			e.printStackTrace();
		}

		return userList;
	}

	public static void setUserList(List<User> userList) {
		Model.userList = userList;
	}

	public static List<Advert> getAdvertList() {
		
		advertList.clear();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advert");
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()){
				Advert advert = new Advert();
				advert.setKind(resultset.getString("Kind"));
				advert.setCategory(resultset.getString("Category"));
				advert.setUser(getUserById(resultset.getString("AdvertUserID")));
				advert.setDescription(resultset.getString("Description"));
				advertList.add(advert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return advertList;
	}

	public static void setAdvertList(List<Advert> advertList) {
		Model.advertList = advertList;
	}
	
	public static User getUserById(String id){
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE UserId = " + id);
			ResultSet resultset = preparedStatement.executeQuery();
			User user;
			while(resultset.next()){
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


}
