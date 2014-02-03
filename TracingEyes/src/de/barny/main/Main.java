package de.barny.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.*;

public class Main {
	
	static int transparency = 255;
	static int eyeSize = 50;
	static int augenAnzahl = 2;
	static int pupillenSize = eyeSize/4;
	static int eEyeWidthSubtract = 5;
	static int physics = 1; // 1 = Anziehung, -1 = Abstoﬂung
	
	static JFrame frame;
	static Point2D mouse;
	static int windowX;
	static int windowY;
	static Main content = new Main();
	static int fSizeX = Eye.abstand*2+Eye.width*augenAnzahl;
	static int fSizeY = Eye.abstand*2+Eye.height;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            	Pupil.drawPupil.start();
            	new Options();
            }
        });
	}

	public static void createAndShowGUI() {
		frame = new JFrame("Tracing Eyes");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		content.addComponents(frame.getContentPane());
		
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setSize(fSizeX, fSizeY);
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getSize().width/2),Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getSize().height/2));
		frame.setVisible(true);
		frame.setBackground(new Color(0,0,0,0));
	}

	static JPanel dE;
	static JPanel dP;
	static JPanel oB;
	static JPanel bg;
	Point iC;
	
	MouseListener eyePanelPressed = new MouseListener() {

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
			dE.getComponentAt(iC);
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	MouseMotionListener eyePanelMoved = new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent e) {
			Point fp = frame.getLocation();
			
			int Xm = (int) ((fp.getX() + e.getX()) - (fp.getX() + iC.getX()));
			int Ym = (int) ((fp.getY() + e.getY()) - (fp.getY() + iC.getY()));
			
			int X = (int) (fp.getX() + Xm);
			int Y =	(int) (fp.getY() + Ym);
			
			frame.setLocation(X, Y);
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private void addComponents(Container container) {
		JLayeredPane lp = new JLayeredPane();
		
		dE = new Eye(augenAnzahl);
		dP = new Pupil(augenAnzahl);
		oB = new OptButton();
		bg = new Background();
		
		container.addMouseListener(eyePanelPressed);
		container.addMouseMotionListener(eyePanelMoved);
		
		dE.setOpaque(true);
		dP.setOpaque(true);
		oB.setOpaque(true);
		bg.setOpaque(true);
		
		lp.add(dE, 2);
		lp.add(dP, 0);
		lp.add(oB, 1);
		lp.add(bg, 3);
		
		dE.setSize(fSizeX, fSizeY);
		dP.setSize(fSizeX, fSizeY);
		oB.setSize(20, 20);
		bg.setSize(fSizeX, fSizeY);
		
		container.add(lp);
	}
}
