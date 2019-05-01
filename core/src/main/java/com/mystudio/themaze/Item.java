package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class Item 
{
	private Texture sprite;
	int posX;
	int posY;
	
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
		posX = x*scale;
		posY = y*scale;

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
