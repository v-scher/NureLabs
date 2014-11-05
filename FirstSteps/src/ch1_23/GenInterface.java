package ch1_23;
// A generic interface example. 
interface MinMax<T extends Comparable<T>> { 
  T min(); 
  T max(); 
} 

class MyClass<T extends Comparable<T>> implements MinMax<T> { 
  T[] vals; 
  MyClass(T[] qwe) { vals = qwe; } 
 
  // Return the minimum value in vals. 
  public T min() { 
    T v = vals[0]; 
    for(int i=1; i < vals.length; i++) 
      if(vals[i].compareTo(v) < 0) v = vals[i]; 
    return v; 
  } 
 
  // Return the maximum value in vals. 
  public T max() { 
    T v = vals[0]; 
    for(int i=1; i < vals.length; i++) 
      if(vals[i].compareTo(v) > 0) v = vals[i]; 
    return v; 
  } 
} 
 
class GenInterface {
  public static void qmain(String args[]) { 
    Integer inums[] = {3, 6, 2, 8, 6 }; 
    Character chs[] = {'b', 'r', 'p', 'w' }; 

    MyClass<Integer> iob = new MyClass<Integer>(inums); 
    MyClass<Character> cob = new MyClass<Character>(chs); 

    System.out.println("Max value in inums: " + iob.max()); 
    System.out.println("Min value in inums: " + iob.min()); 

    System.out.println("Max value in chs: " + cob.max()); 
    System.out.println("Min value in chs: " + cob.min()); 
  } 
}