package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {
	
	private Texture pacMove;
	private ArrayList<Texture> textures;
	private int posX;
	private int posY;
	private int defaultPosX;
	private int defaultPosY;
	private int speed=5;
	private int direction;
	private boolean alive=true;
	private int[][] matrix;
	
	private int mapScale;	
	
	/**
	 * 
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param scale : tile size
	 */
	public Enemy(int x, int y, int scale, Maze maze)
	{
		pacMove = new Texture("badGuy/pacMoveLeft.png");
		mapScale = scale;
		defaultPosX = x*mapScale;
		defaultPosY = y*mapScale;
		posX = defaultPosX;
		posY = defaultPosY;
		
		matrix = maze.getMatrix();
		
		textures = new ArrayList<Texture>();
		textures.add(new Texture("badGuy/pacMoveUp.png"));
		textures.add(new Texture("badGuy/pacMoveRight.png"));
		textures.add(new Texture("badGuy/pacMoveDown.png"));
		textures.add(new Texture("badGuy/pacMoveLeft.png"));
	}
	
	/**
	 * Update the enemy direction randomly
	 * @param maze : maze instance to check collisions with the walls and different fixed objects
	 */
	public  void  update () 
	{		
		
		if(alive) {
			int newPosX;
	    	int newPosY;
	
	    	switch(direction)
	    	{
				case 0: // UP
					newPosX = posX - speed;				
					if(newPosX >=0 
						&& matrix[newPosX / mapScale][posY / mapScale] == 0
						&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == 0
						)
					{
						posX = newPosX;
						pacMove = textures.get(direction);
					}
					else
						direction = (int)(Math.random() * (4-0));				
					break;
					
				case 1: // RIGHT
					newPosY = posY + speed;	    		
		    		if((newPosY + mapScale - speed) / mapScale < matrix[0].length 
						&& matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
						&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
						)
		    		{
						posY = newPosY;
						pacMove = textures.get(direction);
		    		}
		    		else
		    			direction = (int)(Math.random() * (4-0));
					break;
					
				case 2: // DOWN
					newPosX = posX + speed;	    		
					if((newPosX + mapScale - speed) / mapScale < matrix.length 
							&& matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == 0
							&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == 0
							) 
					{
							posX = newPosX;
							pacMove = textures.get(direction);
					}
		    		else
		    			direction = (int)(Math.random() * (4-0));
					break;
					
				case 3: // LEFT
					newPosY = posY - speed;				
		    		if(newPosY >= 0 
						&& matrix[posX / mapScale][newPosY / mapScale] == 0
						&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == 0
						) 
		    		{
						posY = newPosY;
						pacMove = textures.get(direction);
		    		}
		    		else
		    			direction = (int)(Math.random() * (4-0));
		    		break;		
			}
		}
		else 
		{
			posX = defaultPosX;
			posY = defaultPosY;
			alive = true;
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
	
	public int getSpeed()
	{
		return speed;
	}
	
	public boolean getAlive()
	{
		return alive;
	}
	
	public void setAlive(boolean _alive)
	{
		alive = _alive;
	}
	
}
