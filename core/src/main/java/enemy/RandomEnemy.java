package enemy;

import enums.Direction;
import enums.MapObject;
import maze.Maze;

public class RandomEnemy extends Enemy
{
	/**
	 * Call the constructor of the mother class and load the animations
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param maze : maze instance
	 * @param speed : enemy speed
	 */
	public RandomEnemy(int x, int y, Maze maze, int speed)
	{
		super(x, y, maze, speed);
		loadAnimation("enemy");
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
			case UP: // UP
				newPosX = posX - speed;				
				if(newPosX >= 0 
					&& matrix[newPosX / mapScale][posY / mapScale] == MapObject.ALLEY.ordinal()
					&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal())
				{
					posX = newPosX;
				}
				else
					direction = Direction.values()[(int)(Math.random() * (4 - 0))];			
				break;
				
			case RIGHT: // RIGHT
				newPosY = posY + speed;	    		
	    		if((newPosY + mapScale - speed) / mapScale < matrix[0].length 
					&& matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal()
					&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal())
	    		{
					posY = newPosY;
	    		}
	    		else
	    			direction = Direction.values()[(int)(Math.random() * (4 - 0))];	
				break;
				
			case DOWN: // DOWN
				newPosX = posX + speed;	    		
				if((newPosX + mapScale - speed) / mapScale < matrix.length 
						&& matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == MapObject.ALLEY.ordinal()
						&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal()) 
				{
						posX = newPosX;
				}
	    		else
	    			direction = Direction.values()[(int)(Math.random() * (4 - 0))];		
				break;
				
			case LEFT: // LEFT
				newPosY = posY - speed;				
	    		if(newPosY >= 0 
					&& matrix[posX / mapScale][newPosY / mapScale] == MapObject.ALLEY.ordinal()
					&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == MapObject.ALLEY.ordinal()) 
	    		{
					posY = newPosY;
	    		}
	    		else
	    			direction = Direction.values()[(int)(Math.random() * (4 - 0))];		
	    		break;		
	    		
			case NONE:
    		{
    			direction = Direction.values()[(int)(Math.random() * (4 - 0))];	
    		}
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
}
