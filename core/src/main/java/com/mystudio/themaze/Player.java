
package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.collisions.PointQuadTree;
import org.mini2Dx.core.collisions.QuadTree;
import org.mini2Dx.core.collisions.RegionQuadTree;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Player 
{
	private int posX;
	private int posY;
	
	private int defaultPostionX = 1;
	private int defaultPositionY = 9;
	
	private int speed;//Multiple of mapScale
	
	private int mapScale;
	
	private int direction;
	
	private Texture badpac;
	QuadTree<CollisionPoint> collisions;
	
	public Player(int mapScale) 
	{
		posX = defaultPostionX*mapScale;
		posY = defaultPositionY*mapScale;
		badpac = new Texture("badpacLeft.png");
		speed = 5;
		this.mapScale = mapScale;
	}
	/**
	 * Update the direction
	 */
	public  void  update (Maze maze) { // update the player direction according to the user input
		
		int newPosX;
    	int newPosY;
    	
		if(Gdx.input.isKeyPressed(Keys.UP)) 
		{
			badpac = new Texture("badpacUp.png");
			direction=0;
		}
    	if(Gdx.input.isKeyPressed(Keys.RIGHT))
    	{
    		badpac = new Texture("badpacRight.png");
    		direction=1;
    	}
    	if(Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		badpac = new Texture("badpacDown.png");
    		direction=2;
    	}
    	if(Gdx.input.isKeyPressed(Keys.LEFT))
    	{
    		badpac = new Texture("badpacLeft.png");
    		direction=3;
    	}
    	
    	switch(direction)
    	{
			case 0:
				newPosX= posX-speed;
				
				if(newPosX/mapScale>=0 
					&& maze.getMatrix()[newPosX/mapScale][posY/mapScale] == 0
					&& maze.getMatrix()[newPosX/mapScale][(posY+mapScale-speed)/mapScale] == 0
					)
					posX =newPosX;
				break;
			case 1:
				newPosY = posY+speed;
	    		
	    		if((newPosY+mapScale-speed)/mapScale<maze.getMatrix()[0].length 
					&& maze.getMatrix()[posX/mapScale][(newPosY+mapScale-speed)/mapScale] == 0
					&& maze.getMatrix()[(posX+mapScale-speed)/mapScale][(newPosY+mapScale-speed)/mapScale] == 0
					)
					posY = newPosY;
				break;
			case 2:
				newPosX = posX+speed;
	    		
	    		if(newPosX/mapScale<maze.getMatrix().length 
					&& maze.getMatrix()[(newPosX+mapScale-speed)/mapScale][posY/mapScale] == 0
					&& maze.getMatrix()[(newPosX+mapScale-speed)/mapScale][(posY+mapScale-speed)/mapScale] == 0
					) 
					posX = newPosX;			
				break;
			case 3:

				newPosY = posY-speed;
				
	    		if(newPosY>=0 
					&& maze.getMatrix()[posX/mapScale][newPosY/mapScale] == 0
					&& maze.getMatrix()[(posX+mapScale-speed)/mapScale][newPosY/mapScale] == 0
					) 
					posY = newPosY;
	    		break;		
		}
	}
	
	
	public void render(Graphics g) 
	{	
		g.drawTexture(badpac, posY, posX, mapScale, mapScale);
	}
	
	
	public void setPosY(int posY) 
	{
		this.posY = posY;
	}
	
}

