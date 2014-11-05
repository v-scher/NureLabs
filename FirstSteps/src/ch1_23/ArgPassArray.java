package ch1_23;

public class ArgPassArray {
	static void vaTest(String msg, int ... v){
		System.out.print(msg + v.length + " Вміст: " );
		for (int x:v)
			System.out.print(x+ " ");
		System.out.println();
	}
	
	public static void qmain(String args[]){
		vaTest("Один параметр ",10);
		vaTest("Чотири параметри ",1,2,3,4);
		vaTest("Без параметрів ");
	}
}
