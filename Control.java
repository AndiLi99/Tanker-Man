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

//max
public class Control {

	static int controlPanelHeight = 150;
	static int controlPanelY = 350;

	static final int boxHeight = 90;	// Height of box

	static final int healthBoxX = 10;	// Left corner X coord of health box
	static final int healthBoxY = 360;	// Left corner Y coord of health box
	static final int healthBoxLength = 150; 	// Length of health box

	static final int fuelBoxX = 170;
	static final int fuelBoxY = 360;
	static final int fuelBoxLength = 90;

	static final int fireBoxX = 500; 	// Left corner X coord of fire box
	static final int fireBoxY = 360;	// Left corner Y coord of fire box
	static final int fireBoxLength = 200;		// Length of fire box

	static boolean clickFire = false;

	public static void drawBar (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		g2.setPaint(new GradientPaint(0, 350,  new Color (66, 66, 66), 0, 500, new Color (22, 22, 22)));
		g.fillRect(0, 350, 950, 500);	// Draw grey control bar
	}

	public static void drawHealthBox (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	

		// Draw blue background box around health
		g2.setPaint(new GradientPaint(0, healthBoxY, new Color (1, 1, 13), 0, healthBoxY
				+ boxHeight, new Color (0, 0, 161)));	
		g.fillRect(healthBoxX, healthBoxY, healthBoxLength, boxHeight);

		// Armour Box

		// HP box
	}

	public static void drawFuelBox (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, 360,  new Color (11, 11, 11), 0, 490, new Color (35, 35, 35)));
		g.fillRect(fuelBoxX, fuelBoxY, fuelBoxLength, boxHeight);


	}

	public static void drawFireButton (Graphics g) {
		int mouseX = Terrain.getMouseX();
		int mouseY = Terrain.getMouseY();

		Graphics2D g2 = (Graphics2D) g;	

		if (mouseX > fireBoxX && mouseX < fireBoxX + fireBoxLength) {
			if (mouseY > fireBoxY && mouseY < fireBoxY + boxHeight) {	// If mouse is within X nad Y of box
				// Set box color to red
				g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (178, 5, 5), 
						fireBoxX, fireBoxY + boxHeight, new Color (229, 0, 0)));
				// Ability to fire is true
				clickFire = true;
			}
			else {
				// Green if not
				g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (3, 84, 0), 
						fireBoxX, fireBoxY + boxHeight, new Color (4, 127, 11)));
			}
		}
		else {
			// Green color
			g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (3, 84, 0), 
					fireBoxX, fireBoxY + boxHeight, new Color (4, 127, 11)));
		}
		g.fillRect(fireBoxX, fireBoxY, fireBoxLength, boxHeight);
	}

	public static boolean getClickFire () {
		return clickFire;
	}
}
