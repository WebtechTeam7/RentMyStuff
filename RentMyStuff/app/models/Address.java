package models;

public class Address {

	private String street;
	private String postcode;
	private String city;
	private String country;

	public Address() {

	}

	public Address(String street, String postcode, String city, String country) {
		this.street = street;
		this.postcode = postcode;
		this.city = city;
		this.setCountry(country);
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
