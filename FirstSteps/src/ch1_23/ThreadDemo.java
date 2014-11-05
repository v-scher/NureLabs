package ch1_23;
//реалізуємо інтерфейс Runnable
class ImpThread implements Runnable{
	//для зручнішого керування, не обов'язково:
	String name;
	Thread t;
	ImpThread(String name){
		//створити другий, новий потік
		this.name = name;
		t = new Thread(this, "Imp потік");
		System.out.println("Imp потік створено: " + t);
		t.start();
	}
	public void run(){
		try{
			for(int i=10;i>0;i--){
				System.out.println(name + " implemented: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e){
			System.out.println(name + " imp потік перевано");
		}
		System.out.println(name + " imp потік завершено");
	}
}

//розширюємо клас Thread
class ExtThread extends Thread{
	ExtThread(){
		//створити другий, новий потік
		super("Ext потік");
		System.out.println("Ext потік створено: " + this);
		start();
	}
	public void run(){
		try{
			for(int i=10;i>0;i--){
				System.out.println("Extended: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e){
			System.out.println("Ext потік перевано");
		}
		System.out.println("Ext потік завершено");
	}
}

class ThreadDemo{
	public static void main(String args[]){
		new ExtThread();
		ImpThread th1 = new ImpThread("Перший");
		ImpThread th2 = new ImpThread("Другий");
		ImpThread th3 = new ImpThread("Третій");
		
		System.out.println("Потік Один запущений: " + th1.t.isAlive());
		System.out.println("Потік Два запущений: " + th2.t.isAlive());
		System.out.println("Потік Три запущений: " + th3.t.isAlive());
		
		try{
			for(int i=4;i>0;i--){
				System.out.println("Головний потік " + i);
				Thread.sleep(1000);
			}
		} catch(InterruptedException e){
			System.out.println("Головний потік перерваний");
		}
		
		//друга частина
		try{
			System.out.println("Очікування завершення потоків.");
			th1.t.join();
			th2.t.join();
			th3.t.join();
		} catch(InterruptedException e){
			System.out.println("Головний потік перерваний");
		}
		System.out.println("Потік Один запущений: " + th1.t.isAlive());
		System.out.println("Потік Два запущений: " + th2.t.isAlive());
		System.out.println("Потік Три запущений: " + th3.t.isAlive());
		
		System.out.println("Головний потік завершений");
	}
}