package maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import enums.MapObject;

public final class MapTranslator 
{
	private static BufferedImage map;
	private static int tileSize = 32;
	private static int[][] matrix;
	
	private MapTranslator()
	{}

	/**
	 * Go through the image skeleton and get each of its tile's RGB value
	 */
	public static Maze translate(String filePath) 
	{
		try 
		{
			map = ImageIO.read(MapTranslator.class.getResource(filePath));
		} catch (IOException e) 
		{
			System.err.println("Error during the skeleton map translation");
			e.printStackTrace();
		}

		matrix = new int[map.getHeight() / tileSize][map.getWidth() / tileSize];
		
		int xMatrix = 0; // Matrix x
		int yMatrix = 0; // Matrix y

		for (int x = tileSize / 2; x < map.getHeight(); x += tileSize) 
		{
			for (int y = tileSize / 2; y < map.getWidth(); y += tileSize) 
			{
				int pixel = map.getRGB(y, x); // get the RGB value of a pixel

				// Split the pixel R, G and B value in a vector
				int[] RgbVector = new int[3];
				RgbVector[0] = (pixel >> 16) & 0xff;
				RgbVector[1] = (pixel >> 8) & 0xff;
				RgbVector[2] = pixel & 0xff;

				buildMatrix(RgbVector, xMatrix, yMatrix);
				yMatrix++;
			}
			yMatrix = 0;
			xMatrix++;
		}
		// displayMatrix();
		
		Maze maze = new Maze(matrix, tileSize);
		return maze;
	}

	/**
	 * Build the core matrix of the game according to the image skeleton color code
	 * 
	 * @param p : RGB vector
	 * @param x : axis x of the matrix
	 * @param y : axis y of the matrix
	 */
	private static void buildMatrix(int[] p, int x, int y) // (x = line,y = column)
	{
		if (p[0] == 198 && p[1] == 198 && p[2] == 198)
			matrix[x][y] = MapObject.ALLEY.ordinal();
		else if (p[0] == 60 && p[1] == 60 && p[2] == 60)
			matrix[x][y] = MapObject.WALL.ordinal();
		else if (p[0] == 103 && p[1] == 175 && p[2] == 233)
			matrix[x][y] = MapObject.PLAYER_SPAWN.ordinal();
		else if (p[0] == 174 && p[1] == 64 && p[2] == 64)
			matrix[x][y] = MapObject.RANDOM_ENEMY_SPAWN.ordinal();
		else if (p[0] == 111 && p[1] == 19 && p[2] == 17)
			matrix[x][y] = MapObject.TRAP.ordinal();
		else if (p[0] == 95 && p[1] == 76 && p[2] == 54)
			matrix[x][y] = MapObject.DOOR.ordinal();
		else if (p[0] == 158 && p[1] == 233 && p[2] == 135)
			matrix[x][y] = MapObject.EXIT.ordinal();
		else if (p[0] == 46 && p[1] == 68 && p[2] == 117)
			matrix[x][y] = MapObject.OBJECT_SPAWN.ordinal();
		else if (p[0] == 217 && p[1] == 211 && p[2] == 109)
			matrix[x][y] = MapObject.SMART_ENEMY_SPAWN.ordinal();
		else if (p[0] == 163 && p[1] == 104 && p[2] == 162)
			matrix[x][y] = MapObject.TELEPORTER.ordinal();
	}

	/**
	 * Display the game matrix
	 */
	private void displayMatrix() 
	{
		for (int x = 0; x < matrix.length; x += 1) 
		{
			for (int y = 0; y < matrix[0].length; y += 1) 
			{
				System.out.print(matrix[x][y] + ",");
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
