/**
 * 
 */
package ljy.game;

import haframework.HAApp;
import haframework.sound.SoundManager;
import ljy.game.tasks.TaskAbout;
import ljy.game.tasks.TaskGameOver;
import ljy.game.tasks.TaskHelp;
import ljy.game.tasks.TaskLogo;
import ljy.game.tasks.TaskMainMenu;
import ljy.game.tasks.TaskPCGame;
import ljy.game.tasks.TaskSetting;
import ljy.game.tasks.TaskVSGame;

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
		TaskSet._pcGameTask = new TaskPCGame();
		TaskSet._vsGameTask = new TaskVSGame();
		TaskSet._aboutTask = new TaskAbout();
		TaskSet._settingTask = new TaskSetting();
		TaskSet._helpTask = new TaskHelp();
		TaskSet._gameOverTask = new TaskGameOver();
		
		TaskSet._logoTask.Start( 0 );
	}
	
	@Override
	public void onDestory()
	{
		SoundManager.Singleton().StopAll();
	}

}
