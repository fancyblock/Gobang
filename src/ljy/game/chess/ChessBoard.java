package ljy.game.chess;

public class ChessBoard 
{
	static public final int MAX_LINE = 15;
	
	private int m_chessData[][] = null;
	
	/**
	 * @desc	constructor
	 */
	public ChessBoard()
	{
		m_chessData = new int[MAX_LINE][MAX_LINE];
		clean();
	}
	
	/**
	 * @desc	put a chess to the board
	 * @param	type
	 * @param	xPos
	 * @param	yPos
	 * @return
	 */
	public boolean PutChess( int type, int xPos, int yPos )
	{
		if( m_chessData[xPos][yPos] != Chess.CHESS_BLANK )
		{
			return false;
		}
		
		m_chessData[xPos][yPos] = type;
		
		return true;
	}

	/**
	 * @desc	return the chessData
	 * @return
	 */
	public int[][] GetChessData()
	{
		return m_chessData;
	}
	
	// clean the chessboard
	private void clean()
	{
		for( int i = 0; i < MAX_LINE; i++ )
		{
			for( int j = 0; j < MAX_LINE; j++ )
			{
				m_chessData[i][j] = Chess.CHESS_BLANK;
			}
		}
	}
}
