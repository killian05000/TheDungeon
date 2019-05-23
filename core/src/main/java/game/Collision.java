package game;

import java.util.ArrayList;

import enemy.Enemy;
import maze.Item;
import music.SoundPlayer;

public class Collision 
{
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private int mapScale;
	private Player player;
	private SoundPlayer soundPlayer;

	/**
	 * Instantiate the map moving objects that could collide
	 * @param items : items list
	 * @param player : player instance
	 * @param enemies : enemies list
	 * @param scale : tile size
	 * @param soundPlayer : soundPlayer instance to trigger sounds
	 */
	public Collision(ArrayList<Item> items, Player player, ArrayList<Enemy> enemies, int scale, SoundPlayer soundPlayer) 
	{
		mapScale = scale;
		this.player = player;
		this.items = items;
		this.enemies = enemies;
		this.soundPlayer = soundPlayer;
	}
	
	/**
	 * Check all type of collisions
	 */
	public void checkCollisions()
	{
		checkPlayerObjectCollision();
		checkEnemyPlayerCollision();
		checkEnemyObjectCollision();
	}

	/**
	 * Check if the player has collided with an object
	 */
	public void checkPlayerObjectCollision() 
	{
		int playerPosX, playerPosY, itemPosX, itemPosY, playerSpeed = 0;
		playerPosX = player.getPosX();
		playerPosY = player.getPosY();
		playerSpeed = player.getSpeed();

		for (int i = 0; i < items.size(); i++) 
		{
			if (!items.get(i).isLaunched) 
			{
				itemPosX = items.get(i).getPosX();
				itemPosY = items.get(i).getPosY();

				if (playerPosX + mapScale - playerSpeed >= itemPosX 
					&& playerPosY >= itemPosY && playerPosX <= itemPosX + mapScale - playerSpeed
					&& playerPosY <= itemPosY + mapScale - playerSpeed) 
				{
					player.addItem(items.get(i));
					soundPlayer.setPickingObjectSoundON(true);
					break;
				}

				if (playerPosY + mapScale - playerSpeed >= itemPosY 
					&& playerPosX + mapScale - playerSpeed >= itemPosX
					&& playerPosY + mapScale - playerSpeed <= itemPosY + mapScale - playerSpeed
					&& playerPosX <= itemPosX + mapScale - playerSpeed) 
				{
					player.addItem(items.get(i));
					soundPlayer.setPickingObjectSoundON(true);
					break;
				}
			}
		}
	}
	
	/**
	 * Check for each enemy if it has collided with the player
	 */
	public void checkEnemyPlayerCollision() 
	{
		int playerPosX, playerPosY, playerSpeed, enemyPosX, enemyPosY;
		playerPosX = player.getPosX();
		playerPosY = player.getPosY();
		playerSpeed = player.getSpeed();

		for (int i = 0; i < enemies.size(); i++) 
		{
			enemyPosX = enemies.get(i).getPosX();
			enemyPosY = enemies.get(i).getPosY();

			if (playerPosX + mapScale - playerSpeed >= enemyPosX 
				&& playerPosY >= enemyPosY && playerPosX <= enemyPosX + mapScale - playerSpeed
				&& playerPosY <= enemyPosY + mapScale - playerSpeed) 
			{
				player.setAlive(false);
				soundPlayer.setGameOverSoundON(true);
				break;
			}

			if (playerPosY + mapScale - playerSpeed >= enemyPosY 
				&& playerPosX + mapScale - playerSpeed >= enemyPosX
				&& playerPosY + mapScale - playerSpeed <= enemyPosY + mapScale - playerSpeed 
				&& playerPosX <= enemyPosX + mapScale - playerSpeed) 
			{
				player.setAlive(false);
				soundPlayer.setGameOverSoundON(true);
				break;
			}
		}
	}
	
	/**
	 * Check for each enemy if it has collided with a thrown object
	 */	
	public void checkEnemyObjectCollision()
	{
		int enemyPosX, enemyPosY, enemySpeed, itemPosX, itemPosY;		
		
		for (int i = 0; i < enemies.size(); i++) 
		{
			enemyPosX = enemies.get(i).getPosX();
			enemyPosY = enemies.get(i).getPosY();
			enemySpeed = enemies.get(i).getSpeed();
			
			for (int j = 0; j < items.size(); j++) 
			{
				if (items.get(j).isLaunched) 
				{
					itemPosX = items.get(j).getPosX();
					itemPosY = items.get(j).getPosY();
	
					if (enemyPosX + mapScale - enemySpeed >= itemPosX 
						&& enemyPosY >= itemPosY && enemyPosX <= itemPosX + mapScale - enemySpeed
						&& enemyPosY <= itemPosY + mapScale - enemySpeed) 
					{
						enemies.get(i).respawn();
						items.get(j).respawn();
						items.get(j).isLaunched = false;
						break;
					}
	
					if (enemyPosY + mapScale - enemySpeed >= itemPosY 
						&& enemyPosX + mapScale - enemySpeed >= itemPosX
						&& enemyPosY + mapScale - enemySpeed <= itemPosY + mapScale - enemySpeed
						&& enemyPosX <= itemPosX + mapScale - enemySpeed) 
					{
						enemies.get(i).respawn();
						items.get(j).respawn();
						items.get(j).isLaunched = false;
						break;
					}
				}
			}
		}
	}
}
