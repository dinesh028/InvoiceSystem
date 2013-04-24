package com.invoice.service;

import java.util.List;

import com.invoice.model.Item;
import com.invoice.model.Tenant;

public interface ItemService {

	void insertItemDetails(Item item);

	int getNextItemID();

	int deleteItem(int id, Tenant tenant_id);
	
	Item searchItem(int id, Tenant tenant_id);
	
	int updateItem(Item item);
	
	List<Item> searchAllItem(Tenant tenant_id);
	
	List<String> searchNameItem(Tenant tenant);
	
	int searchQuantity(String itemName, Tenant tenant);
	
	int searchPrice(String itemName, Tenant tenant_id);
}
