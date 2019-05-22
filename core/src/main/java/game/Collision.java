package game;

import java.util.ArrayList;

import enemy.Enemy;
import maze.Item;
import music.MusicPlayer;

public class Collision 
{
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private int mapScale;
	private Player player;
	private MusicPlayer eventListener;

	public Collision(ArrayList<Item> items, Player player, ArrayList<Enemy> enemies, int scale, MusicPlayer eventListener) 
	{
		this.player = player;
		this.items = items;
		this.enemies = enemies;
		mapScale = scale;
		this.eventListener = eventListener;
	}

	/**
	 * Check if the player has collided with an object
	 */
	public void verify() 
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
					eventListener.setPickingObjectSoundON(true);
					break;
				}

				if (playerPosY + mapScale - playerSpeed >= itemPosY 
					&& playerPosX + mapScale - playerSpeed >= itemPosX
					&& playerPosY + mapScale - playerSpeed <= itemPosY + mapScale - playerSpeed
					&& playerPosX <= itemPosX + mapScale - playerSpeed) 
				{
					player.addItem(items.get(i));
					eventListener.setPickingObjectSoundON(true);
					break;
				}
			}
		}
	}

	public void verifyEnemy() 
	{
		int playerPosX, playerPosY, playerSpeed, enemyPosX, enemyPosY, enemySpeed, itemPosX, itemPosY = 0;
		playerPosX = player.getPosX();
		playerPosY = player.getPosY();
		playerSpeed = player.getSpeed();

		for (int i = 0; i < enemies.size(); i++) 
		{
			enemyPosX = enemies.get(i).getPosX();
			enemyPosY = enemies.get(i).getPosY();
			enemySpeed = enemies.get(i).getSpeed();

			if (playerPosX + mapScale - playerSpeed >= enemyPosX 
				&& playerPosY >= enemyPosY && playerPosX <= enemyPosX + mapScale - playerSpeed
				&& playerPosY <= enemyPosY + mapScale - playerSpeed) 
			{
				player.setAlive(false);
				eventListener.setGameOverSoundON(true);
				break;
			}

			if (playerPosY + mapScale - playerSpeed >= enemyPosY 
				&& playerPosX + mapScale - playerSpeed >= enemyPosX
				&& playerPosY + mapScale - playerSpeed <= enemyPosY + mapScale - playerSpeed 
				&& playerPosX <= enemyPosX + mapScale - playerSpeed) 
			{
				player.setAlive(false);
				eventListener.setGameOverSoundON(true);
				break;
			}

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
