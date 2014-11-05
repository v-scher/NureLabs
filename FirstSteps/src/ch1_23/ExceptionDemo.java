package ch1_23;
@SuppressWarnings("serial")
class MyException extends Exception {
	private int detail;
	
	public MyException (int a){
		detail = a;
	}
	
	public String toString(){
		return "MyException[" + detail + "]";
	}
}

public class ExceptionDemo {
	static void compute(int a) throws MyException{
		System.out.println("Викликаний compute(" + a + ")");
		if (a > 10)
			throw new MyException(a);
		System.out.println("Нормальне завершення");
	}
	
	public static void main(String[] args){
		try{
			compute(1);
			compute(20);
		} catch(MyException e){
			System.out.println("Перехоплено " + e);
		} 
		System.out.println("Все, кінець");	
	}
}