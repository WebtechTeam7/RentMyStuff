package models;

import java.util.ArrayList;
import java.util.List;

public class Model {

	public Model() {

	}

	private static List<User> userList = new ArrayList<User>();
	private static List<Advert> advertList = new ArrayList<Advert>();

	static User user1 = new User("Dennis", "Klein",
			"dennis.klein@htwg-konstanz.de", "user1");
	static User user2 = new User("Ramona", "Barth",
			"ramona.bart@htwg-konstanz.de", "user2");
	static User user3 = new User("Jan", "Gaideczka",
			"jan.gaideczka@htwg-konstanz.de", "user3");

	static Advert advert1 = new Advert("Angebot", "Elektronik",
			"Ich verleihe meine Bohrmaschine mit sämtlichem Zubehör");
	static Advert advert2 = new Advert("Gesuch", "Dienstleistung",
			"Hallo zusammen, ich suche eine Bohrmaschine");
	
	static Advert advert3 = new Advert("Angebot", "Fahrzeug",
			"Hallo zusammen, ich vermiete für eine Woche meien Golf V. Da ich im Urlaub bin. Liebe Grüße Jan");

	public static List<User> getUserList() {
		return userList;
	}

	public static void setUserList(List<User> userList) {
		Model.userList = userList;
	}

	public static List<Advert> getAdvertList() {
		return advertList;
	}

	public static void setAdvertList(List<Advert> advertList) {
		Model.advertList = advertList;
	}

	public static void createObject() {
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		advertList.add(advert1);
		advertList.add(advert2);
		advertList.add(advert3);
		
	}

}
