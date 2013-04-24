package com.invoice.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.model.Client;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.User;
import com.invoice.service.ClientService;
import com.invoice.service.InvoiceDetailService;
import com.invoice.service.InvoiceService;
import com.invoice.service.ItemService;


@Controller
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceservice;

	@Autowired
	private InvoiceDetailService  invoicedetailservice;

	@Autowired
	private ItemService itemservice;

	@Autowired
	private ClientService clientservice;
	
	
	@RequestMapping("/user/invoice")
	public String registrationMain(ModelMap map) {

		Invoice invoice = new Invoice();
		invoice.setInvoiceId(invoiceservice.getNextInvoiceID());
		invoice.setRecurring((short) 0);
		map.addAttribute("invoice", invoice);

		return "InvoiceOrder";
	}

	@RequestMapping("/user/rinvoice")
	public String registrationRMain(ModelMap map, HttpSession session) {

		User user = (User) session.getAttribute("login");
		Invoice invoice = new Invoice();
		invoice.setRecurring((short) 1);
		invoice.setInvoiceId(invoiceservice.getNextInvoiceID());
		map.addAttribute("invoice", invoice);
		return "InvoiceOrder";
		
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/invoice/save")
	@ResponseBody
	public int registsrationInvoice(
			@RequestParam(value = "total_amount") int grandSum,

			@RequestParam(value = "customer") int clientId,
			@RequestParam(value = "due_date") String dueDate,
			@RequestParam(value = "created_date") String createdDate,
			@RequestParam(value = "tax") float tax,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "quantity") String quantityList,
			@RequestParam(value = "price") String priceList,
			@RequestParam(value = "fAmount") String amountList,
			@RequestParam(value = "fItem") String itemList,
			@RequestParam(value = "status") String status,
			@RequestParam(value = "recurring") short recurring,
			HttpSession session) throws ParseException {
		

		Invoice invoice = new Invoice();
		invoice.setAmount(grandSum);
		invoice.setInvoiceId(id);
		invoice.setStatus(status);
		invoice.setTax(tax); 
		invoice.setRecurring(recurring);	
		invoice.setEnable((short)1);
		User user = (User) session.getAttribute("login");
		invoice.setUser(user);invoice.setTenant(user.getTenant());

		if (recurring == 1) {
			String dt = ""; 
			
			String newDate = "";
			String startDate = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar c = Calendar.getInstance();
			
			c.setTime(new Date());
			dt = sdf.format(c.getTime());
			String newDt="";
			System.out.println(dt);
			
			
			System.out.println("Created Date is "+createdDate);
			
			/*
			 * Comparing days in a month
			 */
			if ( Integer.parseInt(createdDate) > c.getActualMaximum(Calendar.DATE) )
			{	
				createdDate="01";
				
			}
			/*
			 * 
			 * Creating actual date object with above comparison
			 * 
			 */
			if (Integer.parseInt(createdDate) >= Integer.parseInt(dt.substring(8,
					10))) {
				
				System.out.println("in here  "+dt + "   "+dt.substring(8, 10));
				
				
				
				
				startDate =  dt.substring(0, 8)+createdDate;
				if(Integer.parseInt(createdDate)>Integer.parseInt(dueDate))
				{
					c.add(Calendar.MONTH, 1);
					dt = sdf.format(c.getTime());
					newDate = dt.substring(0, 8)+dueDate;
				}
				else
					newDate = dt.substring(0, 8)+dueDate;
				System.out.println("create "+startDate + " new one "+newDate);
				 
			} else {
				System.out.println("in here2  "+dt);
				c.add(Calendar.MONTH, 1);
				dt = sdf.format(c.getTime());
				System.out.println(dt);
				startDate =  dt.substring(0, 8)+createdDate;
				newDate = dt.substring(0, 8)+dueDate;
				/*newDate = dt.replace(dt.substring(8, 10), dueDate);
				startDate = dt.replace(dt.substring(8, 10), createdDate);*/
				System.out.println(startDate);System.out.println(newDate);
			}

			/*
			 * System.out.println("new Date"+newDate); SimpleDateFormat sdf1 =
			 * new SimpleDateFormat("MM/dd/yy");
			 * System.out.println(sdf1.format(new Date(newDate)));
			 */

			//invoice.setDueDate(new Timestamp(newDate).getTime()));
			
			invoice.setSendDate(new Timestamp(new Date(startDate).getTime()));
			invoice.setDueDate(new Timestamp(new Date(newDate).getTime()));
			
			
			
		} else {
			
		
			
			//java.util.Date utilDate = new java.util.Date();
		    SimpleDateFormat formatter = new SimpleDateFormat(user.getTenant().getDateFormat());
		    
		    Timestamp sqlDate = new Timestamp(formatter.parse(dueDate).getTime());
		    
		    invoice.setDueDate(sqlDate);
		    
		    
		    
		    //  formatter = new SimpleDateFormat("yyyy/MM/dd");
		    
		   /* invoice.setCreatedDate(new Timestamp(new Date().getTime()));*/
		    /*Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date());
			invoice.setCreatedDate(new Timestamp(c1.getTime().getTime()));*/
		    
		    
		    
		  
		    //	System.out.println("Created date ekse "+oldCreated+" "+(Timestamp) session.getAttribute("oldDtae"));
		    	/*Calendar c = Calendar.getInstance();
		    	c.setTime(new Date(createdDate));*/
		    	
		    			
		    	/*invoice.setCreatedDate(new Timestamp(new Date(formatter.format(oldCreated)).getTime()));*/
		    	
		    }
		    
		    invoice.setPaidDate(null);
		    
		    
		     
		    /*System.out.println("sqlDate:" + sqlDate);*/
			
			
			
			/*
//			  System.out.println("new Date"+newDate); 
			
			
			String dtFormat = mem.getTenant().getDateFormat();
			
			 dtFormat = dtFormat.contains("yy")?dtFormat.replace("yy", "yyyy"):dtFormat.replace("y", "yy");
			
			  SimpleDateFormat sdf1 =   new SimpleDateFormat(dtFormat);
			  Calendar c = Calendar.getInstance();
			  String dueDate1 = dueDate.replace('-', '/');
//			  System.out.println("date "+dueDate1+" "+sdf.parse(dueDate1));
				//sdf1.format(new Date(dueDate1));
			//	System.out.println("duedate1 "+dueDate1+"after parse "+sdf1.parse(dueDate1)+" Object "+new Date(dueDate1));
				//System.out.println("modified dte "+c.setTime(sdf1.format(new Date(dueDate1))));
				//invoice.setDueDate(sdf1.parse(dueDate1));
				invoice.setDueDate(new Date(sdf1.format(new Date(dueDate1))));
			  //System.out.println(sdf1.format(new Date(newDate)));
			 
			  invoice.setDueDate(new Date(sdf1.format(new Date(dueDate))));
			System.out.println(new Date(dueDate));
			invoice.setDueDate((dueDate));
			*/
			
			
			
			
			
		

		Client client = clientservice.searchClient(clientId, user.getTenant());
		invoice.setClient(client);//invoice.setPaidDate(new Timestamp(new Date().getTime())); 
		//invoice.setCreatedDate(new Timestamp(new Date().getTime()));
		invoice.setCname(invoice.getClient().getName());
		invoice.setInvoiceParentId(id);
	
		Invoice chkForDate =  invoiceservice.getInvoiceDetails(id);
		if(chkForDate==null)
			invoice.setCreatedDate(new Timestamp(new Date().getTime()));
		else
			invoice.setCreatedDate(chkForDate.getCreatedDate());
			/*if(session.getAttribute("oldDate")!=null)
	    	invoice.setCreatedDate((Timestamp) session.getAttribute("oldDate"));
	    else
	    	invoice.setCreatedDate(new Timestamp(new Date().getTime()));*/
		if(status.equals("SENT"))
			invoice.setSendDate(new Timestamp(new Date().getTime()));
		
		invoiceservice.insertInvoiceDetails(invoice);

		Set<InvoiceDetail> invoiceHistorySet = new HashSet<InvoiceDetail>(0);
		// InvoiceHistory invoiceHistory = null;

		int counter = 0;

		for (String retval : itemList.split(","))
			counter++;

		// InvoiceHistory invoiceHistory = new InvoiceHistory();
		// List<InvoiceHistory> iHist = new ArrayList<InvoiceHistory>();

		String i[] = itemList.split(",");
		String q[] = quantityList.split(",");
		String p[] = priceList.split(",");
		String a[] = amountList.split(",");

		// int iHid=59;

		// if(status.equals("SENT"))
		invoicedetailservice.deleteNonReferRecord(invoice);

		for (int j = 0; j < counter; j++) {

			InvoiceDetail invoiceDetail = new InvoiceDetail();
			// invoiceHistory.setHistId();
			invoiceDetail.setItem(i[j]);
			invoiceDetail.setQuantity(Integer.parseInt(q[j]));
			invoiceDetail.setPrice(Integer.parseInt(p[j]));
			invoiceDetail.setAmount(Integer.parseInt(a[j]));
			invoiceDetail.setInvoice(invoice);
			invoiceHistorySet.add(invoiceDetail);
			invoicedetailservice.insertInvoiceHistoryDetails(invoiceDetail);
			// iHid++;
		}
		// invoice.setInvoiceHistory(invoiceHistorySet);
		session.setAttribute("oldDate", invoice.getCreatedDate());
		if (!status.equals("DRAFT") && (recurring == 0)) {
			invoiceservice.performTask(invoiceHistorySet, invoice, session);
			return 1;
		} else if (recurring == 1 && status.equals("SENT")) {
			return 3;
		}
		return 2;
	}

	@RequestMapping("/registration/rinvoice/update/status")
	@ResponseBody
	public String updateStatus(@RequestParam(value = "id") int invoiceId,
			HttpSession session) {

		User mem = (User) session.getAttribute("login");

		invoiceservice.updateStatus(invoiceId,"SENT");

		return "Invoice Scheduled Successfully";

	}

	@RequestMapping("/user/invoice/update/paid")
	@ResponseBody
	public String updateStatusPaid(@RequestParam(value = "id") int invoiceId,
			HttpSession session) {

		String thanks="Thank You for payment of invoice";
		Invoice invoice = invoiceservice.getInvoiceDetails(invoiceId);
		invoice.setPaidDate(new Timestamp(new Date().getTime()));
		invoice.setStatus("PAID");
		invoiceservice.insertInvoiceDetails(invoice);
		User user = (User) session.getAttribute("login");
		if(user.getTenant().getThankyou()==1)
		{
			invoiceservice.sendThank(invoice.getClient().getEmailId(), invoice.getCname(), thanks);
			System.out.println("in");
		}
		return "Status Changed Successfully";

	}

	@RequestMapping("/user/invoice/paid")
	public String updateStatusPaidShow(ModelMap map, HttpSession session) {

		User user = (User) session.getAttribute("login");

		List<Invoice> invoiceList = invoiceservice.searchForPaid(user);

		map.addAttribute("invoiceList", invoiceList);
		
		
		return "UpdateStatus";

	}
	
	@RequestMapping("/user/invoice/view")
	public String invoiceView(@RequestParam(value = "id") int invoiceId, ModelMap map, HttpSession session) {

		
		User user = (User) session.getAttribute("login");

		Invoice invoice = invoiceservice.getInvoiceDetails(invoiceId);

		/*if (invoice.getRecurring() == 1)
			session.setAttribute("date", formatter.format(invoice.getDueDate())
					.subSequence(4, 5));
		else
			session.setAttribute("date", formatter.format(invoice.getDueDate()));*/
		
		session.setAttribute("sendDate", getDateFormatter(user.getTenant().getDateFormat()).format(invoice.getSendDate()));
		session.setAttribute("dueDate", getDateFormatter(user.getTenant().getDateFormat()).format(invoice.getDueDate()));
		
		/*session.setAttribute("invoiceList", invoiceList);*/
		
		
		map.addAttribute("invoice", invoice);
		
		
		return "InvoiceView";


	}
	
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/user/invoice/searchbyuser")
	public String searchInvoice(ModelMap map, HttpSession session) {

		User user = (User) session.getAttribute("login");
		List<Invoice> invoiceList = invoiceservice.searchInvoiceByUser(user);
		map.addAttribute("invoiceList", invoiceList);
		return "InvoiceByUser";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/invoice/search")
	public String searchInvoiceView() {

		return "InvoiceSearch";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/registration/invoice/search/criteria")
	@ResponseBody
	public Invoice searchInvoiceCriteria(
			@RequestParam(value = "searchInvoice") Integer invoice_id,
			@RequestParam(value = "searchItem") String searchItem,
			@RequestParam(value = "searchClient") String searchClient,
			HttpSession session) {

		
		return invoiceservice.getInvoiceDetails(invoice_id);
		
		
		
		
		
	/*	
		List<InvoiceHistory> invoiceList = invoiceservice.searchInvoiceByCriteria(invoice_id, searchItem, searchClient, session);
		
		Iterator<InvoiceHistory> it = invoiceList.iterator();
		
		while (it.hasNext()) {

			InvoiceHistory c = (InvoiceHistory) it.next();
			System.out.println("here");
			System.out.println("Getting Email" + c.getAmount() + c.getHistId()+c.getItem());

		}*/
		
		//return invoiceList;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/invoice/search/criteria1")
	@ResponseBody
	public List<InvoiceDetail> searchInvoiceCriteriads(
			@RequestParam(value = "searchInvoice") Integer invoiceId,
			@RequestParam(value = "searchItem") String searchItem,
			@RequestParam(value = "searchClient") String searchClient,
			HttpSession session) {

		User user = (User)session.getAttribute("login");
		
		return invoiceservice.searchInvoiceByCriteria(invoiceId, searchItem, searchClient, user.getTenant());
		
	/*	List<InvoiceHistory> ih = invoiceservice.searchInvoiceByCriteria(invoice_id, searchItem, searchClient, session);*/
		/*if(ih==null)
			return null;
		else
			return ih;*/
	
	
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/registration/invoice/name/search/criteria")
	@ResponseBody
	public List<Invoice> searchInvoiceCriteria1(
			@RequestParam(value = "searchInvoice") Integer invoice_id,
			@RequestParam(value = "searchItem") String searchItem,
			@RequestParam(value = "searchClient") String searchClient,
			HttpSession session) {

		
		return invoiceservice.getInvoiceDetailsByClient(searchClient);
		
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/rinvoice/searchbyuser")
	public String searchRInvoice(ModelMap map, HttpSession session) {

		User user = (User) session.getAttribute("login");
		List<Invoice> invoiceList = invoiceservice.searchRInvoiceByUser(user);
		map.addAttribute("invoiceList", invoiceList);
		return "InvoiceByUser";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/invoice/delete")
	@ResponseBody
	public String deletInvoice(@RequestParam(value = "id") int invoiceId,
			HttpSession session) {

		if (invoiceservice.deleteInvoice(invoiceId) >= 1) {
			// searchInvoice(new ModelMap(), session);
			return "Records Deleted Successfully";
		} else
			return "Records Not Found";

		// return "InvoiceOrder1";

	}

	DateFormat getDateFormatter(String dateFormat)
	{
		dateFormat = dateFormat.contains("yy")?dateFormat.replace("yy", "yyyy"):dateFormat.replace("y", "yy");
		
		return new SimpleDateFormat(dateFormat);
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/invoice/update")
	public String updateInvoice(@RequestParam(value = "id") int invoice_id,
			ModelMap map, HttpSession session) {
		
		Invoice invoice = invoiceservice.getInvoiceDetails(invoice_id);

		
		User user = (User) session.getAttribute("login");
		
		DateFormat dateFormat = getDateFormatter(user.getTenant().getDateFormat());
		
		if (invoice.getRecurring() == 1)
		{	
			session.setAttribute("sendDate",(invoice.getSendDate()).getDate());
			session.setAttribute("dueDate",(invoice.getDueDate()).getDate());
	    }
		else
			session.setAttribute("dueDate", dateFormat.format(invoice.getDueDate()));

			
		map.addAttribute("invoice", invoice);
		
		return "EditInvoice";
	}

	
	
	@RequestMapping(method = RequestMethod.POST, value = "/registration/invoice/mail")
	@ResponseBody
	public String registsrationInvoice(
			@RequestParam(value = "id") int invoice_id,

			HttpSession session) {

		try{
		System.out.println("In mailing"+new Timestamp(new Date().getTime()));

		Invoice invoice = invoiceservice.getInvoiceDetails(invoice_id);

		invoice.setSendDate(new Timestamp(new Date().getTime()));
		
		invoice.setStatus("SENT");
		
		invoiceservice.insertInvoiceDetails(invoice);
	

		invoiceservice.performTask(invoice.getInvoiceHistory(), invoice,
				session);

		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return "Invoice Mailed Successfully";

	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/dashboard/due")
	@ResponseBody
	public List<Invoice> dashBoardDue(HttpSession session) {

		
		User user = (User)session.getAttribute("login");
		
		List<Invoice> invoiceList = invoiceservice.searchDueInvoice(user.getTenant());
		
		for(Invoice invoice : invoiceList )
		{
			
			invoice.setStatus(getDateFormatter(user.getTenant().getDateFormat()).format(invoice.getDueDate()));
			
			//invoice.setDueDate(new Timestamp(new Date(formatter.format(invoice.getDueDate())).getTime()));
					
		}
			

		return invoiceList;

	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/dashboard/latest")
	@ResponseBody public List<Invoice> dashBoardLatestInvoice(HttpSession session) {

		
		User user = (User)session.getAttribute("login");
		
		List<Invoice> invoiceList = invoiceservice.searchLatestInvoice(user.getTenant());
		
		for(Invoice invoice : invoiceList )
		{
			
			invoice.setStatus(getDateFormatter(user.getTenant().getDateFormat()).format(invoice.getDueDate()));
			
		}

		return invoiceList;

	}
	
	

	
	
	
	@RequestMapping("/user/recurring")	
	 public String searchRecurringToBeDisable(ModelMap map, HttpSession session)
	{
		
		
		List<Invoice> invoiceList = invoiceservice.searchInvoiceToBeDisable((User)session.getAttribute("login"));
		map.addAttribute("invoiceList", invoiceList);
		
		for(Invoice invoice : invoiceList)
			System.out.println(invoice.getInvoiceId());
		
		return "InvoiceDisable";
	}
	
	@RequestMapping("/user/recurring/disable")	
	 public String searchRecurringToBeDisabl1e(@RequestParam(value = "id") int invoiceId)
	{
		
		
		invoiceservice.deleteInvoice(invoiceId);
		
		return "InvoiceDisable";
	}
	
	
	
	

}

