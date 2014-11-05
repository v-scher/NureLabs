package ch1_23;
//в узагальнених класах НЕ МОЖНА:
//створювати екземпляри типа параметру 
//T ob = new T();,
//використовувати статичні члени (змінні та методи, що використовують узаг. тип)
//static T ob;
//static T getob(){};,
//створювати посилання на масиви можна, але створити екземпляр узаг. типу не можна,
//узагальнений клас не може розширяти клас Throwable, а отже не можна створити узаг.
//клас виключень
class Gen<T,V> { 
	T ob1;	//T замінюється на Object
	V ob2;	//V замінюється на Object

	Gen(T qwe1, V qwe2) { ob1 = qwe1; ob2 = qwe2; } 

	T getob1() { return ob1; } 
	
	V getob2() { return ob2; } 
	
	void showType() { 
		System.out.println("Type of T is " + ob1.getClass().getName());
		System.out.println("Type of V is " + ob2.getClass().getName());
	} 
} 

//Use a generic constructor. 
class GenCons { 
	private double val; 

	<T extends Number> GenCons(T arg) { 
		val = arg.doubleValue(); 
	} 
	
	void showval() { 
		System.out.println("val: " + val); 
	} 
}

//обмеження типу T - цей тип має бути Number або молодшим(ширшим)
//параметр T буде замінений на Number
class GenLim<T extends Number>{
	T[] nums;

	GenLim(T[] o) { nums = o; }  
	  
	// Повертає double в б-якому разі, як "найстарший" тип
	double average() {  
		double sum = 0.0; 
	
		for(int i=0; i < nums.length; i++)  
			sum += nums[i].doubleValue();

		return sum / nums.length; 
	}
	
	//узагальнений метод
	//? - будь-який об'єкт класу Stats, що дозволяє порівнювати між собою
	//різні типи
	boolean sameAve(GenLim<?> qwe){
		if(average()==qwe.average())
			return true;
		else
			return false;
	}
}

class Generalization{
	//узагальнений метод.
	//параметр типу оголошений ПЕРЕД значенням, що повертається
	//може бути як статичним (може бути викликаним незалежно від створених об'єктів),
	//однак, узагальнені методи можуть бути нестатичними
	static <T, V extends T> boolean isIn(T x, V[] y) { 		 
		for(int i=0; i < y.length; i++) 
			if(x.equals(y[i])) return true; 
		return false;
	}
	
	public static void main(String args[]){
		//Об'єкт класу Integer 
		//автопакування int в Integer
		Gen<Integer, String> iOb = new Gen<>(88, "Узагальнення");	
		//як видно, вдруге можна не вказувати тип в <>
		iOb.showType(); 
			 
		//під час використання узагальнення автоматично виконується 
		//неявне приведення типів
		int v1 = iOb.getob1();

		System.out.println("value1: " + v1); 
	
		// Get the value of strOb. Again, notice 
		// that no cast is needed. 
		String str = iOb.getob2(); 
		System.out.println("value2: " + str);
		System.out.println();
		
		System.out.println("Демонстрація порівняння різних типів одного класу");
		Integer inums[] = { 1, 2, 3, 4, 5 };
		Double dnums[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
		
		//вдруге можна не вказувати тип в <>
		GenLim<Integer> iOb1 = new GenLim<>(inums);
		GenLim<Double> dOb1 = new GenLim<>(dnums);
		
		double v2 = iOb1.average();
		double v3 = dOb1.average();
		
		System.out.println("AVG is: " + v2);
		System.out.println("AVG is: " + v3);
		
		//порівняння об'єктів одного шаблону але різних типів
		System.out.println("AVGs is equal: " + iOb1.sameAve(dOb1));
		
		System.out.println();
		System.out.println("Демонстрація узагальнених методів");
	    // Use isIn() on Integers. 
	    Integer nums[] = { 1, 2, 3, 4, 5 }; 
	 
	    //УВАГА! Параметр типу в метод не підставляється, це виконується автоматично
	    if(isIn(2, nums)) System.out.println("2 is in nums"); 
	    if(!isIn(7, nums)) System.out.println("7 is not in nums"); 
	    System.out.println(); 
	 
	    // Use isIn() on Strings. 
	    String strs[] = { "one", "two", "three", "four", "five" }; 
	 
	    if(isIn("two", strs)) System.out.println("two is in strs"); 
	    if(!isIn("seven", strs)) System.out.println("seven is not in strs"); 
	 
	    //Opps! Won't compile! Types must be compatible. 
	    //if(isIn("two", nums)) 
	    //System.out.println("two is in strs"); 
	    
		System.out.println();
		System.out.println("Демонстрація узагальнених конструкторів");
		
	    GenCons test = new GenCons(100); 
	    GenCons test2 = new GenCons(123.5F); 
		
	    test.showval(); 
	    test2.showval(); 
	    
		System.out.println();
		System.out.println("Демонстрація базових типів (сумісність старого коду)");
		
	    Gen<Integer,String> intOb = new Gen<>(88,"Узагальнення_текст");  
	    Gen<String,String> strOb = new Gen<>("Значення три","Узагальнення_текст");
	    
	    // Створити об'єкт базового типу і призначити йому значення Double
	    //замість параметра T підставляється Object, що дозволяє присвоїти Gen
	    //посилання на будь-який тип об'єктів класу Gen і навпаки.
	    Gen raw = new Gen(new Double(98.6),"Double Value"); 
	 
	    // Cast here is necessary because type is unknown. 
	    double d = (Double) raw.getob1(); 
	    System.out.println("value: " + d); 
	 
	    // The use of a raw type can lead to run-time. 
	    // exceptions.  Here are some examples. 
	 
	    // The following cast causes a run-time error! 
	    //int i = (Integer) raw.getob(); // run-time error 
	 
	    // This assigment overrides type safety. 
	    strOb = raw; // OK, but potentially wrong 
	    raw = intOb; // OK, but potentially wrong 
	    //String str = strOb.getob(); // run-time error  
	}
}