package ch1_23;
class Queue{
	int n;
	boolean valSet = false;
	
	synchronized int get(){
		while(!valSet)
			try{
				//������ ��������� ���������� � ���� ������
				wait();
			}catch(InterruptedException e){
				System.out.println("��� �������!");
			}
		
		System.out.println("��������: " + n);
		valSet = false;
		//������ ���� ����, ���� �������� wait(), �� ��� ���������
		notify();
		return n;
	}
	
	synchronized void put(int n){
		while(valSet)
			try{
				//������ ��������� ���������� � ���� ������
				wait();
			}catch(InterruptedException e){
				System.out.println("��� �������!");
			}
		this.n = n;
		valSet = true;
		//������ ���� ����, ���� �������� wait(), �� ��� ���������
		notify();
		System.out.println("��������: " + n);
	}
}

class Producer implements Runnable{
	Queue q;
	
	Producer(Queue q){
		this.q = q;
		new Thread(this, "������������").start();
	}
	
	public void run(){
		int i = 0;
		
		while(true){
			q.put(i++);
		}
	}
}

class Consumer implements Runnable{
	Queue q;
	
	Consumer(Queue q){
		this.q = q;
		new Thread(this, "��������").start();
	}
	
	public void run(){
		while(true){
			q.get();
		}
	}
}

class ThreadsSyncProdConsQueue {
	public static void main(String args[]){
		Queue q = new Queue();
		new Producer(q);
		new Consumer(q);
		
		System.out.println("��� ������� ������� STOP.");
	}
}