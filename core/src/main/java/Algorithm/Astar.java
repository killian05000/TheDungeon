package Algorithm;
import java.util.LinkedList;
import java.util.List;
import java.lang.Object;

public class Astar {
	private int[][] matrix;
	private Cell[][] matrixCell;
	private List<Cell> openSet;
	private List<Cell> closedSet;
	private Cell start;
	private Cell end;
	private List<Cell> path;
	private int[] previousPlayerCoord;
	private int[] playerCoord;
	private int[] enemyCoord;
	
	private int mapScale;
	private int counter = 2;
	private int number = 0;
	int nextDir;
	
	public Astar(int[][] matrix, int _mapScale) {
		mapScale = _mapScale;
		previousPlayerCoord = new int[2];
		playerCoord = new int[2];
		enemyCoord = new int[2];
		this.matrix = matrix;
		path = new LinkedList<Cell>();
		openSet = new LinkedList<Cell>();
		closedSet = new LinkedList<Cell>();
		matrixCell = new Cell[matrix.length][matrix[0].length];
		creatingCell();
		addingNeighbors();
	}
	
	public void creatingCell() {
		for(int i = 0; i < matrixCell.length; i++) {
			for(int j = 0; j < matrixCell[i].length; j++) {
				//System.out.println(matrix[i][j]);
				if(matrix[i][j] == 0 || matrix[i][j] == 2 || matrix[i][j] == 3 || matrix[i][j] == 9) {
					matrixCell[i][j] = new Cell(i, j);
					//System.out.println("0 OK");
				}
				//System.out.println("i : " + matrixCell[i][j].i + ", j : " + matrixCell[i][j].j);
			}
		}
		
		
		//System.exit(0);
	}
	
	public void addingNeighbors() {
		for(int i = 0; i < matrixCell.length; i++) {
			for(int j = 0; j < matrixCell[i].length; j++) {
				if(matrixCell[i][j] != null)
					matrixCell[i][j].addNeighbors(matrixCell);
			}
		}
	}
	
	public int heuristic(Cell source, Cell destination) {
		return Math.abs(source.i - destination.i) + Math.abs(source.j - destination.j);
	}
	
	public int updateDirection(int enemyX, int enemyY, int playerX, int playerY) {
		clearStructs();
		//System.out.println("########## START UPDATE DIRECTION #########");

		//path.clear();
		//for(int i=0; i<openSet.size(); i++)
			//System.out.println("Open set AVANT (i) : " + openSet.get(i).i + " / Open set (j) : "+ openSet.get(i).j);
		enemyCoord[0] = enemyX;
		enemyCoord[1] = enemyY;
		
		playerCoord[0] = playerX;
		playerCoord[1] = playerY;
		
		/*if(playerCoord[0]!=previousPlayerCoord[0] || playerCoord[1]!=previousPlayerCoord[1])
		{
			counter=2;
//			openSet.clear();
//			closedSet.clear();
		}
		*/
		previousPlayerCoord[0] = playerX;
		previousPlayerCoord[1] = playerY;
		if(matrixCell[playerCoord[0]/mapScale][playerCoord[1]/mapScale] == null) {
			/*if((playerCoord[0]/mapScale) + 1 < matrix.length && matrix[(playerCoord[0]/mapScale) + 1][(playerCoord[1]/mapScale)] == 0) 
			{
				playerCoord[0] = playerCoord[0] + mapScale;
			}
			else if((playerCoord[0]/mapScale) - 1 > 0 && matrix[(playerCoord[0]/mapScale) - 1][(playerCoord[1]/mapScale)] == 0) {
				playerCoord[0] = playerCoord[0] - mapScale;
			}
			else if((playerCoord[1]/mapScale) + 1 < matrix[0].length && matrix[(playerCoord[0]/mapScale)][(playerCoord[1]/mapScale) + 1] == 0) {
				playerCoord[1] = playerCoord[1] + mapScale;
			}
			else if((playerCoord[1]/mapScale) - 1 > 0 && matrix[(playerCoord[0]/mapScale)][(playerCoord[1]/mapScale) - 1] == 0) {
				playerCoord[1] = playerCoord[1] - mapScale;
			}*/
			
			clearStructs();
			matrixCell[playerCoord[0]/mapScale][playerCoord[1]/mapScale] = new Cell(playerCoord[0]/mapScale, playerCoord[1]/mapScale);
			addingNeighbors();
			System.out.println("ON EST DANS LE IF");
		}
			
		
		return algorithm();
	}
	
