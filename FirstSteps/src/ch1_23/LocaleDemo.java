package ch1_23;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;


public class LocaleDemo {
	public static void main(String args[]){
System.out.println("---Locale - ������� ��������� ����������");
		int count = 0; 
	    double sum = 0.0; 
	 
	    // Write output to a file. 
	    try(FileWriter fout2 = new FileWriter("testScanner3.txt")){
	    
	    //�� ��� ����������� double ����������� �����
	    //���� �� ������ ������� double � ��������� ��������,
	    //����� ������ ����������
	System.out.println("������� ����������: " + java.util.Locale.getDefault());
	//����� ������ Locale. ���� �� �������� ��� ���������
	java.util.Locale.setDefault(new Locale("en", "EN"));
	System.out.println("���������� ������ ��: " + java.util.Locale.getDefault());
	    
	    // Now, store values in comma-separated list. 
	    fout2.write("2, 3.4,    5,6, 7.4, 9.1, 10.5, done"); 
	    fout2.close(); 
	 
	    FileReader fin2 = new FileReader("testScanner3.txt"); 
	 
	    Scanner src = new Scanner(fin2);
	    // Set delimiters to space and comma. 
	    src.useDelimiter(", *"); 
	 
	    // Read and sum numbers. 
	    while(src.hasNext()) { 
	      if(src.hasNextDouble()) { 
	        sum += src.nextDouble(); 
	        count++; 
	      } 
	      else { 
	        String str = src.next();  
	        if(str.equals("done")) break; 
	        else { 
	          System.out.println("File format error."); 
	          return; 
	        } 
	      } 
	    } 
	 
	    src.close(); 
	    System.out.println("Average is " + sum / count); 
	    System.out.println();
	    } catch (Exception e) {
			// TODO: handle exception
		}
	    
System.out.println("---PrintStream ������������ System.out.printf()");
		System.out.printf(new Locale ("uk","UA"), "%f � ����� Double ����������� ����� %f \n", 3.4,3.6);
		System.out.printf(new Locale ("en","UA"), "%f � ������ �������� ����� - ������� %f \n", 3.4,3.6);
		System.out.println();
	}
}
