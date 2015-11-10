package dangusgame.MineGame;



import java.awt.Point;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import dangusgame.core.MyActor;
import dangusgame.model.Dangus;
import dangusgame.model.Minigame;
import dangusgame.model.Persistence;


public class MineGame extends Minigame {
	
	MyActor martelo,barra,apontador,fundo,dango;
	TextureRegion ImgMartelo, ImgBarra,ImgFundo,ImgDango;
	Skin skin; 
	Game jogo;
	Label pontosTotal;
	ArrayList<Label> pontos;
	OrthographicCamera camera;
	Sound martelada;
	//variavel para fazer a animação do martelo batendo
	boolean terminado=false;
	//variavel para fazer a animação do apontador passando
	boolean passado=false;
	int pontuacao=0,adquirido;
	SpriteBatch batch;
	Dangus dangus;


public MineGame(Game game)
{
	super("Mine Game");
	
	dangus = new Dangus("DangusAserSalvo");
	dangus.getStats().setAgilidade(500);
	dangus.getStats().setMovimento(3);
	dangus.getStats().setCarisma(7.3f);
	Persistence.saveDangu(dangus); 
	
	jogo= game;
	
	batch = new SpriteBatch();
	
	//seta a skin
	skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));	
	
	//cria a label para escrever os pontos
	pontosTotal = new Label("Pontos: 0",skin);
	pontosTotal.setSize(100, 20);
	pontosTotal.setFontScale(2f);
	pontos = new ArrayList<Label>();
	
	
	
	//carreguei a imagem do martelo, da barra, do fundo e do dango ferreiro
	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("minegame.atlas"));
	ImgMartelo = atlas.findRegion("Hammer");
	ImgBarra = atlas.findRegion("barra");
	ImgDango = atlas.findRegion("Dango");
	ImgFundo = new TextureRegion(new Texture(Gdx.files.internal("fundo.jpg")));

	//criei o ator do martelo, da bigorna, da barra, do fundo, do apontador
	martelo = new MyActor(ImgMartelo,200,280);
	barra = new MyActor(ImgBarra,17,10);	
	fundo = new MyActor(ImgFundo,0,0);
	apontador = new MyActor(ImgMartelo,17,60);
	apontador.rotateBy(45);
	dango = new MyActor(ImgDango,0,0);
	dango.setOrigin( dango.getWidth()/2,dango.getHeight()/2);
	
	
	
	
	
	//carrega o som do martelo
	martelada = Gdx.audio.newSound(Gdx.files.internal("data/sounds/martelo.wav"));
	
	// create the camera
    camera = new OrthographicCamera();    
}


@Override
public void render(float delta) {
	// clear the screen with a dark blue color. The
    // arguments to glClearColor are the red, green
    // blue and alpha component in the range [0,1]
    // of the color to be used to clear the screen.
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // tell the camera to update its matrices.
    camera.update();

    // tell the SpriteBatch to render in the
    // coordinate system specified by the camera.
    batch.setProjectionMatrix(camera.combined);

    
    //atualização do martelinho apontador para que ele se mova
    if(apontador.getX()>(barra.getX()+(barra.getWidth()*barra.getScaleX())) || apontador.getX()<barra.getX())
    	passado = !passado;
    
    if(passado)
    	apontador.setX(apontador.getX()-(barra.getWidth()*barra.getScaleX())*delta);
    else
    	apontador.setX(apontador.getX()+(barra.getWidth()*barra.getScaleX())*delta);
    	
    
    
    
    // begin a new batch to draw everything
    batch.begin();   
    
    martelo.act(delta);
    
    for( Label elemento: pontos)
 	   elemento.act(delta);   	
    
    
  //martelo batendo
    if(Gdx.input.isTouched())
    	martelar(martelo,delta);
    
    fundo.draw(batch, 1);
    
    
    barra.draw(batch,1);
    apontador.draw(batch,1);
    martelo.draw(batch,1);
    dango.draw(batch, 1);
    
    
    pontosTotal.draw(batch, 1);
    for(Label elemento: pontos)
   	   elemento.draw(batch, 1);
   
    
    batch.end();
}

	//coloca o som da martelada, prepara as flags para que só aconteça uma martelada por clique e adiciona pontuação
	public void martelar(MyActor mtr, float delta)
	{
		if(terminado)
			martelada.play();
		
		terminado = false;
		
		if(martelo.getActions().size==0)
		{
			terminado=true;
			
			//adiciona a animação do amrtelo para que ele possa agir na proxima vez que for clicado
			martelo.addAction(Actions.sequence(Actions.rotateBy(45, 0.03f),Actions.rotateBy(-45, 0.5f)));
			
			adquirido = (int)(1000-((apontador.getX()-(barra.getWidth()*barra.getScaleX())/2>=0)?(apontador.getX()-(barra.getWidth()*barra.getScaleX())/2):-(apontador.getX()-(barra.getWidth()*barra.getScaleX())/2)));
			
			//adiciona a animação para os pontos
			pontos.add(new Label("",skin));
			pontos.get(pontos.size()-1).setPosition(apontador.getX(), 72);
			pontos.get(pontos.size()-1).addAction(Actions.parallel(Actions.moveTo(apontador.getX(), 100,1.5f), Actions.alpha(0, 1.5f)));
			pontos.get(pontos.size()-1).setText(adquirido+"");
			
			pontuacao+=adquirido;
			pontosTotal.setText("Pontos: "+pontuacao);
			
		}
	}
	
	@Override
	public void resize(int width, int height) {

		camera.setToOrtho(false, width, height);
		pontosTotal.setPosition(0, height-30);
		
		martelo.setScaleX(-0.3f);
		martelo.setScaleY(0.3f);		
		martelo.setPosition(width-width/3, height-(height/3));
		
		
		dango.setScaleX(-0.5f);
		dango.setScaleY(0.5f);
		dango.setPosition(martelo.getX()+martelo.getWidth()*martelo.getScaleX(), martelo.getY()-martelo.getHeight()*martelo.getScaleY());
	
		barra.setScaleX(1.5f*(width/800f));
		barra.setScaleY(1.5f*(height/600f));
		
		apontador.setScale(0.1f);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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


	@Override
	public Point getIconPosition() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getIconImageName() {
		// TODO Auto-generated method stub
		return null;
	}	

	

}
