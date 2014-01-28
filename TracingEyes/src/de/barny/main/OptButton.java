package de.barny.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

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
					showMenu(e);
				}
			}
			
		};
		addMouseListener(optionsAl);
	}
	
	private void showMenu(MouseEvent e) {
		JPopupMenu jp = new JPopupMenu();
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem abstoßung = new JMenuItem("Abstoßung!");
		JMenuItem anziehung = new JMenuItem("Anziehung!");
		JMenuItem eyeAnzahl = new JMenuItem("Augenanzahl!");
		JMenuItem zeilenAnzahl = new JMenuItem("Zeilenanzahl!");
		JMenuItem eyeTransp = new JMenuItem("Transparenz An/Aus");
		ActionListener getZeilenAnzahl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String a = JOptionPane.showInputDialog("Wie viele Zeilen willst du denn?");
				try {
					int n = Integer.parseInt(a);
					if (n == 2 && Main.augenAnzahl == 1) {
						JOptionPane.showMessageDialog(null, "Ein Auge auf 2 Zeilen einzuteilen geht nicht.");
					} else if (n <= 0) {
						JOptionPane.showMessageDialog(null, "Um keine Zeilen zu Zeichnen empfehle ich das Programm zu schließen.");
					} else if (n > Main.augenAnzahl) {
						JOptionPane.showMessageDialog(null, "Es sind zuwenig Augen für " + n + " Zeilen vorhanden.");
					} else {
						Main.zeilenAnzahl = n;
						Main.zeilen = n - 1;
						Main.frame.setSize(2+(Main.augenAnzahl*41), 2+(Main.zeilenAnzahl*41));
						Main.dE.setSize(2+(Main.augenAnzahl*41), 2+(Main.zeilenAnzahl*41));
					}
					} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Keine passende Zahl eingegeben.");
				}
			}
			
		};
		zeilenAnzahl.addActionListener(getZeilenAnzahl);
		ActionListener getEyeAnzahl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String a = JOptionPane.showInputDialog("Wie viele Augen willst du denn?");
				try {
					int n = Integer.parseInt(a);
					if (n == 1 && Main.zeilenAnzahl == 2) {
						JOptionPane.showMessageDialog(null, "Ein Auge auf 2 Zeilen einzuteilen geht nicht.");
					} else if (n <= 0) {
						JOptionPane.showMessageDialog(null, "Um keine Augen zu Zeichnen empfehle ich das Programm zu schließen.");
					} else if (n < Main.zeilenAnzahl) {
						JOptionPane.showMessageDialog(null, "Es sind zuviele Zeilen für " + n + " Augen vorhanden.");
					} else {
						Main.augenAnzahl = n;
						Main.anzahl = n - 1;
						Main.frame.setSize(1+(Main.augenAnzahl*41), 2+(Main.zeilenAnzahl*41));
						Main.dE.setSize(1+(Main.augenAnzahl*41), 2+(Main.zeilenAnzahl*41));
					}
					} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Keine passende Zahl eingegeben.");
				}
			}
			
		};
		eyeAnzahl.addActionListener(getEyeAnzahl);
		ActionListener transpAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Main.transparency == 255) {
					Main.transparency = 1;
				} else {
					Main.transparency = 255;
				}
			}

		};
		eyeTransp.addActionListener(transpAl);
		ActionListener anziehungAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.physics = 1;
			}
			
		};
		anziehung.addActionListener(anziehungAl);
		ActionListener abstoßungAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.physics =-1;
			}
			
		};
		abstoßung.addActionListener(abstoßungAl);
		ActionListener exitAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		};
		exit.addActionListener(exitAl);
		jp.add(eyeAnzahl);
		jp.add(zeilenAnzahl);
		jp.add(eyeTransp);
		jp.add(anziehung);
		jp.add(abstoßung);
		jp.add(exit);
		jp.show(Main.frame, e.getX(), e.getY());
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
