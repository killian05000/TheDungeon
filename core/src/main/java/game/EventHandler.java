package com.mystudio.themaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class EventHandler 
{
	private Game game;
	
	public EventHandler(Game game) 
	{
		this.game = game;
	}

	public void checkUserInputs()
	{
		if(Gdx.input.isKeyJustPressed(Keys.UP)) 
		{

		}
		else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
    	{

    	}
		else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
    	{

    	}
		else if(Gdx.input.isKeyJustPressed(Keys.LEFT))
    	{

    	}
		else if(Gdx.input.isKeyJustPressed(Keys.A))
    	{

    	}
		else if(Gdx.input.isKeyJustPressed(Keys.R))
    	{
			game.resetGame();
    	}
		else if(Gdx.input.isKeyJustPressed(Keys.M))
    	{
			game.callMenu();
    	}
	}
}
