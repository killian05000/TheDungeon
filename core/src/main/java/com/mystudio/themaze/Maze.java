package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Maze 
{
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
	
	public int[][] getMatrix() 
	{
		return matrix;
	}
	
	public void PaintMaze(Graphics g) 
	{
		int length =50;
		for (int i=0; i<matrix.length; i++) 
		{
			for (int j=0; j<matrix.length; j++) 
			{
				switch(matrix[i][j])
				{
					case 0:
						g.setColor(Color.WHITE);
						g.fillRect(j*length,i*length,length,length);
						break;
					case 1:
						g.setColor(Color.BLACK);
						g.fillRect(j*length,i*length,length,length);
						break;
				 }
			 }
		 } 
	 }	
}
