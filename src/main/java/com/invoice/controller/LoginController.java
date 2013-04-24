package com.invoice.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.invoice.service.TenantService;
import com.invoice.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private TenantService tenantservice;
	@Autowired
	private UserService userservice;

	
	@RequestMapping(value = "/AfterLogin", method = RequestMethod.GET)
	public String login(ModelMap model, HttpSession session,
			HttpServletRequest request) {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
		     if(grantedAuthority.getAuthority().equals("ROLE_COMPANY"))
		     {
		    	 session.setAttribute("login", tenantservice.getTenantInfo(user.getUsername()));
		    	 return "CompUserView";
		     }
		     else if(grantedAuthority.getAuthority().equals("ROLE_USER"))
		     {
		    	 session.setAttribute("login", userservice.getUserInfo(user.getUsername()));
		    	 return "Dashboard";
		     }
		     else if(grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
		     {
		    	 session.setAttribute("login", userservice.getUserInfo(user.getUsername()));
		    	 return "Dashboard";
		     }
		}
		
		
		return "";
		
	
	}
	
	
	
	
	
	@RequestMapping(value = "/loinfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		
		return "index";

	}

	
	
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpSession session,
			HttpServletRequest request) {
		
		session = request.getSession(false);
		
		session.invalidate();
		
		return "index";
	}
}
