package com.invoice.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.TenantDAO;
import com.invoice.model.Tenant;

@Repository("tenantdao")
@Transactional
public class TenantDAOImpl implements TenantDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertTenantDetails(Tenant tenant) {
		
		/*PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		tenant.setPassword(passwordEncoder.encodePassword(tenant.getPassword(),
				null));*/
		sessionFactory.getCurrentSession().save(tenant);

	}
	
	
	public Tenant getTenantInfo(String tenantId)
	{
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Tenant where tenantId=:tenantId");
		query.setParameter("tenantId", tenantId);
		Tenant tenant = (Tenant) query.uniqueResult();
		
		return tenant;
	}

	public void updateTenantDetails(Tenant tenant)
	{
		
		sessionFactory.getCurrentSession().update(tenant);
		
	}
	
	
}
