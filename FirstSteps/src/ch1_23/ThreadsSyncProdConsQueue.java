package ch1_23;
class Queue{
	int n;
	boolean valSet = false;
	
	synchronized int get(){
		while(!valSet)
			try{
				//передає керування наступному в черзі потоку
				wait();
			}catch(InterruptedException e){
				System.out.println("Все пропало!");
			}
		
		System.out.println("Отримано: " + n);
		valSet = false;
		//сповіщає один потік, який викликав wait(), що час працювати
		notify();
		return n;
	}
	
	synchronized void put(int n){
		while(valSet)
			try{
				//передає керування наступному в черзі потоку
				wait();
			}catch(InterruptedException e){
				System.out.println("Все пропало!");
			}
		this.n = n;
		valSet = true;
		//сповіщає один потік, який викликав wait(), що час працювати
		notify();
		System.out.println("Передано: " + n);
	}
}

class Producer implements Runnable{
	Queue q;
	
	Producer(Queue q){
		this.q = q;
		new Thread(this, "Постачальник").start();
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
		new Thread(this, "Споживач").start();
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
		
		System.out.println("Для зупинки натисни STOP.");
	}
}