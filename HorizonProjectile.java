package tankermanz;

public class HorizonProjectile extends Projectile {

	public HorizonProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 10;
		explosion = 15;
		projectileID = HORIZON_PROJECTILE;
	}

}
