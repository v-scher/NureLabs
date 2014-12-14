package ua.nure.course5.NtaGA.lab1.Kuprienko;

import java.util.Random;

public class Individual implements Comparable<Individual> {

    private static final Random R = new Random();
    private static final double
            L1 = -5.12, R1 = 5.12;

    private double func = 0;
    private int[] genes;

    public double getFunc() {
        return func;
    }

    public Individual() {
        genes = new int[5];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = R.nextInt(Integer.MAX_VALUE);
        }
        recalcFunc();
    }

    private Individual(int[] newGenes) {
        genes = newGenes;
        recalcFunc();
    }

    private void recalcFunc() {
        double f = 0;
        for (int i = 0; i < genes.length; i++) {
            double n = genes[i];
            f = L1 + (R1 - L1) * (n / Integer.MAX_VALUE);
        }
        func = f;
    }

    public Individual crossover(Individual other) {
        // создаем пустой геном
        int[] newGenes = new int[genes.length];

        // проходим по всем пяти генам (каждый соответствует одному иксу)
        for (int k = 0; k < genes.length; k++) {
            // формируем маску, по которой происходит "сливание" двух генов
            int mask = 0;
            for (int i = 0; i < Integer.SIZE; i++) {
                // распределение значений функции nextBoolean() - равномерное
                if (R.nextBoolean())
                    mask |= 1L << i % Integer.SIZE;
            }
            // добавляем в пустой ген ифнормацию от первого родителя,
            // которая соответствует маске
            newGenes[k] |= mask & this.genes[k];
            // от второго родителя - которая соответствует обратной маске
            newGenes[k] |= ~mask & other.genes[k];
        }

        return new Individual(newGenes);
    }

    public void mutate() {
        int[] n = genes;
        for (int k = 0; k < genes.length; k++) {
            for (int i = 0; i < Long.SIZE - 1; i++) {
                if (i != 31 && R.nextDouble() <= GeneticAlgorithm.MUTATION_RATE) {
                    n[k] = (((1 << i) & n[k]) == 0)
                            ? n[k] | (1 << i)
                            : n[k] ^ (1 << i);
                }
            }
        }
        genes = n;
    }

    @Override
    public String toString() {
        return "F(x1, x2, x3, x4, x5) = " + func;
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
