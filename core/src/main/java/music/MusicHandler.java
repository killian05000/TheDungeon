package com.mystudio.themaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicHandler 
{
	Music gameMusic;
	Sound gameOver;
	Sound win;
	Sound gettingItem;
	Sound throwingItem;
	Sound doorOpened;
	Sound doorClosed;
	Sound teleported;
	
	/**
	 * Constructor loading the sounds
	 */
	public MusicHandler() 
	{
		try 
		{
			gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/gameMusic.wav"));
			gameMusic.setLooping(true);
			gameOver = Gdx.audio.newSound(Gdx.files.internal("music/gameOver.wav"));
			win = Gdx.audio.newSound(Gdx.files.internal("music/win.wav"));
			gettingItem = Gdx.audio.newSound(Gdx.files.internal("music/getItem.wav"));
			throwingItem = Gdx.audio.newSound(Gdx.files.internal("music/throwItem.wav"));
			doorOpened = Gdx.audio.newSound(Gdx.files.internal("music/doorOpen.wav"));
			doorClosed = Gdx.audio.newSound(Gdx.files.internal("music/doorClosed.wav"));
			teleported = Gdx.audio.newSound(Gdx.files.internal("music/teleporter.wav"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * Start the game music
	 */
	public void startGameMusic()
	{
		win.stop();
		gettingItem.stop();
		gameMusic.play();
	}
	
	/**
	 * Stop the game music
	 */
	public void stopGameMusic()
	{
		gameMusic.stop();
	}
	
	/**
	 * Game over sound
	 */
	public void gameOverSound()
	{
		gameMusic.stop();
		gameOver.play();
	}
	
	/**
	 * Win sound
	 */
	public void gameWinSound()
	{
		gameMusic.stop();
		win.play();
	}
	
	/**
	 * Pick up item sound
	 */
	public void pickItemSound()
	{
		gettingItem.play();		
	}
	
	/**
	 * Throw item sound
	 */
	public void throwItemSound()
	{
		throwingItem.play();
	}
	
	public void doorOpenSound()
	{
		doorOpened.play();
	}
	
	public void doorClosedSound()
	{
		doorClosed.play();
	}
	
	public void teleporterSound()
	{
		teleported.play();
	}
	
	/**
	 * Reset all the sounds frame position and pause them
	 */
	public void resetSounds()
	{
		gameMusic.stop();
		gameOver.stop();
		win.stop();
		gettingItem.stop();
		throwingItem.stop();
		doorOpened.stop();
		doorClosed.stop();
		teleported.stop();
	}
}
