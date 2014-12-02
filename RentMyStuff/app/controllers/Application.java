package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import models.*;

public class Application extends Controller {

	private static boolean dummyInitialize = false;

	public static Result index() {
		return ok(index.render(Model.getAdvertList()));
	}

	public static Result login() {
		if (dummyInitialize == false) {
			Model.createObject();
			dummyInitialize = true;
		} 
		return ok(login.render());
	}

	public static Result angebote() {
		return ok(angebote.render(Model.getAdvertList()));
	}

	public static Result gesuche() {
		return ok(gesuche.render(Model.getAdvertList()));
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
	
	public static Result newAdvert(String optradio,String kategorie, String comment){
		Advert advert = new Advert(optradio, kategorie, comment, Model.getUserList().get(1));
		Model.getAdvertList().add(advert);
		return ok(index.render(Model.getAdvertList()));
	}

}
