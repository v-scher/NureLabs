package ch1_23;
import java.io.*;

class TypeObjects{
	public static void qmain(String args[])throws IOException{
System.out.println("Розміри стандартних типів:\n" +
					"byte - 8,\n" +
					"short, char - 16,\n" +
					"int,float - 32,\n" +
					"long,double - 64\n");

		int ch = (int) 'к';
		System.out.println("Символ char \"к\": " + (char)ch);
		System.out.println("Символ char \"к\" + 32768 (2^15): " + (char)(ch+32768));
		System.out.println("Символ char \"к\" + 65536 (2^16): " + (char)(ch+65536));
		System.out.println();
		
System.out.println("перетворення String в Double: ");
	    Double d1 = new Double(3.14159);
	    Double d2 = new Double("314159E-5");
	    //оператор == незастосовний, оскільки Double - об'єкт
	    System.out.println(d1 + " = " + d2 + " -> " + d1.equals(d2));
	    System.out.println();
	    
System.out.println("використання методів Double: ");
	    //(0. == 0.0) - double
	    d1 = new Double(1/0.);
	    d2 = new Double(0/0.);
	    System.out.println(d1 + ": " + d1.isInfinite() + ", " + d1.isNaN());
	    System.out.println(d2 + ": " + d2.isInfinite() + ", " + d2.isNaN());
	    System.out.println();
	    
System.out.println("перетворення String в Integer");
	    System.out.println("i = Integer.parseInt(\"2354\"): ");
	    String str = "2354";
	    int i;
	    try {
	    	//перетворення рядка в Integer
	    	i = Integer.parseInt(str);
	    //обов'язково треба відловлювати помилки!!!
	    } catch(NumberFormatException e) {
	    	System.out.println("Invalid format");
	    	i = 0;
	    }
	    System.out.println("int i = " + i);
	    System.out.println();
	    
System.out.println("використання методів перетворення Integer в String " +
	    				"в різних формах: bin, oct, hex ");
	    i = 257;
	    System.out.println(i + " in binary: " + Integer.toBinaryString(i));
	    System.out.println(i + " in octal: " + Integer.toOctalString(i));
	    System.out.println(i + " in hexadecimal: " + Integer.toHexString(i));
	    System.out.println();
	    
System.out.println("використання static методів Character");
	    char a[] = {'a', 'b', '5', '?', 'A', ' '};
	    for(i = 0; i < a.length; i++) {
	      if(Character.isDigit(a[i])) System.out.println(a[i] + " is a digit.");
	      if(Character.isLetter(a[i])) System.out.println(a[i] + " is a letter.");
	      if(Character.isWhitespace(a[i])) System.out.println(a[i] + " is whitespace.");
	      if(Character.isUpperCase(a[i])) System.out.println(a[i] + " is uppercase.");
	      if(Character.isLowerCase(a[i])) System.out.println(a[i] + " is lowercase.");
	    }
	}
}