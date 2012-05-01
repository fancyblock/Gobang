/**
 * 
 */
package ljy.game.ingame;

import haframework.draw.Sprite;
import haframework.draw.SpriteFactory;
import haframework.task.Action;

/**
 * @author hjb
 *
 */
public class AniWhiteTurn extends Action
{
	private Sprite m_imgTxt = null;
	private float m_xPos = 0;
	private float m_speed = 0;
	private int m_state = 0;
	
	/**
	 * @desc	constructor
	 */
	public AniWhiteTurn()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void vEnter()
	{
		m_imgTxt = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_imgTxt.SetUV( 180, 250, 138, 33 );
		
		m_xPos = -138.0f;
		m_speed = 0.0f;
		m_state = 0;
	}
	
	public void vLeave()
	{
		//TODO 
	}
	
	public boolean vUpdate( float elapsed )
	{
		if( m_state == 0 )
		{
			if( m_xPos < 91 )
			{
				m_xPos += m_speed;
				m_speed += 0.3f;
			}
			else
			{
				m_state = 1;
				m_speed = 0;
			}
		}
		
		if( m_state == 1 )
		{
			if( m_speed < 100 )
			{
				m_speed += 1;
			}
			else
			{
				m_state = 2;
				m_speed = 0;
			}
		}
		
		if( m_state == 2 )
		{
			if( m_xPos < 320 )
			{
				m_xPos += m_speed;
				m_speed += 0.3f;
			}
			else
			{
				return false;
			}
		}
		
		return true; 
	}
	
	public void vDraw( float elapsed )
	{
		m_imgTxt.Draw( m_xPos, 410 );
	}

}
