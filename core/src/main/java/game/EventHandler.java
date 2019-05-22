package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import enums.Direction;

public class EventHandler 
{
	private Player player;
	
	public EventHandler(Player player) 
	{
		this.player = player;
	}

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
