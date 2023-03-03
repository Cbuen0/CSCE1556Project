package com.fmt;

public class Purchase extends Equipment {
	
	private double purchasePrice;

	public Purchase(String code, String type, String name, String model, double purchasePrice) {
		super(code, type, name, model);
		this.purchasePrice = purchasePrice;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public double getTaxes() {
		return 0;
	}
	
	
	
	
}
