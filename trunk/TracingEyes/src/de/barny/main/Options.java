package de.barny.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Options extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JFrame optF = new Options();

	Options() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		
		addOptions(getContentPane());
		addWindowListener(seeIfFocused);
		pack();
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(getSize().width/2), 0);
	}
	
	private void addOptions(Container content) {
		JPanel optionP = new JPanel(new FlowLayout());
		optionP.setBackground(new Color(0,0,0,0));
		
		Color bColor = new Color(240,240,240);
		
		JButton exit = new JButton("Beenden");
		exit.setBackground(new Color(255,160,160));
		JButton zeilenAnzahl = new JButton("Zeilen");
		zeilenAnzahl.setBackground(bColor);
		JButton eyeAnzahl = new JButton("Augen");
		eyeAnzahl.setBackground(bColor);
		JButton abstoﬂung = new JButton("Abstoﬂen");
		abstoﬂung.setBackground(bColor);
		JButton anziehung = new JButton("Anziehen");
		anziehung.setBackground(bColor);
		JButton eyeTransp = new JButton("Transparent");
		eyeTransp.setBackground(bColor);
		
		ActionListener getZeilenAnzahl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String a = JOptionPane.showInputDialog("Wie viele Zeilen willst du denn?");
				try {
					int n = Integer.parseInt(a);
					if (n == 2 && Main.augenAnzahl == 1) {
						JOptionPane.showMessageDialog(null, "Ein Auge auf 2 Zeilen einzuteilen geht nicht.");
					} else if (n <= 0) {
						JOptionPane.showMessageDialog(null, "Um keine Zeilen zu Zeichnen empfehle ich das Programm zu schlieﬂen.");
					} else if (n > Main.augenAnzahl) {
						JOptionPane.showMessageDialog(null, "Es sind zuwenig Augen f¸r " + n + " Zeilen vorhanden.");
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
						JOptionPane.showMessageDialog(null, "Um keine Augen zu Zeichnen empfehle ich das Programm zu schlieﬂen.");
					} else if (n < Main.zeilenAnzahl) {
						JOptionPane.showMessageDialog(null, "Es sind zuviele Zeilen f¸r " + n + " Augen vorhanden.");
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
		optionP.add(zeilenAnzahl);
		optionP.add(eyeAnzahl);
		optionP.add(abstoﬂung);
		optionP.add(anziehung);
		optionP.add(eyeTransp);
		optionP.add(exit);
		
		content.add(optionP);
	}

	public static void visibleOpt() {
		if (!optF.isShowing()) {
			optF.setVisible(true);
		} else {
			optF.setVisible(false);
		}
	}

	WindowListener seeIfFocused = new WindowListener() {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			visibleOpt();
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
}
