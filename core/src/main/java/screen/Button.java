package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button 
{
	private int x;
	private int y;
	private String text;
	private Sprite buttonSprite;
	private Sprite buttonClickedSprite;
	private Sprite buttonUnclickedSprite;
	private boolean isClicked;
	private boolean isHovered;
	
	/**
	 * Create a Button
	 * @param x : position X of the Button
	 * @param y : position Y of the Button
	 * @param filePath
	 */
	public Button(int x, int y, String filePath)
	{
		this.x = x;
		this.y = y;
        
        buttonClickedSprite = new Sprite(new Texture(filePath+"Clicked.png"));
		buttonUnclickedSprite = new Sprite(new Texture(filePath+".png"));
		buttonSprite = new Sprite(buttonUnclickedSprite);
		buttonSprite.setPosition(x,y);
	}
	
	/**
	 * Check if a click has be done onto the button hitbox
	 * @param x : x mouse pointer
	 * @param y : y mouse pointer
	 */
	public void checkClick(int x, int y)
	{
		if((x > this.x && x < this.x + this.buttonSprite.getWidth()) && (y > this.y && y < this.y + this.buttonSprite.getHeight()))
		{
			if(!isHovered)
			{
				buttonSprite.set(buttonClickedSprite);
				buttonSprite.setPosition(this.x,this.y);
				isHovered = !isHovered;
			}
			if(Gdx.input.isTouched())
				isClicked=!isClicked;
		}
		else
		{
			if(isHovered)
			{
				buttonSprite.set(buttonUnclickedSprite);
				buttonSprite.setPosition(this.x,this.y);;
				isHovered = !isHovered;
			}
		}
	}
	
	public Sprite getSprite()
	{
		return buttonSprite;
	}
	
	public String getText()
	{
		return text;
	}

	public float getX() 
	{
		return x;
	}
	
	public float getY() 
	{
		return y;
	}
	
	public boolean getIsClicked()
	{
		return isClicked;
	}
	
	public void setIsClicked(boolean b)
	{
		isClicked = b;
	}

}
