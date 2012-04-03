/**
 * 
 */
package ljy.game.tasks;

import haframework.draw.Sprite;
import haframework.draw.SpriteFactory;
import haframework.events.TouchEvent;
import haframework.gui.UIButton;
import haframework.gui.UICheckBox;
import haframework.gui.UIManager;
import haframework.gui.UIWidget;
import haframework.gui.uievent.IButtonCallback;
import haframework.gui.uievent.ICheckBoxCallback;
import haframework.task.Task;

import java.util.Vector;

import ljy.game.TaskSet;
import ljy.game.chess.Chess;
import ljy.game.chess.ChessBoard;
import android.graphics.Point;

/**
 * @author hjb
 *
 */
public class TaskPCGame extends Task implements IButtonCallback, ICheckBoxCallback
{
	//-------------------------------------- static member --------------------------------------
	
	static private final float BOARD_X = 20;
	static private final float BOARD_Y = 120;
	static private final float BOARD_SIZE = 280;
	static private final float BOARD_X2 = 38;
	static private final float BOARD_Y2 = 138;
	static private final float BOARD_SIZE2 = 262;
	
	//-------------------------------------- private member --------------------------------------
	
	private UIWidget m_uiRoot = null;
	private UIButton m_btnBack = null;
	private UICheckBox m_cbBlack = null;
	private UICheckBox m_cbWhite = null;
	
	// graphic
	private Sprite m_bg = null;
	private Sprite m_imgChessboard = null;
	private Sprite m_imgChessBlack = null;
	private Sprite m_imgChessWhite = null;
	private Sprite m_imgLine = null;
	
	private ChessBoard m_chessboard = null;
	private Point m_pendingChess = null;
	private int m_curTurnChess = Chess.CHESS_BLACK;
	
	//-------------------------------------- public function --------------------------------------

	/**
	 * @desc	constructor
	 */
	public TaskPCGame()
	{
	}
	
	@Override
	public void vBegin()
	{
		// init background
		m_bg = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_bg.SetUV( 160, 5, 20, 20 );
		
		// init ui stuff
		m_uiRoot = new UIWidget();
		
		m_btnBack = new UIButton( ljy.game.R.drawable.menus, 5, 140, 90, 140, 39, 19 );
		m_btnBack.SetPos( 12, 12 );
		m_btnBack.SetCallback( this );
		m_btnBack.SetParent( m_uiRoot );
		
		m_cbBlack = new UICheckBox( ljy.game.R.drawable.menus, 180, 80, 180, 60, 39, 19 );
		m_cbBlack.SetPos( 30, 50 );
		m_cbBlack.SetCallback( this );
		m_cbBlack.SetParent( m_uiRoot );
		m_cbBlack.Enable( false );
		
		m_cbWhite = new UICheckBox( ljy.game.R.drawable.menus, 230, 80, 230, 60, 39, 19 );
		m_cbWhite.SetPos( 30, 80 );
		m_cbWhite.SetCallback( this );
		m_cbWhite.SetParent( m_uiRoot );
		m_cbWhite.Enable( false );
		
		UIManager.Singleton().AddToRoot( m_uiRoot );
		
		// init game graphic
		m_imgChessboard = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgChessboard.SetUV( 10, 170, 161, 161 );
		m_imgChessBlack = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgChessBlack.SetUV( 140, 120, 20, 20 );
		m_imgChessBlack.SetAnchor( 10, 10 );
		m_imgChessWhite = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgChessWhite.SetUV( 140, 90, 20, 20 );
		m_imgChessWhite.SetAnchor( 10, 10 );
		m_imgLine = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgLine.SetUV( 190, 5, 20, 20 );
		
		// init the game logic 
		m_chessboard = new ChessBoard();
		
		gameStart();
	}

	@Override
	public void vEnd()
	{
		m_uiRoot.SetParent( null );
	}
	
	@Override
	public void vMain( float elapsed ){}
	
