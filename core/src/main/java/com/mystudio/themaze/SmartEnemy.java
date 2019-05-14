package com.mystudio.themaze;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import Algorithm.Astar;

public class SmartEnemy extends Enemy 
{
	private Astar brain;
	private Player target;
	
	/**
	 * Call the constructor of the mother class
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param maze : maze instance
	 * @param _target : target of the enemy
	 * @param speed : enemy speed
	 */
	public SmartEnemy(int x, int y, Maze maze, Player _target, int speed) 
	{
		super(x, y, maze, speed);
		
		target = _target;
		brain = new Astar(matrix, mapScale);
	}

	/**
	 * Launch the A star algorithm to generate directions for the enemy in order to catch the target
	 */
	@Override
	public void algorithm() 
	{
		int newPosX;
    	int newPosY;
    	direction = brain.updateDirection(posX, posY, target.getPosX(), target.getPosY());

    	switch(direction)
    	{
			case 0: // UP
				newPosX = posX - speed;				
				if(newPosX >=0 
					&& (matrix[newPosX / mapScale][posY / mapScale] == 0
					&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == 0) ||
					(matrix[newPosX / mapScale][posY / mapScale] == 9
					&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == 9))
				{
					posX = newPosX;
				}

				break;
				
			case 1: // RIGHT
				newPosY = posY + speed;	    		
	    		if((newPosY + mapScale - speed) / mapScale < matrix[0].length 
					&& (matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == 0
					&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == 0) ||
	    			(matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == 9
					&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == 9))
	    		{
					posY = newPosY;
	    		}

				break;
				
			case 2: // DOWN
				newPosX = posX + speed;	    		
				if((newPosX + mapScale - speed) / mapScale < matrix.length 
						&& (matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == 0
						&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == 0) ||
						(matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == 9
						&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == 9))
				{
						posX = newPosX;
				}
				break;
				
			case 3: // LEFT
				newPosY = posY - speed;				
	    		if(newPosY >= 0 
					&& (matrix[posX / mapScale][newPosY / mapScale] == 0
					&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == 0) ||
					(matrix[posX / mapScale][newPosY / mapScale] == 9
					&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == 9))
	    		{
					posY = newPosY;
				}
	    		break;	
		}
	}

	/**
	 * Call the respawn() method and clear all the algorithm's structures
	 */
	@Override
	public void reset() 
	{
		respawn();
		brain.clearStructs();
	}
	
	/**
	 * Load the enemy sprites to render the animations
	 */
	@Override
	public void loadAnimation()
	{
		enemySprite = new Texture("enemies/smartEnemy/smartEnemyDown.png");
		
		animationUp = new ArrayList<Texture>();		
		animationUp.add(new Texture("enemies/smartEnemy/smartEnemyUp.png"));
		animationUp.add(new Texture("enemies/smartEnemy/smartEnemyUpGrey.png"));
		
		animationRight = new ArrayList<Texture>();		
		animationRight.add(new Texture("enemies/smartEnemy/smartEnemyRight.png"));
		animationRight.add(new Texture("enemies/smartEnemy/smartEnemyRightGrey.png"));
		
		animationDown = new ArrayList<Texture>();		
		animationDown.add(new Texture("enemies/smartEnemy/smartEnemyDown.png"));
		animationDown.add(new Texture("enemies/smartEnemy/smartEnemyDownGrey.png"));	
		
		animationLeft = new ArrayList<Texture>();		
		animationLeft.add(new Texture("enemies/smartEnemy/smartEnemyLeft.png"));
		animationLeft.add(new Texture("enemies/smartEnemy/smartEnemyLeftGrey.png"));
	}
}
