package tankermanz;

public class FlowerProjectile extends Projectile {

	public FlowerProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 10;
		explosion = 25;
		projectileID = FLOWER_PROJECTILE;
	}

}
