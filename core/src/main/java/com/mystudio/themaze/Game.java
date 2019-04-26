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
	private Player player;
	
	@Override
    public void initialise() {
    	texture = new Texture("mini2Dx.png");
    	maze = new Maze();
    	player = new Player();
    }
    
    @Override
    public void update(float delta) 
    {
    	player.update();
    }
    
    @Override
    public void interpolate(float alpha) {
    	player.interpolate(1);
    }
    
    @Override
    public void render(Graphics g) 
	{
		g.drawTexture(texture, 0f, 0f);
		player.render(g);
		maze.PaintMaze(g);
    }
	
}
