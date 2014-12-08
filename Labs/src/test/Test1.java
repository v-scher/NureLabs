package test;

public class Test1 {
	
	static double Tochka_Optiuma_Y1 = 0, Tochka_Optiuma_Y2 = 0;
	static int temp = 0, prev = 0;
	static double ustupka = 0.05;
	static double[][] arr;

	public static void main(String[] args) {
		arr = new double[10][2];
		double[][] Granitcha_adekvatnosti = { { 0.3, 0.6 }, { 0.3, 0.8 } };

		for (int i = 0; i < 10; i++) {
			double t = (0.1 + Math.random() * (0.5 - 0.1)) * 1000;
			double t2 = (0.2 + Math.random() * (0.7 - 0.2)) * 1000;

			arr[i][0] = Math.round(t) / 1000.0;
			arr[i][1] = Math.round(t2) / 1000.0;
		}
		for (int i = 0; i < Granitcha_adekvatnosti.length; i++) {
			Tochka_Optiuma_Y1 += Granitcha_adekvatnosti[0][i];
		}
		Tochka_Optiuma_Y1 = Tochka_Optiuma_Y1 / Granitcha_adekvatnosti.length;

		for (int i = 0; i < Granitcha_adekvatnosti.length; i++) {
			Tochka_Optiuma_Y2 += Granitcha_adekvatnosti[1][i];
		}
		Tochka_Optiuma_Y1 = 0.3;
		Tochka_Optiuma_Y2 = 0.6;
		for (int i = 0; i < arr.length; i++) {
			System.out.print("-----------------------------\n");
			System.out.print("X" + (i + 1) + " | ");
			for (int j = 0; j < 2; j++) {
				System.out.print(arr[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("\nГраницы области адекватности:");

		for (int i = 0; i < Granitcha_adekvatnosti.length; i++) {
			for (int j = 0; j < Granitcha_adekvatnosti.length; j++) {
				System.out.print(Granitcha_adekvatnosti[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\nТочка оптимальности: " + Tochka_Optiuma_Y1 + " "
				+ Tochka_Optiuma_Y2);
		System.out.println("\nКритерий пригодности: ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (arr[i][0] >= Granitcha_adekvatnosti[0][0]
						&& arr[i][0] <= Granitcha_adekvatnosti[0][1]) {
					if (arr[i][1] >= Granitcha_adekvatnosti[1][0]
							&& arr[i][1] <= Granitcha_adekvatnosti[1][1]) {
						System.out.print(arr[i][j] + " ");
						temp = 1;
					} else {
						temp = 0;
					}
				} else {
					temp = 0;
				}
			}
			if (temp == 1) {
				System.out.print("X" + (i + 1) + " пригоден");
				System.out.println();
			}
		}
		System.out.println("\nУступка для точки адекватности: \n По y1: ["
				+ (Tochka_Optiuma_Y1 - ustupka) + " "
				+ (Tochka_Optiuma_Y1 + ustupka) + "]; \n По y2: ["
				+ (Tochka_Optiuma_Y2 - ustupka) + " "
				+ (Tochka_Optiuma_Y2 + ustupka) + "];");
		System.out.println("\nКритерий оптимальности: ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (arr[i][0] >= (Tochka_Optiuma_Y1 - ustupka)
						&& arr[i][0] <= (Tochka_Optiuma_Y1 + ustupka)) {
					if (arr[i][1] >= (Tochka_Optiuma_Y2 - ustupka)
							&& arr[i][1] <= (Tochka_Optiuma_Y2 + ustupka)) {
						temp = 2;
					} else
						temp = 11;
				} else if (arr[i][1] >= (Tochka_Optiuma_Y2 - ustupka)
						&& arr[i][1] <= (Tochka_Optiuma_Y2 + ustupka)) {
					temp = 12;
				} else {
					temp = 0;
				}
			}
			if (temp == 2) {
				System.out.print(arr[i][0] + " " + arr[i][1] + " ");
				System.out.print("X" + (i + 1) + " оптимален по y1 и y2");
				System.out.println();
			}
			if (temp == 11) {
				System.out.print(arr[i][0] + " " + arr[i][1] + " ");
				System.out.print("X" + (i + 1) + " оптимален по y1");
				System.out.println();
			}
			if (temp == 12) {
				System.out.print(arr[i][0] + " " + arr[i][1] + " ");
				System.out.print("X" + (i + 1) + " оптимален по y2");
				System.out.println();
			}
		}
		System.out.println("\nКритерий превосходства: ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (arr[i][0] >= (Tochka_Optiuma_Y1 - ustupka)
						&& arr[i][0] <= (Tochka_Optiuma_Y1 + ustupka)) {
					if (arr[i][1] >= (Tochka_Optiuma_Y2 - ustupka)
							&& arr[i][1] <= (Tochka_Optiuma_Y2 + ustupka)) {
						temp = 2;
					} else
						temp = 0;
				} else if (arr[i][0] >= (Tochka_Optiuma_Y1 - ustupka)
						&& arr[i][0] <= (Tochka_Optiuma_Y1 + ustupka)) {
					temp = 11;
				} else if (arr[i][1] >= (Tochka_Optiuma_Y2 - ustupka)
						&& arr[i][1] <= (Tochka_Optiuma_Y2 + ustupka)) {
					temp = 12;
				} else
					temp = 0;
			}
			if (temp == 2) {
				System.out.print(arr[i][0] + " " + arr[i][1] + " ");
				System.out.print("X" + (i + 1) + " превосходен");
				System.out.println();
			}
		}
	}
}
