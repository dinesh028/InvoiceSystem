package com.invoice.utility;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.dao.InvoiceDAO;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.service.InvoiceDetailService;
import com.invoice.service.InvoiceService;
import com.invoice.service.PDFGenerateService;

public class RunMeTask {

	@Autowired
	private InvoiceService invoiceservice;
	
	@Autowired
	private PDFGenerateService pdfGenerateService;
	
	@Autowired
	private InvoiceDAO invoicedao;
	
	@Autowired
	private MailMail mailMail;

	@Autowired
	private InvoiceDetailService invoicehistoryservice;

	public Timestamp getNextDate(Timestamp date)
	{
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.MONTH, 1);
		return new Timestamp(c1.getTime().getTime());
	}
	
	
	public void sendRecurring() {

		
		//int maxOID = invoiceservice.getInvoiceID();
		List<Invoice> invoiceList = invoiceservice.searchByCurrentDate();
		int count = 1;
		int counter = 1;
		Set<InvoiceDetail> ih = new HashSet<InvoiceDetail>();
		for(Invoice invoice : invoiceList)
		{
			
			Invoice in = new Invoice();
			in.setInvoiceId(invoiceservice.getNextInvoiceID());in.setAmount(invoice.getAmount());in.setClient(invoice.getClient());
			in.setCname(invoice.getCname());in.setCreatedDate(invoice.getCreatedDate());
			in.setPaidDate(null);in.setRecurring((short)1);in.setInvoiceParentId(invoice.getInvoiceParentId());
			in.setStatus("SENT");in.setTax(invoice.getTax());in.setTenant(invoice.getTenant());
			in.setUser(invoice.getUser());
			in.setDueDate(getNextDate(invoice.getDueDate()));
			in.setCreatedDate(getNextDate(invoice.getCreatedDate()));
			in.setSendDate(getNextDate(invoice.getSendDate()));
			/*
			Calendar c1 = Calendar.getInstance();
			c1.setTime(invoice.getDueDate());
			c1.add(Calendar.MONTH, 1);
			invoice.setDueDate(new java.sql.Date(c1.getTime().getTime()));*/
			//invoice.setInvoiceHistory(null);
			
			//in.setInvoiceHistory(invoice.getInvoiceHistory());
			invoicedao.saveInovice(in);
			Set<InvoiceDetail> invoiceHistorySet = new HashSet<InvoiceDetail>(0);
			for(InvoiceDetail invoiceHistory : invoice.getInvoiceHistory())
			{	
				
				InvoiceDetail newInvoiceHistory = new InvoiceDetail();
				
				newInvoiceHistory.setAmount(invoiceHistory.getAmount());
				newInvoiceHistory.setItem(invoiceHistory.getItem());
				newInvoiceHistory.setPrice(invoiceHistory.getPrice());
				newInvoiceHistory.setQuantity(invoiceHistory.getQuantity());
				newInvoiceHistory.setInvoice(in);
				invoiceHistorySet.add(invoiceHistory);
				invoicehistoryservice.insertInvoiceHistoryDetails(newInvoiceHistory);
				
			}	
			
	/*		pdfGenerateService.generate(invoiceHistorySet,
					in);

			mailMail.sendMail(in.getClient().getEmailid(),
					in.getClient().getName(),
					in.getInvoice_id(),"Please find attachement of invoice");*/
			System.out.println("invoice id print "+in.getInvoiceId());
			
			System.out.println("invoice id print "+in.getInvoiceId());
		}	
		
	}
	
	
	public void createRecurring(){
		
		System.out.println("here1");
		//int maxOID = invoiceservice.getInvoiceID();
		List<Invoice> invoiceList = invoiceservice.searchByCurrentDate();
		int count = 1;
		int counter = 1;
		Set<InvoiceDetail> ih = new HashSet<InvoiceDetail>();
		for(Invoice invoice : invoiceList)
		{
			
			Invoice in = new Invoice();
			
			in.setInvoiceId(invoiceservice.getNextInvoiceID());in.setAmount(invoice.getAmount());in.setClient(invoice.getClient());
			in.setCname(invoice.getCname());in.setCreatedDate(invoice.getCreatedDate());
			in.setPaidDate(null);in.setRecurring((short)1);
			in.setStatus("SENT");in.setTax(invoice.getTax());in.setTenant(invoice.getTenant());
			in.setUser(invoice.getUser());
			in.setDueDate(getNextDate(invoice.getDueDate()));
			in.setCreatedDate(getNextDate(invoice.getCreatedDate()));
			in.setSendDate(getNextDate(invoice.getSendDate()));
			/*
			Calendar c1 = Calendar.getInstance();
			c1.setTime(invoice.getDueDate());
			c1.add(Calendar.MONTH, 1);
			invoice.setDueDate(new java.sql.Date(c1.getTime().getTime()));*/
			//invoice.setInvoiceHistory(null);
			
			//in.setInvoiceHistory(invoice.getInvoiceHistory());
			invoicedao.saveInovice(in);
			Set<InvoiceDetail> invoiceHistorySet = new HashSet<InvoiceDetail>(0);
			for(InvoiceDetail invoiceHistory : invoice.getInvoiceHistory())
			{	
				
				InvoiceDetail newInvoiceHistory = new InvoiceDetail();
				
				newInvoiceHistory.setAmount(invoiceHistory.getAmount());
				newInvoiceHistory.setItem(invoiceHistory.getItem());
				newInvoiceHistory.setPrice(invoiceHistory.getPrice());
				newInvoiceHistory.setQuantity(invoiceHistory.getQuantity());
				newInvoiceHistory.setInvoice(in);
				invoiceHistorySet.add(invoiceHistory);
				invoicehistoryservice.insertInvoiceHistoryDetails(newInvoiceHistory);
				
			}	
			
		
		
	}}
	
	
	public void reminderInvoice()
	{
		try{

		List<Invoice> invoiceList = invoiceservice.searchInvoiceForDue();

		
		for(Invoice invoice : invoiceList)
		{
			if(invoice.getTenant().getReminder()==1){
				System.out.println("here are we");
				mailMail.sendReminder(invoice.getClient().getEmailId(), invoice
						.getClient().getName(),  "Please pay you amount, due date is passed");
			}
			invoiceservice.updateStatus(invoice.getInvoiceId(),"DUE");
				
		}
		
	}catch(Exception ex){System.out.println("ex"+ex);}
	}
}
