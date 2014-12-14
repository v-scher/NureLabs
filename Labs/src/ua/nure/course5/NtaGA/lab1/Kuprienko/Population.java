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

    public Individual getBest() {
        Individual fittest = individuals[0];
        for (int i = 1; i < size(); i++) {
            if (individuals[i].compareTo(fittest) > 0) {
                fittest = individuals[i];
            }
        }
        return fittest;
    }

    public Individual getWorst() {
        Individual worst = individuals[0];
        for (int i = 1; i < size(); i++) {
            if (individuals[i].compareTo(worst) < 0) {
                worst = individuals[i];
            }
        }
        return worst;
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

}
