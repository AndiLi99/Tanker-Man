package tankermanz;

public class AirStrikeSummonProjectile extends Projectile {
	
	public AirStrikeSummonProjectile (double x, double y, int power, double angle){
		super(x, y, power, angle);
		radius= 10;
		damage = 0;
		explosion = 10;
		projectileID = AIR_STRIKE_PROJECTILE;
		
	}
	
	
	
}
