package com.invoice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.model.InvoiceDetail;
import com.invoice.model.User;
import com.invoice.service.InvoiceDetailService;

@Controller
public class InvoiceDetailController {

	@Autowired
	private InvoiceDetailService invoicehistoryservice;

	
	
	@RequestMapping("/registration/invoicehistory/save")
	@ResponseBody public String registrationInvoice(@RequestParam(value = "total_amount") int iem,
			 HttpSession session) {
		
		InvoiceDetail in = new InvoiceDetail();
		System.out.println("here");
		User mem = (User)session.getAttribute("member");
		return "hi";
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/registration/invoicehistory/search/criteria")
	@ResponseBody
	public List<InvoiceDetail> searchInvoiceCriteria(
			@RequestParam(value = "searchItem") String searchItem,
			HttpSession session) {

		
		User mem = (User)session.getAttribute("login");
		
		List<InvoiceDetail> invoiceList = invoicehistoryservice.searchInvoiceByCriteria(searchItem, mem.getTenant());
		
		
		
		return invoiceList;
	}
	
	
	
	
	
	
	
}
