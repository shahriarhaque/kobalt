package ai.evolutionary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class GenericGA {

	/** number of parameters in the solution we are searching **/
	int numberOfParameters;
	
	/** number of chromosomes in the pool **/
	int populationSize;

	/** ArrayList containing the whole population of chromosomes **/
	ArrayList<Chromosome> chromosomes;

	
	/** probability of a chromosome's gene being randomly mutated, default: 10 % (not used) **/
	double mutationRate = 0.1;
	
	/** probability of one chromosome swapping a gene with another one, default: 20% **/
	double crossOverRate = 0.2;

	/** minimum error that has to be achieved for a chromosome to be selected as the best, default 0.01 % **/
	double tolerance = 1.75e-6;

	/** lower bound value for all genes in the chromosome **/
	double upperBound;
	
	/** upper bound value for all genes in the chromosome**/
	double lowerBound;

	/** desired value wished to be achieved through the algorithm **/
	double targetValue;
	
	int maxGeneration = 20;
	
	private Chromosome result;

	FitnessFunction f;
	
	
	private static Random rand = new Random();

	public GenericGA(String initPop) {
		populateWorld(initPop);
	}
	
	
	/**
	 * Initializes the Genetic Algorithm Environment with a randomly generated population.
	 */
	public GenericGA(){
		populateWorld(populationSize, numberOfParameters, upperBound, lowerBound);
	}
	
	
	/**
	 * Initializes the Genetic Algorithm Environment with a population loaded from the given file.
	 */
	private void populateWorld(String initPop){
		try {
			Scanner f = new Scanner(new File(initPop));
			
			int numParam = f.nextInt();
			int popSize = f.nextInt();
			chromosomes = new ArrayList<Chromosome>(popSize);
			
			for(int i = 0; i < popSize; i++){
				Chromosome c = new Chromosome(numParam);
				
				for(int j = 0; j < numParam; j++){
					c.parameters[j] = f.nextInt();
				}
				
				chromosomes.add(c);
			}
			
			
		} 

		catch (FileNotFoundException e) {
			System.err.println("Population file " + initPop + " not found!");
			System.exit(1);
			
		}
		
	}

	private void populateWorld(int popSize,int numParam ,double upperBound, double lowerBound){
		double rand;
		
		chromosomes = new ArrayList<Chromosome>(popSize);

		for(int i = 0; i < popSize; i++){
			Chromosome c = new Chromosome(numParam);

			for(int j = 0; j < c.parameters.length; j++){
				rand = Math.random();
				c.parameters[j] = (int)(((upperBound - lowerBound)*rand) + lowerBound);
			}

			chromosomes.add(c);

		}

	}
	
	
	
	public void runGA(){
		new Thread(new Runnable(){

			public void run() {
				runInner();
				
			}
			
		}).start();
	}

	private void runInner(){

		
		int cg = 0;
		
		double currentBest = Double.POSITIVE_INFINITY;

		while(!false){
			if(cg > maxGeneration) break;
			
			for(int i = 0; i < chromosomes.size(); i++){
				Chromosome c = chromosomes.get(i);
				c.fitness = f.fitnessFunction(c);
				if(c.fitness == 0) break;
			}


			Collections.sort(chromosomes);

			cg++;
			if(chromosomes.get(0).fitness < currentBest){
				currentBest = chromosomes.get(0).fitness;
			}
			System.out.println(cg + "\t" + currentBest);
			
			if(chromosomes.get(0).fitness == 0.0) break;

			
			// swaps the value of a random parameter between two random chromosomes at the crossOverRate
			for(int i = 0; i < populationSize-1; i++){
				int x = rand.nextInt(numberOfParameters);
				int y = rand.nextInt(numberOfParameters);

				int temp;
				Chromosome c1 = chromosomes.get(i);
				Chromosome c2 = chromosomes.get(rand.nextInt(populationSize));

				double rx = Math.random();

				if(rx < crossOverRate){
					temp = c1.parameters[x];
					c1.parameters[x] = c2.parameters[y];
					c2.parameters[y] = temp;
				}



			}

		}


		Chromosome sol = chromosomes.get(0);
		
		synchronized(this){
			result = sol;
		}
	

	}
	
	

	public Chromosome getResult(){
		return result;
	}

}
