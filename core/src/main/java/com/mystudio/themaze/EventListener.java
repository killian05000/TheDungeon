package com.mystudio.themaze;

public class EventListener 
{
	private boolean gameMusicON;
	private boolean gameOverSoundON;
	private boolean victorySoundON;
	private boolean pickingObjectSoundON;
	private boolean throwingObjectSoundON;
	private boolean teleporterSoundON;
	private boolean doorOpenON;
	private boolean doorClosedON;
	private MusicHandler music;

	/**
	 * Constructor puts all sound events to false
	 */
	public EventListener() 
	{
		music = new MusicHandler();
		gameMusicON = true;
		gameOverSoundON = false;
		victorySoundON = false;
		pickingObjectSoundON = false;
		throwingObjectSoundON = false;
		teleporterSoundON = false;
		doorOpenON = false;
		doorOpenON = false;
	}

	/**
	 * Check if the sound events have changed and play sounds accordingly
	 */
	public void updatePlaylist() 
	{
		if (gameMusicON) 
		{
			music.startGameMusic();
			gameMusicON = false;
		}

		if (gameOverSoundON) 
		{
			music.gameOverSound();
			gameOverSoundON = false;
			gameMusicON = false;
		} 
		else if (victorySoundON) 
		{
			music.gameWinSound();
			victorySoundON = false;
			gameMusicON = false;
		}

		if (pickingObjectSoundON) 
		{
			music.pickItemSound();
			pickingObjectSoundON = false;
		}

		if (throwingObjectSoundON) 
		{
			music.throwItemSound();
			throwingObjectSoundON = false;
		}

		if (teleporterSoundON) 
		{
			music.teleporterSound();
			teleporterSoundON = false;
		}

		if (doorOpenON) 
		{
			music.doorOpenSound();
			doorOpenON = false;
		}

		if (doorClosedON) 
		{
			music.doorClosedSound();
			doorClosedON = false;
		}
	}

	/**
	 * Reset the sounds frame position and sets all sound events to false except the
	 * game music
	 */
	public void resetPlaylist() 
	{
		music.resetSounds();
		gameMusicON = true;
		gameOverSoundON = false;
		victorySoundON = false;
		pickingObjectSoundON = false;
	}

	public void setGameMusicON(boolean b) 
	{
		gameMusicON = b;
	}

	public void setGameOverSoundON(boolean b) 
	{
		gameOverSoundON = b;
	}

	public void setVictorySoundON(boolean b) 
	{
		victorySoundON = b;
	}

	public void setPickingObjectSoundON(boolean b) 
	{
		pickingObjectSoundON = b;
	}

	public void setThrowingObjectSoundON(boolean b) 
	{
		throwingObjectSoundON = b;
	}

	public void setDoorOpenSoundON(boolean b) 
	{
		doorOpenON = b;
	}

	public void setDoorClosedSoundON(boolean b) 
	{
		doorClosedON = b;
	}

	public void setTeleporterSoundON(boolean b) 
	{
		teleporterSoundON = b;
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
