package FEMDR;

/*
 * FEMDR - Free (open source) Tool for EMDR
 * Copyright(C) Michel Hummel 
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * ******************************************************************** *
 * @author      Michel Hummel
 * @version     1
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.player.Player;

/**
 * 
 * @author Michel Hummel
 *
 */
public class MP3 {

//	private String filename = "default.mp3";
	private String filename = Prop.getBeep();

    private Player player;

	BufferedInputStream bis = new BufferedInputStream(
			MP3.class.getClassLoader().getResourceAsStream(
					filename
					));
	
	BufferedInputStream bisLeft = new BufferedInputStream(
			MP3.class.getClassLoader().getResourceAsStream(
					filename.substring(0, filename.length()-4).concat("-left.mp3")
					));
	
	BufferedInputStream bisRight = new BufferedInputStream(
			MP3.class.getClassLoader().getResourceAsStream(
					filename.substring(0, filename.length()-4).concat("-right.mp3")
					));

	
	
    public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		bis = new BufferedInputStream(
				MP3.class.getClassLoader().getResourceAsStream(
						filename
						));
	}
	
    public MP3() {
//    	System.out.println(filename.substring(0, filename.length()-4).concat("_left.mp3"));

    }

    public void close() { 
    	if (player != null) 
    		player.close();
    	}

    // play the MP3 file to the sound card
    public void play() {
    	// run in new thread to play in background
    	new Thread() {
    		public void run() {
    			try { 

    				player = new Player(bis);
    				player.play(); 
    				close();	
    			}
    			catch (Exception e) {
    				System.out.println(e); 
    				}
    		}
    	}.start();
    }
    
    // play the MP3 file to the sound card
    public void playLeft() {
    	// run in new thread to play in background
    	new Thread() {
    		public void run() {
    			try { 

    				player = new Player(bisLeft);
    				player.play(); 
    				close();	
    			}
    			catch (Exception e) {
    				System.out.println(e); 
    				}
    		}
    	}.start();
    }
    
    // play the MP3 file to the sound card
    public void playRight() {
    	// run in new thread to play in background
    	new Thread() {
    		public void run() {
    			try { 

    				player = new Player(bisRight);
    				player.play(); 
    				close();	
    			}
    			catch (Exception e) {
    				System.out.println(e); 
    				}
    		}
    	}.start();
    }
    
}

