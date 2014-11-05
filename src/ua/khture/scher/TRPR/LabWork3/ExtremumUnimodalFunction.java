package ua.khture.scher.TRPR.LabWork3;

public class ExtremumUnimodalFunction {
	static double[] precicion = {0.5, 0.1, 0.05, 0.01, 0.005, 0.001};
	static double n = 17D;
	static double start = -n;
	static double end = n;

	static double a;
	static double x1;
	static double xc;
	static double x2;
	static double b;
	static double length;
	
	static double f1;
	static double fc;
	static double f2;
	
	static int calculations;
	
	static double func(double x){
		calculations++;
		return n*n*x*x + n*n*n*x/(n+1) + 4*n;
	}
	
	static void preciseFunction(double precicion){
		length = b - a;
		if(length < precicion) return;
		
		x1 = a + length/4;
		f1 = func(x1);
		fc = func(xc);
		
		if(f1 < fc){
			b = xc;
			xc = x1;
			preciseFunction(precicion);
		} else {
			x2 = b - length/4;
			f2 = func(x2);
			
			if(f2 < fc){
				a = xc;
				xc = x2;
				preciseFunction(precicion);
			} else {
				a = x1;
				b = x2;
				preciseFunction(precicion);
			}
		}
	}
	
	public static void main(String[] args){
		for( int i = 0; i < precicion.length; i++){
			calculations = 0;
			
			a = start;
			b = end;
			length = b - a;
			xc = start + length/2;
			
			preciseFunction(precicion[i]);
			System.out.println("pr: " + precicion[i] +  "\n" +
					"calcs: " + calculations +  "\n" +
					"length: " + length +  "\n" +
					"func: " + fc + "\n" +
					"xc: " + xc);
			System.out.println();
		}
	}
}
