
package game;

import java.util.ArrayList;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

import enums.Direction;
import enums.MapObject;
import maze.Item;
import maze.Maze;
import music.SoundPlayer;

public class Player 
{
	// Movements
	private int posX;
	private int posY;
	private int newPosX;
	private int newPosY;
	private int speed = 4;
	private int defaultPosX;
	private int defaultPosY;
	private Direction direction = Direction.NONE;	
	private Direction nextDirection = Direction.NONE;
	private ArrayList<ArrayList<Integer>> movementModifier;

	// States
	private boolean alive;
	private boolean escape;
	
	// Sound event and map stuff
	private SoundPlayer musicPlayer;
	private int mapScale;	
	int[][] matrix;
	Maze maze;
	
	// Bag and item position
	private int requiredObjectNumber = 3;
	private ArrayList<Item> bag;
	private int itemPos = 0;
	
	// Animation
	private Texture defaultPlayerSprite;	
	private Texture playerSprite;
	private int frameCounter = 0;
	private int animationCounter = 0;	
	private ArrayList<Texture> animationLeft;
	private ArrayList<Texture> animationRight;	
	private Direction previousDirection = Direction.NONE;


	/**
	 * @param x : default x player's position
	 * @param y : default y player's position
	 * @param maze : maze instance
	 * @param musicPlayer : musicPlayer instance
	 */	
	public Player(int x, int y, Maze maze, SoundPlayer musicPlayer) 
	{
		this.maze = maze;
		mapScale = maze.getMapScale();
		matrix = maze.getMatrix();
		this.musicPlayer = musicPlayer;
		
		defaultPosX = x * mapScale;
		defaultPosY = y * mapScale;
		posX = defaultPosX;
		posY = defaultPosY;		
		newPosX = posX;
		newPosY = posY;
		
		movementModifier = new ArrayList<ArrayList<Integer>>();
		movementModifier.add(new ArrayList<Integer>());
		movementModifier.get(0).add(-1);
		movementModifier.get(0).add(0);
		
		movementModifier.add(new ArrayList<Integer>());
		movementModifier.get(1).add(0);
		movementModifier.get(1).add(1);

		movementModifier.add(new ArrayList<Integer>());
		movementModifier.get(2).add(1);
		movementModifier.get(2).add(0);

		movementModifier.add(new ArrayList<Integer>());
		movementModifier.get(3).add(0);
		movementModifier.get(3).add(-1);		
				
		alive = true;
		escape = false;	
				
		defaultPlayerSprite = new Texture("player/runLeft/0.png");
		playerSprite = defaultPlayerSprite;
		loadTextures();
		
		bag = new ArrayList<Item>();
	}
	
	/**
	 * Load all the player's textures
	 */
	public void loadTextures()
	{
		animationRight = new ArrayList<Texture>();		
		animationLeft = new ArrayList<Texture>();
		
		int animationSize = 10;
		
		for(int i = 0; i < animationSize; i++)
		{
			animationRight.add(new Texture("player/runRight/" + i + ".png"));
			animationLeft.add(new Texture("player/runLeft/" + i + ".png"));
		}
	}
	
	/**
	 * Set the player direction to the chosen direction if available, if not, save it in <type>nextDirection</type>
	 * @param direction : direction wanted
	 */
	public void setDirection(Direction direction)
	{
		int corners[] = calculateCorners(direction.ordinal());

		if(directionAvailable(corners[0], corners[1])) 
		{
			this.direction = direction;
			nextDirection = Direction.NONE;
		}
		else
			nextDirection = direction;
		checkMovements();
	}
	
	/**
	 * Check if a <type>nextDirection</type> is saved and move the player
	 * if it's available. If not check if the current direction is available,
	 * move the player if it is, stay put if it's not.
	 */
	public void checkMovements()
	{		
		if(direction == Direction.NONE)
			return;		

		int[] corners;
		
		if(nextDirection != Direction.NONE)
		{
			corners = calculateCorners(nextDirection.ordinal());
			
			if(directionAvailable(corners[0], corners[1]))
			{
				direction = nextDirection;
				move();
				nextDirection = Direction.NONE;
				return;
			}
		}

		corners = calculateCorners(direction.ordinal());
		
		if(!collide(corners[0], corners[1]))
    	{
    		move();
    	}
    	else
    	{
    		nextDirection = Direction.NONE;
			triggerCollision(corners[0], corners[1], maze);
    	}
		
		updateAnimation();
	}
	
