package maze;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import enemy.Enemy;
import enemy.RandomEnemy;
import enemy.SmartEnemy;
import game.Player;
import music.MusicPlayer;

public class Maze 
{
	private int mapScale;
	private int[][] matrix;
	private ArrayList<Texture> maps;
	private MusicPlayer musicPlayer;

	private Player player;
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private ArrayList<ArrayList<Integer>> teleporters;

	private int doorX;
	private int doorY;

	/**
	 * Initializes all the moving object's position, and save the teleporters position.
	 * Load all the maze sprites
	 * @param map    : the mapTranslator object containing the game matrix
	 * @param player : player instance
	 */
	public Maze(int[][] matrix, int tileSize) 
	{
		this.matrix = new int[matrix.length][matrix[0].length];
		this.matrix = matrix;

		musicPlayer = new MusicPlayer();

		enemies = new ArrayList<Enemy>();

		teleporters = new ArrayList<ArrayList<Integer>>();
		items = new ArrayList<Item>();

		maps = new ArrayList<Texture>();
		maps.add(new Texture("map/map.png"));
		maps.add(new Texture("map/doorClosed.png"));
		maps.add(new Texture("map/doorOpen.png"));
		maps.add(new Texture("map/mapSecondLayer.png"));
		maps.add(new Texture("map/gameOver.png"));
		maps.add(new Texture("map/victory.png"));

		mapScale = tileSize;
		initialiseDefaultPlayerPosition();
		initialiseDefaultEnemiesPosition();
		initialiseDefaultItemsPosition();
		initialiseTeleporterPosition();
		initialiseDoorPosition();
	}

	/**
	 * Look for the position of the player's spawn in the matrix
	 */
	private void initialiseDefaultPlayerPosition() 
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				if (matrix[i][j] == 2)
					player = new Player(i, j, this, musicPlayer);
			}
		}
	}

	/**
	 * Look for the position of the enemies' spawn in the matrix
	 */
	private void initialiseDefaultEnemiesPosition() 
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				if (matrix[i][j] == 3)
					enemies.add(new RandomEnemy(i, j, this, 2));
				else if (matrix[i][j] == 8)
					enemies.add(new SmartEnemy(i, j, this, player, 4));
			}
		}
	}

	/**
	 * Look for the original position of the items in the matrix
	 */
	private void initialiseDefaultItemsPosition() 
	{
		ArrayList<String> spritesPath = new ArrayList<String>();
		spritesPath.add("item/key.png");
		spritesPath.add("item/sword.png");
		spritesPath.add("item/potion.png");
		int x = 0;

		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				if (matrix[i][j] == 7 && x < spritesPath.size()) 
				{
					items.add(new Item(i, j, mapScale, spritesPath.get(x), this));
					matrix[i][j] = 0;
					x++;
				}
			}
		}
	}

	/**
	 * save the teleporters coordinates
	 */
	private void initialiseTeleporterPosition() 
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				if (matrix[i][j] == 9) 
				{
					ArrayList<Integer> pos = new ArrayList<Integer>();
					pos.add(i);
					pos.add(j);
					teleporters.add(pos);
				}
			}
		}
	}

	/**
	 * Initialize the door position
	 */
	private void initialiseDoorPosition() 
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				if (matrix[i][j] == 5) 
				{
					doorX = (i * mapScale) - mapScale;
					doorY = j * mapScale;
				}
			}
		}
	}

	/**
	 * Teleport the player onto one of the 3 remaining teleporters
	 * @param x : entrance teleporter X position
	 * @param y : entrance teleporter Y position
	 * @return : the chosen teleporter position
	 */
	public int[] teleportPlayer(int x, int y) 
	{
		int rand = 0;
		int newX = 0;
		int newY = 0;
		do 
		{
			rand = (int) (Math.random() * (4 - 0));
			newX = teleporters.get(rand).get(0);
			newY = teleporters.get(rand).get(1);
		} while (teleporters.get(rand).get(0) == x && teleporters.get(rand).get(1) == y);

		int[] newPos = { newX, newY };
		return newPos;
	}

	/**
	 * Display the map and its door to the user
	 * @param g
	 * @param path : map path
	 */
	public void displayUserMap(Graphics g, Player player) 
	{
		g.drawTexture(maps.get(0), 0, 0, maps.get(0).getWidth(), maps.get(0).getHeight()); // User map

		if (player.getBag().size() != player.getRequiredObjectNumber())
			g.drawTexture(maps.get(1), doorY, doorX, maps.get(1).getWidth(), maps.get(1).getHeight()); // Door closed
		else
			g.drawTexture(maps.get(2), doorY, doorX, maps.get(2).getWidth(), maps.get(1).getHeight()); // Door opened

	}
	
	/**
	 * Display the map second layer
	 * @param g
	 * @param player
	 */
	public void displayUserMapSecondLayer(Graphics g) 
	{
		g.drawTexture(maps.get(3), 0, 0, maps.get(3).getWidth(), maps.get(3).getHeight()); // Map second Layer
	}
	
	/**
	 * Test if the game is over, and display the game over sprite or victory sprite accordingly
	 * @param g
	 */
	public void displayEndGameScreen(Graphics g)
	{
		if (!player.getAlive())
			g.drawTexture(maps.get(4), 0, 0, maps.get(4).getWidth(), maps.get(4).getHeight()); // Game over

		else if (player.getEscape())
			g.drawTexture(maps.get(5), 0, 0, maps.get(5).getWidth(), maps.get(5).getHeight()); // Victory
	}

	/**
	 * Render the items sprites
	 * @param g
	 */
	public void renderItems(Graphics g) 
	{
		if (items.size() > 0) 
		{
			for (int i = 0; i < items.size(); i++) 
			{
				items.get(i).render(g);
			}
		}
	}

	/**
	 * Adding items into the maze
	 * 
	 * @param item
	 */
	public void addItem(Item item) 
	{
		items.add(item);
	}

	public int[][] getMatrix() 
	{
		return matrix;
	}

	public int getMapScale() 
	{
		return mapScale;
	}

	public ArrayList<Item> getItems() 
	{
		return items;
	}

	public Player getPlayer() 
	{
		return player;
	}

	public ArrayList<Enemy> getEnemies() 
	{
		return enemies;
	}

	public MusicPlayer getMusicPlayer() 
	{
		return musicPlayer;
	}
}
