package de.barny.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Eye extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int anzahl = 0;
	static int abstand = 2;
	
	Eye(int i) {
		setBackground(new Color(0,0,0,0));
		
		anzahl = i;
	}

	protected void paintComponent(Graphics g) {
		for (int i = 0; i < anzahl; i++) {
			Graphics2D eye = (Graphics2D)g;
			eye.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			eye.setColor(new Color(255, 255, 255, Main.transparency)); //Farbe des Hintergrunds des Auges
			eye.fillOval(abstand + i*Main.eyeSize, abstand, (Main.eyeSize), (Main.eyeSize));	//Zeichne den Hintergrund des Auges

			eye.setColor(new Color(0, 0, 0, 255)); //Farbe des Rands des Auges
			eye.setStroke(new BasicStroke(2)); //Dicke des Randstrichs
			eye.drawOval(abstand + i*Main.eyeSize, abstand, (Main.eyeSize), (Main.eyeSize)); //Zeichne den Rand des Auges

			eye.setColor(new Color(0, 0, 0, 255)); //Farbe der Pupille
			eye.fillOval((getEyePos(abstand + (Main.eyeSize/2) + (i*Main.eyeSize), 'x')) - Main.pupillenSize/2+1, (getEyePos(abstand + (Main.eyeSize/2) + (i*Main.eyeSize), 'y')) - Main.pupillenSize/2+1, Main.pupillenSize, Main.pupillenSize); //Zeichne die Pupille
		}
	}

	public static int getEyePos(int distance, char dir) {
		try {
			double dist = Main.mouse.distance(Main.windowX+distance, abstand + Main.windowY+Main.eyeSize/2);
			double dX = (Main.mouse.getX() - Main.windowX);
			double dY = (Main.mouse.getY() - Main.windowY);
			double eyePosX = distance;
			double eyePosY = abstand +(Main.eyeSize/2);
			if (dist <= ((Main.eyeSize/2)-Main.pupillenSize/2) && Main.physics == 1) {
				eyePosX = dX;
				eyePosY = dY;
			} else {
				double a = (Main.physics *(((Main.eyeSize/2)-Main.pupillenSize/2)/Main.mouse.distance(Main.windowX+distance, Main.windowY+(abstand + (Main.eyeSize/2))))*((Main.mouse.getX()-(Main.windowX+distance))));
				double b = (Main.physics *(((Main.eyeSize/2)-Main.pupillenSize/2)/Main.mouse.distance(Main.windowX+distance, Main.windowY+(abstand + (Main.eyeSize/2))))*((Main.mouse.getY()-(Main.windowY+(abstand + (Main.eyeSize/2))))));
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
