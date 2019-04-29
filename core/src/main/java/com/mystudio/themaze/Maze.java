package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Maze 
{
	private int mapScale;
	private int [][] matrix = 
		{
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 0, 0, 1, 1, 0, 0 },
			{ 1, 1, 0, 1, 0, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 0, 1, 1 },
			{ 1, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		  };
	
	public Maze(int scale)
	{
		mapScale = matrix.length*scale;
		System.out.println(mapScale);
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
						g.setColor(Color.WHITE);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
					case 1:
						g.setColor(Color.BLACK);
						g.fillRect(j*mapScale,i*mapScale,mapScale,mapScale);
						break;
				 }
			 }
		 } 
	 }	
	
	public int getMapScale()
	{
		return mapScale;
	}
}
