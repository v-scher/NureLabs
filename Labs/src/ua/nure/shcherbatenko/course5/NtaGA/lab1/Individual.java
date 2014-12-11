package ua.nure.shcherbatenko.course5.NtaGA.lab1;


import java.util.Random;

public class Individual implements Comparable<Individual> {

    private static final Random R = new Random();
    private static final double
            L1 = 0.5, R1 = 1.1,
            L2 = 1.0, R2 = 4.6;

    private long genes = 0;
    private double func = 0;

    public Individual() {
        int i1 = R.nextInt(Integer.MAX_VALUE);
        int i2 = R.nextInt(Integer.MAX_VALUE);

        genes = i1;
        genes = genes << Integer.SIZE;
        genes = genes | i2;

        double x1 = L1 + (R1 - L1) * i1 * 1.0 / Integer.MAX_VALUE;
        double x2 = L2 + (R2 - L2) * i2 * 1.0 / Integer.MAX_VALUE;

        func = (-2.0 * Math.pow(x2, 3) + 6 * x2 * x2 + 10) * Math.sin(Math.log(x1) * Math.exp(x2));
    }

    private Individual(long newGenes) {
        genes = newGenes;

        double x1 = L1 + (R1 - L1) * (newGenes >> Integer.SIZE) / Integer.MAX_VALUE;
        double x2 = L2 + (R2 - L2) * ((int) newGenes) / Integer.MAX_VALUE;

        func = (-2.0 * Math.pow(x2, 3) + 6 * x2 * x2 + 10) * Math.sin(Math.log(x1) * Math.exp(x2));
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
            if (i != 31 && R.nextDouble() <= GeneticAlgorithm.MUTATION_RATE) {
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
        double x1 = L1 + (R1 - L1) * (genes >> Integer.SIZE);
        double x2 = L2 + (R2 - L2) * (int) genes;
        return "x1=" + x1 + ",\t\tx2=" + x2 + ",\t\tfunc=" + func;
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
