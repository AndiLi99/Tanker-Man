package tankermanz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class DrawPowerAndAimer {
	public static void drawPowerAndAimer (Graphics g, int X, int Y, double aimAngle, int power) {
		double aimAngleRadians = aimAngle*CONSTANTS.RADS;
		int triangleBase = 20;
		
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		
		// Draw white circle outline to show max radius
		g.drawOval(X - CONSTANTS.AIM_RADIUS, Y - CONSTANTS.AIM_RADIUS - CONSTANTS.TANK_HEIGHT/2, 2*CONSTANTS.AIM_RADIUS, 2*CONSTANTS.AIM_RADIUS);
		
		// Draw triangle, proportional to max power and current power
		double powerRatio = power/(double)CONSTANTS.MAX_POWER;
		int [] triangleX1 = { X, (int)(X + CONSTANTS.AIM_RADIUS*powerRatio), (int)(X + CONSTANTS.AIM_RADIUS*powerRatio) };
		int [] triangleY1 = { Y - CONSTANTS.TANK_HEIGHT/2, (int)(Y + triangleBase/2*powerRatio), (int)(Y - triangleBase/2*powerRatio) };
		g2.rotate(aimAngleRadians, X, Y - CONSTANTS.TANK_HEIGHT/2);
		g2.setColor(new Color (102, 255, 255));
		g2.drawPolygon(triangleX1, triangleY1,3);
		g2.setTransform(resetForm);
	}
}
