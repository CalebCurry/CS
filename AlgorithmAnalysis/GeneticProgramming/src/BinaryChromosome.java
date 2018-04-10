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

    double negativeOrPositive(int bit, int number)
    {
    	if (bit == 0)
    	{
    		return number;
    	}
    	return -number;
    }
    
    @Override
    public double getFitness()
    {
    	if (getNumber(10, 17) == 0 || getNumber(28, 35) == 0 || getNumber(46, 53) == 0)
    	{
    		return 0;
    	}
    	
    	double A = (negativeOrPositive(getNumber(0, 0),getNumber(1, 8)) /  negativeOrPositive(getNumber(9, 9),getNumber(10, 17)));
    	double B = (negativeOrPositive(getNumber(18,18), getNumber(19, 26)) / negativeOrPositive(getNumber(27,27), getNumber(28, 35)));
    	double C = (negativeOrPositive(getNumber(36,36), getNumber(37, 44)) / negativeOrPositive(getNumber(45,45), getNumber(46, 53)));
    	
				
		/*
    	System.out.println(getNumber(0, 0));
    	System.out.println(getNumber(9, 9));
    	System.out.println(getNumber(18, 18));
    	System.out.println(getNumber(27, 27));
    	System.out.println(getNumber(36, 36));
    	System.out.println(getNumber(45, 45));
    	*/
    	
    	double result = A*x + B*y + C;
    	
    	
    	System.out.println("("+ (negativeOrPositive(getNumber(0, 0),getNumber(1, 8))) +  "/" + negativeOrPositive(getNumber(9, 9),getNumber(10, 17)) + ") (" + x + ")"
				+ " + ("+ negativeOrPositive(getNumber(18,18), getNumber(19, 26)) +  "/" + negativeOrPositive(getNumber(27,27), getNumber(28, 35))+ ") (" + y + ")"
				+ " + (" + negativeOrPositive(getNumber(36,36), getNumber(37, 44)) +  "/" + negativeOrPositive(getNumber(45,45), getNumber(46, 53)) + ") = " + result );
    	
    	return result;
    	
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