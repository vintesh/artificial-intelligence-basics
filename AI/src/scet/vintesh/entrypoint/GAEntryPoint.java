/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.entrypoint;

import scet.vintesh.genetic_algorithm.Population;

/**
 * Original implementation is available on,
 * https://github.com/shivamkalra/Genetic-Algorithm-Expression-Synthesizer
 *
 * Here it is the simplified & well explained version. Some DS are modified.
 *
 * @author Vintesh
 */
public class GAEntryPoint {

    /**
     * Specify the number for which you want to Generate Expression
     */
    public static double TARGET = 125;

    public static void main(String[] args) {
        // Creating the population
        Population population = new Population();


        // Termination Criteria 
        int solved = 0;
        while (solved != 10000) {
            // If Requirement is met?
            if (Math.abs(population.getSpecies().peek().getValue() - TARGET) < 0.0005) {
                solved++;
                System.out.println("Progress[" + solved + "]: " + (solved / 10000.0) * 100 + "%");
            }
            population.makeNextGeneration();
        }

        System.out.println("BEST SOLUTION: " + population.bestSolution.toString() + " = "
                + population.bestSolution.getValue());
    }
}
