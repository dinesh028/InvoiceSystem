package com.invoice.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailMail
{
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String emailId, String customerName, int invoice_id, String content) {
	
		MimeMessage message = mailSender.createMimeMessage();
		String invoicename="C:/prj/pdf/invoice"+invoice_id+".pdf";
		
		System.out.println(emailId+" "+customerName+" "+invoice_id+" "+content);
		try{
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			
			helper.setTo(emailId);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(
					simpleMailMessage.getText(), customerName, content));
			
			FileSystemResource file = new FileSystemResource(""+invoicename);
			
			helper.addAttachment(file.getFilename(), file);
			
			
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
		
		
	}
	
	public void sendThank(String emailId, String customerName, String content)
	{
		MimeMessage message = mailSender.createMimeMessage();
	
		try{
			
			System.out.println("   "+emailId+" "+customerName + " "+content);
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			
			helper.setTo(emailId);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(
					simpleMailMessage.getText(), customerName, content ));
			
						
			
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}
	
	
	public void sendReminder(String emailId, String customerName, String content)
	{
		MimeMessage message = mailSender.createMimeMessage();
	
		try{
			
			System.out.println("   "+emailId+" "+customerName + " "+content);
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			
			helper.setTo(emailId);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(
					simpleMailMessage.getText(), customerName, content ));
			
						
			
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}
	
	
	
	
	
	
}
