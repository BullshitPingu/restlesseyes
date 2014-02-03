package de.barny.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		setBackground(new Color(128,128,128,200));
		
		addOptions(getContentPane());
		addWindowListener(seeIfFocused);
		pack();
	}
	
	private void addOptions(Container content) {
		JPanel optionP = new JPanel(new FlowLayout());
		optionP.setBackground(new Color(0,0,0,0));
		
		JButton exit = new JButton("X");
		exit.setBackground(new Color(255,160,160));
		
		JSlider scaleEye = new JSlider(JSlider.HORIZONTAL, 30, 500, 50);
		
		ChangeListener scaleEyeAl = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					Main.eyeSize = (int)source.getValue();
					Eye.updateSize();
					
				}
				
			}
			
		};
		
		scaleEye.addChangeListener(scaleEyeAl);
		
		ActionListener anziehungAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.physics = 1;
			}
			
		};
		
		ActionListener abstoﬂungAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.physics =-1;
			}
			
		};
		
		ActionListener exitAl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		};
		exit.addActionListener(exitAl);
		
		optionP.add(exit);
		optionP.add(scaleEye);
		
		content.add(optionP);
	}

	public static void visibleOpt() {
		if (!optF.isShowing()) {
			optF.setLocation((int)Main.windowX, (int)Main.windowY-optF.getSize().height);
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
			
		}
		
	};
	
}
