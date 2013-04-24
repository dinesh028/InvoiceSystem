package com.invoice.service;

import com.invoice.model.Tenant;
import com.invoice.model.User;

public interface UserService {

	void insertUserDetails(User user) ;

	User getUserInfo(String userId);

	long getTenantID();
	
	int updateUserDetails(User user);

	User searchUser(String userId, Tenant tenant);

	int deleteUser(String email, Tenant tenant);
	
}

