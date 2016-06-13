package tankermanz;

public class TrackerProjectile extends Projectile{

	public TrackerProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=8;
		damage = 10;
		explosion = 25;
		projectileID = TRACKER_PROJECTILE;
	}

}
