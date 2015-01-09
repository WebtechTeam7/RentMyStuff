package controllers;

import org.mindrot.jbcrypt.*;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import models.*;

public class Application extends Controller {

	// static Boolean angemeldet = false;
	// private static User currentUser;

	public static User getUserFromSession() {
		String userCode = "";
		userCode = session("USER");
		try {
			if (userCode.equals("")) {
				return null;
			} else {
				Integer userid = new Integer(userCode);

				for (User user : Model.getInstance().getUserList()) {
					if (user.getUserID() == userid) {
						System.out.println("getUserFromSession: get "
								+ user.getFirstname() + " User from Session");
						return user;
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println("getUserFromSession: " + e
					+ " because of no User loged in");
		}

		return null;
	}

	public static void addUserToSession(User user) {
		Integer userid = new Integer(user.getUserID());
		System.out.println("addUserToSession: Add User " + userid
				+ " to session");
		session("USER", userid.toString());
	}

	public static Result index() {

		if (isUserInSession()) {
			return ok(index.render(Model.getInstance().getUserAdvertList(
					getUserFromSession().getUserID())));
		} else {
			return ok(login.render());
		}
	}

	public static Result login() {

		if (isUserInSession()) {
			session().clear();
			System.out.println("abmelden hat funktioniert");
		}
		return ok(login.render());
	}

	public static Result angebote() {

		if (isUserInSession()) {
			return ok(angebote.render(Model.getInstance().getAdvertList()));
		} else {
			return ok(login.render());
		}
	}

	public static Result gesuche() {

		if (isUserInSession()) {
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

	public static Result getAdvertList() {
		return ok(ReloadAdvert.render(Model.getInstance().getAdvertList()));
	}
	public static Result getSearchList() {
		return ok(searchAdvert.render(Model.getInstance().getAdvertList()));
	}

	public static Result registrieren() {
		return ok(registrieren.render());
	}

	public static Result newAdvert(String optradio, String kategorie,
			String comment) {

		Model.getInstance().createAdvert(optradio, kategorie, comment,
				getUserFromSession());
		return ok(index.render(Model.getInstance().getUserAdvertList(
				getUserFromSession().getUserID())));
	}

	public static Result deleteAdvert(int id, int userId) {
		Model.getInstance().deleteAdvert(id, userId);
		return ok(index.render(Model.getInstance().getUserAdvertList(
				getUserFromSession().getUserID())));
	}

	public static Result anmelden() {

		DynamicForm dynamicForm = Form.form().bindFromRequest();

		String email = dynamicForm.get("email");
		String password = dynamicForm.get("password");

		for (User user : Model.getInstance().getUserList()) {
			if (email.equals(user.getEmail())
					&& BCrypt.checkpw(password, user.getPassword())) {
				System.out.println("Benutzername und Passwort stimmen");
				addUserToSession(user);

				System.out.println("anmelden hat funktioniert");

				return ok(index.render(Model.getInstance().getUserAdvertList(
						getUserFromSession().getUserID())));

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
			// User user = new User(firstname, lastname, email, password);

			String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());

			Model.getInstance().createUser(firstname, lastname, email,
					hashPassword);

			for (User user : Model.getInstance().getUserList()) {

				if (email.equals(user.getEmail())
						&& BCrypt.checkpw(password, user.getPassword())) {

					addUserToSession(user);

					System.out.println("addUser: " + session().get("USER")
							+ " User from Session");

					return ok(index
							.render(Model.getInstance().getUserAdvertList(
									getUserFromSession().getUserID())));
				}
			}
			return ok(fehler.render());

		} else {
			return ok(fehler.render());
		}

	}

	public static Result deleteUser(int userId) {
		Model.getInstance().deleteUser(userId);
		session().clear();
		return ok(login.render());
	}

	public static boolean isUserInSession() {
		User user = getUserFromSession();
		if (user == null) {
			return false;
		} else {

			return true;
		}

	}

}
