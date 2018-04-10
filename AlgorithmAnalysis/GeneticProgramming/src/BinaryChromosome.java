import java.util.Random;

public class BinaryChromosome implements Chromosome
{
    protected int [] bits;
    double x;
    double y;
    private Random random = new Random ();
    
    public BinaryChromosome (int numbits, double x, double y)
    {
        bits = new int [numbits];
        this.x = x;
        this.y = y;
    }
    
    public void randomize ()
    {
        for (int i = 0; i < bits.length; i++)
            bits[i] = random.nextInt(2);
    }
    
    @Override
    public void mutate(double mutationRate)
    {
        for (int i = 0; i < bits.length; i++)
        {
            if (random.nextDouble() <= mutationRate)
            {
                if (bits[i] == 1)
                    bits[i] = 0;
                else
                    bits[i] = 1;
            }
        }
    }

    @Override
    public Chromosome crossover(Chromosome other)
    {
        return crossover ((BinaryChromosome) other, new BinaryChromosome (bits.length, x, y));
    }
    
    public Chromosome crossover(BinaryChromosome other, BinaryChromosome newChromosome)
    {
        int first = random.nextInt(bits.length);
        int second = random.nextInt(bits.length);
        
        if (first > second)
        {
            int temp = first;
            first = second;
            second = temp;
        }
        
        for (int i = 0; i < first; i++)
            newChromosome.bits[i] = bits[i];

        for (int i = first; i < second; i++)
            newChromosome.bits[i] = other.bits[i];
        
        for (int i = second; i < bits.length; i++)
            newChromosome.bits[i] = bits[i];
        
        return newChromosome;
    }

    @Override
    public double getFitness()
    {
    	//currently having a divide by zero issue occasionally
    	double result = ((double)getNumber(1, 8) /  getNumber(10, 17)) * x
						+ ((double)getNumber(19, 26) / getNumber(28, 35)) * y
						+ ((double)getNumber(37, 44) / getNumber(46, 53));
    	
    	System.out.println("("+ getNumber(1, 8) +  "/" + getNumber(10, 17) + ") (" + x + ")"
				+ " + ("+ getNumber(19, 26) +  "/" + getNumber(28, 35) + ") (" + y + ")"
				+ " + (" + getNumber(37, 44) +  "/" + getNumber(46, 53) + ") = " + result );
    	
    	
    	
    
		
        // This should be overridden by a subclass to be useful
        return 0;
    }
    
    public int getNumber (int first, int last)
    {
        int result = 0;
        int placeValue = 1;
        
        for (int i = last; i >= first; i--)
        {
            result += (bits[i] * placeValue);
            placeValue *= 2;
        }
        
        return result;
    }
    
    String getBinaryString()
    {
    	String result = "";
    	for (int i = 0; i < bits.length; i++)
    	{
    		result += bits[i];
    	}
    	return result;
    }
}