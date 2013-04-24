package com.invoice.utility;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;




public class App {
	public static void main(String[] args) throws Exception {
		
		
		
		 java.util.Date date= new java.util.Date();
		 System.out.println(new Timestamp(date.getTime()));
//		System.out.println("done");
//		new ClassPathXmlApplicationContext("spring-quartz.xml");
//		System.out.println("d1one");
		
		/*DecimalFormat dtime = new DecimalFormat("#.##"); 
		  double i2= Double.valueOf(dtime.format(16509.46));
		System.out.println(i2);
		System.out.println( Math.round( 3.265 * 100.0 ) / 100.0);*/
		
//		String seldt="1";
//		String dt = "2008-01-01";  // Start date
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c = Calendar.getInstance();
//		String d = sdf.format(new Date());
//		System.out.println(c.getTime());
//		c.add(Calendar.DATE, 1);  // number of days to add
//		dt = sdf.format(c.getTime()); 
//		System.out.println(dt);
//		
//		
//		
//		
		/*
		String dt = "";  // Start date
		String newDate="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		dt = sdf.format(c.getTime());
		if(Integer.parseInt(sel)>c.getActualMaximum(Calendar.DATE))
			sel="01";
				
		
		System.out.println(dt);
		System.out.println(Integer.parseInt(dt.substring(8, 10)));
		if(Integer.parseInt(sel)>=Integer.parseInt(dt.substring(8, 10)))
				{
					System.out.println("in here");
					newDate= dt.replace(dt.substring(8, 10), sel);
				}
		else
		{
			c.add(Calendar.MONTH, 1);
			dt = sdf.format(c.getTime());
			newDate= dt.replace(dt.substring(8, 10), sel);
		}
			/*if(searchBy.equals("week"))
			calculateStartDateofWeek();
		if(searchBy.equals("month"))
			calculateStartDateofMonth();
		if(searchBy.equals("year"))
			calculateStartDateofYear();*/
	/*	System.out.println("new Date"+newDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
		System.out.println(sdf1.format(new Date(newDate)));
		
		
		
		
	System.out.println(dt.substring(8, 10));
	System.out.println(dt.replace(dt.substring(8, 10),sel));
	
	
		String selected="3"
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		c1.add(Calendar.DATE, 1);
		
		if()
		
			Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.MONTH);

		String dayOfMonthStr = String.valueOf(dayOfMonth);
		
		System.out.println("FF "+dayOfMonthStr);
		
		
		
		
		final Calendar now = GregorianCalendar.getInstance();
				final int dayNumber = now.get(Calendar.DAY_OF_MONTH);
				
				
				
				
				String[] months = {"January", "February",
						  "March", "April", "May", "June", "July",
						  "August", "September", "October", "November",
						  "December"};

						  Calendar cal = Calendar.getInstance(); 
						  String month = months[cal.get(Calendar.mMONTH)];
						  System.out.println("Month name: " + month);
						  }
	
	
	
	int i =2;
	int q=3;
	
	if(i>1 & q>9)
		System.out.println("printe");
	
	

		
		
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(d);
		System.out.println(formatter.format(d));
		
			String toFormat = "2013-05-02 00:00:00";
		    java.util.Date utilDate = new java.util.Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-yy-MM");
		    formatter.parse(toFormat);
		    
		    java.sql.Date sqlDate = new java.sql.Date(formatter.parse(toFormat).getTime());
		    System.out.println("utilDate:" + utilDate);
		    System.out.println("sqlDate:" + toFormat.subSequence(9, 10));
		File fXmlFile = new File("C:/staff.xml");
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
	 
				System.out.println("First Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("email").item(0).getTextContent());
				System.out.println("Nick Name : " + eElement.getElementsByTagName("company").item(0).getTextContent());
				
	 
			}
		}
		    
		    */
		
		
	/*
	        Currency currency = Currency.getInstance(Locale.GERMANY);
	        System.out.println("Currency.getSymbol() = " + currency.getSymbol());

	        currency = Currency.getInstance(Locale.UK);
	        System.out.println("Currency.getSymbol() = " + currency.getSymbol());

	        currency = Currency.getInstance(Locale.US);
	        System.out.println("Currency.getSymbol() = " + currency.getSymbol());
*/
	        Currency currency = Currency.getInstance(new Locale("hi", "IN"));
	        System.out.println("Currency.getSymbol() = " + currency.getSymbol());
	
		
		double iii  =  3443.4344d;
		
		Locale locale=new Locale("it", "IT");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        String s = DateFormat.getDateInstance(DateFormat.MEDIUM,locale).format(new Date());
        System.out.println("DateFormat.SHORT : " + s);

        System.out.println("Format currency: " + format.format(iii));

		
		/*String currencyFormat= "it,IT";
      //  System.out.println("pigg "+tenant.getCurrencyFormat());
String curFormat[] = currencyFormat.split(",");
		
	//System.out.println(retval[0] + "   " +retval[1] );
		Locale locale=new Locale(curFormat[0], curFormat[1]);

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        Currency cur = Currency.getInstance(locale);
        System.out.println("Format currency: " + cur.getSymbol());

double iii =  232.232d;
Locale locale=new Locale("","EURO"); 

NumberFormat format = NumberFormat.getCurrencyInstance(locale);
String s = DateFormat.getDateInstance(DateFormat.MEDIUM,locale).format(new Date());
System.out.println("DateFormat.SHORT : " + s);

System.out.println("Format currency: " + format.format(iii));

//System.out.println(retval[0] + "   " +retval[1] );
//Locale locale=new Locale(curFormat[0], curFormat[1]);

/*NumberFormat format = NumberFormat.getCurrencyInstance(locale);*/
/*Currency cur = Currency.getInstance(locale);
System.out.println("Format currency: " + cur.getSymbol());
*/
		    
		    
		/*    
	        boolean iAmInIndia = "IN".equals(System.getProperty("user.country"));
	        DecimalFormat formatter = iAmInIndia ? new DecimalFormat("\u20B9 000") : new DecimalFormat("\u00A4 000");
		    
		    
		    
		    
		    
	        double amount = 123.45D; //yes, I know that I should be using longs
	        Locale uk = Locale.UK;
	        Locale fr = Locale.FRANCE;
	        Currency euro = Currency.getInstance("EUR");
		    
		    System.out.println(currency.getSymbol(Locale.UK));
		    */
		    
		    
		    /*
	
	//SQL >  yyyy-MM-dd
		
		

*
*
*

		
		
		
		*/
		
		
		
		
		/*Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());

	    // "calculate" the start date of the week
	    Calendar first = (Calendar) cal.clone();
	    first.add(Calendar.DAY_OF_WEEK, 
	              first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

	    // and add six days to the end date
	    Calendar last = (Calendar) first.clone();
	    last.add(Calendar.DAY_OF_YEAR, 6);

	    // print the result
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println(df.format(first.getTime()) + " -> " + 
	                       df.format(last.getTime()));
		
		System.out.println(last.getTime());*/
		
		/* Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);
		    System.out.println(c.getTime()); */
		
		/*Calendar cld = Calendar.getInstance();
		 cld.set(Calendar.DAY_OF_YEAR,1);
		 System.out.println(cld.getTime());
		
		Calendar cal = Calendar.getInstance();
		 Consider whether you need to set the calendar's timezone. 
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH);  0 through 11 
		int quarter = (month / 3) + 1;
		System.out.println(quarter);
		String dt = ""; // Start date
		String createdDate="25";
		String dueDate="31";
		String newDate = "";
		String startDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date());
		dt = sdf.format(c.getTime());
		String newDt="";
		System.out.println(dt);
		
		
		 * Comparing days in a month
		 
		if ( Integer.parseInt(createdDate) > c.getActualMaximum(Calendar.DATE) )
		{	
			createdDate="01";
			
		}
		
		 * 
		 * Creating actual date object with above comparison
		 * 
		 
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
			newDate = dt.replace(dt.substring(8, 10), dueDate);
			startDate = dt.replace(dt.substring(8, 10), createdDate);
			System.out.println(startDate);System.out.println(newDate);
		}
		
		
		
		
		 System.out.println("new Date"+newDate); SimpleDateFormat sdf1 =
		  new SimpleDateFormat("yy/MM/dd");
		  System.out.println(sdf1.format(new Date(startDate)));
		  System.out.println(sdf1.format(new Date(newDate)));
		 

		//invoice.setDueDate(new java.sql.Date(newDate).getTime()));
		 java.sql.Date sqlDate = new java.sql.Date(new Date(newDate).getTime());
	
		
			java.sql.Date sqlDate = new java.sql.Date(new Date(startDate).getTime());
			System.out.println(sqlDate);
			System.out.println(new java.sql.Date(new Date(newDate).getTime()));
		
		
		
		
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		
		System.out.println(ourJavaDateObject);
		System.out.println(new java.sql.Date(System.currentTimeMillis()));
		java.sql.Date ourJavaDateObject1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(ourJavaDateObject1);
		 Calendar c = Calendar.getInstance();   // this takes current date
		 c.add(Calendar.APRIL, 1);
		 System.out.println(c.get(Calendar.YEAR));
		 Date d = c.getTime();
		 System.out.println(d);*/
        
        
        
        
        /*
        Date d   =  new Date();
        
        System.out.println(d);
        
        int count = 4;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, +count);
        System.out.println(calendar.getTime());
        
        
        
        */
        
        
        
       /* Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK, 
                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        // print the result
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(df.format(first.getTime()) + " -> " + 
                           df.format(last.getTime()));
    	
        System.out.println(df.format(first.getTime()));
        System.out.println(df.format(last.getTime()));
        */
        
   /*     Calendar c = Calendar.getInstance();   // this takes current date
		 c.set(Calendar.DAY_OF_MONTH, 1);
		 Date d = c.getTime();
		 SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
		 System.out.println(df1.format(d.getTime()));
		 System.out.println(df1.format(new Date()));
		 //System.out.println(c.getTime()); 
*/        
        
		/* Calendar c = Calendar.getInstance();
		 c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		 for(int i=4; i>=0; i--)
		 {
			     System.out.println("StartDate" + new java.sql.Date((c.getTime()).getTime()));
				//System.out.print("Start Date : " + c.getTime() + ", ");
			     c.add(Calendar.DAY_OF_WEEK, -7);
			     System.out.println(new java.sql.Date((c.getTime()).getTime()));
			     Calendar c1 = (Calendar) c.clone();
			     c1.add(Calendar.DATE, +6);
			     System.out.println("End Date : " + new java.sql.Date((c1.getTime()).getTime()));	
			     
			     //System.out.println(new java.sql.Date((c.getTime()).getTime()));
				 
		     //c.add(Calendar.DAY_OF_WEEK, 7);
		 }
        */
      /*  Calendar c = Calendar.getInstance();   // this takes current date
        for(int i=1; i<=5; i++)
		 {
        
		 c.set(Calendar.DAY_OF_MONTH, 1);
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		 System.out.println( df.format(c.getTime()));
		 
		 c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		 System.out.println( df.format(c.getTime()));
		 //System.out.println(df.format(new Date()));
		 c.add(Calendar.MONTH, -1);
		 }
        
        Calendar cal =  Calendar.getInstance();
        cal.add(Calendar.MONTH ,-1);
        //format it to MMM-yyyy // January-2012
        String previousMonthYear  = new SimpleDateFormat("MMMM").format(cal.getTime()).toString();
        System.out.println(previousMonthYear);

        System.out.println(new java.sql.Date(new Date().getTime()));*/
        
      
      /*  Locale locale=new Locale("hi","IN"); 
        Locale locBR = new Locale("pt", "BR"); // Brazil
        Locale locDK = new Locale("da", "DK"); // Denmark
        Locale locIT = new Locale("it", "IT");//Italy
       Currency cur = Currency.getInstance(locBR);
       String symb  = cur.getSymbol();*/
       
       
      /*  System.out.println("Format currency: " + symb);
		 String s = "2013-04-08 00:00:00.0";
		 System.out.println(s.substring(0,10));
		 
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE,-1);
			Timestamp timestamp = new Timestamp(c.getTimeInMillis());
			timestamp.setHours(0);timestamp.setMinutes(0);
			timestamp.setSeconds(0);
			timestamp.setNanos(0);
			c.setTimeInMillis(timestamp.getTime());
			c.add(Calendar.DATE,2);
			System.out.println(timestamp+""+new Timestamp(c.getTimeInMillis()));
			Calendar cal = GregorianCalendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 23);// I might have the wrong Calendar constant...
			cal.set(Calendar.MONTH, 8);// -1 as month is zero-based
			cal.set(Calendar.YEAR, 2009);
			Timestamp tstamp = new Timestamp(cal.getTimeInMillis());
			System.out.println(tstamp);*/
		/* Locale locale=new Locale("de","DE"); 

         NumberFormat format = NumberFormat.getCurrencyInstance(locale);
         String s = DateFormat.getDateInstance(DateFormat.MEDIUM,locale).format(new Date());
         System.out.println("DateFormat.SHORT : " + s);

         System.out.println("Format currency: " + format.format(12));*/
      

       
       
        System.out.println(new BigInteger(26, random).toString(32));
       

        
	}
	 private static SecureRandom random = new SecureRandom();
}