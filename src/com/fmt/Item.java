package com.fmt;

/**
* 
*Author: Carlos Bueno & Sowparnika Sandhya
*Date: 2023-02-24
*
*This class models an Item.
*
*/
public abstract class Item {
	private String code;
	private String type;
	private String name;
	public Item(String code, String type, String name) {
		super();
		this.code = code;
		this.type = type;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	
	public abstract double getTaxes();
	
	}
