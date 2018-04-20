
public interface Chromosome {

	Chromosome crossover(Chromosome chromosome);
	void mutate(double mutationRate);
	
	//The getFitness method should return a number that represents how fit the chromosome is. Higher
	//values are more fit. A chromosome that represents an illegal solution should return 0 for fitness. A
	//chromosome that represents a perfect solution should return 1 for fitness.
	double getFitness();
	
	
}
