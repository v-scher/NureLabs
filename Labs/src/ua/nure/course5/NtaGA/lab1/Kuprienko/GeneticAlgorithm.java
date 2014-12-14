package ua.nure.course5.NtaGA.lab1.Kuprienko;

import java.util.Random;

public class GeneticAlgorithm {

    public static final double MUTATION_RATE = 0.015;

    private static final Random R = new Random();

    public static Population evolve(Population pop) {
        Population nextGeneration = new Population(pop.size(), false);

        for (int i = 0; i < pop.size(); i++) {
            Individual child;
            Individual parent1 = selection(pop);
            Individual parent2 = selection(pop);
            child = parent1.crossover(parent2);
            child.mutate();
            nextGeneration.saveIndividual(i, child);
        }

        return nextGeneration;
    }

    private static Individual selection(Population p) {
        double min = p.getWorst().getFunc();

        double sum = 0;
        for (int i = 0; i < p.size(); i++) {
             sum  += p.getIndividual(i).getFunc() - min;
        }
        double avg = sum/p.size();

        Individual currentIndividual;
        // начало отбора
        do {
            // значение рулетки
            double randPosition = R.nextDouble() * sum;

            int currentNum = 0;
            // сумма значений функции соответствия
            double currentSum = 0;
            do { // поиск нужной особи
                currentIndividual = p.getIndividual(currentNum++);
                // увеличиваем сумму на величину функции соответствия
                currentSum += currentIndividual.getFunc() - min;
                // пока сумма не перешагнет значение рулетки
            } while (currentSum < randPosition);
            // если значение функции соответствия више среднего - отбор окончен
        } while (currentIndividual.getFunc() - min < avg);

        return currentIndividual;
    }
    
    public static void main(String[] args) {
        Population p = new Population(50, true);
        int generations = 0;
        double func = p.getBest().getFunc();
        while (generations < 50) {
            generations++;
            Individual fittest = p.getBest();
            if (func < fittest.getFunc()) {
                func = fittest.getFunc();
                System.out.println("Поколение: " + generations + ", сильнейший: " + fittest);
            }
            p = evolve(p);
        }

        System.out.println();
        System.out.println("Всего поколений: " + generations);
        System.out.println("Лучшее решение:");
        System.out.println(p.getBest());

    }
    
}