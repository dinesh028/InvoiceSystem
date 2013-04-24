package com.invoice.dao;

import com.invoice.model.Tenant;



public interface TenantDAO {
	
	void insertTenantDetails(Tenant tenant) ;

	Tenant getTenantInfo(String tenantId);
	
	void updateTenantDetails(Tenant tenant);
	
}
