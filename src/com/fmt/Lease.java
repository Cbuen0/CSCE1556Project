package com.fmt;

import java.time.LocalDate;
import java.time.Period;

public class Lease extends Equipment {

	private double leaseRate;
	private String startDate;
	private String endDate;

	public Lease(String code, String name, String model, double leaseRate, String startDate, String endDate) {
		super(code, name, model);
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

	public long getMonths() {
		LocalDate startDate = LocalDate.parse(this.startDate);
		LocalDate endDate = LocalDate.parse(this.endDate);
		Period period = Period.between(startDate, endDate);
		long months = period.toTotalMonths();

		return months;
	}

	public Lease(Lease l, double leaseRate, String startDate, String endDate) {
		super(l.getCode(), l.getName(), l.getModel());
		this.leaseRate = l.getLeaseRate();
		this.startDate = l.getStartDate();
		this.endDate = l.getEndDate();
	}

	public double getTaxes() {
		double taxes = 0;
		if (getTotal() < 10000) {
			taxes = 0;
		}else if (getTotal() >= 10000 && getTotal() < 100000) {
			taxes = 500;
		}else if (getTotal() >= 100000) {
			taxes = 1500;
		}
		return taxes;
	}

	public double getTotal() {
		double total = leaseRate * getMonths();
		return total;
	}
	

}
