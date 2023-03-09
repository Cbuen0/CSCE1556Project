package com.fmt;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Invoice {

	private String invoiceCode;
	private Store storeCode;
	private Person customerCode;
	private Person salespersonCode;
	private String invoiceDate;
	private Map<String, Item> listItem;

	public Invoice(String invoiceCode, Store storeCode, Person customerCode, Person salespersonCode,
			String invoiceDate) {
		super();
		this.invoiceCode = invoiceCode;
		this.storeCode = storeCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public Store getStoreCode() {
		return storeCode;
	}

	public Person getCustomerCode() {
		return customerCode;
	}

	public Person getSalespersonCode() {
		return salespersonCode;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}
	
	public void addItem(Item item) {
		listItem.put(item.getCode(), item);
	}

	public Invoice(Invoice i, Map<String, Item> listItem) {
		super();
		this.invoiceCode = i.getInvoiceCode();
		this.storeCode = i.getStoreCode();
		this.customerCode = i.getCustomerCode();
		this.salespersonCode = i.getSalespersonCode();
		this.invoiceDate = i.getInvoiceDate();
		this.listItem = listItem;
	}
	
	public String toString() {
		return null;
		//TODO: return toString
	}
	
}
