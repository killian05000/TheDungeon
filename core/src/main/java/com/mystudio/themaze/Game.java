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
	private Maze maze;
	private Player player;
	private Collision collision;
	private ArrayList<Enemy> enemies;
	
	//MenuMethode1 menu = new MenuMethode1();
	Menu menu = new Menu();
	
	@Override
	public void initialise() 
	{
		MapTranslator map = new MapTranslator("/map/MapSkeleton.png");
		map.translate();

		maze = new Maze(map);

		player = maze.getPlayer();
		enemies = maze.getEnemies();
		//menu.create();

		collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale(), maze.getEventListener());
		
		menu.create();
	}

	@Override
	public void update(float delta) 
	{
		if (player.getAlive() && !player.getEscape() && !menu.getMenuON()) 
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
		keyPressed();
	}

	@Override
	public void interpolate(float alpha) 
	{

	}

	@Override
	public void render(Graphics g)
	{
		if(!menu.getMenuON())
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

	public void keyPressed() 
	{
		if (Gdx.input.isKeyJustPressed(Keys.R)) 
			resetGame();
		if (Gdx.input.isKeyJustPressed(Keys.M)) 
		{
			resetGame();
			menu.call();
		}
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
