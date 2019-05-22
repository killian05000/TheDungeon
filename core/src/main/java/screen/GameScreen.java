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

	@Override
	public void initialise() 
	{
		maze = MapTranslator.translate("/map/mapSkeleton.png");

		player = maze.getPlayer();
		
		eventHandler = new EventHandler(player);
		
		enemies = maze.getEnemies();

		collision = new Collision(maze.getItems(), player, enemies, maze.getMapScale(), maze.getEventListener());
	}
	
	@Override
	public void update() 
	{
		eventHandler.checkUserInputs();
		if (player.getAlive() && !player.getEscape()) 
		{
			player.checkMovements();
			for (int i = 0; i < enemies.size(); i++)
				enemies.get(i).update();
			
			collision.verify();
			collision.verifyEnemy();
		}
		maze.getEventListener().updatePlaylist();		
	}

	@Override
	public void render(Graphics g) 
	{
		maze.displayUserMap(g, player);

		player.render(g);
		maze.displayItems(g);
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).render(g);

		maze.displayUserMapSecondLayer(g, player);
	}
	
	@Override
	public void reset()
	{
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).reset();
		for (int j = 0; j < maze.getItems().size(); j++)
			maze.getItems().get(j).respawn();
		player.restart();
		maze.getEventListener().resetPlaylist();
	}	

}
