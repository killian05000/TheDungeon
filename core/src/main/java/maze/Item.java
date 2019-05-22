package maze;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class Item 
{
	// Positions
	private int posX;
	private int posY;
	private int defaultPosX;
	private int defaultPosY;
	
	// Animation
	private Texture sprite;
	private int frameCounter = 0;
	
	// Maze
	private int[][] matrix;
	private int mapScale;

	// Used when is thrown
	public boolean isLaunched;
	private int range;
	private int speed;
	private int direction;	

	/**
	 * Create an item
	 * @param x     : default x item's position
	 * @param y     : default y item's position
	 * @param scale : tile size
	 * @param path  : sprite path
	 */
	public Item(int x, int y, int scale, String path, Maze maze) 
	{
		sprite = new Texture(path);
		defaultPosX = x * scale;
		defaultPosY = y * scale;
		posX = defaultPosX;
		posY = defaultPosY;
		matrix = maze.getMatrix();

		this.mapScale = scale;
	}

	/**
	 * Render the item sprite
	 * @param g
	 */
	public void render(Graphics g) 
	{
		if (isLaunched)
			animation(posX, posY, range, speed, direction);
		
		g.drawTexture(sprite, posY, posX, mapScale, mapScale);
	}
	
	/**
	 * Animate the item when its launched, move from the position
	 * he was launched to the that same position + (range*mapScale) cases.
	 * Stop its course if it has collided with anything.
	 * @param playerPosX : player X position (from where the item is launched)
	 * @param playerPosY : player Y position (from where the item is launched)
	 * @param range : number of cases that the item will cross when thrown
	 * @param speed : speed of the item
	 * @param direction : direction of the thrown
	 */
	public void animation(int playerPosX, int playerPosY, int range, int speed, int direction) 
	{
		isLaunched = true;
		posX = playerPosX;
		posY = playerPosY;
		this.range = range;
		this.speed = speed;
		this.direction = direction;

		if (frameCounter < range * mapScale) 
		{
			if (direction == 0 && (posX - speed) > 0
				&& (matrix[(posX - speed) / mapScale][(posY + (mapScale * 20) / 100) / mapScale] == 0
				&& matrix[(posX - speed) / mapScale][(posY + (mapScale * 80) / 100) / mapScale] == 0)) 
			{
				posX = posX - speed;
				frameCounter += 8;
			} 
			else if (direction == 1 && (posY + mapScale + speed) / mapScale < matrix[0].length
					&& (matrix[(posX + (mapScale * 20) / 100) / mapScale][(posY + mapScale + speed) / mapScale] == 0
					&& matrix[(posX + (mapScale * 80) / 100) / mapScale][(posY + mapScale + speed)
									/ mapScale] == 0)) 
			{
				posY = posY + speed;
				frameCounter += 8;
			} 
			else if (direction == 2 
					&& (posX + mapScale + speed) / mapScale < matrix.length
					&& (matrix[(posX + mapScale + speed) / mapScale][(posY + (mapScale * 20) / 100) / mapScale] == 0
					&& matrix[(posX + mapScale + speed) / mapScale][(posY + (mapScale * 80) / 100) / mapScale] == 0)) 
			{
				posX = posX + speed;
				frameCounter += 8;
			} 
			else if (direction == 3 && (posY - speed) > 0
					&& (matrix[(posX + (mapScale * 20) / 100) / mapScale][(posY - speed) / mapScale] == 0
					&& matrix[(posX + (mapScale * 80) / 100) / mapScale][(posY - speed) / mapScale] == 0)) 
			{
				posY = posY - speed;
				frameCounter += 8;
			} 
			else 
			{
				isLaunched = false;
				frameCounter = 0;
			}
		} 
		else 
		{
			isLaunched = false;
			frameCounter = 0;
		}
	}

	/**
	 * Make the item respawn at its original position
	 */
	public void respawn() 
	{
		posX = defaultPosX;
		posY = defaultPosY;
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
