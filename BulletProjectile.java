package tankermanz;

public class BulletProjectile extends Projectile {


	public BulletProjectile (Terrain terrain, double x, double y, int power, double angle){
		super(terrain, x, y, power, angle);
		radius=3;
		damage = 300;
		explosion = 25;
		projectileID = BULLET_PROJECTILE;
	}

}
