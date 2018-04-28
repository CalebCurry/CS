
public class Board 
{
	private final int DefaultBoardSize = 8;
	private Piece [][] board = null;

	public Board ()
	{
		board = new Piece [DefaultBoardSize][DefaultBoardSize];
	}
	
	public Board (int size)
	{
		board = new Piece [size][size];
	}
	
	public int getWidth ()
	{
		return board[0].length;
	}
	
	public int getHeight ()
	{
		return board.length;
	}
	
	public boolean isValidMove (PieceColor player, int startRow, int startCol, int endRow, int endCol)
	{
		if (! isValidCoords(startRow, startCol) || ! isValidCoords(endRow, endCol))
			return false;
		
		if (! isPlayersPiece (player, board[startRow][startCol]))
			return false;
		
		if (! board[startRow][startCol].isValidMove (startRow, startCol, endRow, endCol))
			return false;
		
		if (isBlockedMove (startRow, startCol, endRow, endCol))
			return false;
		
		if (board[endRow][endCol] != null && board[endRow][endCol].getColor() == player)
			return false;
		
		return true;
	}

	public boolean isPlayersPiece (PieceColor color, Piece piece)
	{
		if (piece == null)
			return false;
		
		return piece.getColor() == color;
	}
	
	public void print ()
	{
		System.out.print ("   ");
		
		for (int col = 0; col < board[0].length; col++)
			System.out.print ((col) + "  ");
		
		System.out.println ();
		
		for (int row = 0; row < board.length; row++)
		{
			System.out.print((row) + " |");
			
			for (int col = 0; col < board[row].length; col++)
			{
				if (board[row][col] != null)
					System.out.print(board[row][col].getSymbol ()+" ");
				else
					System.out.print("   ");
			}
			
			System.out.println ();
		}
	}
	
	public int countPieces (PieceColor player)
	{
		int total = 0;
		
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
			{
				if (board[row][col] == null)
					continue;
				
				if (player == board[row][col].getColor())
					total++;
			}
		
		return total;
	}
	
	public Piece getPiece (int row, int col)
	{
		if (isValidCoords (row, col))
			return board[row][col];
		
		return null;
	}
	
	public void setPiece (int row, int col, Piece piece)
	{
		if (isValidCoords (row, col))
			board [row][col] = piece;
	}
	
	public boolean isValidCoords (int row, int col)
	{
		if (row >= 0 && row < board.length)
			if (col >= 0 && col < board[row].length)
				return true;
		
		return false;
	}
	
	public boolean isBlockedMove (int startRow, int startCol, int endRow, int endCol)
	{
		// If the piece doesn't care about a clear path, let it go on its way
		if (! board[startRow][startCol].needsClearPath())
			return false;
		
		// Everyone else moves in straight lines, just
		// with different increments between successive
		// squares
		int rowInc = calculateIncrement (startRow, endRow);
		int colInc = calculateIncrement (startCol, endCol);
	
		// Start one square in on the path, so we don't think the 
		// original piece is blocking itself
		startRow += rowInc;
		startCol += colInc;
		
		while (! atEndOfPath (startRow, startCol, endRow, endCol))
		{
			if (getPiece (startRow, startCol) != null)
				return true;
			
			startRow += rowInc;
			startCol += colInc;
		}
		
		return false;
	}
	
	private boolean atEndOfPath (int startRow, int startCol, int endRow, int endCol)
	{
		return (startRow == endRow) && (startCol == endCol);
	}
	
	private int calculateIncrement (int start, int end)
	{
		if (end - start > 0)
			return 1;
		
		if (end - start < 0) 
			return -1;
		
		return 0;
	}
}
