package ua.nure.course5.TSSA.lab1.Shcherbatenko;

import java.util.Arrays;

public class Criteria {

	static final double[][] S = new double[][] {
		{0.318,	0.484},
		{0.301,	0.659},
		{0.674,	0.673},
		{0.305,	0.688},
		{0.560,	0.765},
		{0.462,	0.526},
		{0.589,	0.613},
		{0.790,	0.480},
		{0.767,	0.462},
		{0.567,	0.723}
	};
	static final double ad1l = 0.4;
	static final double ad1r = 0.7;
	
	static final double ad2l = 0.3;
	static final double ad2r = 0.8;
	
	static final double opt1 = 0.6;
	static final double opt2 = 0.7;
	
	static final double dev = 0.05;

	public static void main(String[] args) {
		double[][] suitable = new double[S.length][];
		double[][] opt = new double[S.length][];
		double[][] excellent = new double[S.length][];
		
		for (int i = 0; i < S.length; i++) {
			if (isSuitable(S[i])) {
				suitable[i] = S[i];
				if (isOptimal(S[i])) {
					opt[i] = S[i];
					if (isExcellent(S[i])) {
						excellent[i] = S[i];
					}
				}
			}
		}
		printSystems("Suitable systems:", suitable);
		printSystems("Optimal systems:", opt);
		printSystems("Excellent systems:", excellent);
	}
	
	static void printSystems(String mes, double[][] arr) {
		System.out.println(mes);
		for (int i = 0; i < S.length; i++) {
			if (arr[i] != null) {
				System.out.println("Sys_" + (i+1) + "\t" + Arrays.toString(arr[i]));
			}
		}
		System.out.println();
	}
	
	static boolean isSuitable(double[] sys) {
		return sys[0] > ad1l 
				&& sys[0] < ad1r
				&& sys[1] > ad2l
				&& sys[1] < ad2r;
	}
	
	static boolean isOptimal(double[] sys) {
		return (sys[0] > opt1-dev && sys[0] < opt1+dev)
				|| (sys[1] > opt2-dev && sys[1] < opt2+dev);
	}
	
	static boolean isExcellent(double[] sys) {
		return (sys[0] > opt1-dev && sys[0] < opt1+dev)
				&& (sys[1] > opt2-dev && sys[1] < opt2+dev);
	}
	
}
