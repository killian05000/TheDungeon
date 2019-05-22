package algorithm;
import java.util.LinkedList;
import java.util.List;

public class Cell 
{
	private int x;
	private int y;
	
	private int g; // For each node, the cost of getting from the start node to that node
	private int h; // Heuristic value of the cheapest path from the actual position to the goal
	private int f; // Total score : f(n) = g(n) + h(n) where n is a node 
	
	private List<Cell> neighbors;
	private Cell previousCell;
	
	public Cell(int x, int y) 
	{
		this.x = x;
		this.y = y;
		f = 0;
		g = 0;
		h = 0;
		previousCell = null;
		neighbors = new LinkedList<Cell>();
	}
	
	/**
	 * Adds the available adjacent tiles to the Cell's neighbors
	 * @param matrixCell : the matrix containing the cells
	 */
	public void addNeighbors(Cell[][] matrixCell) 
	{
		if(x < matrixCell.length - 1 && (matrixCell[x + 1][y] != null)) 
			neighbors.add(matrixCell[x + 1][y]);
		
		if(x > 0 && (matrixCell[x - 1][y] != null))
			neighbors.add(matrixCell[x - 1][y]);

		if(y < matrixCell[0].length - 1 && (matrixCell[x][y + 1] != null))
			neighbors.add(matrixCell[x][y + 1]);
		
		if(y > 0 && (matrixCell[x][y - 1] != null))
			neighbors.add(matrixCell[x][y - 1]);
	}

	public int getX() 
	{
	    return x;
	}

	public int getY() 
	{
	    return y;
	}

	public int getF() 
	{
	    return f;
	}

	public int getG() 
	{
	    return g;
	}

	public int getH()
	{
	    return h;
	}
	
	public List<Cell> getNeighbors() 
	{
	    return neighbors;
	}

	public Cell getPreviousCell() 
	{
	    return previousCell;
	}
	
	public void setF(int f)
	{
	    this.f = f;
	}
	
	public void setG(int g)
	{
	    this.g = g;
	}
	
	public void setH(int h)
	{
	    this.h = h;
	}
	
	public void setPreviousCell(Cell previousCell) 
	{
	    this.previousCell = previousCell;
	}

}
