package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class Item 
{
	private Texture sprite;
	int posX;
	int posY;
	private int frameCounter;
	private boolean isHarmfull;
	private boolean isLaunched;
	private int defaultPosX;
	private int defaultPosY;
	
	int mapScale;

	/**
	 * 
	 * @param x : default x item's position
	 * @param y : default y item's position
	 * @param scale : tile size
	 * @param path : sprite path
	 */
	public Item(int x, int y, int scale, String path)
	{
		sprite = new Texture(path);
		defaultPosX=x*scale;
		defaultPosY=y*scale;
		posX = defaultPosX;
		posY = defaultPosY;

		this.mapScale = scale;
	}
	
	public void update()
	{
		
	}
	
	/**
	 * Render the item sprite
	 * @param g
	 */
	public void render(Graphics g) 
	{	
		g.drawTexture(sprite, posY, posX, mapScale, mapScale);
	}
	
	public void isGettingThrown(int x, int y, int dir)
	{
		if(posX != x || posY != y)
		{
			if(dir==0)
				posX--;
			else if(dir==1)
				posY++;
			else if(dir==2)
				posX++;
			else if(dir==3)
				posY--;
		}
		else
			frameCounter=0;
			
	}
	
	public void respawn()
	{
		posX=defaultPosX;
		posY=defaultPosY;
	}
	
	
	public int getPosX() 
	{
		return posX;
	}
	
	public int getPosY() 
	{
		return posY;
	}
	
	public void setPosX(int x)
	{
		posX = x;
	}
	
	public void setPosY(int y)
	{
		posY = y;
	}
}
