package com.mystudio.themaze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mapTranslator 
{
	private BufferedImage map;
	private int tileSize = 32;
	private int[][] matrix;
	
	/**
	 * @param path : skeleton image file path
	 */
	public mapTranslator(String path)
	{
		try
		{
			map = ImageIO.read(new File("../assets/"+path));
		}
		catch(IOException e)
		{
			System.err.println("Error during the skeleton map translation");
			e.printStackTrace();
		}
		
		matrix = new int[map.getHeight()/tileSize][map.getWidth()/tileSize];		
	}
	
	/**
	 * Go through the image skeleton and get each of its tile's RGB value
	 */
	public void translate()
	{
		int xM = 0; // Matrix x
		int yM = 0;	// Matrix y	

		for(int x = tileSize/2; x < map.getHeight(); x += tileSize)
		{
			for(int y = tileSize/2; y < map.getWidth(); y += tileSize)
			{
				int pixel = map.getRGB(y,x); // get the RGB value of a pixel
				
				// Split the pixel R, G and B value in a vector
				int[] v = new int[3]; 
				v[0] = (pixel >> 16) & 0xff;
				v[1] = (pixel >> 8) & 0xff;
				v[2] = pixel & 0xff;
				
				//System.out.println("["+x+","+y+"] : "+"("+v[0]+","+v[1]+","+v[2]+")");
				buildMatrix(v,xM,yM);
				yM++;
			}
			yM=0;
			xM++;
		}
		displayMatrix();		
	}
	
	/**
	 * Build the core matrix of the game according to the image skeleton color code
	 * @param p : RGB vector
	 * @param x : axis x of the matrix
	 * @param y : axis y of the matrix
	 */
	public void buildMatrix(int[] p, int x, int y) // (x = line,y = column)
	{
		if(p[0]==198 && p[1]==198 && p[2]==198) // ALLEYS
			matrix[x][y] = 0;
		else if(p[0]==60 && p[1]==60 && p[2]==60) // WALLS
			matrix[x][y] = 1;
		else if(p[0]==103 && p[1]==175 && p[2]==233) // PLAYER SPAWN
			matrix[x][y] = 2;
		else if(p[0]==174 && p[1]==64 && p[2]==64) // ENEMY SPAWN
			matrix[x][y] = 3;
		else if(p[0]==111 && p[1]==19 && p[2]==17) // TRAPS
			matrix[x][y] = 4;
		else if(p[0]==95 && p[1]==76 && p[2]==54) // EXIT DOOR
			matrix[x][y] = 5;
		else if(p[0]==158 && p[1]==233 && p[2]==135) // EXIT
			matrix[x][y] = 6;		
		else if(p[0]==163 && p[1]==104 && p[2]==162) // TELEPORTER
			matrix[x][y] = 9;		
	}
	
	/**
	 * Display the game matrix
	 */
	public void displayMatrix()
	{
		//System.out.println("Height : "+matrix.length);
		//System.out.println("Width : "+matrix[0].length);
		for(int x=0; x<matrix.length; x+=1)
		{
			for(int y=0; y<matrix[0].length; y+=1)
			{
				System.out.print(matrix[x][y]+",");
			}
			System.out.println();
		}
	}
	
	public int[][] getMatrix()
	{
		return matrix;
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
}
