
public class GeneticProgramming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double x = -3;
		double y = 2;
		Population population = new Population();
		
		for (int i = 0; i < 100; i++)
		{
			//We are going to use 54 because A B C and each require 16 (8 for numerator 8 for denominator).
			//Plus a sign bit for each
			BinaryChromosome chromosome = new BinaryChromosome(54, x, y);
			population.addChromosome(chromosome);
			chromosome.randomize();
			
			chromosome.getFitness();
			
			
			
			//System.out.println(chromosome.getNumber(0, 7));
			//System.out.println(chromosome.getBinaryString());	
		}

		BinaryChromosome best = population.evaluate();
		

		System.out.println("Bout to print best");
		System.out.println(best.getFitness());
		//System.out.println(best.);
		//System.out.println(population.evaluate().getFitness());
		System.out.println("Done");
	}

}
