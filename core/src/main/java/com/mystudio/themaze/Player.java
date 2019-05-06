
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
	private ArrayList<Texture> textures;
	private int posX;
	private int posY;
	private int speed = 4;
	private int direction;	
	private boolean alive;
	private boolean escape;
	private int nextDirection=-1;
	int[][] matrix;
	Maze maze;
	
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
	public Player(int x, int y, int scale, Maze maze) 
	{
		badpac = new Texture("player/badpacLeft.png");
		mapScale = scale;
		posX = x*mapScale;
		posY = y*mapScale;
		alive=true;
		escape=false;
		this.maze= maze;
		matrix = maze.getMatrix();
				
		bag = new ArrayList<Item>();
		textures = new ArrayList<Texture>();
		textures.add(new Texture("player/badpacUp.png"));
		textures.add(new Texture("player/badpacRight.png"));
		textures.add(new Texture("player/badpacDown.png"));
		textures.add(new Texture("player/badpacLeft.png"));
		
	}
	
	/**
	 * Update the player direction and the player movements according to the user input
	 * @param maze : maze instance to check collisions with the walls and different fixed objects
	 */
	public  void  update () 
	{		
		int newPosX;
    	int newPosY;
		int topLeft, topRight, botLeft, botRight;
		
    	
		if(Gdx.input.isKeyJustPressed(Keys.UP)) 
		{
			newPosX = posX - speed;	
			if(newPosX >= 0)
			{
				
				topLeft = matrix[newPosX / mapScale][posY / mapScale];
				topRight = matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale];
				
				setDir(topLeft, topRight, 0);
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
    	{
			newPosY = posY + speed;	

			if((newPosY + mapScale - speed) / mapScale < maze.getMatrix()[0].length)
			{
				topRight = matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale];
				botRight = matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale]; 
				
	    		setDir(topRight, botRight, 1);
			}
    	}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
    	{
			newPosX = posX + speed;
			if((newPosX + mapScale - speed) / mapScale < maze.getMatrix().length)
			{
				botLeft = matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale];
				botRight = matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale];
					
	    		setDir(botLeft, botRight, 2);
			}
    	}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT))
    	{
			newPosY = posY - speed;
			if(newPosY >= 0)
			{
				topLeft = matrix[posX / mapScale][newPosY / mapScale];
				botLeft = matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale];
				
	    		setDir(topLeft, botLeft, 3);
			}
    	}
		if(Gdx.input.isKeyJustPressed(Keys.A))
    	{
    		throwItem();
    	}

	
    	switch(direction)
    	{
			case 0: // UP
				newPosX = posX - speed;			
				if(newPosX >= 0)
				{
					topLeft = matrix[newPosX / mapScale][posY / mapScale];
					topRight = matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale];
					
					move(topLeft, topRight, newPosX, 0, maze);
				}
				break;
				
			case 1: // RIGHT
				newPosY = posY + speed;
				if((newPosY + mapScale - speed) / mapScale < maze.getMatrix()[0].length)
				{  		
					topRight = matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale];
					botRight = matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale]; 
					
		    		move(topRight, botRight, newPosY, 1, maze);
				}
	    		break;
	    		
			case 2: // DOWN
				newPosX = posX + speed;	 
				if((newPosX + mapScale - speed) / mapScale < matrix.length)
				{
					botLeft = matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale];
					botRight = matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale];
					
		    		move(botLeft, botRight, newPosX, 2, maze);
				}
				break;
				
			case 3: // LEFT
				newPosY = posY - speed;	
				if(newPosY >= 0)
				{
					topLeft = matrix[posX / mapScale][newPosY / mapScale];
					botLeft = matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale];
					
		    		move(topLeft, botLeft, newPosY, 3, maze);
				}
	    		
	    		break;		
		}
    	
    	if(nextDirection != -1)
    	{
	    	if(nextDirection == 0)
	    	{
	    		newPosX = posX - speed;	
				if(newPosX >= 0)
				{
					topLeft = matrix[newPosX / mapScale][posY / mapScale];
					topRight = matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale];
					
					setNextDir(topLeft, topRight, 0);
				}
	    	}
	    	else if(nextDirection == 1)
	    	{
	    		newPosY = posY + speed;	
	
				if((newPosY + mapScale - speed) / mapScale < matrix[0].length)
				{
					topRight = matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale];
					botRight = matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale]; 
					
		    		setNextDir(topRight, botRight, 1);
				}
	    	}
	    	else if(nextDirection == 2)
	    	{
	    		newPosX = posX + speed;	 
				if((newPosX + mapScale - speed) / mapScale < matrix.length)
				{
					botLeft = matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale];
					botRight = matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale];
						
		    		setNextDir(botLeft, botRight, 2);
				}
	    	}
	    	else if(nextDirection == 3)
	    	{
	    		newPosY = posY - speed;
				if(newPosY >= 0)
				{
					topLeft = matrix[posX / mapScale][newPosY / mapScale];
					botLeft = matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale];
					
		    		setNextDir(topLeft, botLeft, 3);
				}
	    	}
    	}
	}
	
	public void move(int corner1, int corner2, int newPos, int dir, Maze maze)
	{
		if(dir == 0 || dir == 2)
		{
			if(corner1 == 0 && corner2 == 0)
			{
				posX = newPos;
			}		    		
			else if(corner1 == 5 && corner2 == 5 && bag.size()==3)
			{
				posX = newPos;
			}
			else if(corner1 == 6 && corner2 == 6)
			{
				posX = newPos;
	    		escape=true;
			}
		}
		else if( dir == 1 || dir == 3)
		{
			if(corner1 == 0 && corner2 == 0)
			{
				posY = newPos;
			}		    		
			else if(corner1 == 5 && corner2 == 5 && bag.size()==3)
			{
				posY = newPos;
			}
			else if(corner1 == 6 && corner2 == 6)
			{
				posY = newPos;
	    		escape=true;
			}
		}
		
		if(corner1 == 9 && corner2 == 9)
		{
			if(dir == 0)
			{
				int[] newTabPos = maze.teleportPlayer(newPos  / mapScale, posY / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
			else if(dir == 1)
			{
				int[] newTabPos = maze.teleportPlayer(posX / mapScale, (newPos+mapScale) / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
			else if(dir == 2)
			{
				int[] newTabPos = maze.teleportPlayer((newPos+mapScale) / mapScale, posY / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
			else if(dir == 3)
			{
				int[] newTabPos = maze.teleportPlayer(posX / mapScale, newPos / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
		}
	}
	
	public void setDir(int corner1, int corner2, int dir)
	{		
		if(corner1 == 0 && corner2 == 0)
		{
			direction = dir;
			badpac = textures.get(dir);
			nextDirection=-1;
			
		}
		else if(corner1 == 5 && corner2 == 5)
		{
			direction = dir;
			badpac = textures.get(dir);
			nextDirection=-1;
		}
		else if(corner1 == 6 && corner2 == 6)
		{
			direction = dir;
			badpac = textures.get(dir);
			nextDirection=-1;
		}
		else if(corner1 == 9 && corner2 == 9)
		{
			direction = dir;
			badpac = textures.get(dir);
			nextDirection=-1;
		}
		else
			nextDirection = dir;
	}
	
	public void setNextDir(int corner1, int corner2, int dir)
	{
		if(corner1 == 0 && corner2 == 0)
		{
			direction = nextDirection;
			badpac = textures.get(dir);
			nextDirection=-1;
		}
		else if(corner1 == 5 && corner2 == 5)
		{
			direction = nextDirection;
			badpac = textures.get(dir);
			nextDirection=-1;
		}
		else if(corner1 == 6 && corner2 == 6)
		{
			direction = nextDirection;
			badpac = textures.get(dir);
			nextDirection=-1;
		}
		else if(corner1 == 9 && corner2 == 9)
		{
			direction = nextDirection;
			badpac = textures.get(dir);
			nextDirection=-1; 
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
			Item item = bag.get(itemPos-1);
			
			if(direction==0)
			{
				item.setPosX(posX-3*mapScale);
				item.setPosY(posY);
			}
			else if(direction==1)
			{
				item.setPosX(posX);
				item.setPosY(posY+3*mapScale);
			}
			else if(direction==2)
			{
				item.setPosX(posX+3*mapScale);
				item.setPosY(posY);
			}
			else if(direction==3)
			{
				item.setPosX(posX);
				item.setPosY(posY-3*mapScale);
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
	
	public boolean getEscape()
	{
		return escape;
	}
}

