package ch1_23;
class NewThread extends Thread {
  boolean suspendFlag;

  NewThread(String threadname, ThreadGroup tgOb) {
    super(tgOb, threadname);
    System.out.println("Нова гілка: " + this);
    suspendFlag = false;
    start(); // Start the thread
  }
  
  // This is the entry point for thread.
  public void run() {
    try {
      for(int i = 5; i > 0; i--) {
        System.out.println(getName() + ": " + i);
        Thread.sleep(1000);
        synchronized(this) {
          while(suspendFlag) {
        	//передає керування наступному в черзі потоку
            wait();
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Exception in " + getName());
    }
    System.out.println(getName() + " завершений.");
  }

  synchronized void mysuspend() {
    suspendFlag = true;
  }

  synchronized void myresume() {
    suspendFlag = false;
    //сповіщає один потік, який викликав wait(), що час працювати
    notify();
  }
}

class ThreadGroupDemo {
  public static void qmain(String args[]) {
    ThreadGroup groupA = new ThreadGroup("Група A");
    ThreadGroup groupB = new ThreadGroup("Група B");

    NewThread ob1 = new NewThread("Перший", groupA);
    NewThread ob2 = new NewThread("Другий", groupA);
    NewThread ob3 = new NewThread("Третій", groupB);
    NewThread ob4 = new NewThread("Четвертий", groupB);

    System.out.println("\nРезультат groupA.list():");
    groupA.list();
    System.out.println("\nРезультат groupB.list():");
    groupB.list();
    System.out.println();

    System.out.println("Призупинення групи A");
    
    //Кількість активних потоків в групі
    Thread tga[] = new Thread[groupA.activeCount()];
    //повертає кількість потоків, 
    //записує в масив потоки поточної групи
    groupA.enumerate(tga); //отримати групу потоків
    
    for(int i = 0; i < tga.length; i++) {
      ((NewThread)tga[i]).mysuspend(); // зупинити кожен потік групи
    }
    
    try {
      Thread.sleep(4000); 
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }

    System.out.println("Відновлення групи A");
    for(int i = 0; i < tga.length; i++) {
      ((NewThread)tga[i]).myresume(); // відновити виконання потоків групи А
    }

    // чекаємо закінчення усіх потоків
    try {
      System.out.println("перший потік перервано!");
      ob1.interrupt();
      System.out.println("Чекаю закінчення усіх потоків.");
      ob2.join();
      ob3.join();
      ob4.join();
    } catch (Exception e) {
      System.out.println("Exception in Main thread");
    }

    //System.out.print("ім'я та пріоритет поточного потоку: ");
    //System.out.println(Thread.currentThread().getName() + " " 
    //			+ Thread.currentThread().getPriority());
    
    //System.out.print("Поточний потік пропонує поступитися ресурсом CPU: ");
    //System.out.println("Thread.currentThread().yield()");
    //Thread.currentThread().yield();
    
    System.out.println("Головний потік завершено.");
  }
}