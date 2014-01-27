package de.barny.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class DrawEyes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Point iC;
	
	DrawEyes() {
		setBackground(new Color(0,0,0,0));
		MouseListener ml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				iC = e.getPoint();
				getComponentAt(iC);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
			
		};
		
		addMouseListener(ml);
		
		MouseMotionListener mml = new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point fp = Main.frame.getLocation();
				
				int Xm = (int) ((fp.getX() + e.getX()) - (fp.getX() + iC.getX()));
				int Ym = (int) ((fp.getY() + e.getY()) - (fp.getY() + iC.getY()));
				
				int X = (int) (fp.getX() + Xm);
				int Y =	(int) (fp.getY() + Ym);
				
				Main.frame.setLocation(X, Y);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		addMouseMotionListener(mml);
	}

	Graphics2D g2d2;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d2 = (Graphics2D)g;
		g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d2.setColor(new Color(255, 255, 255, Main.transparency));
		try {
			int augenProSpalte = (int) Main.augenAnzahl/Main.zeilenAnzahl;
			for (int cSpalte = 0; cSpalte <= Main.zeilen; ) {
				int i = 0;
				do {
					g2d2.fillOval(1 + (i*40), 1 + (cSpalte*40), 40, 40);
					i++;
					if (i == augenProSpalte) {
						cSpalte++;
					}
				} while (i <= (augenProSpalte-1));
			}
			g2d2.setColor(new Color(0, 0, 0, 255));
			g2d2.setStroke(new BasicStroke(2));
			for (int cSpalte = 0; cSpalte <= Main.zeilen; ) {
				int i = 0;
				do {
					g2d2.drawOval(1 + (i*40), 1 + (cSpalte*40), 40, 40);
					i++;
					if (i == augenProSpalte) {
						cSpalte++;
					}
				} while (i <= (augenProSpalte-1));
			}
			g2d2.setColor(new Color(0, 0, 0, 255));
			for (int cSpalte = 0; cSpalte <= Main.zeilen; ) {
				int i = 0;
				do {
					g2d2.fillOval((Main.getEyePos(21 + (i*40), 'x')) - 5, (Main.getEyePos(21 + (i*40), 'y')) - 5 + (cSpalte*40), 10, 10);
					i++;
					if (i == augenProSpalte) {
						cSpalte++;
					}
				} while (i <= (augenProSpalte-1));
			}
		} catch (Exception e) {
			System.out.println("Fehler beim Zeichnen der Augen.");
		}
	}
	
}
