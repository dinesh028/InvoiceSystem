package com.invoice.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.ClientDAO;
import com.invoice.dao.InvoiceDAO;
import com.invoice.model.Client;
import com.invoice.model.Tenant;

@Repository("clientdao")
@Transactional
public class ClientDaoImpl implements ClientDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Autowired
	private InvoiceDAO invoicedao;

	public void insertClientDetails(Client client) {
		sessionFactory.getCurrentSession().save(client);
	}

	public int getNextClientID() {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Client.class)
				.setProjection(Projections.max("clientId"));
		Integer maxOID = (Integer) criteria.uniqueResult();

		if (maxOID == null)
			maxOID = 0;
		int nextOID = maxOID.intValue();
		return ++nextOID;
		

	}

	public int deleteClient(int clientId, Tenant tenant) {
		
		Client client = searchClient(clientId, tenant);
		if(client == null)
			return -1;
		else{
			
			invoicedao.deleteInvoiceByClient(client, tenant);
			
			Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update Client set enable=:enable  where tenant= :tenant and  clientId=:clientId");
			query.setParameter("enable", (short)0);
			query.setParameter("clientId", clientId);
			query.setParameter("tenant", tenant);
			return query.executeUpdate();
		}
			
	}

	public Client searchClient(int clientId, Tenant tenant) {

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Client where clientId= :clientId and tenant=:tenant and enable=:enable ");
		query.setParameter("clientId", clientId);
		query.setParameter("tenant", tenant);
		query.setParameter("enable", (short)1);
		return (Client) query.uniqueResult();
	
	}

	public int updateClient(Client client) {
try{
		 sessionFactory.getCurrentSession().saveOrUpdate(client);
		 return 1;
}catch(Exception ex){
	System.out.println(ex);
}
return 1;
	}

	@Override
	public List<Client> searchAllClient(Tenant tenant) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("tenant", tenant));
        criteria.add(Restrictions.eq("enable", (short)1));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

		}

		
		
	
	
	
	 @Override
		public List<Client> searchAllClientByCriteria(String name, Tenant tenant) {
			// TODO Auto-generated method stub
			
			Session session = sessionFactory.openSession();
	        Criteria crit = session.createCriteria(Client.class);
	        crit.add(Expression.eq("name", name));
	        crit.add(Expression.eq("tenant", tenant));
	        //scriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        return crit.list();

			
			
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getEmail(String name, long tenant) {

		String email="";
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Client where name= :name and tenant= :tenant");
		query.setParameter("name", name);
		query.setParameter("tenant", tenant);
		for (Iterator it = query.iterate(); it.hasNext();) {
				email=(String) it.next();
		}

		return email;
		
	}

	

}
