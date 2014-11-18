package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
public class Application extends Controller {
   
    

	public static Result index() {
        return ok(index.render());
    }
<<<<<<< HEAD
  
=======
	
	public static Result login(){
		return ok(login.render());
	}

	public static Result angebote(){
		return ok(angebote.render());
	}
	public static Result gesuche(){
		return ok(gesuche.render());
	}
	
	public static Result kontakt(){
		return ok(kontakt.render());
	}
	
	public static Result impressum(){
		return ok(impressum.render());
	}
	
	public static Result inserat(){
		return ok(inserat.render());
	}
	
	public static Result registrieren(){
		return ok(registrieren.render());
	}

>>>>>>> 9f05898e0cdf1664d292770ff88a68c08812df7c
}
