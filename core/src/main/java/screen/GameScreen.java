package screen;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import enemy.Enemy;
import game.Collision;
import game.EventHandler;
import game.Player;
import maze.MapTranslator;
import maze.Maze;

public class GameScreen implements Screen
{
	private EventHandler eventHandler;
	private Maze maze;
	private Player player;
	private Collision collision;
	private ArrayList<Enemy> enemies;

	/**
	 * Initializes all the game components 
	 */
	@Override
	public void initialise() 
	{
		maze = MapTranslator.translate("/map/mapSkeleton.png");

		player = maze.getPlayer();
		
		eventHandler = new EventHandler(player);
		
		enemies = maze.getEnemies();

		collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale(), maze.getMusicPlayer());
	}
	
	/**
	 * Update all the game components while the player is alive
	 */
	@Override
	public void update() 
	{
		if (player.getAlive() && !player.getEscape()) 
		{
			eventHandler.checkUserInputs();
			player.checkMovements();
			for (int i = 0; i < enemies.size(); i++)
				enemies.get(i).update();
			
			collision.checkCollisions();
		}
		maze.getMusicPlayer().updatePlaylist();		
	}

	/**
	 * Render all the game components
	 */
	@Override
	public void render(Graphics g) 
	{
		maze.displayUserMap(g, player);

		player.render(g);
		maze.renderItems(g);
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).render(g);

		maze.displayUserMapSecondLayer(g);
		maze.displayEndGameScreen(g);
	}
	
	/**
	 * Reset all the game components
	 */
	@Override
	public void reset()
	{
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).reset();
		for (int j = 0; j < maze.getItems().size(); j++)
			maze.getItems().get(j).respawn();
		player.restart();
		maze.getMusicPlayer().resetPlaylist();
	}	

}
