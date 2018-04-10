
public class GeneticProgramming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 100; i++)
		{
			BinaryChromosome chromosome = new BinaryChromosome(40);
			chromosome.randomize();
			//System.out.println(chromosome.getNumber(0, 7));
			//System.out.println(chromosome.getBinaryString());	
		}
	}

}
