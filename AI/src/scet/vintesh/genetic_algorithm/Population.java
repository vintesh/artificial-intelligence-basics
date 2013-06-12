/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.genetic_algorithm;

import java.util.Random;

/**
 *
 * @author Vintesh
 */
public class Population {

    private static int populationSize = 50;
    private static int mutationProbability = 15;
    private static int tournamentSize = 3;
    public Chromosome bestSolution;
    MyPriorityQueue<Chromosome> species;

    public Population() {
        species = new MyPriorityQueue<>();
        // Creating the popluation by adding instances of the chomosomes
        for (int i = 0; i < populationSize; i++) {
            species.add(Chromosome.makeChromosome());
        }
        bestSolution = species.peek();
    }

    /**
     * @return - The Population's members i.e. species, in Priority Queue
     */
    public MyPriorityQueue<Chromosome> getSpecies() {
        return species;
    }

    /**
     * Modifies or make new Generation.
     */
    public void makeNextGeneration() {

        MyPriorityQueue<Chromosome> newSpecies = new MyPriorityQueue<>();

        for (int i = 0; i < populationSize; i++) {
            int parentOneIndex = new Random().nextInt(populationSize);
            int parentTwoIndex = new Random().nextInt(populationSize);

            for (int j = 0; j < tournamentSize; j++) {
                int temp = new Random().nextInt(populationSize);
                if (species.get(temp).getFitness() < species.get(parentTwoIndex).getFitness()) {
                    parentTwoIndex = temp;
                }
            }
            for (int j = 0; j < tournamentSize; j++) {
                int temp = new Random().nextInt(populationSize);
                if (species.get(temp).getFitness() < species.get(parentOneIndex).getFitness()) {
                    parentOneIndex = temp;
                }
            }
            Chromosome singleChromosome = species.get(parentOneIndex).mateWith(species.get(parentTwoIndex));


            //Apply Mutation according to permissibility of the Probability
            if (1 + new Random().nextInt(100) <= mutationProbability) {
                singleChromosome.mutate();
            }

            newSpecies.add(singleChromosome);
        }

        species = newSpecies;
        if (species.peek().getFitness() < bestSolution.getFitness()) {
            bestSolution = species.peek();
        }
    }
}
