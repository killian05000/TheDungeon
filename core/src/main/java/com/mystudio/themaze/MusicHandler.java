package com.mystudio.themaze;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicHandler {
	private ArrayList<Clip> sounds;
	
	/**
	 * Constructor loading the sounds
	 */
	public MusicHandler() {
		try 
		{
			Clip backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(AudioSystem.getAudioInputStream(new File("../assets/music/Pim Poy.wav")));

			Clip gameOver = AudioSystem.getClip();
			gameOver.open(AudioSystem.getAudioInputStream(new File("../assets/music/Game Over.wav")));
			
			Clip win = AudioSystem.getClip();
			win.open(AudioSystem.getAudioInputStream(new File("../assets/music/Win.wav")));
			
			Clip getItem = AudioSystem.getClip();
			getItem.open(AudioSystem.getAudioInputStream(new File("../assets/music/getItem.wav")));

			Clip throwItem = AudioSystem.getClip();
			throwItem.open(AudioSystem.getAudioInputStream(new File("../assets/music/throwItem.wav")));

			sounds = new ArrayList<Clip>();
			sounds.add(backgroundMusic);
			sounds.add(gameOver);
			sounds.add(win);
			sounds.add(getItem);
			sounds.add(throwItem);
		} 
		catch (Exception e) 
		{
			e.getStackTrace();
		} 
	}
	
	/**
	 * Start the game music
	 */
	public void startGameMusic()
	{
		sounds.get(1).stop();
		sounds.get(2).stop();
		sounds.get(0).loop(LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stop the game music
	 */
	public void stopGameMusic()
	{
		sounds.get(0).stop();
	}
	
	/**
	 * Game over sound
	 */
	public void gameOverSound()
	{
		sounds.get(0).stop();
		sounds.get(1).start();
	}
	
	/**
	 * Win sound
	 */
	public void gameWinSound()
	{
		sounds.get(0).stop();
		sounds.get(2).start();
	}
	
	/**
	 * Pick up item sound
	 */
	public void pickItemSound()
	{
		sounds.get(3).setFramePosition(0);
		sounds.get(3).start();		
	}
	
	/**
	 * Throw item sound
	 */
	public void throwItemSound()
	{
		sounds.get(4).setFramePosition(0);
		sounds.get(4).start();
	}
	
	/**
	 * Reset all the sounds frame position and pause them
	 */
	public void resetSounds()
	{
		for(int i=0; i<sounds.size(); i++)
		{
			sounds.get(i).stop();
			sounds.get(i).setFramePosition(0);
		}
	}
}