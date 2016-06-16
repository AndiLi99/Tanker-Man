package tankermanz;

public class SprayProjectile extends Projectile {
	public SprayProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 12;
		explosion = 5;
		projectileID = SPRAY_PROJECTILE;
	}
}
