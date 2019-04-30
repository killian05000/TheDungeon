package com.mystudio.themaze;

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
		
    	maze = new Maze(1); // map scale
    	player = new Player(maze.getDefaultPosYPlayer(),maze.getDefaultPosXPlayer(),maze.getMapScale());
    	enemy = new Enemy(maze.getMapScale());
    	maze.addItem(new Item(10, 5, maze.getMapScale(), "key.png"));
    	maze.addItem(new Item(11, 6, maze.getMapScale(), "key.png"));
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
		player.render(g);
		maze.paintItemes(g);
		enemy.render(g);
		
		
    }
	
}
