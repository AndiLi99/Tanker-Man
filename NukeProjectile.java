package tankermanz;

public class NukeProjectile extends Projectile {

	public NukeProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 25;
		explosion = 50;
		projectileID = NUKE_PROJECTILE;
	}

}
