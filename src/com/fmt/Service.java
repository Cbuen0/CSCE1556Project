package com.fmt;

/**
* 
*Author: Carlos Bueno & Sowparnika Sandhya
*Date: 2023-02-24
*
*This class represents Services.
*
*/
public class Service extends Item{
	private double hourlyRate;
	private double hoursBilled;

	public Service(String code, String name, double hourlyRate) {
		super(code, name);
		this.hourlyRate = hourlyRate;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	@Override
	public String toString() {
		return this.getCode() + " " + this.getName() + " " + hourlyRate;
	}	
	
	public double getTaxes() {
		double tax = getTotal() * .0345;
		return tax;
	}
	
	public Service(Service s, double hoursBilled) {
		super(s.getCode(), s.getName());
		this.hourlyRate = s.getHourlyRate();
		this.hoursBilled = hoursBilled;
	}
	@Override
	public double getTotal() {
		double total = hoursBilled * hourlyRate;
		return total;
	}
	
}
