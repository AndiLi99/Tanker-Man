package tankermanz;

public class ArmageddonProjectile extends Projectile {

	public ArmageddonProjectile(double x, double y, int power, double angle) {
		super(x, y, power, angle);
		radius=5;
		damage = 55;
		explosion = 125;
		projectileID = ARMAGEDDON_PROJECTILE;
	}

}
