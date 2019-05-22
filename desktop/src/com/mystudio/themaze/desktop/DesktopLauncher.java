package com.mystudio.themaze.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import game.Game;

public class DesktopLauncher {
	
	public static void main(String [] args) 
	{
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Game.GAME_IDENTIFIER);
		cfg.title = "The Dungeon";
		cfg.width = 1024;
		cfg.height = 768;
		cfg.vSyncEnabled = true;
		
		new DesktopMini2DxGame(new Game(), cfg);
	}
}
