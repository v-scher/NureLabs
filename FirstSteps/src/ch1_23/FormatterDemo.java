package ch1_23;
// %s - ��� String
// %S - ��� String, �� ���� ��������� �������� �������
// %c - ��� �������� ������� Char
// %n - ��� ������� ������ �����
// %% - ��� ������� %
// %t - ��� ��� � ����
// %T - 
	    
// %d - ��� ����������� ֳ���� �����
// %o - ��� ��������� ֳ���� �����
// %x - ��� ���������������� ֳ����
// %X
// %f - ��� ����������� float
// %e - float � ������� �������
// %E - 
// %g - ����������� %f ��� %e , ��������� �� �������
// %G - 
	    
// %h - ��� Hash-���� ���������
// %H -

import java.util.*; 
 
class FormatterDemo { 
	public static void main(String args[]) { 
System.out.println("������������� ����������� ������������ TRY-RESOURCE");
	try(Formatter fmt = new Formatter()){ 
		fmt.format("����� \"with Java\": %s \n"
				+ "��������� ���� 10: %d \n"
				+ "float 98,6: %f \n"
				+ "������� ���� 196: %o \n"
				+ "�������������� ���� 196: %X \n", 
				"with Java", 10, 98.6, 196, 196); 
		System.out.println(fmt); 
		}
		System.out.println();
		
System.out.println("������������� %g - ����������� %f ��� %e , ��������� �� �������");
		Formatter fmt = new Formatter();  //�� ����'������ ������ ���
		//������� 1000 �� 100 (�������� �� 2 ���),
		//���� �� ����� 1*(+10)^10 (������ ����) ��������� - 4 ���� (3 ���, 5, 7, 9)
		for(double i=1000; i < 1.0e+10; i *= 100) { 
			//"format" ���� �� ������������ ����� ���� ��������
			fmt.format("%g ", i); 
			System.out.println(fmt); 
		} 
	    System.out.println();
	    
System.out.println("������������� %t");
		fmt = new Formatter(); 
		Calendar cal = Calendar.getInstance(); 
		
		// Display standard 12-hour time format. 
		fmt.format("Calendar %%tr:\n %tr", cal); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display complete time and date information. 
		fmt = new Formatter(); 
		fmt.format("Calendar %%tc:\n %tc", cal); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display just hour and minute. 
		fmt = new Formatter(); 
		fmt.format("Calendar %%tl:%%tM:\n %tl:%tM", cal, cal); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display month by name and number. 
		fmt = new Formatter(); 
		fmt.format("Calendar %%tB %%tb %%tm:\n %tB %tb %tm", cal, cal, cal); 
		System.out.println(fmt); 
		fmt.close();
	    System.out.println();
	    
System.out.println("������������� ��������� �������� ������ ����");
		fmt = new Formatter(); 
		fmt.format("10.12345 %%f: |%f|%n" 
				+ "10.12345 %%12f: |%12f|%n" 
				+ "10.12345 %%012f: |%012f|", 
		           10.12345, 10.12345, 10.12345); 
		System.out.println(fmt); 
		fmt.close(); 
	    System.out.println();

System.out.println("������ �������� ����� � ���� ������� 4 (%4d), �� �������� � ����");
		for(int i=1; i <= 10; i++) { 
			fmt = new Formatter(); 
		
		    fmt.format("%4d %4d %4d", i, i*i, i*i*i); 
		    System.out.println(fmt); 
		    fmt.close(); 
		}
	    System.out.println();
	    
System.out.println("������������� �������� ������� (max ������� ������� � ������)");
		fmt = new Formatter(); 
		
		// Format 4 decimal places. 
		fmt.format("123.1234567 %%.4f :\n %.4f", 123.1234567); 
		System.out.println(fmt); 
		fmt.close();
		
		// Format to 2 decimal places in a 16 character field. 
		fmt = new Formatter(); 
		fmt.format("123.1234567 %%16.2e:\n %16.2e", 123.1234567); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display at most 15 characters in a string. 
		fmt = new Formatter(); 
		fmt.format("\"Formatting with Java is now easy.\" %%.15s: \n %.15s", "Formatting with Java is now easy."); 
		System.out.println(fmt); 
		fmt.close();
	    System.out.println();

System.out.println("������������� �������� �������");
		fmt = new Formatter(); 
		
		// - ����������� ������
		// # �������������� ������ ������������
		// 0 ��������� ������������ ������ ������ ������
		// ����� ������� ������ ������ �����
		// + ������� ������ ������ +
		// , ������ ��������, �� ��������� ������ ����������
		// ( ��'��� ����� 
		
	System.out.println("����������� ������ �� ������������� |%10.2f|");
		fmt.format("|%10.2f|", 123.123); 
		System.out.println(fmt); 
		fmt.close();
		
	System.out.println("����������� ���� |%-10.2f|");
		fmt = new Formatter(); 
		fmt.format("|%-10.2f|", 123.123); 
		System.out.println(fmt); 
		fmt.close();
		
	System.out.println("������� ������ ������ ����� % d");
		fmt = new Formatter(); 
	    fmt.format("% d", -100); 
	    System.out.println(fmt); 
	    fmt.close();
	 
	    fmt = new Formatter(); 
	    fmt.format("% d", 100); 
	    System.out.println(fmt); 
	    fmt.close();
	 
	    fmt = new Formatter(); 
	    fmt.format("% d", -200); 
	    System.out.println(fmt); 
	    fmt.close();
	 
	    fmt = new Formatter(); 
	    fmt.format("% d", 200); 
	    System.out.println(fmt); 
	    fmt.close();
	System.out.println("������������� ���� ��� ��������� ������ ����� ��������� %,.2f");
		fmt = new Formatter(); 
	    fmt.format("%,.2f", 423452345.34); 
	    System.out.println(fmt); 
	    fmt.close();
	    System.out.println();
	    
System.out.println("������������� ������ ���������\n" 
		+ "�������� ��������������� �������� ���� \n" 
		+ "��������� ��� ����� ��������� ��� < ��� ������������");
		fmt = new Formatter(); 
		cal = Calendar.getInstance(); 
		
		fmt.format("������� %te-� %<tB %<tY ����", cal);
		//fmt.format("������� %te-� %1tB %1tY ����", cal); //��� ���� 
		System.out.println(fmt); 
		fmt.close();
	    System.out.println();
	} 
}