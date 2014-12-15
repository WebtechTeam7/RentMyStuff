package controllers;


import play.*;
import play.mvc.*;
import views.html.*;
import models.*;

public class Application extends Controller {

	static Boolean angemeldet = false;
	private static User currentUser;

	public static Result index() {

		if (angemeldet == true) {
			return ok(index.render(Model.getUserAdvertList(currentUser.getUserID())));
		} else {
			return ok(login.render());
		}
	}

	public static Result login() {

		if (angemeldet == true) {
			angemeldet = false;
			System.out.println("abmelden hat fuktioniert");
		}
		return ok(login.render());
	}

	public static Result angebote() {

		if (angemeldet == true) {
			return ok(angebote.render(Model.getAdvertList()));
		} else {
			return ok(login.render());
		}
	}

	public static Result gesuche() {

		if (angemeldet == true) {
			return ok(gesuche.render(Model.getAdvertList()));
		} else {
			return ok(login.render());
		}
	}

	public static Result kontakt() {
		return ok(kontakt.render());
	}

	public static Result impressum() {

		return ok(impressum.render());
	}

	public static Result inserat() {
		return ok(inserat.render());
	}

	public static Result registrieren() {
		return ok(registrieren.render());
	}

	public static Result newAdvert(String optradio, String kategorie,
			String comment) {

		Model.createAdvert(optradio, kategorie, comment, currentUser);
		return ok(index.render(Model.getUserAdvertList(currentUser.getUserID())));
	}

	public static Result deleteAdvert(int id, int userId) {
		Model.deleteAdvert(id, userId);
		return ok(index.render(Model.getUserAdvertList(currentUser.getUserID())));
	}

	public static Result anmelden(String email, String password) {

		for (User user : Model.getUserList()) {
			if (email.equals(user.getEmail())
					&& password.equals(user.getPassword())) {
				System.out.println("geht");
				angemeldet = true;
				System.out.println("hat funktioniert");
				currentUser = user;
				return ok(index.render(Model.getUserAdvertList(currentUser.getUserID())));
			}
			System.out.println("geht nicht");
		}

		return ok(login.render());
	}
	
	public static Result deleteUser(int userId){
		Model.deleteUser(userId);
		angemeldet = false;
		return ok(login.render());
	}

}
