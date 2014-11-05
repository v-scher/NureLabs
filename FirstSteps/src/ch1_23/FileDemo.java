package ch1_23;
// Demonstrate File. 
import java.io.*; 
 
//цей інтерфейс містить один метод bool accept()
//який визначає чи підходить файл
class OnlyBegin implements FilenameFilter { 
	String begin; 
		 
	public OnlyBegin(String qwe) { 
		this.begin = qwe; 
	} 
		 
	public boolean accept(File dir, String name) { 
		return name.startsWith(begin); 
	}
}

class FileDemo {

	static void p(String s) { 
		System.out.println(s); 
	} 

	public static void main(String args[]) { 
p("Використання інформаційних методів File");
		File f1 = new File("COPYRIGHT"); 
	    p("File Name: " + f1.getName()); 
	    p("Path: " + f1.getPath()); 
	    p("Abs Path: " + f1.getAbsolutePath()); 
	    p("Parent: " + f1.getParent()); 
	    p(f1.exists() ? "exists" : "does not exist"); 
	    p(f1.canWrite() ? "is writeable" : "is not writeable"); 
	    p(f1.canRead() ? "is readable" : "is not readable"); 
	    p("is " + (f1.isDirectory() ? "" : "not" + " a directory")); 
	    p(f1.isFile() ? "is normal file" : "might be a named pipe"); 
	    p(f1.isAbsolute() ? "is absolute" : "is not absolute"); 
	    p("File last modified: " + f1.lastModified()); 
	    p("File size: " + f1.length() + " Bytes"); 
	    p("");

p("Використання навігації по каталогах: list(),isDirectory(),");
		String dirname = "E:\\Dropbox\\1 Java\\JFirstJavaPr\\src"; 
		//String dirname = "src";
		File f2 = new File(dirname); 
		
		if (f2.isDirectory()) { 
			p("Directory of " + dirname); 
			String s[] = f2.list(); 
			//File[] fs = f2.listFiles(); //повертає масив файлів
			
			for (int i=0; i < s.length; i++) { 
				File f = new File(dirname + "/" + s[i]); 
				if (f.isDirectory()) { 
					p(s[i] + " is a directory"); 
				} else { 
					p(s[i] + " is a file"); 
				} 
			} 
		} else { 
			p(dirname + " is not a directory"); 
		} 
		p("");
		
p("Використання ІФ FIlenameFilter для фільтрування вмісту з list()");
		dirname = "src"; 
		f1 = new File(dirname); 
	p("вивести лише файли з Threas на початку");
		//FilenameFilter only = new OnlyBegin("Thread"); 
		String s[] = f1.list(new OnlyBegin("Thread"));
		for (int i=0; i < s.length; i++) { 
			p(s[i]); 
		} 
		p("");
		
p("Також можна створювати каталоги методами класу File:" +
		"\nboolean mkdir() - банально створює каталог, в разі невдачі - false" +
		"\nта boolean mkdirs() - створює каталог і всіх його батьків, " +
		"\nякщо повного шляху ще не існує");
	} 
}