package menu;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu 
{
	private ArrayList<Button> buttons;
	private SpriteBatch batch;
	private boolean menuON;
	private boolean displayHelpON;
	private BitmapFont font;
	private String rules;
	
	public void create ()
	{
		batch = new SpriteBatch();
		buttons = new ArrayList<Button>();
		buttons.add(new Button(400,600,50,250, "play"));
		buttons.add(new Button(400,400,50,250, "help"));
		buttons.add(new Button(400,200,50,250, "credits"));
		font = new BitmapFont();
        font.setColor(Color.WHITE);
        displayHelpON=false;
		menuON=true;
		
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
		if(buttons.get(0).getIsClicked())
			menuON=false;
		if(buttons.get(1).getIsClicked())
			displayHelpON=true;
	}
	
	public void render()
	{
		batch.begin();
		keyPressed();
		if(!displayHelpON)
		{
			for(int i=0; i<buttons.size(); i++)
			{
			buttons.get(i).getSprite().draw(batch);
			buttons.get(i).getFont().draw(batch, buttons.get(i).getText(), buttons.get(i).getX()+buttons.get(i).getWidth()/2,
											buttons.get(i).getY()+buttons.get(i).getHeight()/2);
			}
		}
		else
			font.draw(batch, rules, 300, 500);
			
		batch.end();
	}
	
	public void keyPressed() 
	{
//		int screenW = Gdx.graphics.getWidth();
//      int screenH = Gdx.graphics.getHeight();
//      System.out.println(screenW+"/"+screenH);
		for(int i=0; i<buttons.size(); i++)
		{
			buttons.get(i).checkClick(Gdx.input.getX() , Gdx.graphics.getHeight()-Gdx.input.getY());
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			call();
	}
	
	public void call() 
	{
		for(int i=0; i<buttons.size(); i++)
		{
			buttons.get(i).setIsClicked(false);
		}
		displayHelpON=false;
		menuON=true;
	}
	
	public ArrayList<Button> getButtons()
	{
		return buttons;
	}
	
	public boolean getMenuON()
	{
		return menuON;
	}

}
