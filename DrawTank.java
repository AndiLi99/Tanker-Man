package tankermanz;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

//max
public class DrawTank {
	static Color tankBodyTop = null;
	static Color tankBodyBottom = null;
	static Color tankTrackTop = null;
	static Color tankTrackBottom = null;
	static Color tankWheelTop = null;
	static Color tankWheelBottom = null;
	static Color tankDarkAreas = null;
	static Color tankArmTop = null;
	static Color tankArmBottom = null;

	public static void colorGreen () {
		tankBodyTop = new Color (2,181,20);
		tankBodyBottom = new Color (5,70,7);
		tankTrackTop = new Color (2,181,20);
		tankTrackBottom = new Color (0,61,2);
		tankWheelTop = new Color (2,140,20);
		tankWheelBottom = new Color (0,61,2);
		tankDarkAreas = new Color (3,16,3);
		tankArmTop = new Color (2,181,20);
		tankArmBottom = new Color (15,57,15);
	}
	
	public static void colorPink () {
		tankBodyTop = new Color (198,0,138);
		tankBodyBottom = new Color (102,0,72);
		tankTrackTop = new Color (171,5,121);
		tankTrackBottom = new Color (81,0,57);
		tankWheelTop = new Color (118,2,83);
		tankWheelBottom = new Color (53,0,37);
		tankDarkAreas = new Color (30,0,21);
		tankArmTop = new Color (168,0,117);
		tankArmBottom = new Color (87,0,61);
	}
	
	public static void colorBlue () {
		tankBodyTop = new Color (0,114,228);
		tankBodyBottom = new Color (0,18,91);
		tankTrackTop = new Color (0,73,145);
		tankTrackBottom = new Color (0,34,68);
		tankWheelTop = new Color (0,29,145);
		tankWheelBottom = new Color (0,13,63);
		tankDarkAreas = new Color (0,5,23);
		tankArmTop = new Color (4,44,202);
		tankArmBottom = new Color (0,28,129);
	}

	public static void colorRed () {
		tankBodyTop = new Color (196,0,0);
		tankBodyBottom = new Color (95,0,0);
		tankTrackTop = new Color (161,0,0);
		tankTrackBottom = new Color (85,0,0);
		tankWheelTop = new Color (122,0,0);
		tankWheelBottom = new Color (71,0,0);
		tankDarkAreas = new Color (18,0,0);
		tankArmTop = new Color (107,0,0);
		tankArmBottom = new Color (67,0,0);
	}

	public static void colorGrey () {
		tankBodyTop = new Color (65,65,65);
		tankBodyBottom = new Color (40,40,40);
		tankTrackTop = new Color (60,60,60);
		tankTrackBottom = new Color (40,40,40);
		tankWheelTop = new Color (50,50,50);
		tankWheelBottom = new Color (25,25,25);
		tankDarkAreas = new Color (20,20,20);
		tankArmTop = new Color (40,40,40);
		tankArmBottom = new Color (30,30,30);
	}
	
	public static void drawDefaultTank (Graphics g, int tankX, int tankY, int height, int angle, int armAngle) {
		tankArm(g, tankX, tankY, height, armAngle);
		tankTopDefault(g, tankX, tankY, height, angle);
		tankTracksDefault(g, tankX, tankY, height, angle);
	}

	public static void drawMountainTank (Graphics g, int tankX, int tankY, int height, int angle, int armAngle) {
		tankArm(g, tankX, tankY, height, armAngle);
		tankTopMountains(g, tankX, tankY, height, angle);
		tankTracksDefault(g, tankX, tankY, height, angle);
	}
	
	public static void drawCircleTank (Graphics g, int tankX, int tankY, int height, int angle, int armAngle) {
		tankArm(g, tankX, tankY, height, armAngle);
		tankTopCircle(g, tankX, tankY, height, angle);
		tankTracksModern(g, tankX, tankY, height, angle);
	}
	
