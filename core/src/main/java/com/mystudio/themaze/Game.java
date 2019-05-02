package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Maze maze;
	private Player player;
	private Collision collision;
	private ArrayList<Enemy> enemies;
	
	@Override
    public void initialise() 
	{	
		mapTranslator map = new mapTranslator("map/m1SK.png");
		map.translate();
		
    	maze = new Maze(map);
    	
    	player = new Player(maze.getPlayerSpawnX(), maze.getPlayerSpawnY(), maze.getMapScale());
    	enemies = new ArrayList<Enemy>();
    	enemies.add(new Enemy(maze.getEnemySpawnX(0), maze.getEnemySpawnY(0), maze.getMapScale()));
    	enemies.add(new Enemy(maze.getEnemySpawnX(1), maze.getEnemySpawnX(1), maze.getMapScale()));
    	
    	maze.addItem(new Item(23, 27, maze.getMapScale(), "item/key.png"));
    	maze.addItem(new Item(23, 28, maze.getMapScale(), "item/sword.png"));
    	maze.addItem(new Item(23, 29, maze.getMapScale(), "item/potion.png"));
    	
    	collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale());
    }
    
    @Override
    public void update(float delta) 
    {
    	if(player.getAlive())
    	{
	    	player.update(maze);
	    	for(int i=0; i<enemies.size(); i++)
				enemies.get(i).update(maze);
	    	collision.verify();    
	    	collision.verifyEnemy();
    	}
    }
    
    @Override
    public void interpolate(float alpha) 
    {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		//maze.PaintMaze(g);
    	maze.paintTrueMap(g, "map/m1.png");
		player.render(g);
		//maze.paintTrueMap(g, "map/MapLevel1Layer2.png");
		maze.paintItems(g);
		for(int i=0; i<enemies.size(); i++)
			enemies.get(i).render(g);	

		if(!player.getAlive())
			maze.paintTrueMap(g, "map/GameOver.png");
    }	
}
