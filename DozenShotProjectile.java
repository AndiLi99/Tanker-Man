package tankermanz;

public class DozenShotProjectile extends Projectile {
	public DozenShotProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 10;
		explosion = 15;
		projectileID = DOZEN_SHOT_PROJECTILE;
	}
}
