package com.mystudio.themaze;

import java.util.ArrayList;

public class Collision {
	Player player;
	ArrayList<Item> items;
	int mapScale;
	
	public Collision(ArrayList<Item> items, Player player, int mapScale) 
	{
		this.player = player;
		this.items = items;
		this.mapScale = mapScale;
	}
	
	public ArrayList<Item> verify() 
	{
		for(int i=0; i<items.size(); i++) 
		{
			/*if(player.getPosX() == (items.get(i).getPixelX()) && player.getPosY() == (items.get(i).getPixelY())
				|| player.getPosX() == (items.get(i).getPixelX()) && player.getPosY() == (items.get(i).getPixelY())
				) 
			{
				player.addItem(items.get(i));
				items.remove(i);
			}
			
			if(player.getPosX() == (items.get(i).getPixelX()) && (player.getPosY()+mapScale) == (items.get(i).getPixelY()+mapScale)) 
			{
				
				player.addItem(items.get(i));
				System.out.println(player.items.size());
				items.remove(i);
			}*/
			/*
			if(player.getPosX() == (items.get(i).getPixelX()) && player.getPosY() == (items.get(i).getPixelY())) 
			{
				player.addItem(items.get(i));
				items.remove(i);
			}
			
			if(player.getPosX() == (items.get(i).getPixelX()) && player.getPosY() == (items.get(i).getPixelY())) 
			{
				player.addItem(items.get(i));
				items.remove(i);
			}*/
			
			
			if(player.getPosX()+mapScale-player.getSpeed()>=items.get(i).getPixelX() 
				&& player.getPosY() >=items.get(i).getPixelY()
				&& player.getPosX() <=items.get(i).getPixelX()+mapScale-player.getSpeed()
				&& player.getPosY() <=items.get(i).getPixelY()+mapScale-player.getSpeed()
				) 
			{
				player.addItem(items.get(i));
				System.out.println(player.items.size());
				items.remove(i);
				break;
			}
			
			if(player.getPosY()+mapScale-player.getSpeed()>=items.get(i).getPixelY() 
					&& player.getPosX()+mapScale-player.getSpeed()>=items.get(i).getPixelX()
					&& player.getPosY()+mapScale-player.getSpeed() <=items.get(i).getPixelY()+mapScale-player.getSpeed()
					&& player.getPosX() <=items.get(i).getPixelX()+mapScale-player.getSpeed()
					) 
				{
					player.addItem(items.get(i));
					System.out.println(player.items.size());
					items.remove(i);
					break;
				}
			
			
		}
		return items;
	}

}
