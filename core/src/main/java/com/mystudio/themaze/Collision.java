package com.mystudio.themaze;

import java.util.ArrayList;

public class Collision {
	Player player;
	ArrayList<Item> items;
	int mapScale;
	
	/**
	 * 
	 * @param _items : maze items
	 * @param _player : player instance
	 * @param scale : tile size
	 */
	public Collision(ArrayList<Item> _items, Player _player, int scale) 
	{
		player = _player;
		items = _items;
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
				System.out.println(player.getItems().size());
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
					System.out.println(player.getItems().size());
					//items.remove(i);
					break;
				}		
		}
	}
}
