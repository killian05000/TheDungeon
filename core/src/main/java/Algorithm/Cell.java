package Algorithm;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class Cell {
	protected int i;
	protected int j;
	protected int f;
	protected int g;
	protected int h;
	protected List<Cell> neighbors;
	protected Cell previous;
	
	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
		f = 0;
		g = 0;
		h = 0;
		previous = null;
		neighbors = new LinkedList<Cell>();
	}
	
	public void addNeighbors(Cell[][] matrixCell) {
		if(i < matrixCell.length - 1 && (matrixCell[i + 1][j] != null)) {
			neighbors.add(matrixCell[i + 1][j]);
		}
		if(i > 0 && (matrixCell[i - 1][j] != null)) {
			neighbors.add(matrixCell[i - 1][j]);
		}
		if(j < matrixCell[0].length - 1 && (matrixCell[i][j + 1] != null)) {
			neighbors.add(matrixCell[i][j + 1]);
		}
		if(j > 0 && (matrixCell[i][j - 1] != null)) {
			neighbors.add(matrixCell[i][j - 1]);
		}
	}
	
	
}
