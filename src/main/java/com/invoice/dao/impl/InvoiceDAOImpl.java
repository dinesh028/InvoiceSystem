package com.invoice.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.InvoiceDAO;
import com.invoice.model.Client;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;
import com.invoice.model.User;

@Repository("invoicedao")
@Transactional
public class InvoiceDAOImpl implements InvoiceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertInvoiceDetails(Invoice invoice) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoice);

	}

	@Override
	public int getNextInvoiceID() {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Invoice.class)
				.setProjection(Projections.max("id"));
		Integer maxOID = (Integer) criteria.uniqueResult();

		if (maxOID == null)
			maxOID = 129544;
		int re = maxOID.intValue();

		return ++re;
	}

	@Override
	public int updateStatus(int id, String status) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"update Invoice set status=:status where id=:id");
		query.setParameter("status", status);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	@Override
	public int updateRStatus(int id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"update Invoice set status=:status where id=:id");
		query.setParameter("status", "PAID");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	@Override
	public List<Invoice> searchAllInvoice() {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Invoice.class);
		criteria.add(Restrictions.eq("recurring", (short) 1));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("status", "PAID"));
		or.add(Restrictions.eq("status", "SENT"));
		criteria.add(or);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public List<Invoice> searchInvoiceByUser(User user) {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("status", "DRAFT"));
		crit.add(Restrictions.eq("recurring", (short) 0));
		crit.add(Restrictions.eq("enable", (short) 1));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();

	}

	@SuppressWarnings("unchecked")
	public List<Invoice> searchRInvoiceByUser(User user) {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("status", "DRAFT"));
		crit.add(Restrictions.eq("recurring", (short) 1));
		crit.add(Restrictions.eq("enable", (short) 1));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return crit.list();

	}

	public List<Invoice> searchInvoiceForDue() {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		crit.add(Restrictions.eq("status", "SENT"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.lt("dueDate",
				new java.sql.Date(new Date().getTime())));
		return crit.list();

	}

	public Invoice getInvoiceDetails(int invoiceId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Invoice where invoiceId= :invoiceId");
		query.setParameter("invoiceId", invoiceId);
		/* query.setParameter("enable", (short)1); */
		return (Invoice) query.uniqueResult();

	}

	public int deleteInvoice(int invoiceId) {

		
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update Invoice set enable=:enable  where invoiceId= :invoiceId");
		query.setParameter("invoiceId", invoiceId);
		query.setParameter("enable", (short) 0);
		return query.executeUpdate();
	}

	public List<Invoice> searchForPaid(User user, Timestamp sendDate) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);

		System.out.println("printing send date" + sendDate);
		crit.add(Restrictions.eq("user", user));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("status", "DUE"));
		or.add(Restrictions.eq("status", "SENT"));
		crit.add(or);
		crit.add(Restrictions.le("sendDate", sendDate));
		crit.add(Restrictions.eq("enable", (short) 1));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return crit.list();

	}

	public int updateStatusPaid(int id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"update Invoice set status=:status where id=:id");
		query.setParameter("status", "PAID");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	public List<InvoiceDetail> searchInvoiceByCriteria(Integer invoiceId,
			String searchItem, String searchClient, Tenant tenant) {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);

		List<Invoice> invoiceList = new ArrayList<Invoice>();

		if (invoiceId != null) {
			crit.add(Restrictions.eq("invoiceId", invoiceId));
		}
		if (searchClient.length() >= 1) {

			crit.add(Restrictions.eq("cname", searchClient));

		}

		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("status", "PAID"));
		or.add(Restrictions.eq("status", "SENT"));
		or.add(Restrictions.eq("status", "DUE"));
		crit.add(or);
		crit.add(Restrictions.eq("tenant", tenant));
		crit.add(Restrictions.eq("enable", (short)1));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		invoiceList = crit.list();

		List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>();

	
		if (searchItem.length() >= 1) {

			for (Invoice it : invoiceList) {
				Criteria crit1 = sessionFactory.getCurrentSession()
						.createCriteria(InvoiceDetail.class);
				crit1.add(Restrictions.eq("invoice", it));
				crit1.add(Restrictions.eq("item", searchItem));

				crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				List<InvoiceDetail> newInvoiceDetailList = crit1.list();
				invoiceDetailList.addAll(newInvoiceDetailList);

			}

		} else {
			

			for (Invoice it : invoiceList) {
				Criteria crit1 = sessionFactory.getCurrentSession()
						.createCriteria(InvoiceDetail.class);
				crit1.add(Restrictions.eq("invoice", it));
				Iterator iss = it.getInvoiceHistory().iterator();
				InvoiceDetail demo = (InvoiceDetail) iss.next();

				crit1.add(Restrictions.eq("detailId", demo.getDetailId()));
				crit1.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				List<InvoiceDetail> ihh = crit1.list();

				if (ihh != null) {
					for (InvoiceDetail ihhh : ihh) {
						System.out.println("1");
						invoiceDetailList.add(ihhh);
					}
				}

			}

		}
		
		return invoiceDetailList;
	}

	@Override
	public List<Invoice> getInvoiceDetailsByClient(String searchClient) {
		
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		cr.add(Restrictions.eq("cname", searchClient));
		return cr.list();

	}

	public List<Invoice> searchForExcelWeeek(Date startDate, Date endDate,
			Tenant tenant) {

		System.out.println("start date " + startDate + " end date " + endDate);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		criteria.add(Restrictions.eq("tenant", tenant));
		criteria.add(Restrictions.eq("enable", (short)1));
		criteria.add(Restrictions.between("createdDate", startDate, endDate));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
		

		
	}

	@Override
	public List<Invoice> searchDueInvoice(Tenant tenant) {
	
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Invoice  where status=:status and enable=:enable and  tenant = :tenant order by dueDate desc");
		query.setParameter("status", "DUE");
		query.setParameter("tenant", tenant);
		query.setParameter("enable", (short)1);
		query.setMaxResults(3);
		return query.list();

	}

	@Override
	public List<Invoice> searchLatestInvoice(Tenant tenant, Timestamp sendDate) {
		// TODO Auto-generated method stub

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Invoice  where status=:status and tenant = :tenant and enable=:enable and sendDate<=:sendDate order by sendDate desc");
		query.setParameter("status", "SENT");
		query.setParameter("tenant", tenant);
		query.setParameter("sendDate", sendDate);
		query.setParameter("enable", (short)1);
		query.setMaxResults(3);
		return  query.list();

	}

	@Override
	public List<Invoice> searchByCurrentDate(Timestamp currentStartDate,
			Timestamp currentEndDate) {
		// TODO Auto-generated method stub

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		criteria.add(Restrictions.eq("status", "SENT"));
		criteria.add(Restrictions.ge("sendDate", currentStartDate));
		criteria.add(Restrictions.le("sendDate", currentEndDate));
		criteria.add(Restrictions.eq("recurring", (short) 1));
		criteria.add(Restrictions.eq("enable", (short) 1));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Invoice> invoiceList = criteria.list();
		Iterator<Invoice> it = invoiceList.iterator();
		while (it.hasNext()) {

			Invoice c1 = (Invoice) it.next();

			System.out.println("Getting Email        >" + c1.getInvoiceId()
					+ "  > >  > ");

		}

		return invoiceList;
	}

	@Override
	public void saveInovice(Invoice invoice) {
		// TODO Auto-generated method stub

		sessionFactory.getCurrentSession().save(invoice);

	}

	@Override
	public Long searchForGraph(Timestamp startDate, Timestamp endDate,
			Tenant tenant) {
		

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"SELECT SUM(amount)  FROM Invoice  WHERE status = :status and enable=:enable and tenant= :tenant  AND paidDate between :startDate and :endDate ");

		query.setParameter("tenant", tenant);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("status", "PAID");
		query.setParameter("enable", (short)1);

		Long i = (Long) query.uniqueResult();

		if (i == null)
			i = (long) 0;
		System.out.println("SUm is " + i);
		return i;


	}

	public Long searchForGraph1(Timestamp startDate, Timestamp endDate,
			Tenant tenant) {
		
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"SELECT SUM(amount)  FROM Invoice  WHERE status = :status and enable=:enable AND tenant= :tenant  AND dueDate between :startDate and :endDate ");

		query.setParameter("tenant", tenant);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("status", "DUE");
		query.setParameter("enable", (short)1);

		Long i = (Long) query.uniqueResult();

		if (i == null)
			i = (long) 0;
		System.out.println("SUm is " + i);
		return i;

	}

	@Override
	public List<Invoice> searchInvoiceToBeDisable(User user, Timestamp sendDate) {
		// TODO Auto-generated method stub
		
		System.out.println(sendDate);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Invoice.class);
		criteria.add(Restrictions.eq("status", "SENT"));
		criteria.add(Restrictions.gt("sendDate", sendDate));
		criteria.add(Restrictions.eq("recurring", (short) 1));
		criteria.add(Restrictions.eq("enable", (short) 1));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
		
	}

	@Override
	public int deleteInvoiceByClient(Client client, Tenant tenant) {
		{
			
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"update Invoice set enable=:enable  where tenant= :tenant and  client=:client and status=:status");
				query.setParameter("enable", (short)0);
				query.setParameter("client", client);
				query.setParameter("status", "DRAFT");
				query.setParameter("tenant", tenant);
				return query.executeUpdate();
		}	
		
	}

	@Override
	public int deleteInvoiceByUser(User user, Tenant tenant) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update Invoice set enable=:enable  where tenant = :tenant and  user = :user and status = :status");
			query.setParameter("enable", (short)0);
			query.setParameter("user", user);
			query.setParameter("tenant", tenant);
			query.setParameter("status", "DRAFT");
			return query.executeUpdate();
	}

	

}
