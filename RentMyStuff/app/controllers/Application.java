package controllers;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class Application extends Controller {

	static Boolean angemeldet = false;
	private static User currentUser;

	public static Result index() {

		if (angemeldet == true) {
			return ok(index.render(Model.getInstance().getUserAdvertList(currentUser.getUserID())));
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
			return ok(angebote.render(Model.getInstance().getAdvertList()));
		} else {
			return ok(login.render());
		}
	}

	public static Result gesuche() {

		if (angemeldet == true) {
			return ok(gesuche.render(Model.getInstance().getAdvertList()));
		} else {
			return ok(login.render());
		}
	}

	public static Result kontakt() {
		return ok(kontakt.render());
	}

	public static Result fehler() {
		return ok(fehler.render());
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
	
	public static Result map(String street, String city, String postcode, String country){
		return ok(map.render(street, city, postcode, country));
	}


	public static Result newAdvert(String optradio, String kategorie,
			String comment, String street, String postcode, String city, String country) {

		Model.getInstance().createAdvert(optradio, kategorie, comment, currentUser, street, postcode, city, country);
		return ok(index.render(Model.getInstance().getUserAdvertList(currentUser.getUserID())));
	}

	public static Result deleteAdvert(int id, int userId) {
		Model.getInstance().deleteAdvert(id, userId);
		return ok(index.render(Model.getInstance().getUserAdvertList(currentUser.getUserID())));
	}

	public static Result anmelden() {

		DynamicForm dynamicForm = Form.form().bindFromRequest();

		String email = dynamicForm.get("email");
		String password = dynamicForm.get("password");

		for (User user : Model.getInstance().getUserList()) {
			if (email.equals(user.getEmail())
					&& password.equals(user.getPassword())) {
				System.out.println("geht");
				angemeldet = true;
				System.out.println("hat funktioniert");
				currentUser = user;
				return ok(index.render(Model.getInstance().getUserAdvertList(currentUser.getUserID())));

			}
			System.out.println("geht nicht");

		}

		// return ok(login.render());
		return ok(fehler.render());
	}

	public static Result createUser() {

		DynamicForm dynamicForm = Form.form().bindFromRequest();

		String firstname = dynamicForm.get("first_name");
		String lastname = dynamicForm.get("last_name");
		String email = dynamicForm.get("email");
		String password = dynamicForm.get("password");
		String password_confirmation = dynamicForm.get("password_confirmation");

		if (password.equals(password_confirmation)) {
			User user = new User(firstname, lastname, email, password);
			Model.getInstance().createUser(firstname, lastname, email, password);
			currentUser = user;

			return ok(index.render(Model.getInstance().getUserAdvertList(currentUser.getUserID())));
		} else {
			return ok(fehler.render());
		}

	}

	public static Result deleteUser(int userId){
		Model.getInstance().deleteUser(userId);
		angemeldet = false;
		return ok(login.render());
	}

}
