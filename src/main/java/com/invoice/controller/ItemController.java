package com.invoice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.model.Item;
import com.invoice.model.User;
import com.invoice.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemservice;

	@RequestMapping("/user/item")
	public String registrationMain(ModelMap map) {

		Item item = new Item();
		item.setItemId(itemservice.getNextItemID());
		map.addAttribute("item", item);
		return "AddItem";
	}

	@RequestMapping("/user/item/add")
	public String registrationItem(@ModelAttribute("item") Item item,
			HttpSession session) {
		User user = (User) session.getAttribute("login");
		item.setTenant(user.getTenant());
		item.setEnable((short)1);
		itemservice.insertItemDetails(item);
		return "Success1";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/delete/Item/view")
	public @ResponseBody
	String deletionItem(@RequestParam(value = "itemId") int itemId, HttpSession session) {
		
		User user = (User) session.getAttribute("login");
		int result = itemservice.deleteItem(itemId, user.getTenant());
		if (result > 0)
			return "Record Deleted Successfully";
		else
			return "No records found";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/item/search/view")
	@ResponseBody
	public Item searchItem(@RequestParam(value = "itemId") int itemId,
			HttpSession session) {
		
		User user = (User) session.getAttribute("login");
		Item itemsearch = itemservice.searchItem(itemId, user.getTenant());
		return itemsearch;
		
	}

	@RequestMapping("/user/delete/Item")
	public String deletionView(@ModelAttribute("item") Item item) {
		return "DeleteItem";

	}

	@RequestMapping("/user/item/search")
	public String searchView(@ModelAttribute("item") Item item) {
		return "SearchItem";

	}

	@RequestMapping("/user/Item/update")
	public String searchView() {
		return "UpdateItem";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/update/item/save")
	@ResponseBody
	public String deleteItem(@RequestParam(value = "itemId") int itemId,
			@RequestParam(value = "name") String name,  @RequestParam(value = "price") int price, HttpSession session) {
		
		
		User user = (User)session.getAttribute("login");
		Item item = new Item();
		item.setItemId(itemId);
		item.setPrice(price);item.setName(name.trim());
		item.setTenant(user.getTenant());
		
		int result = itemservice.updateItem(item);

		if (result > 0)
			return "Record Updated Successfully";
		else
			return "No records found";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/item/searchall")
	@ResponseBody public List<Item> searchAllItem(HttpSession session) {

		User user = (User) session.getAttribute("login");
		List<Item> itemName = itemservice.searchAllItem(user.getTenant());
		
		
		session.setAttribute("itemsearch", itemName);
		
		List<Item> item=(List<Item>) session.getAttribute("itemsearch");
		java.util.Iterator<Item> itt =  item.iterator();
		while(itt.hasNext())
		{
				Item c = (Item) itt.next();
		}
		
		
		
		return itemName;

	}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/registration/search/price")
	@ResponseBody public int searchPrice(@RequestParam(value = "itemname") String itemName, HttpSession session) {

		User mem = (User) session.getAttribute("login");
		int price = itemservice.searchPrice(itemName, mem.getTenant());
		System.out.println("price is "+price);
		return price;

	}

	
	
	

}
