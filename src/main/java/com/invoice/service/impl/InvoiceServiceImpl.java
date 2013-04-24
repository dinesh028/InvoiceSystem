package com.invoice.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.dao.InvoiceDAO;
import com.invoice.model.Invoice;
import com.invoice.model.InvoiceDetail;
import com.invoice.model.Tenant;
import com.invoice.model.User;
import com.invoice.service.InvoiceDetailService;
import com.invoice.service.InvoiceService;
import com.invoice.service.PDFGenerateService;
import com.invoice.utility.MailMail;

@Service("invoiceservice")
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDAO invoicedao;
	
	@Autowired
	private PDFGenerateService pdfservice;
	
	@Autowired
	private MailMail mailMail;
	
	Timestamp startDate;
	
	Timestamp endDate;
	

	@Autowired
	private InvoiceDetailService invoicehistoryservice;

	public void insertInvoiceDetails(Invoice invoice) {

		invoicedao.insertInvoiceDetails(invoice);
	}

	public int updateStatus(int invoice, String status) {
		return invoicedao.updateStatus(invoice, status);

	}

	public int updateRStatus(int invoice) {
		return invoicedao.updateRStatus(invoice);

	}

	public int getNextInvoiceID() {
		return invoicedao.getNextInvoiceID();
	}

	public List<Invoice> searchAllInvoice() {
		return invoicedao.searchAllInvoice();
	}

	public void performTask(Set<InvoiceDetail> invoiceDetailSet,
			Invoice invoice, HttpSession session) {
		String content  =  "Please find attachement of invoice";
		pdfservice.generate(invoiceDetailSet, invoice);
		//mm.sendMail(emailId, customer, invoiceId);
		mailMail.sendMail(invoice.getClient().getInvoiceEmailId(), invoice
				.getClient().getName(), invoice.getInvoiceId(), content);

	}
	
	
	public List<Invoice> searchInvoiceForDue()
	{
		
		
		
		
			return	invoicedao.searchInvoiceForDue();
	}
	
	
	
	@Override
	public void sendThank(String emailid, String customerName, String content) {
		mailMail.sendThank(emailid, customerName, content);

	}

	public List<Invoice> searchInvoiceByUser(User user) {

		return invoicedao.searchInvoiceByUser(user);
	}

	public int deleteInvoice(int invoiceId) {

		return invoicedao.deleteInvoice(invoiceId);
	}

	public Invoice getInvoiceDetails(int invoiceId) {

		return invoicedao.getInvoiceDetails(invoiceId);
	}

	public List<Invoice> searchRInvoiceByUser(User user) {
		return invoicedao.searchRInvoiceByUser(user);
	}

	public List<Invoice> searchForPaid(User user) {
		
		return invoicedao.searchForPaid(user, getCurrentEndDate());

	}

	public int updateStatusPaid(int invoiceId) {
		return invoicedao.updateStatusPaid(invoiceId);
	}

	@Override
	public List<InvoiceDetail> searchInvoiceByCriteria(Integer invoiceId,
			String searchItem, String searchClient, Tenant tenant) {
		// TODO Auto-generated method stub

		return invoicedao.searchInvoiceByCriteria(invoiceId, searchItem,
				searchClient, tenant);
		
		/*Iterator<InvoiceHistory> it = ih.iterator();
		InvoiceHistory in=null;
 		while(it.hasNext())
		{
			System.out.println("here1");
			in = (InvoiceHistory)it.next();
			System.out.println("pringtin "+in.getInvoice().getInvoiceId()+" "+in.getItem());
		}
		System.out.println("in2");*/
		

		
	}

	@Override
	public List<Invoice> getInvoiceDetailsByClient(String searchClient) {
		// TODO Auto-generated method stub
		return invoicedao.getInvoiceDetailsByClient(searchClient);
	}

	@Override
	public List<Invoice> searchForExcel(String startDate, String endDate, Tenant tenant)  {
		// TODO Auto-generated method stub
		
		try {
			
			return invoicedao.searchForExcelWeeek(formatDate(startDate, tenant), formatDate(endDate, tenant), tenant);
			
			
				
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				
		  
		
	}

	public java.sql.Date formatDate(String date, Tenant tenant) throws ParseException
	{
		System.out.println("inside it "+date);
		
		
		
		SimpleDateFormat df = new SimpleDateFormat(tenant.getDateFormat());
	//	java.sql.Date sqlDate = new java.sql.Date(new Date(df.format(date)).getTime());
		java.sql.Date sqlDate = new java.sql.Date(df.parse(date).getTime());
		System.out.println("pringting "+sqlDate);
		return sqlDate;
	}
	
	
	
	/*public void calculateStartDateofYear()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR,1);
		 Date d = c.getTime();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		 startDate = df.format(d.getTime());
		 endDate = df.format(new Date());
	}*/
	 
	
	
	/*public void calculateStartDateofQuarter()
	{
		Calendar c = Calendar.getInstance();
		String monthName=null;
		c.setTime(new Date());
		
		int month = c.get(Calendar.MONTH);  
		int quarter = (month / 3) + 1;
		switch(quarter)
		{
			case 1:
				startDate="";
				break;
			case 4:
				monthName="APRIL";
				break;
			case 7:
				monthName="JULY";
				break;
			case 10:
				monthName="OCTOBER";
				break;	
		}
		
		Calendar ca = Calendar.getInstance();
		
		
		
	}*/
	
	
	
	
	/*public void calculateStartDateofWeek(){
	Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());

    // "calculate" the start date of the week
    Calendar first = (Calendar) cal.clone();
    first.add(Calendar.DAY_OF_WEEK, 
              first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

    // and add six days to the end date
    Calendar last = (Calendar) first.clone();
    last.add(Calendar.DAY_OF_YEAR, 6);

    // print the result
    SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
    System.out.println(df.format(first.getTime()) + " -> " + 
                       df.format(last.getTime()));
	
    startDate = df.format(first.getTime());
    endDate =  df.format(last.getTime());
    
    
	}*/

	@Override
	public List<Invoice> searchDueInvoice(Tenant tenant) {
				
		return	invoicedao.searchDueInvoice(tenant);
				
	}

	@Override
	public List<Invoice> searchLatestInvoice(Tenant tenant) {
		// TODO Auto-generated method stub
		return invoicedao.searchLatestInvoice(tenant, getCurrentEndDate());
	}

	
	public Timestamp getCurrentStartDate()
	{
		Date currentDate =  new Date();
		
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		timestamp.setHours(0);timestamp.setMinutes(0);
		timestamp.setSeconds(0);
		timestamp.setNanos(0);
		return timestamp;
		
	}
	
	public Timestamp getCurrentEndDate()
	{
		Date currentDate =  new Date();
		
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		timestamp.setHours(23);timestamp.setMinutes(59);
		timestamp.setSeconds(59);
		timestamp.setNanos(59);
		return timestamp;
		
	}
	
	
	
	@Override
	public List<Invoice> searchByCurrentDate() {
		
		
		return invoicedao.searchByCurrentDate(getCurrentStartDate(), getCurrentEndDate()); 
	}
	
	
	public void calcMonth(Tenant tenant, int count, Calendar c)
	{
		 c.set(Calendar.DAY_OF_MONTH, 1);
		 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		 startDate = formatStartTimeStamp(new Timestamp((c.getTime()).getTime()));
		
		 if(count==1)
		 {
			 endDate = formatEndTimeStamp(new Timestamp(new Date().getTime()));
			 c.add(Calendar.MONTH, -1);
		 }
		 else{
			 
			 c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			 endDate =  formatEndTimeStamp(new Timestamp((c.getTime()).getTime()));
			 c.add(Calendar.MONTH, -1);
		 }
		
	}
	
	
	public void calcWeek(Tenant tenant, int count, Calendar c)
	{
		
		if(count==4)
		{
			startDate = formatStartTimeStamp(new Timestamp((c.getTime()).getTime()));
			
			endDate = formatEndTimeStamp(new Timestamp((new Date()).getTime()));
			
		}
		else{
			 c.add(Calendar.DAY_OF_WEEK, -7);
		     startDate = formatStartTimeStamp(new Timestamp((c.getTime()).getTime()));
		     Calendar c1 = (Calendar) c.clone();
		     c1.add(Calendar.DATE, +6);
		     endDate =  formatEndTimeStamp(new Timestamp((c1.getTime()).getTime()));
		
		}
	}
	
	
	
	
	
	@Override
	public List searchForGraph(Tenant tenant, int graphFormat) {
		// TODO Auto-generated method stub
		
		
		Map<java.sql.Timestamp, Long> mapData = new LinkedHashMap<java.sql.Timestamp, Long>();
		Map<java.sql.Timestamp, Long> mapData1 = new LinkedHashMap<java.sql.Timestamp, Long>();
		
		
		
		if(graphFormat==1){
			for(int i=0; i<=4; i++)
			{
				Long sum = invoicedao.searchForGraph(calcStartTimestamp(tenant, i), calcEndTimestamp(tenant, i), tenant );
				mapData.put(calcStartTimestamp(tenant, i ), sum);
//				System.out.println(calcStartTimestamp(tenant, i )+"  "+calcEndTimestamp(tenant, i ));
			}
			for(int i=0; i<=4; i++)
			{
				Long sum = invoicedao.searchForGraph1(calcStartTimestamp(tenant, i ), calcEndTimestamp(tenant, i), tenant );
				mapData1.put(calcStartTimestamp(tenant, i ), sum);
				calcStartTimestamp(tenant, i );
			}
		}
		else if(graphFormat==2){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			for(int i=4; i>=0; i--)
			{
				calcWeek(tenant, i, c);
//				System.out.println("start date "+startDate);
//				System.out.println("end date "+endDate);
				Long sum = invoicedao.searchForGraph(startDate, endDate, tenant );
				mapData.put( startDate, sum);
			}
			c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			System.out.println("due in in due ");
			for(int i=4; i>=0; i--)
			{
				calcWeek(tenant, i, c);
//				System.out.println("start date "+startDate);
//				System.out.println("end date "+endDate);
				Long sum = invoicedao.searchForGraph1(startDate, endDate, tenant);
				mapData1.put(endDate, sum);
			}	
			
		}
		else{
			 Calendar c = Calendar.getInstance(); 
			 for(int i=1; i<=5; i++)
			 {
				
				    calcMonth(tenant, i,  c);
//					System.out.println("start date "+startDate);
//					System.out.println("end date "+endDate);
					Long sum = invoicedao.searchForGraph(startDate, endDate, tenant );
					mapData.put( startDate, sum);
				}
			 	c = Calendar.getInstance(); 
				System.out.println("due in in due ");
				for(int i=1; i<=5; i++)
				 {
					
					    calcMonth(tenant, i,  c);
//						System.out.println("start date "+startDate);
//						System.out.println("end date "+endDate);
						Long sum = invoicedao.searchForGraph1(startDate, endDate, tenant );
						mapData1.put( startDate, sum);
					}	
		}
			
		List<Map<java.sql.Timestamp, Long>> list = new ArrayList<Map<java.sql.Timestamp, Long>>();
		Map<java.sql.Timestamp, Long> m1= new LinkedHashMap<Timestamp, Long>();
		for(int i=mapData.size()-1; i>=0; i--){
		m1.put((java.sql.Timestamp)mapData.keySet().toArray()[i], (Long)mapData.values().toArray()[i]);
		}
		list.add(m1);
		m1= new LinkedHashMap<Timestamp, Long>();
		for(int i=mapData1.size()-1; i>=0; i--){
			m1.put((java.sql.Timestamp)mapData1.keySet().toArray()[i], (Long)mapData1.values().toArray()[i]);
			}
		list.add(m1);
		
		
		
		
		return list;
	}
	
	
	public java.sql.Date startDate(Tenant tenant)
	{
		/*SimpleDateFormat df = new SimpleDateFormat(tenant.getDateFormat());
		startDate = df.format(new Date());*/
		
		return new java.sql.Date((new java.util.Date()).getTime());
	}
	
	public java.sql.Date calcDate(Tenant tenant, int count)
	{
		SimpleDateFormat df = new SimpleDateFormat(tenant.getDateFormat());
		
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.add(Calendar.DATE, -count);
	     
		//startDate = df.format(new Date());
		
		return new java.sql.Date((calendar.getTime()).getTime());
	}
	
	
	public java.sql.Date endDate(Tenant tenant)
	{
		SimpleDateFormat df = new SimpleDateFormat(tenant.getDateFormat());
		
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.add(Calendar.DATE, -4);
	     
		//startDate = df.format(new Date());
		
		return new java.sql.Date((calendar.getTime()).getTime());
	}
	
	public Timestamp calcEndTimestamp(Tenant tenant, int count)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentEndDate());
	    calendar.add(Calendar.DATE, -count);
	    return formatEndTimeStamp(new Timestamp((calendar.getTime()).getTime()));
	    
	}
	
	
	public Timestamp calcStartTimestamp(Tenant tenant, int count)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentStartDate());
	    calendar.add(Calendar.DATE, -count);
	    return formatStartTimeStamp(new Timestamp((calendar.getTime()).getTime()));
	    
	}
	
	
	
	public void endDate()
	{
		 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(new Date());
	     calendar.add(Calendar.DATE, -4);
	     
	}
	
	public Timestamp formatStartTimeStamp(Timestamp timestamp)
	{
		
		timestamp.setHours(0);timestamp.setMinutes(0);
		timestamp.setSeconds(0);
		timestamp.setNanos(0);
		return timestamp;
	}
	
	public Timestamp formatEndTimeStamp(Timestamp timestamp)
	{
		
		timestamp.setHours(23);timestamp.setMinutes(59);
		timestamp.setSeconds(59);
		timestamp.setNanos(59);
		return timestamp;
	}

	@Override
	public List<Invoice> searchInvoiceToBeDisable(User user) {
		// TODO Auto-generated method stub
		
		return invoicedao.searchInvoiceToBeDisable(user, getCurrentEndDate());
		
	}
	
	
	
	
	
	
	
}
