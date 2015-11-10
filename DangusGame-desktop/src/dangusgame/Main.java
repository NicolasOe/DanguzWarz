package dangusgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dangusgame.DangusGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "DangusGame";
		cfg.width = 800;
		cfg.height = 600;
		
		new LwjglApplication(new DangusGame(), cfg);
	}
}
