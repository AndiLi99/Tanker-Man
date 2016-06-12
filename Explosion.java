package tankermanz;

public class Explosion {
	int radius;
	int timeLeft;
	int damage;
	int x;
	int y;
	
	public Explosion(Projectile p) {
		radius = p.explosion;
		//in ms
		timeLeft = 1000;
		x = (int)p.x;
		y = (int)p.y;
		damage = p.damage;
		
		Terrain.terrainDestruction(x, radius, Terrain.CIRCLE_DESTRUCTION);
		
	}

	public void incrementTime (int elapsedTime){
			timeLeft -= elapsedTime;
		
	}
	
	public boolean shouldRemove (){
		if (timeLeft <= 0)
			return true;
		else
			return false;
	}
	
}
