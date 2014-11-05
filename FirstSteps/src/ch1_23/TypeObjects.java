package ch1_23;
import java.io.*;

class TypeObjects{
	public static void qmain(String args[])throws IOException{
System.out.println("������ ����������� ����:\n" +
					"byte - 8,\n" +
					"short, char - 16,\n" +
					"int,float - 32,\n" +
					"long,double - 64\n");

		int ch = (int) '�';
		System.out.println("������ char \"�\": " + (char)ch);
		System.out.println("������ char \"�\" + 32768 (2^15): " + (char)(ch+32768));
		System.out.println("������ char \"�\" + 65536 (2^16): " + (char)(ch+65536));
		System.out.println();
		
System.out.println("������������ String � Double: ");
	    Double d1 = new Double(3.14159);
	    Double d2 = new Double("314159E-5");
	    //�������� == �������������, ������� Double - ��'���
	    System.out.println(d1 + " = " + d2 + " -> " + d1.equals(d2));
	    System.out.println();
	    
System.out.println("������������ ������ Double: ");
	    //(0. == 0.0) - double
	    d1 = new Double(1/0.);
	    d2 = new Double(0/0.);
	    System.out.println(d1 + ": " + d1.isInfinite() + ", " + d1.isNaN());
	    System.out.println(d2 + ": " + d2.isInfinite() + ", " + d2.isNaN());
	    System.out.println();
	    
System.out.println("������������ String � Integer");
	    System.out.println("i = Integer.parseInt(\"2354\"): ");
	    String str = "2354";
	    int i;
	    try {
	    	//������������ ����� � Integer
	    	i = Integer.parseInt(str);
	    //����'������ ����� ����������� �������!!!
	    } catch(NumberFormatException e) {
	    	System.out.println("Invalid format");
	    	i = 0;
	    }
	    System.out.println("int i = " + i);
	    System.out.println();
	    
System.out.println("������������ ������ ������������ Integer � String " +
	    				"� ����� ������: bin, oct, hex ");
	    i = 257;
	    System.out.println(i + " in binary: " + Integer.toBinaryString(i));
	    System.out.println(i + " in octal: " + Integer.toOctalString(i));
	    System.out.println(i + " in hexadecimal: " + Integer.toHexString(i));
	    System.out.println();
	    
System.out.println("������������ static ������ Character");
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