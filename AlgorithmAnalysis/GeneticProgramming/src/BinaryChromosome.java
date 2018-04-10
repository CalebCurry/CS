import java.util.Random;

public class BinaryChromosome implements Chromosome
{
    protected int [] bits;
    private Random random = new Random ();
    
    public BinaryChromosome (int numbits)
    {
        bits = new int [numbits];
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
        return crossover ((BinaryChromosome) other, new BinaryChromosome (bits.length));
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
}