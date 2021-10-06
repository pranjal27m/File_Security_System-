package project.client;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*; 
import javax.mail.internet.*; 

public class mailSender{ 

	public static void sendBeginMail(String recepient) {
		
		System.out.println("Sending mail");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.user","rapisalkar@gmail.com");
		properties.put("mail.debug","true");
		properties.put("mail.smtp.port","587");
		
		String mailId = "rapisalkar@gmail.com";
		String mailpassword= "rutu@1209";
		
		Session session = Session.getInstance(properties, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailId,mailpassword);
			}
			
		});
		
			Message message = prepareMessage(session,mailId,recepient);
			
			try {
				Transport.send(message);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Message sent successfully");
			
		
	}

	private static Message prepareMessage(Session session, String mailId, String recepient) {
		// TODO Auto-generated method stub
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailId));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Welcome to FileSecurity");
			message.setText("Dear user,\n You have successfully signed up for our service.\n\nThank You :)");
			return message;
			
		}catch(Exception ex) {
			Logger.getLogger(mailSender.class.getName()).log(Level.SEVERE,null,ex);
			ex.printStackTrace();
		}
		
		return null;
	}
	
	
}
