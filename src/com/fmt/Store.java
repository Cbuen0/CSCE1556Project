package com.fmt;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Author: Carlos Bueno & Sowparnika Sandhya Date: 2023-02-24
 *
 * This class models a Store.
 *
 */
public class Store {
	private String storeCode;
	private Person manager;
	private Address address;
	List<Invoice> invoices;

	public Store(String storeCode, Person manager, Address address) {
		super();
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
		this.invoices = new ArrayList<Invoice>();
	}

	public String getStoreCode() {
		return storeCode;
	}

	public Person getManager() {
		return manager;
	}

	public Address getAddress() {
		return address;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void addInvoice(Invoice inv) {
		this.invoices.add(inv);
	}

	public String managerAddressString() {
		return storeCode + " " + manager + " " + address + " ";
	}

	public double getGrandTotal() {
		double grandTotal = 0.0;
		for (int i = 0; i < this.invoices.size(); i++) {
			grandTotal += this.invoices.get(i).getTaxes() + this.invoices.get(i).getTotal();
		}
		return grandTotal;
	}

	@Override
	public String toString() {
		return String.format("%s \t   %s \t\t  %d \t\t$%8.2f", this.storeCode, this.manager.getName(),
				this.invoices.size(), this.getGrandTotal());
	}
}
