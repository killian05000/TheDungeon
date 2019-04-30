package com.mystudio.themaze;

import java.io.IOException;
import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Maze maze;
	private Player player;

	private Collision collision;
	Enemy enemy;
	
	@Override
    public void initialise() 
	{
		mapTranslator map = null;
		try {
			map = new mapTranslator("MapTest.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.translate();
		map.displayMatrix();
    	maze = new Maze(1, map.getMatrix()); // map scale
    	System.out.println(maze.getPlayerSpawnX()+"/"+maze.getPlayerSpawnY());
    	player = new Player(maze.getPlayerSpawnX(),maze.getPlayerSpawnY(),maze.getMapScale());
    	enemy = new Enemy(maze.getMapScale());
    	maze.addItem(new Item(10, 2, maze.getMapScale(), "key.png"));
    	maze.addItem(new Item(20, 28, maze.getMapScale(), "key.png"));
    	collision = new Collision(maze.getItems(), player, maze.getMapScale());

    }
    
    @Override
    public void update(float delta) 
    {
    	player.update(maze);
    	enemy.update(maze);
    	collision.verify();
    	
    }
    
    @Override
    public void interpolate(float alpha) {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		maze.PaintMaze(g);
		maze.paintTrueMap(g, "MapTest.png");
		player.render(g);
		maze.paintItemes(g);
		enemy.render(g);
		
		
    }
	
}
