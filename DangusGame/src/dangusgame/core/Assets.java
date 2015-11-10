package dangusgame.core;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	
	private static AssetManager am = new AssetManager();
	
	public static void addTextureAtlas(String name){
		am.load(name, TextureAtlas.class);
		am.finishLoading();
	}
	
	public static TextureAtlas getAtlas(String atlas_id){
		return am.get(atlas_id + ".atlas");
	}
	
	public static void removeTextureAtlas(String atlas_id){
		am.unload(atlas_id);
	}
	
	public static void addSound(String sound_name){
		am.load(sound_name, Sound.class);
		am.finishLoading();
	}
	
	public static Sound getSound(String sound_id){
		return am.get(sound_id);
	}
	
	public static void removeSound(String sound_id){
		am.unload(sound_id);
	}
}
