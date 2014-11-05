package ch1_23;
import java.io.*;
import java.util.StringTokenizer;

class StringDemo{
	public static void main(String args[]) throws IOException {
//КОНСТРУКТОРИ String:
		char c[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
		
		String s1 = new String(c);
		System.out.println("передати масив char: " + s1);

		String s2 = new String(s1); 
		System.out.println("передати інший String: " + s2);
		
		s2 = new String(c,2,3);
		System.out.println("діапазон членів масиву char 3 штуки починаючи з 2: " + s2);
		System.out.println();
		
		byte ascii[] = {65, 66, 67, 68, 69, 70 };

	    s1 = new String(ascii);
	    System.out.println("передати масив byte: " + s1);

	    s1 = new String(ascii, 2, 3);
	    System.out.println("діапазон членів масиву byte 3 штуки починаючи з 2: " + s1);
	    System.out.println();
	    
	    String longStr = "This could have been " +
	    "a very long line. But string concatenation " +
	    "prevents this.";
	    System.out.println(longStr + "\nМоя довжина: " + longStr.length());
	    System.out.println();
	    
	    StringBuffer sbuf = new StringBuffer("StringBuffer text");
	    StringBuilder sbuild = new StringBuilder("StringBuilder text");
	    s1 = new String(sbuf + " " + sbuild);
	    System.out.println("передати об'єкти StringBuffer та StringBuilder: " + s1);
	    System.out.println();
	    
	    //клас Box тепер містить метод toString:
		//public String toString() {
		//	return "Розмір: " + w + " на " + 
		//			d + " на " + h + ".";
		//}
		
	    Box b = new Box(10, 12, 14);
	    String s = "конкатенація об'єкту Box: " + b;
	    System.out.println(s);
	    System.out.println("перетворення Box в String: " + b);
	    System.out.println();
	    
	    char ch;
	    ch = "abc".charAt(1);
System.out.println("використання методу \"abc\".charAt(1): " + ch);
	    System.out.println();
	    
s = "Це демонстрація використання методу s.getChars(start,end,buf,0)";
	    int start = 16;
	    char buf[] = new char[s.length()-start];
	    s.getChars(start, s.length(), buf, 0);
	    //тут buf - масив char[], а отже конкатенація незастосовна
	    System.out.println(buf); 
	    System.out.println();
	    
s = "використання buf = s.toCharArray()";
	    buf = new char[s.length()];
	    buf = s.toCharArray();
	    System.out.println(buf);
	    System.out.println();
	    
	    s1 = "Hello";
	    s2 = new String(s1);
	    String s3 = "HELLO";
	    System.out.println(s1 +" equals "+ s2 + " -> " + s1.equals(s2));
	    System.out.println(s1 +" equals "+ s3 + " -> " + s1.equals(s3));
	    System.out.println(s1 +" equalsIgnoreCase "+ s3 + " -> " + s1.equalsIgnoreCase(s3));
	    //оператор == порівнює ОБ'ЄКТИ, а отже порівняння власне РЯДКІВ не відбувається
	    System.out.println(s1 + " == " + s2 + " -> " + (s1 == s2));
	    System.out.println();
	    
	    s = "бвг бвг бвг";
	    s1 = "абвгд";
	    if(s.regionMatches(0, s1, 1, 3))
	    	System.out.println("\"бвг бвг бвг\" містить 3 символи від 1 \"абвгд\" - бвг");
	    if(!s.regionMatches(0, s1, 1, 4))
	    	System.out.println("\"бвг бвг бвг\" не містить 4 символи від 1 \"абвгд\" - бвгд");
	    if(!s.regionMatches(0, s1, 0, 3))
	    	System.out.println("\"бвг бвг бвг\" не містить 3 символи від 0 \"абвгд\" - абв");
	    System.out.println();
	    
	    s = "Foobar";
	    if(s.startsWith("Foo"))
	    	System.out.println("\"Foobar\".startsWith(\"Foo\")");
	    if(s.endsWith("bar"))
	    	System.out.println("\"Foobar\".endsWith(\"bar\")");
	    System.out.println();
	    
System.out.println("використання compareTo");
	    //Велика літера має менший номер в таблиці символів, тому слово Now буде першим
	    String arr[] = {"Now", "is", "the", "time", "for", "all", "good", "men",
	    					"to", "come", "to", "the", "aid", "of", "their", "country"};
	    for(int j = 0; j < arr.length; j++) {
	    	for(int i = j + 1; i < arr.length; i++) {
	    		//якщо рядок, що викликає "МЕНШИЙ", за рядок, що переданий,
	    		//то результат порівняння МЕНШИЙ від нуля
	    		if(arr[i].compareTo(arr[j]) < 0) {
	    			String t = arr[j];
	    			arr[j] = arr[i];
	    			arr[i] = t;
	    		}
	    	}
	    	System.out.println(arr[j]);
	    }
	    System.out.println();
	    
	    s = "Now is the time for all good men " +
	         "to come to the aid of their country.";
	    System.out.println(s);
	    System.out.println("indexOf(t) = " + s.indexOf('t'));
	    System.out.println("lastIndexOf(t) = " + s.lastIndexOf('t'));
	    System.out.println("indexOf(the) = " + s.indexOf("the"));
	    System.out.println("lastIndexOf(the) = " + s.lastIndexOf("the"));
	    //тут задано номер символа, з якого починається пошук
	    System.out.println("indexOf(t, 10) = " + s.indexOf('t', 10));
	    System.out.println("lastIndexOf(t, 60) = " + s.lastIndexOf('t', 60));
	    System.out.println("indexOf(the, 10) = " + s.indexOf("the", 10));
	    System.out.println("lastIndexOf(the, 60) = " + s.lastIndexOf("the", 60));
	    System.out.println();
	    
System.out.println("використання substr(is): ");
	    String org = "This is a test. This is, too.";
	    String search = "is";
	    String sub = "was";
	    String result = "";
	    int i;
	    do { // replace all matching substrings
	    	System.out.println(org);
	    	i = org.indexOf(search);
	    	if(i != -1) {
	    		//записати шматоквід 0 ДО знайденого рядка
	    		result = org.substring(0, i);
	    		//додати до ПЕРШОГО шматка заміну
	    		result = result + sub;
	    		//відрізати шматок ПІСЛЯ знайденого рядка
	    		//і додати до решти
	    		result = result + org.substring(i + search.length());//довжина шуканого рядка
	    		org = result;
	    	}
	    } while(i != -1);
	    System.out.println();
	    
System.out.println("використання replace(): ");
	    s = "HELLOMAN".replace("LL", "asdasdasd");
	    System.out.println(s);
	    System.out.println();
	    
System.out.println("використання trim та BufferedReader: ");
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str;
	    System.out.println("Enter 'stop' to quit.");
	    System.out.println("Enter State (Missouri,Washington): ");
	    do {
	    	str = br.readLine();
	    	str = str.trim(); // remove whitespace
	    	if(str.equals("Illinois"))
	    		System.out.println("Capital is Springfield.");
	    	else if(str.equals("Missouri"))
	    		System.out.println("Capital is Jefferson City.");
	    	else if(str.equals("California"))
	    		System.out.println("Capital is Sacramento.");
	    	else if(str.equals("Washington"))
	    		System.out.println("Capital is Olympia.");
	    } while(!str.equals("stop"));
	    System.out.println();
	    
System.out.println("Використання StringTokenizer");
	//рядок для розкладання
		String in = "title=Java: The Complete Reference;" +
				"author=Schildt;" +
				"publisher=McGraw-Hill;" +
				"copyright=2011";
	//розкласти рядок
		StringTokenizer st = new StringTokenizer(in, "=;");
		
		while(st.hasMoreTokens()) {
			//оскільки "ключі" та "значення" розташовані через один:
			String key = st.nextToken();
			String val = st.nextToken();
			System.out.println(key + "\t" + val);
		}
		System.out.println();
	}
}