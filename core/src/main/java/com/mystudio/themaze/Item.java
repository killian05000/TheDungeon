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
	QuadTree<CollisionPoint> collisions;
	
	public Item(int x, int y, String path)
	{
		posX = x;
		posY = y;
		sprite = new Texture(path);
		collisions = new PointQuadTree<CollisionPoint>(4, 2, 0, 0, 640, 320);
		collisions.add(new CollisionPoint(posX+25, posY+25));
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g, int mapScale) 
	{	
		g.drawTexture(sprite, posY*mapScale, posX*mapScale, mapScale, mapScale);
	}
}
