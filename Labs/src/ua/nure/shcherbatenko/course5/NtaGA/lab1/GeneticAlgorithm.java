package ua.nure.shcherbatenko.course5.NtaGA.lab1;


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
            Individual parent1 = tournamentSelection(pop);
            Individual parent2 = tournamentSelection(pop);
            Individual child = parent1.crossover(parent2);
            child.mutate();
            nextGeneration.saveIndividual(i, child);
        }

        return nextGeneration;
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(TOURNAMENT_SIZE, false);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }

        return tournament.getFittest();
    }

    // Інбридинг	З витис-ненням
    
    public static void main(String[] args) {
        Population p = new Population(50, true);
        int generationCount = 0;

        while (generationCount < 20) {
            generationCount++;
            System.out.println("Generation:\t\t" + generationCount + "\t\tFittest: " + p.getFittest());
            p = GeneticAlgorithm.evolvePopulation(p);
        }

        System.out.println("Generations used: " + generationCount);
        System.out.println("Best solution:");
        System.out.println(p.getFittest());

    }
    
}