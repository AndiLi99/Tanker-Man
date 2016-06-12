package tankermanz;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

//max
public class DrawTank {

	static final double RADS = 0.01745329251;

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
		tankArmTop = new Color (15,57,15);
		tankArmBottom = new Color (2,181,20);
	}

	public static void colorRed () {
		tankBodyTop = new Color (196,0,0);
		tankBodyBottom = new Color (95,0,0);
		tankTrackTop = new Color (161,0,0);
		tankTrackBottom = new Color (85,0,0);
		tankWheelTop = new Color (122,0,0);
		tankWheelBottom = new Color (71,0,0);
		tankDarkAreas = new Color (18,0,0);
		tankArmTop = new Color (67,0,0);
		tankArmBottom = new Color (107,0,0);
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

	public static void tankArm (Graphics g, int tankX, int tankY, int height, int armAngle) {
		double armRadians = armAngle*RADS;

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
		double radians = angle*RADS;
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
		double radians = angle*RADS;
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
		double radians = angle*RADS;
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
		double radians = angle*RADS;
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
		double radians = angle*RADS;
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
