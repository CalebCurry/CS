
public class King extends Piece
{
	public King (PieceColor color)
	{
		super(color);
	}
	
	public String getSymbol ()
	{
		return "K"+super.getSymbol ();
	}

	public boolean needsClearPath ()
	{
		// Since the king only moves one space at at time,
		// there is no need for a clear path.  So no sense
		// wasting time checking for it.
		return false;
	}
	
	public boolean getsPromoted ()
	{
		return false;
	}
	
	public boolean isValidMove (int startRow, int startCol, int endRow, int endCol)
	{
		// Not worrying about castling for this version
		
		int rowDistance = Math.abs(startRow - endRow);
		int colDistance = Math.abs(startCol - endCol);
		
		if (rowDistance != 1 && rowDistance != 0 && colDistance != 1 && colDistance != 0)
			return false;
		
		return true;
	}
}
