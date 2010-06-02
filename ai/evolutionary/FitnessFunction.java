package ai.evolutionary;

public interface FitnessFunction {
	
	/**
	 * Returns a real number representing how close the solution represented by the
	 * Chromosome is. A higher fitness value means better solution.
	 * @param chromosome
	 * @return
	 */
	double fitnessFunction(Chromosome chromosome);

}
