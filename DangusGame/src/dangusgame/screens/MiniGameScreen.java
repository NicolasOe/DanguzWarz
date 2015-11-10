package dangusgame.screens;

import com.badlogic.gdx.Screen;

import dangusgame.DangusGame;
import dangusgame.MineGame.MineGame;

public class MiniGameScreen implements Screen {
	
	DangusGame game;
	MineGame mg;

	public MiniGameScreen(DangusGame g){
		game = g;
		mg = null;
	}
	
	@Override
	public void render(float delta) {
		mg.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		mg.resize(width, height);
	}

	@Override
	public void show() {
		mg = new MineGame(game);
		mg.show();
	}

	@Override
	public void hide() {
		mg.hide();
		mg.dispose();
	}

	@Override
	public void pause() {
		mg.pause();
	}

	@Override
	public void resume() {
		mg.resume();
	}

	@Override
	public void dispose() {
		mg.dispose();
	}
	
	

}
