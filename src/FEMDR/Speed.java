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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Michel Hummel
 */
public  class  Speed {

	static int step = 1000/120;

	static boolean pause = false;

	private Speed() {
	}

	synchronized static int getStep() {
		return step;
	}

	synchronized static void UpdateStep(int newStep) {
		step = newStep;
	}

	synchronized static void Pause(JFrame frame) {
		//inverse pause
		pause = true;
		try {
			String strDiag = " FEMDR V"+Prop.getVersion()+" - "+Prop.getRevision()+"\n Open source (Free) EMDR tool\n Written by Michel Hummel\n To help Zéév Maoz and its patients";

			Object[] options = {"Show options",
					"Continue ping pong",
					"Quit FEMDR"
			};
			int n = JOptionPane.showOptionDialog(frame,
					strDiag,
					"Options panel",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					options,
					options[1]);
			
			if (n == 0) {
//				OptionDialog.createAndShowGUI(frame);
//				while (true) {
//					if (frame.isVisible()) {
//						// do the validation
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e1) {
//							break;
//						}
//					}
//				}
//				System.out.println("return option");
//	            frame.setVisible(true);
//				JOptionPane.showMessageDialog(frame,
//								strDiag);
			}else if (n == 1) {
				//continue
			}else if (n == 2) {
				//Quit
				System.exit(0);
			}
		} catch (Exception e) {

		}finally {
			pause = false;
		}

	}

	synchronized static boolean isPause() {
		return pause;
	}

}
