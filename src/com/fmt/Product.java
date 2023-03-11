package com.fmt;

/**
 * 
 * Author: Carlos Bueno & Sowparnika Sandhya Date: 2023-02-24
 *
 * This class represents Product.
 *
 */
public class Product extends Item {
	private String unit;
	private double unitPrice;
	private double quantity;

	public Product(String code, String name, String type, String unit, double unitPrice) {
		super(code, name, type);
		this.unit = unit;
		this.unitPrice = unitPrice;
	}

	public Product(String code, String name, String type, String unit, double unitPrice, double quantity) {
		super(code, name, type);
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.quantity = quantity;

	}

	public String getUnit() {
		return unit;
	}

	public double getUnitPrice() {
		return unitPrice;
	}
	
	public double getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return this.getCode() + " " + " " + this.getName() + " " + unit + " " + unitPrice + " ";
	}

	@Override
	public double getTaxes() {
		return Math.round(getTotal() * 0.0715 * 100)/ 100.0;
	}

	@Override
	public double getTotal() {
		return Math.round(unitPrice * quantity * 100)/100.0;
	}

	public double getGrandTotal() {
		return getTotal() + getTaxes();

	}
}
