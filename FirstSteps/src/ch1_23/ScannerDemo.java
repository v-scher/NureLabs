package ch1_23;
import java.util.*;
import java.io.*; 

public class ScannerDemo {
	public static void main(String args[]) throws IOException{
		int i; 
	    double d; 
	    boolean b; 
	    String str1; 
	    
System.out.println("Читання значень різних типів");
	    FileWriter fout1 = new FileWriter("testScanner1.txt"); 
	    fout1.write("Тестування Scanner 10 12,2 12.2 один true два false"); 
	    fout1.close(); 
	 
	    FileReader fin1 = new FileReader("testScanner1.txt"); 
	    Scanner src1 = new Scanner(fin1);
	    while(src1.hasNext()) { 
	      if(src1.hasNextInt()) { 
	        i = src1.nextInt(); 
	        System.out.println("int: " + i); 
	      } 
	      else if(src1.hasNextDouble()) { 
	        d = src1.nextDouble(); 
	        System.out.println("double: " + d); 
	      } 
	      else if(src1.hasNextBoolean()) { 
	        b = src1.nextBoolean(); 
	        System.out.println("boolean: " + b); 
	      } 
	      else { 
	        str1 = src1.next(); 
	        System.out.println("String: " + str1); 
	      } 
	    } 
	    src1.close(); 
	    System.out.println();
	    
System.out.println("Читання з клавіатури");
		//стандартно потік на вхід - клавіатура
		Scanner conin = new Scanner(System.in); 
		 
	    int count = 0; 
	    double sum = 0.0; 
	 
	    System.out.println("Введіть числа для обчислення середнього (\"готово\" для завершення)."); 
	 
	    // Read and sum numbers. 
	    while(conin.hasNext()) { 
	    	if(conin.hasNextDouble()) { 
	    		sum += conin.nextDouble(); 
	    		count++; 
	    	} 
	    	else { 
	    		String str = conin.next();  
	    		if(str.equals("готово")) break; 
	    		else { 
	    			System.out.println("Введіть число або \"готово\"!"); 
	    		}
	    	} 
	    } 
	    conin.close(); 
	    System.out.println("Середнє значення: " + sum / count); 
	    System.out.println();
	    
System.out.println("Читання з файлу");
		count = 0; 
		sum = 0.0; 
		
		// Write output to a file. 
		FileWriter fout = new FileWriter("testScanner2.txt"); 
		fout.write("2 3,4 5 6 7,4 9,1 10,5 done"); 
		fout.close(); 
		
		FileReader fin = new FileReader("testScanner2.txt"); 
		//fin закривається автоматично з src
		try(Scanner src = new Scanner(fin)){ 
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
		}
		System.out.println("Середнє значення: " + sum / count); 
		System.out.println();
		
System.out.println("Встановити роздільники");
		count = 0; 
	    sum = 0.0; 
	 
	    // Write output to a file. 
	    FileWriter fout2 = new FileWriter("testScanner3.txt"); 
	    
	    //за укр стандартами double розділяються КОМОЮ
	    //якщо ми хочемо зчитати double з дробовими КРАПКАМИ,
	    //треба змінити локалізацію
	System.out.println("Поточна локалізація: " + java.util.Locale.getDefault());
	//можна просто Locale. тому що бібліотека вже підключена
	java.util.Locale.setDefault(new Locale("en", "EN"));
	System.out.println("Локалізація змінена на: " + java.util.Locale.getDefault());
	    
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
	    
System.out.println("Власний шаблон");
	    String instr = "Name: Tom Age: 28 ID: 77"; 
	    
	    Scanner conin1 = new Scanner(instr); 
	 
	    // Find and display age. 
	    conin1.findInLine("Age:"); // find Age 
	 
	    if(conin1.hasNext()) 
	      System.out.println(conin1.next()); 
	    else 
	      System.out.println("Error!"); 

	    conin1.close();
	}
}
