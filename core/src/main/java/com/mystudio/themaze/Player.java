
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
	private int speed = 5;
	private int direction;	
	
	private int mapScale;	

	private ArrayList<Item> items;	
	private int itemPos = 0;

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
				
		items = new ArrayList<Item>();
	}
	
	/**
	 * Update the player direction and the player movements according to the user input
	 * @param maze : maze instance to check collisions with the walls and different fixed objects
	 */
	public  void  update (Maze maze) 
	{		
		int newPosX;
    	int newPosY;
    	
		if(Gdx.input.isKeyPressed(Keys.UP)) 
		{
			badpac = new Texture("player/badpacUp.png");
			direction=0;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT))
    	{
    		badpac = new Texture("player/badpacRight.png");
    		direction=1;
    	}
		else if(Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		badpac = new Texture("player/badpacDown.png");
    		direction=2;
    	}
		else if(Gdx.input.isKeyPressed(Keys.LEFT))
    	{
    		badpac = new Texture("player/badpacLeft.png");
    		direction=3;
    	}
    	
    	switch(direction)
    	{
			case 0: // UP
				newPosX = posX - speed;				
				if(newPosX >= 0 
					&& maze.getMatrix()[newPosX / mapScale][posY / mapScale] == 0
					&& maze.getMatrix()[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == 0
					)
					posX = newPosX;
				break;
				
			case 1: // RIGHT
				newPosY = posY + speed;	    		
	    		if((newPosY + mapScale - speed) / mapScale < maze.getMatrix()[0].length 
					&& maze.getMatrix()[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
					&& maze.getMatrix()[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
					)
					posY = newPosY;
	    		break;
	    		
			case 2: // DOWN
				newPosX = posX + speed;	    		
	    		if((newPosX + mapScale - speed) / mapScale < maze.getMatrix().length 
					&& maze.getMatrix()[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == 0
					&& maze.getMatrix()[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == 0
					) 
					posX = newPosX;		
				break;
				
			case 3: // LEFT
				newPosY = posY - speed;				
	    		if(newPosY >= 0 
					&& maze.getMatrix()[posX / mapScale][newPosY / mapScale] == 0
					&& maze.getMatrix()[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == 0
					) 
					posY = newPosY;
	    		break;		
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
	
	/**
	 * Add an item to the player bag
	 * @param item : item instance
	 */
	public void addItem(Item item) {
		items.add(item);
		item.setPosX(0);
		item.setPosY(itemPos*mapScale);
		itemPos++;
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
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
}

