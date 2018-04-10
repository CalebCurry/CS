
public class GeneticProgramming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double x = 5;
		double y = 20;
		
		for (int i = 0; i < 100; i++)
		{
			//We are going to use 54 because A B C and each require 16 (8 for numerator 8 for denominator).
			//Plus a sign bit for each
			BinaryChromosome chromosome = new BinaryChromosome(54, x, y);
			chromosome.randomize();
			
			chromosome.getFitness();
			
			//System.out.println(chromosome.getNumber(0, 7));
			//System.out.println(chromosome.getBinaryString());	
		}
	}

}
