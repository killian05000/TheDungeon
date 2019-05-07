package com.mystudio.themaze;

import java.util.ArrayList;

public class Collision {
	Player player;
	ArrayList<Item> items;
	ArrayList<Enemy> enemies;
	int mapScale;
	
	/**
	 * 
	 * @param _items : maze items
	 * @param _player : player instance
	 * @param scale : tile size
	 */
	public Collision(ArrayList<Item> _items, Player _player, ArrayList<Enemy> _enemies, int scale) 
	{
		player = _player;
		items = _items;
		enemies = _enemies;
		mapScale = scale;
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
		
		for(int i=0; i<items.size(); i++) 
		{		
			iPosX = items.get(i).getPosX();
			iPosY = items.get(i).getPosY();
			
			
			if(pPosX + mapScale - pSpeed >= iPosX 
				&& pPosY >= iPosY
				&& pPosX <= iPosX + mapScale - pSpeed
				&& pPosY <= iPosY + mapScale - pSpeed
				) 
			{
				player.addItem(items.get(i));
				System.out.println(player.getBag().size());
				//items.remove(i);
				break;
			}
			
			if(pPosY + mapScale - pSpeed >= iPosY 
					&& pPosX + mapScale - pSpeed >= iPosX
					&& pPosY + mapScale - pSpeed <= iPosY + mapScale - pSpeed
					&& pPosX <= iPosX + mapScale - pSpeed
					) 
			{
				player.addItem(items.get(i));
				System.out.println(player.getBag().size());
				//items.remove(i);
				break;
			}		
		}
	}
	
	public void verifyEnemy() 
	{
		int pPosX, pPosY, pSpeed, ePosX, ePosY, eSpeed, iPosX, iPosY = 0;
		pPosX = player.getPosX();
		pPosY = player.getPosY();
		pSpeed = player.getSpeed();
		
		for(int i=0; i<enemies.size(); i++) 
		{
			ePosX = enemies.get(i).getPosX();
			ePosY = enemies.get(i).getPosY();
			eSpeed = enemies.get(i).getSpeed();
			
			if(pPosX + mapScale - pSpeed >= ePosX 
				&& pPosY > ePosY
				&& pPosX < ePosX + mapScale - pSpeed
				&& pPosY < ePosY + mapScale - pSpeed
				) 
			{
				player.setAlive(false);
				break;
			}
			
			if(pPosY + mapScale - pSpeed > ePosY 
					&& pPosX + mapScale - pSpeed > ePosX
					&& pPosY + mapScale - pSpeed < ePosY + mapScale - pSpeed
					&& pPosX < ePosX + mapScale - pSpeed
					) 
			{
					player.setAlive(false);
					break;
			}		
			
			for(int j=0; j<items.size(); j++)
			{
				iPosX = items.get(j).getPosX();
				iPosY = items.get(j).getPosY();
				
				if(ePosX + mapScale - eSpeed >= iPosX 
						&& ePosY >= iPosY
						&& ePosX <= iPosX + mapScale - eSpeed
						&& ePosY <= iPosY + mapScale - eSpeed
						) 
					{
						enemies.get(i).respawn();
						items.get(j).respawn();
						break;
					}
					
					if(ePosY + mapScale - eSpeed >= iPosY 
							&& ePosX + mapScale - eSpeed >= iPosX
							&& ePosY + mapScale - eSpeed <= iPosY + mapScale - eSpeed
							&& ePosX <= iPosX + mapScale - eSpeed
							) 
					{
						enemies.get(i).respawn();
						items.get(j).respawn();
						break;
					}
			}
		}
	}
}
