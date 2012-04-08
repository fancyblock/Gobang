/**
 * 
 */
package ljy.game.tasks;

import ljy.game.TaskSet;
import haframework.draw.Sprite;
import haframework.draw.SpriteFactory;
import haframework.gui.UIButton;
import haframework.gui.UIManager;
import haframework.gui.UIWidget;
import haframework.gui.uievent.IButtonCallback;
import haframework.sound.SoundManager;
import haframework.task.Task;

/**
 * @author hejiabin
 *
 */
public class TaskMainMenu extends Task implements IButtonCallback
{
	protected Sprite m_bg = null;
	protected Sprite m_title = null;
	protected UIWidget m_uiRoot = null;
	protected UIButton m_btnPCMode = null;
	protected UIButton m_btnVSMode = null;
	protected UIButton m_btnAbout = null;
	protected UIButton m_btnSetting = null;
	protected UIButton m_btnHelp = null;
	
	/**
	 * @desc	constructor
	 */
	public TaskMainMenu() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void vBegin()
	{
		m_bg = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_bg.SetUV( 160, 5, 20, 20 );
		m_title = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_title.SetUV( 5, 5, 150, 29 );
		
		m_uiRoot = new UIWidget();
		
		m_btnPCMode = new UIButton( ljy.game.R.drawable.menus, 5, 40, 90, 40, 79, 19 );
		m_btnPCMode.SetPos( 121, 180 );
		m_btnPCMode.SetCallback( this );
		m_btnPCMode.SetParent( m_uiRoot );
		
		m_btnVSMode = new UIButton( ljy.game.R.drawable.menus, 5, 60, 90, 60, 79, 19 );
		m_btnVSMode.SetPos( 121, 215 );
		m_btnVSMode.SetCallback( this );
		m_btnVSMode.SetParent( m_uiRoot );
		
		m_btnAbout = new UIButton( ljy.game.R.drawable.menus, 5, 80, 90, 80, 39, 19 );
		m_btnAbout.SetPos( 140, 250 );
		m_btnAbout.SetCallback( this );
		m_btnAbout.SetParent( m_uiRoot );
		
		m_btnSetting = new UIButton( ljy.game.R.drawable.menus, 5, 100, 90, 100, 39, 19 );
		m_btnSetting.SetPos( 140, 285 );
		m_btnSetting.SetCallback( this );
		m_btnSetting.SetParent( m_uiRoot );
		
		m_btnHelp = new UIButton( ljy.game.R.drawable.menus, 5, 120, 90, 120, 39, 19 );
		m_btnHelp.SetPos( 140, 320 );
		m_btnHelp.SetCallback( this );
		m_btnHelp.SetParent( m_uiRoot );
		
		UIManager.Singleton().AddToRoot( m_uiRoot );
		
		SoundManager.Singleton().PlayBGM( "menuBgm" );
	}
	
	@Override
	public void vEnd()
	{
		m_uiRoot.SetParent( null );
	}
	
	@Override
	public void vDestroy(){}
	
	@Override
	public void vMain( float elapsed )
	{
	}
	
	@Override
	public void vDraw( float elapsed )
	{
		m_bg.Draw( 0, 0, 320, 480 );
		m_title.Draw( 86.5f, 80 );
	}

	public void onButtonDown( UIButton btn )
	{
	}
	
	public void onButtonUp( UIButton btn ){}
	
	public void onButtonClick( UIButton btn )
	{
		SoundManager.Singleton().PlaySE( "btnOver" );
		
		if( btn == m_btnPCMode )
		{
			this.Stop();
			TaskSet._pcGameTask.Start( 0 );
		}
		else if( btn == m_btnVSMode )
		{
			this.Stop();
			TaskSet._vsGameTask.Start( 0 );
		}
		else if( btn == m_btnAbout )
		{
			this.Stop();
			TaskSet._aboutTask.Start( 0 );
		}
		else if( btn == m_btnSetting )
		{
			this.Stop();
			TaskSet._settingTask.Start( 0 );
		}
		else if( btn == m_btnHelp )
		{
			this.Stop();
			TaskSet._helpTask.Start( 0 );
		}
	}
}
