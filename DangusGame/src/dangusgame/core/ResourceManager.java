package dangusgame.core;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ResourceManager {

	private HashMap<String, TextureAtlas> textures;
	private HashMap<String, Sound> sounds;
	
	private ResourceManager(){
		textures = new HashMap<String, TextureAtlas>();
		sounds = new HashMap<String, Sound>();
	}
	
	public static ResourceManager RM(){
		return new ResourceManager();
	}
	
	public Boolean addTextureAtlas(String atlas_id, TextureAtlas atlas){
		if(textures.containsKey(atlas_id)){
			return false;
		} else {
			textures.put(atlas_id, atlas);
			return true;
		}
	}
	
	public TextureAtlas getAtlas(String atlas_id){
		return textures.get(atlas_id);
	}
	
	public Boolean removeTextureAtlas(String atlas_id){
		if(textures.containsKey(atlas_id)){
			textures.remove(atlas_id);
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean addSound(String sound_id, Sound sound){
		if(sounds.containsKey(sound_id)){
			return false;
		} else {
			sounds.put(sound_id, sound);
			return true;
		}
	}
	
	public Sound getSound(String sound_id){
		return sounds.get(sound_id);
	}
	
	public Boolean removeSound(String sound_id){
		if(sounds.containsKey(sound_id)){
			sounds.remove(sound_id);
			return true;
		} else {
			return false;
		}
	}
}
