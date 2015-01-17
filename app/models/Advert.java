package models;

import play.*;


public class Advert {

	public Advert(){
		
	}
	
	public Advert(String kind, String category, String description,Address address, User user){
		this.kind = kind;
		this.category = category;
		this.description = description;
		this.user = user;
		this.address = address;
	}
	private int id;
	private String kind;
	private	String category;
	private String description;
	private User user;
	private Address address;
	private String date;
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
