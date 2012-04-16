package ljy.game.chess;

import android.graphics.Point;

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
		CleanBoard();
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
	
	/**
	 * @desc	clean the chessboard
	 * @para	none
	 * @return	none
	 */
	public void CleanBoard()
	{
		for( int i = 0; i < MAX_LINE; i++ )
		{
			for( int j = 0; j < MAX_LINE; j++ )
			{
				m_chessData[i][j] = Chess.CHESS_BLANK;
			}
		}
	}

	/**
	 * @desc	if some one win the game , return the array of the chess
	 * @return
	 */
	public Point[] GetFiveLine()
	{
		Point[] pts = null;
		
		//new Point[5];
		for( int i = 0; i < MAX_LINE; i++ )
		{
			for( int j = 0; j < MAX_LINE; j++ )
			{
				int type = m_chessData[i][j];
				
				if( type != Chess.CHESS_BLANK )
				{
					pts = checkFive( i, j, 1, 0 );
					if( pts != null )	return pts;
					
					pts = checkFive( i, j, 1, 1 );
					if( pts != null )	return pts;
					
					pts = checkFive( i, j, 0, 1 );
					if( pts != null )	return pts;
					
					pts = checkFive( i, j, -1, 1 );
					if( pts != null )	return pts;
				}
			}
		}
		
		return null;
	}
	
	//---------------------------- private function ----------------------------
	
	private Point[] checkFive( int x, int y, int incX, int incY ) 
	{
		Point[] pts = null;
		
		int type = m_chessData[x][y];
		
		int posX = x;
		int posY = y;
		int count = 0;
		
		while( true )
		{
			if( m_chessData[posX][posY] == type )
			{
				count++;
			}
			
			int nextPosX = posX + incX;
			int nextPosY = posY + incY;
			
			if( isLegalPos( nextPosX, nextPosY ) == true )
			{
				posX = nextPosX;
				posY = nextPosY;
			}
			else
			{
				break;
			}
		}
		
		if( count >= 5 )
		{
			pts = new Point[count];
			
			for( int i = 0; i < count; i++ )
			{
				pts[i] = new Point();
				pts[i].x = x + i * incX;
				pts[i].y = y + i * incY;
			}
		}
		
		return pts;
	}

	private boolean isLegalPos( int posX, int posY ) 
	{
		if( posX < 0 || posY < 0 ||
				posX >= MAX_LINE || posY >= MAX_LINE )
		{
			return false;
		}
		
		return true;
	}
	
}
