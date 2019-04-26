package com.mystudio.themaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Texture texture;
	int [][] maze = 
	{
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 0, 0, 0, 0, 1, 1, 0, 0 },
		{ 1, 1, 0, 1, 0, 1, 1, 0, 0, 1 },
		{ 1, 0, 0, 1, 0, 0, 1, 0, 1, 1 },
		{ 1, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
		{ 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 },
		{ 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
		{ 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
	  };
	
	@Override
    public void initialise() {
    	texture = new Texture("mini2Dx.png");
    }
    
    @Override
    public void update(float delta) {
    
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) 
	{
		g.drawTexture(texture, 0f, 0f);
		PaintMaze(g);
    }
	
	
	    public void PaintMaze(Graphics g) {
    	
    	int TAILLE =50;
	    for (int i=0; i<maze.length; i++) {
	      for (int j=0; j<maze.length; j++) {
	        switch(maze[i][j]) {
	        case 0: 
	          g.setColor(Color.WHITE);
	          g.fillRect(j*TAILLE,i*TAILLE,TAILLE,TAILLE);
	          break;
	        case 1: 
	          g.setColor(Color.BLACK);
	          g.fillRect(j*TAILLE,i*TAILLE,TAILLE,TAILLE);
	          break;
	        }
	      }
	    }
    }
}
