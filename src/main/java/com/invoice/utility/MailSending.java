package com.invoice.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailSending {

	
	public static void mailSend(String emailId, String customer, int invoice_id ){
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
   	 
    	MailMail mm = (MailMail) context.getBean("mailMail");
    	//mm.sendMail(emailId, customer, invoice_id);
      
}
}
