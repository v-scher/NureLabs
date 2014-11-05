package ch1_23;
import java.io.*; 
import java.nio.*; 
import java.nio.channels.*; 
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NIODemo {
	public static void qmain(String args[]) { 
System.out.println("---SeekableByteChannel fChab = Files.newByteChannel(Paths.get(\"Шлях\"))\n" +
		"створили канал для зчитування байтів");
		int readed; 	    
	    // Next, obtain a channel to that file within a try-with-resources block. 
	    try ( SeekableByteChannel fChan = 
	    		Files.newByteChannel(Paths.get("NIODemo.txt")) ) 
	    { 
	 
	System.out.println("ByteBuffer mBuf = ByteBuffer.allocate(128) \n" +
	    	"створили буфер байтів");
			ByteBuffer mBuf = ByteBuffer.allocate(128); 
			
	System.out.println("fChab.read(ByteBuffer) \n" +
			"зчитали в нього вміст файлу через канал fChan\n" +
			"за доп. ByteBuffer.get() посимвольно виводимо текст з буферу");
			do { 
				readed = fChan.read(mBuf); 
	 
				if(readed != -1) {
					// Підготувати буфер для зчитування - встановити поч. позицію в нуль
					mBuf.rewind(); 
					for(int i=0; i < readed; i++) 
						System.out.print((char)mBuf.get()); 
				} 
			} while(readed != -1); 
	 
	      System.out.println(); 
	    } catch (IOException e) { 
	      System.out.println("I/O Error " + e); 
	    }
	    System.out.println();
	    
System.out.println("---FileChannel.map Призначаємо буфер об'єкту FileChannel\n" +
		"та зчитуємо файл");
		//Obtain a channel to a file within a try-with-resources block. 
		try ( FileChannel fChan = 
		     (FileChannel) Files.newByteChannel(Paths.get("NIODemo.txt")) ) 
		{ 
		  // Get the size of the file. 
		  long fSize = fChan.size(); 
		
		  // Now, map the file into a buffer. 
		  MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_ONLY, 0, fSize); 
		  
		  // Read and display bytes from buffer. 
		  for(int i=0; i < fSize; i++) 
		    System.out.print((char)mBuf.get()); 
		} catch(InvalidPathException e) { 
		  System.out.println("Path Error " + e); 
		} catch (IOException e) { 
		  System.out.println("I/O Error " + e); 
		}
		System.out.println();
		System.out.println();
		
System.out.println("---FileChannel.write(ByteBuffer) пишемо в файл з буфера - \nспочатку" +
		" заповнюємо буфер, потім ВРУЧНУ пишемо з нього в файл"); 
		//Obtain a channel to a file within a try-with-resources block.  
		try ( FileChannel fChan = (FileChannel)  
		        Files.newByteChannel(Paths.get("NIODemo1.txt"),  
		                             StandardOpenOption.WRITE,  
		                             StandardOpenOption.CREATE);
				FileInputStream fin = new FileInputStream("NIODemo1.txt") ) 
		{  
			// Create a buffer.  
			ByteBuffer mBuf = ByteBuffer.allocate(26);  
		
			// Write some bytes to the buffer. 
			for(int i=0; i<26; i++)
				mBuf.put((byte)('A' + i));
			  
			// команда put() щоразу пересуває вказівник буфера
			// тому перед записом його треба повернути в початок:
			mBuf.rewind(); 
			 
			// Write the buffer to the output file. 
			// запис вмісту буфера починається з вказівника і пересуває його
			fChan.write(mBuf); 
			 
			// Rewind the buffer so that it can be written to again. 
			mBuf.rewind(); 
		
	System.out.println("Вміст файлу: ");
			int i = 0;
			while((i = fin.read()) != -1)
				System.out.print((char) i);
		} catch(InvalidPathException e) {  
			System.out.println("Path Error " + e);  
		} catch (IOException e) {  
			System.out.println("I/O Error: " + e);  
		}
		System.out.println("\n"); 
		
System.out.println("---FileChannel.map(MappedByteBuffer) запис в файл за доп. \nасоціації " +
					"буфера та каналу - канал одразу виконує запис з буфера (неявно)"); 
		//Obtain a channel to a file within a try-with-resources block. 
		try ( FileChannel fChan = 
				(FileChannel) Files.newByteChannel(Paths.get("NIODemo2.txt"), 
		               StandardOpenOption.WRITE, 
		               StandardOpenOption.READ, 
		               StandardOpenOption.CREATE);
				FileInputStream fin = new FileInputStream("NIODemo2.txt")) 
		{ 
		
			// Зіставити канал з буфером
			// другий параметр - початок зіставлення
			// третій - кількість символів для асоціації
			MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_WRITE, 0, 26); 
		
			// Write some bytes to the buffer. 
			for(int i=0; i<26; i++) 
				mBuf.put((byte)('A' + i)); 
		
	System.out.println("Вміст файлу: ");
			int i = 0;
			while((i = fin.read()) != -1)
				System.out.print((char) i);
		} catch(InvalidPathException e) { 
			System.out.println("Path Error " + e); 
		} catch (IOException e) { 
			System.out.println("I/O Error " + e); 
		} 
		System.out.println("\n"); 
			
System.out.println("---Files.copy(Path source, Path target, StandardCopyOption.REPLACE_EXISTING)\n " +
				"копіювання файлів"); 
		    try { 
		      Path source = Paths.get("NIODemo.txt"); 
		      Path target = Paths.get("E:\\Dropbox\\1 Java\\JFirstJavaPr\\NIOCopyDemo.txt"); 
		      
		      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING); 
		 
		    } catch(InvalidPathException e) { 
		    	System.out.println("Path Error " + e); 
		    } catch (IOException e) { 
		    	System.out.println("I/O Error " + e); 
		    }
		System.out.println(); 
		
