package com.fmt;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Invoice {

	private String invoiceCode;
	private Store store;
	private Person customer;
	private Person salesManager;
	private String date;
	private Map<String, Item> mapItem;

	public Invoice(String invoiceCode, Store store, Person customer, Person salesManager,
			String date) {
		super();
		this.invoiceCode = invoiceCode;
		this.store = store;
		this.customer = customer;
		this.salesManager = salesManager;
		this.date = date;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public Store getStore() {
		return store;
	}

	public Person getCustomer() {
		return customer;
	}

	public Person getSalesManager() {
		return salesManager;
	}

	public String getDate() {
		return date;
	}
	
	public void addItem(Item item) {
		mapItem.put(item.getCode(), item);
	}

	public Invoice(Invoice i, Map<String, Item> mapItem) {
		super();
		this.invoiceCode = i.getInvoiceCode();
		this.store = i.getStore();
		this.customer = i.getCustomer();
		this.salesManager = i.getSalesManager();
		this.date = i.getDate();
		this.mapItem = mapItem;
	}
	
	
	public String toStringHeader() {
	    StringBuilder in = new StringBuilder();
	    in.append("Invoice  #" + invoiceCode + "\n");
	    in.append("Store    #" + store.getStoreCode() + "\n");
	    in.append("Date     " + date);
	    in.append(String.format("%s, %s", this.invoiceCode, this.salesManager));
	    return in.toString();
	}
	
	//this is just a test i was doing to see if i could get the methods under testing to work
	//it is called in invoice report which is giving an error
	public String ContentsTesttoString() {
		return (String.format("%s\t %s \t%s", this.invoiceCode, this.store.getStoreCode(), this.customer.getName(), this.getTax(), this.getTotal()));
	}
	
	//To run program without testing use this method in the invoice report toString method
	public String SummaryReporttoString() {
		return (String.format("%s\t %s \t%s", this.invoiceCode, this.store.getStoreCode(), this.customer.getName()));
	}
	
	public String StoreSalestoString() {
		return (String.format("%s \t\t%s ", this.store.getStoreCode(), this.salesManager.getName()));
	}
	
	////////////////////////////////////////////////////TESTING
	public double getTax() {
		double totalTax = 0.0;
		for(Item item : mapItem.values()) {
			double tax = item.getTaxes();
			totalTax += tax;
		}
		return totalTax;
	}

	public double getTotal() {
		double totalPrice = 0.0;
		for(Item item : mapItem.values()) {
			double price = item.getPrice();
			totalPrice += price * item.getQuantity();
		}
		return totalPrice;
	}
	
	public int getTotalItems() {
		int totalItems = 0;
		for(Item item : mapItem.values()) {
			totalItems += item.getQuantity();
		}
		return totalItems;
	}
	//////////////////////////////////////////////////////////
	//iterate over Item map and pull out tax and price and total them
	//make a getTax method for them
	
	//TODO: ask question about output, says that mapItem is null 
	//Ask where to put the toStrings for the person, and item details
	//ask if the methods above are wrong bc error keeps popping up with them saying
	//mapItem is null
	//How to find the num Items in the summary report 
}
