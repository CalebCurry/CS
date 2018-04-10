import java.util.ArrayList;

public class Population {

	ArrayList<BinaryChromosome> population = new ArrayList<BinaryChromosome>();
	
	Population()
	{
		
	}
	
	void addChromosome(BinaryChromosome chromosome)
	{
		population.add(chromosome);
	}
	
	BinaryChromosome evaluate()
	{
		//This will return the chromosome with the highest fitness
		return null;
	}
	
	void breed()
	{
		
	}
	
	void setMutationRate(double rate)
	{
		
	}
}

