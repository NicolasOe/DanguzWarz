package dangusgame.screens;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import dangusgame.DangusGame;
import dangusgame.SomeRequest;
import dangusgame.SomeResponse;

public class NetworkScreen implements Screen {

	DangusGame game;
	
	Stage stage;
	Label connection_state;
	
	Server server;
	Client client;
	
	Kryo kryo;
	
	public NetworkScreen(DangusGame game){
		this.game = game;
		
		stage = new Stage();
		
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		Skin skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		
		Table root = new Table();
		
		TextButton hostButton = new TextButton("Host", skin);
		TextButton connectButton = new TextButton("Connect to", skin);
		
		connection_state = new Label("Aguardando acao", skin);
		
		hostButton.addListener(
				new InputListener(){
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("clicked!");
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						server = new Server();
						server.start();
						
						kryo = server.getKryo();
						kryo.register(SomeRequest.class);
						kryo.register(SomeResponse.class);
						
						try {
							server.bind(54555, 54777);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						server.addListener(
								new Listener(){
									public void received(Connection connection, Object object){
										if(object instanceof SomeRequest){
											SomeRequest request = (SomeRequest)object;
											System.out.println(request.text);
											
											SomeResponse response = new SomeResponse();
								            response.text = "Recebido";
								            connection.sendTCP(response);
										}
									}
								});
						
						System.out.println("hosting!!");
						connection_state.setText("Hosting...");
						
					}
				});
		
		connectButton.addListener(
				new InputListener(){
					public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
					{
						System.out.println("clicked!");
						return true;
					}
					public void touchUp (InputEvent event, float x, float y, int pointer, int button)
					{
						client = new Client();
						
						client.start();
						
						kryo = client.getKryo();
						kryo.register(SomeRequest.class);
						kryo.register(SomeResponse.class);
						
						List<InetAddress> addr = client.discoverHosts(54777, 5000);
						
						if(addr != null){
							
							for(InetAddress a : addr){
								System.out.println(a.toString());
							}
							
						}
						
						
						SomeRequest request = new SomeRequest();
						request.text = "Pedido";
						
						client.sendTCP(request);
						
						client.addListener(new Listener() {
					       public void received (Connection connection, Object object) {
					          if (object instanceof SomeResponse) {
					             SomeResponse response = (SomeResponse)object;
					             System.out.println(response.text);
					             connection_state.setText("Conectado");
					          }
					       }
						});
						
						System.out.println("Enviado!!");
					}
				});
		
		root.add(connection_state).row();
		root.add(hostButton).row();
		root.add(connectButton);
		root.setFillParent(true);
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
		stage.dispose();
	}

}
