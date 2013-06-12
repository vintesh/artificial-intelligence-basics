/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.genetic_algorithm;

import scet.vintesh.entrypoint.GAEntryPoint;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Vintesh
 */
public class Chromosome implements Comparable<Chromosome> {

    private static int CONSTANT_X = 61;
    private static int WEIGHT = 1;
    private int[] genes;
    private double fitness;
    private double value;

    public Chromosome(int[] genes) {
        this.genes = genes;
        value = evaluateChromosome(genes);
        fitness = CONSTANT_X * Math.abs(value - GAEntryPoint.TARGET) + WEIGHT * genes.length;
    }

    public int[] raw() {
        return genes;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] < 10) {
                sb.append(genes[i]);
            } else {
                if (genes[i] == 10) {
                    sb.append("+");
                } else if (genes[i] == 11) {
                    sb.append("-");
                } else if (genes[i] == 12) {
                    sb.append("*");
                } else if (genes[i] == 13) {
                    sb.append("/");
                } else {
                    sb.append("^");
                }
            }
        }
        return "Value: " + this.value + " Fitness: " + this.fitness + " Gene: " + sb.toString();
    }

    public double getFitness() {
        return fitness;
    }

    /**
     * This method should be called on parent Chromosome to generate the new
     * Child.
     *
     * @param parentChromosome - The other Parent with whom the current Species
     * will mate.
     * @return - Newly generated Child with having some of Genes of the parents.
     */
    public Chromosome mateWith(Chromosome parentChromosome) {
        int gene1Id1, gene1Id2, gene2Id1, gene2Id2;

        // Generating the random splits from Gene1
        gene1Id1 = new Random().nextInt(this.genes.length);
        if (gene1Id1 % 2 != 0) {
            gene1Id1--;
        }
        gene1Id2 = new Random().nextInt(this.genes.length);
        if (gene1Id2 % 2 != 0) {
            gene1Id2--;
        }

        // Generating the random splits from Gene2
        gene2Id1 = new Random().nextInt(parentChromosome.genes.length);
        if (gene2Id1 % 2 != 0) {
            gene2Id1--;
        }
        gene2Id2 = new Random().nextInt(parentChromosome.genes.length);
        if (gene2Id2 % 2 != 0) {
            gene2Id2--;
        }

        // Making the new Gene having new Length which gonna be...
        int[] newOffSpring = new int[Math.abs(gene1Id1 - gene1Id2)
                + Math.abs(gene2Id1 - gene2Id2) + 3];

        // Taking the Bits/DNAs from Gene1
        int minIndexOf = Math.min(gene1Id1, gene1Id2);
        int maxIndexOf = Math.max(gene1Id1, gene1Id2);
        int c = 0;

        // Adding the Operand/Number & Operators in between
        for (int i = minIndexOf; i <= maxIndexOf; i++) {
            newOffSpring[c++] = this.genes[i];
        }
        // Adding the Operator
        newOffSpring[c++] = minIndexOf == 0 ? this.genes[1] : this.genes[minIndexOf - 1];

        // Taking the Bits/DNAs from Gene2
        minIndexOf = Math.min(gene2Id1, gene2Id2);
        maxIndexOf = Math.max(gene2Id1, gene2Id2);

        // Adding Operands/Number & Operators 
        for (int i = minIndexOf; i <= maxIndexOf; i++) {
            newOffSpring[c++] = parentChromosome.genes[i];
        }

        // Setting MAXIMUM length of the chormosome
        if (newOffSpring.length > Chromosome.CONSTANT_X) {
            Chromosome.CONSTANT_X = newOffSpring.length;
        }

        return (new Chromosome(newOffSpring));
    }

    /**
     * Applies Mutation on the Chromosome
     */
    public void mutate() {

        int changedNumber = (int) (2 + (0.1 * this.genes.length));
        for (int i = 0; i < changedNumber; i++) {
            int changeIndexForGenes = new Random().nextInt(this.genes.length);
            // Replace the Number/Operand i.e. 1-9
            if (changeIndexForGenes % 2 == 0) {
                genes[changeIndexForGenes] = 1 + new Random().nextInt(9);
            } else { // Replace Operator Instead i.e. 10 - 15
                genes[changeIndexForGenes] = 10 + new Random().nextInt(5);
            }
        }

        if (new Random().nextInt(100) < Chromosome.CONSTANT_X) {

            // Marking boundries of interval of Offspring to be mutated
            int bitsId1 = new Random().nextInt(this.genes.length);
            if (bitsId1 % 2 != 0) {
                bitsId1--;
            }
            int bitsId2 = new Random().nextInt(this.genes.length);
            if (bitsId2 % 2 != 0) {
                bitsId2--;
            }

            // Making the new Modified OffSpring
            int[] tempGenes = new int[this.genes.length + Math.abs(bitsId2 - bitsId1) + 2];
            int i;

            // Copying the old OffSpring
            for (i = 0; i < this.genes.length; i++) {
                tempGenes[i] = genes[i];
            }

            // Adding Operator inbetween
            tempGenes[i++] = 10 + new Random().nextInt(5);

            bitsId1 = Math.min(bitsId1, bitsId2);

            // Adding the operators & operand of the marked Interval
            for (; i < tempGenes.length; i++) {
                tempGenes[i] = genes[bitsId1++];
            }

            // Replacing the genes
            this.genes = tempGenes;
        }

        // Updating the Value & Fitness value
        value = evaluateChromosome(genes);
        fitness = CONSTANT_X * Math.abs(value - GAEntryPoint.TARGET)
                + WEIGHT * genes.length;
    }

    /**
     * @return the newly made chromosome by making random genes.
     */
    public static Chromosome makeChromosome() {
        // Decides the strength
        int chromosomeLength = new Random().nextInt(31) + 1;
        int j = 0;
        int[] randomChromosomeGenes = new int[(chromosomeLength * 2) + 1];

        for (int i = 0; i < chromosomeLength; i++) { // will iterate to
            // Generates the numbers/operands i.e. among {1 to 9}
            randomChromosomeGenes[j++] = 1 + new Random().nextInt(9);
            // Generates the operator i.e. among { + , / , * , - }
            randomChromosomeGenes[j++] = 10 + new Random().nextInt(5);

        }
        // adding last members/gene as operand/number to ensure validity of chromosome
        randomChromosomeGenes[j] = 1 + new Random().nextInt(9);

        return new Chromosome(randomChromosomeGenes);
    }

    /**
     * Determines how strong the Chromosome is based on the genes passed.
     *
     * @param genes - The genes to be evaluated
     * @return - Fitness value of the Chromosome
     */
    public static double evaluateChromosome(int[] genes) {
        Stack<Double> numberStack = new Stack<Double>();
        Stack<Integer> operatorStack = new Stack<Integer>();

        for (int i = 0; i < genes.length; i++) {
            // if Gene values < 10 => it is number/openrand
            if (genes[i] < 10) {
                numberStack.push((double) genes[i]);
            } // if Gene values >= 10 => it is operator
            else {
                if (operatorStack.empty()) {
                    operatorStack.push(genes[i]);
                } else {
                    if (operatorStack.peek() >= genes[i]) {
                        int operator = operatorStack.pop();
                        double n1 = numberStack.pop();
                        double n2 = numberStack.pop();
                        double res = 0;

                        if (operator == 10) {
                            res = n1 + n2;
                        } else if (operator == 11) {
                            res = n2 - n1;
                        } else if (operator == 12) {
                            res = n1 * n2;
                        } else if (operator == 13) {
                            res = n2 / n1;
                        } else {
                            res = Math.pow(n2, n1);
                        }
                        numberStack.push(res);
                        i -= 1;
                        continue;
                    }
                    operatorStack.push(genes[i]);
                }
            }
        }
        double result = 0, operand;
        while (!operatorStack.empty()) {
            double n1 = numberStack.pop();
            double n2 = numberStack.pop();
            operand = operatorStack.pop();
            if (operand == 10) {
                result = n1 + n2;
            } else if (operand == 11) {
                result = n2 - n1;
            } else if (operand == 12) {
                result = n1 * n2;
            } else if (operand == 13) {
                result = n2 / n1;
            } else {
                result = Math.pow(n2, n1);
            }

            numberStack.push(result);
        }

        return numberStack.pop();
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        if (this.fitness == chromosome.fitness) {
            return 0;
        }
        if (this.fitness < chromosome.fitness) {
            return -1;
        }
        return 1;
    }
}
