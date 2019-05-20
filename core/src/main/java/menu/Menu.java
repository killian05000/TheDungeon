package menu;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu 
{
	private ArrayList<Button> menuButtons;
	private ArrayList<Button> helpButtons;
	private SpriteBatch batch;
	private boolean menuON;
	private boolean displayHelpON;
	private boolean menuState;
	private BitmapFont font;
	private String rules;
	private Sprite menuSprite;
	private Sprite helpSprite;
	
	public void create ()
	{
		menuSprite = new Sprite(new Texture("menu/backgroundSprite.png"), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menuSprite.setPosition(0,0);
		helpSprite = new Sprite(new Texture("menu/helpSprite.png"), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		helpSprite.setPosition(0,0);
		
		batch = new SpriteBatch();
		menuButtons = new ArrayList<Button>();
		menuButtons.add(new Button(400,250, "menu/playButton"));
		menuButtons.add(new Button(400,150, "menu/helpButton"));
		menuButtons.add(new Button(400,50, "menu/quitButton"));
		
		helpButtons = new ArrayList<Button>();
		helpButtons.add(new Button(50,350, "menu/escapeButton"));
		
		font = new BitmapFont();
        font.setColor(Color.BLACK);
        displayHelpON=false;
		menuON=true;
		menuState=true;
		
		rules = "The player needs to collect 3 objects to open the donjon's door \n"
				+ "There is three gards garding this level, two minions pretty dumb,\n"
				+ "browsing the donjon randomly, and their boss who is smarter. \n"
				+ "The last will hunt you down. But don't worry you can kill those \n"
				+ "guys by throwing objects to their faces. Although if they collied, \n"
				+ "the object and the enemy will return to their default position \n"
				+ "Also take care to the traps, they could ruin your journey ! \n \n"
				+ "->Use the keyboard's arrow keys to move \n"
				+ "->Press A to throw an object (letal to enemies) \n"
				+ "->Press R to reset the game \n"
				+ "->Press M to come back to the menu";				
	}
	
	public void update()
	{
		if(menuButtons.get(0).getIsClicked())
		{
			menuState=false;
			resetClicks();
		}
		else if(menuButtons.get(1).getIsClicked())
		{
			menuON=false;
			displayHelpON=true;
			resetClicks();
		}
		else if(menuButtons.get(2).getIsClicked())
		{
			resetClicks();
			System.exit (1);
		}
		
		if(displayHelpON)
		{
			if(helpButtons.get(0).getIsClicked())
			{
				menuON=true;
				displayHelpON=false;
				resetClicks();
			}
		}
	}
	
	public void render()
	{
		batch.begin();

		keyPressed();
		if(menuON)
		{
			menuSprite.draw(batch);
			for(int i=0; i<menuButtons.size(); i++)
				menuButtons.get(i).getSprite().draw(batch);
		}
		else if(displayHelpON)
		{
			helpSprite.draw(batch);
			for(int i=0; i<menuButtons.size(); i++)
				helpButtons.get(0).getSprite().draw(batch);
			font.draw(batch, rules, 300, 500);
		}
			
		batch.end();
	}
	
	public void keyPressed() 
	{
//		int screenW = Gdx.graphics.getWidth();
//      int screenH = Gdx.graphics.getHeight();
//      System.out.println(screenW+"/"+screenH);
		for(int i=0; i<menuButtons.size(); i++)
		{
			menuButtons.get(i).checkClick(Gdx.input.getX() , Gdx.graphics.getHeight()-Gdx.input.getY());
		}
		
		for(int i=0; i<helpButtons.size(); i++)
		{
			helpButtons.get(i).checkClick(Gdx.input.getX() , Gdx.graphics.getHeight()-Gdx.input.getY());
		}
	}
	
	public void resetClicks()
	{
		for(int i=0; i<menuButtons.size(); i++)
			menuButtons.get(i).setIsClicked(false);
		
		for(int i=0; i<helpButtons.size(); i++)
			helpButtons.get(i).setIsClicked(false);
	}
	
	public void call() 
	{
		for(int i=0; i<menuButtons.size(); i++)
		{
			menuButtons.get(i).setIsClicked(false);
		}
		displayHelpON=false;
		menuON=true;
		menuState=true;
	}
	
	public ArrayList<Button> getmenuButtons()
	{
		return menuButtons;
	}
	
	public boolean getMenuON()
	{
		return menuON;
	}

	public boolean getMenuState() 
	{
		return menuState;
	}

}
