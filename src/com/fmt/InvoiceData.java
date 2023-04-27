package com.fmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

	/**
	 * Removes all records from all tables in the database.
	 */
	public static void clearDatabase() {
		// TODO: implement

	}

	/**
	 * Method to add a person record to the database with the provided data.
	 *
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {
		// TODO: implement
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// TODO: ?
		int countryId = 0;
		int stateId = 0;
		int addressId = 0;

		try {
			/////////////////////////////////////////////////////////
			query = "Insert into State (state) values (?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet stateKey = ps.getGeneratedKeys();
			stateKey.next();
			stateId = stateKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into Country (country) values (?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, country);
			ps.executeUpdate();

			ResultSet countryKey = ps.getGeneratedKeys();
			countryKey.next();
			countryId = countryKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into Address (street, city, stateId, zip, countryId) values (?,?,?,?,?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setInt(3, stateId);
			ps.setString(4, zip);
			ps.setInt(5, countryId);

			ps.executeUpdate();

			ResultSet addressKey = ps.getGeneratedKeys();
			addressKey.next();
			addressId = addressKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into Person (personCode, firstName, lastName, addressId) values(?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, addressId);

			ps.executeUpdate();

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return;
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 *
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		// TODO: ????
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = "Select personId From Person Where personCode = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int personId = 0;

		try {

			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				personId = rs.getInt("personId");
			}

			query = "Insert into Email (personId, email) values (?,?);";
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			ps.setString(2, email);
			ps.executeUpdate();

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;

	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 *
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip, String country) {
		// TODO: implement
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int managerId = 0;
		int addressId = 0;
		int stateId = 0;
		int countryId = 0;

		try {
			
			query = "Insert into State (state) values (?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet stateKey = ps.getGeneratedKeys();
			stateKey.next();
			stateId = stateKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into Country (country) values (?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, country);
			ps.executeUpdate();

			ResultSet countryKey = ps.getGeneratedKeys();
			countryKey.next();
			countryId = countryKey.getInt(1);
			/////////////////////////////////////////////////////////
			
			query = "Insert into Address (street, city, stateId, zip, countryId) values (?,?,?,?,?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setInt(3, stateId);
			ps.setString(4, zip);
			ps.setInt(5, countryId);

			ps.executeUpdate();

			ResultSet addressKey = ps.getGeneratedKeys();
			addressKey.next();
			addressId = addressKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Select personId From Person Where personCode = ?;";
			ps = conn.prepareStatement(query);
			ps.setString(1, managerCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				managerId = rs.getInt("personId");
			}
			/////////////////////////////////////////////////////////

			query = "Insert into Store (storeCode, managerId, addressId) values (?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			ps.setInt(2, managerId);
			ps.setInt(3, addressId);
			ps.executeUpdate();

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;

	}

	/**
	 * Adds a product record to the database with the given <code>code</code>,
	 * <code>name</code> and <code>unit</code> and <code>pricePerUnit</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param unit
	 * @param pricePerUnit
	 */
	public static void addProduct(String code, String name, String unit, double pricePerUnit) {
		// TODO: implement

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String type = "P";

		try {
			
			query = "Insert into Item (itemCode, type, name, unit, unitPrice) values(?,?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, type);
			ps.setString(3, name);
			ps.setString(4, unit);
			ps.setDouble(5, pricePerUnit);
			rs = ps.executeQuery();
			
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return;
	}



	/**
	 * Adds an equipment record to the database with the given <code>code</code>,
	 * <code>name</code> and <code>modelNumber</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param modelNumber
	 */
	public static void addEquipment(String code, String name, String modelNumber) {
		// TODO: implement
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String type = "E";

		try {
			
			query = "Insert into Item (itemCode, type, name, model) values(?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, type);
			ps.setString(3, name);
			ps.setString(4, modelNumber);
			rs = ps.executeQuery();
			
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return;
	}


	/**
	 * Adds a service record to the database with the given <code>code</code>,
	 * <code>name</code> and <code>costPerHour</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param modelNumber
	 */
	public static void addService(String code, String name, double costPerHour) {
		// TODO: implement
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String type = "S";

		try {
			
			query = "Insert into Item (itemCode, type, name, hourlyRate) values(?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, type);
			ps.setString(3, name);
			ps.setDouble(4, costPerHour);
			rs = ps.executeQuery();
			
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return;
	}

	/**
	 * Adds an invoice record to the database with the given data.
	 *
	 * @param invoiceCode
	 * @param storeCode
	 * @param customerCode
	 * @param salesPersonCode
	 * @param invoiceDate
	 */
	public static void addInvoice(String invoiceCode, String storeCode, String customerCode, String salesPersonCode,
			String invoiceDate) {
		// TODO: implement
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int storeId = 0;
		int customerId = 0;
		int salesPersonId = 0;
		
		try {
			
			query = "Select storeId From Store where storeCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, storeCode);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet storeKey = ps.getGeneratedKeys();
			storeKey.next();
			storeId = storeKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Select personId from Person where personCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customerCode);
			ps.executeUpdate();

			ResultSet customerKey = ps.getGeneratedKeys();
			customerKey.next();
			customerId = customerKey.getInt(1);
			/////////////////////////////////////////////////////////
			
			query = "Select personId from Person where personCode = ?";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, salesPersonCode);
			ps.executeUpdate();

			ResultSet managerKey = ps.getGeneratedKeys();
			managerKey.next();
			salesPersonId = managerKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into Invoice (invoiceCode, storeId, customerId, salesPersonId, date) values(?,?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			ps.setInt(2, storeId);
			ps.setInt(3, customerId);
			ps.setInt(4, salesPersonId);
			ps.setString(5, invoiceDate);
			rs = ps.executeQuery();
			
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;

	}

	/**
	 * Adds a particular product (identified by <code>itemCode</code>) to a
	 * particular invoice (identified by <code>invoiceCode</code>) with the
	 * specified quantity.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToInvoice(String invoiceCode, String itemCode, int quantity) {
		// TODO: implement
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceId = 0;
		int itemId = 0;
		
		
		try {
			
			query = "Select invoiceId From Invoice Where invoiceCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, invoiceCode);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet invoiceKey = ps.getGeneratedKeys();
			invoiceKey.next();
			invoiceId = invoiceKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Select itemId From Item where itemCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, itemCode);
			ps.executeUpdate();

			ResultSet itemKey = ps.getGeneratedKeys();
			itemKey.next();
			itemId = itemKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into InvoiceItem (invoiceId, itemId, quantity) values(?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setInt(3, quantity);
			rs = ps.executeQuery();
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;

	}

	/**
	 * Adds a particular equipment <i>purchase</i> (identified by
	 * <code>itemCode</code>) to a particular invoice (identified by
	 * <code>invoiceCode</code>) at the given <code>purchasePrice</code>.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param purchasePrice
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String itemCode, double purchasePrice) {
		// TODO: implement
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceId = 0;
		int itemId = 0;
		
		
		
		try {
			
			query = "Select invoiceId From Invoice Where invoiceCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, invoiceCode);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet invoiceKey = ps.getGeneratedKeys();
			invoiceKey.next();
			invoiceId = invoiceKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Select itemId From Item where itemCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, itemCode);
			ps.executeUpdate();

			ResultSet itemKey = ps.getGeneratedKeys();
			itemKey.next();
			itemId = itemKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into InvoiceItem (invoiceId, itemId, purchasePrice) values(?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setDouble(3, purchasePrice);
			rs = ps.executeQuery();
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;


	}

	/**
	 * Adds a particular equipment <i>lease</i> (identified by
	 * <code>itemCode</code>) to a particular invoice (identified by
	 * <code>invoiceCode</code>) with the given 30-day <code>periodFee</code> and
	 * <code>beginDate/endDate</code>.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String itemCode, double periodFee, String beginDate,
			String endDate) {
		// TODO: implement
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceId = 0;
		int itemId = 0;
		
		
		
		try {
			
			query = "Select invoiceId From Invoice Where invoiceCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, invoiceCode);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet invoiceKey = ps.getGeneratedKeys();
			invoiceKey.next();
			invoiceId = invoiceKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Select itemId From Item where itemCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, itemCode);
			ps.executeUpdate();

			ResultSet itemKey = ps.getGeneratedKeys();
			itemKey.next();
			itemId = itemKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into InvoiceItem (invoiceId, itemId, leaseRate, startDate, endDate) values(?,?,?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setDouble(3, periodFee);
			ps.setString(4,beginDate);
			ps.setString(4,endDate);
			//TODO: reading in as strings ok?
			rs = ps.executeQuery();
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;


	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular invoice (identified by <code>invoiceCode</code>) with the
	 * specified number of hours.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param billedHours
	 */
	public static void addServiceToInvoice(String invoiceCode, String itemCode, double billedHours) {
		// TODO: implement
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String query = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceId = 0;
		int itemId = 0;
		
		
		
		try {
			
			query = "Select invoiceId From Invoice Where invoiceCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, invoiceCode);
			ps.executeUpdate();
			//TODO: Where does this stuff go

			ResultSet invoiceKey = ps.getGeneratedKeys();
			invoiceKey.next();
			invoiceId = invoiceKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Select itemId From Item where itemCode = ?;";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, itemCode);
			ps.executeUpdate();

			ResultSet itemKey = ps.getGeneratedKeys();
			itemKey.next();
			itemId = itemKey.getInt(1);
			/////////////////////////////////////////////////////////

			query = "Insert into InvoiceItem (invoiceId, itemId, hoursBilled) values(?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setInt(1, invoiceId);
			ps.setInt(2, itemId);
			ps.setDouble(3, billedHours);
			rs = ps.executeQuery();
			ps.executeUpdate();


			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return;


	}

}
