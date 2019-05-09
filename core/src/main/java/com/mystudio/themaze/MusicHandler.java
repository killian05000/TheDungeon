package com.mystudio.themaze;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicHandler {
	ArrayList<Clip> sounds;
	
	public MusicHandler() {
		try 
		{
			System.out.println("1");

			Clip backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(AudioSystem.getAudioInputStream(new File("../assets/music/Pim Poy.wav")));
			
			System.out.println("2");

			
			Clip gameOver = AudioSystem.getClip();
			gameOver.open(AudioSystem.getAudioInputStream(new File("../assets/music/Game Over.wav")));
			
			System.out.println("3");

			
			Clip win = AudioSystem.getClip();
			win.open(AudioSystem.getAudioInputStream(new File("../assets/music/Win.wav")));
			
			System.out.println("4");
			
			Clip getItem = AudioSystem.getClip();
			getItem.open(AudioSystem.getAudioInputStream(new File("../assets/music/getItem.wav")));
			
			System.out.println("5");
			
			Clip throwItem = AudioSystem.getClip();
			throwItem.open(AudioSystem.getAudioInputStream(new File("../assets/music/throwItem.wav")));
			
			System.out.println("6");			
			
			sounds = new ArrayList<Clip>();
			sounds.add(backgroundMusic);
			sounds.add(gameOver);
			sounds.add(win);
			sounds.add(getItem);
			sounds.add(throwItem);
			System.out.println("sounds DONE");
		} catch (Exception e) 
		{
			e.getStackTrace();
		} 
	}
	
	public void startGameMusic()
	{
		sounds.get(1).stop();
		sounds.get(2).stop();
		sounds.get(0).loop(LOOP_CONTINUOUSLY);
	}
	
	public void stopGameMusic()
	{
		sounds.get(0).stop();
	}
	
	public void gameOver()
	{
		sounds.get(0).stop();
		sounds.get(1).start();
	}
	
	public void gameWin()
	{
		sounds.get(0).stop();
		sounds.get(2).start();
	}
	
	public void getItem()
	{
		sounds.get(3).setFramePosition(0);
		sounds.get(3).start();		
	}
	
	public void throwItem()
	{
		sounds.get(4).setFramePosition(0);
		sounds.get(4).start();
	}
}
