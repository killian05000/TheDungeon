
package com.mystudio.themaze;

import java.util.ArrayList;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Player 
{
	private Texture badpac;	
	private int posX;
	private int posY;
	private int speed = 4;
	private int direction;	
	private boolean alive;
	
	private int mapScale;	

	private ArrayList<Item> bag;	
	private int itemPos = 0;
	
	public enum mapObject
	{
		
	}

	/**
	 * @param x : default x player's position
	 * @param y : default y player's position
	 * @param scale : tile size
	 */
	public Player(int x, int y, int scale) 
	{
		badpac = new Texture("player/badpacLeft.png");
		mapScale = scale;
		posX = x*mapScale;
		posY = y*mapScale;
		alive=true;
				
		bag = new ArrayList<Item>();
	}
	
	/**
	 * Update the player direction and the player movements according to the user input
	 * @param maze : maze instance to check collisions with the walls and different fixed objects
	 */
	public  void  update (Maze maze) 
	{		
		int newPosX;
    	int newPosY;
    	
		if(Gdx.input.isKeyJustPressed(Keys.UP)) 
		{
			badpac = new Texture("player/badpacUp.png");
			direction=0;
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
    	{
    		badpac = new Texture("player/badpacRight.png");
    		direction=1;
    	}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
    	{
    		badpac = new Texture("player/badpacDown.png");
    		direction=2;
    	}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT))
    	{
    		badpac = new Texture("player/badpacLeft.png");
    		direction=3;
    	}
		if(Gdx.input.isKeyJustPressed(Keys.A))
    	{
    		throwItem();
    	}
		
		int topLeft, topRight, botLeft, botRight;
  	
    	switch(direction)
    	{
			case 0: // UP
				newPosX = posX - speed;			
				if(newPosX >= 0)
				{
					topLeft = maze.getMatrix()[newPosX / mapScale][posY / mapScale];
					topRight = maze.getMatrix()[newPosX / mapScale][(posY + mapScale - speed) / mapScale];
					
					if(topLeft == 0 && topRight == 0)
						posX = newPosX;
					if(topLeft == 9 && topRight == 9)
		    		{
						//posY = newPosY;
						int[] newPos = maze.teleportPlayer(newPosX  / mapScale, posY / mapScale);
						posX = newPos[0]*mapScale;
						posY = newPos[1]*mapScale;
		    		}
				}
				break;
				
			case 1: // RIGHT
				newPosY = posY + speed;
				if((newPosY + mapScale - speed) / mapScale < maze.getMatrix()[0].length)
				{  		
					topRight = maze.getMatrix()[posX / mapScale][(newPosY + mapScale - speed) / mapScale];
					botRight = maze.getMatrix()[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale]; 
					
		    		if(topRight == 0 && botRight == 0)
						posY = newPosY;
		    		if(topRight == 9 && botRight == 9)
		    		{
						//posY = newPosY;
						int[] newPos = maze.teleportPlayer(posX / mapScale, (newPosY+mapScale) / mapScale);
						posX = newPos[0]*mapScale;
						posY = newPos[1]*mapScale;
		    		}
				}
	    		break;
	    		
			case 2: // DOWN
				newPosX = posX + speed;	 
				if((newPosX + mapScale - speed) / mapScale < maze.getMatrix().length)
				{
					botLeft = maze.getMatrix()[(newPosX + mapScale - speed) / mapScale][posY / mapScale];
					botRight = maze.getMatrix()[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale];
					
		    		if(botLeft == 0 && botRight == 0)
						posX = newPosX;		
		    		if(botLeft == 9 && botRight == 9)
		    		{
						//posY = newPosY;
						int[] newPos = maze.teleportPlayer((newPosX+mapScale) / mapScale, posY / mapScale);
						posX = newPos[0]*mapScale;
						posY = newPos[1]*mapScale;
		    		}
				}
				break;
				
			case 3: // LEFT
				newPosY = posY - speed;	
				if(newPosY >= 0)
				{
					topLeft = maze.getMatrix()[posX / mapScale][newPosY / mapScale];
					botLeft = maze.getMatrix()[(posX + mapScale - speed) / mapScale][newPosY / mapScale];
					
		    		if(topLeft == 0 && botLeft == 0)
						posY = newPosY;
		    		if(topLeft == 9 && botLeft == 9)
		    		{
						//posY = newPosY;
						int[] newPos = maze.teleportPlayer(posX / mapScale, newPosY / mapScale);
						posX = newPos[0]*mapScale;
						posY = newPos[1]*mapScale;
		    		}
				}
	    		
	    		break;		
		}
	}
	
	/**
	 * Add an item to the player bag
	 * @param item : item instance
	 */
	public void addItem(Item item) {
		bag.add(item);
		item.setPosX(0);
		item.setPosY(itemPos*mapScale);
		itemPos++;
	}
	
	public void throwItem()
	{
		if(itemPos>0)
		{
			System.out.println("itemPos : "+itemPos);
			bag.get(itemPos-1);
			
			if(direction==0)
			{
				bag.get(itemPos-1).setPosX(posX-3*mapScale);
				bag.get(itemPos-1).setPosY(posY);
			}
			else if(direction==1)
			{
				bag.get(itemPos-1).setPosX(posX);
				bag.get(itemPos-1).setPosY(posY+3*mapScale);
			}
			else if(direction==2)
			{
				bag.get(itemPos-1).setPosX(posX+3*mapScale);
				bag.get(itemPos-1).setPosY(posY);
			}
			else if(direction==3)
			{
				bag.get(itemPos-1).setPosX(posX);
				bag.get(itemPos-1).setPosY(posY-3*mapScale);
			}
			
			bag.remove(itemPos-1);
			itemPos--;
		}
	}
	
	/**
	 * Render the player sprite
	 * @param g
	 */
	public void render(Graphics g) 
	{	
		g.drawTexture(badpac, posY, posX, mapScale, mapScale);
	}	

	public int getSpeed() {
		return speed;
	}
	
	public int getPosX() 
	{
		return posX;
	}
	
	public int getPosY() 
	{
		return posY;
	}
	
	public ArrayList<Item> getBag()
	{
		return bag;
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

