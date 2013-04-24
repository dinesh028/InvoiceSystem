package com.invoice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.invoice.model.Invoice;
import com.invoice.model.User;
import com.invoice.service.InvoiceService;

@Controller
public class ReportController extends AbstractController {

	@Autowired
	private InvoiceService invoiceservice;

	List<Invoice> invoiceList;
	
	@RequestMapping("/user/invoice/export")
	public String exportExcel(ModelMap map) {
		map.addAttribute("excel","1");
		return "SetOption";
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/user/invoice/export/view")
	public ModelAndView exportExcelDisplay(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate, ModelMap map,  HttpSession session, HttpServletRequest request,  HttpServletResponse response) {
		
		System.out.println("in controller");
		
		User usr = (User) session.getAttribute("login");

		invoiceList = invoiceservice.searchForExcel(startDate, endDate, usr.getTenant());
		
		request.setAttribute("usr", usr);
		
		try {
			
		return	handleRequestInternal(request, response);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
		
		
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		return new ModelAndView("ExcelRevenueSummary", "invoiceList", invoiceList);
		
	}

	
	
	
}
