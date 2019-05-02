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
		for(int i=0; i<items.size(); i++) 
		{
			if(player.getPosX() + mapScale - player.getSpeed() >= items.get(i).getPosX() 
				&& player.getPosY() >= items.get(i).getPosY()
				&& player.getPosX() <= items.get(i).getPosX() + mapScale - player.getSpeed()
				&& player.getPosY() <= items.get(i).getPosY() + mapScale - player.getSpeed()
				) 
			{
				player.addItem(items.get(i));
				System.out.println(player.getBag().size());
				//items.remove(i);
				break;
			}
			
			if(player.getPosY() + mapScale - player.getSpeed() >= items.get(i).getPosY() 
					&& player.getPosX() + mapScale - player.getSpeed() >= items.get(i).getPosX()
					&& player.getPosY() + mapScale - player.getSpeed() <= items.get(i).getPosY() + mapScale - player.getSpeed()
					&& player.getPosX() <= items.get(i).getPosX() + mapScale - player.getSpeed()
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
		for(int i=0; i<enemies.size(); i++) 
		{
			if(player.getPosX() + mapScale - player.getSpeed() >= enemies.get(i).getPosX() 
				&& player.getPosY() >= enemies.get(i).getPosY()
				&& player.getPosX() <= enemies.get(i).getPosX() + mapScale - player.getSpeed()
				&& player.getPosY() <= enemies.get(i).getPosY() + mapScale - player.getSpeed()
				) 
			{
				System.out.println("CRUNCH");
				player.setAlive(false);
				break;
			}
			
			if(player.getPosY() + mapScale - player.getSpeed() >= enemies.get(i).getPosY() 
					&& player.getPosX() + mapScale - player.getSpeed() >= enemies.get(i).getPosX()
					&& player.getPosY() + mapScale - player.getSpeed() <= enemies.get(i).getPosY() + mapScale - player.getSpeed()
					&& player.getPosX() <= enemies.get(i).getPosX() + mapScale - player.getSpeed()
					) 
				{
					System.out.println("CRUNCH");
					player.setAlive(false);
					break;
				}		
		}
	}
}
