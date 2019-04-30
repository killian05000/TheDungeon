package com.mystudio.themaze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mapTranslator 
{
	private BufferedImage map;
	private int tileSize;
	private int[][] M;
	
	public mapTranslator(String path) throws IOException
	{
		System.out.println(System.getProperties().get("user.dir"));
		map = ImageIO.read(new File("../assets/"+path));
		tileSize = 30;
		M = new int[map.getWidth()/tileSize][map.getHeight()/tileSize];
		System.out.println(map.getWidth()/tileSize);
		System.out.println(map.getHeight());
		System.out.println(map.getWidth());
	}
	
	public void translate()
	{
		int xM = 0;
		int yM = 0;		

		for(int x=tileSize/2; x<map.getHeight(); x+=tileSize)
		{
			for(int y=tileSize/2; y<map.getWidth(); y+=tileSize)
			{
				int pixel=map.getRGB(x,y);
				int[] v = new int[3];
				v[0] = (pixel >> 16) & 0xff;
				v[1] = (pixel >> 8) & 0xff;
				v[2] = pixel & 0xff;
				
				//System.out.println("["+x+","+y+"] : "+"("+v[0]+","+v[1]+","+v[2]+")");				
				buildMatrix(v,xM,yM);
				xM++;
			}
			xM=0;
			yM++;
		}
		//displayMatrix();		
	}
	
	public void buildMatrix(int[] p, int x, int y) // (x = line,y = column)
	{
		if(p[0]==198 && p[1]==198 && p[2]==198) //ALLEYS
			M[x][y] = 0;
		else if(p[0]==60 && p[1]==60 && p[2]==60) //WALLS
			M[x][y] = 1;
		else if(p[0]==103 && p[1]==175 && p[2]==233) //PLAYER SPAWN
			M[x][y] = 2;
		else if(p[0]==174 && p[1]==64 && p[2]==64) //ENEMY SPAWN
			M[x][y] = 3;
		else if(p[0]==111 && p[1]==19 && p[2]==17) // TRAPS
			M[x][y] = 4;
		else if(p[0]==95 && p[1]==76 && p[2]==54) // EXIT DOOR
			M[x][y] = 5;
		else if(p[0]==158 && p[1]==233 && p[2]==135) // EXIT
			M[x][y] = 6;		
		else if(p[0]==163 && p[1]==104 && p[2]==162) //TELEPORTER
			M[x][y] = 9;		
	}
	
	public void displayMatrix()
	{
		for(int x=0; x<M.length; x+=1)
		{
			for(int y=0; y<M[0].length; y+=1)
			{
				System.out.print(M[x][y]+",");
			}
			System.out.println();
		}
		System.out.println("Width : "+M.length);
		System.out.println("Height : "+M[0].length);
	}
	
	public int[][] getMatrix()
	{
		return M;
	}
}
