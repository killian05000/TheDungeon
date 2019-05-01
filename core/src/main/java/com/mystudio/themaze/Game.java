package com.mystudio.themaze;

import java.io.IOException;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Maze maze;
	private Player player;
	private Collision collision;
	Enemy enemy1;
	Enemy enemy2;
	
	@Override
    public void initialise() 
	{
		mapTranslator map = new mapTranslator("map/mapLevel1Skeleton.png");
		map.translate();
		
    	maze = new Maze(map, player);
    	
    	player = new Player(maze.getPlayerSpawnX(), maze.getPlayerSpawnY(), maze.getMapScale());
    	enemy1 = new Enemy(maze.getEnemySpawnX(0), maze.getEnemySpawnY(0), maze.getMapScale());
    	enemy2 = new Enemy(maze.getEnemySpawnX(1), maze.getEnemySpawnX(1), maze.getMapScale());
    	
    	maze.addItem(new Item(22, 27, maze.getMapScale(), "item/key.png"));
    	maze.addItem(new Item(22, 28, maze.getMapScale(), "item/sword.png"));
    	maze.addItem(new Item(22, 29, maze.getMapScale(), "item/potion.png"));
    	
    	collision = new Collision(maze.getItems(), player, maze.getMapScale());
    }
    
    @Override
    public void update(float delta) 
    {
    	player.update(maze);
    	enemy1.update(maze);
    	enemy2.update(maze);
    	collision.verify();    	
    }
    
    @Override
    public void interpolate(float alpha) 
    {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		maze.PaintMaze(g);
		maze.paintTrueMap(g, "map/MapLevel1.png");
		player.render(g);
		maze.paintItems(g);
		enemy1.render(g);	
		enemy2.render(g);
    }	
}
