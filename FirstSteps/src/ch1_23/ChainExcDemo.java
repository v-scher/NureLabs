package ch1_23;
class ChainExcDemo {
	static void demoproc(){
		NullPointerException e =  new NullPointerException("Верхній рівень");
		//додати виключення-причину
		e.initCause(new ArithmeticException("Вагома причина"));
		throw e;
	}
	
	public static void main(String args[]){
		try{
			demoproc();
		} catch(NullPointerException e){
			//Показати перехоплене виключення
			System.out.println("Перехоплено: " + e);
			
			//Показати виключення-причину
			System.out.println("Початкова причина: " + e.getCause());
		}
	}
}