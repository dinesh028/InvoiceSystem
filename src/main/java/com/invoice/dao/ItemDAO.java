package com.invoice.dao;

import java.util.List;

import com.invoice.model.Item;
import com.invoice.model.Tenant;

public interface ItemDAO {

	void insertItemDetails(Item item);

	int getNextItemID();

	int deleteItem(int id, Tenant tenant_id);

	Item searchItem(int id, Tenant tenant_id);

	int updateItem(Item item);
	
	List<Item> searchAllItem(Tenant tenant_id);
	
	List<String> searchNameItem(Tenant tenant_id);

	int searchQuantity(String itemName, Tenant tenant_id);
	
	int searchPrice(String itemName, Tenant tenant_id);
		

}
