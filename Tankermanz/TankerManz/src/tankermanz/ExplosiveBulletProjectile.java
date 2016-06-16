package tankermanz;

public class ExplosiveBulletProjectile extends Projectile {

	public ExplosiveBulletProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 25;
		explosion = 45;
		projectileID = EXPLOSIVE_BULLET_PROJECTILE;
	}

}
