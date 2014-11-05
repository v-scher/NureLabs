package ch1_23;
import java.util.*;

public class SampleResourceBundleDemo {
	public static void main(String args[]) { 
	    ResourceBundle rd = ResourceBundle.getBundle("SampleRB");

	    System.out.println("��������� �����: ");
	    System.out.println("����� ��� ����� Title: " + rd.getString("title"));
	    System.out.println("����� ��� ����� StopText: " + rd.getString("StopText"));
	    System.out.println("����� ��� ����� StartText: " + rd.getString("StartText"));

	    rd = ResourceBundle.getBundle("SampleRB", Locale.GERMAN);

	    System.out.println("\nͳ������ �����: ");
	    System.out.println("����� ��� ����� Title: " + rd.getString("title"));
	    System.out.println("����� ��� ����� StopText: " + rd.getString("StopText"));
	    System.out.println("����� ��� ����� StartText: " + rd.getString("StartText"));
	  } 
	}
