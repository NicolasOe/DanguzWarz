package dangusgame.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import dangusgame.core.MyActor;

public class PauseMenu extends MyActor {

	Stage stage;
	
	Label pausedLabel;
	TextButton exitButton;
	
	public PauseMenu(TextureRegion tr, float x, float y) {
		super(tr);

		stage = new Stage();
		
		Skin skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		
		pausedLabel = new Label("PAUSED", skin);
		pausedLabel.setScale(4);
		pausedLabel.setOrigin(pausedLabel.getWidth()/2, pausedLabel.getHeight()/2);
		pausedLabel.setPosition(x, y);
		
		exitButton = new TextButton("Exit game", skin);
		exitButton.setOrigin(exitButton.getWidth()/2, exitButton.getHeight()/2);
		exitButton.setPosition(x, y - pausedLabel.getHeight());
		
		stage.addActor(pausedLabel);
		stage.addActor(exitButton);
		
		this.setOrigin(tr.getRegionWidth()/2, tr.getRegionHeight()/2);
		this.setPosition(x, y);
	}

	@Override
	public void act(float delta){
		super.act(delta);
		stage.act(delta);
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stage.draw();
        
    }
	
	public Stage getStage(){
		return stage;
	}
}
