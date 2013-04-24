package com.invoice.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.invoice.model.Tenant;

public interface TenantService {

	void insertTenantDetails(Tenant tenant) ;

	Tenant getTenantInfo(String tenantId);

	void updateTenantDetails(Tenant tenant);

	void uploadLogo(CommonsMultipartFile[] fileUpload, HttpServletRequest request, Tenant tenant);
	
	
	/*Member getUserDetailByEmail(String email);

	long getTenantID();
	
	void updateUserDetails(Member user);*/
	
}

