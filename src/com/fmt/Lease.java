package com.fmt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
	
	
	public Lease(Lease l, double leaseRate, String startDate, String endDate) {
		super(l.getCode(), l.getType(), l.getName(), l.getModel());
		this.leaseRate = l.getLeaseRate();
		this.startDate = l.getStartDate();
		this.endDate = l.getEndDate();
	}
	
	public double getTaxes() {
		return 0;
	}
	//TODO: proper get tax calculation, go through expected and find out 
	// what you need for each class and what you can add and where as well as
	//calculations and date time calculations
	
	
	
}
