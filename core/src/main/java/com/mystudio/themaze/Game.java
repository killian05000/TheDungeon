package com.mystudio.themaze;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Maze maze;
	private Player player;
	
	@Override
    public void initialise() 
	{
    	maze = new Maze(5); // map scale
    	player = new Player();
    }
    
    @Override
    public void update(float delta) 
    {
    	player.update(maze);
    }
    
    @Override
    public void interpolate(float alpha) {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		maze.PaintMaze(g);
		player.render(g, maze.getMapScale());
    }
	
}
