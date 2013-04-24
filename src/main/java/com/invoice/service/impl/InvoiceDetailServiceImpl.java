package com.invoice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.dao.InvoiceDetailDAO;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;
import com.invoice.service.InvoiceDetailService;


@Service("invoicehistoryservice")
public class InvoiceDetailServiceImpl implements InvoiceDetailService{
	
	@Autowired
	private InvoiceDetailDAO invoicedetaildao;
	
	public int insertInvoiceHistoryDetails(InvoiceDetail invoice){
		
		return invoicedetaildao.insertInvoiceHistoryDetails(invoice);
	}

	public int getInvoiceID()
	{
		/*return invoicedao.getInvoiceID();*/
		return 1;
	}
	
	
	
	public List<InvoiceDetail> searchAllInvoice(Invoice invoice)
	{
			return invoicedetaildao.searchAllInvoice(invoice);
		
	}
	
	
	public int deleteNonReferRecord(Invoice invoice_id)
	{
		return invoicedetaildao.deleteNonReferRecord(invoice_id);
		
	}

	@Override
	public List<InvoiceDetail> searchAllInvoiceByItem(String searchItem) {
		// TODO Auto-generated method stub
		return invoicedetaildao.searchAllInvoiceByItem(searchItem);
	}
	
	
	
	/*@Override
	public List<InvoiceDetail> searchInvoiceByCriteria(
			String searchItem, Tenant tenant) {
		
		return invoicedetaildao.searchInvoiceByCriteria(searchItem, tenant);
		
		
	}*/
	
}
