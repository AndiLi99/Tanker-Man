package tankermanz;

public class ArmageddonProjectile extends Projectile {

	public ArmageddonProjectile(Terrain terrain, double x, double y, int power, double angle) {
		super(terrain, x, y, power, angle);
		radius=5;
		damage = 55;
		explosion = 125;
		projectileID = ARMAGEDDON_PROJECTILE;
	}

}
