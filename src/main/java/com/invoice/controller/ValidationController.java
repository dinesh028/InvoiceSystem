package com.invoice.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.invoice.model.Tenant;
import com.invoice.service.TenantService;


@Controller
public class ValidationController {
	
	@Autowired
	private TenantService tenantservice;
	
	
	
	@RequestMapping(value="/registration/validity", method=RequestMethod.POST)
	public String processValidationForm(@ModelAttribute("tenant")  Tenant tenant, @RequestParam CommonsMultipartFile[] fileUpload, HttpServletRequest request ) throws Exception {	
	
		
		
		
		
		
		if(tenant.getReminder()==0)
			tenant.setReminder((short) 1);
		if(tenant.getReminder()==0)
			tenant.setReminder((short)0);
	
		tenant.setEmailId(tenant.getEmailId().trim());
    	tenant.setCompanyName(tenant.getCompanyName().trim());
    	tenant.setAddress(tenant.getAddress().trim());
    	tenant.setCountry(tenant.getCountry().trim());
    	tenant.setState(tenant.getState().trim());
    	tenant.setRole("ROLE_COMPANY");
    	tenant.setEnable(1);
    	tenant.setPassword(tenant.getPassword().trim());
    	tenantservice.uploadLogo(fileUpload, request, tenant);
    	
    	
    	tenantservice.insertTenantDetails(tenant);
    	
    	return "RegSuccess";
	
		
   	 }
     

}
