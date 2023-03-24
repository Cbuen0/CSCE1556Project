package com.fmt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	//Compares person names and sorts by alphabetically 
	public static HashMap<String, Store> sortByNames(HashMap<String, Store> hm) {

		List<Map.Entry<String, Store>> list = new LinkedList<Map.Entry<String, Store>>(hm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Store>>() {
			public int compare(Map.Entry<String, Store> o1, Map.Entry<String, Store> o2) {
				int value;
				value = o1.getValue().getManager().getLastName().compareTo(o2.getValue().getManager().getLastName());
				if (value == 0) {
					value = o1.getValue().getManager().getFirstName()
							.compareTo(o2.getValue().getManager().getFirstName());
				}
				if (value == 0) {
					if (o1.getValue().getGrandTotal() > o2.getValue().getGrandTotal()) {
						value = -1;
					} else if (o1.getValue().getGrandTotal() < o2.getValue().getGrandTotal()) {
						return 1;
					} else {
						return 0;
					}
				}
				return value;
			}
		});

		HashMap<String, Store> temp = new LinkedHashMap<String, Store>();
		for (Map.Entry<String, Store> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

}
