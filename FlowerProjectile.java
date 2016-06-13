package tankermanz;

public class FlowerProjectile extends Projectile {

	public FlowerProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 10;
		explosion = 25;
		projectileID = FLOWER_PROJECTILE;
	}

}
