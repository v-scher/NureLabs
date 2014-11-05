package ch1_23;
import java.io.*;
import java.util.TreeMap;


public class SerializeDemo {
	public static void main(String args[]) {
		// Object serialization 
		 
	    try ( ObjectOutputStream objOS = 
	            new ObjectOutputStream(new FileOutputStream("SerializeDemo")) ) 
	    { 
	      MyClass1 object1 = new MyClass1("Hello", -7, 2.7e10); 
	      
	      object1.map.put("123", 12);
	      object1.map.put("323", 532);
	      object1.map.put("asdf", 52);
	      object1.map.put("df", 12123);
	      object1.map.put("fuyh", 22);
	      
	      System.out.println("object1: " + object1);
	      
	      object1.Out();
	      
	      objOS.writeObject(object1); 
	    } 
	    catch(IOException e) { 
	      System.out.println("Exception during serialization: " + e); 
	    } 
	 
	    // Object deserialization 
	
	    try ( ObjectInputStream objIS = 
	            new ObjectInputStream(new FileInputStream("SerializeDemo")) ) 
	    { 
	      MyClass1 object2 = (MyClass1)objIS.readObject(); 
	      System.out.println("object2: " + object2); 
	      object2.Out();
	    } 
	    catch(Exception e) { 
	      System.out.println("Exception during deserialization: " + e); 
	    }
	}
}

class MyClass1 implements Serializable { 
	  String s; 
	  int i; 
	  double d; 
	  public TreeMap<String, Integer> map;
	  
	  public void Out(){
		  for (String S : map.keySet())
			  System.out.println(S + " " + map.get(S));
	  }
	  public MyClass1(String s, int i, double d) { 
		map = new TreeMap<String, Integer>();
	    this.s = s; 
	    this.i = i; 
	    this.d = d; 
	  } 
	 
	  public String toString() { 
	    return "s=" + s + "; i=" + i + "; d=" + d; 
	  } 
	}

