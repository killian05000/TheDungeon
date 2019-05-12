package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";
	private Maze maze;
	private Player player;
	private Collision collision;
	private ArrayList<Enemy> enemies;

	private EventListener eventListener;


	@Override
    public void initialise() 
	{	
		mapTranslator map = new mapTranslator("MapSkeleton.png");
		map.translate();
		
    	maze = new Maze(map);
    	eventListener = new EventListener();
    	
    	player = new Player(maze.getPlayerSpawnX(), maze.getPlayerSpawnY(), maze.getMapScale(), maze, eventListener);
    	enemies = new ArrayList<Enemy>();
    	enemies.add(new Enemy(maze.getEnemySpawnX(0), maze.getEnemySpawnY(0), maze.getMapScale(), maze, player, 4, 1));
    	enemies.add(new Enemy(maze.getEnemySpawnX(1), maze.getEnemySpawnY(1), maze.getMapScale(), maze, player, 2, 0));
    	enemies.add(new Enemy(maze.getEnemySpawnX(2), maze.getEnemySpawnY(2), maze.getMapScale(), maze, player, 2, 0));
    	
//    	maze.addItem(new Item(23, 27, maze.getMapScale(), "item/key.png", maze));
//    	maze.addItem(new Item(23, 28, maze.getMapScale(), "item/sword.png", maze));
//    	maze.addItem(new Item(23, 29, maze.getMapScale(), "item/potion.png", maze));
    	
    	maze.addItem(new Item(6, 0, maze.getMapScale(), "item/key.png", maze));
    	maze.addItem(new Item(6, 31, maze.getMapScale(), "item/sword.png", maze));
    	maze.addItem(new Item(21, 3, maze.getMapScale(), "item/potion.png", maze));

    	
    	collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale(), eventListener);
    }
    
    @Override
    public void update(float delta) 
    {
    	if(player.getAlive() && !player.getEscape())
    	{
	    	player.update();
	    	for(int i=0; i<enemies.size(); i++)
	    		enemies.get(i).update();
	    	collision.verify();    
	    	collision.verifyEnemy();
    	}
    	
    	eventListener.updatePlaylist();
    	keyPressed();
    }
    
    @Override
    public void interpolate(float alpha) 
    {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		//maze.PaintMaze(g);
  
		maze.displayUserMap(g, player);
    	
		player.render(g);
		maze.displayItems(g);
		for(int i=0; i<enemies.size(); i++)
				enemies.get(i).render(g);
		
		maze.displayUserMapSecondLayer(g, player);			
    }	
    
    public void keyPressed()
    {
    	if(Gdx.input.isKeyJustPressed(Keys.R)) 
		{
			for(int i=0; i<enemies.size(); i++)
				enemies.get(i).respawn();
			for(int j=0; j<maze.getItems().size(); j++)
				maze.getItems().get(j).respawn();
			player.restart();
			eventListener.resetPlaylist();
		}
    }
}
