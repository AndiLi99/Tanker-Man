package tankermanz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class DrawPowerAndAimer {
	public static void drawPowerAndAimer (Graphics g, int X, int Y, double aimAngle, int power) {
		double aimAngleRadians = aimAngle*Constants.RADS;
		int triangleBase = 20;
		
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		
		// Draw white circle outline to show max radius
		g.drawOval(X - Constants.AIM_RADIUS, Y - Constants.AIM_RADIUS - Constants.TANK_HEIGHT/2, 2*Constants.AIM_RADIUS, 2*Constants.AIM_RADIUS);
		
		// Draw triangle, proportional to max power and current power
		double powerRatio = power/(double)Constants.MAX_POWER;
		int [] triangleX1 = { X, (int)(X + Constants.AIM_RADIUS*powerRatio), (int)(X + Constants.AIM_RADIUS*powerRatio) };
		int [] triangleY1 = { Y - Constants.TANK_HEIGHT/2, (int)(Y + triangleBase/2*powerRatio), (int)(Y - triangleBase/2*powerRatio) };
		g2.rotate(aimAngleRadians, X, Y - Constants.TANK_HEIGHT/2);
		g2.setColor(new Color (102, 255, 255));
		g2.drawPolygon(triangleX1, triangleY1,3);
		g2.setTransform(resetForm);
	}
}
