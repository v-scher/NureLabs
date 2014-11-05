package ch1_23;
class NewThread extends Thread {
  boolean suspendFlag;

  NewThread(String threadname, ThreadGroup tgOb) {
    super(tgOb, threadname);
    System.out.println("���� ����: " + this);
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
        	//������ ��������� ���������� � ���� ������
            wait();
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Exception in " + getName());
    }
    System.out.println(getName() + " ����������.");
  }

  synchronized void mysuspend() {
    suspendFlag = true;
  }

  synchronized void myresume() {
    suspendFlag = false;
    //������ ���� ����, ���� �������� wait(), �� ��� ���������
    notify();
  }
}

class ThreadGroupDemo {
  public static void qmain(String args[]) {
    ThreadGroup groupA = new ThreadGroup("����� A");
    ThreadGroup groupB = new ThreadGroup("����� B");

    NewThread ob1 = new NewThread("������", groupA);
    NewThread ob2 = new NewThread("������", groupA);
    NewThread ob3 = new NewThread("�����", groupB);
    NewThread ob4 = new NewThread("���������", groupB);

    System.out.println("\n��������� groupA.list():");
    groupA.list();
    System.out.println("\n��������� groupB.list():");
    groupB.list();
    System.out.println();

    System.out.println("������������ ����� A");
    
    //ʳ������ �������� ������ � ����
    Thread tga[] = new Thread[groupA.activeCount()];
    //������� ������� ������, 
    //������ � ����� ������ ������� �����
    groupA.enumerate(tga); //�������� ����� ������
    
    for(int i = 0; i < tga.length; i++) {
      ((NewThread)tga[i]).mysuspend(); // �������� ����� ���� �����
    }
    
    try {
      Thread.sleep(4000); 
    } catch (InterruptedException e) {
      System.out.println("Main thread interrupted.");
    }

    System.out.println("³��������� ����� A");
    for(int i = 0; i < tga.length; i++) {
      ((NewThread)tga[i]).myresume(); // �������� ��������� ������ ����� �
    }

    // ������ ��������� ��� ������
    try {
      System.out.println("������ ���� ���������!");
      ob1.interrupt();
      System.out.println("����� ��������� ��� ������.");
      ob2.join();
      ob3.join();
      ob4.join();
    } catch (Exception e) {
      System.out.println("Exception in Main thread");
    }

    //System.out.print("��'� �� �������� ��������� ������: ");
    //System.out.println(Thread.currentThread().getName() + " " 
    //			+ Thread.currentThread().getPriority());
    
    //System.out.print("�������� ���� ������� ����������� �������� CPU: ");
    //System.out.println("Thread.currentThread().yield()");
    //Thread.currentThread().yield();
    
    System.out.println("�������� ���� ���������.");
  }
}