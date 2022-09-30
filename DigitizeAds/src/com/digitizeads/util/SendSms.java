package com.digitizeads.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SendSms { 

	public String sendsmsBhashSMS(String phoneNumber, String msg) {

		try { 
			// Construct data
			String user = "user=" + "adwitg";
			String hash = "&pass=" + "9880534661";
			String sender = "&sender=" + "ADWITG";
			String message = "&text=" + msg;
			String numbers = "&phone=" + phoneNumber;
			String additionalInfo = "&priority=ndnd&stype=normal";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("http://bhashsms.com/api/sendmsg.php?").openConnection();
			String data = user + hash + numbers + message + sender+additionalInfo;
			System.out.println("data : "+data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("stringBuffer.toString() : "+stringBuffer.toString());
			
			return stringBuffer.toString();
			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	
	}
	
	
	public String sendsms(String phoneNumber, String msg) {
		try { 
			// Construct data
			String user = "username=" + "sales@furlay.com";
			String hash = "&apiKey=" + "Ir4+7hPnnw0-H2FOJT8BunPeZBkdoXefLhhclQLjZK";
			String message = "&message=" + msg;
//			String sender = "&sender=" + "ENCREDIA";
			String numbers = "&numbers=" + phoneNumber;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
//			String data = user + hash + numbers + message + sender;
			String data = user + hash + numbers + message;
			System.out.println("data : "+data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("stringBuffer.toString() : "+stringBuffer.toString());
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	} 
	
	public static void main(String[] args){
		SendSms sendSMS = new SendSms();
		System.out.println(sendSMS.sendsms("9986057474", "Hi Giri, \nThanks for waiting"));
	}

}
