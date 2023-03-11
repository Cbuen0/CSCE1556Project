package com.fmt;

/**
 * 
 * Author: Carlos Bueno & Sowparnika Sandhya Date: 2023-02-24
 *
 * This class models an Item.
 *
 */
public abstract class Item {
	private String code;
	private String name;
	private String type;

	public Item(String code, String name,String type) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public abstract double getTaxes();

	public abstract double getTotal();

	public double getGrandTotal() {
		return getTaxes() + getTotal();
	};

	// TODO: proper string method
	public String toString() {
		return String.format("penis");
	}
}