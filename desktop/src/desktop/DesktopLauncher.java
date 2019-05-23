package desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import game.Main;

public class DesktopLauncher {
	
	public static void main(String [] args) 
	{
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Main.GAME_IDENTIFIER);
		cfg.title = "The Dungeon";
		cfg.width = 1024;
		cfg.height = 768;
		cfg.vSyncEnabled = true;
		
		new DesktopMini2DxGame(new Main(), cfg);
	}
}
