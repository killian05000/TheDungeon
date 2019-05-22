package maze;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class Item 
{
	private Texture sprite;
	private int posX;
	private int posY;
	private int frameCounter = 0;
	public boolean isLaunched;
	private int defaultPosX;
	private int defaultPosY;
	private int[][] matrix;

	// Used when is thrown
	private int range;
	private int dir;
	private int speed;

	private int mapScale;

	/**
	 * 
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
	 * 
	 * @param g
	 */
	public void render(Graphics g) 
	{
		if (isLaunched)
			animation(posX, posY, range, speed, dir);
		g.drawTexture(sprite, posY, posX, mapScale, mapScale);
	}

	public void animation(int pPosX, int pPosY, int range, int speed, int dir) 
	{
		isLaunched = true;
		posX = pPosX;
		posY = pPosY;
		this.range = range;
		this.dir = dir;
		this.speed = speed;

		if (frameCounter < range * mapScale) 
		{
			if (dir == 0 && (posX - speed) > 0
				&& (matrix[(posX - speed) / mapScale][(posY + (mapScale * 20) / 100) / mapScale] == 0
				&& matrix[(posX - speed) / mapScale][(posY + (mapScale * 80) / 100) / mapScale] == 0)) 
			{
				posX = posX - speed;
				frameCounter += 8;
			} 
			else if (dir == 1 && (posY + mapScale + speed) / mapScale < matrix[0].length
					&& (matrix[(posX + (mapScale * 20) / 100) / mapScale][(posY + mapScale + speed) / mapScale] == 0
					&& matrix[(posX + (mapScale * 80) / 100) / mapScale][(posY + mapScale + speed)
									/ mapScale] == 0)) 
			{
				posY = posY + speed;
				frameCounter += 8;
			} 
			else if (dir == 2 
					&& (posX + mapScale + speed) / mapScale < matrix.length
					&& (matrix[(posX + mapScale + speed) / mapScale][(posY + (mapScale * 20) / 100) / mapScale] == 0
					&& matrix[(posX + mapScale + speed) / mapScale][(posY + (mapScale * 80) / 100) / mapScale] == 0)) 
			{
				posX = posX + speed;
				frameCounter += 8;
			} 
			else if (dir == 3 && (posY - speed) > 0
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
