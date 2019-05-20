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
	private int height;
	private int width;
	private String text;
	private BitmapFont font;
	private Sprite sprite;
	private boolean isClicked;
	
	public Button(int x, int y, int height, int width, String text)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.text = text;
		font = new BitmapFont();
        font.setColor(Color.DARK_GRAY);
        
		sprite = new Sprite(new Texture("button/button2.png"), width, height);
		sprite.setPosition(x,y);
	}
	
	public void checkClick(int x, int y)
	{
		System.out.println("x : "+x);
		System.out.println("y : "+y);
		
		System.out.println("button x : "+this.x);
		System.out.println("button y : "+this.y);
		System.out.println("button x + widthy : "+(this.x+this.width));
		System.out.println("button y + height : "+(this.y+this.height));
		
		if((x > this.x && x < this.x+this.width) && (y > this.y && y < this.y+this.height))
		{
			if(Gdx.input.isTouched())
			{
				isClicked=!isClicked;
	
				if(isClicked)
				{
					sprite.set(new Sprite(new Texture("button/button2.png"), width, height));
					sprite.setPosition(this.x,this.y);
				}
				else
				{
					sprite.set(new Sprite(new Texture("button/button.png"), width, height));
					sprite.setPosition(this.x,this.y);;
				}
			}
		}
		System.out.println(isClicked);
	}
	
	public Sprite getSprite()
	{
		return sprite;
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

	public int getWidth() 
	{
		return width;
	}

	public int getHeight() 
	{
		return height;
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
