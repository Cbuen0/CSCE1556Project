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

	public Service(String code, String type, String name, double hourlyRate) {
		super(code, type, name);
		this.hourlyRate = hourlyRate;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	@Override
	public String toString() {
		return this.getCode() + " " + this.getType() + " " + this.getName() + " " + hourlyRate;
	}	
	
	public double getTaxes() {
		return 0;
	}
	//TODO: proper get tax calculation
	
	public Service(Service s, double hoursBilled) {
		super(s.getCode(), s.getType(), s.getName());
		this.hourlyRate = s.getHourlyRate();
		this.hoursBilled = hoursBilled;
	}
	
}
