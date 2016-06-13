package tankermanz;

public class AirStrikeProjectile extends Projectile {
	
	public AirStrikeProjectile (double x, double y, int power, double angle){
		super(x, y, power, angle);
		radius= 5;
		damage = 25;
		explosion = 25;
		projectileID = 0;
	}
	
	
	
}
