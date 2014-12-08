package models;

import play.*;


public class User {
	
	public User(){
		
	}
	
	public User(String firstname, String lastname, String email, String pw, boolean online){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = pw;
		this.online = online;
	}
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean online = false;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName(){
		return firstname + " " + lastname;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	

}
