package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player 
{
	private int direction;
	
	private int posX;
	private int posY;
	
	private int defaultPostionX = 1;
	private int defaultPositionY = 9;
	
	private Texture badpac;
	
	public Player() {
		posX = defaultPostionX;
		posY = defaultPositionY;
		badpac = new Texture("badpacLeft.png");
	}
	/**
	 * Update the direction
	 */
	public  void  updateDirection () {
		if(Gdx.input.isKeyPressed(Keys.UP)) 
		{
			badpac = new Texture("badpacUp.png");
			direction = 0;
		}
    	if(Gdx.input.isKeyPressed(Keys.RIGHT))
    	{
    		badpac = new Texture("badpacRight.png");
    		direction = 1;
    	}
    	if(Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		badpac = new Texture("badpacDown.png");
    		direction = 2;
    	}
    	if(Gdx.input.isKeyPressed(Keys.LEFT))
    	{
    		badpac = new Texture("badpacLeft.png");
    		direction = 3;   
    	}
	}
	
	
	public void render(Graphics g) 
	{
		
		g.drawTexture(badpac, posY*50, posX*50, 50, 50);
	}
	
	public int getPosX() 
	{
		return posX;
	}
	
	public int getPosY() 
	{
		return posY;
	}
	
	public void setPosX(int posX) 
	{
		this.posX = posX;
	}
	
	public void setPosY(int posY) 
	{
		this.posY = posY;
	}
	
	public int getDirection() 
	{
		return direction;
	}
	
}
