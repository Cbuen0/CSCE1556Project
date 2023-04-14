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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseLoader {

	private static final Logger LOGGER = LogManager.getLogger();

	public static Address getDetailedAddress(int addressId) {

		Address a = null;

		Connection conn = DatabaseInfo.getConnection();

		String query = "Select a.street, a.city, s.state, a.zip,  c.country" + "	   From Address a"
				+ "    Join State s" + "    Join Country c" + "    On a.countryId = c.countryId "
				+ "    and a.stateId = s.stateId" + "    Where addressId = ?;";

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
				LOGGER.error("SQLException : Address not found. ");
				throw new IllegalStateException("Address not found. ");
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			LOGGER.error("SQLException : ");
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return a;
	}

	public static Person getDetailedPerson(int personId) {
		Person p = null;

		Connection conn = DatabaseInfo.getConnection();

		String query = "Select p.personCode, p.lastName, p.firstName, p.addressId, p.personId" + "	   From Person p"
				+ "    Join Address a" + "    On p.addressId = a.addressId" + "    Where p.personId = ?;";

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

				Address a = DatabaseLoader.getDetailedAddress(addressId);
				List<String> emails = DatabaseLoader.getemailList(rs.getInt("p.personId"));

				p = new Person(personCode, lastName, firstName, a, emails);

			} else {
				LOGGER.error("SQLException : Person not found. ");

				throw new IllegalStateException("Person not found. ");
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("SQLException : ");

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return p;
	}

	public static HashMap<String, Person> personMap() {
		HashMap<String, Person> personMap = new HashMap<String, Person>();

		LOGGER.info("Loading all person data...");

		Person p = null;

		Connection conn = DatabaseInfo.getConnection();

		String query = "Select p.personCode, p.lastName, p.firstName, p.addressId, p.personId" + "	   From Person p"
				+ "    Join Address a" + "    On p.addressId = a.addressId;";

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
				int personId = rs.getInt("p.personId");
				Address a = DatabaseLoader.getDetailedAddress(addressId);
				List<String> emails = DatabaseLoader.getemailList(personId);

				p = new Person(personCode, lastName, firstName, a, emails);

				personMap.put(personCode, p);

			} else {
				LOGGER.error("SQLException : Person not found. ");
				throw new IllegalStateException("Trouble loading Person table");
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("SQLException : ");

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		LOGGER.info("Done loading all person data...");
		return personMap;
	}

	public static List<String> getemailList(int personId) {
		List<String> emailList = new ArrayList<String>();

		Connection conn = DatabaseInfo.getConnection();

		String emailQuery = "Select email" + "	   From Email " + "    Where personId = ?;";

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
			LOGGER.error("SQLException : ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return emailList;

	}

	public static HashMap<Integer, Store> loadStore() {
		List<Store> storeList = new ArrayList<Store>();
		LOGGER.info("Loading all stores...");
		Store s = null;

		Connection conn = DatabaseInfo.getConnection();

		String storeQuery = "Select storeId, storeCode, managerId, addressId" + "	From Store;";

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
				Address a = DatabaseLoader.getDetailedAddress(addressId);
				Person p = DatabaseLoader.getDetailedPerson(managerId);
				// storeId
				s = new Store(storeId, storeCode, p, a);

				storeList.add(s);

			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("SQLException : ");

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		HashMap<Integer, Store> storeMap = new HashMap<Integer, Store>();
		for (Store store : storeList) {
			storeMap.put(store.getStoreId(), store);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		LOGGER.info("Done loading all stores...");
		return storeMap;
	}

	public static HashMap<Integer, Invoice> getInvoices(HashMap<Integer, Store> stores) {
		List<Invoice> invList = new ArrayList<Invoice>();
		LOGGER.info("Loading all invoices...");
		Connection conn = DatabaseInfo.getConnection();

		// get store code
		String invoiceQuery = "Select i.invoiceId, i.invoiceCode, i.customerId, i.salespersonId, date, s.storeId"
				+ "    From Invoice i" + "    Join Store s" + "    On i.storeId = s.storeId;";

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
				Person c = DatabaseLoader.getDetailedPerson(customerId);
				Person m = DatabaseLoader.getDetailedPerson(salespersonId);
				String date = rs.getString("date");
				Integer storeId = rs.getInt("s.storeId");
				Store storeCode = stores.get((storeId));

				Invoice i = new Invoice(invoiceId, invoiceCode, storeCode, c, m, date);
				invList.add(i);
				storeCode.addInvoice(i);

			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("SQLException : ");

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		HashMap<Integer, Invoice> invoiceMap = new HashMap<Integer, Invoice>();
		for (Invoice invoice : invList) {
			invoiceMap.put(invoice.getInvoiceId(), invoice);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		LOGGER.info("Done loading all invoices...");
		return invoiceMap;

	}

	public static List<Item> getItemList(HashMap<Integer, Invoice> invoice) {
		List<Item> itemList = new ArrayList<Item>();
		LOGGER.info("Loading all items...");
		Connection conn = DatabaseInfo.getConnection();
		Item a = null;

		String itemQuery = "Select i.type, t.date, t.invoiceId, t.invoiceCode, i.itemId, i.name, i.model, i.itemCode,"
				+ "	   i.hourlyRate, i.unit, i.unitPrice, v.quantity,"
				+ "	   v.purchasePrice, v.leaseRate, v.startDate, v.endDate, v.hoursBilled" + "	   From Item i"
				+ "    Join InvoiceItem v" + "	   Join Invoice t"
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
				} else {
					LOGGER.error("SQLException : Address not found. ");
					throw new IllegalStateException("Address not found. ");
				}

				itemList.add(a);
				newInvoice.addItem(a);

			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			LOGGER.error("SQLException : ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		LOGGER.info("Done loading all items...");
		return itemList;

	}

}