import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MinesweeperJava {

	public static char exists(char[][] rows, int row, int column)
	{
		if(row < 0 || column < 0 || row == rows.length || column == rows[0].length)
		{
			return 'C';	
		} else 
		{ 
			return rows[row][column];
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("let's input");
		//ArrayList<String> rows = new ArrayList<String>();
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		int height = Integer.parseInt(reader.readLine());
		int width = Integer.parseInt(reader.readLine());
		
		char[][] rows = new char[height][width];
		
		for(int i = 0; i < height; i++)
		{
			String row = reader.readLine();
			
			for (int j = 0; j < width; j++)
			{
				rows[i][j] = row.charAt(j);
			}
		}
		
		for(int i = 0; i < height; i++)
		{	
			for (int j = 0; j < width; j++)
			{
				System.out.print(rows[i][j]);
			}

			System.out.println("");
		}
		
		System.out.println("~~~~~");
		
		
		int count = 0;
				
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if(rows[i][j] != '*')
				{
					if(exists(rows,i-1, j-1) == '*') count++;
					if(exists(rows, i-1, j) == '*') count++;
					if(exists(rows, i-1, j + 1) == '*') count++;
					if(exists(rows, i, j-1) == '*') count++;
					if(exists(rows, i, j+1) == '*') count++;
					if(exists(rows, i+1, j-1) == '*') count++;
					if(exists(rows, i+1, j) == '*') count++;
					if(exists(rows, i+1, j+1) == '*') count++;
				
					if (count != 0)
					{
						Character theNumber = Character.forDigit(count, 10);
						rows[i][j] = theNumber;
					}
					count = 0;
				}
			}
		}
		for(int i = 0; i < height; i++)
		{	
			for (int j = 0; j < width; j++)
			{
				System.out.print(rows[i][j]);
			}

			System.out.println("");
		}
	}

}
