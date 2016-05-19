package EMDR;

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
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javazoom.jl.player.Player;


public class MP3 {
    private String filename;
    private Player player;
    
    static public String goTo = "bip4.mp3";

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
    }
    
    public MP3() {
        this.filename = goTo;
        try {
        	MP3.class.getClassLoader();
        	ClassLoader cl = MP3.class.getClassLoader();
        	InputStream fis = MP3.class.getClassLoader().getResourceAsStream(goTo);
 //        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            FileInputStream fis     = new FileInputStream(filename);

        	BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
    }

    public void close() { if (player != null) player.close(); }

    // play the MP3 file to the sound card
    public void play() {
        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }
}
