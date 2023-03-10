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

	public Invoice(Invoice i, Map<String, Item> listItem) {
		super();
		this.invoiceCode = i.getInvoiceCode();
		this.store = i.getStore();
		this.customer = i.getCustomer();
		this.salesManager = i.getSalesManager();
		this.date = i.getDate();
		this.mapItem = listItem;
	}
	
	
	public String toString() {
	    StringBuilder in = new StringBuilder();
	    in.append("Invoice  #" + invoiceCode + "\n");
	    in.append("Store    #" + store.getStoreCode() + "\n");
	    in.append("Date     " + date);
	    in.append(String.format("%s, %s", this.invoiceCode, this.salesManager));
	    return in.toString();
	}
	
	public String SummaryReporttoString() {
		return (String.format("%s\t %s \t%s", this.invoiceCode, this.store.getStoreCode(), this.customer.getName()));
	}
	

	
	public double getTax() {
	    double totalTax = 0;
	    for (Map.Entry<String, Item> entry : mapItem.entrySet()) {
	        Item item = entry.getValue();
	        totalTax += item.getTaxes() * item.getQuantity();
	    }
	    return totalTax;
	}

	public double getTotal() {
	    double totalPrice = 0;
	    for (Map.Entry<String, Item> entry : mapItem.entrySet()) {
	        Item item = entry.getValue();
	        totalPrice += item.getPrice() * item.getQuantity();
	    }
	    return totalPrice + getTax();
	}


	
	
	//iterate over Item map and pull out tax and price and total them
	//make a getTax method for them
}
