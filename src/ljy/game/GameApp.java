/**
 * 
 */
package ljy.game;

import haframework.HAApp;
import haframework.sound.SoundManager;
import ljy.game.tasks.TaskGame;
import ljy.game.tasks.TaskGameOver;
import ljy.game.tasks.TaskLogo;
import ljy.game.tasks.TaskMainMenu;
import ljy.game.tasks.TaskModeMenu;

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
		TaskSet._logoTask = new TaskLogo();
		TaskSet._mainMenuTask = new TaskMainMenu();
		TaskSet._modeMenuTask = new TaskModeMenu();
		TaskSet._gameTask = new TaskGame();
		TaskSet._gameOverTask = new TaskGameOver();
		
		TaskSet._logoTask.Start( 0 );
	}
	
	@Override
	public void onDestory()
	{
		SoundManager.Singleton().StopAll();
	}

}
