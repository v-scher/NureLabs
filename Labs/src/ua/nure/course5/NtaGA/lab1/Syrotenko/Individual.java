package ua.nure.course5.NtaGA.lab1.Syrotenko;


import java.util.Random;

public class Individual implements Comparable<Individual> {

    private static final Random R = new Random();
    private static final double
            L1 = -100, R1 = 100,
            L2 = -100, R2 = 100;

    private long genes = 0;
    private double func = 0;
    private double x1;
    private double x2;

    public double getFunc() {
        return func;
    }

    public Individual() {
        double i1 = R.nextInt(Integer.MAX_VALUE);
        double i2 = R.nextInt(Integer.MAX_VALUE);

        genes = (int)i1;
        genes = genes << Integer.SIZE;
        genes = genes | (int)i2;

        x1 = L1 + (R1 - L1) * (i1 / Integer.MAX_VALUE);
        x2 = L2 + (R2 - L2) * (i2 / Integer.MAX_VALUE);

        func = func(x1, x2);
    }

    private double func(double x1, double x2) {
        return 1 + Math.pow(x1 * x1 + x2 * x2, 0.25) * (Math.sin(50 * Math.pow(x1 * x1 + x2 * x2, 0.1) * 3.14/180) + 1);
    }

    public static void main(String[] args) {
    }

    private Individual(long newGenes) {
        genes = newGenes;

        x1 = L1 + (R1 - L1) * (newGenes >> Integer.SIZE) / Integer.MAX_VALUE;
        x2 = L2 + (R2 - L2) * ((int) newGenes) / Integer.MAX_VALUE;

        func = func(x1, x2);
    }

    public Individual crossover(Individual other) {
        int start = R.nextInt(Long.SIZE);
        int length = R.nextInt(Long.SIZE);
        long mask = 0;

        for (int i = start; i < start + length; i++) {
            mask |= 1L << i % Long.SIZE;
        }

        long newGenes = 0;
        newGenes |= mask & this.genes;
        newGenes |= ~mask & other.genes;

        return new Individual(newGenes);
    }

    public void mutate() {
        long n = genes;
        for (int i = 0; i < Long.SIZE - 1; i++) {
            if (i != 31 && R.nextDouble() <= ua.nure.course5.NtaGA.lab1.Syrotenko.GeneticAlgorithm.MUTATION_RATE) {
                n = (((1L << i) & n) == 0)
                        ? n | (1L << i)
                        : n ^ (1L << i);
            }
        }
        genes = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Individual that = (Individual) o;

        return genes == that.genes;
    }

    @Override
    public int hashCode() {
        return (int) (genes ^ (genes >>> 32));
    }

    @Override
    public String toString() {
        return "x1 = " + exactLength(x1) + ",\t\tx2 = " + exactLength(x2) + ",\t\tfunc = " + exactLength(func);
    }

    public static String exactLength(double d) {
        String s = Double.toString(d);
        if (s.indexOf('e') > 0 || s.indexOf('E') > 0) {
            return s;
        }
        int dot = s.indexOf('.');
        int LENGTH = 3;
        if (s.length() - dot < LENGTH) {
            return s;
        }
        return s.substring(0, dot + LENGTH + 1);
    }

    public double getDistance(Individual individual) {
        double x1 = getX(genes);
        double y1 = getY(genes);

        double x2 = getX(individual.genes);
        double y2 = getY(individual.genes);

        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }

    public double getX(long genes) {
        return L1 + (R1 - L1) * (genes >> Integer.SIZE) / Integer.MAX_VALUE;
    }

    public double getY(long genes) {
        return L2 + (R2 - L2) * ((int) genes) / Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Individual o) {
        return (func - o.func > 0)
                ? 1 
                : (func == 0.0)
                    ? 0
                    : -1;
    }
}
