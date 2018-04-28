
public abstract class Piece 
{
	// The piece's row and column on the board could be stored here.
	// However, that would mean storing it in two places...here, and
	// implicit in the piece's location in the board array.  The design
	// decision could be made either way.
	
	private PieceColor color;
	
	public Piece (PieceColor color)
	{
		this.color = color;
	}
	
	public PieceColor getColor ()
	{
		return color;
	}
	
	public String getSymbol ()
	{
		// I'm not particularly happy with the piece needing to know
		// all the colors that are available.  That means if I want
		// to support more than two players, I need to come back and 
		// change this.  The alternative is a more complicated design,
		// though, which would be harder to understand.
		
		if (color == PieceColor.White)
			return "1";
		
		return "2";
	}
	
	// Overridden by subclasses to provide piece specific logic
	public abstract boolean isValidMove (int startRow, int startCol, int endRow, int endCol);
	public abstract boolean needsClearPath ();
	public abstract boolean getsPromoted ();
}
