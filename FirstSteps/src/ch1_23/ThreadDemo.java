package ch1_23;
//�������� ��������� Runnable
class ImpThread implements Runnable{
	//��� ��������� ���������, �� ����'������:
	String name;
	Thread t;
	ImpThread(String name){
		//�������� ������, ����� ����
		this.name = name;
		t = new Thread(this, "Imp ����");
		System.out.println("Imp ���� ��������: " + t);
		t.start();
	}
	public void run(){
		try{
			for(int i=10;i>0;i--){
				System.out.println(name + " implemented: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e){
			System.out.println(name + " imp ���� ��������");
		}
		System.out.println(name + " imp ���� ���������");
	}
}

//���������� ���� Thread
class ExtThread extends Thread{
	ExtThread(){
		//�������� ������, ����� ����
		super("Ext ����");
		System.out.println("Ext ���� ��������: " + this);
		start();
	}
	public void run(){
		try{
			for(int i=10;i>0;i--){
				System.out.println("Extended: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e){
			System.out.println("Ext ���� ��������");
		}
		System.out.println("Ext ���� ���������");
	}
}

class ThreadDemo{
	public static void main(String args[]){
		new ExtThread();
		ImpThread th1 = new ImpThread("������");
		ImpThread th2 = new ImpThread("������");
		ImpThread th3 = new ImpThread("�����");
		
		System.out.println("���� ���� ���������: " + th1.t.isAlive());
		System.out.println("���� ��� ���������: " + th2.t.isAlive());
		System.out.println("���� ��� ���������: " + th3.t.isAlive());
		
		try{
			for(int i=4;i>0;i--){
				System.out.println("�������� ���� " + i);
				Thread.sleep(1000);
			}
		} catch(InterruptedException e){
			System.out.println("�������� ���� ����������");
		}
		
		//����� �������
		try{
			System.out.println("���������� ���������� ������.");
			th1.t.join();
			th2.t.join();
			th3.t.join();
		} catch(InterruptedException e){
			System.out.println("�������� ���� ����������");
		}
		System.out.println("���� ���� ���������: " + th1.t.isAlive());
		System.out.println("���� ��� ���������: " + th2.t.isAlive());
		System.out.println("���� ��� ���������: " + th3.t.isAlive());
		
		System.out.println("�������� ���� ����������");
	}
}