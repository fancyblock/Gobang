/**
 * 
 */
package ljy.game.tasks;

import haframework.draw.Sprite;
import haframework.draw.SpriteFactory;
import haframework.sound.SoundManager;
import haframework.task.Task;
import ljy.game.TaskSet;

/**
 * @author hejiabin
 *
 */
public class TaskLogo extends Task 
{
	protected Sprite m_bg = null;
	protected float m_time = 0.0f;

	/**
	 * @desc	constructor
	 */
	public TaskLogo() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void vBegin()
	{
		m_bg = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.logo );
		m_bg.SetUV( 0, 0, 1.0f, 1.0f );
		
		m_time = 0.0f;
		
		SoundManager.Singleton().LoadSound( ljy.game.R.raw.btnover, "btnOver" );
		SoundManager.Singleton().LoadSoundBGM( ljy.game.R.raw.menubgm, "menuBgm" );
	}
	
	@Override
	public void vMain( float elapsed )
	{
		m_time += 0.1f;
		
		if( m_time > 20.0f )
		{
			this.Stop();
			TaskSet._mainMenuTask.Start( 0 );
		}
	}
	
	@Override
	public void vDraw( float elapsed )
	{
		m_bg.Draw( 0, 0, 320, 480 );
	}

}
