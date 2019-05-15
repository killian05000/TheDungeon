package com.mystudio.themaze;

import java.util.ArrayList;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public abstract class Enemy 
{
	// Movements
	protected int posX;
	protected int posY;
	protected int speed;
	protected int defaultPosX;
	protected int defaultPosY;
	protected int direction;
	
	// Map stuff and player
	protected int mapScale;	
	protected int[][] matrix;
	protected Player target;
	
	// Animation 
	private int previousDir=-1;
	private int animCounter=0;
	private int frameCounter=0;
	protected Texture enemySprite;
	protected ArrayList<Texture> animationUp;
	protected ArrayList<Texture> animationRight;
	protected ArrayList<Texture> animationDown;
	protected ArrayList<Texture> animationLeft;
	
	/**
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param _maze : maze instance
	 * @param _speed : enemy speed
	 */
	public Enemy(int x, int y, Maze maze, int _speed)
	{
		mapScale = maze.getMapScale();
		matrix = maze.getMatrix();	
		
		defaultPosX = x*mapScale;
		defaultPosY = y*mapScale;
		posX = defaultPosX;
		posY = defaultPosY;
		speed = _speed;
		
		loadAnimation();
	}
	
	/**
	 * Update movements and animations
	 */
	protected void update()
	{
		algorithm();
		updateAnimation();
	}
	
	/**
	 * Update enemy's animation according to its direction
	 */
	private void updateAnimation()
	{
		if(previousDir != direction && direction !=0 && direction != 2)
		{
			animCounter=0;
			frameCounter=0;
		}
		
		if(direction == 0 && previousDir == 3)
		{
			animate(animationLeft);
			previousDir = 3;
		}
		else if(direction == 0 && previousDir == 1)
		{
			animate(animationRight);
			previousDir = 1;
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
		}
		else if(direction == 2 && previousDir == 1)
		{
			animate(animationRight);
			previousDir = 1;
		}
		else if(direction == 3)	
		{
			animate(animationLeft);
			previousDir = 3;
		}
	}
	
	/**
	 * Animate the enemy
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
			enemySprite = anim.get(animCounter);
			animCounter++;
		}		
		frameCounter++;
	}
	
	/**
	 * Set the enemy position to default
	 */
	protected void respawn()
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
	abstract protected void reset();
	abstract protected void loadAnimation();
}
