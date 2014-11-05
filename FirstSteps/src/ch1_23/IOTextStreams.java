package ch1_23;
import java.io.*;

public class IOTextStreams {
	public static void main(String args[]){
System.out.println("�� ������ �������� ���������� ������ IO ��� �����: " +
				"Reader �� Writer");
System.out.println("---FileW ����� ������ � �����\n" +
					"����������� (char[]),\n" +
					"����� char[],\n" +
					"������� ������ char[]");
		
		String source = "Now is the time for all good men\n" 
			      + " ������ �� �������� �����\n" 
			      + " and pay their due taxes.";
		char buffer[] = new char[source.length()]; 
	    source.getChars(0, source.length(), buffer, 0); 
	 
	    try ( FileWriter f0 = new FileWriter("FileW1.txt"); 
	          FileWriter f1 = new FileWriter("FileW2.txt"); 
	          FileWriter f2 = new FileWriter("FileW3.txt") ) { 
	      // write to first file
	      for (int i=0; i < buffer.length; i += 2) { 
	        f0.write(buffer[i]); 
	      } 
	 
	      // write to second file 
	      f1.write(buffer); 
	 
	      // write to third file 
	      f2.write(buffer,buffer.length-buffer.length/4,buffer.length/4); 
	 
	    } catch(IOException e) { 
	      System.out.println("An I/O Error Occured"); 
	    }
	    System.out.println();
	    
System.out.println("---FileR ������� ����� �����������!");
	    try (FileReader fo1 = new FileReader("FileW1.txt");
	    	FileReader fo2 = new FileReader("FileW2.txt");
	   		FileReader fo3 = new FileReader("FileW3.txt")){
	    	
	    	int c;  
	    	System.out.print("FileW1:\n");
	    	while((c = fo1.read()) != -1 ){
	    		System.out.print((char)c);
	    	}
	    	
	    	System.out.print("\nFileW2:\n");
	    	while((c = fo2.read()) != -1 ){
	    		System.out.print((char)c);
	    	}
	    	
	    	System.out.print("\nFileW3:\n");
	    	while((c = fo3.read()) != -1 ){
	    		System.out.print((char)c);
	    	}
	    	
		} catch (IOException e) {
			System.out.println("��� �������!");
		}
		System.out.println();
		System.out.println();
		
System.out.println("---CharArrayR �������� ����� ������ char");
		String tmp = "abcdefghijklmnopqrstuvwxyz"; 
	    int length = tmp.length(); 
	    char c[] = new char[length]; 
	    tmp.getChars(0, length, c, 0);
	    
	    int i1; 
	 
	    try (CharArrayReader CharArrayR1 = new CharArrayReader(c);
	    		CharArrayReader CharArrayR2 = new CharArrayReader(c, 0, 5)) 
	    { 
	    	System.out.println("CharArrayR1 ������ ��� ������:"); 
	    	while((i1 = CharArrayR1.read()) != -1) { 
	    		System.out.print((char)i1); 
	    	} System.out.println(); 
	    
	    	System.out.println("CharArrayR2 ������ ���� ����� 5 ����:"); 
	    	while((i1 = CharArrayR2.read()) != -1) { 
	    		System.out.print((char)i1); 
	    	} System.out.println(); 
	    } catch(IOException e) { 
	    	System.out.println("I/O Error: " + e); 
	    } 
	    System.out.println();
	    
System.out.println("---CharArrayW �������� ������� ��� � �����");
		CharArrayWriter f = new CharArrayWriter(); 
		String s = "This should end up in the array"; 
		char buf[] = new char[s.length()]; 
		
		s.getChars(0, s.length(), buf, 0); 
		
		try { 
		  f.write(buf); 
		} catch(IOException e) { 
		  System.out.println("Error Writing to Buffer"); 
		  return; 
		} 
		
		System.out.println("����� � ������ �����"); 
		System.out.println(f.toString()); 
		System.out.println("� �����"); 
		
		char c1[] = f.toCharArray(); 
		for (int i=0; i<c1.length; i++) { 
		  System.out.print(c1[i]); 
		} 
		
	System.out.println("\n� FileWriter()"); 
		
		// Use try-with-resources to manage the file stream. 
		try ( FileWriter f2 = new FileWriter("CharArrayWDemo.txt") ) 
		{ 
		  f.writeTo(f2); 
		} catch(IOException e) { 
		  System.out.println("I/O Error: " + e); 
		} 
		
		int i2=0;
		try {
			FileInputStream qwe = new FileInputStream("CharArrayWDemo.txt");
			while((i2 =qwe.read()) != -1)
				System.out.print((char)i2);
			System.out.println("/n");
			qwe.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("��������"); 
		f.reset(); 
		
		for (int i=0; i<3; i++) f.write('X'); 
		
		System.out.println(f.toString());
		System.out.println();
		
System.out.println("---Console");
		String str; 
		Console con; 
		
		// Obtain a reference to the console. 
		con = System.console(); 
		// If no console available, exit. 
		if(con != null){ 
			// Read a string and then display it. 
			str = con.readLine("Enter a string: "); 
			con.printf("Here is your string: %s\n", str); 
		} else
			System.out.println("ͳ���� �� ������((");
		System.out.println();
		
System.out.println("����� ������� �����:\n" +
				"BufferedReader �� BufferedWriter\n" +
				"PushbackReader\n" +
				"PrintWriter");
	}
}