	/**
	 * According to the direction picked, calculate which <type>MapObject</type> 
	 * the 2 corners involved are onto.
	 * @param direction : the chosen direction
	 * @return a table of two corners
	 */
	public int[] calculateCorners(int direction)
	{
		newPosX = posX + movementModifier.get(direction).get(0) * speed; 
    	newPosY = posY + movementModifier.get(direction).get(1) * speed;
    	
    	int[] corners = new int[2];    	
    	corners[0] = 1;
    	corners[1] = 1;
    	    	
    	if(direction == 0 && newPosX + speed <= 0)
    		return corners;
		if(direction == 1 && (newPosY + mapScale - speed)/mapScale >= matrix[0].length)
			return corners;
		if(direction == 2 && (newPosX + mapScale - speed)/mapScale >= matrix.length)
			return corners;
		if(direction == 3 && newPosY + speed <= 0)
			return corners;
	    	
    	int topLeft = matrix[newPosX / mapScale][newPosY / mapScale];
    	int topRight = matrix[newPosX / mapScale][(newPosY + mapScale - speed) / mapScale];
    	int botRight = matrix[(newPosX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale];
    	int botLeft = matrix[(newPosX + mapScale - speed) / mapScale][newPosY / mapScale];

    	if(direction == Direction.UP.ordinal())
    	{
    		corners[0] = topLeft;
    		corners[1] = topRight;
    	}
		else if(direction == Direction.RIGHT.ordinal())
		{
			corners[0] = topRight;
    		corners[1] = botRight;
    	}
		else if(direction == Direction.DOWN.ordinal())
		{
			corners[0] = botLeft;
			corners[1] = botRight;
    	}
		else if(direction == Direction.LEFT.ordinal())
		{
			corners[0] = topLeft;
			corners[1] = botLeft;
    	}
    	
    	return corners;
	}
	
	/**
	 * Set the player's position to the new position
	 */
	public void move()
	{
		posX = newPosX;
		posY = newPosY;
	}
	
	/**
	 * Trigger an effect according to which <type>MapObject</type>
	 * the corners are onto.
	 * @param corner1
	 * @param corner2
	 * @param maze
	 */
	public void triggerCollision(int corner1, int corner2, Maze maze)
	{
		if(corner1 == MapObject.TRAP.ordinal() || corner2 == MapObject.TRAP.ordinal())
		{
			move();
			alive = false;
			musicPlayer.setGameOverSoundON(true);
		}
		else if(corner1 == MapObject.DOOR.ordinal() && corner2 == MapObject.DOOR.ordinal() && bag.size() == requiredObjectNumber)
		{
			move();
		}
		else if(corner1 == MapObject.EXIT.ordinal() && corner2 == MapObject.EXIT.ordinal())
		{
			move();
    		escape = true;
    		musicPlayer.setVictorySoundON(true);
		}
		else if(corner1 == MapObject.TELEPORTER.ordinal() && corner2 == MapObject.TELEPORTER.ordinal())
		{
			musicPlayer.setTeleporterSoundON(true);
			int[] newTabPos = null;
			if(direction == Direction.UP)
				newTabPos = maze.teleportPlayer(newPosX  / mapScale, posY / mapScale);
			else if(direction == Direction.RIGHT)
				newTabPos = maze.teleportPlayer(posX / mapScale, (newPosY + mapScale) / mapScale);
			else if(direction == Direction.DOWN)
				newTabPos = maze.teleportPlayer((newPosX + mapScale) / mapScale, posY / mapScale);
			else if(direction == Direction.LEFT)
				newTabPos = maze.teleportPlayer(posX / mapScale, newPosY / mapScale);
			
			newPosX = newTabPos[0] * mapScale;
			newPosY = newTabPos[1] * mapScale;
			move();
		}	
	}
	
	/**
	 * Check if the two corners are onto something else than an ALLEY,
	 * if they are, they have collided with something.
	 * @param corner1
	 * @param corner2
	 * @return true if there is a collision
	 */
	public boolean collide(int corner1, int corner2)
	{
		return (corner1 != MapObject.ALLEY.ordinal() || corner2 != MapObject.ALLEY.ordinal());
	}
	
	/**
	 * Check if the direction is available based on the two corners given
	 * @param corner1
	 * @param corner2
	 * @return true if available
	 */
	public boolean directionAvailable(int corner1, int corner2)
	{
		if(corner1 == MapObject.ALLEY.ordinal() && corner2 == MapObject.ALLEY.ordinal())
			return true;
		if(corner1 == MapObject.TRAP.ordinal() && corner2 == MapObject.TRAP.ordinal())
			return true;
		else if(corner1 == MapObject.DOOR.ordinal() && corner2 == MapObject.DOOR.ordinal() && bag.size() == requiredObjectNumber)
			return true;
		else if(corner1 == MapObject.EXIT.ordinal() && corner2 == MapObject.EXIT.ordinal())
			return true;
		else if(corner1 == MapObject.TELEPORTER.ordinal() && corner2 == MapObject.TELEPORTER.ordinal())
			return true;
		else
			return false;
	}
	
	/**
	 * Add an item to the player bag
	 * @param item : item instance
	 */
	public void addItem(Item item) 
	{
		bag.add(item);
		item.setPosX(0);
		item.setPosY(itemPos * mapScale);
		itemPos++;
		
		if(bag.size() == requiredObjectNumber)
			musicPlayer.setDoorOpenSoundON(true);
	}
	
	/**
	 * Throw an item from the player's bag
	 */
	public void throwItem()
	{
		if(itemPos > 0)
		{
			Item item = bag.get(itemPos - 1);			
			musicPlayer.setThrowingObjectSoundON(true);
			item.animate(posX,  posY, 5, 8, direction.ordinal());			
			bag.remove(itemPos - 1);
			itemPos--;
			
			if(bag.size() == requiredObjectNumber - 1)
				musicPlayer.setDoorClosedSoundON(true);
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
		if(previousDirection != direction && (direction != Direction.UP && direction != Direction.DOWN))
		{
			animationCounter = 0;
			frameCounter = 0;
		}
		
		if(direction == Direction.UP && previousDirection == Direction.LEFT)
		{
			animate(animationLeft);
			previousDirection = Direction.LEFT;
		}
		else if(direction == Direction.UP && previousDirection == Direction.RIGHT)
		{
			animate(animationRight);
			previousDirection = Direction.RIGHT;
		}
		else if(direction == Direction.RIGHT)
		{
			animate(animationRight);
			previousDirection = Direction.RIGHT;
		}
		else if(direction == Direction.DOWN && previousDirection == Direction.LEFT)
		{
			animate(animationLeft);
			previousDirection = Direction.LEFT;
		}
		else if(direction == Direction.DOWN && previousDirection == Direction.RIGHT)
		{
			animate(animationRight);
			previousDirection = Direction.RIGHT;
		}
		else if(direction == Direction.LEFT)	
		{
			animate(animationLeft);
			previousDirection = Direction.LEFT;
		}
	}
	
	/**
	 * Animate the player
	 * @param anim : animation sprites list
	 */
	private void animate(ArrayList<Texture> animation)
	{
		if(frameCounter == animationCounter * 5)
		{
			if(animationCounter == animation.size())
			{
				animationCounter = 0;
				frameCounter = 0;
			}
			playerSprite = animation.get(animationCounter);
			animationCounter++;
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
		nextDirection = Direction.NONE;
		direction = Direction.NONE;
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
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public boolean getEscape()
	{
		return escape;
	}

	public int getRequiredObjectNumber() 
	{
		return requiredObjectNumber;
	}
}

