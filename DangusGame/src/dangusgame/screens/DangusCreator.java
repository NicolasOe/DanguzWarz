package dangusgame.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;

import dangusgame.DangusGame;
import dangusgame.core.MyActor;
import dangusgame.model.Classe;
import dangusgame.model.Dangus;
import dangusgame.model.Persistence;

public class DangusCreator implements Screen
{
	Texture background; 
	Dangus dangus;
	DangusGame jogo;
	TextField nomeField;
	String nome;
	Label nomeLabel,fisicoLabel,agilidadeLabel,inteligenciaLabel,resistenciaLabel,fsLabel,agiLabel,intLabel,resLabel,classeLabel;
	SpriteBatch batch;
	Skin skin;
	Stage stage;
	Table menu;
	int indexColor,modFs=0,modAgi=0,modInt=0,modRes=0, posClasse=0;
	Slider sliderR,sliderG,sliderB;
	MyActor setDango; 
	
	
	public DangusCreator(DangusGame game)
	{
		// create the camera
		jogo = game;
		batch = new SpriteBatch();
		

	}

	@Override
	public void render(float delta)
	{
		
		batch.begin();
		batch.draw(background, 0, 0, DangusGame.WIDTH, DangusGame.HEIGHT);
		batch.end();
		
		dangus.setCor(new Color(sliderR.getValue(),sliderG.getValue(),sliderB.getValue(),1));
		
		System.out.println(sliderR.getValue());
		stage.draw();
		Table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void show()
	{
		stage = new Stage();
		
		
		
		//arruma para o dango  ser desenhado corretamente
		dangus = new Dangus();
		dangus.getActor().setPosition(0, (stage.getHeight()/2)-(dangus.getActor().getHeight()/2));
		dangus.getActor().setScale(1.5f);
		
		//arruma o set do dango dependendo da sua classe
		setDango = new MyActor(new TextureRegion(new Texture(Gdx.files.internal("dangoPadrao_fit.png"))));
		setDango.setPosition(0, (stage.getHeight()/2)-(dangus.getActor().getHeight()/2));
		setDango.setScale(1.5f);
		
		//carrega a imagem do background
		background = new Texture(Gdx.files.internal("100_Dango.png"));
		
		
		// carrega uma skin
		skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		
		//cria os sliders para cor
		sliderR = new Slider(0,1,0.04f,false,skin);
		sliderR.setValue(new Random().nextFloat());		
		sliderG = new Slider(0,1,0.04f,false,skin);
		sliderG.setValue(new Random().nextFloat());
		sliderB = new Slider(0,1,0.04f,false,skin);
		sliderB.setValue(new Random().nextFloat());
		
		
		
		//tabela pra mostrar as informações do dango
		menu = new Table();
		
		//labels
		classeLabel = new Label("Classe do Dango  ", skin);
		classeLabel.setColor(Color.BLACK);
		nomeLabel = new Label("Nome  ", skin);
		nomeLabel.setColor(Color.BLACK);
		fisicoLabel=new Label("Fisico:  ",skin);
		fisicoLabel.setColor(Color.BLACK);
		agilidadeLabel=new Label("Agilidade:  ",skin);
		agilidadeLabel.setColor(Color.BLACK);
		inteligenciaLabel=new Label("Inteligencia:  ",skin);
		inteligenciaLabel.setColor(Color.BLACK);
		resistenciaLabel=new Label("Resistencia:  ",skin);
		resistenciaLabel.setColor(Color.BLACK);
		
		// textfield para pegar o nome do dangus
		nomeField = new TextField("", skin);
		nomeField.setTextFieldListener(new TextFieldListener()
		{
			@Override
			public void keyTyped(TextField textField, char key)
			{
				dangus.setNome(textField.getText());
			}
		});
		
		
		//botoes para sair, salvar e trocar de classe
		TextButton sairButton = new TextButton("Sair", skin);
		
		sairButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
	                    jogo.setScreen(jogo.menuScreen);
					}
				}
				);
		
		TextButton salvarButton = new TextButton("Salvar", skin);
		sairButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						Persistence.saveDangu(dangus);
	                    jogo.setScreen(jogo.menuScreen);
					}
				}
				);
		
		TextButton proxButton = new TextButton("", skin,"maior");
		proxButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						if(posClasse<3)
							posClasse++;
						else
							posClasse=0;
						
						dangus.setClasse(Classe.values()[posClasse]);
						classeLabel.setText(dangus.getClasse().toString());
						
						fsLabel.setText(dangus.getClasse().getStats().getFisico()+"");
						agiLabel.setText(dangus.getClasse().getStats().getAgilidade()+"");
						intLabel.setText(dangus.getClasse().getStats().getInteligencia()+"");
						resLabel.setText(dangus.getClasse().getStats().getResistencia()+"");
						
						setDango.setTexture(new TextureRegion(new Texture(Gdx.files.internal(dangus.getClasse().toString()+".png"))));
					}
				}
				);
		
		TextButton antButton = new TextButton("", skin,"menor");
		antButton.addListener(
				new InputListener()
				{
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						if(posClasse<1)
							posClasse=3;
						else
							posClasse--;
						
						dangus.setClasse(Classe.values()[posClasse]);
						classeLabel.setText(dangus.getClasse().toString());
						fsLabel.setText(dangus.getClasse().getStats().getFisico()+"");
						agiLabel.setText(dangus.getClasse().getStats().getAgilidade()+"");
						intLabel.setText(dangus.getClasse().getStats().getInteligencia()+"");
						resLabel.setText(dangus.getClasse().getStats().getResistencia()+"");
						
						setDango.setTexture(new TextureRegion(new Texture(Gdx.files.internal(dangus.getClasse().toString()+".png"))));
					}
				}
				);
		
		
		fsLabel = new Label(0+" + "+modFs,skin);
		fsLabel.setColor(Color.BLACK);
		agiLabel = new Label(0+" + "+modAgi,skin);
		agiLabel.setColor(Color.BLACK);
		intLabel = new Label(0+" + "+modInt,skin);
		intLabel.setColor(Color.BLACK);
		resLabel = new Label(0+" + "+modRes,skin);
		resLabel.setColor(Color.BLACK);

		
		
		
			
		sliderR.setWidth(280);
		sliderG.setWidth(280);
		sliderB.setWidth(280);
		nomeLabel.sizeBy(25);
		skin.add("fundinho", new Texture(Gdx.files.internal("fundo_menu.png")));
		
		menu.setBackground(skin.getDrawable("fundinho"));
		menu.add(nomeLabel);
		menu.add(nomeField);
		menu.row().height(45);
		menu.add(sliderR).colspan(3).width(400);
		menu.row().height(45);
		menu.add(sliderG).colspan(3).width(400);
		menu.row().height(45);
		menu.add(sliderB).colspan(3).width(400);		
		menu.row().height(45);
		menu.add(fisicoLabel);
		menu.add(fsLabel);
		menu.row().height(45);
		menu.add(agilidadeLabel);
		menu.add(agiLabel);
		menu.row().height(45);
		menu.add(inteligenciaLabel);
		menu.add(intLabel);
		menu.row().height(45);
		menu.add(resistenciaLabel);
		menu.add(resLabel);
		menu.row();
		menu.add(antButton);
		menu.add(classeLabel);
		menu.add(proxButton);
		menu.row().height(45);
		menu.add(salvarButton);
		menu.add(sairButton);
		
		
		menu.setFillParent(true);		
		menu.right();
		System.out.println("Setou o menu no stage numa boa.");
		
		//menu.debug();
		stage.addActor(menu);
		stage.addActor(dangus.getActor());
		stage.addActor(setDango);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide()
	{
		Persistence.saveDangu(dangus);

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

}
