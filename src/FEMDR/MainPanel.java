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
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Michel Hummel
 *
 */
public class MainPanel {
	static JPanel draw;

    public static void main(String[] args) throws InterruptedException {
            JFrame frame = new KeyCacherFrame();
            frame.setIgnoreRepaint(true);
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            GraphicsDevice gd = gs[0];
            GraphicsConfiguration gc = gs[0].getDefaultConfiguration();
            Rectangle bounds = gc.getBounds();
            
            frame.setPreferredSize(new Dimension(bounds.width, bounds.height));
            draw = new Board();
            frame.add(draw);
            frame.pack();
            frame.setVisible(true);
            
            MainPanel me = new MainPanel();
            String strDiag = " FEMDR V"+Prop.getVersion()+" - "+Prop.getRevision()+"\n Open source (Free) EMDR tool\n Written by Michel Hummel\n To help Zéév Maoz and its patients";
            //default title and icon
            JOptionPane.showMessageDialog(frame,
            		strDiag);

            updateScreen updateThread = me.new updateScreen();
            updateThread.start();
		    draw.updateUI();
		    

            if (gd.isFullScreenSupported()) {
            	gd.setFullScreenWindow(frame);
            } else {
            	frame.setVisible(true);
            }

//            Speed.Pause(frame);


    }
    

	private class updateScreen extends Thread {
		
		public void run() {
			int timems = 1000/120;
			while(true) {
				
			    draw.updateUI();
			    try {
					Thread.sleep(timems);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}

