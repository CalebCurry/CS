
public class Bishop extends Piece
{
	public Bishop (PieceColor color)
	{
		super(color);
	}
	
	public String getSymbol ()
	{
		return "B"+super.getSymbol ();
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
		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol))
			return true;
		
		return false;
	}
}
