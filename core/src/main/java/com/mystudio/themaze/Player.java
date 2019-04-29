
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
	
	private int direction;
	
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
			direction=0;
		}
    	if(Gdx.input.isKeyPressed(Keys.RIGHT))
    	{
    		badpac = new Texture("badpacRight.png");
    		direction=1;
    	}
    	if(Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		badpac = new Texture("badpacDown.png");
    		direction=2;
    	}
    	if(Gdx.input.isKeyPressed(Keys.LEFT))
    	{
    		badpac = new Texture("badpacLeft.png");
    		direction=3;
    	}
    	
    	switch(direction)
    	{
			case 0:
				newPosX= posX-1;
				if(newPosX>=0 && maze.getMatrix()[newPosX][posY] == 0)
					posX=newPosX;
				break;
			case 1:
				newPosY = posY+1;
				if(newPosY<maze.getMatrix()[0].length && maze.getMatrix()[posX][newPosY] == 0)
					posY=newPosY;
				break;
			case 2:
				newPosX = posX+1;
				if(newPosX<maze.getMatrix().length && maze.getMatrix()[newPosX][posY] == 0) 
					posX=newPosX;			
				break;
			case 3:
				newPosY = posY-1;
				if(newPosY>=0 && maze.getMatrix()[posX][newPosY] == 0) 
					posY=newPosY;
				break;	
		}
	}
	
	
	public void render(Graphics g, int mapScale) 
	{	
		g.drawTexture(badpac, posY*mapScale, posX*mapScale, mapScale, mapScale);
	}
	
	public void setPosY(int posY) 
	{
		this.posY = posY;
	}
	
}

