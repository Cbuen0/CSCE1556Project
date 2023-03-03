package com.fmt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * Author: Carlos Bueno & Sowparnika Sandhya
 * Date: 2023-02-24
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
	 * @param mapPerson
	 * @return 
	 */
	public static List<Store> parseStoreFile(Map<String, Person> mapPerson) {
		mapPerson = mapPersonFile();
		List<Store> result = new ArrayList<Store>();
		File f = new File("data/Stores.csv");
		try (Scanner s = new Scanner(f)) {
			s.nextLine();
			while (s.hasNext()) {
				String line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Store e = null;
					String tokens[] = line.split(",");
					String storeCode = tokens[0];
					Person managerCode = mapPerson.get(tokens[1]);
					String street = tokens[2];
					String city = tokens[3];
					String state = tokens[4];
					String zip = tokens[5];
					String country = tokens[6];

					Address a = new Address(street, city, state, zip, country);

					e = new Store(storeCode, managerCode, a);
					result.add(e);
				}
			}
			s.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * Parses Item.csv File and returns a string
	 * @return
	 */
	public static List<Item> parseItemFile() {
		List<Item> result = new ArrayList<Item>();
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
					e = new Equipment(code, type, name, model);
				} else if (type.equals("P")) {
					String unit = tokens[3];
					double unitPrice = Double.parseDouble(tokens[4]);
					e = new Product(code, type, name, unit, unitPrice);
				} else if (type.equals("S")) {
					double hourlyrate = Double.parseDouble(tokens[3]);
					e = new Service(code, type, name, hourlyrate);
				}
				result.add(e);
			}
		}
		s.close();

		return result;
	}
}
