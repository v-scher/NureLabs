package ch1_23;
import java.util.*;

//власне відкладене завдання має розширювати клас TimerTask
class MyTimerTask extends TimerTask {
	//він реалізує інтерфейс Runnable і запускається у потоці 
	//тому "робочий код" має бути всерединиі методу Run()
	public void run() {
		System.out.println("Відкладене завдання виконано.");
	}
}

class TimerTaskDemo {
	public static void main(String args[]) {
		MyTimerTask myTask = new MyTimerTask();
		Timer myTimer = new Timer();

		/* Set an initial delay of 1 second,
		then repeat every half second.
		 */
		myTimer.schedule(myTask, 1000, 500);
    
		try {
			Thread.sleep(5000);
		} catch (InterruptedException exc) {}

		//преривання потоку завдання
		myTimer.cancel();
	}
}
