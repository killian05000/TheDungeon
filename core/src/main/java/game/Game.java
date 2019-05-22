package game;


import java.util.ArrayList;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import enums.ScreenType;
import screen.GameScreen;
import screen.Menu;
import screen.Screen;
import screen.ScreenHandler;

public class Game extends BasicGame 
{
	public static final String GAME_IDENTIFIER = "com.mystudio.themaze";
	private ScreenType state = ScreenType.MENU;
	private ScreenHandler screenHandler;
	private ArrayList<Screen> screens;
	
	@Override
	public void initialise() 
	{
		screenHandler = new ScreenHandler();
		screens = new ArrayList<Screen>();
		screens.add(new Menu(screenHandler));
		screens.add(new GameScreen(screenHandler));
		
		for(int i = 0; i < screens.size(); i++)
			screens.get(i).initialise();
	}

	@Override
	public void update(float delta) 
	{
		keyInputs();
		state = screenHandler.getState();
		screens.get(state.ordinal()).update();		
	}

	@Override
	public void interpolate(float alpha) 
	{
		// Not used
	}

	@Override
	public void render(Graphics g)
	{
		screens.get(state.ordinal()).render(g);
	}
	
	public void keyInputs()
	{
		if(Gdx.input.isKeyJustPressed(Keys.R))
		{
			reset(ScreenType.GAME.ordinal());
		}
		else if(Gdx.input.isKeyJustPressed(Keys.M))
		{
			callMenu();
		}
	}
	
	public void reset(int screen) 
	{
		screens.get(screen).reset();
	}

	public void callMenu() 
	{
		reset(ScreenType.MENU.ordinal());
		reset(ScreenType.GAME.ordinal());
		screenHandler.setState(ScreenType.MENU);
	}
}
