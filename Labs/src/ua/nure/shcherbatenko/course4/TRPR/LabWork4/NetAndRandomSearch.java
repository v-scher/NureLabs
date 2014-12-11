package ua.nure.shcherbatenko.course4.TRPR.LabWork4;

import java.util.Random;

public class NetAndRandomSearch {
	
	static double n = 17.0;
	static double x1start = -100*n, x1end = 100*n,
				y1start = -100*n, y1end = 100*n;
	
	static double x2start = -100*n, x2end = 100*n,
				y2start = -100*n, y2end = 100*n,
				z2start = -100*n, z2end = 100*n;

	static double x1Center = 0, y1Center = 0;
	static double x2Center = 0, y2Center = 0, z2Center = 0;
	
	static double[] precisions = { 0.5, 0.1, 0.05, 0.01, 0.005, 0.001 };
	
	static double steps = 7;
	
	public static double f1(double x, double y){
		return n*n*x*x + n/(n+1)*x + 15*(n+1)*y*y - 2*n*y + 4*n;
	}
	
	public static double f2(double x, double y, double z){
		return n*n*x*x + n/(n+1)*x + 15*(n+1)*y*y - 2*n*y + n*z*z + 3*n*z + 4*n;
	}
	
	public static void netSearchF1(double precision,
			double X1, double X2, 
			double Y1, double Y2){
		int iterations = 0;
		int operations = 0;
		
		double step;
		double minI = 0, minJ = 0;
		double minValue = 0;
		
		operations++;
		while((step = (X2 - X1)/steps) > precision){
			operations+=2;
			minValue = f1(X1, Y1);
			minI = X1;
			minJ = Y1;

			operations++;
			for( double i = X1 + step; i <= X2; i += step){
				operations+=2;
				for( double j = Y1 + step; j <= Y2; j += step){
					operations+=2;
					double currentValue = f1(i, j);
					operations++;
					if( minValue > currentValue){
						minValue = currentValue;
						minI = i;
						minJ = j;
					}
				}
			}
			
			X1 = minI - step;
			X2 = minI + step;
			Y1 = minJ - step;
			Y2 = minJ + step;
			iterations++;
		}

		System.out.println(precision + "\t" + iterations + "\t" + operations +
				"\t" + minI + "\t" + minJ + "\t" + minValue);
	}
	
	public static void netSearchF2(double precision,
			double X1, double X2, 
			double Y1, double Y2, 
			double Z1, double Z2){
		int iterations = 0;
		int operations = 0;
		
		double step;

		double minValue = 0;
		double minI = 0, minJ = 0, minK = 0;
		
		operations++;
		while((step = (X2 - X1)/steps) > precision){
			operations+=2;
			
			minValue = f2(X1, Y1, Z1);
			minI = X1;
			minJ = Y1;
			minK = Z1;
			
			operations++;
			for( double i = X1 + step; i <= X2; i += step){
				operations+=2;
				for( double j = Y1 + step; j <= Y2; j += step){
					operations+=2;
					for( double k = Z1 + step; k <= Z2; k += step){
						operations++;
						double currentValue = f2(i, j, k);
						operations++;
						if( minValue > currentValue){
							minValue = currentValue;
							minI = i;
							minJ = j;
							minK = k;
						}
					}
				}
			}
			
			X1 = minI - step;
			X2 = minI + step;
			Y1 = minJ - step;
			Y2 = minJ + step;
			Z1 = minK - step;
			Z2 = minK + step;
			iterations++;
		}

		System.out.print("\t" + precision + "\t" + iterations + "\t" + operations +
				"\t" + minI + "\t" + minJ + "\t" + minK + "\t" + minValue);
		System.out.println();
	}
	
	public static void randomSearchF1(double precision,
			double X, double Y, double rad){
		int iterations = 0;
		int operations = 0;
		
		Random rand = new Random();
		
		operations++;
		double minValue = f1(X, Y);
		int fails = 0;
		
		operations++;
		while(rad > precision){
			operations+=2;
			double newX = X + rad *(-1.0 + rand.nextDouble()*2.0);
			double newY = Y + rad *(-1.0 + rand.nextDouble()*2.0);
			double currentValue = f1(newX, newY);
			
			operations++;
			if( minValue > currentValue){
				minValue = currentValue;
				X = newX;
				Y = newY;
				fails = 0;
			} else {
				operations++;
				if(fails++ > 7){
					rad /= 2;
					fails = 0;
				}
			}
			
			iterations++;
		}

		System.out.print("\t" + precision + "\t" + iterations + "\t" + operations +
				"\t" + X + "\t" + Y + "\t" + minValue);
		System.out.println();
	}
	
	public static void randomSearchF2(double precision,
			double X, double Y, double Z, double rad){
		int iterations = 0;
		int operations = 0;
		
		Random rand = new Random();
		
		operations++;
		double minValue = f2(X, Y, Z);
		int fails = 0;
		
		operations++;
		while(rad > precision){
			operations+=2;
			double newX = X + rad *(-1.0 + rand.nextDouble()*2.0);
			double newY = Y + rad *(-1.0 + rand.nextDouble()*2.0);
			double newZ = Z + rad *(-1.0 + rand.nextDouble()*2.0);
			double currentValue = f2(newX, newY, newZ);
			
			operations++;
			if( minValue > currentValue){
				minValue = currentValue;
				X = newX;
				Y = newY;
				Z = newZ;
				fails = 0;
			} else {
				operations++;
				if(fails++ > 7){
					rad /= 2;
					fails = 0;
				}
			}
			
			iterations++;
		}

		System.out.print("\t" + precision + "\t" + iterations + "\t" + operations +
				"\t" + X + "\t" + Y + "\t" + Z + "\t" + minValue);
		System.out.println();
	}
	
	public static void main(String args[]){
		System.out.println("---------------------------------------- NET SEARCH ----------------------------------------");
		for( double prec : precisions)
			netSearchF1(prec, x1start, x1end, y1start, y1end);
		
		for( double prec : precisions)
			netSearchF2(prec, x2start, x2end, y2start, y2end, z2start, z2end);
		
		System.out.println("---------------------------------------- RANDOM SEARCH ----------------------------------------");
		for( double prec : precisions)
			randomSearchF1(prec, x1start, y1start, 1000);
		
		for( double prec : precisions)
			randomSearchF2(prec, x2start, y2start, z2start, 1000);
	}
}
