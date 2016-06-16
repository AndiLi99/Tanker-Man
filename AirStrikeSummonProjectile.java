package tankermanz;

public class AirStrikeSummonProjectile extends Projectile {
	public AirStrikeSummonProjectile (Terrain terrain, double x, double y, int power, double angle){
		super(terrain, x, y, power, angle);
		damage = 0;
		explosion = 10;
		projectileID = AIR_STRIKE_PROJECTILE;
	}
}
