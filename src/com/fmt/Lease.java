package com.fmt;

public class Lease extends Equipment{

	private double leaseRate;
	private String startDate;
	private String endDate;
	
	public Lease(String code, String type, String name, String model, double leaseRate, String startDate,
			String endDate) {
		super(code, type, name, model);
		this.leaseRate = leaseRate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public double getLeaseRate() {
		return leaseRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public double getTaxes() {
		return  0;
	}
	//TODO: proper get tax calculation
	
}
