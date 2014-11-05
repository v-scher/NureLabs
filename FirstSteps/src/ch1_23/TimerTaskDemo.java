package ch1_23;
import java.util.*;

//������ ��������� �������� �� ����������� ���� TimerTask
class MyTimerTask extends TimerTask {
	//�� ������ ��������� Runnable � ����������� � ������ 
	//���� "������� ���" �� ���� ��������� ������ Run()
	public void run() {
		System.out.println("³�������� �������� ��������.");
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

		//���������� ������ ��������
		myTimer.cancel();
	}
}
