package tankermanz;

public class AirStrikeProjectile extends Projectile {
	
	public AirStrikeProjectile (Terrain terrain, double x, double y, int power, double angle, int delay){
		super(terrain, x, y, power, angle);
		radius= 5;
		damage = 25;
		explosion = 25;
		projectileID = 0;
		this.delay = delay;
	}
	
	
	
}
