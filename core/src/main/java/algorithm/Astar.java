package algorithm;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enums.Direction;
import enums.MapObject;

public class Astar 
{
	private int[][] matrix;
	private Cell[][] matrixCell;
	private int mapScale;
	
	private List<Cell> openSet; 
	private List<Cell> closedSet;
	
	private Cell startCell;
	private Cell endCell;
	private List<Cell> path;
	
	private int[] previousPlayerCoord;
	private int[] playerCoord;
	private int[] enemyCoord;
 
	private final ArrayList<Integer> walkableSpots;
	private Direction direction = Direction.NONE;
	private Direction nextDirection;
	
	/**
	 * This constructor will create a cell for each available tiles and add its neighbors
	 * @param matrix : game matrix
	 * @param mapScale : tile size
	 */
	public Astar(int[][] matrix, int mapScale) 
	{
		this.matrix = matrix;
		matrixCell = new Cell[matrix.length][matrix[0].length];
		this.mapScale = mapScale;
		
		previousPlayerCoord = new int[2];
		playerCoord = new int[2];
		enemyCoord = new int[2];
		
		openSet = new LinkedList<Cell>();
		closedSet = new LinkedList<Cell>();
		path = new LinkedList<Cell>();
		
		walkableSpots = new ArrayList<Integer>();
		walkableSpots.add(MapObject.ALLEY.ordinal());
		walkableSpots.add(MapObject.PLAYER_SPAWN.ordinal());
		walkableSpots.add(MapObject.RANDOM_ENEMY_SPAWN.ordinal());
		walkableSpots.add(MapObject.SMART_ENEMY_SPAWN.ordinal());
		walkableSpots.add(MapObject.TELEPORTER.ordinal());
		
		creatingCell();
		addingNeighbors();
	}
	
	/**
	 * Creates cells for each walkable tile  
	 */
	private void creatingCell() 
	{
		for(int i = 0; i < matrixCell.length; i++) 
		{
			for(int j = 0; j < matrixCell[i].length; j++) 
			{
				if(walkableSpots.contains(matrix[i][j])) 
					matrixCell[i][j] = new Cell(i, j);
			}
		}
	}
	
	/**
	 * For each cells, the method will add its neighbors
	 */
	private void addingNeighbors() 
	{
		for(int i = 0; i < matrixCell.length; i++) 
		{
			for(int j = 0; j < matrixCell[i].length; j++) 
			{
				if(matrixCell[i][j] != null)
					matrixCell[i][j].addNeighbors(matrixCell);
			}
		}
	}
	
	/**
	 * Calculate the Manhattan distance
	 * @param source : source Cell
	 * @param destination : destination Cell
	 * @return the result of the calculated Manhattan distance
	 */
	public int heuristic(Cell source, Cell destination) 
	{
		return Math.abs(source.getX() - destination.getX()) + Math.abs(source.getY() - destination.getY());
	}
	
	/**
	 * Clears the structures and update the player's position
	 * @param enemyX : position X of the enemy 
	 * @param enemyY : position Y of the enemy
	 * @param playerX : position X of the player
	 * @param playerY : position Y of the player
	 * @return the direction predicted
	 */
	public Direction updateDirection(int enemyX, int enemyY, int playerX, int playerY) 
	{
	    clearStructs();
		
	    enemyCoord[0] = enemyX;
	    enemyCoord[1] = enemyY;
	    
	    playerCoord[0] = playerX;
	    playerCoord[1] = playerY;
		
	    previousPlayerCoord[0] = playerX;
	    previousPlayerCoord[1] = playerY;
		
	    if(matrixCell[playerCoord[0] / mapScale][playerCoord[1] / mapScale] == null) 
	    {
	    	clearStructs();
	    	matrixCell[playerCoord[0] / mapScale][playerCoord[1] / mapScale] = new Cell(playerCoord[0] / mapScale, playerCoord[1] / mapScale);
	    	addingNeighbors();
	    }
		
	    Direction direction = algorithm(); 
	    return direction;
	}
	
