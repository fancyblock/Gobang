/**
 * 
 */
package ljy.game.chess;

import android.graphics.Point;

/**
 * @author hejiabin
 *
 */
public class GameTree 
{
	//--------------------------------- static member -------------------------------------

	static public final int MAX_LINE = 15;
	
	static public final int DIR_0 = 0;
	static public final int DIR_1 = 1;
	static public final int DIR_2 = 2;
	static public final int DIR_3 = 3;
	
	static public final Point DIR_LIB[] = { new Point( 1, 0 ), new Point( 1, 1 ), new Point( 0, 1 ), new Point( -1, 1 ) };
	
	//--------------------------------- private member -------------------------------------

	//TODO 
	
	//--------------------------------- public function -------------------------------------
	
	/**
	 * @desc	constructor
	 */
	public GameTree() 
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @desc	return the next step of the chess
	 * @param	board
	 * @param	nextType
	 * @return
	 */
	static public Point GetNextStep( ChessBoard board, int nextType )
	{
		Point pt = new Point();
		
		int maxMark = -1;
		
		int chessData[][] = board.GetChessData();
		
		for( int i = 0; i < ChessBoard.MAX_LINE; i++ )
		{
			for( int j = 0; j < ChessBoard.MAX_LINE; j++ )
			{
				int type = chessData[i][j];
				
				if( type == Chess.CHESS_BLANK )
				{
					int mark = getPosMark( chessData, i, j );
					
					if( mark > maxMark )
					{
						maxMark = mark;
						
						pt.x = i;
						pt.y = j;
					}
				} 
			}
		}
		
		return pt;
	}
	
	
	/**
	 * @desc	return the position mark 
	 * @param	data
	 * @param	posX
	 * @param	posY
	 * @return
	 */
	static protected int getPosMark( int[][] data, int posX, int posY )
	{
		int mark = 0;
		
		int lineNum = 0;
		
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_BLACK, DIR_0 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 ) * 3;
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_BLACK, DIR_1 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 ) * 3;
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_BLACK, DIR_2 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 ) * 3;
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_BLACK, DIR_3 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 ) * 3;
		
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_WHITE, DIR_0 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 );
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_WHITE, DIR_1 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 );
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_WHITE, DIR_2 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 );
		lineNum = getLineNum( data, posX, posY, Chess.CHESS_WHITE, DIR_3 );
		mark += ( lineNum - 1 ) * ( lineNum - 1 );
		
		return mark;
	}
	
	//--------------------------------- private function -------------------------------------
	
	static protected int getLineNum( int[][] data, int posX, int posY, int chessType, int dir )
	{
		int count = 1;
		
		Point curDir = DIR_LIB[dir];
		
		int curPosX;
		int curPosY;
		
		// forward
		curPosX = posX;
		curPosY = posY;
		while( true )
		{
			curPosX += curDir.x;
			curPosY += curDir.y;
			
			if( curPosX >= 0 && curPosX < MAX_LINE && curPosY >= 0 && curPosY < MAX_LINE &&
					data[curPosX][curPosY] == chessType )
			{
				count++;
			}
			else
			{
				break;
			}
		}
		
		// back
		curPosX = posX;
		curPosY = posY;
		while( true )
		{
			curPosX -= curDir.x;
			curPosY -= curDir.y;
			
			if( curPosX >= 0 && curPosX < MAX_LINE && curPosY >= 0 && curPosY < MAX_LINE &&
					data[curPosX][curPosY] == chessType )
			{
				count++;
			}
			else
			{
				break;
			}
		}
		
		return count;
	}

}
