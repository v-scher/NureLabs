package ua.nure.course5.NtaGA.lab1.Kuprienko;

public class Population {

    private Individual[] individuals;

    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        if (initialise) {
            for (int i = 0; i < size(); i++) {
                saveIndividual(i, new Individual());
            }
        }
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        for (int i = 1; i < size(); i++) {
            if (individuals[i].compareTo(fittest) > 0) {
                fittest = individuals[i];
            }
        }
        return fittest;
    }

    public int size() {
        return individuals.length;
    }

    public Individual getIndividual(int i) {
        return individuals[i];
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }

    public boolean contains(Individual child) {
        for (int i = 0; i < individuals.length; i++)
            if (child.equals(individuals[i]))
                return true;
        return false;
    }
}
