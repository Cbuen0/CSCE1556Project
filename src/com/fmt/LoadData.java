package com.fmt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * Author: Carlos Bueno & Sowparnika Sandhya Date: 2023-02-24
 * 
 * The program that loads data from CSV File and inputs into a list.
 */
public class LoadData {
	/*
	 * Parses Person.csv File and returns a String
	 *
	 */
	public static HashMap<String, Person> mapPersonFile() {
		HashMap<String, Person> result = new HashMap<>();
		File f = new File("data/Persons.csv");
		try (Scanner s = new Scanner(f)) {
			s.nextLine();
			while (s.hasNext()) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Person person = null;
					String tokens[] = line.split(",");
					String code = tokens[0];
					String lastName = tokens[1];
					String firstName = tokens[2];
					String street = tokens[3];
					String city = tokens[4];
					String state = tokens[5];
					String zip = tokens[6];
					String country = tokens[7];

					Address a = new Address(street, city, state, zip, country);

					List<String> emails = new ArrayList<>();
					for (int i = 8; i < tokens.length; i++) {
						emails.add(tokens[i]);
					}
					person = new Person(code, lastName, firstName, a, emails);
					result.put(code, person);
				}
			}
			s.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Parses Store.csv File and returns a string
	 * 
	 * @param mapPerson
	 * @return
	 */
	public static HashMap<String, Store> parseStoreFile(Map<String, Person> mapPerson) {
		HashMap<String, Store> storeMap = new HashMap<String, Store>();
		File f = new File("data/Stores.csv");
		try (Scanner s = new Scanner(f)) {
			s.nextLine();
			while (s.hasNext()) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					String tokens[] = line.split(",");
					String storeCode = tokens[0];
					Person managerCode = mapPerson.get(tokens[1]);
					String street = tokens[2];
					String city = tokens[3];
					String state = tokens[4];
					String zip = tokens[5];
					String country = tokens[6];
					Address a = new Address(street, city, state, zip, country);
					Store store = new Store(storeCode, managerCode, a);
					storeMap.put(storeCode, store);
				}
			}
			s.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return storeMap;
	}

	/**
	 * Parses Item.csv File and returns a string
	 * 
	 * @return
	 */
	public static Map<String, Item> parseItemFile() {
	    Map<String, Item> itemMap = new HashMap<>();
	    File f = new File("data/Items.csv");
	    Scanner s = null;
	    try {
	        s = new Scanner(f);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    s.nextLine();
	    while (s.hasNext()) {
	        String line = s.nextLine();
	        if (!line.trim().isEmpty()) {
	            Item e = null;
	            String tokens[] = line.split(",");
	            String code = tokens[0];
	            String type = tokens[1];
	            String name = tokens[2];

	            if (type.equals("E")) {
	                String model = tokens[3];
	                e = new Equipment(code, name, model);
	            } else if (type.equals("P")) {
	                String unit = tokens[3];
	                double unitPrice = Double.parseDouble(tokens[4]);
	                e = new Product(code, name, unit, unitPrice);
	            } else if (type.equals("S")) {
	                double hourlyrate = Double.parseDouble(tokens[3]);
	                e = new Service(code, name, hourlyrate);
	            }
	            itemMap.put(code, e);
	        }
	    }
	    s.close();

	    return itemMap;
	}


	public static HashMap<String, Invoice> parseInvoiceDataFile() {

		HashMap<String, Person> persons = mapPersonFile();
		HashMap<String, Store> stores = parseStoreFile(persons);

		HashMap<String, Invoice> invoiceMap = new HashMap<String, Invoice>();

		File f = new File("data/Invoices.csv");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		while (s.hasNext()) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {
				String tokens[] = line.split(",");
				String invoiceCode = tokens[0];
				Store store = stores.get(tokens[1]);
				Person customer = persons.get(tokens[2]);
				Person salesperson = persons.get(tokens[3]);
				
				String invoiceDate = tokens[4];

				Invoice i = new Invoice(invoiceCode, store, customer, salesperson, invoiceDate);
				invoiceMap.put(invoiceCode, i);
			}
		}
		s.close();

		return invoiceMap;
	}

	
	public static void parseInvoiceItemFile(Map<String, Invoice> invoiceMap) {
		Map<String, Item> itemMap = parseItemFile();
		
		File f = new File("data/InvoiceItems.csv");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		while (s.hasNext()) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {
				String tokens[] = line.split(",");
				String invoiceCode = tokens[0];
				Item t = itemMap.get(tokens[1]);
				Item h = null;
				
				if(t instanceof Product) {
					int quantityPurchased = Integer.parseInt(tokens[2]);
					h = new Product((Product) t,quantityPurchased);
				
				}else if (t instanceof Service) {
					double hoursBilled = Double.parseDouble(tokens[2]);
					h = new Service((Service) t, hoursBilled);
				
				}else if (t instanceof Equipment) {
					String type = tokens[2];
					
					if(type.equalsIgnoreCase("P")) {
						double purchasePrice = Double.parseDouble(tokens[3]);
						h = new Purchase((Purchase) t, purchasePrice);
						
					}else if(type.equalsIgnoreCase("L")) {
						int rate = Integer.parseInt(tokens[3]);
						String startDate = tokens[4];
						String endDate = tokens[5];
						h = new Lease((Lease) t, rate, startDate, endDate);
					}
					
				}
				invoiceMap.get(invoiceCode).addItem(h);
				// takes invoice code and gets invoice and add item to that invoice
				
			}
		}
		s.close();

		return;
	}
	
}
