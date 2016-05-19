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