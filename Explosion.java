package tankermanz;

public class Explosion {
	int radius;
	int timeLeft;
	int damage;
	int x;
	int y;
	boolean hasNotDamaged = true;
	Terrain terrain;

	public Explosion(Terrain terrain, Projectile p) {
		radius = p.explosion;
		//in ms
		timeLeft = 500;
		x = (int)p.x;
		y = (int)p.y;
		damage = p.damage;
		this.terrain = terrain;

		if (p.projectileID != Projectile.SPRAY_PROJECTILE && p.projectileID != Projectile.SNIPER_PROJECTILE && p.projectileID != Projectile.STREAM_PROJECTILE){
			if (x > 0 && x < Terrain.LENGTH)
				terrain.terrainDestruction(x, radius, Terrain.CIRCLE_DESTRUCTION);
		}
	}

	public Explosion (Terrain terrain, Projectile p, int x, int y){
		this.x = x;
		this.y = y;

		radius = p.explosion;
		damage = p.damage;
		timeLeft = 500;
		this.terrain = terrain;
		if (x > 0 && x < Terrain.LENGTH)
			terrain.terrainDestruction(x, radius, Terrain.CIRCLE_DESTRUCTION);
	}

	public void incrementTime (int elapsedTime){
		timeLeft -= elapsedTime;
	}

	public boolean shouldRemove (){
		if (timeLeft <= 0)
			return true;
		return false;
	}

	public boolean hasNotDamaged() {
		if (hasNotDamaged){
			hasNotDamaged = false;
			return true;
		}
		return false;
	}

}