	@Override
	public void vDraw( float elapsed )
	{
		int i,j;
		
		m_bg.Draw( 0, 0, 320, 480 );
		
		// draw chessboard
		m_imgChessboard.Draw( BOARD_X, BOARD_Y, BOARD_SIZE, BOARD_SIZE );
		
		// draw chesses
		int chessData[][] = m_chessboard.GetChessData();
		int chess;
		for( i = 0; i < ChessBoard.MAX_LINE; i++ )
		{
			for( j = 0; j < ChessBoard.MAX_LINE; j++ )
			{
				chess = chessData[i][j];
				Point pt = boardToScreen( i, j );
				
				if( chess == Chess.CHESS_BLACK )
				{
					m_imgChessBlack.Draw( pt.x, pt.y );
				}
				
				if( chess == Chess.CHESS_WHITE )
				{
					m_imgChessWhite.Draw( pt.x, pt.y );
				}
			}
		}
		
		// draw pending chess
		if( m_pendingChess != null )
		{
			Point pt = boardToScreen( m_pendingChess.x, m_pendingChess.y );
			
			m_imgLine.Draw( BOARD_X2 - 3, pt.y - 3, BOARD_SIZE2 - 12, 6 );
			m_imgLine.Draw( pt.x - 3, BOARD_Y2 - 3, 6, BOARD_SIZE2 - 12 );
			
			if( m_curTurnChess == Chess.CHESS_BLACK )
			{
				m_imgChessBlack.Draw( pt.x, pt.y );
			}
			else if( m_curTurnChess == Chess.CHESS_WHITE )
			{
				m_imgChessWhite.Draw( pt.x, pt.y );
			}
		}
	}

	@Override
	public boolean vOnTouchEvent( Vector<TouchEvent> events )
	{
		TouchEvent evt = events.get( 0 );
		
		switch( evt.Type )
		{
		case TouchEvent.TOUCH:
			startPut( evt.X, evt.Y );
			break;
		case TouchEvent.MOVE:
			chessMove( evt.X, evt.Y );
			break;
		case TouchEvent.UNTOUCH:
			putChess( evt.X, evt.Y );
			break;
		default:
			break;
		}
		
		return false;
	}

	public void onButtonDown( UIButton btn ){}
	
	public void onButtonUp( UIButton btn ){}
	
	public void onButtonClick( UIButton btn )
	{
		if( btn == m_btnBack )
		{
			this.Stop();
			TaskSet._mainMenuTask.Start( 0 );
		}
		
		//TODO
	}
	
	public void onCheckBoxCheck( UICheckBox checkbox ){}
	
	//------------------------------------------- private function --------------------------------------------------
	
	private void gameStart()
	{
		m_pendingChess = null;
		m_curTurnChess = Chess.CHESS_BLACK;		// black first
		
		m_cbBlack.Check( true );
		m_cbWhite.Check( false );
		
	}
	
	// put the chess to the board
	private void startPut(int x, int y)
	{
		if( x < BOARD_X || x > ( BOARD_X + BOARD_SIZE ) || y < BOARD_Y || y > ( BOARD_Y + BOARD_SIZE ) )
		{
			return;
		}
		
		Point chessPos = screenToBoard( x, y );

		m_pendingChess = chessPos;
	}
	
	// chess move
	private void chessMove(int x, int y)
	{
		if( m_pendingChess != null )
		{
			m_pendingChess = screenToBoard( x, y );
		}
	}
	
	// final put chess
	private void putChess(int x, int y)
	{
		if( m_pendingChess == null )
		{
			return;
		}
		
		Point pt = screenToBoard( x, y );
		
		if( m_chessboard.PutChess( m_curTurnChess, pt.x, pt.y ) == true )
		{
			// TODO Auto-generated method stub
			
			m_curTurnChess = 3 - m_curTurnChess;	//[HACK]
		
			m_cbBlack.Check( !m_cbBlack.IsChecked() );
			m_cbWhite.Check( !m_cbWhite.IsChecked() );
		}
		
		m_pendingChess = null;
		
	}
	
	//--------------------------------------------------- private function -----------------------------------------------------
	
	private Point screenToBoard(int x, int y)
	{
		Point pt = new Point();
		
		if( x < BOARD_X2 )
		{
			pt.x = 0;
		}
		else if( x > ( BOARD_X2 + BOARD_SIZE2 ) )
		{
			pt.x = ChessBoard.MAX_LINE - 1;
		}
		else
		{
			pt.x = (int)( ( (float)x - BOARD_X2 ) / ( BOARD_SIZE2 / (float)ChessBoard.MAX_LINE ) );
		}
		
		if( y < BOARD_Y2 )
		{
			pt.y = 0;
		}
		else if( y > ( BOARD_Y2 + BOARD_SIZE2 ) )
		{
			pt.y = ChessBoard.MAX_LINE - 1;
		}
		else
		{
			pt.y = (int)( ( (float)y - BOARD_Y2 ) / ( BOARD_SIZE2 / (float)ChessBoard.MAX_LINE ) );
		}
		
		return pt;
	}
	
	private Point boardToScreen(int x, int y)
	{
		Point pt = new Point();
		
		float cellSize = BOARD_SIZE2 / (float)ChessBoard.MAX_LINE;
		
		pt.x = (int)( BOARD_X2 + (float)x * cellSize );
		pt.y = (int)( BOARD_Y2 + (float)y * cellSize );
		
		return pt;
	}

}
