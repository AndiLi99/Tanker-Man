package tankermanz;

public class NukeProjectile extends Projectile {

	public NukeProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		damage = 25;
		explosion = 50;
		projectileID = NUKE_PROJECTILE;
	}

}
