package tankermanz;

public class SniperProjectile extends Projectile {

	public SniperProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 30;
		explosion = 5;
		projectileID = SNIPER_PROJECTILE;
	}

}
