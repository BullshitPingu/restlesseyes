package de.be.restlesseyes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Pupil extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int anzahl = 0;
	
	Pupil(int i) {
		setBackground(new Color(0,0,0,0));
		
		anzahl = i;
	}
	
	static Thread drawPupil = new Thread() {
		@Override public void run() {
			try {
				while (true) {
					Main.mouse = MouseInfo.getPointerInfo().getLocation();
					Main.windowX = Main.frame.getContentPane().getLocationOnScreen().x;
					Main.windowY = Main.frame.getContentPane().getLocationOnScreen().y;
					Main.frame.getContentPane().repaint();
					
					Thread.sleep(20);
				}
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	};

	protected void paintComponent(Graphics gPupil) {
		super.paintComponent(gPupil);
		Graphics2D pupil = (Graphics2D)gPupil;
		pupil.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		pupil.setColor(new Color(0, 0, 0, 255)); //Farbe der Pupille
		for (int i = 0; i < anzahl; i++) {
			pupil.fillOval((getEyePos(Eye.abstand + (Eye.width/2) + (i*Eye.width), 'x')) - Main.pupillenSize/2+1, (getEyePos(Eye.abstand + (Eye.width/2) + (i*Eye.width), 'y')) - Main.pupillenSize/2+1, Main.pupillenSize, Main.pupillenSize); //Zeichne die Pupille
		}
	}

	public static int getEyePos(int distance, char dir) {
		try {
			double dist = Main.mouse.distance(Main.windowX+distance, Eye.abstand + Main.windowY+Main.eyeSize/2);
			double dX = (Main.mouse.getX() - Main.windowX);
			double dY = (Main.mouse.getY() - Main.windowY);
			double eyePosX = distance;
			double eyePosY = Eye.abstand +(Main.eyeSize/2);

			if (dist <= ((Eye.width/2)-Main.pupillenSize/2) && Main.physics == 1) {
				eyePosX = dX;
				eyePosY = dY;
			} else {
				double a = (Main.physics *(((Eye.width/2)-Main.pupillenSize/2)/Main.mouse.distance(Main.windowX+distance, Main.windowY+(Eye.abstand + (Main.eyeSize/2))))*((Main.mouse.getX()-(Main.windowX+distance))));
				double b = (Main.physics *(((Eye.height/2)-Main.pupillenSize/2)/Main.mouse.distance(Main.windowX+distance, Main.windowY+(Eye.abstand + (Main.eyeSize/2))))*((Main.mouse.getY()-(Main.windowY+(Eye.abstand + (Main.eyeSize/2))))));
				eyePosX += a;
				eyePosY += b;
			}

			if (dir == 'x') {
				return (int) eyePosX;
			} else if(dir == 'y') {
				return (int) eyePosY;
			} else {
				return (int)0;
			}
		} catch (Exception e) {
			return 0;
		}

	}

}
