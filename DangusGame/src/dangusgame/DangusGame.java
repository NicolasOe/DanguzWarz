package dangusgame;

import com.badlogic.gdx.Game;

import dangusgame.screens.DangusProfileScreen;
import dangusgame.screens.GameOverviewScreen;
import dangusgame.screens.MenuScreen;
import dangusgame.screens.NetworkScreen;
import dangusgame.screens.SplashScreen;
import dangusgame.screens.DangusCreator;

public class DangusGame extends Game
{
	public static final int WIDTH = 800;
	public static final int HEIGHT= 600;
	
	public SplashScreen splashScreen;
	public MenuScreen menuScreen;
	public GameOverviewScreen gameOverviewScreen;
	public DangusProfileScreen dangusProfileScreen;
	public DangusCreator DangusCreator;
	
	public NetworkScreen networkScreen;
	
	@Override
	public void create()
	{
		splashScreen = new SplashScreen(this);
		menuScreen = new MenuScreen(this);
		gameOverviewScreen = new GameOverviewScreen(this);
		dangusProfileScreen = new DangusProfileScreen(this);
		networkScreen = new NetworkScreen(this);
		DangusCreator = new DangusCreator(this);
		
		this.setScreen(menuScreen);
	}
}
