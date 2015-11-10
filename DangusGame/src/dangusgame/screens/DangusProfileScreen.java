package dangusgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dangusgame.DangusGame;

public class DangusProfileScreen implements Screen {

	DangusGame game;
	
	Skin skin;
	Stage stage;
	
	ButtonGroup tabs;
	Table profileScreen;
	Table statsScreen;
	Table skillsScreen;
	Table equipsScreen;
	
	Label dangusStr;
	Label dangusInt;
	Label dangusDex;
	Label dangusRes;
	
	ProgressBar dangusStrExp;
	ProgressBar dangusIntExp;
	ProgressBar dangusDexExp;
	ProgressBar dangusResExp;
	
	public DangusProfileScreen(DangusGame g){
		game = g;
		stage = new Stage();
		skin = null;
		tabs = null;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		skin.add("tab", new Texture(Gdx.files.internal("tab.png")));
		skin.add("tab2", new Texture(Gdx.files.internal("tab2.png")));
		tabs = new ButtonGroup();
		
		TextButton profileTab = new TextButton("Profile", skin);
		TextButton statsTab = new TextButton("Stats", skin);
		TextButton skillsTab = new TextButton("Skills", skin);
		TextButton equipsTab = new TextButton("Equips", skin);
		
		tabs.add(profileTab, statsTab, skillsTab, equipsTab);
		tabs.setMaxCheckCount(1);
		tabs.setMinCheckCount(1);
		tabs.setChecked("Profile");
		
		Table root = new Table();
		
		root.add(profileTab).size(128, 64).maxWidth(128).left();
		root.add(statsTab).size(128, 64).maxWidth(128).left();
		root.add(skillsTab).size(128, 64);
		root.add(equipsTab).size(128, 64).row();
		
		//Profile Screen build
		profileScreen = new Table();
		
		Table right_half = new Table();
		Label stat_label = new Label("Stats: ", skin);
		Label str_label = new Label("Str: ", skin);
		Label int_label = new Label("Int: ", skin);
		Label dex_label = new Label("Dex: ", skin);
		Label res_label = new Label("Res: ", skin);
		
		ProgressBarStyle pbs = new ProgressBarStyle();
		pbs.background = skin.getDrawable("tab");
		
		dangusStr = new Label("0", skin);
		dangusDex = new Label("0", skin);
		dangusInt = new Label("0", skin);
		dangusRes = new Label("0", skin);
		
		right_half.add(stat_label).row();
		right_half.add(str_label).size(64, 64);
		right_half.add(dangusStr).size(128, 64).right().row();
		right_half.add(int_label).size(64, 64);
		right_half.add(dangusInt).size(128, 64).right().row();
		right_half.add(dex_label).size(64, 64);
		right_half.add(dangusDex).size(128, 64).right().row();
		right_half.add(res_label).size(64, 64);
		right_half.add(dangusRes).size(128, 64).right();
		
		profileScreen.add(right_half).height(999).width(999);
		
		root.add(profileScreen).colspan(4);
		root.setFillParent(true);
		root.top().left();
		
		root.debug();
		profileScreen.debug();
		stage.addActor(root);
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
		// TODO Auto-generated method stub
		
	}

}
