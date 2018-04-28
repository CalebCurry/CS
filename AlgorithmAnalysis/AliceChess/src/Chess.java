import java.awt.Color;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Chess 
{
	private int player1Wins = 0;
	private int player2Wins = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{	
		Chess game = new Chess ();
		game.doThings ();
	}
	
	public void doThings() throws NumberFormatException, IOException
	{
		//based on a pre-order depth first traversal (the root node is visited before children)
		//root of tree will be starting position
		//then branch to all possible 	
		
		//board.print();
		//King piece = new King(PieceColor.White);
		//board.setPiece(0, 0, piece);
		//System.out.println("");
		//board.print();
		
		System.out.println("Lets input");
		//ArrayList<String> rows = new ArrayList<String>();
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		int numBoards = Integer.parseInt(reader.readLine());
		
		ArrayList<Board> boards = new ArrayList<Board>();
		String[] lines = new String[8];
		for (int i = 0; i < numBoards; i++)
		{
			for (int x = 0; x < lines.length; x++)
			{
				lines[x] = reader.readLine();
			}
			boards.add(getBoard(lines));
			//board.print();
			if(numBoards > 1)
			{
				reader.readLine();
			}
		}
		
		for (int i = 0; i< numBoards; i++)
		{
			solveBoard(i, boards.get(i));
		}
	}
	public Board getBoard(String[] lines)
	{
		Board board = new Board();
		for (int i = 0; i < lines.length; i++)
		{
			System.out.println(lines[1].length());
			for(int x = 0; x < lines[1].length(); x += 2)
			{
				Piece piece = getPiece( lines[i].charAt(x));
				if (piece != null)
				{
					board.setPiece(i, x/2, piece);
				}	
			}
		}
		return board;
	}

	public Piece getPiece(char pieceCode)
	{
		if(pieceCode == 'P')
		{
			return new Pawn(PieceColor.White);
		} else if (pieceCode == 'B')
		{
			return new Bishop(PieceColor.White);
		} else if (pieceCode == 'R')
		{
			return new Rook(PieceColor.White);
		} else if (pieceCode == 'N')
		{
			return new Knight(PieceColor.White);
		} else if (pieceCode == 'Q')
		{
			return new Queen(PieceColor.White);
		} else if (pieceCode == 'K')
		{
			return new King(PieceColor.White);
		}
		else 
		{
			return null;
		}
		
		
	}
	public Boolean solveBoard(int boardNumber, Board board)
	{
		return true;
	}
	
	public void go ()
	{
		int choice = 0;
		
		while (choice != 3)
		{
			choice = getMenuChoice();
			
			switch (choice)
			{
				case 1:
					playGame ();
					break;
					
				case 2:
					displayResults ();
					break;
			}
		}
	}
	
	public void playGame ()
	{
		Board board = new Board ();
		int player = 0;
		
		placePieces (board);
		board.print();
		
		while (! gameOver (board))
		{
			playerMove (board, player);
			board.print ();
			
			// Switch to the next player
			// Behold the power of modulus!
			player = (player + 1) % 2;
		}
		
	}

	public void displayResults ()
	{
		System.out.println ();
		System.out.println ("Total games played: " + (player1Wins + player2Wins));
		System.out.println ("Player 1 won: " + player1Wins);
		System.out.println ("Player 2 won: " + player2Wins);
		System.out.println ();
	}
	
	public int getMenuChoice ()
	{
		Scanner scanner = new Scanner (System.in);
		int choice = 0;

		do
		{
			showMenu ();
			choice = scanner.nextInt ();
		}
		while (choice < 1 && choice > 3);
		
		return choice;
	}
	
	public void showMenu ()
	{
		System.out.println ("1. Play Game");
		System.out.println ("2. Show Results");
		System.out.println ("3. Quit");
		System.out.print ("Enter your choice (1-3): ");
	}
	
	// All the code above this point did not have to change between
	// ChessQuito and Chess
	
	public void playerMove (Board board, int player)
	{
		Scanner input = new Scanner (System.in);
		
		String playerName = "Player 1 (White)";
		PieceColor playerColor = PieceColor.White;
		
		while (true)
		{
			int row = -1;
			int col = -1;
			
			if (player == 1)
			{
				playerName = "Player 2 (Black)";
				playerColor = PieceColor.Black;
			}
			
			System.out.println (playerName + ", pick which piece to move");
			
			while ((row == -1 && col == -1) || ! board.isValidCoords(row, col) || ! board.isPlayersPiece (playerColor, board.getPiece(row, col)))
			{
				System.out.print ("Enter the row for the piece: ");
				row = input.nextInt ();
				System.out.print ("Enter the column for the piece: ");
				col = input.nextInt ();
			}
			
			int endRow = -1;
			int endCol = -1;
			
			while ((endRow == -1 && endCol == -1) || ! board.isValidCoords(endRow, endCol))
			{
				System.out.print ("Enter the end row for the move: ");
				endRow = input.nextInt ();
				System.out.print ("Enter the end column for the move: ");
				endCol = input.nextInt ();
			}
			
			if (board.isValidMove(playerColor, row, col, endRow, endCol))
			{
				Piece piece = board.getPiece(row, col);
				
				board.setPiece(endRow, endCol, piece);
				board.setPiece(row, col, null);
				
				// Handle promotion of pieces that get to the far end of the board
				
				if (piece.getColor() == PieceColor.White && endRow == 0 && piece.getsPromoted())
				{
					board.setPiece(endRow, endCol, new Queen (PieceColor.White));
				}
				
				if (piece.getColor() == PieceColor.Black && endRow == 7 && piece.getsPromoted())
				{
					board.setPiece(endRow, endCol, new Queen (PieceColor.Black));
				}
				
				break;
			}
			else
				System.out.println ("Invalid move, try again");
		}
	}
	
	public void placePieces (Board board)
	{
		// Chess has predefined starting positions for pieces
		
		// Black pawns
		for (int col = 0; col < 8; col++)
			board.setPiece (1, col, new Pawn (PieceColor.Black));
		
		// White pawns
		for (int col = 0; col < 8; col++)
			board.setPiece (6, col, new Pawn (PieceColor.White));
		
		board.setPiece(0, 0, new Rook (PieceColor.Black));
		board.setPiece(0, 7, new Rook (PieceColor.Black));
		board.setPiece(7, 0, new Rook (PieceColor.White));
		board.setPiece(7, 7, new Rook (PieceColor.White));
		
		board.setPiece(0, 1, new Knight (PieceColor.Black));
		board.setPiece(0, 6, new Knight (PieceColor.Black));
		board.setPiece(7, 1, new Knight (PieceColor.White));
		board.setPiece(7, 6, new Knight (PieceColor.White));
		
		board.setPiece(0, 2, new Bishop (PieceColor.Black));
		board.setPiece(0, 5, new Bishop (PieceColor.Black));
		board.setPiece(7, 2, new Bishop (PieceColor.White));
		board.setPiece(7, 5, new Bishop (PieceColor.White));
		
		board.setPiece(0, 3, new Queen (PieceColor.Black));
		board.setPiece(0, 4, new King (PieceColor.Black));
		board.setPiece(7, 3, new Queen (PieceColor.White));
		board.setPiece(7, 4, new King (PieceColor.White));
		
}

	public boolean gameOver (Board board)
	{
		// Need to check for both Black and White, each spot their kings can move,
		// can another piece target that spot.  If that is true for all the spots 
		// the king can move, that's checkmate

		// I'm checking every spot on the board to see which are valid moves for
		// the king.  This probably seems silly, but the idea is that the King 
		// class is the only one that should know about king movement.  If we 
		// later program a chess variant that allows the king to move in 
		// different ways, this code will still work without modification.
		
		int height = board.getHeight ();
		int width = board.getWidth ();
		
		// Find the kings
		
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				Piece piece = board.getPiece(row, col);
				
				if (piece != null && piece instanceof King)
				{
					// Found one, now check to see if it's in checkmate
					if (isCheckmate (row,col, board))
					{
						if (piece.getColor () == PieceColor.White)
						{
							System.out.println ("Player 2 wins");
							player2Wins++;
						}
						
						if (piece.getColor () == PieceColor.Black)
						{
							System.out.println ("Player 1 wins");
							player1Wins++;
						}
						
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean isCheckmate (int startRow, int startCol, Board board)
	{
		// This piece is the king
		Piece piece = board.getPiece(startRow,startCol);

		int height = board.getHeight ();
		int width = board.getWidth ();
		
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				// Here we're seeing if the king can move to this spot
				if (piece.isValidMove(startRow, startCol, row, col) && ! board.isBlockedMove(startRow, startCol, row, col))
				{
					// The king can move to this spot, see if it is not threatened by a piece of the opposite color
					if (piece.getColor() == PieceColor.White && ! isSpaceThreatened (PieceColor.Black, row, col, board))
						return false;
						
					if (piece.getColor() == PieceColor.Black && ! isSpaceThreatened (PieceColor.White, row, col, board))
						return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean isSpaceThreatened (PieceColor color, int endRow, int endCol, Board board)
	{
		int height = board.getHeight ();
		int width = board.getWidth ();
		
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
			{
				// This is another piece on the board, or null if it's an empty spot
				Piece piece = board.getPiece(row, col);
				
				// Check to see if this other piece can get to the target spot
				if (piece != null && piece.getColor() == color && piece.isValidMove(row, col, endRow, endCol) && ! board.isBlockedMove(row, col, endRow, endCol))
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
