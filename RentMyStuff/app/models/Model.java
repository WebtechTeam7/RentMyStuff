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

//	static User user1 = new User("Dennis", "Klein",
//			"dennis.klein@htwg-konstanz.de", "user1");
//	static User user2 = new User("Ramona", "Barth",
//			"ramona.barth@htwg-konstanz.de", "user2");
//	static User user3 = new User("Jan", "Gaideczka",
//			"jan.gaideczka@htwg-konstanz.de", "user3");
//
//	static Advert advert1 = new Advert("Angebot", "Elektronik",
//			"Ich verleihe meine Bohrmaschine mit sämtlichem Zubehör", user2);
//	static Advert advert2 = new Advert("Gesuch", "Elektronik",
//			"Hallo zusammen, ich suche eine Bohrmaschine", user1);
//
//	static Advert advert3 = new Advert(
//			"Angebot",
//			"Fahrzeuge",
//			"Hallo zusammen, ich vermiete für eine Woche meien Golf V. Da ich im Urlaub bin. Liebe Grüße Jan",
//			user3);

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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	public static void setUserList(List<User> userList) {
		Model.userList = userList;
	}

	public static List<Advert> getAdvertList() {
		
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advert");
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()){
				Advert advert = new Advert();
				advert.setKind(resultset.getString("Kind"));
				advert.setCategory(resultset.getString("Category"));
				//advert.setUser(resultset.getString("AdvertUserID"));
				advert.setDescription(resultset.getString("Description"));
				advertList.add(advert);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return advertList;
	}

	public static void setAdvertList(List<Advert> advertList) {
		Model.advertList = advertList;
	}


}
