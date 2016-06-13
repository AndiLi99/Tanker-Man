package tankermanz;

public class BigBulletProjectile extends Projectile {

	public BigBulletProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 15;
		explosion = 35;
		projectileID = BULLET_PROJECTILE;
	}

}
