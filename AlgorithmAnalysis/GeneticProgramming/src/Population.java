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
		BinaryChromosome best = population.get(0);
		//This will return the chromosome with the highest fitness
		for (int i = 0; i < population.size(); i++)
		{
			if(Math.abs(population.get(i).getFitness()) > Math.abs(best.getFitness()))
			{
			  best = population.get(i);
			}
		}
		
		System.out.println("Eval inside evaluate");
		System.out.println(best.getFitness());
		return best;
	}
	
	void breed()
	{
		
	}
	
	void setMutationRate(double rate)
	{
		
	}
}

