package ua.nure.course5.NtaGA.lab1.Syrotenko;

public class GeneticAlgorithm {

    public static final double MUTATION_RATE = 0.015;

    public static Population evolvePopulation(Population population) {
        Population nextGeneration = new Population(population.size(), false);

        for (int i = 0; i < population.size(); i++) {
            Individual child;
            do {
                Individual parent1 = randomSelection(population);
                Individual parent2 = outbreedingSelection(population, parent1);
                child = parent1.crossover(parent2);
                child.mutate();
            } while(nextGeneration.contains(child));
            nextGeneration.saveIndividual(i, child);
        }

        return nextGeneration;
    }

    private static Individual randomSelection(Population p) {
        return p.getIndividual((int) (Math.random() * p.size()));
    }

    private static Individual outbreedingSelection(
            Population population,
            Individual firstParent)
    {
        Individual farrest = population.getIndividual(0);
        double max = Double.MIN_VALUE;

        Individual individual;
        for (int i = 1; i < population.size(); i++) {
            individual = population.getIndividual(i);
            if (individual == null || individual.equals(farrest))
                continue;
            double dist = firstParent.getDistance(individual);
            if (dist > max) {
                max = dist;
                farrest = individual;
            }
        }
        return farrest;
    }

    public static void main(String[] args) {
        Population p = new Population(50, true);
        int generations = -1;
        Individual fittest = p.getFittest();
        double func = fittest.getFunc();
        do {
            generations++;
            Individual individual = p.getFittest();
            if (func < individual.getFunc()) {
                fittest = individual;
                func = individual.getFunc();
                System.out.print("Generation: " + generations + "\t");
                System.out.println("Fittest: " + fittest);
            }
            p = GeneticAlgorithm.evolvePopulation(p);
        } while (generations < 1000);

        System.out.println("Generations used: " + generations);
        System.out.println("Best solution:");
        System.out.println(fittest);
    }
    
}