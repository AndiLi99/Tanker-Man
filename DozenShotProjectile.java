package tankermanz;

public class DozenShotProjectile extends Projectile {

	public DozenShotProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 10;
		explosion = 15;
		projectileID = DOZEN_SHOT_PROJECTILE;
	}

}
