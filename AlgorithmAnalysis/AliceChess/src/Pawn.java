
public class Pawn extends Piece
{
	public Pawn (PieceColor color)
	{
		super(color);
	}
	
	public String getSymbol ()
	{
		return "p"+super.getSymbol ();
	}

	public boolean needsClearPath ()
	{
		// Since the pawn only moves one space at at time,
		// there is no need for a clear path.  So no sense
		// wasting time checking for it.
		return false;
	}
	
	public boolean getsPromoted ()
	{
		return true;
	}
	
	public boolean isValidMove (int startRow, int startCol, int endRow, int endCol)
	{
		// Movement must always be forward.  This code is slightly bad design, because
		// the Pawn class shouldn't know anything about the board layout.  But because of the
		// direction of movement, the Pawn class needs to know how to decide if 
		// proposed movement is forward or not.  There are more complex ways of doing
		// this that would be better design, but this will do for now (the more complex 
		// ways would be more adaptable, such as if we wanted a larger board with a four
		// player version of chess...with this slightly flawed design, we'd have to 
		// change the Pawn class to support more than two players)
		
		int rowDirection = endRow - startRow;
		int colDirection = endCol - startCol;
		
		if (colDirection != 0)
			return false;
		
		if ((getColor () == PieceColor.White && rowDirection != -1) || (getColor () == PieceColor.Black && rowDirection != 1))
			return false;
		
		return true;
	}
}
