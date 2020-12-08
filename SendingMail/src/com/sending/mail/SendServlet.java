package com.sending.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name=request.getParameter("name");
		String msg=request.getParameter("msg");
		
		
		String sender="sagarsingh841222@gmail.com";
		String password="Sagarsingh7463";
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		Session session=Session.getInstance(properties,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender,password);
			}
		});
		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress("sagarsingh841222@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sagarsingh841222@gmail.com"));
			message.setSubject("Massage From "+name);
			message.setText(msg);
			
			Transport.send(message);
			
			System.out.println("Mail successfully sent"); 
		} 
		catch (Exception mex) { 
			System.out.println(mex);
		} 
		PrintWriter out=response.getWriter();
		out.println("Mail sent Successfully!!");
	}
}
