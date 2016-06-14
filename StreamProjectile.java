package tankermanz;

public class StreamProjectile extends Projectile {

	public StreamProjectile(double x, double y, int power, double angle, int delay) {
		super(x, y, power, angle);
		radius=3;
		damage = 5;
		explosion = 3;
		projectileID = STREAM_PROJECTILE;
		this.delay = delay;
	}

}
