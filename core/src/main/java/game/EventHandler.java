package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import enums.Direction;

public class EventHandler 
{
	private Player player;
	
	/**
	 * Event Handler used to get the user input in order to control the player
	 * @param player : player instance
	 */
	public EventHandler(Player player) 
	{
		this.player = player;
	}

	/**
	 * check the user input and make the player act accordingly
	 */
	public void checkUserInputs()
	{
		if(Gdx.input.isKeyJustPressed(Keys.UP)) 
		{
			player.setDirection(Direction.UP);
		}
		else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
    	{
			player.setDirection(Direction.RIGHT);
    	}
		else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
    	{
			player.setDirection(Direction.DOWN);
    	}
		else if(Gdx.input.isKeyJustPressed(Keys.LEFT))
    	{
			player.setDirection(Direction.LEFT);
    	}
		else if(Gdx.input.isKeyJustPressed(Keys.A))
    	{
			player.throwItem();
    	}
	}
}
