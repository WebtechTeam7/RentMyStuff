package models;

public class Address {

	private String street;
	private String postcode;
	private String city;

	public Address() {

	}

	public Address(String street, String postcode, String city) {
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
