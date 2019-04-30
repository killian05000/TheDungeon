package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Maze 
{
	/*
	 * 5 teleporteur
	 * 7 piege 
	 * 2 position de depart pour le monstre
	 * 6 porte
	 * 8 sortie
	 * 3 position de depart du joueur
	 * 
	 * 30*30
	 * 
	 * 
	 */
	private int mapScale;
	private int defaultPosXPlayer;
	private int defaultPosYPlayer;
	
	private int [][] matrix = 
		{
			{1,1,1,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,5,0,0,0,0,0,1},
			{0,0,0,0,0,1,0,1,1,0,1,0,1,7,1,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1},
			{0,1,1,1,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,1,0,1,0,1,1,1,1,1},
			{0,1,0,1,0,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,1,0,0,0,0,6,8},
			{0,0,5,0,0,1,1,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
			{0,1,0,1,0,1,1,0,1,1,0,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1},
			{0,0,0,1,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0,1},
			{1,1,1,1,1,0,1,2,1,0,1,1,1,1,0,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,1,0,1,1,0,1,0},
			{0,1,1,0,1,1,1,0,1,0,1,0,1,1,1,1,0,1,1,1,0,0,0,1,0,1,1,0,1,0},
			{0,1,1,0,1,0,0,0,1,1,1,0,1,1,1,1,0,1,1,1,0,1,0,1,0,1,1,0,1,0},
			{0,0,0,0,1,7,1,0,1,1,1,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,1,1,0,1,0},
			{0,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1,1,0,1,0},
			{0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,1,0,1,0,1,1,1,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,0,1,1,1,1,0,1,0,0,1,0,1,1,1,0,1,1,0,1,1,1,0},
			{0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,7,1,1,1,0},
			{0,0,0,0,1,1,1,1,1,0,1,1,1,0,1,5,1,0,1,1,1,1,0,1,1,0,0,0,0,0},
			{1,0,1,0,0,0,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,1,1},
			{1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,0},
			{1,1,1,5,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,5,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,1,1,1,0,1,0,1,1,0},
			{0,0,1,1,1,1,1,1,1,0,1,1,1,0,1,3,1,0,0,1,0,1,1,1,0,1,0,1,1,0},
			{0,1,1,1,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,0,1,1,1,0,1,0,1,1,0},
			{0,1,1,1,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,1,1,0},
			{0,1,1,1,0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,0,1,0,1,1,0},
			{0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0},
	};
	
	private ArrayList<Item> items;
	
	
	public Maze(int scale)
	{
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
					defaultPosXPlayer = i;
					defaultPosYPlayer = j;
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
					case 5:
						g.setColor(Color.PINK);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 6: 
						g.setColor(Color.BROWN);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 7: 
						g.setColor(Color.BLUE);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 8: 
						g.setColor(Color.GREEN);
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
	
	public int getDefaultPosXPlayer() {
		return defaultPosXPlayer;
	}
	
	public int getDefaultPosYPlayer() {
		return defaultPosYPlayer;
	}
	
	
	public ArrayList<Item>getItems()
	{
		return items;
	}
	
}
