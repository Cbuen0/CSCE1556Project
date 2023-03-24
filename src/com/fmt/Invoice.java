package com.fmt;

/**
* Author: Carlos Bueno, Sowparnika Ssandhya
* Date: 2023-03-10
* 
* This class models the invoices of sales. 
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Invoice {

	private String invoiceCode;
	private Store store;
	private Person customer;
	private Person salesManager;
	private String date;
	private List<Item> items;

	public Invoice(String invoiceCode, Store store, Person customer, Person salesManager, String date) {
		super();
		this.invoiceCode = invoiceCode;
		this.store = store;
		this.customer = customer;
		this.salesManager = salesManager;
		this.date = date;
		this.items = new ArrayList<Item>();
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

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public Invoice(Invoice i, List<Item> listItem) {
		super();
		this.invoiceCode = i.getInvoiceCode();
		this.store = i.getStore();
		this.customer = i.getCustomer();
		this.salesManager = i.getSalesManager();
		this.date = i.getDate();
		this.items = listItem;
	}

	public String invtoString() {
		StringBuilder in = new StringBuilder();
		in.append("Invoice  #" + this.invoiceCode + "\n");
		in.append("Store    #" + this.store.getStoreCode() + "\n");
		in.append("Date      " + date);
		in.append("\n");
		in.append("\nCustomer:\n" + this.customer);
		in.append("\n");
		in.append("\nSales Person:\n" + this.salesManager);
		return in.toString();
	}

	public String toSummaryReportString() {
		return (String.format("%s\t   %s \t%s \t%.2f \t%.2f", this.invoiceCode, this.store.getStoreCode(),
				this.customer.getName(), this.getTaxes(), this.getTotal()));
	}

	public String toSalesSummaryReportString() {
		return (String.format("%s\t   %s \t", this.store.getStoreCode(), this.salesManager.getName()));
	}

	public double getTaxes() {
		double totalTax = 0;
		for (int i = 0; i < items.size(); i++) {
			totalTax += items.get(i).getTaxes();
		}
		return totalTax;
	}

	public double getTotal() {
		double totalPrice = 0;
		for (int i = 0; i < items.size(); i++) {
			totalPrice += items.get(i).getTotal();
		}

		return totalPrice;
	}

	public double getGrandTotal() {
		return getTaxes() + getTotal();
	};

	// Compares Totals and sorts by totals
	public static HashMap<String, Invoice> sortByTotal(HashMap<String, Invoice> hm) {
		
		// Create a list from elements of HashMap
		List<Map.Entry<String, Invoice>> list = new LinkedList<Map.Entry<String, Invoice>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Invoice>>() {
			public int compare(Map.Entry<String, Invoice> o1, Map.Entry<String, Invoice> o2) {
				if (o1.getValue().getGrandTotal() > o2.getValue().getGrandTotal()) {
					return -1;
				} else if (o1.getValue().getGrandTotal() > o2.getValue().getGrandTotal()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		HashMap<String, Invoice> temp = new LinkedHashMap<String, Invoice>();
		for (Map.Entry<String, Invoice> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

}