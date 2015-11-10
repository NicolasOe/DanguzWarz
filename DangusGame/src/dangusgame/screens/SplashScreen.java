package dangusgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dangusgame.DangusGame;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen
{
	private SpriteBatch batch;
	private Stage stage;
	
	private DangusGame game;
	private boolean done;
	
	private TextureRegion tr;
	
	public SplashScreen(DangusGame g)
	{
		game = g;
		done = false;
		stage = new Stage();
		batch = new SpriteBatch();
		tr = null;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(!done)
		{
			if(Gdx.input.justTouched() || Gdx.input.isButtonPressed(Buttons.LEFT))
				done = true;
			
			stage.act(delta);
			
			stage.draw();
		}
		else
		{
			dispose();
			game.setScreen(game.menuScreen);
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		tr = new TextureRegion(new Texture(Gdx.files.internal("100_Dango.png")));
		
		Table root = new Table();
		root.setFillParent(true);
		
		Image img = new Image(tr);
		img.setColor(1, 1, 1, 0);
		root.add(img).width(DangusGame.WIDTH).height(DangusGame.HEIGHT);
		
		stage.addActor(root);
		
		img.addAction(
				sequence(fadeIn(1), delay(1), fadeOut(1),
				new Action()
				{
					public boolean act(float f)
					{
						done = true;
						return true;
					}
				}
						));
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		System.out.println("disposed");
		batch.dispose();
		stage.dispose();
	}
}