	public int algorithm() {
		start = matrixCell[enemyCoord[0]/mapScale][enemyCoord[1]/mapScale];
		end = matrixCell[playerCoord[0]/mapScale][playerCoord[1]/mapScale];
		/*System.out.println("----------------");
		System.out.println("start (i): " + start.i + " / start (j) : " + start.j);
		System.out.println("end (i): " + end.i + " / end (j) : " + end.j);*/
		
		//openSet.clear();
		//System.out.println(openSet.size());
		openSet.add(start);
		//System.out.println("openSet start : " + openSet.get(0).i + ", " + openSet.get(0).j);
		
		while(true) {
			if(!openSet.isEmpty()) {
				// We can keep going since there are still Cells in the list
				int winner = 0;
				for(int i = 0; i < openSet.size(); i++) {
					if(openSet.get(i).f < openSet.get(winner).f)
						winner = i;
				}
							
				Cell current = openSet.get(winner);
				if(current == end) 
				{
					Cell temp = current;
					path.add(temp);
					
					while(temp.previous != null) 
					{
						path.add(temp.previous);
						temp = temp.previous;
					}
					//System.out.println("Done");
					break;
				}
				
				
				
				openSet.remove(current);
				closedSet.add(current);
				
				List<Cell> neighbors = new LinkedList<Cell>();
				neighbors = current.neighbors;
				for(int i = 0; i < neighbors.size(); i++) {
					Cell neighbor = neighbors.get(i);
					if(!closedSet.contains(neighbor)) {
						int tempG = current.g + 1;
						
						if(openSet.contains(neighbor)) {
							if(tempG < neighbor.g) {
								neighbor.g = tempG;
							}
						}
						else {
							neighbor.g = tempG;
							openSet.add(neighbor);
						}
						
						neighbor.h = heuristic(neighbor, end);
						neighbor.f = neighbor.g + neighbor.h;
						neighbor.previous = current;
					}
				}
			}
			else {
				// No solution
				System.out.println("No solution");
				break;
			}
		}
		
		//System.out.println("-----------------");
		/*for(int i=0; i<openSet.size(); i++)	
			System.out.println("Open set APRES (i) : " + openSet.get(i).i + " / Open set APRES (j) : "+ openSet.get(i).j);*/
		
		return findDirection();
	}
	
	private int findDirection() 
	{
		int direction = -1;
		int[] newPos = new int[2];
		for(int i = 0; i < path.size(); i++) {
			System.out.println("Path " + i + " : " + path.get(i).i + " / " + path.get(i).j);
		}
		//System.out.println("Path size : " + path.size());
		//System.out.println("Counter : "+counter);

		//System.out.println("i : " + path.get(path.size() - counter).i + " / path size : " + path.size());
		//System.out.println("j : " + path.get(path.size() - counter).j + " / counter : " + counter);

		newPos[0] = path.get(path.size() - 2).i;
		newPos[1] = path.get(path.size() - 2).j;
		/*if(enemyCoord[0]/mapScale == newPos[0] && enemyCoord[1]/mapScale == newPos[1]) {
			newPos[0] = path.get(path.size() - 2).i;
			newPos[1] = path.get(path.size() - 2).j;
			counter++;
			//System.out.println("next dir : "+path.get(path.size() - 3).i+" /"+path.get(path.size() - 3).j);
		}*/
		System.out.println(path.contains(matrixCell[0][0]));
//	newPos[0] = path.get(path.size() - 2).i;
//	newPos[1] = path.get(path.size() - 2).j;
		System.out.println("posEnemy : " + enemyCoord[0]/mapScale + ", " + enemyCoord[1]/mapScale);
		System.out.println("newPos : " + newPos[0] + ", " + newPos[1]);
		if(enemyCoord[0]/mapScale > newPos[0] &&
			path.contains(matrixCell[enemyCoord[0]/mapScale][enemyCoord[1]/mapScale]) 
			&& path.contains(matrixCell[enemyCoord[0]/mapScale][(enemyCoord[1]+mapScale-1)/mapScale]))
		{
			System.out.println("JE VEUX MONTER");
			direction = 0; // UP
		}
		else if(enemyCoord[1]/mapScale < newPos[1] &&
				path.contains(matrixCell[enemyCoord[0]/mapScale][(enemyCoord[1]+mapScale)/mapScale]) 
				&& path.contains(matrixCell[(enemyCoord[0]+mapScale-1)/mapScale][(enemyCoord[1]+mapScale)/mapScale]))
		{
			System.out.println("JE VEUX DROITE");
			direction = 1; // RIGHT
		}
		else if(enemyCoord[0]/mapScale < newPos[0] && 
				path.contains(matrixCell[(enemyCoord[0]+mapScale)/mapScale][enemyCoord[1] / mapScale]) 
				&& path.contains(matrixCell[(enemyCoord[0]+mapScale)/mapScale][(enemyCoord[1]+mapScale-1) / mapScale]))
		{
			System.out.println("JE VEUX BAS");
			direction = 2; // DOWN
		}
		else if(enemyCoord[1]/mapScale > newPos[1] && 
				path.contains(matrixCell[enemyCoord[0]/mapScale][enemyCoord[1] / mapScale]) 
				&& path.contains(matrixCell[(enemyCoord[0]+mapScale-1)/mapScale][enemyCoord[1] / mapScale]))
		{
			System.out.println("JE VEUX GAUCHE");
			direction = 3; // LEFT
		}
		
		if(direction==-1)
		{
			direction= nextDir;
			System.out.println("NEXT DIR");
		}
		else
			nextDir=direction;
		
		System.out.println("Direction enemy : " + direction);
		//if(direction == 3)
			//System.exit(1);
		
		if(direction == -1) {
			/*System.out.println("new i : " + newPos[0]);
			System.out.println("new j : " + newPos[1]);
			System.out.println("enemyCoord i : " + enemyCoord[0]/mapScale);
			System.out.println("enemyCoord j : " + enemyCoord[1]/mapScale);*/
			
			//System.exit(1);
		}
		
		
		number++;
		
		return direction;
		
	}
	
	public void clearStructs(){
		path.clear();
		openSet.clear();
		closedSet.clear();
		creatingCell();
		addingNeighbors();
	}
	
	public Cell[][] getMatrixCell(){
		return this.matrixCell;
	}
	
	public List<Cell> getOpenSet(){
		return this.openSet;
	}
	
	public List<Cell> getClosedSet(){
		return this.closedSet;
	}
	
	public List<Cell> getPath(){
		return this.path;
	}
}
