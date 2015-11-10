package dangusgame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class Persistence {
	
	private static Json json = new Json();

	public static void saveDangu(Dangus d){
		FileHandle file = Gdx.files.local(d+".txt");
		file.writeString(json.toJson(d), false);
	}
	
	public static Dangus loadDangu(String name){
		FileHandle file = Gdx.files.local(name+".txt");
		return json.fromJson(Dangus.class, file);
	}
	
}
