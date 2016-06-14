package tankermanz;

public class TrackerProjectile extends Projectile{
boolean activated;
	public TrackerProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=8;
		damage = 10;
		explosion = 25;
		projectileID = TRACKER_PROJECTILE;
		activated = false;
	}
	public void activate() {
		activated = true;		
	}
	

}
