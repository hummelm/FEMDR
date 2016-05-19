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

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

/**
 * A advanced JFrame that closes when someone presses the ESC key.
 * 
 * @author djemili
 */
public class KeyCacherFrame extends JFrame
{
	
  
    
    public KeyCacherFrame()
    {
    	
    	

    	    GraphicsDevice gd =
    	            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    	    
    	    //FULLSCREEN MODE
    	    dispose();
    	    setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	    setUndecorated(true);
    	    
    	    
    	    
    	    
    	    
    	    
    	    setUndecorated(true);
//    	    
//    	    if (gd.isFullScreenSupported()) {
//    	        setUndecorated(true);
//    	        gd.setFullScreenWindow(this);
//    	    } else {
//    	        setVisible(true);
//    	    }
    	    final JFrame me = this;
    	
            // on SPACE pause the application
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Pause"); //$NON-NLS-1$
            getRootPane().getActionMap().put("Pause", new AbstractAction(){ //$NON-NLS-1$
                public void actionPerformed(ActionEvent e)
                {
                	Pause(me);
                }
            });
    	
            // on UP SpeedUp key close frame
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "SpeedUp"); //$NON-NLS-1$
            getRootPane().getActionMap().put("SpeedUp", new AbstractAction(){ //$NON-NLS-1$
                public void actionPerformed(ActionEvent e)
                {
                	SpeedUp();
                }
            });
            
            // on DOWN SpeedDown key close frame
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "SpeedDown"); //$NON-NLS-1$
            getRootPane().getActionMap().put("SpeedDown", new AbstractAction(){ //$NON-NLS-1$
                public void actionPerformed(ActionEvent e)
                {
                	SpeedDown();
                }
            });
    	    
    	    
    	
        // on ESC key close frame
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
            public void actionPerformed(ActionEvent e)
            {
                close();
            }
        });
        
        // on close window the close method is called
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) 
            {
                close();
            }
        });
    }
    
    /**
     * Is called when the frame is closed by pressing ESC or closing it by
     * clicking on the close icon.
     * 
     * @return True if the frame got closed; false otherwise.
     */
    public boolean close()
    {
    	System.exit(0);
    	return true;
    	
    }
    
    public boolean SpeedUp()
    {
    	int step = Speed.getStep();
    	Speed.UpdateStep(step + step);
    	step = step + step;
    	System.out.println("step ++" + Speed.getStep());
    	return true;
    	
    }
    
    public boolean SpeedDown()
    {
    	int step = Speed.getStep();
    	Speed.UpdateStep(step /2 );
    	System.out.println("step --" + Speed.getStep());
    	return true;
    	
    }
    
    public boolean Pause(JFrame frame)
    {
    	Speed.Pause(frame);
    	return true;
    	
    }
}