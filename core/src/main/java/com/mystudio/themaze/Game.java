package com.mystudio.themaze;


import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import menu.Menu;
import menu.MenuMethode1;

public class Game extends BasicGame 
{
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";
	private EventHandler eventHandler;
	private Maze maze;
	private Player player;
	private Collision collision;
	private ArrayList<Enemy> enemies;
	
	//MenuMethode1 menu = new MenuMethode1();
	Menu menu = new Menu();
	
	@Override
	public void initialise() 
	{
		eventHandler = new EventHandler(this);
		//MapTranslator map = new MapTranslator();
		maze = MapTranslator.translate("/map/MapSkeleton.png");

		player = maze.getPlayer();
		enemies = maze.getEnemies();
		//menu.create();

		collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale(), maze.getEventListener());
		
		menu.create();
	}

	@Override
	public void update(float delta) 
	{
		eventHandler.checkUserInputs();
		if (player.getAlive() && !player.getEscape() && !menu.getMenuState()) 
		{
			player.update();
			for (int i = 0; i < enemies.size(); i++)
				enemies.get(i).update();
			
			collision.verify();
			collision.verifyEnemy();
		}
		else
			menu.update();

		maze.getEventListener().updatePlaylist();
	}

	@Override
	public void interpolate(float alpha) 
	{

	}

	@Override
	public void render(Graphics g)
	{
		if(!menu.getMenuState())
		{
		// maze.PaintMaze(g);

		maze.displayUserMap(g, player);

		player.render(g);
		maze.displayItems(g);
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).render(g);

		maze.displayUserMapSecondLayer(g, player);
		
		//menu.render();
		}
		else
			menu.render();		
	}

	public void callMenu() 
	{
		resetGame();
		menu.call();
	}
	
	public void resetGame()
	{
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).reset();
		for (int j = 0; j < maze.getItems().size(); j++)
			maze.getItems().get(j).respawn();
		player.restart();
		maze.getEventListener().resetPlaylist();
	}
}
