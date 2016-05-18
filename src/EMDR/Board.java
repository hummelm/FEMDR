package EMDR;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Board extends JPanel {

    private Dot player = new Dot();

    public Board(){
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g){  
        super.paintComponent(g);
    	if (! Speed.isPause() ) {
        player.update();
        g.setColor(Color.green);
        g.fillOval(player.getCenter().x, player.getCenter().y,
             player.getRadius(), player.getRadius());
    	}
    } 
}