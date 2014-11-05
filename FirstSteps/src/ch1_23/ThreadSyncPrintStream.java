package ch1_23;
//��'��� � ������� ��� ����� ~ �������
class Printer{
	void call(String msg){
		System.out.print("[" + msg);
		try{
			Thread.sleep(1000);
		} catch(InterruptedException e){
			System.out.println("���������");
		}
		System.out.println("]");
	}
}

//��'���, �� ���� ��������� ���� ����� �� ��������� (������������� ����)
class StrStream implements Runnable{
	String msg;
	Printer target;
	Thread t;
	
	//�����, �� ������ ��������� �� ��'��� ����� � �����
	public StrStream(Printer target, String msg){
		this.target = target;
		this.msg = msg;
		t = new Thread(this);//�������� ���� ��� ����� ��'���� � �������������
		t.start();//������ ����
	}
	
	public void run(){
		synchronized (target) {//���� ������� �� ��������� �� ��'��� �����
			target.call(msg);//������ ����
		}
	}
}

class ThreadSyncPrintStream {
	public static void qmain(String args[]){
		Printer target = new Printer();
		//�������� ���� ��'��� ����� � �������� ����
		//��� ����� �������
		StrStream th1 = new StrStream(target, "³����");
		StrStream th2 = new StrStream(target, "� ���������������");
		StrStream th3 = new StrStream(target, "���!");
		
		//���������� �� ���������� ������
		try{
			th1.t.join();
			th2.t.join();
			th3.t.join();
		} catch(InterruptedException e){
			System.out.println("�������� ���� ����������");
		}
	}
}