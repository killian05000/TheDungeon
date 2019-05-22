package enemy;

import algorithm.Astar;
import enums.MapObject;
import game.Player;
import maze.Maze;

public class SmartEnemy extends Enemy 
{
	private Astar brain;
	private Player target;
	
	/**
	 * Call the constructor of the mother class, instantiate the Astar algorithm and load the animations
	 * @param x : default x enemy's position
	 * @param y : default y enemy's position
	 * @param maze : maze instance
	 * @param target : target of the enemy
	 * @param speed : enemy speed
	 */
	public SmartEnemy(int x, int y, Maze maze, Player target, int speed) 
	{
		super(x, y, maze, speed);
		
		this.target = target;
		brain = new Astar(matrix, mapScale);
		loadAnimation("smartEnemy");
	}

	/**
	 * Launch the Astar algorithm to generate directions for the enemy in order to catch the target
	 */
	@Override
	public void algorithm() 
	{
		int newPosX;
    	int newPosY;
    	direction = brain.updateDirection(posX, posY, target.getPosX(), target.getPosY());

    	switch(direction)
    	{
			case UP:
				newPosX = posX - speed;				
				if(newPosX >= 0 
					&& (matrix[newPosX / mapScale][posY / mapScale] == MapObject.ALLEY.ordinal()
					&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal()) 
					|| (matrix[newPosX / mapScale][posY / mapScale] == MapObject.TELEPORTER.ordinal()
					&& matrix[newPosX / mapScale][(posY + mapScale - speed) / mapScale] == MapObject.TELEPORTER.ordinal()))
				{
					posX = newPosX;
				}

				break;
				
			case RIGHT:
				newPosY = posY + speed;	    		
	    		if((newPosY + mapScale - speed) / mapScale < matrix[0].length 
					&& (matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal()
					&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal()) 
					|| (matrix[posX / mapScale][(newPosY + mapScale - speed) / mapScale] == MapObject.TELEPORTER.ordinal()
					&& matrix[(posX + mapScale - speed) / mapScale][(newPosY + mapScale - speed) / mapScale] == MapObject.TELEPORTER.ordinal()))
	    		{
					posY = newPosY;
	    		}

				break;
				
			case DOWN:
				newPosX = posX + speed;	    		
				if((newPosX + mapScale - speed) / mapScale < matrix.length 
						&& (matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == MapObject.ALLEY.ordinal()
						&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == MapObject.ALLEY.ordinal()) 
						|| (matrix[(newPosX + mapScale - speed) / mapScale][posY / mapScale] == MapObject.TELEPORTER.ordinal()
						&& matrix[(newPosX + mapScale - speed) / mapScale][(posY + mapScale - speed) / mapScale] == MapObject.TELEPORTER.ordinal()))
				{
						posX = newPosX;
				}
				break;
				
			case LEFT:
				newPosY = posY - speed;				
	    		if(newPosY >= 0 
					&& (matrix[posX / mapScale][newPosY / mapScale] == MapObject.ALLEY.ordinal()
					&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == MapObject.ALLEY.ordinal()) 
					|| (matrix[posX / mapScale][newPosY / mapScale] == MapObject.TELEPORTER.ordinal()
					&& matrix[(posX + mapScale - speed) / mapScale][newPosY / mapScale] == MapObject.TELEPORTER.ordinal()))
	    		{
					posY = newPosY;
				}
	    		break;	
	    		
			case NONE:
    		{
    			// Do nothing
    		}
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
}
