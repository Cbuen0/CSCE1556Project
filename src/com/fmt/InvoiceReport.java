package com.fmt;
/**
 * Author: Carlos Bueno, Sowparnika Ssandhya

 * Date: 2023-03-10
 * 
 * This class give summary reports on sales and sale totals. 
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceReport {

	public static <T> void summaryReport(HashMap<T, Invoice> invoices) {
		StringBuilder totalReport = new StringBuilder();

		totalReport.append(String.format("""
				+----------------------------------------------------------------------------------------------+
				| Summary Report - By Total                                                                    |
				+----------------------------------------------------------------------------------------------+
				Invoice #       Store        Customer                 Num Items          Tax           Total
				\n"""));

		int numOfItem = 0;
		double totalTax = 0;
		double grandTotal = 0;

		for (T list : invoices.keySet()) {
			numOfItem = numOfItem + invoices.get(list).getItems().size();
			totalTax = totalTax + invoices.get(list).getTaxes();
			grandTotal = grandTotal + invoices.get(list).getGrandTotal();

			totalReport.append(String.format("%s       %9s       %-20s %9d           $%8.2f    $%11.2f\n",
					invoices.get(list).getInvoiceCode(), invoices.get(list).getStore().getStoreCode(),
					invoices.get(list).getCustomer().getName(), invoices.get(list).getItems().size(),
					invoices.get(list).getTaxes(), invoices.get(list).getGrandTotal()));
		}
		totalReport.append(String.format("""
				+----------------------------------------------------------------------------------------------+
				\n"""));

		totalReport.append(String.format("%60d          $ %.2f    $  %.2f", numOfItem, totalTax, grandTotal));
		totalReport.append(String.format("\n"));

		System.out.println(totalReport);
	}
	//prints summary of the sales report.
	public static <T> void salesSummaryReport(HashMap<T, Store> stores) {
		StringBuilder totalReport = new StringBuilder();

		totalReport.append(("""
				+---------------------------------------------------------------------------+
				| Store Sales Summary Report                                                |
				+---------------------------------------------------------------------------+
				Store      Manager                    #Sales          Grand Total
				\n"""));

		int numOfSales = 0;
		double grandTotal = 0;
		for (T list : stores.keySet()) {
			numOfSales = numOfSales + stores.get(list).invoices.size();
			grandTotal = grandTotal + stores.get(list).getGrandTotal();

			totalReport.append(String.format("%s     %s   \t\t%d    \t\t$ %.2f         \n",
					stores.get(list).getStoreCode(), stores.get(list).getManager().getName(),
					stores.get(list).invoices.size(), stores.get(list).getGrandTotal()));
		}

		totalReport.append(String.format("""
				+---------------------------------------------------------------------------+
				\n"""));

		totalReport.append(String.format("%41d          \t$ %.2f", numOfSales, grandTotal));
		totalReport.append(String.format("\n"));

		System.out.println(totalReport);

	}
	//prints Summary of Invoice Items.
	public static <T> void invoiceItemsSummary(HashMap<T, Invoice> invoices) {
		for (Invoice i: invoices.values()) {
			System.out.println(i);
		}
	}

	
	public static void main(String[] args) {

//		HashMap<String, Person> persons = LoadData.mapPersonFile();
//		HashMap<String, Store> stores = LoadData.parseStoreFile(persons);
//		HashMap<String, Invoice> invoices = LoadData.parseInvoiceDataFile(stores, persons);
//		List<Item> updatedInvoices = LoadData.parseInvoiceItemFile(invoices);
		
//		Adds items to invoice
		
		
//		for (String key : invoices.keySet()) {
//			Invoice i = invoices.get(key);
//			List<Item> updatedInvoicesDB = DataBaseLoader.itemList(i);
//		}
//		
//		//associates store and invoice
//		for(String key : stores.keySet()) {
//			Store s = stores.get(key);
//			HashMap<String, Invoice> invoicesDB = DataBaseLoader.invoiceList(s);
//			
//		}
		
		//associates stores with person
		HashMap<Integer, Store> storesDB = DataBaseLoader.loadStore();
		HashMap<Integer,Invoice> invoiceDB = DataBaseLoader.getInvoices(storesDB);
		@SuppressWarnings("unused")
		List<Item> itemDB = DataBaseLoader.itemList(invoiceDB);
		
		
		//person
		//HashMap<String, Person> personsDB = DataBaseLoader.personMap();
		
		
		summaryReport(Invoice.sortByTotal(invoiceDB));
		salesSummaryReport(Store.sortByNames(storesDB));
		invoiceItemsSummary(invoiceDB);

	}

}