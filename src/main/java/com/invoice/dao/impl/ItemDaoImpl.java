package com.invoice.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.InvoiceDetailDAO;
import com.invoice.dao.ItemDAO;
import com.invoice.model.Item;
import com.invoice.model.Tenant;

@Repository("itemdao")
@Transactional
public class ItemDaoImpl implements ItemDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private InvoiceDetailDAO invoicedetaildao;

	public void insertItemDetails(Item item) {

		try {
			sessionFactory.getCurrentSession().save(item);
		} catch (Exception ex) {
			System.out.println("EXCEPTION IS " + ex);
		}

	}

	public int getNextItemID() {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Item.class)
				.setProjection(Projections.max("itemId"));
		Integer maxOID = (Integer) criteria.uniqueResult();

		if (maxOID == null)
			maxOID = 0;
		int nextOID = maxOID.intValue();
		return ++nextOID;
	}

	public int deleteItem(int itemId, Tenant tenant) {

		
		
		Item item = searchItem(itemId, tenant);
		if(item == null)
			return -1;
		else{
			
			invoicedetaildao.deleteInvoiceByItem(item.getName(), tenant);
			
			Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update Item set enable=:enable  where tenant= :tenant and  itemId=:itemId");
			query.setParameter("enable", (short)0);
			query.setParameter("itemId", itemId);
			query.setParameter("tenant", tenant);
			return query.executeUpdate();
		}

	}

	public Item searchItem(int itemId, Tenant tenant) {

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Item where itemId= :itemId and tenant= :tenant and enable=1");
		query.setParameter("itemId", itemId);
		query.setParameter("tenant", tenant);
		return (Item) query.uniqueResult();
	}

	public int updateItem(Item item) {

		sessionFactory.getCurrentSession().saveOrUpdate(item);
		return 1; 

	}

	public List<Item> searchAllItem(Tenant tenant) {

		Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("tenant", tenant));
        criteria.add(Restrictions.eq("enable", (short)1));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
	}

	@Override
	public List<String> searchNameItem(Tenant tenant) {
		// TODO Auto-generated method stub
		
		List<String> nameItem = new ArrayList<String>();
		Item itemvalue = null;
		String sql = "";

		sql = "select name from Item where tenant=:tenant";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);

		query.setParameter("tenant", tenant);
		for (Iterator it = query.iterate(); it.hasNext();) {
			String name = (String) it.next();
			nameItem.add(name);
		}
		return nameItem;
	}
	
	
	

	@Override
	public int searchQuantity(String id, Tenant tenant) {
		// TODO Auto-generated method stub

		String sql = "";
		int quantity=0;

		sql = "select quantity from Item where tenant=:tenant and name=:name";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("tenant", tenant);
		query.setParameter("name", id);
		for (Iterator it = query.iterate(); it.hasNext();) {
			quantity = (Integer) it.next();
		}
		System.out.println("her e  "+quantity);
		return quantity;
	}
	
	
	
	@Override
	public int searchPrice(String itemName, Tenant tenant) {
		// TODO Auto-generated method stub

		String sql = "";
		int price=0;

		sql = "select price from Item where tenant=:tenant and name=:name";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);

		query.setParameter("tenant", tenant);
		query.setParameter("name", itemName);
		for (Iterator it = query.iterate(); it.hasNext();) {
			price = (Integer) it.next();
		}
		return price;
	}

	

	
	
}
