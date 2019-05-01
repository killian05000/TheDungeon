package com.mystudio.themaze;

import org.mini2Dx.core.collisions.PointQuadTree;
import org.mini2Dx.core.collisions.QuadTree;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

public class Item 
{
	private Texture sprite;
	int posX;
	int posY;
	
	int mapScale;

	public Item(int x, int y, int mapScale, String path)
	{
		posX = x;
		posY = y;
		sprite = new Texture(path);

		this.mapScale = mapScale;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g) 
	{	
		g.drawTexture(sprite, posY*mapScale, posX*mapScale, mapScale, mapScale);
	}
	
	
	public int getPixelX() 
	{
		return posX*mapScale;
	}
	
	public int getPixelY() 
	{
		return posY*mapScale;
	}
	
	public void setPosX(int x)
	{
		posX=x;
	}
	
	public void setPosY(int y)
	{
		posY=y;
	}
}
