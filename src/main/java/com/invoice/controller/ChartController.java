package com.invoice.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class ChartController {

	@Autowired
	private InvoiceService invoiceservice;

	@Autowired
	private InvoiceDetailService invoicehistoryservice;

	@Autowired
	private ItemService itemservice;

	@Autowired
	private ClientService clientservice;


String startDate;
String endDate;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/dashboard/graph")
	@ResponseBody
	public String dashBoardGraph(@RequestParam(value="graphFormat") int graphFormat, HttpSession session) {
		
		

		User usr = (User)session.getAttribute("login");
		
		
		//Map<java.sql.Date, Long> chartPlotMap = invoiceservice.searchForGraph(usr.getTenant());
		List Data = invoiceservice.searchForGraph(usr.getTenant(), graphFormat);
		Map<java.sql.Timestamp, Long> chartPlotMap =  (Map<java.sql.Timestamp, Long>) Data.get(0);
		Map<java.sql.Timestamp, Long> chartPlotMap1 =  (Map<java.sql.Timestamp, Long>) Data.get(1);
//		System.out.println("here invoicked");
		
		StringBuffer chartData = new StringBuffer();
		chartData
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><chart caption='"
						+ "Graph"
						+ "' subcaption='Interval Chart' xAxisName='Timeline' yAxisName='Amount' numberPrefix='Rs.' showAlternateVGridColor='1' bgColor='FFFFFF' numVDivLines='10' numDivLines='10' showValues='0' showBorder='0' formatNumber='1' formatNumberScale='0' yAxisValuesStep='1' setAdaptiveYMin='1' alternateVGridColor='D9E5F1' alternateVGridAlpha='100'>");
		
//		System.out.println("here invoicked");
		StringBuffer chartdataend = new StringBuffer("</chart>");
		Set<java.sql.Timestamp> se = chartPlotMap.keySet();
		Set<java.sql.Timestamp> se1 = chartPlotMap1.keySet();
		int i=1;
		
		Calendar cal = Calendar.getInstance();
		
		chartData.append("<categories>");
		for (Date d : se) {
			i++;
			String currentDateFormat = usr.getTenant().getDateFormat();
			
			DateFormat df2 = new SimpleDateFormat(currentDateFormat.contains("yy")?currentDateFormat.replace("yy", "yyyy"):currentDateFormat.replace("y", "yy"));
			cal.setTime(d);
			if(graphFormat==1)
		      chartData.append("<category label='"+df2.format(new java.sql.Date((cal.getTime()).getTime()))+"'/>");
		    else if(graphFormat==2)
		    {
		    	
		    	
		    	 startDate = df2.format(new java.sql.Date((cal.getTime()).getTime()));
				cal.add(Calendar.DAY_OF_WEEK, +6);
				 endDate =  df2.format(new java.sql.Date((cal.getTime()).getTime()));
		    	chartData.append("<category label='("+startDate+") - ("+endDate+")'/>");
		    }
		    else
		    {	
		    	
		    	String month = new SimpleDateFormat("MMMM").format(cal.getTime()).toString();
		    	chartData.append("<category label='"+month+"'/>");
		    }
		}
		chartData.append("</categories>");
		chartData.append("<dataset seriesname=' Paid ' color='F0807F' showValues='1'>");
		for (Date d : se) {
			chartData.append("<set value='"+chartPlotMap.get(d)+"'/>");
			
		}
		chartData.append("</dataset>");
		chartData.append("<dataset seriesname=' Due ' color='F1C7D2'  showValues='1'>");
		
		for (Date d : se1) {
			
			chartData.append("<set value='"+chartPlotMap1.get(d)+"'/>");
			
		}
		chartData.append("</dataset>");
		chartData.append(chartdataend);
		System.out.println("chart data..."+chartData);
		return chartData.toString();
		

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dashboard/view")
	
	public String dashBoard(HttpSession session) {
		return "Dashboard";
	}
	

	
	
	
	
	
	
	

}

