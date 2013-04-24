package com.invoice.dao;

import java.util.List;

import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;



public interface InvoiceDetailDAO {
	
	int insertInvoiceHistoryDetails(InvoiceDetail invoice) ;
	
	int getInvoiceID();

	List<InvoiceDetail> searchAllInvoice(Invoice invoice);

	int deleteNonReferRecord(Invoice invoice);
	
	List<InvoiceDetail> searchAllInvoiceByItem(String searchItem);

	int deleteInvoiceByItem(String searchItem,
			Tenant tenant);
	
	void saveInovice(Invoice invoice);

	
	
}
