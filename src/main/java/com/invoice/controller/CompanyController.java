package com.invoice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.invoice.model.Tenant;
import com.invoice.model.User;
import com.invoice.service.TenantService;
import com.invoice.service.UserService;

@Controller
public class CompanyController {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private TenantService tenantservice;

	@RequestMapping("/company/registration")
	public String registrationMain() {

		return "Register";

	}

	
	@RequestMapping("/user/settings")
	public String companySettings(ModelMap map, HttpSession session)
	{
		String tenantEmail = ((User)session.getAttribute("login")).getTenant().getTenantId();
		map.addAttribute("tenantInfo", tenantservice.getTenantInfo(tenantEmail));
		map.addAttribute("excel", 0);
		return "SetOption";
		
	}
	
	@RequestMapping("/user/logo")
	public String logoChange(ModelMap map, HttpSession session)
	{
		map.addAttribute("excel", 2);
		return "SetOption";
		
	}
	
	
	
	@RequestMapping("/user/settings/save")
	@ResponseBody public String companySettingsSave(@RequestParam(value = "reminder") short reminder,
			@RequestParam(value = "thankyou") short thankyou,
			@RequestParam(value = "dateFormat") String dateFormat,
			@RequestParam(value = "currencyFormat") String currencyFormat,
			HttpSession session
			)
	{
		
		User user = (User) session.getAttribute("login");
	
		Tenant tenant = user.getTenant();
		
		tenant.setThankyou(thankyou);
		tenant.setReminder(reminder);
		tenant.setDateFormat(dateFormat);
		tenant.setCurrencyFormat(currencyFormat);
		tenantservice.updateTenantDetails(tenant);
		
		
		return "SetOption";
				
		
		
	}
	
	

	@RequestMapping("/user/dashboard")
	public String dashBoard()
	{
		return "Dashboard";
	}
	
	
	@RequestMapping("/dashboard/due")	
	@ResponseBody public String dashBoardDue()
	{
		return "Dashboard";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/user/logo/save")	
	 public String uploadLogo(@RequestParam CommonsMultipartFile[] fileUpload, HttpServletRequest request, HttpSession session)
	{
		System.out.println("here");
		
		tenantservice.uploadLogo(fileUpload, request, ((User)session.getAttribute("login")).getTenant());
		
		return "Success1";
	}
	
	
	
	
	
	
	
	
	
	
}
