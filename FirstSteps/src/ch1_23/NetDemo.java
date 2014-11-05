package ch1_23;
import java.io.*;
import java.net.*;
import java.util.*;


public class NetDemo {
	public static void qmain(String args[]) throws Exception{
System.out.println("---InetAddress використання методів-фабрик"); 
		InetAddress Address = InetAddress.getLocalHost(); 
	    System.out.println(Address); 
	    
	    Address = InetAddress.getByName("www.HerbSchildt.com"); 
	    System.out.println(Address); 
	   
	    InetAddress SW[] = InetAddress.getAllByName("www.nba.com"); 
	    for (int i=0; i<SW.length; i++) 
	    	System.out.println(SW[i]); 
	    System.out.println();
	    
System.out.println("---Socket Надсилаємо запит на віддалений сервер internic.net і отримуємо відповідь" +
		" - перші 18 рядків");
		int c;
		
		// Створює сокетне з'єднання з internic.net, порт 43. 
		Socket s = new Socket("whois.internic.net", 43); 
		
		// Отримує вхідний та вихідний потоки.
		InputStream in = s.getInputStream(); 
		OutputStream out = s.getOutputStream(); 
		
		// Створює рядок запиту. 
		// Перетворює в байти. 
		byte buf[] = "MHProfessional.com\n".getBytes(); 
		
		// надсилаємо запит. 
		out.write(buf); 
		
		// Читає та друкує відповідь. 
		int counter = 0;
		while (((c = in.read()) != -1) && (counter != 18)) { 
			System.out.print((char) c);
			if (c == '\n') counter++;
		} 
		s.close(); 
		System.out.println();
		
System.out.println("---URL відвідуємо сайт http://www.HerbSchildt.com/abouths.htm");
		URL hp = new URL("http://www.HerbSchildt.com/abouths.htm"); 
		
		System.out.println("Protocol: " + hp.getProtocol()); 
		System.out.println("Port: " + hp.getPort()); 
		System.out.println("Host: " + hp.getHost()); 
		System.out.println("File: " + hp.getFile()); 
		System.out.println("Повна (комбінована) адреса:" + hp.toExternalForm()); 
		System.out.println();

System.out.println("---URLConnection ");
		//int c; 
		c = 0;
		
		URL hp1 = new URL("http://www.internic.net"); 
		URLConnection hpCon = hp1.openConnection(); 
		
		// get date 
		long d = hpCon.getDate();  
		if(d==0) 
			System.out.println("No date information."); 
		else 
			System.out.println("Date: " + new Date(d)); 
		
		// get content type 
		System.out.println("Content-Type: " + hpCon.getContentType()); 
		
		// get expiration date 
		d = hpCon.getExpiration(); 
		if(d==0) 
			System.out.println("No expiration information."); 
		else 
			System.out.println("Застаріє: " + new Date(d)); 
		
		// get last-modified date 
		d = hpCon.getLastModified(); 
		if(d==0) 
			System.out.println("No last-modified information."); 
		else 
			System.out.println("Last-Modified: " + new Date(d)); 
		
		// get content length 
		long len = hpCon.getContentLengthLong(); 
		if(len == -1) 
			System.out.println("Content length unavailable."); 
		else 
			System.out.println("Content-Length: " + len + " bytes"); 
		
		if(len != 0) { 
			System.out.println("=== Content (first 6 strings) ==="); 
			InputStream input = hpCon.getInputStream(); 
		
			int count = 0;
			while (((c = input.read()) != -1) && (count != 6)) { 
				System.out.print((char) c); 
				if (c == '\n') count++;
		  } 
		  input.close(); 
		
		} else { 
			System.out.println("No content available."); 
		} 
		System.out.println();

System.out.println("---HttpURLConnection встановлюємо з'єднання з Google.com");
		
		HttpURLConnection hpCon1 = 
				(HttpURLConnection) new URL("http://www.google.com").openConnection(); 
		
		// Display request method. 
		System.out.println("Request method is " + 
		                   hpCon1.getRequestMethod()); 
		
		// Display response code. 
		System.out.println("Response code is " + 
		                   hpCon1.getResponseCode()); 
		
		// Display response message. 
		System.out.println("Response Message is " + 
		                   hpCon1.getResponseMessage()); 
		
		// Get a list of the header fields and a set 
		// of the header keys. 
		Map<String, List<String>> hdrMap = hpCon1.getHeaderFields(); 
		Set<String> hdrField = hdrMap.keySet(); 
		
		System.out.println("\nHere is the header:"); 
		
		// Display all header keys and values.. 
		for(String k : hdrField) { 
		  System.out.println("Key: " + k + "  Value: " + hdrMap.get(k)); 
		} 
		System.out.println();
	}
}
