package com.fmt;

/**
 * 
 * 
 *Author: Carlos Bueno & Sowparnika Sandhya
 *Date: 2023-02-24
 *
 *This class represents Equipment-Item
 *
 */
public class Equipment extends Item {
	private String model;

	public Equipment(String code, String type, String name, String model) {
		super(code, type, name);
		this.model = model;
	}
	public String getModel() {
		return model;
	}
	@Override
	public String toString() {
		return this.getCode() + " " + this.getType() + " " + this.getName() + " " + this.model;
	}
	@Override
	public double getTaxes() {
		return 0;
	}
}
//TODO: proper get tax calculation