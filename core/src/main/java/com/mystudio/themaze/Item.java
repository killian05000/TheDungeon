package com.mystudio.themaze;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

public class Item 
{
	private Texture sprite;
	int posX;
	int posY;
	
	public Item(int x, int y, String path)
	{
		posX = x;
		posY = y;
		sprite = new Texture(path);
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g, int mapScale) 
	{	
		g.drawTexture(sprite, posY, posX, mapScale, mapScale);
	}
}
