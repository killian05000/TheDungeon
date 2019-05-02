package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Enemy {
	
	private Texture pacMove;
	private int posX;
	private int posY;	
	private int speed=10;
	private int direction;
	
	private int mapScale;	
	
	/**
	 * 
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param scale : tile size
	 */
	public Enemy(int x, int y, int scale)
	{
		pacMove = new Texture("badGuy/pacMoveLeft.png");
		mapScale = scale;
		posX = x*mapScale;
		posY = y*mapScale;		
	}
	
	/**
	 * Update the enemy direction randomly
	 * @param maze : maze instance to check collisions with the walls and different fixed objects
	 */
	public  void  update (Maze maze) 
	{		
		int newPosX;
    	int newPosY;

    	switch(direction)
    	{
			case 0: // UP
				newPosX = posX - speed;				
				if(newPosX >=0 
					&& maze.getMatrix()[newPosX / mapScale][posY / mapScale] == 0
					&& maze.getMatrix()[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == 0
					)
					posX = newPosX;
				else
					direction = (int)(Math.random() * (4-0));				
				break;
				
			case 1: // RIGHT
				newPosY = posY + speed;	    		
	    		if((newPosY + mapScale - speed) / mapScale < maze.getMatrix()[0].length 
					&& maze.getMatrix()[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
					&& maze.getMatrix()[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
					)
					posY = newPosY;
	    		else
	    			direction = (int)(Math.random() * (4-0));
				break;
				
			case 2: // DOWN
				newPosX = posX + speed;	    		
				if((newPosX + mapScale - speed) / mapScale < maze.getMatrix().length 
						&& maze.getMatrix()[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == 0
						&& maze.getMatrix()[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == 0
						) 
						posX = newPosX;
	    		else
	    			direction = (int)(Math.random() * (4-0));
				break;
				
			case 3: // LEFT
				newPosY = posY - speed;				
	    		if(newPosY >= 0 
					&& maze.getMatrix()[posX / mapScale][newPosY / mapScale] == 0
					&& maze.getMatrix()[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == 0
					) 
					posY = newPosY;
	    		else
	    			direction = (int)(Math.random() * (4-0));
	    		break;		
		}
	}
	
	/**
	 * render the enemy sprite
	 * @param g
	 */
	public void render(Graphics g) 
	{	
		g.drawTexture(pacMove, posY, posX, mapScale, mapScale);
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}
	
}
