package com.fmt;

import java.util.HashMap;
import java.util.Map;

public class InvoiceReport {

	// TODO: create method to print first summary (by Total)
	public static void SummaryReport(HashMap<String, Invoice> invoice) {
		
		String header = """
				Summary Report - By Total
				+---------------------------------------------------------------------------+
				Invoice#   Store        Customer               Num Items        Tax     Total
				""";
		System.out.println(header);
		for (Map.Entry<String, Invoice> list : invoice.entrySet()) {
			String invoiceCode = list.getKey();
			Invoice i = list.getValue();
			i.SummaryReporttoString();
			System.out.println(i.SummaryReporttoString());
		}

}

	// TODO: create method to print 2nd summary

	// TODO: create method to print 3rd section (each invoice)

	public static void main(String[] args) {
		// TODO: call methods here, should only have like 6-9 lines of code here
		// Don't print from here
		
		HashMap<String, Invoice> invoice = LoadData.parseInvoiceDataFile();

		SummaryReport(invoice);
		
		

	}

}
