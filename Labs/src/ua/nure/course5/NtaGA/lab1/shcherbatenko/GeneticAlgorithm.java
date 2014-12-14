package ua.nure.course5.NtaGA.lab1.Shcherbatenko;


public class GeneticAlgorithm {

    public static final double MUTATION_RATE = 0.015;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM_ON = true;

    public static Population evolvePopulation(Population pop) {
        Population nextGeneration = new Population(pop.size(), false);
        int elitismOffset;

        if (ELITISM_ON) {
            nextGeneration.saveIndividual(0, pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual child;
            do {
                Individual parent1 = randomSelection(pop);
                Individual parent2 = inbreedingSelection(pop, parent1);
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

    private static Individual inbreedingSelection(Population p, Individual firstParent) {
        Individual closest = p.getIndividual(0);
        double dist = Double.MAX_VALUE;

        Individual ind;
        for (int i = 1; i < p.size(); i++) {
            ind = p.getIndividual(i);
            if (ind == null || ind.equals(closest))
                continue;
            double newDist = firstParent.getDistance(ind);
            if (newDist < dist) {
                dist = newDist;
                closest = ind;
            }
        }
        return closest;
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(TOURNAMENT_SIZE, false);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }

        return tournament.getFittest();
    }
    
    public static void main(String[] args) {
        Population p = new Population(50, true);
        int generationCount = 0;
        double func = p.getFittest().getFunc();
        while (generationCount < 50) {
            generationCount++;
            Individual fittest = p.getFittest();
            if (func < fittest.getFunc()) {
                func = fittest.getFunc();
                System.out.println("Generation:\t\t" + generationCount + "\t\tFittest: " + fittest);
            }
            p = GeneticAlgorithm.evolvePopulation(p);
        }

        System.out.println("Generations used: " + generationCount);
        System.out.println("Best solution:");
        System.out.println(p.getFittest());

    }
    
}