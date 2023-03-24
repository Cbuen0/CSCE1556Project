package com.fmt;

/**
* Author: Carlos Bueno, Sowparnika Ssandhya
* Date: 2023-03-10
* 
* This class models a purchase for equipment. 
*/

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
	
	//EDIT THIS OUT WHEN SAFE
//	public double getPurchasePrice() {
//		return this.purchasePrice;
//	}
	
	public double getTotal() {
		return this.purchasePrice;
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
