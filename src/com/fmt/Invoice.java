package com.fmt;

import java.util.List;

public class Invoice {

	private String invoiceCode;
	private Store storeCode;
	private Person customerCode;
	private Person salespersonCode;
	private String invoiceDate;
	private List<Item> listItem;

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

}
