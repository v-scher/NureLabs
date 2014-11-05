package ch1_23;
import java.io.*;
import java.util.*;

public class IOStreams {
	public static void qmain(String args[]) {   
		int size;  
		  
	    // Use try-with-resources to close the stream. 
	    try (FileInputStream f =  new FileInputStream("src\\IOStreams.java")){ 
	    	System.out.println("Всього доступно байтів: " + (size = f.available()));  
	  
System.out.println("---FileIS Посимвольне зчитування (char)FileInputStream.read()");
    		//зчитування перших трьох РЯДКІВ
	    	char qq;
	    	int newLine = 0;
	    	while(newLine != 3){
	    		if((qq = (char)f.read()) == '\n'){
	    			System.out.print("\\n");
	    			newLine++;
	    			continue;
	    		}
	    		if(qq == '\r'){
	    			System.out.print("\\r");
	    			continue;
	    		}
	    		System.out.print(qq);
	    	}
	    	System.out.println("\nStill Available: " + f.available()); 
	    	System.out.println();
	    	
System.out.println("---FileIS Посимвольне зчитування (char)FileInputStream.read()");
	    	int n = size/40;  
	    	System.out.println("First " + n +  
                  " bytes of the file one read() at a time");  
			for (int i=0; i < n; i++) {  
				System.out.print((char) f.read());  
			}  
			System.out.println("\nStill Available: " + f.available());  
			System.out.println();
			
System.out.println("---FileIS Зчитування в масив байтів FileInputStream.read(byte[] b)"); 
			byte b[] = new byte[n];  
			if (f.read(b) != n) {  
			 System.err.println("couldn't read " + n + " bytes.");  
			}  
			System.out.println(new String(b, 0, n));  
			System.out.println("\nStill Available: " + (size = f.available())); 
			System.out.println();
			
System.out.println("---FileIS Пропускаємо половину решти байтів " +
					"за доп. FileInputStrem.skip(int size/2)");  
			f.skip(size/2);  
			System.out.println("Still Available: " + f.available());  
			System.out.println();
			
System.out.println("---FileIS Зчитування " +n/2+ " байтів в кінець масиву " +
					"\nза доп. FileInputStream.read(byte[] b, int begin, int howMany)");  
			if (f.read(b, n/2, n/2) != n/2) {  
				System.err.println("couldn't read " + n/2 + " bytes.");  
			}  
			System.out.println(new String(b, 0, b.length));  
			System.out.println("\nStill Available: " + f.available());
		} catch(IOException e) {  
			System.out.println("I/O Error: " + e);  
		}
	    System.out.println();
	    
System.out.println("---FileOS використання масиву байтів " +
					"для запису в файли по одному біту");
		String source = "Now is the time for all good men\n" 
	      + " to come to the aid of their country\n" 
	      + " and pay their due taxes."; 
	    byte buf[] = source.getBytes(); 
	 
	    try (FileOutputStream f0 = new FileOutputStream("FileOS1.txt"); 
	         FileOutputStream f1 = new FileOutputStream("FileOS2.txt"); 
	         FileOutputStream f2 = new FileOutputStream("FileOS3.txt") ) 
	    { 
	 
	    	// write to first file 
	    	for (int i=0; i < buf.length; i += 2) f0.write(buf[i]);      
	 
	    	// write to second file 
	    	f1.write(buf); 
	 
	    	// write to third file 
	    	f2.write(buf, buf.length-buf.length/4, buf.length/4); 
	    } catch(IOException e) { 
	    	System.out.println("An I/O Error Occured"); 
	    } 
	    
	    System.out.println("Поглянемо на результат");
	    try (FileInputStream fo1 = new FileInputStream("FileOS1.txt");
	    	FileInputStream fo2 = new FileInputStream("FileOS2.txt");
	   		FileInputStream fo3 = new FileInputStream("FileOS3.txt")){
	    	
	    	System.out.print("\nFile1: ");
	    	while(fo1.available() != 0 ){
	    		System.out.print((char)fo1.read());
	    	}
	    	System.out.print("\nFile2: ");
	    	while(fo2.available() != 0 ){
	    		System.out.print((char)fo2.read());
	    	}
	    	System.out.print("\nFile3: ");
	    	while(fo3.available() != 0 ){
	    		System.out.print((char)fo3.read());
	    	}
	    	
		} catch (IOException e) {
			System.out.println("Все пропало!");
		}
		System.out.println();
		System.out.println();
		
System.out.println("---ByteArrayIS Демонстрація застосування - зчитування англ. абетки");    
	    //tmp = "abc"; 
	    //b = tmp.getBytes(); 
	    ByteArrayInputStream in = new ByteArrayInputStream("abc".getBytes()); 
	
	    //байтові потоки можна не "закривати"
	    for (int i=0; i<2; i++) { 
	    	int c; 
	    	while ((c = in.read()) != -1) { 
	    		if (i == 0) { 
	    			System.out.print((char) c); 
	    		} else { 
	    			System.out.print(Character.toUpperCase((char) c)); 
	    		} 
	    	} 
	    	System.out.println(); 
	    	in.reset(); 
	    } 
		System.out.println();
		
System.out.println("---ByteArrayOS ");
		ByteArrayOutputStream f = new ByteArrayOutputStream(); 
		String s = "This should end up in the array"; 
		byte buf1[] = s.getBytes(); 
		
		try { 
			//запис в потік
			f.write(buf1); 
		} catch(IOException e) { 
			System.out.println("Error Writing to Buffer"); 
			return; 
		} 
		
		System.out.println("Повернути потік у вигляді рядка: "); 
		System.out.println(f.toString()); 
		System.out.println("у вигляді масива: "); 
		byte b[] = f.toByteArray(); 
		for (int i=0; i<b.length; i++) System.out.print((char) b[i]); 
		
		System.out.println("\nу вигляді OutputStream()"); 
		
		// Use try-with-resources to manage the file stream. 
		try ( FileOutputStream f2 = new FileOutputStream("ByteArrayOS.txt") ) 
		{ 
			//пишемо з байтового потоку виведення в файловий потік виведення
			f.writeTo(f2); 
		} catch(IOException e) { 
			System.out.println("I/O Error: " + e); 
			return; 
		} 
		
		System.out.println("Скидаємо \"каретку\" потоку байтів"); 
		f.reset(); 
		
		//записуємо в масив байтів "Ікси"
		for (int i=0; i<3; i++) f.write('X'); 
		
		System.out.println("Записані в потік байтів Ікси після скидання \"каретки\" " + f.toString()); 
		System.out.println();
		
System.out.println("---BufferedIS дозволяє створити \"оболонку\" " +
		"навколо InputStream, що може підвищити \nпродуктивність за рахунок" +
		" відправки байтів купками - бажано кратними розміру сторінки (8192)");
		s = "This is a &copy; copyright symbol " + 
	      "but this is &copy not.\n"; 
	    byte buf2[] = s.getBytes(); 
	 
	    ByteArrayInputStream in1 = new ByteArrayInputStream(buf2); 
	    int c; 
	    boolean marked = false; 
	 
	    // Перетворюємо байтовий потік введеня в Буферизований потік введення
	    try ( BufferedInputStream f1 = new BufferedInputStream(in1) ) { 
	    	while ((c = f1.read()) != -1) { 
	    		switch(c) { 
	    		case '&': 
	    			//якщо цей символ досі не був знайдений, зробити помітку:
	    			if (!marked) { 
	    				//зберігає відмітку, доки не буде зчитано 32 байти
	    				//цієї кількості достатньо для посилання на правовласника
	    				f1.mark(32); 
	    				//почали зчитування посилання правовласника
	    				marked = true; 
	    			} else { 
	    				//якщо цей символ трапився вдруге - скасувати відмітку
	    				marked = false; 
	    			} 
	    			break; 
	    		case ';': 
	    			if (marked) { 
	    				//якщо початок (&) вже був знайдений - скасувати відмітку
	    				//і вивести знак копії
	    				marked = false; 
	    				System.out.print("(c)"); 
	    			} else 
	    				//якщо початок імені правовласника ще не знайдений,
	    				//вивести символ ";"
	    				System.out.print((char) c); 
	    			break; 
	    		case ' ': 
	    			//якщо після & знайдено пробіл, скасувати відмітку
	    			if (marked) { 
	    				marked = false; 
	    				//скинути каретку на початок імені і вивести символ &
	    				f.reset(); 
	    				System.out.print("&"); 
	    			} else 
	    				//якщо пробіл знайдений, а початок імені правовласника ще ні
	    				//вивести символ:
	    				System.out.print((char) c); 
	    			break; 
	    		default: 
	    			if (!marked) 
	    				System.out.print((char) c); 
	    			break; 
	    		} 
	    	} 
	    } catch(IOException e) { 
	    	System.out.println("I/O Error: " + e); 
	    } 
		System.out.println();
		
System.out.println("---PushbackIS дозволяє \"зазирнути\" в буфер введення");
		//s = "if (a == 4) a = 0;\n"; 
		//buf1 = s.getBytes(); 
		in1 = new ByteArrayInputStream("if (a == 4) a = 0;\n".getBytes()); 
		int qwe; 

		try ( PushbackInputStream f1 = new PushbackInputStream(in1) ) 
		{ 
			while ((qwe = f1.read()) != -1) { 
				switch(qwe) { 
				case '=': 
					//якщо цей і наступний символ == '='
					if ((qwe = f1.read()) == '=') 
						//вивести "дорівнює"
						System.out.print(".eq."); 
					else {
						//якщо цей символ == '=', а наступний - ні
						//вивести знак привласнення
						System.out.print("<-"); 
						//повернути останній символ в буфер
						f1.unread(qwe); 
					} 
					break; 
				default: 
					System.out.print((char) qwe); 
					break; 
				} 
			} 
		} catch(IOException e) { 
			System.out.println("I/O Error: " + e); 
		} 
		System.out.println();
		
System.out.println("---SequenceIS - дозволяє зібрати ПОСЛІДОВНО кілька IS в один об'єкт");
		class ISEnum implements Enumeration<FileInputStream>{
			private Enumeration<String> files;
			
			public ISEnum(Vector<String> files){
				this.files = files.elements();
			}

			@Override
			public boolean hasMoreElements() {
				return files.hasMoreElements();
			}

			@Override
			public FileInputStream nextElement() {
				try {
					return new FileInputStream(files.nextElement().toString());
				} catch (FileNotFoundException e) {
					return null;
				}
			}
		}
		
		//int c; 
	    Vector<String> files = new Vector<String>(); 
	 
	    files.addElement("SequenceIS1.txt"); 
	    files.addElement("SequenceIS2.txt"); 
	    files.addElement("SequenceIS3.txt"); 
	    
	    try (InputStream input = new SequenceInputStream(new ISEnum(files))){ 
	    	while ((c = input.read()) != -1) 
	    		System.out.print((char) c); 
	    } catch(IOException e) { 
	    	System.out.println("I/O or opening file error: " + e); 
	    }
		System.out.println();
		System.out.println();
		
System.out.println("---PrintStream дозволяє вказати ФОРМАТ, КОДУВАННЯ, ЛОКАЛІЗАЦІЮ\n" +
					"використання System.out.printf() " +
					"- Потік System.out має клас PrintStream");
		System.out.printf(//new Locale ("uk","UA"), 
							"%f В Україні Double розділяються КОМОЮ %f \n", 
							3.4, 3.6);
		System.out.printf(new Locale ("en","UA"), 
							"%f В країнах колишньої імперії - КРАПКОЮ %f \n", 
							3.4,3.6);
		System.out.println();
		
		
System.out.println("---PrintStream використання форматування");

		System.out.printf("Формати цілих чисел: "); 
		System.out.printf("%d %(d %+d %05d\n", 3, -3, 3, 3); 
		
		System.out.println(); 
		System.out.printf("Формат дробу за замовчуванням %%f: \n" +
				"%f\n", 1234567.123); 
		System.out.printf("Дріб з роздільниками %%,f: \n" +
				"%,f\n", 1234567.123); 
		System.out.printf("Від'ємний дріб з роздільниками %%,f: \n" +
				"%,f\n", -1234567.123); 
		System.out.printf("Від'ємний дріб з параметрами %%,(f: \n" +
				"%,(f\n", -1234567.123); 
		
		System.out.println(); 
		
		System.out.printf("рядок з додатніх та від'ємних значень:\n"); 
		System.out.printf("% ,.2f\n% ,.2f\n", 
		                1234567.123, -1234567.123);
		System.out.println();
		
System.out.println("---DataOS ");
		//First, write the data.  
		try ( DataOutputStream dout = 
				new DataOutputStream(new FileOutputStream("DataOS.dat")) ) 
		{ 
		  dout.writeDouble(98.6); 
		  dout.writeInt(1000); 
		  dout.writeBoolean(true); 
		} catch(IOException e) { 
		  System.out.println("Біда: " + e); 
		}  
		
		// Now, read the data back. 
		try ( DataInputStream din =  
		        new DataInputStream(new FileInputStream("DataOS.dat")) ) 
		{ 
		
		  double d = din.readDouble(); 
		  int i = din.readInt(); 
		  boolean bool = din.readBoolean(); 
		
		  System.out.println("Зчитані значення різних типів: " + 
		                      d + " " + i + " " + bool); 
		} catch(IOException e) { 
		  System.out.println("Все пропало! " + e); 
		} 
		System.out.println();
	}  
}
