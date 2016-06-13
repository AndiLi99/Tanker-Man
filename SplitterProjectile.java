package tankermanz;

public class SplitterProjectile extends Projectile {
boolean split;

	public SplitterProjectile(double x, double y, int power, double angle, boolean split) {
		super(x, y, power, angle);
		radius=5;
		damage = 10;
		explosion = 25;
		projectileID = SPLITTER_PROJECTILE;
		this.split = split;
		
	}

	public SplitterProjectile(double x, double y, int velocityX, boolean split) {
		super(x, y);
		radius=5;
		damage = 10;
		explosion = 25;
		projectileID = SPLITTER_PROJECTILE;
		this.split = split;
		this.velocityX = velocityX;
		this.velocityY = 100;
	}

}
