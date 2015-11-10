package dangusgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import dangusgame.DangusGame;
import dangusgame.core.MyActor;
import dangusgame.core.Assets;
import dangusgame.userinterface.PauseMenu;

public class GameOverviewScreen implements Screen
{

	DangusGame game;
	
	private Stage stage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private MyActor creationIcon;
	
	private boolean location_hold; //Se tiver apertando em algum lugar visitavel, desliga o movimento da camera
	private boolean paused;
	private boolean pressed;
	private PauseMenu pauseMenu;
	
	private InputMultiplexer im;
	
	public GameOverviewScreen(DangusGame g)
	{
		this.game = g;
		stage = new Stage();
		batch = new SpriteBatch();
		creationIcon = null;
		
		location_hold = false;
		paused = false;
		pressed= false;
	
		im = new InputMultiplexer();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			camera.translate(0, 400*delta);
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			camera.translate(-400*delta, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			camera.translate(0, -400*delta);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			camera.translate(400*delta, 0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.P) && !pressed){
			pressed = true;
			if(!paused){
				System.out.println("paused.");
				paused = true;
				
				pauseMenu = new PauseMenu(new TextureRegion(new Texture(Gdx.files.internal("100_Dango.png"))), 400, 300);
				stage.addActor(pauseMenu);
				im.removeProcessor(stage);
				im.addProcessor(pauseMenu.getStage());
			}else{
				System.out.println("unpaused.");
				paused = false;
				im.removeProcessor(pauseMenu.getStage());
				im.addProcessor(stage);
				pauseMenu.remove();
			}
		}
		if(!Gdx.input.isKeyPressed(Keys.P) && pressed){
			pressed = false;
		}
		
		if(!location_hold && Gdx.input.isTouched())
		{
			camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
		}
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		stage.draw();
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(im);
		im.addProcessor(stage);
		
		Assets.addTextureAtlas( "locations.atlas" );
		
		creationIcon = new MyActor(Assets.getAtlas("locations").findRegion("mochimaker"), 100, 200);
		creationIcon.setSize(128, 128);
		creationIcon.setOrigin(creationIcon.getWidth()/2, creationIcon.getHeight()/2);
		creationIcon.addListener(
				new ClickListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						creationIcon.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(1.1f, 1.1f, 0.3f), Actions.scaleTo(1, 1, 0.2f))));
						location_hold = true;
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						creationIcon.clearActions();
						creationIcon.addAction(Actions.scaleTo(1, 1));
						
						if(this.isOver())
							game.setScreen(game.menuScreen);
						else
							location_hold = false;
					}
				}
				);
		
		stage.addActor(creationIcon);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		camera.translate(w/2, h/2);
		stage.getViewport().setCamera(camera);
		
		location_hold = false;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
