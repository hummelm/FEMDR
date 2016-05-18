package EMDR;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel {
	static JPanel draw;

    public static void main(String[] args) throws InterruptedException {
            JFrame frame = new KeyCacherFrame();
            
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

            //default title and icon
            JOptionPane.showMessageDialog(frame,
    				" FEMDR V0.1 - RC1\n Open source (Free) EMDR tool\n Written by Michel Hummel\n To help XXXX XXXX and its patients");

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

