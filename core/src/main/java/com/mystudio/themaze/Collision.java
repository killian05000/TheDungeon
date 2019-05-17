package com.mystudio.themaze;

import java.util.ArrayList;

public class Collision 
{

	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private int mapScale;
	private Player player;
	private EventListener eventListener;

	/**
	 * 
	 * @param _items         :
	 * @param _player
	 * @param _enemies
	 * @param scale
	 * @param _eventListener
	 */
	public Collision(ArrayList<Item> items, Player player, ArrayList<Enemy> enemies, int scale,EventListener eventListener) 
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
		int pPosX, pPosY, iPosX, iPosY, pSpeed = 0;
		pPosX = player.getPosX();
		pPosY = player.getPosY();
		pSpeed = player.getSpeed();

		for (int i = 0; i < items.size(); i++) 
		{
			if (!items.get(i).isLaunched) 
			{
				iPosX = items.get(i).getPosX();
				iPosY = items.get(i).getPosY();

				if (pPosX + mapScale - pSpeed >= iPosX 
					&& pPosY >= iPosY && pPosX <= iPosX + mapScale - pSpeed
					&& pPosY <= iPosY + mapScale - pSpeed) 
				{
					player.addItem(items.get(i));
					System.out.println("The player just collected an item");
					eventListener.setPickingObjectSoundON(true);
					// items.remove(i);
					break;
				}

				if (pPosY + mapScale - pSpeed >= iPosY 
					&& pPosX + mapScale - pSpeed >= iPosX
					&& pPosY + mapScale - pSpeed <= iPosY + mapScale - pSpeed
					&& pPosX <= iPosX + mapScale - pSpeed) 
				{
					player.addItem(items.get(i));
					System.out.println("The player just collected an item");
					eventListener.setPickingObjectSoundON(true);
					// items.remove(i);
					break;
				}
			}
		}
	}

	public void verifyEnemy() 
	{
		int pPosX, pPosY, pSpeed, ePosX, ePosY, eSpeed, iPosX, iPosY = 0;
		pPosX = player.getPosX();
		pPosY = player.getPosY();
		pSpeed = player.getSpeed();

		for (int i = 0; i < enemies.size(); i++) 
		{
			ePosX = enemies.get(i).getPosX();
			ePosY = enemies.get(i).getPosY();
			eSpeed = enemies.get(i).getSpeed();

			if (pPosX + mapScale - pSpeed >= ePosX 
				&& pPosY >= ePosY && pPosX <= ePosX + mapScale - pSpeed
				&& pPosY <= ePosY + mapScale - pSpeed) 
			{
				System.out.println("Enemy has collided with the player");
				player.setAlive(false);
				eventListener.setGameOverSoundON(true);
				break;
			}

			if (pPosY + mapScale - pSpeed >= ePosY 
				&& pPosX + mapScale - pSpeed >= ePosX
				&& pPosY + mapScale - pSpeed <= ePosY + mapScale - pSpeed 
				&& pPosX <= ePosX + mapScale - pSpeed) 
			{
				System.out.println("Enemy has collided with the player");
				player.setAlive(false);
				eventListener.setGameOverSoundON(true);
				break;
			}

			for (int j = 0; j < items.size(); j++) 
			{
				if (items.get(j).isLaunched) 
				{
					iPosX = items.get(j).getPosX();
					iPosY = items.get(j).getPosY();

					if (ePosX + mapScale - eSpeed >= iPosX 
						&& ePosY >= iPosY && ePosX <= iPosX + mapScale - eSpeed
						&& ePosY <= iPosY + mapScale - eSpeed) 
					{
						System.out.println("Enemy has collided with an object");
						enemies.get(i).respawn();
						items.get(j).respawn();
						items.get(j).isLaunched = false;
						break;
					}

					if (ePosY + mapScale - eSpeed >= iPosY 
						&& ePosX + mapScale - eSpeed >= iPosX
						&& ePosY + mapScale - eSpeed <= iPosY + mapScale - eSpeed
						&& ePosX <= iPosX + mapScale - eSpeed) 
					{
						System.out.println("Enemy has collided with an object");
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
