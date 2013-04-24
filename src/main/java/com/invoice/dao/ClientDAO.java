package com.invoice.dao;

import java.util.List;

import com.invoice.model.Client;
import com.invoice.model.Tenant;




public interface ClientDAO {
	
	void insertClientDetails(Client client) ;

	int getNextClientID();

	int deleteClient(int clientId, Tenant tenant);
	
	Client searchClient(int clientId, Tenant tenant);
	
	int updateClient(Client client);
	
//	List<String> searchAllClient(Tenant tenant_id);
	
	List<Client> searchAllClient(Tenant tenant_id);
	
	String getEmail(String name, long tenant_id);

	List<Client> searchAllClientByCriteria(String name, Tenant tenant);
		
	
	
	
}
