package com.fmt;
/**
 * Author: Carlos Bueno, Sowparnika Ssandhya
 * Date: 2023-03-10
 * 
 * This class give summary reports on sales and sale totals. 
 */

import java.util.HashMap;
import java.util.List;

public class InvoiceReport {

	public static void summaryReport(HashMap<String, Invoice> invoices) {

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

		for (String list : invoices.keySet()) {

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

	public static void salesSummaryReport(HashMap<String, Store> stores) {

		StringBuilder totalReport = new StringBuilder();

		totalReport.append(("""
				+---------------------------------------------------------------------------+
				| Store Sales Summary Report                                                |
				+---------------------------------------------------------------------------+
				Store      Manager                    #Sales          Grand Total
				\n"""));

		int numOfSales = 0;
		double grandTotal = 0;

		for (String list : stores.keySet()) {

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

	public static void invoiceItemsSummary(HashMap<String, Invoice> invoices) {

		double subTotal = 0;
		double totalTax = 0;
		double grandTotal = 0;
		StringBuilder totalReport = new StringBuilder();

		for (String code : invoices.keySet()) {

			
			
	//MOVE THIS INTO INVOICE CLASS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			totalReport.append(String.format(
					"\n\nInvoice   %s\n" + "Store     %s\n" + "Date      %s\n" + "Customer:   \n" + "%s" + "\n\n"
							+ "\nSales Person:" + "\n%s",
					invoices.get(code).getInvoiceCode(), invoices.get(code).getStore().getStoreCode(),
					invoices.get(code).getDate(), invoices.get(code).getCustomer().PersonInfotoString(),
					invoices.get(code).getCustomer().PersonInfotoString()));

			totalReport.append(String.format("""
					\nItem                                                             Total
					+---------------------------------------------------------------------------+
					"""));

			subTotal = invoices.get(code).getTotal();
			totalTax = invoices.get(code).getTaxes();
			grandTotal = invoices.get(code).getGrandTotal();

			for (Item item : invoices.get(code).getItems()) {

				totalReport.append(String.format("%s", item.ItemInfotoString()));
			}

			totalReport.append(String.format("""
					\n+---------------------------------------------------------------------------+
					\n"""));
			totalReport.append(String.format("                                                    SubTotal: $ %.2f\n "
											+"                                                        Tax: $   %.2f\n "
											+"                                                 GrandTotal: $ %.2f\n ", subTotal, totalTax, grandTotal));

		}
	///////////////////////////MOVE^^^^^^^///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		System.out.println(totalReport);

	}

	public static void main(String[] args) {

		HashMap<String, Person> persons = LoadData.mapPersonFile();
		HashMap<String, Store> stores = LoadData.parseStoreFile(persons);

		HashMap<String, Invoice> invoices = LoadData.parseInvoiceDataFile(stores, persons);
		List<Item> updatedInvoices = LoadData.parseInvoiceItemFile(invoices);
		
		

		summaryReport(Invoice.sortByTotal(invoices));
		salesSummaryReport(Store.sortByNames(stores));
		invoiceItemsSummary(invoices);

	}

}