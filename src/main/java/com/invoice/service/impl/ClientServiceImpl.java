package com.invoice.service.impl;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.invoice.dao.ClientDAO;
import com.invoice.model.Client;
import com.invoice.model.Tenant;
import com.invoice.service.ClientService;


@Service("clientserviceimpl")
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientDAO clientdao;
	
	public void insertClientDetails(Client Client){
		
		clientdao.insertClientDetails(Client);
	}

	
	public int getNextClientID()
	{
		return clientdao.getNextClientID();
	}

	public int deleteClient(int clientId, Tenant tenant)
	{
		
		
		return clientdao.deleteClient(clientId, tenant);
	}
	
	public Client searchClient(int clientId, Tenant tenant) {
		
		return clientdao.searchClient(clientId, tenant);
	}

	public int updateClient(Client client)
	{
		return clientdao.updateClient(client);
	}

	/*public List<String> searchAllClient(Tenant tenant_id)
	{
		return clientdao.searchAllClient(tenant_id);		
	}
	*/
	
	public List<Client> searchAllClient(Tenant tenant_id)
	{
		return clientdao.searchAllClient(tenant_id);		
	}
public String getEmail(String name, long tenant_id) {
		
		return clientdao.getEmail(name, tenant_id);
	}
	
 	public List<Client> searchAllClientByCriteria(String name, Tenant tenant)
 	{
 		
 		return clientdao.searchAllClientByCriteria(name, tenant);
 	}
 	
 	
 	public void saveXML(File fXmlFile, Tenant tenant) 
 	{
 		System.out.println(fXmlFile);
 		
 		
 		try{
 	
 		Client client = new Client();
 		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
	 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	 
		NodeList nList = doc.getElementsByTagName("client");
	 
		System.out.println("----------------------------");
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
	 
				client.setEmailid(eElement.getElementsByTagName("name").item(0).getTextContent());
				client.setName(eElement.getElementsByTagName("email").item(0).getTextContent());
				client.setCompanyname(eElement.getElementsByTagName("company").item(0).getTextContent());
				client.setTenant(tenant);
				
				System.out.println("First Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("email").item(0).getTextContent());
				System.out.println("Nick Name : " + eElement.getElementsByTagName("company").item(0).getTextContent());
				
	 
				clientdao.insertClientDetails(client);
			}
		}
		
		System.out.println("Deleted "+fXmlFile.delete());
 		
 		
 		}catch(Exception ex)
 		{
 			
 			System.out.println("exception "+ex);
 		}
 		
 		
 		
 		
 		
 		
 	
 	}
 	
 	
}
