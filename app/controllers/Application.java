package controllers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import models.Advert;
import models.Model;
import models.User;

import org.mindrot.jbcrypt.BCrypt;

import play.data.DynamicForm;
import play.data.Form;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.account;
import views.html.angebote;
import views.html.fehler;
import views.html.gesuche;
import views.html.impressum;
import views.html.index;
import views.html.inserat;
import views.html.kontakt;
import views.html.login;
import views.html.map;
import views.html.registrieren;
import views.html.reloadAdvert;
import views.html.searchAdvert;

public class Application extends Controller implements Observer {

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
			User user = getUserFromSession();
			return ok(index.render(
					Model.getInstance().getUserAdvertList(
							getUserFromSession().getUserID()), user));
		} else {
			return ok(login.render());
		}
	}

	public static Result login() {

		Model.getInstance().initDatabase();
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

	// Für Ajax

	public static Result getAngebotList(String category) {
		if (category.equals("")) {
			return ok(reloadAdvert.render(Model.getInstance().getAdvertList()));
		}
		List<Advert> list = Model.getInstance().getAdvertList(category);
		return ok(reloadAdvert.render(list));
	}

	public static Result getGesuchList(String category) {
		System.out.println(category);
		if (category.equals("")) {
			return ok(searchAdvert.render(Model.getInstance().getAdvertList()));
		}
		List<Advert> list = Model.getInstance().getAdvertList(category);
		return ok(searchAdvert.render(list));
	}

	public static Result registrieren() {
		return ok(registrieren.render());
	}

	public static Result map(String street, String city, String postcode,
			String country) {
		return ok(map.render(street, city, postcode, country));
	}

	public static Result newAdvert(String optradio, String kategorie,
			String comment, String street, String postcode, String city,
			String country) {

		User user = getUserFromSession();
		Model.getInstance().createAdvert(optradio, kategorie, comment, user,
				street, postcode, city, country);
		return ok(index.render(
				Model.getInstance().getUserAdvertList(user.getUserID()), user));

	}

	public static Result deleteAdvert(int id, int userId) {
		Model.getInstance().deleteAdvert(id, userId);
		User user = getUserFromSession();
		return ok(index.render(
				Model.getInstance().getUserAdvertList(
						getUserFromSession().getUserID()), user));
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

				return ok(index.render(
						Model.getInstance().getUserAdvertList(
								getUserFromSession().getUserID()), user));

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

					return ok(index.render(Model.getInstance()
							.getUserAdvertList(

							getUserFromSession().getUserID()), user));
				}

			}
			return ok(fehler.render());

		} else {
			return ok(fehler.render());
		}

	}

	public static Result account() {
		return ok(account.render());
	}

	public static Result loeschen() {
		DynamicForm dynamicForm = Form.form().bindFromRequest();

		String email = dynamicForm.get("email");
		String password = dynamicForm.get("password");

		for (User user : Model.getInstance().getUserList()) {
			if (email.equals(user.getEmail())
					&& BCrypt.checkpw(password, user.getPassword())) {
				Model.getInstance().deleteUser(user.getEmail(),
						user.getPassword());
				session().clear();
				System.out.println("Removed following user: "
						+ user.getFullName());
				return ok(login.render());

			}
		}
		return ok(fehler.render());
	}

	public static boolean isUserInSession() {
		User user = getUserFromSession();
		if (user == null) {
			return false;
		} else {

			return true;
		}

	}

	public static WebSocket<String> refresh() {
		
//		return WebSocket.withActor(new Function<ActorRef, Props>() {
//			
//			public Props apply(ActorRef out) throws Throwable {
//				System.out.println("refresh() - Method");
//				Model.add(new Application());
//				return MyWebSocketActor.props(out);
//			}
//		});
		
		return new WebSocket<String>(){

			@Override
			public void onReady(play.mvc.WebSocket.In<String> in,
					final play.mvc.WebSocket.Out<String> out) {
				
				in.onMessage(new Callback<String>() {
					
					public void invoke(String arg0) throws Throwable {
						Model.add(new Application());
						System.out.println("invoke- Method " + arg0);
						out.write(arg0 +  " out.write");
					}
				});
				
				in.onClose(new Callback0() {
					
					public void invoke() throws Throwable {
						Model.delete(new Application());
						System.out.println("invoke- Method");
						
					}
				});
				
				out.write("Hello you are connected!");
				
			} 
		};
	}

	public void update(Observable observable, Object object) {
		Advert advert = (Advert) object;
		// ToDo - was passiert beim Update?!;
		System.out.println(advert.getCategory());
	}

}
