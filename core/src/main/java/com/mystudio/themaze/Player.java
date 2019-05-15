
package com.mystudio.themaze;

import java.util.ArrayList;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;


public class Player 
{
	// Movements
	private int posX;
	private int posY;
	private int speed = 4;
	private int defaultPosX;
	private int defaultPosY;
	private int direction=-1;	
	private int nextDirection=-1;

	// States
	private boolean alive;
	private boolean escape;
	
	// Sound event and map stuff
	private EventListener eventListener;
	private int mapScale;	
	int[][] matrix;
	Maze maze;
	
	// Bag and item position
	private ArrayList<Item> bag;	
	private int itemPos = 0;
	
	// Animation
	private Texture defaultPlayerSprite;	
	private Texture playerSprite;
	private ArrayList<Texture> animationLeft;
	private ArrayList<Texture> animationRight;
	private ArrayList<Texture> animationUp;
	private ArrayList<Texture> animationDown;
	private int animCounter=0;
	private int frameCounter=0;
	private int previousDir=-1;


	/**
	 * @param x : default x player's position
	 * @param y : default y player's position
	 * @param _maze : maze instance
	 * @param _eventListener : event listener instance
	 */	
	public Player(int x, int y, Maze _maze, EventListener _eventListener) 
	{
		maze = _maze;
		mapScale = maze.getMapScale();
		matrix = maze.getMatrix();
		eventListener = _eventListener;
		
		defaultPosX = x*mapScale;
		defaultPosY = y*mapScale;
		posX = defaultPosX;
		posY = defaultPosY;		
				
		alive=true;
		escape=false;	
				
		defaultPlayerSprite = new Texture("player/badpacLeft.png");
		playerSprite = defaultPlayerSprite;
		
		bag = new ArrayList<Item>();
//		animationLeft = new ArrayList<Texture>();		
//		animationLeft.add(new Texture("player/badpacLeft.png"));
//		animationLeft.add(new Texture("player/badpacLeftPink.png"));
		
		animationLeft = new ArrayList<Texture>();		
		animationLeft.add(new Texture("player/runLeft/0.png"));
		animationLeft.add(new Texture("player/runLeft/1.png"));
		animationLeft.add(new Texture("player/runLeft/2.png"));
		animationLeft.add(new Texture("player/runLeft/3.png"));
		animationLeft.add(new Texture("player/runLeft/4.png"));
		animationLeft.add(new Texture("player/runLeft/5.png"));
		animationLeft.add(new Texture("player/runLeft/6.png"));
		animationLeft.add(new Texture("player/runLeft/7.png"));
		animationLeft.add(new Texture("player/runLeft/8.png"));
		animationLeft.add(new Texture("player/runLeft/9.png"));
		
//		animationRight = new ArrayList<Texture>();		
//		animationRight.add(new Texture("player/badpacRight.png"));
//		animationRight.add(new Texture("player/badpacRightYellow.png"));
		
		animationRight = new ArrayList<Texture>();		
		animationRight.add(new Texture("player/runRight/0.png"));
		animationRight.add(new Texture("player/runRight/1.png"));
		animationRight.add(new Texture("player/runRight/2.png"));
		animationRight.add(new Texture("player/runRight/3.png"));
		animationRight.add(new Texture("player/runRight/4.png"));
		animationRight.add(new Texture("player/runRight/5.png"));
		animationRight.add(new Texture("player/runRight/6.png"));
		animationRight.add(new Texture("player/runRight/7.png"));
		animationRight.add(new Texture("player/runRight/8.png"));
		animationRight.add(new Texture("player/runRight/9.png"));
		
		/*
		animationUp = new ArrayList<Texture>();		
		animationUp.add(new Texture("player/badpacUp.png"));
		animationUp.add(new Texture("player/badpacUpBlue.png"));
		
		animationDown = new ArrayList<Texture>();		
		animationDown.add(new Texture("player/badpacDown.png"));
		animationDown.add(new Texture("player/badpacDownGreen.png"));
		*/
	}
	
	
	
