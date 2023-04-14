package com.fmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: ALL Illegal state exceptions
//TODO: Logger statements 

public class DataBaseLoader {

	
// LOAD ADDRESS FROM DATABASE
	public static Address getDetailedAddress(int addressId) {

		Address a = null;

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = "Select a.street, a.city, s.state, a.zip,  c.country" + "	   From Address a"
				+ "    Join State s" + "    Join Country c" + "    On a.countryId = c.countryId "
				+ "    and a.stateId = s.stateId"
				+ "    Where addressId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String street = rs.getString("a.street");
				String city = rs.getString("a.city");
				String state = rs.getString("s.state");
				String zip = rs.getString("a.zip");
				String country = rs.getString("c.country");

				a = new Address(street, city, state, zip, country);

			} else {
				throw new IllegalStateException("No ");
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return a;
	}
	
	
	
	
//  LOAD PERSON FROM DATABASE
	public static Person getDetailedPerson(int personId) {
		Person p = null;

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = "Select p.personCode, p.lastName, p.firstName, p.addressId, p.personId"
				+ "	   From Person p"
				+ "    Join Address a"
				+ "    On p.addressId = a.addressId"
				+ "    Where p.personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String personCode = rs.getString("p.personCode");
				String lastName = rs.getString("p.lastName");
				String firstName = rs.getString("p.firstName");
				int addressId = rs.getInt("p.addressId");
				
				Address a = DataBaseLoader.getDetailedAddress(addressId);
				List<String> emails = DataBaseLoader.emailList(rs.getInt("p.personId"));

				p = new Person(personCode, lastName, firstName, a, emails);
				
				

			} else {
				throw new IllegalStateException("No ");
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return p;
	}
	
	
	//Load Persons and add to HashMap
	//TODO: is this allowed
	public static HashMap<String, Person> personMap(){
			HashMap<String, Person> personMap = new HashMap<String, Person>();
			Person p = null;

			Connection conn = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			String query = "Select p.personCode, p.lastName, p.firstName, p.addressId, p.personId"
					+ "	   From Person p"
					+ "    Join Address a"
					+ "    On p.addressId = a.addressId;";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(query);
		
				rs = ps.executeQuery();
				if (rs.next()) {
					String personCode = rs.getString("p.personCode");
					String lastName = rs.getString("p.lastName");
					String firstName = rs.getString("p.firstName");
					int addressId = rs.getInt("p.addressId");
					Address a = DataBaseLoader.getDetailedAddress(addressId);
					List<String> emails = DataBaseLoader.emailList(rs.getInt("p.personId"));

					p = new Person(personCode, lastName, firstName, a, emails);
					
					personMap.put(personCode, p);

				} else {
					throw new IllegalStateException("No ");
				}
				
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return personMap;
		}
	
	
	
	
// Load list of email's to add to person	
	public static List<String> emailList(int personId) {
		List<String> emailList = new ArrayList<String>();

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String emailQuery = "Select email"
				+ "	   From Email "
				+ "    Where personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(emailQuery);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while (rs.next()) {
				emailList.add(rs.getString("email"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return emailList;

	}
	
	
	//TODO: CHECK store, take in person?
	public static HashMap<Integer, Store> loadStore() {
		List<Store> storeList = new ArrayList<Store>();
		
		Store s = null;

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String storeQuery = "Select storeId, storeCode, managerId, addressId"
				+ "	From Store;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(storeQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int storeId = rs.getInt("storeId");
				String storeCode = rs.getString("storeCode");
				int managerId = rs.getInt("managerId");
				int addressId = rs.getInt("addressId");
				Address a = DataBaseLoader.getDetailedAddress(addressId);
				Person p = DataBaseLoader.getDetailedPerson(managerId);
				// storeId
				s = new Store(storeId, storeCode, p, a);
				
				storeList.add(s);
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		//must add invoices to each store here, iterating through these stores and adding the correct invoice
//		for (Store st : storeList) {
//			DataBaseLoader.invoiceList(st);
//			
//		}
		
		HashMap<Integer, Store> storeMap = new HashMap<Integer, Store>();
		for (Store store : storeList) {
			storeMap.put(store.getStoreId(), store);
		}
		
		return storeMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//////////////// Load list of Invoices to add to store	
//		public static HashMap<Integer, Invoice> invoiceList(Store store) {
//			List<Invoice> invList = new ArrayList<Invoice>();
//
//			Connection conn = null;
//
//			try {
//				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
//			} catch (SQLException e) {
//				System.out.println("SQLException: ");
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			}
//
//			String invoiceQuery = "Select invoiceId, invoiceCode, customerId, salespersonId, storeId, date"
//					+ "       From Invoice"
//					+ "       Where storeId = ?;";
//			
//
//			PreparedStatement ps = null;
//			ResultSet rs = null;
//
//			try {
//				ps = conn.prepareStatement(invoiceQuery);
//				ps.setInt(1, store.getStoreId());
//				rs = ps.executeQuery();
//				
//				while (rs.next()) {
//					int invoiceId = rs.getInt("invoiceId");
//					String invoiceCode = rs.getString("invoiceCode");
//					int customerId = rs.getInt("customerId");
//					int salespersonId = rs.getInt("salespersonId");
////					Store s = DataBaseLoader.loadStore();
//					//: If use store list this breaks^^^
//					Person c = DataBaseLoader.getDetailedPerson(customerId);
//					Person m = DataBaseLoader.getDetailedPerson(salespersonId);
//					String date = rs.getString("date");
//					// add object id's
//					Invoice i = new Invoice(invoiceId, invoiceCode, store, c, m, date);
//					
//					invList.add(i);
//					
//					//adds invoice to the store
//					store.addInvoice(i);
//					
//				}
//				rs.close();
//				ps.close();
//				conn.close();
//			} catch (SQLException e) {
//				System.out.println("SQLException: ");
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			}
//
//			//iterate through each invoice in store and follow the same pattern for adding items to the invoice item list
//			for (Invoice in : invList) {
//				DataBaseLoader.itemList(in);
//			}
//	
//			HashMap<String, Invoice> invoiceMap = new HashMap<String, Invoice>();
//			for (Invoice invoice : invList) {
//				invoiceMap.put(invoice.getInvoiceCode(), invoice);
//			} 
//			
//			return invoiceMap;
//
//		}
	/////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		public static HashMap<Integer, Invoice> getInvoices (HashMap<Integer, Store> stores) {
			List<Invoice> invList = new ArrayList<Invoice>();

			Connection conn = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			//get store code
			String invoiceQuery = "Select i.invoiceId, i.invoiceCode, i.customerId, i.salespersonId, date, s.storeId"
					+ "    From Invoice i"
					+ "    Join Store s"
					+ "    On i.storeId = s.storeId;";
			

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(invoiceQuery);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {
					int invoiceId = rs.getInt("invoiceId");
					String invoiceCode = rs.getString("invoiceCode");
					int customerId = rs.getInt("customerId");
					int salespersonId = rs.getInt("salespersonId");
					Person c = DataBaseLoader.getDetailedPerson(customerId);
					Person m = DataBaseLoader.getDetailedPerson(salespersonId);
					String date = rs.getString("date");
					Integer storeId = rs.getInt("s.storeId");
					Store storeCode = stores.get((storeId));
					
					// add object id's
					Invoice i = new Invoice(invoiceId, invoiceCode, storeCode, c, m, date);
					invList.add(i);
					storeCode.addInvoice(i);
					//adds invoice to the store
					
				}
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

//			//iterate through each invoice in store and follow the same pattern for adding items to the invoice item list
//			for (Invoice in : invList) {
//				DataBaseLoader.itemList(in);
//			}
	
			HashMap<Integer, Invoice> invoiceMap = new HashMap<Integer, Invoice>();
			for (Invoice invoice : invList) {
				invoiceMap.put(invoice.getInvoiceId(), invoice);
			} 
			
			return invoiceMap;

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
// Load items to add to invoice
		public static List<Item> itemList(HashMap<Integer, Invoice> invoice) {
			List<Item> itemList = new ArrayList<Item>();

			Connection conn = null;
			Item a = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			String itemQuery = "Select i.type, t.date, t.invoiceId, t.invoiceCode, i.itemId, i.name, i.model, i.itemCode,"
					+ "	   i.hourlyRate, i.unit, i.unitPrice, v.quantity,"
					+ "	   v.purchasePrice, v.leaseRate, v.startDate, v.endDate, v.hoursBilled"
					+ "	   From Item i"
					+ "    Join InvoiceItem v"
					+ "	   Join Invoice t"
					+ "    On i.itemId = v.itemId and v.invoiceId = t.invoiceId;";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conn.prepareStatement(itemQuery);
				rs = ps.executeQuery();
				while (rs.next()) {
					String itemType = rs.getString("i.type");
					String itemCode = rs.getString("i.itemCode");
					String itemName = rs.getString("i.name");
					Integer invoiceId = rs.getInt("t.invoiceId");
					Invoice newInvoice = invoice.get(invoiceId);
					if (itemType.equals("E")) {
						String model = rs.getString("i.model");

						if (rs.getString("v.leaseRate") == null) {
							double purchasePrice = rs.getDouble("v.purchasePrice");
							a = new Purchase(itemCode, itemName, model, purchasePrice);
							

						} else if (rs.getString("v.purchasePrice") == null) {
							double leaseRate = rs.getDouble("v.leaseRate");
							String startDate = rs.getString("v.startDate");
							String endDate = rs.getString("v.endDate");
							a = new Lease(itemCode, itemName, model, leaseRate, startDate, endDate);
						}

					} else if (itemType.equals("P")) {
						String unit = rs.getString("i.unit");
						int unitPrice = rs.getInt("i.unitPrice");
						int quantity = rs.getInt("v.quantity");
						a = new Product(itemCode, itemName, unit, unitPrice, quantity);

					} else if (itemType.equals("S")) {
						double hourlyRate = rs.getDouble("i.hourlyRate");
						double hoursBilled = rs.getDouble("v.hoursBilled");
						a = new Service(itemCode, itemName, hourlyRate, hoursBilled);
					}
					
					//adds items to invoice
					itemList.add(a);
					newInvoice.addItem(a);
					
				}
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			return itemList;

		}

}