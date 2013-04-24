package com.invoice.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.InvoiceDAO;
import com.invoice.dao.UserDAO;
import com.invoice.model.Tenant;
import com.invoice.model.User;

@Repository("userdao")
@Transactional
public class UserDaoImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private InvoiceDAO invoicedao;

	public void insertUserDetails(User user) {
		
		/*PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
				null));*/
		sessionFactory.getCurrentSession().save(user);
		
		

	}
	
	public int updateUserDetails(User user)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
		return 1;
	}
	

	public User getUserInfo(String userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from User where userId=:userId");
		query.setParameter("userId", userId);
		return (User) query.uniqueResult();
		
	}

	public long getTenantID() {

		/*
		 * String sql =
		 * "SELECT MAX(TENANT_ID) FROM USER_INFO WHERE ROLE='ROLE_COMPANY'";
		 * Query query = sessionFactory.getCurrentSession().createQuery(sql).;
		 */

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setProjection(Projections.max("tenant_id"));
		Long maxOID = (Long) criteria.uniqueResult();
		if(maxOID==null)
			maxOID=(long) 17453;
		/*
		 * query.setMaxResults(1); Long price=null; for (Iterator it =
		 * query.iterate(); it.hasNext();) { Object[] f = (Object[]) it.next();
		 * price = (Long) f[0]; }
		 */
		long re = maxOID.longValue();
		System.out.println("Value is " + re);
		return ++re;

	}

	@Override
	public User searchUser(String userId, Tenant tenant) {
		// TODO Auto-generated method stub
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		crit.add(Restrictions.eq("userId", userId));
		crit.add(Restrictions.eq("tenant", tenant));
		crit.add(Restrictions.eq("enable", (short)1));
		
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return (User) crit.uniqueResult();
		
		
	}
	
	
	public int deleteUser(String userId, Tenant tenant)
	{
		
		User user = searchUser(userId, tenant);
		if(user == null)
			return -1;
		else{
			
			invoicedao.deleteInvoiceByUser(user, tenant);
			Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"update User set enable=:enable  where tenant= :tenant and  userId=:userId");
			query.setParameter("enable", (short)0);
			query.setParameter("userId", userId);
			query.setParameter("tenant", tenant);
			return query.executeUpdate();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
