package com.fmt;

public class Purchase extends Equipment {
	
	private String contract;
	private double purchasePrice;

	public Purchase(String code, String name, String type, String model, String contract, double purchasePrice) {
		super(code, name, type, model);
		this.purchasePrice = purchasePrice;
		this.contract = contract;
	}

	public String getContract() {
		return this.contract;
	}
	
	public double getPurchasePrice() {
		return this.purchasePrice;
	}
	
	public double getTotal() {
		return this.getPurchasePrice();
	}
	
	public double getTaxes() {
		return 0;
	}
	
	public String ItemInfotoString() {
		return (String.format("\n%s     (Purchase)    %s        \n 					\t\t\t%.2f",
				this.getCode(),
				this.getName(),
				this.getTotal()));
	}
}
