package dangusgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dangusgame.DangusGame;

public class MenuScreen implements Screen
{
	SpriteBatch batch;
	DangusGame game;
	
	Stage stage;
	Table root;
	
	public MenuScreen(DangusGame g)
	{
		batch = new SpriteBatch();
		this.game = g;		
	
		
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		batch.begin();
		
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		System.out.println("resized");
		stage.getViewport().update(width, height);
	}

	@Override
	public void show()
	{
		stage = new Stage();
		root = new Table();
		System.out.println("showed");
		Gdx.input.setInputProcessor(stage);
		root.setFillParent(true);
		
		Skin skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		skin.add("table_background", new Texture(Gdx.files.internal("100_Dango.png")));
		root.setBackground(skin.getDrawable("table_background"));
		
		TextButton playButton = new TextButton("Jogar", skin);
		TextButton newButton = new TextButton("Novo Dango", skin);
		TextButton netButton = new TextButton("Rede", skin);
		TextButton settingsButton = new TextButton("Opcoes", skin);
		TextButton exitButton = new TextButton("Sair", skin);
		
		playButton.setColor(1, 1, 1, 0.9f);
		newButton.setColor(1,1,1,0.9f);
		netButton.setColor(1, 1, 1, 0.9f);
		settingsButton.setColor(1, 1, 1, 0.9f);
		exitButton.setColor(1, 1, 1, 0.9f);
		
		playButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("clicked!");
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("unclicked!");
	                    game.setScreen(game.gameOverviewScreen);
					}
				}
				);
		
		netButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("clicked!");
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("unclicked!");
	                    game.setScreen(game.networkScreen);
					}
				}
				);
		
		newButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("clicked!");
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("unclicked!");
	                    game.setScreen(game.DangusCreator);
					}
				}
				);
		
		exitButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("clicked!");
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("unclicked!");
						
	                    Gdx.app.exit();
					}
				}
				);
		
		root.left();
		root.add(playButton).size(200, 50).row().pad(5);
		root.add(newButton).size(200,50).row();
		root.add(netButton).size(200, 50).row().pad(5);
		root.add(settingsButton).size(200, 50).row();
		root.add(exitButton).size(200, 50);
		
		stage.addActor(root);
		stage.addAction(Actions.sequence(Actions.moveTo(0, DangusGame.HEIGHT), Actions.moveTo(0, 0, 0.2f)));
	}

	@Override
	public void hide()
	{
		System.out.println("hid");
		Gdx.input.setInputProcessor(null);
		stage.dispose();
	}

	@Override
	public void pause()
	{
		System.out.println("paused");
	}

	@Override
	public void resume()
	{
		System.out.println("resumed");
	}

	@Override
	public void dispose()
	{
		System.out.println("disposed");
	}

}
