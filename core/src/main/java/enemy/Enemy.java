package enemy;

import java.util.ArrayList;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

import enums.Direction;
import game.Player;
import maze.Maze;

public abstract class Enemy 
{
	// Movements
	protected int posX;
	protected int posY;
	protected int speed;
	protected int defaultPosX;
	protected int defaultPosY;
	protected Direction direction = Direction.NONE;
	
	// Map stuff and player
	protected int mapScale;	
	protected int[][] matrix;
	protected Player target;
	
	// Animation 
	private int animationCounter = 0;
	private int frameCounter = 0;
	protected Texture enemySprite;
	protected ArrayList<Texture> animationUp;
	protected ArrayList<Texture> animationRight;
	protected ArrayList<Texture> animationDown;
	protected ArrayList<Texture> animationLeft;
	private Direction previousDirection = Direction.NONE;

	
	/**
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param maze : maze instance
	 * @param speed : enemy speed
	 */
	public Enemy(int x, int y, Maze maze, int speed)
	{
		mapScale = maze.getMapScale();
		matrix = maze.getMatrix();	
		
		defaultPosX = x * mapScale;
		defaultPosY = y * mapScale;
		posX = defaultPosX;
		posY = defaultPosY;
		this.speed = speed;
	}
	
	/**
	 * Update movements and animations
	 */
	public void update()
	{
		algorithm();
		updateAnimation();
	}
	
	/**
	 * Load the enemy sprites to render the animations
	 * @param enemyType : type of the enemy (e.g : smartEnemy)
	 */
	protected void loadAnimation(String enemyType) 
	{
		enemySprite = new Texture("enemies/" + enemyType + "/runLeft/0.png");

		animationRight = new ArrayList<Texture>();		
		animationLeft = new ArrayList<Texture>();
		
		int animationSize = 10;
		
		for(int i = 0; i < animationSize; i++)
		{
			animationRight.add(new Texture("enemies/" + enemyType + "/runRight/" + i + ".png"));
			animationLeft.add(new Texture("enemies/" + enemyType + "/runLeft/" + i + ".png"));
		}
	}
	/**
	 * Update enemy's animation according to its direction
	 */
	private void updateAnimation()
	{
		if(previousDirection.ordinal() != direction.ordinal() && (direction != Direction.UP) && direction != Direction.DOWN)
		{
			animationCounter=0;
			frameCounter=0;
		}

		if(direction == Direction.UP && previousDirection == Direction.RIGHT)
		{
			animate(animationRight);
			previousDirection = Direction.RIGHT;
		}
		else if(direction == Direction.UP && previousDirection == Direction.LEFT)
		{
			animate(animationLeft);
			previousDirection = Direction.LEFT;
		}
		else if(direction == Direction.RIGHT)
		{
			animate(animationRight);
			previousDirection = Direction.RIGHT;
		}
		else if(direction == Direction.DOWN && previousDirection == Direction.RIGHT)
		{
			animate(animationRight);
			previousDirection = Direction.RIGHT;
		}
		else if(direction == Direction.DOWN && previousDirection == Direction.LEFT)
		{
			animate(animationLeft);
			previousDirection = Direction.LEFT;
		}
		else if(direction == Direction.LEFT)	
		{
			animate(animationLeft);
			previousDirection = Direction.LEFT;
		}
	}
	
	/**
	 * Animate the enemy
	 * @param animation : animation sprites list
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
			enemySprite = animation.get(animationCounter);
			animationCounter++;
		}		
		frameCounter++;
	}
	
	/**
	 * Set the enemy position to default
	 */
	public void respawn()
	{
		posX = defaultPosX;
		posY = defaultPosY;	
	}
	
	/**
	 * Render the enemy sprite
	 * @param g
	 */
	public void render(Graphics g) 
	{	
		g.drawTexture(enemySprite, posY, posX, mapScale, mapScale);
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
	
	abstract protected void algorithm();	
	public abstract void reset();
}
