import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MinesweeperJava {

	
	/*
	rows = []

			print("first, we will take manual input:")

			height = input()
			width = input()

			for i in range(0, int(height)):
			    rows.append(input())

			count = 0
			for i in range(0, int(height)):
			    #print("i:", i)
			    for y in range(0, int(width)):
			        if(rows[i][y] == "*"):
			            print('*', end='')
			            continue
			        if(i == 0):
			            if(y > 0):
			                if(rows[i][y-1] == '*'):
			                    count = count + 1
			            if(y < int(width)-1):
			                if(rows[i][y+1] == '*'):
			                    count = count + 1
			                    if (count > 0):
			                        print(count, end='')
			                    else:
			                        print('teret ', end='')
			                    
			            #print(count, end='')
			        elif(i > 0 and i < int(height)-1):
			            1 + 1
			            #print("middle row  ", end='')
			        else:
			            1 + 1
			            #print("last row  ", end='')
			    
			print('\n', end='')
			print(rows)

*/
	
	public static void main(String[] args) throws IOException {
		System.out.println("let's input");
		ArrayList<String> rows = new ArrayList<String>();
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		String input = new String();
		String height = reader.readLine();
		String width = reader.readLine();
		
		
		for(int i = 0; i <  Integer.parseInt(height); i++)
		{
			rows.add(reader.readLine());
		}
		
		
		
		int count = 0;
				
		for (int i = 0; i < Integer.parseInt(height); i++)
		{
			for (int y = 0; y < Integer.parseInt(width); y++)
			{
				if (rows.get(i).charAt(y) == '*')
				{
					continue;
				}
				else
				{
					if(i == 0) //first row
					{
						
						System.out.println("first row");
						
						if(y == 0)
						{
							if(rows.get(i).charAt(y) == '*')
							{
								count++;
							}
						}
						else if (y > 0 && y < Integer.parseInt(width) - 1)
						{
							if(rows.get(i).charAt(y-1) == '*')
							{
								count++;
							}
							if(rows.get(i).charAt(y) == '*')
							{
								count++;
							}
							
						} else
						{
							if(rows.get(i).charAt(y-1) == '*')
							{
								count++;
							}
						}
						
						String taco = "hello";
						taco[5] = 'h'
						rows.get(i).charAt(y) = count;
						count = 0;
					}
					else if (i > 0 && i < Integer.parseInt(height) - 1)
					{
						//middle row
						System.out.println("middle row");
					}
					else
					{
						//bottom row
						System.out.println("bottom row");
					}
				}
			}
		}
		
		
		
				        

	}

}
