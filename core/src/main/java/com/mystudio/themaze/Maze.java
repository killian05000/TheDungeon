package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Maze 
{
	private int mapScale;
	private int[][] matrix;
	private ArrayList<Texture> maps;
	private EventListener eventListener;

	private Player player;
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private ArrayList<ArrayList<Integer>> teleporters;

	private int doorX;
	private int doorY;

	/**
	 * 
	 * @param map    : the mapTranslator object containing the game matrix
	 * @param player : player instance
	 */
	public Maze(MapTranslator map) 
	{
		matrix = new int[map.getMatrix().length][map.getMatrix()[0].length];
		matrix = map.getMatrix();

		eventListener = new EventListener();

		enemies = new ArrayList<Enemy>();

		teleporters = new ArrayList<ArrayList<Integer>>();
		items = new ArrayList<Item>();

		maps = new ArrayList<Texture>();
		maps.add(new Texture("map/Map.png"));
		maps.add(new Texture("map/DoorClosed.png"));
		maps.add(new Texture("map/DoorOpen.png"));
		maps.add(new Texture("map/MapSecondLayer.png"));
		maps.add(new Texture("map/GameOver.png"));
		maps.add(new Texture("map/Victory.png"));

		mapScale = map.getTileSize();
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
					player = new Player(i, j, this, eventListener);
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
	 * Paint the game matrix which was generated by the image skeleton
	 * 
	 * @param g
	 */
	public void PaintMaze(Graphics g) 
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				switch (matrix[i][j]) 
				{
				case 0:
					g.setColor(Color.GRAY);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 1:
					g.setColor(Color.BLACK);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 2:
					g.setColor(Color.BLUE);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 3:
					g.setColor(Color.PINK);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 4:
					g.setColor(Color.RED);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 5:
					g.setColor(Color.BROWN);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 6:
					g.setColor(Color.GREEN);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				case 9:
					g.setColor(Color.PURPLE);
					g.fillRect(j * mapScale, i * mapScale, mapScale, mapScale);
					break;
				}
			}
		}
	}

	/**
	 * Paint the map displayed to the user
	 * 
	 * @param g
	 * @param path : map path
	 */
	public void displayUserMap(Graphics g, Player player) 
	{
		g.drawTexture(maps.get(0), 0, 0, maps.get(0).getWidth(), maps.get(0).getHeight());

		if (player.getBag().size() != 3)
			g.drawTexture(maps.get(1), doorY, doorX, maps.get(1).getWidth(), maps.get(1).getHeight());
		else
			g.drawTexture(maps.get(2), doorY, doorX, maps.get(2).getWidth(), maps.get(1).getHeight());

	}

	public void displayUserMapSecondLayer(Graphics g, Player player) 
	{
		g.drawTexture(maps.get(3), 0, 0, maps.get(2).getWidth(), maps.get(2).getHeight());

		if (!player.getAlive())
			g.drawTexture(maps.get(4), 0, 0, maps.get(3).getWidth(), maps.get(3).getHeight());

		else if (player.getEscape())
			g.drawTexture(maps.get(5), 0, 0, maps.get(4).getWidth(), maps.get(4).getHeight());
	}

	/**
	 * Paint the items
	 * 
	 * @param g
	 */
	public void displayItems(Graphics g) 
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

	public EventListener getEventListener() 
	{
		return eventListener;
	}
}
