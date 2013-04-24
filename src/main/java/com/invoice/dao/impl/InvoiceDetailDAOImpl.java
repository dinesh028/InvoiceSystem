package com.invoice.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.InvoiceDAO;
import com.invoice.dao.InvoiceDetailDAO;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;

@Repository("invoicehistorydao")
@Transactional
public class InvoiceDetailDAOImpl implements InvoiceDetailDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private InvoiceDAO invoicedao;

	public int insertInvoiceHistoryDetails(InvoiceDetail invoice) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(invoice);
		return invoice.getDetailId();

	}
	
public void insertInvoiceHistoryDetails1(InvoiceDetail invoice) {
		
		
		sessionFactory.getCurrentSession().save(invoice);
		//return invoice.getHistId();

	}
	
	
	public int deleteNonReferRecord(Invoice invoice)
	{
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				"delete from InvoiceDetail where invoice is null");
		return query.executeUpdate();
	}
	
	
	
	
	
	public int getInvoiceID() {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Invoice.class)
				.setProjection(Projections.max("histid"));
		Integer maxOID = (Integer) criteria.uniqueResult();

		if (maxOID == null)
			maxOID = 129544;
		int re = maxOID.intValue();
		System.out.println("Value is " + re);
		return ++re;
		
	}
	
	
	@Override
	public List<InvoiceDetail> searchAllInvoice(Invoice invoice) {
		
		
		
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(InvoiceDetail.class);
		cr.add(Restrictions.eq("invoice", invoice));
		return cr.list();
	}

	
	@Override
	public List<InvoiceDetail> searchAllInvoiceByItem(String itemName) {
		
		
		
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(InvoiceDetail.class);
		cr.add(Restrictions.eq("item", itemName));
		return cr.list();
		
	}
	
	
	
	
	
	@Override
	public int deleteInvoiceByItem(String itemName,
			Tenant tenant) {
		// TODO Auto-generated method stub
		List<Integer> invoiceIdList = new ArrayList<Integer>();
		System.out.println(itemName);
		Query query = sessionFactory
			.getCurrentSession()
			.createQuery(
					"select distinct invoice from InvoiceDetail where item=:item");
		query.setParameter("item", itemName);
		for (Iterator it = query.iterate(); it.hasNext();)
		{
			Invoice invoice = (Invoice)it.next();
			System.out.println(invoice.getInvoiceId());
			invoicedao.deleteInvoice(invoice.getInvoiceId());
		}
		return 2;
		
	}

	@Override
	public void saveInovice(Invoice invoice) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(invoice);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
}
