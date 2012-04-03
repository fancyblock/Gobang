/**
 * 
 */
package ljy.game.tasks;

import haframework.draw.Sprite;
import haframework.draw.SpriteFactory;
import haframework.events.TouchEvent;
import haframework.gui.UIButton;
import haframework.gui.UIManager;
import haframework.gui.UIWidget;
import haframework.gui.uievent.IButtonCallback;
import haframework.task.Task;

import java.util.Vector;

import ljy.game.TaskSet;
import ljy.game.chess.ChessBoard;
import android.graphics.Point;

/**
 * @author hjb
 *
 */
public class TaskPCGame extends Task implements IButtonCallback
{
	//-------------------------------------- static member --------------------------------------
	
	static private final float BOARD_X = 20;
	static private final float BOARD_Y = 120;
	static private final float BOARD_SIZE = 280;
	static private final float BOARD_X2 = 37;
	static private final float BOARD_Y2 = 137;
	static private final float BOARD_SIZE2 = 264;
	static private final int MAX_LINE = 15;
	
	//-------------------------------------- private member --------------------------------------
	
	private UIWidget m_uiRoot = null;
	private UIButton m_btnBack = null;
	
	// graphic
	private Sprite m_bg = null;
	private Sprite m_imgChessboard = null;
	private Sprite m_imgChessBlack = null;
	private Sprite m_imgChessWhite = null;
	private Sprite m_imgLine = null;
	
	private ChessBoard m_chessboard = null;
	private Point m_pendingChess = null;
	
	//-------------------------------------- public function --------------------------------------

	/**
	 * @desc	constructor
	 */
	public TaskPCGame()
	{
		// TODO Auto-generated constructor stub
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
		
		UIManager.Singleton().AddToRoot( m_uiRoot );
		
		// init game graphic
		m_imgChessboard = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgChessboard.SetUV( 10, 170, 161, 161 );
		m_imgChessBlack = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgChessBlack.SetUV( 140, 90, 20, 20 );
		m_imgChessBlack.SetAnchor( 10, 10 );
		m_imgChessWhite = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgChessWhite.SetUV( 140, 120, 20, 20 );
		m_imgChessWhite.SetAnchor( 10, 10 );
		m_imgLine = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgLine.SetUV( 190, 5, 20, 20 );
		
		// init the game logic 
		m_chessboard = new ChessBoard();
		m_pendingChess = null;
		
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
		m_bg.Draw( 0, 0, 320, 480 );
		
		// draw chessboard
		m_imgChessboard.Draw( BOARD_X, BOARD_Y, BOARD_SIZE, BOARD_SIZE );
		
		// draw pending chess
		if( m_pendingChess != null )
		{
			Point pt = boardToScreen( m_pendingChess.x, m_pendingChess.y );
			
			m_imgLine.Draw( BOARD_X2 - 3, pt.y - 3, BOARD_SIZE2, 6 );
			m_imgLine.Draw( pt.x - 3, BOARD_Y2 - 3, 6, BOARD_SIZE2 );
			
			//TODO 
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
			//TODO 
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
		// TODO Auto-generated method stub
	
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
			pt.x = MAX_LINE - 1;
		}
		else
		{
			pt.x = (int)( ( (float)x - BOARD_X2 ) / ( BOARD_SIZE2 / (float)MAX_LINE ) );
		}
		
		if( y < BOARD_Y2 )
		{
			pt.y = 0;
		}
		else if( y > ( BOARD_Y2 + BOARD_SIZE2 ) )
		{
			pt.y = MAX_LINE - 1;
		}
		else
		{
			pt.y = (int)( ( (float)y - BOARD_Y2 ) / ( BOARD_SIZE2 / (float)MAX_LINE ) );
		}
		
		return pt;
	}
	
	private Point boardToScreen(int x, int y)
	{
		Point pt = new Point();
		
		float cellSize = BOARD_SIZE2 / (float)MAX_LINE;
		
		pt.x = (int)( BOARD_X2 + (float)x * cellSize );
		pt.y = (int)( BOARD_Y2 + (float)y * cellSize );
		
		return pt;
	}

}
