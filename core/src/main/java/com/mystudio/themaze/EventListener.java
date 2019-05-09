package com.mystudio.themaze;

public class EventListener 
{
	private boolean gameMusicON;
	private boolean gameOverSoundON;
	private boolean victorySoundON;
	private boolean pickingObjectSoundON;
	private boolean throwingObjectSoundON;
	private MusicHandler music;

	public EventListener()
	{
		music = new MusicHandler();
		gameMusicON=true;
		gameOverSoundON=false;
		victorySoundON=false;
		pickingObjectSoundON=false;
		throwingObjectSoundON=false;
	}
	
	public void updatePlaylist()
	{
		if(gameMusicON)
		{
			music.startGameMusic();
			System.out.println("gameMusicOn");
			gameMusicON=false;
		}
		
		if(gameOverSoundON)
		{
			System.out.println("gameOverSoundON");
			music.gameOver();
			gameOverSoundON=false;
			gameMusicON=false;
		}
		else if(victorySoundON)
		{
			System.out.println("victorySoundON");
			music.gameWin();
			victorySoundON=false;
			gameMusicON=false;
		}
		
		if(pickingObjectSoundON)
		{
			System.out.println("pickItemSoundON");
			music.getItem();
			pickingObjectSoundON=false;
		}
		
		if(throwingObjectSoundON)
		{
			System.out.println("throwItemSoundON");
			music.throwItem();
			throwingObjectSoundON=false;
		}
	}
	
	public void resetPlaylist()
	{
		music.stopGameMusic();
		music = new MusicHandler();
		gameMusicON=true;
		gameOverSoundON=false;
		victorySoundON=false;
		pickingObjectSoundON=false;
	}
	
	public void setGameMusicON(boolean b)
	{
		gameMusicON=b;
	}
	
	public void setGameOverSoundON(boolean b)
	{
		gameOverSoundON=b;
	}
	
	public void setVictorySoundON(boolean b)
	{
		victorySoundON=b;
	}
	
	public void setPickingObjectSoundON(boolean b)
	{
		pickingObjectSoundON = b;
	}
	
	public void setThrowingObjectSoundON(boolean b)
	{
		throwingObjectSoundON = b;
	}
	
	public boolean getGameMusicON()
	{
		return gameMusicON;
	}
	
	public boolean getGameOverSoundON()
	{
		return gameOverSoundON;
	}
	
	public boolean getVictorySoundON()
	{
		return victorySoundON;
	}
	
	public boolean getPickingObjectSoundON()
	{
		return pickingObjectSoundON;
	}
	
	public boolean getThrowingObjectSoundON()
	{
		return throwingObjectSoundON;
	}
}
