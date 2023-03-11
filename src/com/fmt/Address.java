package com.fmt;

/**
 * 
 *Author: Carlos Bueno & Sowparnika Sandhya
 *Date: 2023-02-24
 *
 *This class models an Address
 *
 */
public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	public Address(String street, String city, String state, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}
	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}
	@Override
	public String toString() {
		return street + "\n\t" + city + " "+ state + " " + zip + " " + country;
	}
	
}
