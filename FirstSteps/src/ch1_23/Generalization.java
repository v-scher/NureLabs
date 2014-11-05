package ch1_23;
//� ������������ ������ �� �����:
//���������� ���������� ���� ��������� 
//T ob = new T();,
//��������������� ������� ����� (���� �� ������, �� �������������� ����. ���)
//static T ob;
//static T getob(){};,
//���������� ��������� �� ������ �����, ��� �������� ��������� ����. ���� �� �����,
//������������ ���� �� ���� ��������� ���� Throwable, � ���� �� ����� �������� ����.
//���� ���������
class Gen<T,V> { 
	T ob1;	//T ���������� �� Object
	V ob2;	//V ���������� �� Object

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

//��������� ���� T - ��� ��� �� ���� Number ��� ��������(������)
//�������� T ���� �������� �� Number
class GenLim<T extends Number>{
	T[] nums;

	GenLim(T[] o) { nums = o; }  
	  
	// ������� double � �-����� ���, �� "����������" ���
	double average() {  
		double sum = 0.0; 
	
		for(int i=0; i < nums.length; i++)  
			sum += nums[i].doubleValue();

		return sum / nums.length; 
	}
	
	//������������ �����
	//? - ����-���� ��'��� ����� Stats, �� �������� ���������� �� �����
	//��� ����
	boolean sameAve(GenLim<?> qwe){
		if(average()==qwe.average())
			return true;
		else
			return false;
	}
}

class Generalization{
	//������������ �����.
	//�������� ���� ���������� ����� ���������, �� �����������
	//���� ���� �� ��������� (���� ���� ���������� ��������� �� ��������� ��'����),
	//�����, ���������� ������ ������ ���� ������������
	static <T, V extends T> boolean isIn(T x, V[] y) { 		 
		for(int i=0; i < y.length; i++) 
			if(x.equals(y[i])) return true; 
		return false;
	}
	
	public static void main(String args[]){
		//��'��� ����� Integer 
		//������������� int � Integer
		Gen<Integer, String> iOb = new Gen<>(88, "������������");	
		//�� �����, ������ ����� �� ��������� ��� � <>
		iOb.showType(); 
			 
		//�� ��� ������������ ������������ ����������� ���������� 
		//������ ���������� ����
		int v1 = iOb.getob1();

		System.out.println("value1: " + v1); 
	
		// Get the value of strOb. Again, notice 
		// that no cast is needed. 
		String str = iOb.getob2(); 
		System.out.println("value2: " + str);
		System.out.println();
		
		System.out.println("������������ ��������� ����� ���� ������ �����");
		Integer inums[] = { 1, 2, 3, 4, 5 };
		Double dnums[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
		
		//������ ����� �� ��������� ��� � <>
		GenLim<Integer> iOb1 = new GenLim<>(inums);
		GenLim<Double> dOb1 = new GenLim<>(dnums);
		
		double v2 = iOb1.average();
		double v3 = dOb1.average();
		
		System.out.println("AVG is: " + v2);
		System.out.println("AVG is: " + v3);
		
		//��������� ��'���� ������ ������� ��� ����� ����
		System.out.println("AVGs is equal: " + iOb1.sameAve(dOb1));
		
		System.out.println();
		System.out.println("������������ ������������ ������");
	    // Use isIn() on Integers. 
	    Integer nums[] = { 1, 2, 3, 4, 5 }; 
	 
	    //�����! �������� ���� � ����� �� �������������, �� ���������� �����������
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
		System.out.println("������������ ������������ ������������");
		
	    GenCons test = new GenCons(100); 
	    GenCons test2 = new GenCons(123.5F); 
		
	    test.showval(); 
	    test2.showval(); 
	    
		System.out.println();
		System.out.println("������������ ������� ���� (�������� ������� ����)");
		
	    Gen<Integer,String> intOb = new Gen<>(88,"������������_�����");  
	    Gen<String,String> strOb = new Gen<>("�������� ���","������������_�����");
	    
	    // �������� ��'��� �������� ���� � ���������� ���� �������� Double
	    //������ ��������� T ������������� Object, �� �������� �������� Gen
	    //��������� �� ����-���� ��� ��'���� ����� Gen � �������.
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