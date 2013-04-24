package com.invoice.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.invoice.model.Client;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;
import com.invoice.model.User;



public interface InvoiceDAO {
	
	void insertInvoiceDetails(Invoice invoice) ;
	
	int getNextInvoiceID();
	
	int updateStatus(int invoiceId, String status);

	List<Invoice> searchAllInvoice();

	List<Invoice> searchInvoiceByUser(User member);
	
	List<Invoice> searchRInvoiceByUser(User member);

	int deleteInvoice(int invoiceId);

	Invoice getInvoiceDetails(int invoiceId);
	
	List<Invoice> searchInvoiceForDue();
	
	List<Invoice> searchForPaid(User mem, Timestamp timestamp);
	
	public int updateStatusPaid(int id);
	
	
	List<InvoiceDetail> searchInvoiceByCriteria(Integer invoiceId, String searchItem,
			String searchClient, Tenant tenant);

	int updateRStatus(int invoice);
	
	List<Invoice> getInvoiceDetailsByClient(String searchClient);

	List<Invoice> searchForExcelWeeek(Date startDate, Date date, Tenant tenant);
	
	
	List<Invoice> searchDueInvoice(Tenant tenant);
	
	
	List<Invoice> searchLatestInvoice(Tenant tenant, Timestamp timestamp);

	List<Invoice> searchByCurrentDate(Timestamp timestamp, Timestamp timestamp2);

	void saveInovice(Invoice invoice);

	Long searchForGraph(Timestamp timestamp, Timestamp timestamp2, Tenant tenant);

	Long searchForGraph1(Timestamp endDate, Timestamp startDate,
			Tenant tenant);

	List<Invoice> searchInvoiceToBeDisable(User user, Timestamp timestamp);

	int deleteInvoiceByClient(Client client, Tenant tenant);

	int deleteInvoiceByUser(User user, Tenant tenant);

	
	
}
