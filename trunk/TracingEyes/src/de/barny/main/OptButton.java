package de.barny.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class OptButton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	static Color ButtonColor = new Color(0,0,0,128);
	static Boolean isOver;
	
	OptButton() {
		setBackground(new Color(0,0,0,0));
		MouseListener optionsAl = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ButtonColor = new Color(20,50,200,200);
				Main.frame.getContentPane().repaint();
				isOver = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ButtonColor = new Color(0,0,0,128);
				Main.frame.getContentPane().repaint();
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
		super.paintComponent(gButton);
		Graphics2D optButton = (Graphics2D)gButton;
		optButton.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		optButton.setColor(ButtonColor);
		int[] xPoints = {5, 5, 15};
		int[] yPoints = {5, 12, 5};
		optButton.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
}
