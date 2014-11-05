package ch28_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _1_RegExpr {

	public static void main(String[] args) {
		Pattern pat; 
	    Matcher mat; 
	    boolean found; 
	 
	    pat = Pattern.compile("Java");
	    mat = pat.matcher("Java");
	 
	    found = mat.matches(); // check for a match 
	 
	    System.out.println("Testing Java against Java."); 
	    if(found) System.out.println("Matches"); 
	    else System.out.println("No Match"); 
	 
	    System.out.println(); 
	 
	    System.out.println("Testing Java against Java 7."); 
	    mat = pat.matcher("Java 7"); // create a new matcher 
	 
	    found = mat.matches(); // check for a match 
	 
	    if(found) System.out.println("Matches");
	    else System.out.println("No Match");
	    
//------------------------------------------------
	    
	    pat = Pattern.compile("Java"); 
	    mat = pat.matcher("Java 7"); 
	 
	    System.out.println("Looking for Java in Java 7."); 
	 
	    if(mat.find()) System.out.println("subsequence found"); 
	    else System.out.println("No Match");
	    
//------------------------------------------------
	    
	    pat = Pattern.compile("test"); 
	    mat = pat.matcher("test 1 2 3 test"); 
	 
	    while(mat.find()) { 
	      System.out.println("test found at index " + mat.start()); 
	    } 
	}

}
