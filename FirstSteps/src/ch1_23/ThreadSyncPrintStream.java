package ch1_23;
//об'єкт з методом для друку ~ принтер
class Printer{
	void call(String msg){
		System.out.print("[" + msg);
		try{
			Thread.sleep(1000);
		} catch(InterruptedException e){
			System.out.println("Перервано");
		}
		System.out.println("]");
	}
}

//об'єкт, що може формувати потік даних на виведення (синхронізувати друк)
class StrStream implements Runnable{
	String msg;
	Printer target;
	Thread t;
	
	//метод, що приймає посилання на об'єкт друку і текст
	public StrStream(Printer target, String msg){
		this.target = target;
		this.msg = msg;
		t = new Thread(this);//створити потік для цього об'єкту з синхронізацією
		t.start();//почати потік
	}
	
	public void run(){
		synchronized (target) {//поділ доступу до вказівника на об'єкт друку
			target.call(msg);//власне друк
		}
	}
}

class ThreadSyncPrintStream {
	public static void qmain(String args[]){
		Printer target = new Printer();
		//створили ОДИН об'єкт друку і передали його
		//усім трьом потокам
		StrStream th1 = new StrStream(target, "Вітаємо");
		StrStream th2 = new StrStream(target, "в синхронізованому");
		StrStream th3 = new StrStream(target, "світі!");
		
		//очікування на завершення потоків
		try{
			th1.t.join();
			th2.t.join();
			th3.t.join();
		} catch(InterruptedException e){
			System.out.println("Головний потік перерваний");
		}
	}
}