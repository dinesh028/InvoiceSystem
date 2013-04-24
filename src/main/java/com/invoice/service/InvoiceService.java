package com.invoice.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;
import com.invoice.model.User;

public interface InvoiceService {

	void insertInvoiceDetails(Invoice invoice) ;

	int getNextInvoiceID();
	
	int deleteInvoice(int invoiceid);
	
	Invoice getInvoiceDetails(int invoiceId);

	int updateStatus(int invoiceId, String status);
	
	 void calcWeek(Tenant tenant, int count, Calendar c);
	
	public List<Invoice> searchAllInvoice();

	void performTask(Set<InvoiceDetail> invoiceHistorySet, Invoice invoice, HttpSession session);

	List<Invoice> searchInvoiceByUser(User member);
	
	
	
	
	
	
	List<Invoice> searchRInvoiceByUser(User member);
	
	public void sendThank(String emailId,
			String name, String content) ;
	
	List<Invoice> searchForPaid(User mem);
	
	public int updateStatusPaid(int id);



	int updateRStatus(int invoiceId);

	List<InvoiceDetail> searchInvoiceByCriteria(Integer invoiceId, String searchItem,
			String searchClient, Tenant tenant);

	List<Invoice> getInvoiceDetailsByClient(String searchClient);

	List<Invoice> searchInvoiceForDue();

	List<Invoice> searchForExcel(String searchBy, String endDate, Tenant tenant);

	List<Invoice> searchDueInvoice(Tenant tenant);

	List<Invoice> searchLatestInvoice(Tenant tenant);

	List<Invoice> searchByCurrentDate();

	List searchForGraph(Tenant tenant, int graphFormat);

	List<Invoice> searchInvoiceToBeDisable(User user);

	//void updateStatus(int invoiceId, String string);

	
	
	
}

