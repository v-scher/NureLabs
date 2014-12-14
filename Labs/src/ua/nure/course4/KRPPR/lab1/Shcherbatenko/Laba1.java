package ua.nure.course4.KRPPR.lab1.Shcherbatenko;

import java.util.Random;
import java.util.Scanner;

public class Laba1 {
	public static void main(String args[]){
		int[] abcd = new int[4];
		System.out.println("����� ��������. ����� ���� abcd !\n");
		
		try(Scanner sc = new Scanner(System.in)){
			for( int i = 0; i<4; i++){
				abcd[i] = sc.nextInt();
			}
		}
		
		int[] tr;
		
		// ���� ������ ����������
		for( int i = 0; i<4; i++){
			// �������� ����� ��� ������ ����������
			tr = new int[3];
			int q = 0;
			
			// �������� � abcd ��� ������� ��� �����
			for(int k = 0; k<4; k++){
				if( k==i ) // ���� � 4 ����� �� ����������
					continue;
				tr[q++] = abcd[k];
			}
			
			// ���������� �� ��������� ����������
			int max = tr[0];
			int maxSide = 0;
			
			for( int f = 1; f<3; f++ ){
				if( tr[f] > max){
					max=tr[f];
					maxSide = f;
				}
			}
			
			int sideSum = 0;
			for( int f = 0; f<3; f++){
				if(f == maxSide)
					continue;
				sideSum += tr[f];
			}
			
			if( max >= sideSum){
				System.out.println(tr[0] + ", " + 
						tr[1] + ", " +
						tr[2] + " - ���� ������ ����������!");
				continue;
			}
			
			// ������� ����������
			double half = 0; // �������� ����������
			for(Integer side : tr)
				half += side;
			half /= 2;
			// System.out.println("ϳ��������� " + half);
			
			double square2 = half;
			for( Integer side : tr)
				square2 *= (half - side);
			
			System.out.println("����� ���������� � ��������� " + tr[0] + ", " + 
								tr[1] + ", " +
								tr[2] + " ������� " + Math.sqrt(square2));
		}
		
		System.out.println();
		System.out.println();
		System.out.println("����� ��������:");
		
		int[][] m = new int[7][7];
		Random r = new Random();
		for( int row=0; row < 7 ; row++){
			for( int col = 0; col < 7; col++){
				m[row][col] = r.nextInt(10);
				System.out.print(m[row][col] + "  ");				
			}
			System.out.println();
		}
		
		for( int row=0; row < 7 ; row++){
			for( int col = 0; col < 7; col++)
				if( m[col][row] != m[row][col]){
					System.out.println("������� ������������!");
					return;
				}
		}
		System.out.println("������� ����������!");
	}
}
