package dangusgame.model;

import dangusgame.model.interfaces.IMinigame;

import com.badlogic.gdx.Screen;

/**
 * This is the base minigame model. This helps keep the entire system tight.
 * 
 * 
 * @author Lucas M Carvalhaes
 * 
 */
public abstract class Minigame implements Screen, IMinigame {

	/**
	 * The minigame name.
	 */
	protected String	name;

	public Minigame(String nm) {
		name = nm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufscar.comp.dangus.model.interfaces.IMinigame#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Screen getScreen() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {}

}
