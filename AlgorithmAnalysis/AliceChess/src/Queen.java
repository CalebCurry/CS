
public class Queen extends Piece
{
	public Queen (PieceColor color)
	{
		super(color);
	}
	
	public String getSymbol ()
	{
		return "Q"+super.getSymbol ();
	}
	
	public boolean needsClearPath ()
	{
		return true;
	}
	
	public boolean getsPromoted ()
	{
		return false;
	}
	
	public boolean isValidMove (int startRow, int startCol, int endRow, int endCol)
	{
		// Bishop movement
		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol))
			return true;
		
		// Rook movement
		if (startRow == endRow && startCol != endCol)
			return true;
		
		if (startRow != endRow && startCol == endCol)
			return true;
		
		return false;
	}
}
