package dangusgame.model.interfaces;

import java.awt.Point;

import dangusgame.screens.GameOverviewScreen;

import com.badlogic.gdx.Screen;

/**
 * 
 * A minigame is a game that you play to upgrade your 'dangu'.
 * Each minigame has an acessible 'icon' and 'name'.
 * This is a model that provides the structure to the developer
 * for creating new minigames.
 * 
 * @author Lucas M Carvalhaes
 * 
 */
public interface IMinigame {

	/**
	 * Implement to tell the UI where the minigame icon will be in the {@link GameOverviewScreen}.
	 * 
	 * @return A new Point containing the X and Y from bottom left.
	 */
	public Point getIconPosition();

	/**
	 * Implement to return the icon string indetifier. The string that will be used to find
	 * the icon image on the texture atlas.
	 * 
	 * @return A string containing the icon image name or null to use the default minigame icon.
	 */
	public String getIconImageName();

	/**
	 * Just implement to return the minigame itself.
	 * 
	 * @return I did what was necessary in the minigame abstract model. =)
	 */
	public Screen getScreen();

	/**
	 * Implement to return the minigame name.
	 * 
	 * @return The minigame name.
	 */
	public String getName();

}
