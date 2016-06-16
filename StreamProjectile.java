package tankermanz;

public class StreamProjectile extends Projectile {

	public StreamProjectile(Terrain terrain, double x, double y, int power, double angle, int delay) {
		super(terrain, x, y, power, angle);
		damage = 5;
		explosion = 3;
		projectileID = STREAM_PROJECTILE;
		this.delay = delay;
	}

}
