package ua.khture.scher.crppr.LabWork1;

import java.util.Random;
import java.util.Scanner;

public class Laba1 {
	public static void main(String args[]){
		int[] abcd = new int[4];
		System.out.println("Перше завдання. Давай пиши abcd !\n");
		
		try(Scanner sc = new Scanner(System.in)){
			for( int i = 0; i<4; i++){
				abcd[i] = sc.nextInt();
			}
		}
		
		int[] tr;
		
		// буде чотири трикутники
		for( int i = 0; i<4; i++){
			// створили масив для одного трикутника
			tr = new int[3];
			int q = 0;
			
			// вибираємо з abcd три сторони для нього
			for(int k = 0; k<4; k++){
				if( k==i ) // одну з 4 сторін не переписуємо
					continue;
				tr[q++] = abcd[k];
			}
			
			// перевіряємо на існування трикутника
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
						tr[2] + " - немає такого трикутника!");
				continue;
			}
			
			// складна математика
			double half = 0; // майбутній півпериметр
			for(Integer side : tr)
				half += side;
			half /= 2;
			// System.out.println("Півпериметр " + half);
			
			double square2 = half;
			for( Integer side : tr)
				square2 *= (half - side);
			
			System.out.println("Площа трикутника зі сторонами " + tr[0] + ", " + 
								tr[1] + ", " +
								tr[2] + " дорівнює " + Math.sqrt(square2));
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Друге завдання:");
		
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
					System.out.println("Матриця НЕсиметрична!");
					return;
				}
		}
		System.out.println("Матриця симетрична!");
	}
}
