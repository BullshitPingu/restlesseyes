package de.barny.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class OptButton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	static Color ButtonColor = new Color(0,0,0,128);
	static Boolean isOver;
	
	OptButton() {
		MouseListener optionsAl = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ButtonColor = new Color(77,121,255,255);
				isOver = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ButtonColor = new Color(0,0,0,128);
				isOver = false;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isOver) {
					Options.visibleOpt();
				}
			}
			
		};
		addMouseListener(optionsAl);
	}
	
	protected void paintComponent(Graphics gButton) {
		Ellipse2D shapeForOpt = new Ellipse2D.Double(1, 1, 40, 40);
		Area outline = getOutline(shapeForOpt);
		Graphics2D optButton = (Graphics2D)gButton;
		optButton.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		optButton.setColor(ButtonColor);
		optButton.clip(outline);
		int[] xPoints = {0, 1, 2, 3, 12};
		int[] yPoints = {12, 3, 2, 1, 0};
		optButton.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	public Area getOutline(Ellipse2D a) {
		Area outline = new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
		outline.subtract(new Area(a));
		return outline;
	}
	
}
