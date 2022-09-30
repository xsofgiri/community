package com.digitizeads.util;

import org.apache.log4j.Logger;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class SendGridExample {

	final static Logger logger = Logger.getLogger(SendGridExample.class);

	public String sendSendGridMail(String toAddress, String toName,
			String subject, String message) {
		SendGrid sendgrid = new SendGrid("help@encredia.com", "Encredia@2020");

		if (toAddress != null) {
			SendGrid.Email email = new SendGrid.Email();

			email.addTo(toAddress);
			email.addToName(toName);
			email.setFrom("help@encredia.com");
			email.setFromName("Encredia");
			email.setSubject(subject);
			email.setHtml(message);

			try {
				SendGrid.Response response = sendgrid.send(email);
				System.out.println(response.getMessage() + " : " + toAddress);
				return response.getMessage();
			} catch (SendGridException e) {
				System.err.println(e);
				return "Error";
			}
		} else {
			return "Error";
		}
	}

	public String sendSendGridMail(String fromAddress, String toAddress,
			String toName, String subject, String message) {
		SendGrid sendgrid = new SendGrid("help@encredia.com", "Encredia@2020");

		if (toAddress != null) {
			SendGrid.Email email = new SendGrid.Email();

			email.addTo(toAddress);
			email.addToName(toName);
			email.setFrom(fromAddress);
			email.setSubject(subject);
			email.setHtml(message);

			try {
				SendGrid.Response response = sendgrid.send(email);
				System.out.println(response.getMessage() + " : " + toAddress);
				return response.getMessage();
			} catch (SendGridException e) {
				System.err.println(e);
				return "Error";
			}
		} else {
			return "Error";
		}
	}
	
	
	public static void main(String[] args){
		SendGridExample sendGridExample = new SendGridExample();
		sendGridExample.sendSendGridMail("xsofgiri@gmail.com", "Giri", "test subject", "test message");
	}

}