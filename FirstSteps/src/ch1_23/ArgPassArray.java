package ch1_23;

public class ArgPassArray {
	static void vaTest(String msg, int ... v){
		System.out.print(msg + v.length + " ����: " );
		for (int x:v)
			System.out.print(x+ " ");
		System.out.println();
	}
	
	public static void qmain(String args[]){
		vaTest("���� �������� ",10);
		vaTest("������ ��������� ",1,2,3,4);
		vaTest("��� ��������� ");
	}
}
