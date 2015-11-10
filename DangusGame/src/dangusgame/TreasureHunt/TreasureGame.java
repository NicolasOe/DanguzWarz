package dangusgame.TreasureHunt;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import dangusgame.model.Minigame;

public class TreasureGame extends Minigame {

	Integer board[][];
	Integer width, height;
	
	public TreasureGame(String nm, int width, int height) {
		super(nm);

		this.width = width;
		this.height = height;
		board = new Integer[width][height];
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				board[i][j]=0;
			}
		}
		
		
		
	}

	@Override
	public void render(float delta) {


		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				switch(board[i][j]){
				
				case 0:
				{
					break;
				}
				
				}
			}
		}
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

	
	public void Load(){
		FileHandle f = Gdx.files.internal("th01.txt");
		String s = f.readString();
		String lines[] = s.split("\n");
		String dim[] = lines[0].split(" ");
		this.width = Integer.parseInt(dim[0]);
		this.height = Integer.parseInt(dim[1]);
		
		for(int i=1;i<=height;i++)
		{
			
		}
	}
}
