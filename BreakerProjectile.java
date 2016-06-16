package tankermanz;

public class BreakerProjectile extends Projectile {
	boolean split;
	
	public BreakerProjectile(Terrain terrain, double x, double y, int power, double angle, boolean split) {
		super(terrain, x, y, power, angle);
		damage = 10;
		explosion = 25;
		projectileID = BREAKER_PROJECTILE;
	}
	
	public BreakerProjectile(Terrain terrain, double x, double y, int velocityX, boolean split) {
		super(terrain, x, y);
		damage = 10;
		explosion = 25;
		projectileID = BREAKER_PROJECTILE;
		this.split = split;
		this.velocityX = velocityX;
		this.velocityY = -100;
	}
}
