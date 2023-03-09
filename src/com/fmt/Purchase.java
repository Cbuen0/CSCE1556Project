package com.fmt;

public class Purchase extends Equipment {
	
	private double purchasePrice;

	public Purchase(String code,String name, String model, double purchasePrice) {
		super(code, name, model);
		this.purchasePrice = purchasePrice;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public double getTaxes() {
		return 0;
	}
	
	public double getTotal() {
		return purchasePrice; 
	}
	
	public Purchase(Purchase p, double purchasePrice) {
		super(p.getCode(), p.getName(), p.getModel());
		this.purchasePrice = purchasePrice;
	}
	
	
}
