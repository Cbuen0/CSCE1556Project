package com.fmt;

/**
 * 
 * Author: Carlos Bueno & Sowparnika Sandhya Date: 2023-02-24
 *
 * This class represents Services.
 *
 */
public class Service extends Item {

	private double hourlyRate;
	private double hoursBilled;

	public Service(String code, String name, String type, double hourlyRate) {
		super(code, name, type);
		this.hourlyRate = hourlyRate;
	}

	public Service(String code, String name, String type, double hourlyRate, double hoursBilled) {
		super(code, name, type);
		this.hourlyRate = hourlyRate;
		this.hoursBilled = hoursBilled;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public double getHoursBilled() {
		return hoursBilled;
	}

	@Override
	public String toString() {
		return this.getCode() + " " + " " + this.getName() + " " + hourlyRate;
	}

	public double getTaxes() {
		return Math.round(getTotal() * .0345 * 100) / 100.0;
	}

	public double getTotal() {
		return Math.round(hoursBilled * hourlyRate * 100) / 100.0;
	}

}
