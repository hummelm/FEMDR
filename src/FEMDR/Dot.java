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
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Michel Hummel
 *
 */
public class Dot {

	// Radius of the dot
    private int radius = 20;

	// Center of the dot
    private Point center = new Point(50, 50);
    
    // x, y resolution
    Rectangle bounds;
    
    // Max X
    int xmax =  0;
    
    boolean toBeep = true;
    // Beep X% before to hit the border
    int beepAtPercOfWidth = 25;
    
	// Will be init in contructor
	int beepPos;
	
	//sound to play
	MP3 audio;
    
    

	/*
	 * return center
	 */
    public Point getCenter() {
        return center;
    }
    
	/*
	 * update the position of the dot
	 * beep at the request position 
	 */
    public void update() {
    	center.x += Speed.getStep();
    	
    	//BEEP ON CENTER
    	//*****************************************************
    	/*
    	if (toBeep) {
    		// on augmente
    		if (Speed.getStep()>0) {
    			if (center.x >= bounds.width/2) {
    				new MP3().play();
    	    		toBeep = false;
    			}
   			// on repart
    		}else{
    			if (center.x <= bounds.width/2) {
    				new MP3().play();
    	    		toBeep = false;
    			}    			
    		}
    	}
    	 */
    	//**********************************************************
    	// need to beep again
    	int step = Speed.getStep();
    	if(toBeep) {
    		// go on right 
    		if (center.x >= (xmax-beepPos) && step > 0) {
    			// Beep and wait for new ask
    			toBeep = false;
    	    	new MP3().playRight();
    		}
    		// go on left
    		if (center.x <= 0+beepPos && step < 0) {
    			// Beep and wait for new ask
    			toBeep = false;
    	    	new MP3().playLeft();
    		}
    	}


    	if (center.x >= xmax) {
    		// need to beep again
    		toBeep = true;
    		
    		// can go after xmax
    		center.x = xmax;
    		Speed.UpdateStep(-Speed.getStep());
    	}
    	
    	if (center.x <= 0) {
    		// need to beep again
    		toBeep = true;
    		
    		//can go before 0
    		center.x = 0;
    		Speed.UpdateStep(-Speed.getStep());
    	}
    	
    	
    }


    /*
     * return the radius
     */
    public int getRadius() {
        return radius;
    }
    
    /*
     * Constructor
     */
    public Dot() {
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsConfiguration gc = gs[0].getDefaultConfiguration();
        bounds = gc.getBounds();
        xmax = bounds.width;
        center.x = 0;
        center.y = bounds.height/2;

    	// beep beepAtPercOfWidth% before the edge
    	beepPos = xmax*beepAtPercOfWidth/100;
        // force MP3 load
    	new MP3().play();
    	
    }
}