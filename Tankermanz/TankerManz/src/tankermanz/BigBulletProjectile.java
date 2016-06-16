package tankermanz;

public class BigBulletProjectile extends Projectile {

	public BigBulletProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 15;
		explosion = 35;
		projectileID = BULLET_PROJECTILE;
	}
}
