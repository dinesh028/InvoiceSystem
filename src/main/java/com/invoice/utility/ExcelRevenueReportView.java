package com.invoice.utility;

import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.User;

public class ExcelRevenueReportView extends AbstractExcelView{
	
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		response.setHeader("Content-Disposition", "inline;filename=\"Invoice Summary.xls"
				+ "\"");
		List<Invoice> invoiceList = (List<Invoice>) model.get("invoiceList");
				
		User usr = (User) request.getAttribute("usr");
		SimpleDateFormat sdf = new SimpleDateFormat(usr.getTenant().getDateFormat());
		
		
		String dtFormat = usr.getTenant().getDateFormat();
		dtFormat = dtFormat.contains("yy")?dtFormat.replace("yy", "yyyy"):dtFormat.replace("y", "yy");
		
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Invoice Summary");
		HSSFSheet sheet1 = workbook.createSheet("Invoice Items ");
		
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Invoice Id");
		header.createCell(1).setCellValue("Client Id");
		header.createCell(2).setCellValue("Client Name");
		header.createCell(3).setCellValue("Client Email-Id");
		header.createCell(4).setCellValue("Status");
		header.createCell(5).setCellValue("Recurring");
		header.createCell(6).setCellValue("Final Sum");
		
		
		HSSFRow header1 = sheet1.createRow(0);
		header1.createCell(0).setCellValue("Invoice Id");
		header1.createCell(1).setCellValue("Item Name");
		header1.createCell(2).setCellValue("Quantity");
		header1.createCell(3).setCellValue("Price");
		header1.createCell(4).setCellValue("Amount");
		
		
	    int rowNum = 1;int rowHist = 1;
		for (Invoice invoice : invoiceList) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(invoice.getInvoiceId());
			row.createCell(1).setCellValue(invoice.getClient().getClientId());
			row.createCell(2).setCellValue(invoice.getCname());
			row.createCell(3).setCellValue(invoice.getClient().getEmailId());
			row.createCell(4).setCellValue(invoice.getStatus());
			if(invoice.getRecurring()==1)
				row.createCell(5).setCellValue("No");
			else
				row.createCell(5).setCellValue("Yes");
			row.createCell(6).setCellValue(getCurrencySymbol(invoice.getTenant().getCurrencyFormat())+" "+ invoice.getAmount());
			for (InvoiceDetail invoiceHistory : invoice.getInvoiceHistory()) {
				HSSFRow row1 = sheet1.createRow(rowHist++);
				row1.createCell(0).setCellValue(invoice.getInvoiceId());
				row1.createCell(1).setCellValue(invoiceHistory.getItem());
				row1.createCell(2).setCellValue(invoiceHistory.getQuantity());
				row1.createCell(3).setCellValue(invoiceHistory.getPrice());
				row1.createCell(4).setCellValue(getCurrencySymbol(invoice.getTenant().getCurrencyFormat())+" "+ invoiceHistory.getAmount());
					
				}
				
			
			for(int i=0;i<=5;i++)
			{
				sheet.autoSizeColumn(i);
				sheet1.autoSizeColumn(i);
			}
			
			
	    }
		
	}
	
	public  String getCurrencySymbol(String currencyFormat)
	{
		

		String curFormat[] = currencyFormat.split(",");
		Locale locale=new Locale(curFormat[0], curFormat[1]);
        Currency cur = Currency.getInstance(locale);
        return cur.getSymbol();
	}
	
	
}