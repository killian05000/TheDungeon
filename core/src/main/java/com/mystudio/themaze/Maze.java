package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Maze 
{
	private int mapScale;

	private int [][] matrix;
	private int playerSpawnX;
	private int playerSpawnY;
	
	private ArrayList<Item> items;
	
	public Maze(int scale, int[][] M)
	{
		matrix = new int[M.length][M[0].length];
		matrix = M;
		items = new ArrayList<Item>();
		mapScale = matrix.length*scale;
		initialiseDefaultPositionPlayer();
	}
	
	private void initialiseDefaultPositionPlayer() 
	{
		for (int i=0; i<matrix.length; i++)
		{
			for (int j=0; j<matrix[0].length; j++) 
			{
				if(matrix[i][j]==2) 
				{
					playerSpawnX = i;
					playerSpawnY = j;
				}
			}	
		}
	}
	
	public int[][] getMatrix() 
	{
		return matrix;
	}
	
	public void setMatrix(int posX , int posY, int valeur) 
	{
		matrix[posX][posY] = valeur;
		
	}
	
	public void PaintMaze(Graphics g) 
	{
		for (int i=0; i<matrix.length; i++) 
		{
			for (int j=0; j<matrix[0].length; j++) 
			{
				switch(matrix[i][j])
				{
					case 0:
						g.setColor(Color.GRAY);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 1:
						g.setColor(Color.BLACK);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 2:
						g.setColor(Color.BLUE);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 3:
						g.setColor(Color.PINK);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 4:
						g.setColor(Color.RED);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 5:
						g.setColor(Color.BROWN);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 6:
						g.setColor(Color.GREEN);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 9:
						g.setColor(Color.PURPLE);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
				 }
			 }
		 } 
	 }
	
	public void paintItemes(Graphics g) 
	{
		if(items.size()>0) 
    	{
	    	for(int i=0; i<items.size(); i++) 
	    	{
	    		items.get(i).render(g);
	    	}
    	}
	}
	
	public void addItem(Item item) 
	{
		items.add(item);
	}

	public int getMapScale()
	{
		return mapScale;
	}
	

	public int getPlayerSpawnX()
	{
		return playerSpawnX;
	}
	public int getPlayerSpawnY()
	{
		return playerSpawnY;
	}
	
	
	public ArrayList<Item>getItems()
	{
		return items;
	}
}
