package com.mystudio.themaze;

import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Game extends BasicGame 
{
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";
	private Maze maze;
	private Player player;
	private Collision collision;
	private ArrayList<Enemy> enemies;


	@Override
	public void initialise() 
	{
		MapTranslator map = new MapTranslator("MapSkeleton.png");
		map.translate();
		
    	maze = new Maze(map);
    	
    	player = maze.getPlayer();
    	enemies = maze.getEnemies();

    	collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale(), maze.getEventListener());
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
				enemies.get(i).reset();
			for(int j=0; j<maze.getItems().size(); j++)
				maze.getItems().get(j).respawn();
			player.restart();
			maze.getEventListener().resetPlaylist();
		}
    }
}
