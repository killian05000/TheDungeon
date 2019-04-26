package com.mystudio.themaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";

	private Texture texture;
	private Maze maze;
	
	@Override
    public void initialise() {
    	texture = new Texture("mini2Dx.png");
    	maze = new Maze();
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
		maze.PaintMaze(g);
    }
	
}
