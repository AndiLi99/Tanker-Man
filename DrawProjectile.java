package tankermanz;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawProjectile {
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

	public static void drawProjectile (Graphics g, int projectileID, int x, int y) {
		if (projectileID == Projectile.BULLET_PROJECTILE)
			drawBullet(g, x, y);
		else if (projectileID == Projectile.BIG_BULLET_PROJECTILE)
			drawBigBullet(g, x, y);
		else if (projectileID == Projectile.EXPLOSIVE_BULLET_PROJECTILE)
			drawExplosiveBullet(g, x, y);
		else if (projectileID == Projectile.SPRAY_PROJECTILE)
			drawSpray(g, x, y);
		else if (projectileID == Projectile.TRIPLE_SHOT_PROJECTILE)
			drawTripleShot(g, x, y);
		else if (projectileID == Projectile.DOZEN_SHOT_PROJECTILE)
			drawDozenShot(g, x, y);
		else if (projectileID == Projectile.AIR_STRIKE_PROJECTILE)
			drawAirStrike(g, x, y);
		else if (projectileID == Projectile.SPLITTER_PROJECTILE)
			drawSpliter(g, x, y);
		else if (projectileID == Projectile.BREAKER_PROJECTILE)
			drawBreaker(g, x, y);
		else if (projectileID == Projectile.TRACKER_PROJECTILE)
			drawTracker(g, x, y);
		else if (projectileID == Projectile.HORIZON_PROJECTILE)
			drawHorizon(g, x, y);
		else if (projectileID == Projectile.FLOWER_PROJECTILE)
			drawFlower(g, x, y);
		else if (projectileID == Projectile.STREAM_PROJECTILE)
			drawStream(g, x, y);
		else if (projectileID == Projectile.SNIPER_PROJECTILE)
			drawSniper(g, x, y);
		else if (projectileID == Projectile.NUKE_PROJECTILE)
			drawNuke(g, x, y);
		else if (projectileID == Projectile.ARMAGEDDON_PROJECTILE)
			drawArmgeddon(g, x, y);
		else if (projectileID == Projectile.FOUNTAIN_PROJECTILE)
			drawFountain(g, x, y);
	}

	private static void drawTracker(Graphics g, int x, int y) {
		int bulletRadius = 6;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + bulletRadius, new Color (51, 0, 51), 
				0, y - bulletRadius, new Color (217, 0, 255)));
		g.fillOval(x-bulletRadius, y-bulletRadius, 2*bulletRadius, 2*bulletRadius);
	}

	private static void drawBullet (Graphics g, int x, int y) {
		int bulletRadius = 3;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + bulletRadius, new Color (53, 53, 53), 
				0, y - bulletRadius, new Color (125, 125, 125)));
		g.fillOval(x-bulletRadius, y-bulletRadius, 2*bulletRadius, 2*bulletRadius);
	}

	private static void drawBigBullet (Graphics g, int x, int y) {
		int bigBulletRadius = 7;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + bigBulletRadius, new Color (53, 53, 53), 
				0, y - bigBulletRadius, new Color (125, 125, 125)));
		g.fillOval(x-bigBulletRadius, y-bigBulletRadius, 2*bigBulletRadius, 2*bigBulletRadius);
	}

	private static void drawSpray (Graphics g, int x, int y) {
		int sprayRadius = 2;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + sprayRadius, new Color (0, 74, 105), 
				0, y - sprayRadius, new Color (2, 115, 164)));
		g.fillOval(x-sprayRadius, y-sprayRadius, 2*sprayRadius, 2*sprayRadius);
	}

	private static void drawTripleShot (Graphics g, int x, int y) {
		int tripleShotRadius = 6;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + tripleShotRadius, new Color (74, 74, 74), 
				0, y - tripleShotRadius, new Color (90, 90, 90)));
		g.fillOval(x-tripleShotRadius, y-tripleShotRadius, 2*tripleShotRadius, 2*tripleShotRadius);
	}

	private static void drawDozenShot (Graphics g, int x, int y) {
		int dozenShotRadius = 6;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + dozenShotRadius, new Color (74, 74, 74), 
				0, y - dozenShotRadius, new Color (90, 90, 90)));
		g.fillOval(x-dozenShotRadius, y-dozenShotRadius, 2*dozenShotRadius, 2*dozenShotRadius);
	}

	private static void drawSpliter (Graphics g, int x, int y) {
		int spliterRadius = 6;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + spliterRadius, new Color (219, 183, 0), 
				0, y - spliterRadius, new Color (225, 213, 0)));
		g.fillOval(x-spliterRadius, y-spliterRadius, 2*spliterRadius, 2*spliterRadius);
	}

	private static void drawBreaker (Graphics g, int x, int y) {
		int breakerRadius = 6;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + breakerRadius, new Color (0, 102, 0), 
				0, y - breakerRadius, new Color (25, 255, 5)));
		g.fillOval(x-breakerRadius, y-breakerRadius, 2*breakerRadius, 2*breakerRadius);
	}

	private static void drawHorizon (Graphics g, int x, int y) {
		int horizonRadius = 6;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + horizonRadius, new Color (239, 24, 24), 
				0, y - horizonRadius, new Color (255, 255, 255)));
		g.fillOval(x-horizonRadius, y-horizonRadius, 2*horizonRadius, 2*horizonRadius);
	}

	private static  void drawFlower (Graphics g, int x, int y) {
		int flowerRadius = 5;
		g.setColor(new Color (247, 255, 0));
		g.fillOval(x-flowerRadius, y-flowerRadius, 2*flowerRadius, 2*flowerRadius);	
	}

	private static void drawNuke (Graphics g, int x, int y) {
		int nukeRadius = 7;
		g.setColor(new Color (102, 102, 0));
		g.fillOval(x-nukeRadius, y-nukeRadius, 2*nukeRadius, 2*nukeRadius);	
	}

	private static void drawArmgeddon (Graphics g, int x, int y) {
		int armegeddonRadius = 7;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + armegeddonRadius, new Color (51, 51, 0), 
				0, y - armegeddonRadius, new Color (255, 218, 0)));
		g.fillOval(x-armegeddonRadius, y-armegeddonRadius, 2*armegeddonRadius, 2*armegeddonRadius);	
	}

	private static void drawExplosiveBullet (Graphics g, int x, int y) {
		int explosiveBulletRadius = 9;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + explosiveBulletRadius, new Color (53, 53, 53), 
				0, y - explosiveBulletRadius, new Color (162, 60, 60)));
		g.fillOval(x-explosiveBulletRadius, y-explosiveBulletRadius, 2*explosiveBulletRadius, 2*explosiveBulletRadius);
	}
	
	private static void drawStream(Graphics g, int x, int y) {
		int streamRadius = 3;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + streamRadius, new Color (43, 0, 255), 
				0, y - streamRadius, new Color (0, 196, 255)));
		g.fillOval(x-streamRadius, y-streamRadius, 2*streamRadius, 2*streamRadius);	
	}
	
	private static void drawFountain(Graphics g, int x, int y) {
		int streamRadius = 3;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, y + streamRadius, new Color (43, 0, 255), 
				0, y - streamRadius, new Color (0, 196, 255)));
		g.fillOval(x-streamRadius, y-streamRadius, 2*streamRadius, 2*streamRadius);
		
	}
	
	private static void drawSniper (Graphics g, int x, int y) {
		int sniperRadius = 1;
		g.setColor(Color.white);
		g.fillOval(x - sniperRadius, y - sniperRadius, 2*sniperRadius, 2*sniperRadius);
	}
	private static void drawAirStrike (Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		int airStrikeOuterRadius = 5;
		g2.setColor(new Color (255, 0, 0));
		g.fillOval(x-airStrikeOuterRadius, y-airStrikeOuterRadius, 2*airStrikeOuterRadius, 2*airStrikeOuterRadius);
		
		int airStrikeInnerRadius = 3;
		g2.setColor(Color.white);
		g.fillOval(x-airStrikeInnerRadius, y-airStrikeInnerRadius, 2*airStrikeInnerRadius, 2*airStrikeInnerRadius);	
	}
}