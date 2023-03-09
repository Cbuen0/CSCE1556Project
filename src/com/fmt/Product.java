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
	private int quantity;

	public Product(String code, String name, String unit, double unitPrice) {
		super(code, name);
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
		return this.getCode() + " " + this.getName() + " " + unit + " " + unitPrice + " ";
	}

	public double getTaxes() {
		return unitPrice * .0715;
	}

	public Product(Product p, int quantity) {
		super(p.getCode(), p.getName());
		this.unit = p.getUnit();
		this.unitPrice = p.getUnitPrice();
		this.quantity = quantity;
	}

	@Override
	public double getTotal() {
		double total = unitPrice * quantity;
		return total;
	}
}