package de.barny.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

import javax.swing.*;

public class Main {
	
	static int transparency = 255;
	static int augenAnzahl = 2;
	static int zeilenAnzahl = 1;
	
	static JFrame frame;
	static Point2D mouse;
	static int windowX;
	static int windowY;
	static Main content = new Main();
	static int physics = 1; // 1 = Anziehung, -1 = Abstoﬂung
	static int anzahl = augenAnzahl - 1;
	static int zeilen = zeilenAnzahl - 1;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            	getStarted.start();
            }
        });
	}
	
	static Thread getStarted = new Thread() {
		@Override public void run() {
			try {
				while (true) {
					if (mouse == MouseInfo.getPointerInfo().getLocation()) {
						//Thread.sleep(2000);
					}
					mouse = MouseInfo.getPointerInfo().getLocation();
					windowX = frame.getContentPane().getLocationOnScreen().x;
					windowY = frame.getContentPane().getLocationOnScreen().y;
					frame.getContentPane().repaint();
					oB.repaint();
					Thread.sleep(20);
				}
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	};
	
	public static int getEyePos(int distance, char dir) {
		try {
			double dist = mouse.distance(windowX+distance, windowY+20);
			double dX = (mouse.getX() - windowX);
			double dY = (mouse.getY() - windowY);
			double eyePosX = distance;
			double eyePosY = 21;
			if (dist <= 16 && physics == 1) {
				eyePosX = dX;
				eyePosY = dY;
			} else {
				double a = (physics *(15/mouse.distance(windowX+distance, windowY+21))*((mouse.getX()-(windowX+distance))));
				double b = (physics *(15/mouse.distance(windowX+distance, windowY+21))*((mouse.getY()-(windowY+21))));
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

	public static void createAndShowGUI() {
		frame = new JFrame("Tracing Eyes");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		content.addComponents(frame.getContentPane());
		
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setSize(2+(augenAnzahl*41), 2+(zeilenAnzahl*41));
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getSize().width/2),Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getSize().height/2));
		frame.setVisible(true);
		frame.setBackground(new Color(0,0,0,0));
	}

	static JPanel dE;
	static JPanel oB;
	
	private void addComponents(Container container) {
		JLayeredPane lp = new JLayeredPane();
		dE = new DrawEyes();
		oB = new OptButton();
		dE.setOpaque(true);
		oB.setOpaque(true);
		lp.add(dE, 1);
		lp.add(oB, 0);
		dE.setSize(2+(augenAnzahl*41), 2+(zeilenAnzahl*41));
		oB.setSize(20, 20);
		container.add(lp);
	}
}
