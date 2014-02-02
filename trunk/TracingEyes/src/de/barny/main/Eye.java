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
	static int width = Main.eyeSize-Main.eEyeWidthSubtract;
	
	static int height = Main.eyeSize;
	
	Eye(int i) {
		setBackground(new Color(0,0,0,0));
		
		anzahl = i;
	}

	protected void paintComponent(Graphics gEye) {
		for (int i = 0; i < anzahl; i++) {
			Graphics2D eye = (Graphics2D)gEye;
			eye.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			eye.setColor(new Color(255, 255, 255, Main.transparency)); //Farbe des Hintergrunds des Auges
			eye.fillOval(abstand + i*width, abstand, width, height);	//Zeichne den Hintergrund des Auges

			eye.setColor(new Color(0, 0, 0, 255)); //Farbe des Rands des Auges
			eye.setStroke(new BasicStroke(2)); //Dicke des Randstrichs
			eye.drawOval(abstand + i*width, abstand, width, height); //Zeichne den Rand des Auges
		}
	}

}
