package com.invoice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.dao.UserDAO;
import com.invoice.model.Tenant;
import com.invoice.model.User;
import com.invoice.service.UserService;


@Service("userserviceimpl")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userdao;
	
	public void insertUserDetails(User user){
		
		userdao.insertUserDetails(user);
	}

	public User getUserInfo(String userId) {
		// TODO Auto-generated method stub
		return userdao.getUserInfo(userId);
	}
	
	public long getTenantID()
	{
		return userdao.getTenantID();
	}

	public int updateUserDetails(User user)
	{
		return userdao.updateUserDetails(user);
		
	}

	@Override
	public User searchUser(String userId, Tenant tenant) {
		// TODO Auto-generated method stub
		return userdao.searchUser(userId, tenant);
	}
	
	
	
	public int deleteUser(String userId, Tenant tenant)
	{
		
		
		return userdao.deleteUser(userId, tenant);
	}
	
}
