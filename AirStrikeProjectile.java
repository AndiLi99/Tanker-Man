package tankermanz;

public class AirStrikeProjectile extends Projectile {
	
	public AirStrikeProjectile (Terrain terrain, double x, double y, int power, double angle, int delay){
		super(terrain, x, y, power, angle);
		damage = 25;
		explosion = 25;
		projectileID = BULLET_PROJECTILE;
		this.delay = delay;
	}
}