	/**
	 * Run the A star algorithm
	 * @return the direction predicted
	 */
	private Direction algorithm() 
	{
		startCell = matrixCell[enemyCoord[0] / mapScale][enemyCoord[1] /mapScale];
		endCell = matrixCell[playerCoord[0] / mapScale][playerCoord[1] / mapScale];
		
		openSet.add(startCell);
		
		while(true) 
		{
			if(!openSet.isEmpty()) 
			{
				int lowestFscoreIndex = 0;
				for(int i = 0; i < openSet.size(); i++) 
				{
					if(openSet.get(i).getF() < openSet.get(lowestFscoreIndex).getF())
					    lowestFscoreIndex = i;
				}
							
				Cell currentCell = openSet.get(lowestFscoreIndex);
				if(currentCell == endCell) 
				{
					Cell temp = currentCell;
					path.add(temp);
					
					while(temp.getPreviousCell() != null) 
					{
						path.add(temp.getPreviousCell());
						temp = temp.getPreviousCell();
					}
					break;
				}
				
				openSet.remove(currentCell);
				closedSet.add(currentCell);
				
				List<Cell> neighbors = new LinkedList<Cell>();
				neighbors = currentCell.getNeighbors();
				for(int i = 0; i < neighbors.size(); i++) 
				{
					Cell neighbor = neighbors.get(i);
					if(!closedSet.contains(neighbor)) 
					{
						int tempG = currentCell.getG() + 1;
						
						if(openSet.contains(neighbor)) 
						{
							if(tempG < neighbor.getG()) 							
								neighbor.setG(tempG);							
						}
						else 
						{
							neighbor.setG(tempG);
							openSet.add(neighbor);
						}
						
						neighbor.setH(heuristic(neighbor, endCell));
						neighbor.setF(neighbor.getG() + neighbor.getH());
						neighbor.setPreviousCell(currentCell);
					}
				}
			}
			else // No solution
			    break;
		}
		
		Direction direction = findDirection();
		return direction;
	}
	
	/**
	 * Finds the direction and returns it
	 * @return the direction predicted
	 */
	private Direction findDirection() 
	{	
		int[] newPos = new int[2];
		
		if(path.size() < 2) 
		{
		    newPos[0] = path.get(path.size() - 1).getX();
		    newPos[1] = path.get(path.size() - 1).getY();
		}
		else 
		{
		    newPos[0] = path.get(path.size() - 2).getX();
		    newPos[1] = path.get(path.size() - 2).getY();
		}
		
		if(enemyCoord[0] / mapScale > newPos[0]
	            && path.contains(matrixCell[enemyCoord[0] / mapScale][enemyCoord[1] / mapScale]) // TOP LEFT
	            && path.contains(matrixCell[enemyCoord[0] / mapScale][(enemyCoord[1] + mapScale - 1) / mapScale])) // TOP RIGHT
	        {
	            direction = Direction.UP;
	        }
	        else if(enemyCoord[1] / mapScale < newPos[1]
	            && path.contains(matrixCell[enemyCoord[0] / mapScale][(enemyCoord[1] + mapScale) / mapScale]) // TOP RIGHT
	            && path.contains(matrixCell[(enemyCoord[0] + mapScale - 1)/mapScale][(enemyCoord[1] + mapScale) / mapScale])) // BOTTOM RIGHT
	        {
	            direction = Direction.RIGHT;
	        }
	        else if(enemyCoord[0] / mapScale < newPos[0]
	            && path.contains(matrixCell[(enemyCoord[0] + mapScale) / mapScale][enemyCoord[1] / mapScale])  // BOTTOM LEFT
	            && path.contains(matrixCell[(enemyCoord[0] + mapScale) / mapScale][(enemyCoord[1] + mapScale - 1) / mapScale])) // BOTTOM RIGHT
	        {
	            direction = Direction.DOWN;
	        }
	        else if(enemyCoord[1]/mapScale > newPos[1]
	            && path.contains(matrixCell[enemyCoord[0] / mapScale][enemyCoord[1] / mapScale]) // TOP LEFT
	            && path.contains(matrixCell[(enemyCoord[0] + mapScale - 1)/mapScale][enemyCoord[1] / mapScale])) // BOTTOM RIGHT
	        {
	            direction = Direction.LEFT;
	        }
		
		if(direction.equals(Direction.NONE))
			direction = nextDirection;
		else
			nextDirection = direction;
		
		return direction;	
	}
	
	/**
	 * Clears all the structures
	 */
	public void clearStructs()
	{
		path.clear();
		openSet.clear();
		closedSet.clear();
		creatingCell();
		addingNeighbors();
	}
	
	public Cell[][] getMatrixCell()
	{
		return this.matrixCell;
	}
	
	public List<Cell> getOpenSet()
	{
		return this.openSet;
	}
	
	public List<Cell> getClosedSet()
	{
		return this.closedSet;
	}
	
	public List<Cell> getPath()
	{
		return this.path;
	}
}
