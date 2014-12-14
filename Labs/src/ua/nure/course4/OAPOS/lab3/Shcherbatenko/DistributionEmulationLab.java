package ua.nure.course4.OAPOS.lab3.Shcherbatenko;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

interface Distribution{
	public double getValue();
}

public class DistributionEmulationLab{
	static Random rand = new Random();
	static int width = 600;
	static int height = 400;
	static int[] columnsN = new int[width];
	static int[] columnsU = new int[width];
	static double[] stupidNumbers = new double[20];
	static NormalDistribution normal = new NormalDistribution(width/2, 5000);
	static UniformDistribution uniform =  new UniformDistribution(0, width-1);
	
	static void fillColumns(Distribution distr, int[] columns){
		for(int i = 0; i < columns.length; i++)
			columns[i] = 0;
		
		for(int i = 0; i < 1000000; i++){
			double r = distr.getValue();
			int value = (int) ( (r >= columns.length) ? width-1 :(r < 0) ? 0 : r) ;
			columns[ value ]++;
		}
		
		int max = 0;
		for(int i = 0; i < columns.length; i++)
			if( max < columns[i]) max = columns[i]; 
		
		for(int i = 0; i < columns.length; i++)
			columns[i] = (height - 40) * columns[i] / max;
	}
	
	static int event(double d, double e, double f, double g){
		double x = rand.nextDouble();
		if(d+e+f+g < 1.0) return -1;
		
		return (x < d)
				? 1
				: (x < d+e)
					? 2
					: (x < d+e+f)
						? 3
						: 4;
	}
	
	public static void main(String args[]){
		JFrame wnd = new JFrame("Normal Distribution");
		wnd.setSize(width, height);
		wnd.setLocation(150, 100);
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fillColumns(normal, columnsN);
		wnd.add(new DistrPanel(columnsN));
		wnd.setVisible(true);
		
		System.out.println("Grischuk events: ");
		for(int i = 0; i < 20; i++)
			System.out.print("" + event(0.1, 0.7, 0.15, 0.05));
		System.out.println('\n');
		
		System.out.println("Grischuk distr: ");
		normal.expectedValue = 1;
		normal.variance2 = 1;
		for(int i = 0; i < 20; i++)
			System.out.println("Normal " + normal.getValue() + "\t uniform " + uniform.getValue());
		System.out.println('\n');

		System.out.println("Scherbatenko events: ");
		for(int i = 0; i < 20; i++)
			System.out.print("" + event(0.45, 0.15, 0.2, 0.2));
		System.out.println('\n');

		System.out.println("Scherbatenko distr: ");
		normal.expectedValue = 7;
		normal.variance2 = 2;
		for(int i = 0; i < 20; i++)
			System.out.println("Normal " + normal.getValue() + "\t  uniform " + uniform.getValue());
		System.out.println('\n');

		System.out.println("Chumak events: ");
		for(int i = 0; i < 20; i++)
			System.out.print("" + event(0.15, 0.25, 0.25, 0.35));
		System.out.println('\n');

		System.out.println("Chumak normal: ");
		normal.expectedValue = 9;
		normal.variance2 = 1;
		for(int i = 0; i < 20; i++)
			System.out.println("Normal " + normal.getValue() + "\t  uniform " + uniform.getValue());
		System.out.println('\n');
		
		wnd = new JFrame("Uniform Distribution");
		wnd.setSize(width, height);
		wnd.setLocation(150 + width, 100);
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fillColumns(uniform, columnsU);
		wnd.add(new DistrPanel(columnsU));
		wnd.setVisible(true);
	}
}

class DistrPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	int[] columns;
	
	public DistrPanel(int[] columns) {
		this.columns = columns;
		setBackground(Color.WHITE);
		setForeground(Color.GREEN);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		
		for(int i=0; i < columns.length && i < width; i++)
			g.drawLine(i, height, i, (height - columns[i] < 0) ? 0 : height - columns[i]);
	}
}

class NormalDistribution implements Distribution {
	double expectedValue;
	double variance2;
	Random rand = new Random();
	
	NormalDistribution(double expectedValue, double variance2){
		this.expectedValue = expectedValue;
		this.variance2 = variance2;
	}
	
	public double getValue(){
		double x = 0;
		for( int i = 0; i < 12; i++)
			x += rand.nextDouble();
		
		return (x - 6)*Math.sqrt(variance2) + expectedValue;
	}
}

class UniformDistribution implements Distribution{
	Random r = new Random();
	double a,b;
	
	UniformDistribution(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	@Override
	public double getValue() {
		return a + (b-a)*r.nextDouble();
	}
}