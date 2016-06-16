package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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

	static final int fireBoxX = 520; 	// Left corner X coord of fire box
	static final int fireBoxY = 360;	// Left corner Y coord of fire box
	static final int fireBoxLength = 220;		// Length of fire box

	static final int weaponScrollerHeight = 20;	// Dimentions of triangle to move left and right for weapons
	static final int weaponScrollerLength = 10;
	static final int weaponBoxX = 270; 	// Left corner X coord of weapon box
	static final int weaponBoxY = 360;	// Left corner Y coord of weapon box
	static final int weaponBoxLength = 240;		// Length of fire box
	static final int weaponBoxHeight = 35;
	static final int weaponSelectorLength = 200;
	static final int weaponSelectorHeight = 45;
	
	static boolean inWeaponChangerLeft = false;
	static boolean inWeaponChangerRight = false;

	static boolean clickFire = false;

	public static void drawBar (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		g2.setPaint(new GradientPaint(0, 350,  new Color (66, 66, 66), 0, 500, new Color (22, 22, 22)));
		g.fillRect(0, 350, 950, 500);	// Draw grey control bar
	}

	public static void drawHealthBox (Graphics g, int hp, int maxHP, int team) {
		Graphics2D g2 = (Graphics2D) g;	

		// Draw blue background box around health
		g2.setPaint(new GradientPaint(0, healthBoxY, new Color (1, 1, 13), 0, healthBoxY
				+ boxHeight, new Color (0, 0, 161)));	
		g.fillRect(healthBoxX, healthBoxY, healthBoxLength, boxHeight);

		// Armour Box

		// HP box
		int distanceHpX = 10;
		int distanceHpY = 20;
		int heightHp = 20;
		// Draw red bar behind HP
		g2.setPaint(new GradientPaint(0, healthBoxY + distanceHpX + heightHp, new Color (119, 0, 0), 0, 
				healthBoxY + distanceHpX, new Color (186, 0, 0)));
		g.fillRect(healthBoxX + distanceHpX, healthBoxY + distanceHpY, healthBoxLength-2*distanceHpX, heightHp);
		// Draw available HP
		int hpLength = (healthBoxLength - 2*distanceHpX)*hp/maxHP;
		g2.setPaint(new GradientPaint(0, healthBoxY + distanceHpX + heightHp, new Color (2, 65, 0), 0, 
				healthBoxY + distanceHpX, new Color (4, 105, 0)));
		g.fillRect(healthBoxX + distanceHpX, healthBoxY + distanceHpY, hpLength, heightHp);

		// Write HP and Max HP on health bar
		String strHP = Integer.toString(hp) + "/" + Integer.toString(maxHP);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.setColor(Color.white);
		g.drawString(strHP, healthBoxX + healthBoxLength/2 - 4*strHP.length(), healthBoxY + 2*distanceHpY - 4);


		// Tank 
		if (team == 0) {
			DrawTank.colorGreen();
			DrawTank.drawCircleTank(g, healthBoxX + healthBoxLength/2, healthBoxY + boxHeight - 5, 20, 0, 315);
		}
		else {
			DrawTank.colorRed();
			DrawTank.drawMountainTank(g, healthBoxX + healthBoxLength/2, healthBoxY + boxHeight - 5, 20, 0, 315);
		}
	}

	public static void drawFuelBox (Graphics g, int fuel, int maxFuel) {
		Graphics2D g2 = (Graphics2D) g;
		int needleCenterDistance = 10;
		AffineTransform resetForm = g2.getTransform();
		// Draw backgroud grey 
		g2.setPaint(new GradientPaint(0, 360,  new Color (11, 11, 11), 0, 490, new Color (35, 35, 35)));
		g.fillRect(fuelBoxX, fuelBoxY, fuelBoxLength, boxHeight);

		// Write fuel
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(new Color(60,60,60));
		g.drawString("Fuel", fuelBoxX + fuelBoxLength/6, fuelBoxY + 2*fuelBoxLength/3);

		// Write F and E
		g.setColor(Color.white);
		g.drawString("F", fuelBoxX + needleCenterDistance, fuelBoxY + fuelBoxLength/4 - 4);
		g.drawString("E", fuelBoxX + fuelBoxLength - 16, fuelBoxY + fuelBoxLength - needleCenterDistance + 4);

		// Draw little ticks for fuel bar
		g2.rotate(Terrain.RADS*285, fuelBoxX + needleCenterDistance, fuelBoxY + fuelBoxLength - needleCenterDistance);
		g2.setColor(Color.white);
		g2.fillRect(fuelBoxX + 3*fuelBoxLength/4 + 5, fuelBoxY + fuelBoxLength, 10, 3);
		g2.rotate(Terrain.RADS*22.5, fuelBoxX + needleCenterDistance, fuelBoxY + fuelBoxLength - needleCenterDistance);
		g2.fillRect(fuelBoxX + 3*fuelBoxLength/4 + 5, fuelBoxY + fuelBoxLength, 10, 3);
		g2.rotate(Terrain.RADS*22.5, fuelBoxX + needleCenterDistance, fuelBoxY + fuelBoxLength - needleCenterDistance);
		g2.fillRect(fuelBoxX + 3*fuelBoxLength/4 + 5, fuelBoxY + fuelBoxLength, 10, 3);
	
		g2.setTransform(resetForm);
		int degreesFuelBar = 90 - (int)(90*(fuel/(double)maxFuel));
		double radiansFuelBar = Terrain.RADS*degreesFuelBar;

		g2.rotate(radiansFuelBar, fuelBoxX + needleCenterDistance, fuelBoxY + fuelBoxLength - needleCenterDistance);
		g2.setColor(Color.red);
		int [] triangleX = { fuelBoxX + needleCenterDistance - 3, fuelBoxX + needleCenterDistance + 3, fuelBoxX + needleCenterDistance };
		int [] triangleY = { fuelBoxY + fuelBoxLength - needleCenterDistance + 3, fuelBoxY + fuelBoxLength - needleCenterDistance + 3, fuelBoxY + fuelBoxLength/4};
		g2.fillPolygon(triangleX, triangleY, 3);

		g2.setTransform(resetForm);

		// Write how much fuel is left
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.setColor(Color.white);
		g.drawString(Integer.toString(fuel), fuelBoxX + fuelBoxLength - 6*Integer.toString(fuel).length() - 10, fuelBoxY + 20);

	}

	public static void drawWeaponBox (Graphics g, int mouseX, int mouseY){
		// Draw blue box for word weapon
		Graphics2D g2 = (Graphics2D) g; 
		g2.setPaint(new GradientPaint(weaponBoxX, 0, new Color (2, 61, 105), weaponBoxX+weaponBoxLength, 0, new Color (0, 102, 102)));	 
		g.fillRect(weaponBoxX, weaponBoxY, weaponBoxLength, weaponBoxHeight);
		g.setColor(new Color (0,0,51));
		g.setFont(new Font ("Franklin Gothic Heavy", Font.PLAIN, 30));
		g.drawString("WEAPONS", weaponBoxX + weaponBoxLength/8, weaponBoxY + 5*weaponBoxHeight/6);

		// Draw triangles to move through weapons
		int centerX1 = weaponBoxX + (weaponBoxLength - weaponSelectorLength)/4;
		int centerY = weaponBoxY + boxHeight - weaponSelectorHeight/2;
		int [] triangleX1 = {centerX1 - weaponScrollerLength/2, centerX1 + weaponScrollerLength/2, centerX1 + weaponScrollerLength/2 };
		int [] triangleY = {centerY, centerY - weaponScrollerHeight/2, centerY + weaponScrollerHeight/2};
		g.setColor(Color.white);
		g.fillPolygon(triangleX1, triangleY, 3);
		
		int centerX2 = weaponBoxX + weaponBoxLength - (weaponBoxLength - weaponSelectorLength)/4;
		int [] triangleX2 = {centerX2 + weaponScrollerLength/2, centerX2 - weaponScrollerLength/2, centerX2 - weaponScrollerLength/2 };
		g.fillPolygon(triangleX2, triangleY, 3);
		
		DrawWeaponChooser.drawWeaponChooser(g, Terrain.getCurrentPlayer().getCurrentWeapon(), weaponBoxX + (weaponBoxLength - weaponSelectorLength)/2, weaponBoxY + boxHeight - weaponSelectorHeight, weaponSelectorLength, weaponSelectorHeight);
		
		setInMapScroller(mouseX, mouseY, centerY, centerX1, centerX2);
	} 

	public static void drawFireButton (Graphics g) {
		int mouseX = Terrain.getMouseX();
		int mouseY = Terrain.getMouseY();

		Graphics2D g2 = (Graphics2D) g;	

		if (mouseX > fireBoxX && mouseX < fireBoxX + fireBoxLength) {
			if (mouseY > fireBoxY && mouseY < fireBoxY + boxHeight) {	// If mouse is within X nad Y of box
				// Set box color to red
				g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (111, 0, 0), 
						fireBoxX, fireBoxY + boxHeight, new Color (78, 0, 0)));
				// Ability to fire is true
				clickFire = true;
			}
			else {
				// Green if not
				g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (3, 84, 0), 
						fireBoxX, fireBoxY + boxHeight, new Color (4, 127, 11)));
				clickFire = false;	
			}
		}
		else {
			// Green color
			g2.setPaint(new GradientPaint(fireBoxX, fireBoxY,  new Color (3, 84, 0), 
					fireBoxX, fireBoxY + boxHeight, new Color (4, 127, 11)));
			clickFire = false;
		}
		g.fillRect(fireBoxX, fireBoxY, fireBoxLength, boxHeight);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.setColor(Color.black);
		g.drawString("FIRE", fireBoxX + fireBoxLength/2 - 55, fireBoxY + 60);
	}

	public static boolean getClickFire () { return clickFire; }
	
	public static void setInMapScroller (int mouseX, int mouseY, int centerY, int centerX1, int centerX2) {
		if (mouseY > centerY - weaponScrollerHeight/2 && mouseY < centerY +weaponScrollerHeight/2) {
			if (mouseX > centerX1 - weaponScrollerLength/2 && mouseX < centerX1 + weaponScrollerLength/2) {
				inWeaponChangerLeft = true;
				inWeaponChangerRight = false;
			}
			else if (mouseX > centerX2 - weaponScrollerLength/2 && mouseX < centerX2 + weaponScrollerLength/2) {
				inWeaponChangerLeft = false;
				inWeaponChangerRight = true;
			}
			else {
				inWeaponChangerLeft = false;
				inWeaponChangerRight = false;
			}
		}
		else {
			inWeaponChangerLeft = false;
			inWeaponChangerRight = false;
		}
	}
}
