package tankermanz;

public class ExplosiveBulletProjectile extends Projectile {

	public ExplosiveBulletProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=8;
		damage = 25;
		explosion = 45;
		projectileID = EXPLOSIVE_BULLET_PROJECTILE;
	}

}
