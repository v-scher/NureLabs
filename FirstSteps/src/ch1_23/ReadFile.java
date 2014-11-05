package ch1_23;
import java.io.*;
import java.util.*;
//�� ����� ��������� �������� ���� txt � ������� � ������ ASCI

class ReadFile{
	public static void main(String args[]){
		//���� ����������� � ���� � ��������
		
		//String args1[] = new String[1];
		//args1[0] = "testReadFile.txt";
		System.out.println("������ ���� �� ����� \"testReadFile.txt\"");
		Scanner qwe = new Scanner(System.in);
		
		//args1[0] = new String ("test ReadFile.txt");
		String args1 = new String (qwe.next());
		
		int i;
		
		//������������, �� ��'� ����� �������
		//if(args1.length != 1){
		//	System.out.println("����� ��'� �����");
		//	return;
		//}
		
		//������ �������� �����
		try(FileInputStream fin = new FileInputStream(args1)){
			//���� ��������, ��� ��������� �������
			do{
				i = fin.read();
				if(i!=-1) System.out.print((char) i);
			} while ( i != -1);
			
		} catch(IOException e){
			System.out.println("������� " + e);
		}
	}
}