package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Enemy {
	
	private int posX;
	private int posY;
	
	private int defaultPostionX = 4;
	private int defaultPositionY = 8;
	
	private int speed;//Multiple of mapScale
	
	private int mapScale;
	
	private int direction;
	
	private Texture pacMove;
	
	public Enemy(int mapScale)
	{
		posX = defaultPostionX*mapScale;
		posY = defaultPositionY*mapScale;
		pacMove = new Texture("pacMoveLeft.png");
		speed = 10;
		this.mapScale = mapScale;
		direction = 0;
	}
	
	public  void  update (Maze maze) 
	{ 
		
		int newPosX;
    	int newPosY;
		//System.out.println((int) (Math.random() * (4-0)));

    	switch(direction)
    	{
			case 0:
				newPosX= posX-speed;
				
				if(newPosX/mapScale>=0 
					&& maze.getMatrix()[newPosX/mapScale][posY/mapScale] == 0
					&& maze.getMatrix()[newPosX/mapScale][(posY+mapScale-speed)/mapScale] == 0
					)
					posX =newPosX;
				else
					direction = (int) (Math.random() * (4-0));
				break;
			case 1:
				newPosY = posY+speed;
	    		
	    		if((newPosY+mapScale-speed)/mapScale<maze.getMatrix()[0].length 
					&& maze.getMatrix()[posX/mapScale][(newPosY+mapScale-speed)/mapScale] == 0
					&& maze.getMatrix()[(posX+mapScale-speed)/mapScale][(newPosY+mapScale-speed)/mapScale] == 0
					)
					posY = newPosY;
	    		else
	    			direction = (int) (Math.random() * (4-0));
				break;
			case 2:
				newPosX = posX+speed;
	    		
				if((newPosX+mapScale-speed)/mapScale<maze.getMatrix().length 
						&& maze.getMatrix()[(newPosX+mapScale-speed)/mapScale][posY/mapScale] == 0
						&& maze.getMatrix()[(newPosX+mapScale-speed)/mapScale][(posY+mapScale-speed)/mapScale] == 0
						) 
						posX = newPosX;
	    		else
	    			direction = (int) (Math.random() * (4-0));
				break;
			case 3:
				newPosY = posY-speed;
				
	    		if(newPosY>=0 
					&& maze.getMatrix()[posX/mapScale][newPosY/mapScale] == 0
					&& maze.getMatrix()[(posX+mapScale-speed)/mapScale][newPosY/mapScale] == 0
					) 
					posY = newPosY;
	    		else
	    			direction = (int) (Math.random() * (4-0));
	    		break;		
		}
	}
	
	public void render(Graphics g) 
	{	
		g.drawTexture(pacMove, posY, posX, mapScale, mapScale);
	}
	
}
