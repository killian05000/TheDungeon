package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button 
{
	private int x;
	private int y;
	private String text;
	private BitmapFont font;
	private Sprite buttonSprite;
	private Sprite buttonClickedSprite;
	private Sprite buttonUnclickedSprite;
	private boolean isClicked;
	private boolean isHovered;
	
	public Button(int x, int y, String filePath)
	{
		this.x = x;
		this.y = y;
		font = new BitmapFont();
        font.setColor(Color.DARK_GRAY);
        
        buttonClickedSprite = new Sprite(new Texture(filePath+"Clicked.png"));
		buttonUnclickedSprite = new Sprite(new Texture(filePath+".png"));
		buttonSprite = new Sprite(buttonUnclickedSprite);
		buttonSprite.setPosition(x,y);
	}
	
	public void checkClick(int x, int y)
	{
		/*
		System.out.println("x : "+x);
		System.out.println("y : "+y);
		
		System.out.println("button x : "+this.x);
		System.out.println("button y : "+this.y);
		System.out.println("button x + widthy : "+(this.x+this.buttonSprite.getWidth()));
		System.out.println("button y + height : "+(this.y+this.buttonSprite.getHeight()));*/
		
		
		if((x > this.x && x < this.x+this.buttonSprite.getWidth()) && (y > this.y && y < this.y+this.buttonSprite.getHeight()))
		{
			

			if(!isHovered)
			{
				System.out.println("DESSUS");
				buttonSprite.set(buttonClickedSprite);
				buttonSprite.setPosition(this.x,this.y);
				isHovered = !isHovered;
			}
			
			
			if(Gdx.input.isTouched())
			{
				isClicked=!isClicked;
			}
		}
		else
		{
			
			if(isHovered)
			{
				System.out.println("PAS DESSUS");
				buttonSprite.set(buttonUnclickedSprite);
				buttonSprite.setPosition(this.x,this.y);;
				isHovered = !isHovered;
			}
		}
		
		//System.out.println(isHovered);
	}
	
	public Sprite getSprite()
	{
		return buttonSprite;
	}
	
	public BitmapFont getFont()
	{
		return font;
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
