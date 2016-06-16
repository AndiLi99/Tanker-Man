package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class DrawWeaponChooser {
	static Color spriteBoxLeft = null;
	static Color spriteBoxRight = null;
	static Color toGrey = null;
	static Color darkGrey = null;
	static Color toWhite = null;
	static Color white = null;

	public static void yellow () {
		spriteBoxLeft = new Color (80, 80, 0);
		spriteBoxRight = new Color (154, 154, 0);
		toGrey = new Color (60, 60, 53);
		darkGrey = new Color (30, 30, 30);
		toWhite = new Color (225, 225, 166);
		white = new Color (180, 180, 180);
	}
	public static void red () {
		spriteBoxLeft = new Color (102, 0, 0);
		spriteBoxRight = new Color (204, 0, 0);
		toGrey = new Color (71, 52, 52);
		darkGrey = new Color (30, 30, 30);
		toWhite = new Color (225, 213, 213);
		white = new Color (180, 180, 180);
	}
	public static void purple () {
		spriteBoxLeft = new Color (60, 0, 60);
		spriteBoxRight = new Color (94, 0, 94);
		toGrey = new Color (57, 42, 57);
		darkGrey = new Color (30, 30, 30);
		toWhite = new Color (225, 205, 225);
		white = new Color (180, 180, 180);
	}
	public static void green () {
		spriteBoxLeft = new Color (0, 50, 0);
		spriteBoxRight = new Color (0, 80, 0);
		toGrey = new Color (38, 54, 38);
		darkGrey = new Color (30, 30, 30);
		toWhite = new Color (200, 225, 200);
		white = new Color (180, 180, 180);
	}
	public static void blue () {
		spriteBoxLeft = new Color (0, 0, 51);
		spriteBoxRight = new Color (0, 0, 204);
		toGrey = new Color (55, 63, 80);
		darkGrey = new Color (30, 30, 30);
		toWhite = new Color (176, 203, 255);
		white = new Color (180, 180, 180);
	}
	public static void grey () {
		spriteBoxLeft = new Color (40, 40, 40);
		spriteBoxRight = new Color (55, 55, 55);
		toGrey = new Color (70, 70, 70);
		darkGrey = new Color (30, 30, 30);
		toWhite = new Color (156, 156, 156);
		white = new Color (180, 180, 180);
	}

	public static void drawWeaponChooser (Graphics g, int projectileID, int X, int Y, int boxLength, int boxHeight) {
		int spriteBoxLength = boxLength/5;

		/* Bullet - Grey
		 * Big Bullet - Grey
		 * Explosive Bullet - Grey
		 * Spray - Blue
		 * Triple shot - Grey
		 * Dozen Shot - Grey
		 * Air Strike - Red
		 * Splitter - Yellow
		 * Breaker - Green
		 * Tracker - Purple
		 * Horizon - Red
		 * Flower - Yellow
		 * Stream - Blue
		 * Sniper - Red
		 * Nuke - Yellow
		 * Armegeddon - Yellow
		 * 
		 */

		// Set background of weapon box chooser
		if (projectileID == Projectile.BULLET_PROJECTILE || projectileID == Projectile.BIG_BULLET_PROJECTILE || 
				projectileID == Projectile.EXPLOSIVE_BULLET_PROJECTILE || projectileID == Projectile.TRIPLE_SHOT_PROJECTILE ||
				projectileID == Projectile.DOZEN_SHOT_PROJECTILE)
			grey();
		else if (projectileID == Projectile.SPRAY_PROJECTILE || projectileID == Projectile.STREAM_PROJECTILE ||
				projectileID == Projectile.FOUNTAIN_PROJECTILE)
			blue();
		else if (projectileID == Projectile.AIR_STRIKE_PROJECTILE || projectileID == Projectile.HORIZON_PROJECTILE || 
				projectileID == Projectile.SNIPER_PROJECTILE)
			red();
		else if (projectileID == Projectile.BREAKER_PROJECTILE)
			green();
		else if (projectileID == Projectile.SPLITTER_PROJECTILE || projectileID == Projectile.FLOWER_PROJECTILE ||
				projectileID == Projectile.NUKE_PROJECTILE || projectileID == Projectile.ARMAGEDDON_PROJECTILE)
			yellow();
		else if (projectileID == Projectile.TRACKER_PROJECTILE)
			purple();

		// Draws background for box
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(X + spriteBoxLength, Y, toGrey, X+2*spriteBoxLength, Y, darkGrey));
		g2.fillRect(X, Y, boxLength, boxHeight);

		// Draws white line for box
		g2.setPaint(new GradientPaint(X + spriteBoxLength, Y, toWhite, X+2*spriteBoxLength, Y, white));
		g2.fillRect(X, Y + 2*boxHeight/5, boxLength, boxHeight/10);

		g.setFont(new Font ("Arial", Font.BOLD, 15));
		g.setColor(Color.white);
		g.drawString(Tank.weaponNames[projectileID], X + spriteBoxLength + 5, Y + 8*boxHeight/9);
		g.drawString(String.valueOf(Terrain.getCurrentPlayer().getCurrentWeaponAmmo()),  X + spriteBoxLength + 145, Y + 8*boxHeight/9);
		drawSpriteBox(g, projectileID, X, Y, spriteBoxLength, boxHeight);
	}

	public static void drawSpriteBox (Graphics g, int projectileID, int X, int Y, int spriteBoxLength, int boxHeight) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(X, Y, spriteBoxLeft, X+spriteBoxLength, Y + boxHeight, spriteBoxRight));
		g2.fillRect(X, Y, spriteBoxLength, boxHeight);

		/*
		 * BULLET_PROJECTILE = 0;
		 * BIG_BULLET_PROJECTILE = 1;
		 * EXPLOSIVE_BULLET_PROJECTILE = 2;
		 * SPRAY_PROJECTILE = 3;
		 * TRIPLE_SHOT_PROJECTILE = 4;
		 * DOZEN_SHOT_PROJECTILE = 5;
		 * AIR_STRIKE_PROJECTILE = 6;
		 * SPLITTER_PROJECTILE = 7;
		 * BREAKER_PROJECTILE = 8;
		 * TRACKER_PROJECTILE = 9;
		 * HORIZON_PROJECTILE = 10;
		 * FLOWER_PROJECTILE = 11;
		 * STREAM_PROJECTILE = 12;
		 * SNIPER_PROJECTILE = 13;
		 * NUKE_PROJECTILE = 14;
		 * ARMAGEDDON_PROJECTILE = 15;
		 */

		// Draw Sprite
		if (projectileID == Projectile.BULLET_PROJECTILE)
			spriteBullet(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.BIG_BULLET_PROJECTILE)
			spriteBigBullet(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.EXPLOSIVE_BULLET_PROJECTILE)
			spriteExplosiveBullet(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.SPRAY_PROJECTILE)
			spriteSpray(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.TRIPLE_SHOT_PROJECTILE)
			spriteTripleShot(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.DOZEN_SHOT_PROJECTILE)
			spriteDozenShot(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.AIR_STRIKE_PROJECTILE)
			spriteAirStrike(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.SPLITTER_PROJECTILE)
			spriteSplitter(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.BREAKER_PROJECTILE)
			spriteBreaker(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.TRACKER_PROJECTILE)
			spriteTracker(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.HORIZON_PROJECTILE)
			spriteHorizon(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.FLOWER_PROJECTILE)
			spriteFlower(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.STREAM_PROJECTILE)
			spriteStream(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.SNIPER_PROJECTILE)
			spriteSniper(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.NUKE_PROJECTILE)
			spriteNuke(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.ARMAGEDDON_PROJECTILE)
			spriteArmegeddon(g, X, Y, spriteBoxLength, boxHeight);
		else if (projectileID == Projectile.FOUNTAIN_PROJECTILE)
			spriteFountain(g2, X, Y, spriteBoxLength, boxHeight);
	}

	private static void spriteBullet (Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 7;
		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Draw bullet
		g.setColor(Color.gray);
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2);
	}
	private static void spriteBigBullet(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 10;
		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Draw bullet
		g.setColor(Color.gray);
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2);
	}
	private static void spriteExplosiveBullet(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 12;
		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Draw bullet
		g.setColor(Color.gray);
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2);
	}
	private static void spriteSpray(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 4;
		// coordinates kind of random to match the projectile
		int ballX1 = X + spriteBoxLength/2; int ballY1 = Y + boxHeight/3;
		int ballX2 = X + 3*spriteBoxLength/5; int ballY2 = Y + boxHeight/7;
		int ballX3 = X + spriteBoxLength/7; int ballY3 = Y + 4*boxHeight/5;
		int ballX4 = X + 3*spriteBoxLength/7; int ballY4 = Y + 3*boxHeight/5;
		int ballX5 = X + 6*spriteBoxLength/7; int ballY5 = Y + boxHeight/4;

		// Draw circles
		g.setColor(new Color (155, 255, 255));
		g.fillOval(ballX1-radius, ballY1-radius, 2*radius, 2*radius);
		g.fillOval(ballX2-radius, ballY2-radius, 2*radius, 2*radius);
		g.fillOval(ballX3-radius, ballY3-radius, 2*radius, 2*radius);
		g.fillOval(ballX4-radius, ballY4-radius, 2*radius, 2*radius);
		g.fillOval(ballX5-radius, ballY5-radius, 2*radius, 2*radius);
	}
	private static void spriteFountain (Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;
		int ballX1 = X + spriteBoxLength/4;
		int ballX2 = X + 3*spriteBoxLength/4;
		int ballY = Y + boxHeight/4;

		// Draw trail
		g.setColor(new Color (77,125,220));
		int [] triangleX1 = { X + spriteBoxLength/2, ballX1 - radius, ballX1 + radius };
		int [] triangleY = { Y + 7*boxHeight/8, ballY, ballY };
		g.fillPolygon(triangleX1, triangleY,3);

		int [] triangleX2 = { X + spriteBoxLength/2, ballX2 - radius, ballX2 + radius};
		g.fillPolygon(triangleX2, triangleY,3);

		// Draw balls
		g.setColor(new Color  (165,232,255));
		g.fillOval(ballX1-radius, ballY-radius, 2*radius, 2*radius);
		g.fillOval(ballX2-radius, ballY-radius, 2*radius, 2*radius);
	}
	private static void spriteTripleShot (Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int ballRadius = 6;
		int centerY1 = Y + boxHeight/3;
		int centerY2 = centerY1 + boxHeight/3;

		int centerXtop = X + spriteBoxLength/2;
		int centerX1bottom = X + spriteBoxLength/3;
		int centerX2bottom = centerX1bottom + spriteBoxLength/3;

		// Light grey
		g.setColor(new Color (191, 191, 191));

		// Draw circles in triangular shape
		g.fillOval(centerXtop - ballRadius, centerY1 - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX1bottom - ballRadius, centerY2 - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX2bottom - ballRadius, centerY2 - ballRadius, ballRadius*2, ballRadius*2);
	}
	private static void spriteDozenShot(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int ballRadius = 4;

		int centerX1top = X + spriteBoxLength/4;
		int centerX2top = X + spriteBoxLength/2;
		int centerX3top = X + 3*spriteBoxLength/4;
		int centerX1bottom = X + spriteBoxLength/3;
		int centerX2bottom = centerX1bottom + spriteBoxLength/3;

		int centerY1 = Y + boxHeight/4;
		int centerY =  Y + boxHeight/2;
		int centerY2 = Y + 3*boxHeight/4;

		// Light grey
		g.setColor(new Color (191, 191, 191));

		// Draw circles in triangular shape
		g.fillOval(centerX1bottom - ballRadius, centerY1 - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX2bottom - ballRadius, centerY1 - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX1top - ballRadius, centerY - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX2top - ballRadius, centerY - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX3top - ballRadius, centerY - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX1bottom - ballRadius, centerY2 - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX2bottom - ballRadius, centerY2 - ballRadius, ballRadius*2, ballRadius*2);
	}
	private static void spriteAirStrike(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int ballRadius = 4;

		int centerX1top = X + spriteBoxLength/4;
		int centerX2top = X + spriteBoxLength/2;
		int centerX3top = X + 3*spriteBoxLength/4;

		int bottomY = Y + 3*boxHeight/4;

		int tailHeight = boxHeight/2;
		int topY = Y + boxHeight/4;
		Graphics2D g2 = (Graphics2D) g;

		// Draw tail of strike
		g2.setPaint(new GradientPaint(0, topY, new Color (125, 0, 0), 
				0, topY + tailHeight/2, new Color (240, 120, 120)));
		g.fillRect(centerX1top - ballRadius, topY, ballRadius*2, tailHeight);
		g.fillRect(centerX2top - ballRadius, topY, ballRadius*2, tailHeight);
		g.fillRect(centerX3top - ballRadius, topY, ballRadius*2, tailHeight);

		// Light grey
		g.setColor(new Color (191, 191, 191));

		// Draw circles
		g.fillOval(centerX1top - ballRadius, bottomY - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX2top - ballRadius, bottomY - ballRadius, ballRadius*2, ballRadius*2);
		g.fillOval(centerX3top - ballRadius, bottomY - ballRadius, ballRadius*2, ballRadius*2);

	}
	private static void spriteSplitter(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;
		int ballX1 = X + spriteBoxLength/2;
		int ballY1 = Y + 3*boxHeight/4;

		int ballX2 = X + 3*spriteBoxLength/4;
		int ballY2 = Y + boxHeight/2;

		// Draw Trail of balls
		g.setColor(new Color (212,220,96));
		int [] triangleX1 = { X + spriteBoxLength/8, ballX1 - radius, ballX1 + radius };
		int [] triangleY1 = { Y + boxHeight/8, ballY1, ballY1 };
		g.fillPolygon(triangleX1, triangleY1,3);

		int [] triangleX2 = { X + spriteBoxLength/8, ballX2, ballX2 };
		int [] triangleY2 = { Y + boxHeight/8, ballY2 - radius, ballY2 + radius};
		g.fillPolygon(triangleX2, triangleY2,3);

		// Draw balls
		g.setColor(new Color (255, 255, 179));
		g.fillOval(ballX1-radius, ballY1-radius, 2*radius, 2*radius);
		g.fillOval(ballX2-radius, ballY2-radius, 2*radius, 2*radius);
	}
	private static void spriteBreaker(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;
		int ballX1 = X + spriteBoxLength/4;
		int ballX2 = X + 3*spriteBoxLength/4;
		int ballY = Y + boxHeight/4;

		// Draw trail
		g.setColor(new Color (44,174,53));
		int [] triangleX1 = { X + spriteBoxLength/2, ballX1 - radius, ballX1 + radius };
		int [] triangleY = { Y + 7*boxHeight/8, ballY, ballY };
		g.fillPolygon(triangleX1, triangleY,3);

		int [] triangleX2 = { X + spriteBoxLength/2, ballX2 - radius, ballX2 + radius};
		g.fillPolygon(triangleX2, triangleY,3);

		// Draw balls
		g.setColor(new Color (179, 255, 180));
		g.fillOval(ballX1-radius, ballY-radius, 2*radius, 2*radius);
		g.fillOval(ballX2-radius, ballY-radius, 2*radius, 2*radius);
	}
	private static void spriteTracker(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;
		int bottomX = X + 3*spriteBoxLength/4;
		int bottomY = Y + 3*boxHeight/4;

		// Draw Trail of ball
		g.setColor(new Color (153,51,255));
		int [] triangleX = { X + spriteBoxLength/4, bottomX - radius, bottomX + radius };
		int [] triangleY = { Y + boxHeight/4, bottomY, bottomY };
		g.fillPolygon(triangleX, triangleY,3);

		// Draw ball
		g.setColor(new Color (204, 153, 255));
		g.fillOval(bottomX-radius, bottomY-radius, 2*radius, 2*radius);
	}
	private static void spriteHorizon(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;
		int X1 = X + spriteBoxLength/6;
		int X2 = X + 2*spriteBoxLength/6;
		int centerX = X + spriteBoxLength/2;
		int X4 = X + 4*spriteBoxLength/6;
		int X5 = X + 5*spriteBoxLength/6;
		int y = Y + 3*boxHeight/4;

		// Draw white circles
		g.setColor(Color.lightGray);
		g.fillOval(X2 - radius, y - radius, radius*2, radius*2);
		g.fillOval(X4 - radius, y - radius, radius*2, radius*2);

		// Red Circles
		g.setColor(new Color(255,85,85));
		g.fillOval(X1 - radius, y - radius, radius*2, radius*2);
		g.fillOval(centerX - radius, y - radius, radius*2, radius*2);
		g.fillOval(X5 - radius, y - radius, radius*2, radius*2);
	}
	private static void spriteFlower(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;

		int leftX = X + spriteBoxLength/4;
		int rightX = X + 3*spriteBoxLength/4;
		int middleX = X + spriteBoxLength/2;
		int topY = Y + boxHeight/4;
		int middleY = Y + boxHeight/2;
		int bottomY = Y + 3*boxHeight/4;

		// Draw white petals
		g.setColor(new Color (255, 251, 202));
		g.fillOval((leftX+middleX)/2 - 4*radius/3, (topY+middleY)/2 - 4*radius/3, radius*2, radius*2);
		g.fillOval((rightX+middleX)/2 - 2*radius/3, (topY+middleY)/2 - 4*radius/3, radius*2, radius*2);
		g.fillOval((leftX+middleX)/2 - 4*radius/3, (bottomY+middleY)/2 - 2*radius/3, radius*2, radius*2);
		g.fillOval((rightX+middleX)/2 - 2*radius/3, (bottomY+middleY)/2 - 2*radius/3, radius*2, radius*2);

		// Draw yellow petals
		g.setColor(new Color (235, 255, 90));
		g.fillOval(leftX - radius, middleY - radius, radius*2, radius*2);
		g.fillOval(rightX - radius, middleY - radius, radius*2, radius*2);
		g.fillOval(middleX - radius, topY - radius, radius*2, radius*2);
		g.fillOval(middleX - radius, bottomY - radius, radius*2, radius*2);
	}
	private static void spriteStream(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 4;

		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Other ball center coord's
		int leftX = X + spriteBoxLength/4;
		int rightX = X + 3*spriteBoxLength/4;
		int leftY = Y + 3*boxHeight/4;
		int rightY = Y + boxHeight/4;

		// Draw circles
		g.setColor(new Color (155, 255, 255));
		g.fillOval(leftX - radius, leftY - radius, radius*2, radius*2);
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2);
		g.fillOval(rightX - radius, rightY - radius, radius*2, radius*2);
	}
	private static void spriteSniper(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 15;
		int lineThickness = 2;

		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Draw white circle
		g.setColor(Color.white);
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2);

		// Draw red circle to make it an outline
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(new GradientPaint(X, Y, spriteBoxLeft, X+spriteBoxLength, Y + boxHeight, spriteBoxRight));
		g.fillOval(centerX - radius + lineThickness, centerY-radius+lineThickness, 2*radius - 2*lineThickness, 2*radius - 2*lineThickness);

		// Draw white rectangles (crossing lines)
		g.setColor(Color.white);
		g.fillRect(centerX - lineThickness/2, centerY-radius, lineThickness, 2*radius);
		g.fillRect(centerX - radius, centerY - lineThickness/2, 2*radius, lineThickness);
	}
	private static void spriteNuke(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int radius = 5;
		int radiusOuterCircle = 8;
		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Draw triangles (sides) of nuke logo
		int [] triangleX = { centerX, centerX + 3*radius/2, centerX - 3*radius/2 };
		int [] triangleY = { centerY, centerY - 3*boxHeight/5 + 2*radius, centerY - 3*boxHeight/5 + 2*radius };

		g.setColor(new Color (255, 255, 179));
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		g2.fillPolygon(triangleX, triangleY, 3);
		g2.rotate(120*Terrain.RADS, centerX, centerY);
		g2.fillPolygon(triangleX, triangleY, 3);
		g2.rotate(120*Terrain.RADS, centerX, centerY);
		g2.fillPolygon(triangleX, triangleY, 3);
		g2.setTransform(resetForm);

		// Draw circle in center of nuke
		g2.setPaint(new GradientPaint(X, Y, spriteBoxLeft, X+spriteBoxLength, Y + boxHeight, spriteBoxRight));
		g.fillOval(centerX - radiusOuterCircle, centerY - radiusOuterCircle, radiusOuterCircle*2, radiusOuterCircle*2);
		g.setColor(new Color (255, 255, 179));
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2);
	}
	private static void spriteArmegeddon(Graphics g, int X, int Y, int spriteBoxLength, int boxHeight) {
		int explosionWidth = 10;
		int explosionHeight = 30;

		int centerX = X + spriteBoxLength/2;
		int centerY = Y + boxHeight/2;

		// Draw stem of explosion
		// Darker yellow
		g.setColor(new Color (212,220,96));
		int [] triangleX = { centerX - explosionWidth/2 , centerX + explosionWidth/2, centerX };
		int [] triangleY = { centerY + explosionHeight/2, centerY + explosionHeight/2, centerY - explosionHeight/2 };
		g.fillPolygon(triangleX, triangleY, 3);

		int radius = 6;

		// Draw cloud for explosion
		int circleX1 = centerX + spriteBoxLength/10; int circleY1 = centerY;
		int circleX2 = centerX - spriteBoxLength/9; int circleY2 = centerY;
		int circleX3 = centerX + spriteBoxLength/10; int circleY3 = centerY - 4*radius/3;
		int circleX4 = centerX + spriteBoxLength/10 + 3*radius/4; int circleY4 = centerY - radius/2;
		int circleX5 = centerX - spriteBoxLength/9; int circleY5 = centerY - 4*radius/3;
		int circleX6 = centerX - spriteBoxLength/4; int circleY6 = centerY - radius/3;

		// Light yellow
		g.setColor(new Color (255, 255, 179));
		g.fillOval(circleX4 - radius, circleY4 - radius, 2*radius, 2*radius);
		// Darker yellow
		g.setColor(new Color (212,220,96));
		g.fillOval(circleX1 - radius, circleY1 - radius, 2*radius, 2*radius);
		g.fillOval(circleX5 - radius, circleY5 - radius, 2*radius, 2*radius);
		// Light yellow
		g.setColor(new Color (255, 255, 179));
		g.fillOval(circleX2 - radius, circleY2 - radius, 2*radius, 2*radius);
		g.fillOval(circleX3 - radius, circleY3 - radius, 2*radius, 2*radius);
		// Darker yellow
		g.setColor(new Color (212,220,96));
		g.fillOval(circleX6 - radius, circleY6 - radius, 2*radius, 2*radius);

	}
}
