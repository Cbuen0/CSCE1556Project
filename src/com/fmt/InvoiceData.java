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
    //TODO: implement
		

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
	public static void addPerson(String personCode, String firstName, String lastName, String street,
			String city, String state, String zip, String country) {
        //TODO: implement
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
		//TODO: ?
		int countryId = 0;
		int stateId = 0;
		int addressId = 0;
		
		try {
			/////////////////////////////////////////////////////////
			query = "Insert into State (state) values (?);";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state);
			ps.executeUpdate();

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
    //TODO: ????
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
        //TODO: implement

	}

	/**
	 * Adds a product record to the database with the given <code>code</code>, <code>name</code> and
	 * <code>unit</code> and <code>pricePerUnit</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param unit
	 * @param pricePerUnit
	 */
	public static void addProduct(String code, String name, String unit, double pricePerUnit) {
    //TODO: implement

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
    //TODO: implement

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
    //TODO: implement

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
	public static void addInvoice(String invoiceCode, String storeCode, String customerCode, String salesPersonCode, String invoiceDate) {
    //TODO: implement

	}

	/**
	 * Adds a particular product (identified by <code>itemCode</code>)
	 * to a particular invoice (identified by <code>invoiceCode</code>) with the
	 * specified quantity.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToInvoice(String invoiceCode, String itemCode, int quantity) {
    //TODO: implement

	}

	/**
	 * Adds a particular equipment <i>purchase</i> (identified by <code>itemCode</code>) to a
	 * particular invoice (identified by <code>invoiceCode</code>) at the given <code>purchasePrice</code>.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param purchasePrice
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String itemCode, double purchasePrice) {
    //TODO: implement

	}

	/**
	 * Adds a particular equipment <i>lease</i> (identified by <code>itemCode</code>) to a
	 * particular invoice (identified by <code>invoiceCode</code>) with the given 30-day
	 * <code>periodFee</code> and <code>beginDate/endDate</code>.
	 *
	 * @param invoiceCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String itemCode, double periodFee, String beginDate, String endDate) {
    //TODO: implement

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
    //TODO: implement

	}


}