	public static void drawCustomTank (Graphics g, int tankX, int tankY, int height, int angle, int armAngle, int body, int tracks) {
		tankArm(g, tankX, tankY, height, armAngle);
		
		if (body == Constants.TANK_TOP_DEFAULT)
			tankTopDefault(g, tankX, tankY, height, angle);
		else if (body == Constants.TANK_TOP_MOUNTAIN)
			tankTopMountains(g, tankX, tankY, height, angle);
		else if (body == Constants.TANK_TOP_CIRCLE)
			tankTopCircle(g, tankX, tankY, height, angle);
		
		if (tracks == Constants.TANK_TRACK_DEFAULT)
			tankTracksDefault(g, tankX, tankY, height, angle);
		else if (tracks == Constants.TANK_TRACK_MODERN)
			tankTracksModern(g, tankX, tankY, height, angle);
	}

	public static void tankArm (Graphics g, int tankX, int tankY, int height, int armAngle) {
		double armRadians = armAngle*Constants.RADS;

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		// Draw Tank arm
		g2.setPaint(new GradientPaint(tankX, tankY, tankArmBottom, 
				tankX, tankY - height/2, tankArmTop));
		g2.rotate(armRadians, tankX, tankY - height/2);
		g2.fillRect(tankX - (int)Math.ceil(height/4.0)/2, tankY - height/2, (int)(height*1.3), (int)Math.ceil(height/4.0));
		g2.setTransform(resetForm);
	}

	public static void tankTopDefault (Graphics g, int tankX, int tankY, int height, int angle) {
		double radians = angle*Constants.RADS;
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();

		g2.setPaint(new GradientPaint(tankX, tankY - height/2, tankBodyBottom, tankX, tankY - height, tankBodyTop));
		g2.rotate(radians, tankX, tankY);
		// Draw body of tank
		g2.fillRect(tankX - height/2, tankY - height, height, height);

		// Draw sides of tank
		int [] xTriangle1 = { tankX - height/2, tankX - height/2, tankX - 3*height/4};
		int [] yTriangle1 = { tankY - height, tankY - height/2, tankY - height/2 };
		g2.fillPolygon(xTriangle1, yTriangle1, 3);

		int [] xTriangle2 = { tankX + height/2, tankX + height/2, tankX + 3*height/4};
		int [] yTriangle2 = { tankY - height, tankY - height/2, tankY - height/2 };

		g2.fillPolygon(xTriangle2, yTriangle2, 3);
		g2.setTransform(resetForm);
	}

