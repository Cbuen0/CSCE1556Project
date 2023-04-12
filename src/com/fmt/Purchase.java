package com.fmt;

/**
* Author: Carlos Bueno, Sowparnika Ssandhya
* Date: 2023-03-10
* 
* This class models a purchase for equipment. 
*/

public class Purchase extends Equipment {
	
	//private String contract;
	private double purchasePrice;

	public Purchase(String code, String name, String model, double purchasePrice) {
		super(code, name, model);
		this.purchasePrice = purchasePrice;
		//this.contract = contract;
	}

	
//	public String getContract() {
//		return this.contract;
//	}
	
	public double getTotal() {
		return this.purchasePrice;
	}
	
	public double getTaxes() {
		return 0;
	}
	
	public String itemInfoToString() {
		return (String.format("\n%s     (Purchase)    %s-%s        \n 					\t\t\t%.2f",
				this.getCode(),
				this.getName(),
				this.getModel(),
				this.getTotal()));
	}
}