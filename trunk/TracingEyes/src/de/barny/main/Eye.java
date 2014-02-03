package de.barny.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
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
	static int abstand = 5;
	static int stroke = 2;
	static int width = Main.eyeSize-Main.eEyeWidthSubtract;
	
	static int height = Main.eyeSize;
	
	Eye(int i) {
		setBackground(new Color(0,0,0,0));
		
		anzahl = i;
	}

	protected void paintComponent(Graphics gEye) {
		super.paintComponent(gEye);
		
		Graphics2D eye = (Graphics2D)gEye;
		eye.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gradient = new GradientPaint(0, 0, new Color(255,255,255, Main.transparency), 0, height, new Color(240,240,240, Main.transparency), true);
		for (int i = 0; i < anzahl; i++) {
			eye.setPaint(gradient);
			eye.fillOval(abstand + i*width, abstand, width, height);	//Zeichne den Hintergrund des Auges

			eye.setColor(new Color(128, 128, 128)); //Farbe des Rands des Auges
			eye.setStroke(new BasicStroke(stroke)); //Dicke des Randstrichs
			eye.drawOval(abstand + i*width, abstand, width, height); //Zeichne den Rand des Auges
		}
	}
	
	public static void updateSize() {
		width = Main.eyeSize-Main.eEyeWidthSubtract;
		height = Main.eyeSize;
		
		Main.fSizeX = Eye.abstand*2+Eye.width*Main.augenAnzahl;
		Main.fSizeY = Eye.abstand*2+Eye.height;
		
		Main.frame.setSize(Main.fSizeX, Main.fSizeY);
		Main.dE.setSize(Main.fSizeX, Main.fSizeY);
		Main.dP.setSize(Main.fSizeX, Main.fSizeY);
		Main.bg.setSize(Main.fSizeX, Main.fSizeY);
		
		Main.pupillenSize = Main.eyeSize/4;
	}

}
