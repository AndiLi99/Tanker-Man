package tankermanz;

public class SprayProjectile extends Projectile {

	public SprayProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=3;
		damage = 7;
		explosion = 5;
		projectileID = SPRAY_PROJECTILE;
	}

}