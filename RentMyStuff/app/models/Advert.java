package models;

import play.*;


public class Advert {

	public Advert(){
		
	}
	
	public Advert(String kind, String category, String description, User user){
		this.kind = kind;
		this.category = category;
		this.description = description;
		this.setUser(user);
	}
	
	private String kind;
	private	String category;
	private String description;
	private boolean file_existing;
	private User user;
	
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
	public boolean isFile_existing() {
		return file_existing;
	}
	public void setFile_existing(boolean file_existing) {
		this.file_existing = file_existing;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
