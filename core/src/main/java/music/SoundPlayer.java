package music;

public class SoundPlayer 
{
	private boolean gameMusicON;
	private boolean gameOverSoundON;
	private boolean victorySoundON;
	private boolean pickingObjectSoundON;
	private boolean throwingObjectSoundON;
	private boolean teleporterSoundON;
	private boolean doorOpenON;
	private boolean doorClosedON;
	private SoundHandler soundHandler;

	/**
	 * Constructor puts all sound events to false
	 */
	public SoundPlayer() 
	{
		soundHandler = new SoundHandler();
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
			soundHandler.startGameMusic();
			gameMusicON = false;
		}

		if (gameOverSoundON) 
		{
			soundHandler.gameOverSound();
			gameOverSoundON = false;
			gameMusicON = false;
		} 
		else if (victorySoundON) 
		{
			soundHandler.gameWinSound();
			victorySoundON = false;
			gameMusicON = false;
		}

		if (pickingObjectSoundON) 
		{
			soundHandler.pickItemSound();
			pickingObjectSoundON = false;
		}

		if (throwingObjectSoundON) 
		{
			soundHandler.throwItemSound();
			throwingObjectSoundON = false;
		}

		if (teleporterSoundON) 
		{
			soundHandler.teleporterSound();
			teleporterSoundON = false;
		}

		if (doorOpenON) 
		{
			soundHandler.doorOpenSound();
			doorOpenON = false;
		}

		if (doorClosedON) 
		{
			soundHandler.doorClosedSound();
			doorClosedON = false;
		}
	}

	/**
	 * Reset the sounds frame position and sets all sound events to false except the
	 * game music
	 */
	public void resetPlaylist() 
	{
		soundHandler.resetSounds();
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
