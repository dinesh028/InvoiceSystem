package com.invoice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.dao.InvoiceDetailDAO;
import com.invoice.dao.ItemDAO;
import com.invoice.model.Item;
import com.invoice.model.Tenant;
import com.invoice.service.ItemService;


@Service("itemserviceimpl")
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDAO itemdao;
	
	
	
	public void insertItemDetails(Item item){
		
		itemdao.insertItemDetails(item);
	}

	
	public int getNextItemID()
	{
		return itemdao.getNextItemID();
	}


	public int deleteItem(int id, Tenant tenant_id)
	{
		return itemdao.deleteItem(id, tenant_id);
	}


	public Item searchItem(int itemId, Tenant tenant_id)
	{
		return itemdao.searchItem(itemId, tenant_id);
	}

	public int updateItem(Item item)
	{
		return itemdao.updateItem(item);
	}

	public List<Item> searchAllItem(Tenant tenant_id)
	{
		return itemdao.searchAllItem(tenant_id);		
	}
	
	public List<String> searchNameItem(Tenant tenant_id)
	{
		return itemdao.searchNameItem(tenant_id);		
	}
	
	public int searchQuantity(String itemName, Tenant tenant_id)
	{
		return itemdao.searchQuantity(itemName, tenant_id);
	}
	
	public int searchPrice(String itemName, Tenant tenant_id)
	{
		return itemdao.searchPrice(itemName, tenant_id);
	}

}

