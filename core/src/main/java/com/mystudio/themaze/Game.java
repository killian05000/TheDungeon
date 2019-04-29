package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Maze maze;
	private Player player;
	private ArrayList<Item> Items;
	
	@Override
    public void initialise() 
	{
		Items = new ArrayList<Item>();
    	maze = new Maze(3); // map scale
    	player = new Player(maze.getMapScale());
    	Items.add(new Item(1,9,"key.png"));

    }
    
    @Override
    public void update(float delta) 
    {
    	player.update(maze);
    	Items.get(0).update();
    }
    
    @Override
    public void interpolate(float alpha) {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		maze.PaintMaze(g);
		player.render(g);
		Items.get(0).render(g, maze.getMapScale());
    }
	
}
