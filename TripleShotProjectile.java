package tankermanz;

public class TripleShotProjectile extends Projectile {

	public TripleShotProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 10;
		explosion = 25;
		projectileID = TRIPLE_SHOT_PROJECTILE;
	}

}
