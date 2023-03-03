package com.fmt;

/**
* 
*Author: Carlos Bueno & Sowparnika Sandhya
*Date: 2023-02-24
*
*This class represents Product.
*
*/
public class Product extends Item{
	private String unit;
	private double unitPrice;
	public Product(String code, String name, String type, String unit, double unitPrice) {
		super(code, type, name);
		this.unit = unit;
		this.unitPrice = unitPrice;
	}
	public String getUnit() {
		return unit;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	@Override
	public String toString() {
		return this.getCode() + " " + this.getType() + " " + this.getName() + " " + unit + " " + unitPrice + " ";
	}
	
	public double getTaxes() {
		return unitPrice*.0715;
	}
}
