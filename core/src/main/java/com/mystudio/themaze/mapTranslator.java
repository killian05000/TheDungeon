package com.mystudio.themaze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mapTranslator 
{
	private BufferedImage map;
	private int tileSize;
	
	public mapTranslator(String path) throws IOException
	{
		System.out.println(System.getProperties().get("user.dir"));

		map = ImageIO.read(new File("../assets/"+path));
		tileSize = 30;
	}
	
	public int[][] translate()
	{
		//System.out.println(map.getHeight());
		//System.out.println(map.getWidth());
		//System.out.println(tileSize);

		for(int x=0; x<map.getHeight(); x+=tileSize)
		{
			for(int y=0; y<map.getWidth(); y+=tileSize)
			{
				//int pixel = map.getRGB(x,y);
				int[] pixel = map.getRGB(x,y, tileSize, tileSize, null, 0, 30);
				System.out.println("["+x+","+y+"] : "+pixel);
			}
		}

		return null;
		
	}
}
