package com.mystudio.themaze;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;

public class RandomEnemy extends Enemy
{
	
	/**
	 * Call the constructor of the mother class
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param maze : maze instance
	 * @param speed : enemy speed
	 */
	public RandomEnemy(int x, int y, Maze maze, int speed)
	{
		super(x, y, maze, speed);
	}
	
	/**
	 * Generate random movements for the enemy
	 */
	@Override
	public void algorithm() 
	{		
		int newPosX;
    	int newPosY;

    	switch(direction)
    	{
			case 0: // UP
				newPosX = posX - speed;				
				if(newPosX >= 0 
					&& matrix[newPosX / mapScale][posY / mapScale] == 0
					&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == 0)
				{
					posX = newPosX;
				}
				else
					direction = (int)(Math.random() * (4 - 0));				
				break;
				
			case 1: // RIGHT
				newPosY = posY + speed;	    		
	    		if((newPosY + mapScale - speed) / mapScale < matrix[0].length 
					&& matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
					&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == 0)
	    		{
					posY = newPosY;
	    		}
	    		else
	    			direction = (int)(Math.random() * (4 - 0));
				break;
				
			case 2: // DOWN
				newPosX = posX + speed;	    		
				if((newPosX + mapScale - speed) / mapScale < matrix.length 
						&& matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == 0
						&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == 0) 
				{
						posX = newPosX;
				}
	    		else
	    			direction = (int)(Math.random() * (4 - 0));
				break;
				
			case 3: // LEFT
				newPosY = posY - speed;				
	    		if(newPosY >= 0 
					&& matrix[posX / mapScale][newPosY / mapScale] == 0
					&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == 0) 
	    		{
					posY = newPosY;
	    		}
	    		else
	    			direction = (int)(Math.random() * (4 - 0));
	    		break;		
		}
	}

	/**
	 * Call the respawn() method
	 */
	@Override
	public void reset()
	{
		respawn();
	}

	/**
	 * Load the enemy sprites to render the animations
	 */
	@Override
	protected void loadAnimation() 
	{
		enemySprite = new Texture("enemies/enemy/RunLeft/n0.png");
		
		/*
		animationUp = new ArrayList<Texture>();		
		animationUp.add(new Texture("enemies/enemy/enemyUp.png"));
		animationUp.add(new Texture("enemies/enemy/enemyUpGrey.png"));*/
		
//		animationRight = new ArrayList<Texture>();		
//		animationRight.add(new Texture("enemies/enemy/enemyRight.png"));
//		animationRight.add(new Texture("enemies/enemy/enemyRightGrey.png"));
		
		animationRight = new ArrayList<Texture>();		
		animationRight.add(new Texture("enemies/enemy/RunRight/n0.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n2.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n3.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n4.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n5.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n6.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n7.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n8.png"));
		animationRight.add(new Texture("enemies/enemy/RunRight/n9.png"));
		
		/*
		animationDown = new ArrayList<Texture>();		
		animationDown.add(new Texture("enemies/enemy/enemyDown.png"));
		animationDown.add(new Texture("enemies/enemy/enemyDownGrey.png"));*/
		
//		animationLeft = new ArrayList<Texture>();		
//		animationLeft.add(new Texture("enemies/enemy/enemyLeft.png"));
//		animationLeft.add(new Texture("enemies/enemy/enemyLeftGrey.png"));
		
		animationLeft = new ArrayList<Texture>();		
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n0.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n1.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n2.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n3.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n4.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n5.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n6.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n7.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n8.png"));
		animationLeft.add(new Texture("enemies/enemy/RunLeft/n9.png"));


	}		
}
