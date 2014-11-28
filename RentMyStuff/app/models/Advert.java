package models;

import play.*;


public class Advert {

	public Advert(){
		
	}
	
	private String kind;
	private	String category;
	private String description;
	private boolean file_existing;
	
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
	
	
	
}
