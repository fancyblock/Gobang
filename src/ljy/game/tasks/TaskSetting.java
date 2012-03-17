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
import haframework.task.Task;

/**
 * @author hjb
 *
 */
public class TaskSetting extends Task implements IButtonCallback
{
	protected Sprite m_bg = null;
	protected UIWidget m_uiRoot = null;
	protected UIButton m_btnBack = null;
	
	/**
	 * @desc	constructor
	 */
	public TaskSetting()
	{
	}
	
	@Override
	public void vBegin()
	{
		m_bg = SpriteFactory.Singleton().CreateSprite( ljy.game.R.drawable.menus );
		m_bg.SetUV( 160, 5, 20, 20 );
		
		m_uiRoot = new UIWidget();
		
		m_btnBack = new UIButton( ljy.game.R.drawable.menus, 5, 140, 90, 140, 39, 19 );
		m_btnBack.SetPos( 12, 12 );
		m_btnBack.SetCallback( this );
		m_btnBack.SetParent( m_uiRoot );
		
		UIManager.Singleton().AddToRoot( m_uiRoot );
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
}
