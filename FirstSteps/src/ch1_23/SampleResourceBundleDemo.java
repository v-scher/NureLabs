package ch1_23;
import java.util.*;

public class SampleResourceBundleDemo {
	public static void main(String args[]) { 
	    ResourceBundle rd = ResourceBundle.getBundle("SampleRB");

	    System.out.println("Англійська версія: ");
	    System.out.println("Рядок для ключа Title: " + rd.getString("title"));
	    System.out.println("Рядок для ключа StopText: " + rd.getString("StopText"));
	    System.out.println("Рядок для ключа StartText: " + rd.getString("StartText"));

	    rd = ResourceBundle.getBundle("SampleRB", Locale.GERMAN);

	    System.out.println("\nНімецька версія: ");
	    System.out.println("Рядок для ключа Title: " + rd.getString("title"));
	    System.out.println("Рядок для ключа StopText: " + rd.getString("StopText"));
	    System.out.println("Рядок для ключа StartText: " + rd.getString("StartText"));
	  } 
	}
