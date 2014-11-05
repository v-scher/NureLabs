package ch1_23;
import java.util.Random;

public class RandomDemo {
	public static void qmain(String args[]){
System.out.println("������������ Random");
	//�������� ��������� ���������� ����� (���������)
		Random r = new Random();	//����������� "�������" �� �������������
		//� ���� - �������� ���. ���� �� �� � ����:
		//Random r1 = new Random(date.getTime());
		double val;
		double sum = 0;
		int bell[] = new int[10];
		
		for(int i=0; i<100; i++) {
			val = r.nextGaussian();
			sum += val;
			//��������� 10 ������:
			//(-inf;-2) (-2;-1.5) (-1.5;-1) (-1;-0.5) (-0.5;0) � �� 5 ����� �����
			double t = -2;
			for(int x=0; x<10; x++, t += 0.5)
				if(val < t) {
					bell[x]++;
					break;
				}
		}
		System.out.println("���. ��������� M(X) = " + (sum/100));
		
		// ���������� ������� ��������
		for(int i=0; i<10; i++) {
			for(int x=bell[i]; x>0; x--)
				System.out.print("*");
			System.out.println();
		}
		System.out.println();
	}
}
