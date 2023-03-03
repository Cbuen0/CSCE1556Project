package com.fmt;

/**
* 
*Author: Carlos Bueno & Sowparnika Sandhya
*Date: 2023-02-24
*
*This class models a Store.
*
*/
public class Store {
	private String storeCode;
	private Person manager;
	private Address address;
	public Store(String storeCode, Person manager, Address address) {
		super();
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public Person getManager() {
		return manager;
	}
	public Address getAddress() {
		return address;
	}
	@Override
	public String toString() {
		return storeCode + " " + manager + " " + address + " ";
	}
}