	/**
	 * Update the player movements and animations according to the user input
	 */
	public  void  update () 
	{		
		int newPosX;
    	int newPosY;
		int topLeft, topRight, botLeft, botRight;
		
		keyEventListener();		
	
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
					
					setNextDir(topLeft, topRight);
				}
	    	}
	    	else if(nextDirection == 1)
	    	{
	    		newPosY = posY + speed;	
	
				if((newPosY + mapScale - speed) / mapScale < matrix[0].length)
				{
					topRight = matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale];
					botRight = matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale]; 
					
		    		setNextDir(topRight, botRight);
				}
	    	}
	    	else if(nextDirection == 2)
	    	{
	    		newPosX = posX + speed;	 
				if((newPosX + mapScale - speed) / mapScale < matrix.length)
				{
					botLeft = matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale];
					botRight = matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale];
						
		    		setNextDir(botLeft, botRight);
				}
	    	}
	    	else if(nextDirection == 3)
	    	{
	    		newPosY = posY - speed;
				if(newPosY >= 0)
				{
					topLeft = matrix[posX / mapScale][newPosY / mapScale];
					botLeft = matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale];
					
		    		setNextDir(topLeft, botLeft);
				}
	    	}
    	}
    	
    	updateAnimation();
	}
	
	/**
	 * 	Listen user's keyboard inputs & act accordingly (change direction / throw Item)
	 */
	private void keyEventListener()
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
		else if(Gdx.input.isKeyJustPressed(Keys.RIGHT))
    	{
			newPosY = posY + speed;	
			if((newPosY + mapScale - speed) / mapScale < maze.getMatrix()[0].length)
			{
				topRight = matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale];
				botRight = matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale]; 
				
	    		setDir(topRight, botRight, 1);
			}
    	}
		else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
    	{
			newPosX = posX + speed;
			if((newPosX + mapScale - speed) / mapScale < maze.getMatrix().length)
			{
				botLeft = matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale];
				botRight = matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale];
					
	    		setDir(botLeft, botRight, 2);
			}
    	}
		else if(Gdx.input.isKeyJustPressed(Keys.LEFT))
    	{
			newPosY = posY - speed;
			if(newPosY >= 0)
			{
				topLeft = matrix[posX / mapScale][newPosY / mapScale];
				botLeft = matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale];
				
	    		setDir(topLeft, botLeft, 3);
			}
    	}		
		else if(Gdx.input.isKeyJustPressed(Keys.A))
    		throwItem();
	}
	
	/**
	 * Check the collision between the player and map elements
	 * @param corner1 : used to check if the corner1 is colliding with anything (map level)
	 * @param corner2 : used to check if the corner2 is colliding with anything (map level)
	 * @param newPos : new position after the supposed movement
	 * @param dir : player direction
	 * @param maze : maze instance
	 */
	private void move(int corner1, int corner2, int newPos, int dir, Maze maze)
	{
		if(dir == 0 || dir == 2)
		{
			if(corner1 == 0 && corner2 == 0)
			{
				posX = newPos;
			}	
			else if(corner1 == 4 || corner2 == 4)
			{
				posX = newPos;
				alive=false;
				eventListener.setGameOverSoundON(true);
			}
			else if(corner1 == 5 && corner2 == 5 && bag.size()==3)
			{
				posX = newPos;
			}
			else if(corner1 == 6 && corner2 == 6)
			{
				posX = newPos;
	    		escape=true;
	    		eventListener.setVictorySoundON(true);
			}
		}
		else if( dir == 1 || dir == 3)
		{
			if(corner1 == 0 && corner2 == 0)
			{
				posY = newPos;
			}	
			else if(corner1 == 4 || corner2 == 4)
			{
				posY = newPos;
				alive=false;
				eventListener.setGameOverSoundON(true);
			}
			else if(corner1 == 5 && corner2 == 5 && bag.size()==3)
			{
				posY = newPos;
			}
			else if(corner1 == 6 && corner2 == 6)
			{
				posY = newPos;
	    		escape=true;
	    		eventListener.setVictorySoundON(true);
			}
		}
		
		if(corner1 == 9 && corner2 == 9)
		{
			eventListener.setTeleporterSoundON(true);
			int[] newTabPos = null;
			if(dir == 0)
			{
				newTabPos = maze.teleportPlayer(newPos  / mapScale, posY / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
			else if(dir == 1)
			{
				newTabPos = maze.teleportPlayer(posX / mapScale, (newPos+mapScale) / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
			else if(dir == 2)
			{
				newTabPos = maze.teleportPlayer((newPos+mapScale) / mapScale, posY / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
			else if(dir == 3)
			{
				newTabPos = maze.teleportPlayer(posX / mapScale, newPos / mapScale);
				posX = newTabPos[0]*mapScale;
				posY = newTabPos[1]*mapScale;
			}
		}
	}
	
	/**
	 * Check and change the direction
	 * @param corner1 : used to check if the direction is available according to the corner1
	 * @param corner2 : used to check if the direction is available according to the corner2
	 * @param dir : player direction
	 */
	private void setDir(int corner1, int corner2, int dir)
	{		
		if(corner1 == 0 && corner2 == 0)
		{
			direction = dir;
			nextDirection=-1;
			
		}
		else if(corner1 == 4 && corner2 == 4)
		{
			direction = dir;
			nextDirection=-1;
		}
		else if(corner1 == 5 && corner2 == 5)
		{
			direction = dir;
			nextDirection=-1;
		}
		else if(corner1 == 6 && corner2 == 6)
		{
			direction = dir;
			nextDirection=-1;
		}
		else if(corner1 == 9 && corner2 == 9)
		{
			direction = dir;
			nextDirection=-1;
		}
		else
			nextDirection = dir;
	}
	
	/**
	 * Set the direction to <type>nextDirection</type> if available
	 * @param corner1 : used to check if the direction is available according to the corner1
	 * @param corner2 : used to check if the direction is available according to the corner2
	 */
	private void setNextDir(int corner1, int corner2)
	{
		if(corner1 == 0 && corner2 == 0)
		{
			direction = nextDirection;
			nextDirection=-1;
		}
		if(corner1 == 4 && corner2 == 4)
		{
			direction = nextDirection;
			nextDirection=-1;
		}
		else if(corner1 == 5 && corner2 == 5)
		{
			direction = nextDirection;
			nextDirection=-1;
		}
		else if(corner1 == 6 && corner2 == 6)
		{
			direction = nextDirection;
			nextDirection=-1;
		}
		else if(corner1 == 9 && corner2 == 9)
		{
			direction = nextDirection;
			nextDirection=-1; 
		}			
	}
	
	/**
	 * Add an item to the player bag
	 * @param item : item instance
	 */
	public void addItem(Item item) 
	{
		bag.add(item);
		item.setPosX(0);
		item.setPosY(itemPos*mapScale);
		itemPos++;
		
		if(bag.size()==3)
			eventListener.setDoorOpenSoundON(true);
	}
	
	/**
	 * Throw an item from the player's bag
	 */
	public void throwItem()
	{
		if(itemPos>0)
		{
			Item item = bag.get(itemPos-1);			
			eventListener.setThrowingObjectSoundON(true);
			item.animation(posX,  posY, 5, 8, direction);			
			bag.remove(itemPos-1);
			itemPos--;
			
			if(bag.size()==2)
				eventListener.setDoorClosedSoundON(true);
		}
	}
	
	
	/**
	 * Render the player sprite
	 * @param g
	 */
	public void render(Graphics g) 
	{			
		g.drawTexture(playerSprite, posY, posX, mapScale, mapScale);

	}
	
	/**
	 * Update player's animation according to its direction
	 */
	private void updateAnimation()
	{
		if(previousDir != direction && direction !=0 && direction != 2)
		{
			animCounter=0;
			frameCounter=0;
		}
		
//		if(direction == 0)
//		{
//			animate(animationUp);
//			previousDir = 0;
//		}
//		else if(direction == 1)
//		{
//			animate(animationRight);
//			previousDir = 1;
//		}
//		else if(direction == 2)
//		{
//			animate(animationDown);
//			previousDir = 2;
//		}
//		else if(direction == 3)	
//		{
//			animate(animationLeft);
//			previousDir = 3;
//		}
		
		if(direction == 0 && previousDir == 3)
		{
			animate(animationLeft);
			previousDir = 3;
			System.out.println("haut-gauche");
		}
		else if(direction == 0 && previousDir == 1)
		{
			animate(animationRight);
			previousDir = 1;
			System.out.println("haut-droit");
		}
		else if(direction == 1)
		{
			animate(animationRight);
			previousDir = 1;
		}
		else if(direction == 2 && previousDir == 3)
		{
			animate(animationLeft);
			previousDir = 3;
			System.out.println("bas-gauche");
		}
		else if(direction == 2 && previousDir == 1)
		{
			animate(animationRight);
			previousDir = 1;
			System.out.println("bas-droit");
		}
		else if(direction == 3)	
		{
			animate(animationLeft);
			previousDir = 3;
		}
	}
	
	/**
	 * Animate the player
	 * @param anim : animation sprites list
	 */
	private void animate(ArrayList<Texture> anim)
	{
		if(frameCounter==animCounter*10)
		{
			if(animCounter == anim.size())
			{
				animCounter=0;
				frameCounter=0;
			}
			playerSprite = anim.get(animCounter);
			animCounter++;
		}		
		frameCounter++;
	}

	/**
	 * Set all the player's information to a default state
	 */
	public void restart()
	{
		playerSprite = defaultPlayerSprite;
		posX = defaultPosX;
		posY = defaultPosY;
		nextDirection = -1;
		direction = -1;
		bag.clear();	
		itemPos = 0;
		escape = false;
		alive = true;
	}
	
	public int getSpeed()
	{
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
	
	public void setItemPos(int pos)
	{
		itemPos=0;
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

