/**
 * 
 */
package ljy.game.ingame;

import haframework.draw.Sprite;
import haframework.task.Action;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * @author hejiabin
 *
 */
public class AniDropChess extends Action 
{

	public Sprite CHESS_SPR = null;
	public Point CHESS_POS = null;
	
	protected PointF m_curPos = null;
	protected float m_speed = 0.0f;
	
	/**
	 * @desc	constructor
	 */
	public AniDropChess() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void vEnter()
	{
		if( CHESS_POS != null )
		{
			m_curPos = new PointF( CHESS_POS.x, CHESS_POS.y );
		}
		
		m_speed = 0.0f;
	}
	
	@Override
	public void vLeave()
	{
		//TODO 
	}
	
	@Override
	public boolean vUpdate( float elapsed )
	{
		if( CHESS_SPR == null || CHESS_POS == null )
		{
			return false;
		}
		
		m_curPos.y += m_speed;
		m_speed += 0.02f;
		
		if( m_curPos.y > 1000 )
		{
			return false;
		}
		
		return true; 
	}
	
	@Override
	public void vDraw( float elapsed )
	{
		CHESS_SPR.Draw( m_curPos.x, m_curPos.y );
	}

}
