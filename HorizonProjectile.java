package tankermanz;

public class HorizonProjectile extends Projectile {

	public HorizonProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 10;
		explosion = 15;
		projectileID = HORIZON_PROJECTILE;
	}

}
