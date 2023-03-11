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
				+---------------------------------------------------------------------------+
				| Summary Report - By Total                                                 |
				+---------------------------------------------------------------------------+
				Invoice #  Store        Customer            Num Items        Tax     Total
				\n"""));

		int numOfItem = 0;
		double totalTax = 0;
		double grandTotal = 0;

		for (String list : invoices.keySet()) {

			numOfItem = numOfItem + invoices.get(list).getItems().size();
			totalTax = totalTax + invoices.get(list).getTaxes();
			grandTotal = grandTotal + invoices.get(list).getGrandTotal();

			totalReport.append(String.format("%s     %s \t%s  \t\t%d \t%.2f               \t%.2f\n",
					invoices.get(list).getInvoiceCode(), invoices.get(list).getStore().getStoreCode(),
					invoices.get(list).getCustomer().getName(), invoices.get(list).getItems().size(),
					invoices.get(list).getTaxes(), invoices.get(list).getGrandTotal()));

		}
		totalReport.append(String.format("""
				+---------------------------------------------------------------------------+
				\n"""));

		totalReport.append(String.format("%d %.2f %.2f", numOfItem, totalTax, grandTotal));

		System.out.println(totalReport);

	}

	
	
	public static void salesSummaryReport(HashMap<String, Store> stores) {

		StringBuilder totalReport = new StringBuilder();

		totalReport.append(("""
				+---------------------------------------------------------------------------+
				| Store Sales Summary Report                                                |
				+---------------------------------------------------------------------------+
				Store      Manager                        #Sales          Grand Total
				\n"""));

		int numOfSales = 0;
		double grandTotal = 0;

		for (String list : stores.keySet()) {

			// numOfSales = numOfSales + stores.get(list).getItems().size();
			numOfSales = numOfSales + stores.get(list).invoices.size();
			grandTotal = grandTotal + stores.get(list).getGrandTotal();

			totalReport.append(String.format("%s     %s   \t%d    \t%.2f         \n",
					stores.get(list).getStoreCode(), stores.get(list).getManager().getName(),
					stores.get(list).invoices.size(), stores.get(list).getGrandTotal()));

		}

		totalReport.append(String.format("""
				+---------------------------------------------------------------------------+
				\n"""));

		totalReport.append(String.format("%d  %.2f", numOfSales, grandTotal));
		totalReport.append(String.format("\n"));

		System.out.println(totalReport);

	}

	public static void invoiceItemsSummary(HashMap<String, Invoice> invoices) {
		for (Map.Entry<String, Invoice> invoice : invoices.entrySet()) {
			Invoice inv = invoice.getValue();
			System.out.println(inv.invToString() + "\n");
			// print out item info
			for (int i = 0; i < invoices.size(); i++) {

			}
		}

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
//		invoiceItemsSummary(updatedInvoices);

	}

}