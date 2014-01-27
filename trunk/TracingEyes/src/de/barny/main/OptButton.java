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
	
	OptButton() {
		MouseListener optionsAl = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				showMenu(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ButtonColor = new Color(255,0,0,128);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ButtonColor = new Color(0,0,0,128);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		};
		addMouseListener(optionsAl);
	}
	
	private void showMenu(MouseEvent e) {
		JPopupMenu jp = new JPopupMenu();
		JMenuItem exit = new JMenuItem("Beenden");
		JMenuItem abstoﬂung = new JMenuItem("Abstoﬂung!");
		JMenuItem anziehung = new JMenuItem("Anziehung!");
		JMenuItem eyeAnzahl = new JMenuItem("Augenanzahl!");
		JMenuItem spaltenAnzahl = new JMenuItem("Spaltenanzahl!");
		JMenuItem eyeTransp = new JMenuItem("Transparenz An/Aus");
		ActionListener getSpaltenAnzahl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String a = JOptionPane.showInputDialog("Wie viele Zeilen willst du denn?");
				try {
					int n = Integer.parseInt(a);
					Main.spaltenAnzahl = n;
					Main.spalten = n - 1;
					Main.frame.setSize(1+(Main.augenAnzahl*41), 1+(Main.spaltenAnzahl*41));
					} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Keine passende Zahl eingegeben.");
				}
			}
			
		};
		spaltenAnzahl.addActionListener(getSpaltenAnzahl);
		ActionListener getEyeAnzahl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String a = JOptionPane.showInputDialog("Wie viele Augen willst du denn?");
				try {
					int n = Integer.parseInt(a);
					Main.augenAnzahl = n;
					Main.anzahl = n - 1;
					Main.frame.setSize(1+(Main.augenAnzahl*41), 2+(Main.spaltenAnzahl*41));
					Main.dE.setSize(1+(Main.augenAnzahl*41), 2+(Main.spaltenAnzahl*41));
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
		ActionListener abstoﬂungAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.physics =-1;
			}
			
		};
		abstoﬂung.addActionListener(abstoﬂungAl);
		ActionListener exitAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		};
		exit.addActionListener(exitAl);
		jp.add(eyeAnzahl);
		jp.add(spaltenAnzahl);
		jp.add(eyeTransp);
		jp.add(anziehung);
		jp.add(abstoﬂung);
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
