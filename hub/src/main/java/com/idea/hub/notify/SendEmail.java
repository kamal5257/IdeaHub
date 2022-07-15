package com.idea.hub.notify;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class SendEmail {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void mailSend(String toEmail, String Sub, String Body) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("uic.19mca8141@gmail.com");
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(Sub);
		mailMessage.setText(Body);
		
		javaMailSender.send(mailMessage);
		System.out.println("SUCCESS");
	}
	
	
	public void notifyUser() throws MessagingException {
		 final String username = "uic.19mca8141@gmail.com";  
		 final String toMail = "kamal.chadha162@gmail.com"; 
	     final String password = "avjuajhzueitchhn";
	     final String host = "localhost";
	     
	     Properties props = new Properties();   
	     props.put("mail.smtp.auth", "true");              
	     props.put("mail.smtp.starttls.enable", "true");   
	     props.put("mail.smtp.host", "173.194.202.108");    
	     props.put("mail.smtp.port", "587");     
	     props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
	     Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    	 @Override
	    	 protected PasswordAuthentication getPasswordAuthentication() {
	    		 return new PasswordAuthentication(username,password);
	         }
	       });
	     Message message = prepareMessage( session,  username,  toMail);
	     Transport.send(message);
//	   
	}
	
	private static Message prepareMessage(Session session, String fromMail, String toMail) {
		  try {
		         Message message = new MimeMessage(session);   
		         message.setFrom(new InternetAddress(fromMail));
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
		         message.setSubject("hello");
		         message.setText("Yo it has been sent");
		               return message;   //send Message
		     } catch (MessagingException e) {
		         throw new RuntimeException(e);
		     }
	}
	
	public static void main(String args[]) throws MessagingException {
		SendEmail sendEmail = new SendEmail();
		sendEmail.notifyUser();
	}
	
}
