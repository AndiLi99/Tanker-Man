package tankermanz;

public class TripleShotProjectile extends Projectile {

	public TripleShotProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 10;
		explosion = 25;
		projectileID = TRIPLE_SHOT_PROJECTILE;
	}

}
