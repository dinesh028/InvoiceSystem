package com.invoice.dao;

import com.invoice.model.Tenant;
import com.invoice.model.User;



public interface UserDAO {
	
	void insertUserDetails(User user) ;

	long getTenantID();
	
	int updateUserDetails(User user);

	User searchUser(String userId, Tenant tenant);

	int deleteUser(String email, Tenant tenant);

	User getUserInfo(String userId);
	
	
}
