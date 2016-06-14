package tankermanz;

public class FountainProjectile extends Projectile {
boolean activated;
	public FountainProjectile(double x, double y, int power, double angle, boolean activated) {
		super(x, y, power, angle);
		radius = 5;
		damage = 5;
		explosion = 5;
		this.activated = activated;
		projectileID = FOUNTAIN_PROJECTILE;
	}

}