System.out.println("---Потокове читання файлів за доп. NIO"); 
		int i; 
		
		// Open the file and obtain a stream linked to it. 
		/*System.out.println("Введіть шлях до файлу: ");
		try ( InputStream fin = 
				Files.newInputStream(
					Paths.get(
							new BufferedReader(
								new InputStreamReader(System.in)
							).readLine()
					)
				) 
			) {
			*/
		
		// try ( OutputStream fout = Files.newOutputStream(Paths.get("test.txt")) ) {
		// також його можна зробити буферезованим
		// try ( OutputStream fout = new BufferedOutputStream( Files.newOutputStream(Paths.get("test.txt"))) ) {
		try ( InputStream fin = Files.newInputStream(Paths.get("NIODemo.txt"))) { 

			do { 
				i = fin.read(); 
				if(i != -1) System.out.print((char) i); 
			} while(i != -1); 
		
		} catch(InvalidPathException e) { 
			System.out.println("Path Error " + e); 
		} catch(IOException e) { 
			System.out.println("I/O Error "  + e); 
		} 
		System.out.println("\n"); 
		
System.out.println("---фільтроване виведення файлів - лише теки"); 
		String dirname = "E:\\Dropbox\\1 Java\\JFirstJavaPr"; 
		
		DirectoryStream.Filter<Path> how = new DirectoryStream.Filter<Path>() {  
			public boolean accept(Path filename) throws IOException {  
				if(Files.isDirectory(filename)) return true;  
				return false;
			}  
		}; 
		    
		// без фільтрування:
		//try ( DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirname)) ) {
		try ( DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirname), how) ) {
			System.out.println("Directory of " + dirname); 
			for(Path entry : dirstrm) { 
				BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class); 
		
				if(attribs.isDirectory()) 
					System.out.print("<DIR> "); 
				else 
					System.out.print("      "); 
		
				System.out.println(entry.getName(3)); 
			}
		} catch (Exception e) { 
			System.out.println("Error: " + e); 
		} 
		System.out.println(); 
		
System.out.println("---walkFileTree() - огляд дерева тек"); 
	    //String dirname = "\\MyDir";  
		dirname = "E:\\Dropbox\\1 Java\\ZChumaJAVA"; 
		
		System.out.println("Дерево тек для " + dirname + ":\n"); 
			 
		try { 
			Files.walkFileTree(Paths.get(dirname), 
								new SimpleFileVisitor<Path>(){ 
									public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) throws IOException { 
										System.out.println(path); 
										return FileVisitResult.CONTINUE; 
									} 
								}); 
		} catch (IOException exc) { 
			System.out.println("I/O Error"); 
		} 
		System.out.println(); 
		
System.out.println("---Канальне читання файлів до JDK 7 - ВРУЧНУ витягаємо з каналу в буфер, потім виводимо буфер"); 	
		FileInputStream fIn = null; 
	    FileChannel fChan = null; 
	    ByteBuffer mBuf; 
	    int count; 
	 
	    try { 
	    	// First, open a file for input. 
	    	fIn = new FileInputStream("NIODemo.txt"); 
	 
	    	// Next, obtain a channel to that file. 
	    	fChan = fIn.getChannel(); 
	 
	    	// Allocate a buffer.  
	    	mBuf = ByteBuffer.allocate(128);  
		 
	    	do { 
	    		count = fChan.read(mBuf); 
	    		if(count != -1) { 
	    			mBuf.rewind(); 
	    			for(int i1=0; i1 < count; i1++) 
	    				System.out.print((char)mBuf.get()); 
	    		} 
	    	} while(count != -1); 
	 
	    	System.out.println(); 
	 
	    } catch (IOException e) { 
	    	System.out.println("I/O Error " + e); 
	    } finally { 
	    	try { 
	    		if(fChan != null) fChan.close(); // close channel 
	    	} catch(IOException e) { 
	    		System.out.println("Error Closing Channel."); 
	    	} 
	    	try { 
	    		if(fIn != null) fIn.close(); // close file 
	    	} catch(IOException e) { 
	    		System.out.println("Error Closing File."); 
	    	} 
	    } 
	    System.out.println(); 
	    
System.out.println("---Канальне читання файлів до JDK 7 - асоціація з буфером"); 	
		//FileInputStream fIn = null; 
	    //FileChannel fChan = null; 
	    //ByteBuffer mBuf; 
	    //int count; 
	 
	    try { 
	    	// First, open a file for input. 
	    	fIn = new FileInputStream("NIODemo.txt"); 
	 
	    	// Next, obtain a channel to that file. 
	    	fChan = fIn.getChannel(); 
	 
	    	long size = fChan.size();

	    	mBuf = fChan.map(FileChannel.MapMode.READ_ONLY, 0, size);
		 
	    	for(int i1 = 0; i1<size; i1++)
	    		System.out.print((char)mBuf.get());
	    	System.out.println(); 
	 
	    } catch (IOException e) { 
	    	System.out.println("I/O Error " + e); 
	    } finally { 
	    	try { 
	    		if(fChan != null) fChan.close(); // close channel 
	    	} catch(IOException e) { 
	    		System.out.println("Error Closing Channel."); 
	    	} 
	    	try { 
	    		if(fIn != null) fIn.close(); // close file 
	    	} catch(IOException e) { 
	    		System.out.println("Error Closing File."); 
	    	} 
	    }
	    System.out.println();
	}
}