	public static void tankTopMountains (Graphics g, int tankX, int tankY, int height, int angle) {
		double radians = angle*Constants.RADS;
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();

		g2.rotate(radians, tankX, tankY);

		// Draw behind top triangle
		g2.setPaint(new GradientPaint(tankX, tankY - height/2, tankBodyTop, tankX, tankY - height, tankBodyBottom));
		int [] xTriangle1 = { tankX + 3*height/4, tankX + height/2, tankX - 3*height/4 };
		int [] yTriangle1 = { tankY - height/2, tankY - height, tankY - height/2 };
		g2.fillPolygon(xTriangle1, yTriangle1, 3);

		// Draw front top triangle
		g2.setPaint(new GradientPaint(tankX, tankY - height/2, tankBodyBottom, tankX, tankY - height, tankBodyTop));
		int [] xTriangle2 = { tankX - 3*height/4, tankX - height/2, tankX + 3*height/4 };
		g2.fillPolygon(xTriangle2, yTriangle1, 3);

		g2.setTransform(resetForm);
	}
	public static void tankTopCircle (Graphics g, int tankX, int tankY, int height, int angle) {
		double radians = angle*Constants.RADS;
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();

		g2.rotate(radians, tankX, tankY);

		g2.setPaint(new GradientPaint(tankX, tankY - height/2, tankBodyBottom, tankX, tankY - height, tankBodyTop));
		int [] circleTopX = new int [2*(3*height/4)+1];
		int [] circleTopY = new int [2*(3*height/4)+1];

		int loopCount = 0;
		for (int a = tankX - 3*height/4; a <= tankX + 3*height/4; a++) {
			// Draw circle
			circleTopY[loopCount] = tankY - (int)Math.sqrt(9*height*height/16 - (tankX-a)*(tankX-a)) - height/4;
			circleTopY[loopCount] = circleTopY[loopCount];	// Make circle flatter
			circleTopX[loopCount] = a;
			loopCount++;
		}
		// Draw top
		g2.fillPolygon(circleTopX, circleTopY, circleTopX.length);

		g2.setTransform(resetForm);
	}
	public static void tankTracksDefault (Graphics g, int tankX, int tankY, int height, int angle) {
		double radians = angle*Constants.RADS;
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		g2.rotate(radians, tankX, tankY);
		// Draw tracks of tank
		g2.setPaint(new GradientPaint(tankX, tankY, tankTrackBottom, tankX, tankY - height/2, tankTrackTop));
		g2.fillRect(tankX - 3*height/4, tankY - height/2, 3*height/2, height/2);
		// Dark Part behind track
		g2.setColor(tankDarkAreas);
		g2.fillRect(tankX - 3*height/4, (int)(tankY - .8*height/2), 3*height/2, (int)(.6*height/2));
		// Draw circles for wheels
		g2.setPaint(new GradientPaint(tankX, tankY, tankWheelBottom, tankX, tankY - height/2, tankWheelTop));
		g2.fillOval(tankX - height, tankY - height/2, (int) Math.ceil(height/2.0), (int) Math.ceil(height/2.0));
		g2.fillOval(tankX + height/2, tankY - height/2, (int) Math.ceil(height/2.0), (int) Math.ceil(height/2.0));
		// Draw inner light green wheels
		g2.fillOval(tankX - (int)(.6*height/2)/2, (int)(tankY - .8*height/2), (int)(.6*height/2), (int)(.6*height/2));
		g2.fillOval(tankX -	(int)(.95*height/2), (int)(tankY - .8*height/2), (int)(.6*height/2), (int)(.6*height/2));
		g2.fillOval(tankX + (int)(.35*height/2), (int)(tankY - .8*height/2), (int)(.6*height/2), (int)(.6*height/2));
		// Dark Part on wheels
		g2.setColor(tankDarkAreas);
		g2.fillOval(tankX - (int)(.94*height), tankY - (int)(.87*height/2), (int) Math.ceil(.7*height/2.0), (int) Math.ceil(.7*height/2.0));
		g2.fillOval(tankX + (int)(1.15*height/2), tankY - (int)(.87*height/2), (int) Math.ceil(.7*height/2.0), (int) Math.ceil(.7*height/2.0));
		g2.fillOval(tankX + (int)(1.32*height/2), tankY - (int)(.71*height/2), (int) Math.ceil(.4*height/2.0), (int) Math.ceil(.4*height/2.0));

		// Light Part over dark part on wheels
		g2.setPaint(new GradientPaint(tankX, tankY, tankWheelBottom, tankX, tankY - height/2, tankWheelTop));
		g2.fillOval(tankX - (int)(.85*height), tankY - (int)(.71*height/2), (int) Math.ceil(.4*height/2.0), (int) Math.ceil(.4*height/2.0));
		g2.fillOval(tankX + (int)(1.32*height/2), tankY - (int)(.71*height/2), (int) Math.ceil(.4*height/2.0), (int) Math.ceil(.4*height/2.0));
		g2.setTransform(resetForm);
	}

	public static void tankTracksModern (Graphics g, int tankX, int tankY, int height, int angle) {
		double radians = angle*Constants.RADS;
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		g2.rotate(radians, tankX, tankY);
		// Draw tracks of tank
		g2.setPaint(new GradientPaint(tankX, tankY, tankTrackBottom, tankX, tankY - height/2, tankTrackTop));
		g2.fillRect(tankX - 3*height/4, tankY - height/2, 3*height/2, height/2);
		// Draw circles for wheels
		g2.fillOval(tankX - height, tankY - height/2, (int) Math.ceil(height/2.0), (int) Math.ceil(height/2.0));
		g2.fillOval(tankX + height/2, tankY - height/2, (int) Math.ceil(height/2.0), (int) Math.ceil(height/2.0));
		// Dark Part behind track
		g2.setColor(tankArmTop);
		g2.fillRect(tankX - 3*height/4, (int)(tankY - .8*height/2), 3*height/2, (int)(.6*height/2));
		// Dark Part on sides
		g2.fillOval(tankX - (int)(.88*height), tankY - (int)(.87*height/2), (int) Math.ceil(.6*height/2.0), (int) Math.ceil(.6*height/2.0));
		g2.fillOval(tankX + (int)(1.15*height/2), tankY - (int)(.87*height/2), (int) Math.ceil(.6*height/2.0), (int) Math.ceil(.6*height/2.0));

		g2.setTransform(resetForm);
	}


}