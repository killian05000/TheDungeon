
package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player 
{
	private int posX;
	private int posY;
	
	private int defaultPostionX = 1;
	private int defaultPositionY = 9;
	
	private Texture badpac;
	
	public Player() 
	{
		posX = defaultPostionX;
		posY = defaultPositionY;
		badpac = new Texture("badpacLeft.png");
	}
	/**
	 * Update the direction
	 */
	public  void  update (Maze maze) { // update the player direction according to the user input
		
		int newPosX;
    	int newPosY;
    	
		if(Gdx.input.isKeyPressed(Keys.UP)) 
		{
			badpac = new Texture("badpacUp.png");
			newPosX= getPosX()-1;
			if(newPosX>=0 && maze.getMatrix()[newPosX][getPosY()] == 0)
				setPosX(newPosX);
		}
    	if(Gdx.input.isKeyPressed(Keys.RIGHT))
    	{
    		badpac = new Texture("badpacRight.png");
    		newPosY = getPosY()+1;
			if(newPosY<maze.getMatrix()[0].length && maze.getMatrix()[getPosX()][newPosY] == 0)
				setPosY(newPosY);
    	}
    	if(Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		badpac = new Texture("badpacDown.png");
    		newPosX = getPosX()+1;
			if(newPosX<maze.getMatrix().length && maze.getMatrix()[newPosX][getPosY()] == 0) 
				setPosX(newPosX);	
    	}
    	if(Gdx.input.isKeyPressed(Keys.LEFT))
    	{
    		badpac = new Texture("badpacLeft.png");  
    		newPosY = getPosY()-1;
			if(newPosY>=0 && maze.getMatrix()[getPosX()][newPosY] == 0) 
				setPosY(newPosY);
    	}
	}
	
	
	public void render(Graphics g, int mapScale) 
	{	
		g.drawTexture(badpac, posY*mapScale, posX*mapScale, mapScale, mapScale);
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
	
}

