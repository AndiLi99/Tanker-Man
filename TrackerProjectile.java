package tankermanz;

public class TrackerProjectile extends Projectile{
boolean activated;
	public TrackerProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 10;
		explosion = 25;
		projectileID = TRACKER_PROJECTILE;
		activated = false;
	}
	public void activate() {
		activated = true;		
	}
	

}
