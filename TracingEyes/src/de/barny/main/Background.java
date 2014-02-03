package de.barny.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class Background extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Background() {
		setBackground(new Color(0,0,0,0));
	}
	
	protected void paintComponent(Graphics backg) {
		super.paintComponent(backg);
		
		Graphics2D bg = (Graphics2D)backg;
		bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Shape roundR = new RoundRectangle2D.Double(0, 0, Main.fSizeX, Main.fSizeY, 20, 20);
		bg.setColor(new Color(128,128,128,60));
		bg.fill(roundR);
	}
	
}
