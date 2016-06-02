package org.tankermanz;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Control extends JPanel{
	//
	//	JPanel controlPanel;
	//	
	//	JButton fireButton;
	//	
	//	JPanel weaponSelectPanel;
	//	JButton weaponSelectLeft;
	//	JButton weaponSelectRight;
	//	JLabel weaponSelectLabel;
	//	
	//	JButton menu;
	//	
	//	JPanel powerAnglePanel;
	//	JLabel powerLabel;
	//	JLabel angleLabel;
	//	
	//	JPanel fuelPanel;
	//	JLabel fuelLabel;
	//	JProgressBar fuelAvailable;
	//	
	//	ImageIcon weaponImage1;
	//	

	//	public Control (){
	//		controlPanel = new JPanel();
	//		
	//		fuelLabel = new JLabel("Fuel 100%");
	//		fuelAvailable = new JProgressBar();
	//		fuelPanel = new JPanel();
	//		fuelPanel.setLayout(new GridLayout(1,2));
	//		
	//		fuelPanel.add(fuelLabel);
	//		fuelPanel.add(fuelAvailable);
	//		controlPanel.add(fuelPanel);
	//		
	//		weaponSelectLeft = new JButton ("<");
	//		weaponSelectRight = new JButton(">");
	//		weaponSelectLabel = new JLabel("Tank Round");
	//		weaponSelectPanel = new JPanel ();
	//		
	//		weaponSelectPanel.add(weaponSelectLeft);
	//		weaponSelectPanel.add(weaponSelectLabel);
	//		weaponSelectPanel.add(weaponSelectRight);
	//		//weaponSelectPanel.add(weaponImageIcon);
	//		controlPanel.add(weaponSelectPanel);
	//		
	//		powerLabel = new JLabel("Power: 100");
	//		angleLabel = new JLabel("Angle: 45");
	//		powerAnglePanel = new JPanel();
	//		powerAnglePanel.setLayout(new GridLayout(1, 2));
	//		powerAnglePanel.add(powerLabel);
	//		powerAnglePanel.add(angleLabel);
	//		controlPanel.add(powerAnglePanel);
	//				
	//		fireButton = new JButton ("Fire!");
	//		controlPanel.add(fireButton);
	//	}
	//	}

	static int fireBoxX = 350; 	// Left corner X coord of fire box
	static int fireBoxY = 375;	// Left corner Y coord of fire box
	static int fireBoxLength = 200;	// Length of box
	static int fireBoxHeight = 75;	// Height of box

	static boolean clickFire = false;

	public static void drawBar (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		g2.setPaint(new GradientPaint(0, 350,  new Color (66, 66, 66), 0, 500, new Color (44, 44, 44)));
		g.fillRect(0, 350, 950, 500);	// Draw grey control bar
	}

	public static void drawFireButton (Graphics g) {
		int mouseX = Terrain.getMouseX();
		int mouseY = Terrain.getMouseY();
		

		Graphics2D g2 = (Graphics2D) g;	

		if (mouseX > fireBoxX && mouseX < fireBoxX + fireBoxLength) {
			if (mouseY > fireBoxY && mouseY < fireBoxY + fireBoxHeight) {
				g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (178, 5, 5), 
						fireBoxX, fireBoxY + fireBoxHeight, new Color (229, 0, 0)));
				clickFire = true;
			}
			else {
				g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (3, 84, 0), 
						fireBoxX, fireBoxY + fireBoxHeight, new Color (4, 127, 11)));
				clickFire = false;
			}
		}
		else {
			g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (3, 84, 0), 
					fireBoxX, fireBoxY + fireBoxHeight, new Color (4, 127, 11)));
			clickFire = false;
		}
		g.fillRect(fireBoxX, fireBoxY, fireBoxLength, fireBoxHeight);
	}

	public static boolean getClickFire () {
		return clickFire;
		
	}
}
