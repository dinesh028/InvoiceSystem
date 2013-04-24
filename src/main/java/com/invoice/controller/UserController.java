package com.invoice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.model.Tenant;
import com.invoice.model.User;
import com.invoice.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;

	@RequestMapping("/user/profile/save")
	public String registrationUser1(@ModelAttribute("user") User user,
			HttpSession session) {
		
		user.setName(user.getName().trim());
		user.setEnable((short)1);
		User usr = (User)session.getAttribute("login");
		user.setRole(usr.getRole());
		user.setTenant(usr.getTenant());
		user.setUserId(usr.getUserId());
		userservice.updateUserDetails(user);
		user=userservice.getUserInfo(user.getUserId());
		session.setAttribute("login",user);
		/*if(mem.getRole().equals("ROLE_COMPANY"))
			return "Success";
		else*/
			return "Success1";
		
	}
	
	
	/*
	 * Save the user details created by admin
	 * 
	 */
	
	@RequestMapping("/user/save")
	public String registrationUserSave(@ModelAttribute("user") User user,
			HttpSession session) {
		
		user.setName(user.getName().trim());

		user.setRole("ROLE_USER");

		user.setEnable((short)1);
		
		User mem=(User)session.getAttribute("login");
		
		user.setTenant(mem.getTenant());
		
		userservice.insertUserDetails(user);
		
		return "Success1";
		
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/search/view")
	@ResponseBody public User searchUser(@RequestParam(value = "userId") String userId,
			HttpSession session) {
		
	
		
		User usr = (User) session.getAttribute("login");
		
		User user =  userservice.searchUser(userId, usr.getTenant() );

		return user;
		

	}
	
	
	
	
	
	@RequestMapping("/user/create")
	public String registrationUserSave() {
		
		return "AddUser";
		
	}
	
	@RequestMapping("/user/search")
	public String registrationUserSearch() {
		
		return "SearchUser";
		
	}
	
	
	@RequestMapping("/user/update")
	public String registrationUserUpdate() {
		
		return "UpdateUser";
		
	}
	
	
	@RequestMapping("/user/delete")
	public String registrationUserDelete() {
		
		return "DeleteUser";
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/delete/save")
	public @ResponseBody
	String deletionClient(@RequestParam(value = "userId") String userId, HttpSession session) {
		
		User user = (User) session.getAttribute("login");
		
		if(user.getUserId().equals(userId))
		{
			return "Sorry, you can't delete your self";
		}	
		else{
			
		int result = userservice.deleteUser(userId, user.getTenant());

		if (result > 0)
			return "Record Deleted Successfully";
		else
			return "No records found";

		}
		// return "Its done";
	}
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/update/save")
	@ResponseBody
	public String updateClient(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "emailId") String emailId,
			 HttpSession session) {

		User usr = (User) session.getAttribute("login");
		User user = new User();
		user.setTenant(usr.getTenant());
		user.setEnable((short)1);
		user.setRole("ROLE_USER");
		user.setEmailId(emailId.trim());
		user.setUserId(userId);
		user.setName(name.trim());
		int result = userservice.updateUserDetails(user);
		if (result > 0)
			return "Record Updated Successfully";
		else
			return "No records found";

	}
	
	
	
	
	

	@RequestMapping("/user/profile")
	public String registrationProfileUser( ) {
		return "UserDetails";
	}
	
	
	@RequestMapping("/company/user")
	public String registrationUser(@ModelAttribute("user") User user,
			HttpSession session) {
		
		if (user.getEmailId() == null) {
			
			return "CompUserView";

		} else {

		user.setUserId(user.getUserId().trim());
		user.setEmailId(user.getEmailId().trim());
		user.setName(user.getName().trim());
		user.setPassword(user.getPassword().trim());
		user.setRole("ROLE_USER");
		user.setEnable((short)1);
		user.setTenant((Tenant) session.getAttribute("login"));
		userservice.insertUserDetails(user);
		return "Success";
	}
	}
	
	@RequestMapping("/company/admin")
	public String registrationAdmin(@ModelAttribute("user") User user,
			HttpSession session) {
		// System.out.println("Getting Data "+user.getEmail());
		if (user.getEmailId() == null) {
			user.setRole("ROLE_ADMIN");
			return "CompUserView";

		} else {

			user.setUserId(user.getUserId().trim());
			user.setEmailId(user.getEmailId().trim());

			user.setName(user.getName().trim());

			user.setPassword(user.getPassword().trim());

			user.setRole("ROLE_ADMIN");

			user.setEnable((short)1);

			user.setTenant((Tenant) session.getAttribute("login"));

			userservice.insertUserDetails(user);

			return "Success";
		}
	}

	
	
	
}
