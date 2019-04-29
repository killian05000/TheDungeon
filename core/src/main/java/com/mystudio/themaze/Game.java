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
    	maze = new Maze();
    	player = new Player();
    }
    
    @Override
    public void update(float delta) 
    {
    	player.updateDirection();
    	
    	int newPosX;
    	int newPosY;
    	
    	switch(player.getDirection()) 
    	{
    		case 0:
    			newPosX= player.getPosX()-1;
    			if(newPosX>=0 && maze.getMatrix()[newPosX][player.getPosY()] == 0)
    				player.setPosX(newPosX);
    			break;
    		case 1:
    			newPosY = player.getPosY()+1;
    			if(newPosY<maze.getMatrix()[0].length && maze.getMatrix()[player.getPosX()][newPosY] == 0)
    				player.setPosY(newPosY);
    			break;
    		case 2:
    			newPosX = player.getPosX()+1;
    			if(newPosX<maze.getMatrix().length && maze.getMatrix()[newPosX][player.getPosY()] == 0) 
    				player.setPosX(newPosX);			
    			break;
    		case 3:
    			newPosY = player.getPosY()-1;
    			if(newPosY>=0 && maze.getMatrix()[player.getPosX()][newPosY] == 0) 
    				player.setPosY(newPosY);
    			break;	
    	}
    }
    
    @Override
    public void interpolate(float alpha) {
    	
    }
    
    @Override
    public void render(Graphics g) 
    {
		maze.PaintMaze(g);
		player.render(g);
    }
	
}
