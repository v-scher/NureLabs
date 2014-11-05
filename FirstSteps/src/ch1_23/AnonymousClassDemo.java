package ch1_23;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class AnonymousClassDemo {
	public static void qmain(String args[]){
System.out.println("---фільтроване виведення файлів - лише теки"); 
		String dirname = "E:\\Dropbox\\1 Java\\JFirstJavaPr"; 
		
		DirectoryStream.Filter<Path> how = new DirectoryStream.Filter<Path>() {  
			public boolean accept(Path filename) throws IOException {  
				if(Files.isDirectory(filename)) return true;  
				return false;
			}  
		}; 
		    
		// Obtain and manage a directory stream within an ARM Block. 
		try ( DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirname), how) ) 
		{ 
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
			System.out.println("Error " + e); 
		} 
		System.out.println(); 
		
System.out.println("---walkFileTree() - огляд дерева тек"); 
	    //String dirname = MyDir;  
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
			System.out.println("IO Error"); 
		} 
		System.out.println(); 
		
System.out.println("---потоки в анонімних класах"); 
		Thread qwe = new Thread(new Runnable() {
            public void run() {
                    try { 
                    	for(int i = 0; i < 8; i++){
                    		System.out.println("номер " + i);
                    		Thread.sleep(1000); 
                    	}
                    } catch (InterruptedException e) { }
            }
        });
		qwe.start();
		
		Thread qwe1 = new Thread(new Runnable() {
            public void run() {
                    try { 
                    	for(int i = 0; i < 6; i++){
                    		System.out.println("число " + i);
                    		Thread.sleep(1000);
                    	}
                    } catch (InterruptedException e) { }
            }
        });
		qwe1.start();
		System.out.println(); 
	}
}
