package com.fmt;

import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

public class InvoiceReport {

	// TODO: create method to print first summary (by Total)
	public static void summaryReport(HashMap<String, Invoice> invoices) {

		StringBuilder totalReport = new StringBuilder();

		totalReport.append(String.format("""
				+-------------------------------------------------------------------------------------------+
				| Summary Report - By Total                                                                 |
				+-------------------------------------------------------------------------------------------+
				Invoice #  Store        Customer                       Num Items      Tax          Total
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
				+---------------------------------------------------------------------------+
				\n"""));

		totalReport.append(String.format("%d %.2f %.2f", numOfItem, totalTax, grandTotal));
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

			totalReport.append(String.format("%s     %s   \t\t%d    \t\t%.2f         \n", 
					stores.get(list).getStoreCode(),
					stores.get(list).getManager().getName(), 
					stores.get(list).invoices.size(),
					stores.get(list).getGrandTotal()));

		}

		totalReport.append(String.format("""
				+---------------------------------------------------------------------------+
				\n"""));

		totalReport.append(String.format("%d  %.2f", numOfSales, grandTotal));
		totalReport.append(String.format("\n"));

		System.out.println(totalReport);

	}

	public static void invoiceItemsSummary(HashMap<String, Invoice> invoices) {

		double subTotal = 0;
		double totalTax = 0;
		double grandTotal = 0;
		StringBuilder totalReport = new StringBuilder();
		
		
		
//		totalReport.append(String.format("""
//				Item                                                             Total
//				+---------------------------------------------------------------------------+
//				"""));

		for (String list : invoices.keySet()) {

			totalReport.append(String.format("\n\nInvoice   %s\n"
											+"Store     %s\n"
											+"Date      %s\n"
											+"Customer:   \n"
											+"%s"
											+"\n\n"
											+"\nSales Person:"
											+"\n%s",
											invoices.get(list).getInvoiceCode(),
											invoices.get(list).getStore().getStoreCode(),
											invoices.get(list).getDate(),
											invoices.get(list).getCustomer().PersonInfotoString(),
											invoices.get(list).getCustomer().PersonInfotoString()));
			
			totalReport.append(String.format("""
					Item                                                             Total
					+---------------------------------------------------------------------------+
					"""));
			
			subTotal = subTotal + invoices.get(list).getTotal();
			totalTax = totalTax + invoices.get(list).getTaxes();
			grandTotal = grandTotal + invoices.get(list).getGrandTotal();
			
			for(Item item : invoices.get(list).getItems()) {
				
			totalReport.append(String.format("%s", 
					item.ItemInfotoString()));
			}
			
			totalReport.append(String.format(""" 
					\n+---------------------------------------------------------------------------+
					\n"""));
			totalReport.append(String.format("%.2f\n  %.2f\n  %.2f", subTotal, totalTax, grandTotal));
			
			
		}
		System.out.println(totalReport);

//		totalReport.append(String.format(""" 
//				\n+---------------------------------------------------------------------------+
//				\n"""));
//		totalReport.append(String.format("%.2f\n  %.2f\n  %.2f", subTotal, totalTax, grandTotal));
//		System.out.println(totalReport);
//		
		
		
		

	}

	// TODO: create method to print 3rd section (each invoice)

	public static void main(String[] args) {
		// TODO: call methods here, should only have like 6-9 lines of code here
		// Don't print from here
		HashMap<String, Person> persons = LoadData.mapPersonFile();
		HashMap<String, Store> stores = LoadData.parseStoreFile(persons);

		HashMap<String, Invoice> invoices = LoadData.parseInvoiceDataFile(stores, persons);
		List<Item> updatedInvoices = LoadData.parseInvoiceItemFile(invoices);

		summaryReport(invoices);
		salesSummaryReport(stores);
		invoiceItemsSummary(invoices);

	}

}