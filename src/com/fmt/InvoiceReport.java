package com.fmt;

import java.util.HashMap;
import java.util.Map;

public class InvoiceReport {

	// TODO: create method to print first summary (by Total)
	public static void SummaryReport(Map<String, Invoice> invoice) {

		String header = """
				Summary Report - By Total
				+---------------------------------------------------------------------------+
				Invoice#   Store        Customer               Num Items        Tax     Total
				""";
		System.out.println(header);
		for (Map.Entry<String, Invoice> list : invoice.entrySet()) {
			String invoiceCode = list.getKey();
			Invoice i = list.getValue();
			i.ContentsTesttoString();
			System.out.println(i.ContentsTesttoString());
		}

	}


	
	// TODO: create method to print 2nd summary
	public static void StoreSalesSummaryReport(HashMap<String, Invoice> invoice) {

		String header = """
				
				Store Sales Summary Report
				+---------------------------------------------------------------------------+
				Store           Manager               #Sales            Grand Total
				""";
		System.out.println(header);
		for (Map.Entry<String, Invoice> list : invoice.entrySet()) {
			String invoiceCode = list.getKey();
			Invoice i = list.getValue();
			i.StoreSalestoString();
			System.out.println(i.StoreSalestoString());
		}

	}

	
	
	// TODO: create method to print 3rd section (each invoice)
	public static void InvoiceReports(HashMap<String, Invoice> invoice) {
		
		for (Map.Entry<String, Invoice> list : invoice.entrySet()) {
			String invoiceCode = list.getKey();
			Invoice i = list.getValue();
			i.toStringHeader();
			System.out.println(i.toStringHeader());
		}

		
	}
	
	
	
	public static void main(String[] args) {
		// TODO: call methods here, should only have like 6-9 lines of code here
		// Don't print from here
		HashMap<String, Invoice> invoice = LoadData.parseInvoiceDataFile();

		SummaryReport(invoice);
		StoreSalesSummaryReport(invoice);
		InvoiceReports(invoice);
	}

}
