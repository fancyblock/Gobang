/**
 * 
 */
package ljy.game;

import haframework.HAApp;
import haframework.sound.SoundManager;

/**
 * @author hjb
 *
 */
public class GameApp extends HAApp
{

	/**
	 * @desc	constructor
	 */
	public GameApp()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate()
	{
		//TODO
	}
	
	@Override
	public void onDestory()
	{
		SoundManager.Singleton().StopAll();
	}

}
