package com.invoice.service;

import java.util.List;

import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;

public interface InvoiceDetailService {

	int insertInvoiceHistoryDetails(InvoiceDetail invoice) ;

	int getInvoiceID();

	
	List<InvoiceDetail> searchAllInvoice(Invoice id);

	int deleteNonReferRecord(Invoice invoice_id);

	List<InvoiceDetail> searchAllInvoiceByItem(String searchItem);

//	List<InvoiceDetail> searchInvoiceByCriteria(String searchItem, Tenant tenant);

	

	
	
}

