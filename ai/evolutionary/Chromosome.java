package ai.evolutionary;

/**
 * A Chromosome represents a candidate solution in a genetic algorithm. The current
 * representation can model arbitrary number of real valued parameters. 
 * @author Shahriar Haque
 *
 */
public class Chromosome implements Comparable<Chromosome>{

	/** number of genes in the chromosome **/
	int[] parameters;
	/** fitness of this chromosome**/
	double fitness;

	/**
	 * Creates a new chromosome with the given number of parameters
	 * @param numParameters
	 */
	public Chromosome(int numParameters){
		parameters = new int[numParameters];
	}

	/**
	 * Compares two chromosomes and returns the sign of the difference of their fitness
	 */
	public int compareTo(Chromosome c) {
		return ((fitness - c.fitness) > 0 ? 1 : -1) ;
	}

	public String toString(){
		return fitness + "";
	}

}