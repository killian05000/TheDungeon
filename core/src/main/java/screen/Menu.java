package screen;

import java.util.ArrayList;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import enums.ScreenType;

public class Menu implements Screen 
{
	private ArrayList<Button> menuButtons;
	private ArrayList<Button> helpButtons;
	private SpriteBatch batch;
	private boolean menuON;
	private boolean helpON;
	private Sprite menuSprite;
	private Sprite helpSprite;
	
	private ScreenHandler screenHandler;

	/**
	 * Create a Menu screen
	 * @param screenHandler : screenHandler instance
	 */
	public Menu(ScreenHandler screenHandler)
	{
		this.screenHandler = screenHandler;
	}
	
	/**
	 * Initialize all the menu components
	 */
	@Override
	public void initialise()
	{
		menuSprite = new Sprite(new Texture("menu/backgroundSprite.png"), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menuSprite.setPosition(0, 0);
		helpSprite = new Sprite(new Texture("menu/helpSprite.png"), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		helpSprite.setPosition(0, 0);
		
		batch = new SpriteBatch();
		menuButtons = new ArrayList<Button>();
		menuButtons.add(new Button(400, 250, "menu/playButton"));
		menuButtons.add(new Button(400, 150, "menu/helpButton"));
		menuButtons.add(new Button(400, 50, "menu/quitButton"));
		
		helpButtons = new ArrayList<Button>();
		helpButtons.add(new Button(50, 350, "menu/escapeButton"));
		
        helpON = false;
		menuON = true;
	}
	
	/**
	 * Check if a button has been clicked, and act accordingly
	 */
	@Override
	public void update()
	{
		if(menuButtons.get(0).getIsClicked()) // Play button
		{
			resetClicks();
			screenHandler.setState(ScreenType.GAME);
		}
		else if(menuButtons.get(1).getIsClicked()) // Help button
		{
			resetClicks();
			menuON = false;
			helpON = true;
		}
		else if(menuButtons.get(2).getIsClicked()) // Quit button
		{
			resetClicks();
			System.exit (0);
		}
		
		if(helpON) // if we are on the help tab
		{
			if(helpButtons.get(0).getIsClicked()) // Return button
			{
				resetClicks();
				menuON = true;
				helpON = false;
			}
		}
	}
	
	/**
	 * Render the buttons and the background sprite of the current tab (menu or help)
	 */
	@Override
	public void render(Graphics g)
	{
		batch.begin();
		buttonPressed();
		
		if(menuON)
		{
			menuSprite.draw(batch);
			for(int i = 0; i < menuButtons.size(); i++)
				menuButtons.get(i).getSprite().draw(batch);
		}
		else if(helpON)
		{
			helpSprite.draw(batch);
			for(int i = 0; i < menuButtons.size(); i++)
				helpButtons.get(0).getSprite().draw(batch);
		}
			
		batch.end();
	}
	
	/**
	 * Check for the current tab if one of its buttons has been clicked
	 */
	private void buttonPressed() 
	{
		if(menuON)
		{
			for(int i = 0; i < menuButtons.size(); i++)
			{
				menuButtons.get(i).checkClick(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
			}
		}
		
		if(helpON)
		{
			for(int i = 0; i < helpButtons.size(); i++)
			{
				helpButtons.get(i).checkClick(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
			}
		}
	}
	
	/**
	 * Set for all the buttons their isClicked variable to false 
	 */
	private void resetClicks()
	{
		for(int i = 0; i < menuButtons.size(); i++)
			menuButtons.get(i).setIsClicked(false);
		
		for(int i = 0; i < helpButtons.size(); i++)
			helpButtons.get(i).setIsClicked(false);
	}
	
	/**
	 * Reset the clicks, and call the menu to be displayed
	 */
	@Override
	public void reset()
	{
		resetClicks();
		helpON = false;
		menuON = true;
	}
	
	public ArrayList<Button> getmenuButtons()
	{
		return menuButtons;
	}
	
	public boolean getMenuON()
	{
		return menuON;
	}
}