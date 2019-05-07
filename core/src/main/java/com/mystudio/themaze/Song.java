package com.mystudio.themaze;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Song {
	String path;
	
	public Song(String path) {
		this.path = path;
	}
	
	public void startWithoutLoop() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("../assets/song/"+path)));
			clip.start();
		} catch (Exception e) {
			e.getStackTrace();
		} 
	}
	
	public void startLoop() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("../assets/song/"+path)));
			
			clip.loop(LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.getStackTrace();
		} 
	}
	
	
	
}
