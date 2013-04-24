package com.invoice.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.invoice.model.Client;
import com.invoice.model.Item;
import com.invoice.model.User;
import com.invoice.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientservice;

	@RequestMapping("/user/client")
	public String registrationMain(ModelMap map) {
		Client client = new Client();
		client.setClientId(clientservice.getNextClientID());
		map.addAttribute("client", client);
		return "AddClient";
	}

	@RequestMapping("/user/client/add")
	public String registrationClient(@ModelAttribute("client") Client client,
			HttpSession session) {
		
		User user = (User) session.getAttribute("login");
		client.setTenant(user.getTenant());
		client.setEnable((short)1);
		clientservice.insertClientDetails(client);
		return "Success1";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/delete/Client/view")
	public @ResponseBody
	String deletionClient(@RequestParam(value = "clientId") int clientId, HttpSession session) {
		User user = (User) session.getAttribute("login");
		int result = clientservice.deleteClient(clientId, user.getTenant());
		if (result > 0)
			return "Record Deleted Successfully";
		else
			return "No records found";
	}

	@RequestMapping("/user/delete/client")
	public String deletionView() {
		return "DeleteClient";

	}
	
	
	@RequestMapping("/registration/client/xml")
	public String uploadXML() {
		return "UploadXML";

	}
	
	
	@RequestMapping("/registration/client/xml/save")
	public String saveXML(@RequestParam CommonsMultipartFile[] fileUpload, HttpSession session ) throws IllegalStateException, IOException {
		
		
		File oldFile = null;
		String saveDirectory = "D:/Impetus/Impetus Prj/InvoiceSystem/src/main/webapp/images/";
		
		
		User usr  = (User) session.getAttribute("login");
		
		
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload){
				
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
					oldFile = new File(saveDirectory + aFile.getOriginalFilename()); 

					//Now invoke the renameTo() method on the reference, oldFile in this case
					oldFile.renameTo(new File(saveDirectory+usr.getTenant().getEmail()+".xml"));
					
				}
			}
		}
		
		oldFile = new File(saveDirectory+usr.getTenant().getEmail()+".xml");
		System.out.println("After Renaming"+oldFile);
		
		clientservice.saveXML(oldFile, usr.getTenant());
		
		return "UploadXML";
		
		//return "UploadXML";

	}
	
	

	@RequestMapping("/user/client/search")
	public String searchView(@ModelAttribute("client") Client client) {
		return "SearchClient";

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/client/search/view")
	 public @ResponseBody Client searchClient(@RequestParam(value = "clientId") int clientId,
			HttpSession session) {
		User user = (User) session.getAttribute("login");
		Client clientsearch  = clientservice.searchClient(clientId,  user.getTenant());
		//System.out.println(clientsearch.getCompanyName()+">>>>>"+clientsearch.getInvoiceEmailId());
		return clientsearch;
		
	}

	
	@RequestMapping("/user/client/update")
	public String searchView() {
		return "UpdateClient";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/update/client/save")
	@ResponseBody
	public String updateClient(@RequestParam(value = "emailId") String emailId,
			@RequestParam(value = "invoiceEmailId") String invoiceEmailId,
			@RequestParam(value = "clientId") int clientId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "companyName") String companyName, HttpSession session) {

		User user = (User) session.getAttribute("login");
		Client client = new Client();
		client.setClientId(clientId);
		client.setTenant(user.getTenant());
		client.setEmailId(emailId.trim());
		client.setcompanyName(companyName.trim());
		client.setEnable((short)1);
		client.setInvoiceEmailId(invoiceEmailId.trim());
		client.setName(name.trim());
		int result = clientservice.updateClient(client);
		if (result > 0)
			return "Record Updated Successfully";
		else
			return "No records found";

	}

	

	@RequestMapping(method = RequestMethod.GET, value = "/user/client/searchall")
	@ResponseBody  public  	List<Client> searchAllClient(HttpSession session) {

		User user = (User) session.getAttribute("login");
		return clientservice.searchAllClient(user.getTenant());
		
		
		
		

	}

	
	
	
	
	
	
	
}
