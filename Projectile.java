package tankermanz;

public class Projectile {
	double x;
	double y;
	int velocityX;
	double velocityY;
	int projectileID;
	int delay;
	int damage = 10;
	int explosion = 50 ;
	Terrain terrain;
		
	static final int BULLET_PROJECTILE = 0;
	static final int BIG_BULLET_PROJECTILE = 1;
	static final int EXPLOSIVE_BULLET_PROJECTILE = 2;
	static final int SPRAY_PROJECTILE = 3;
	static final int TRIPLE_SHOT_PROJECTILE = 4;
	static final int DOZEN_SHOT_PROJECTILE = 5;
	static final int AIR_STRIKE_PROJECTILE = 6;
	static final int SPLITTER_PROJECTILE = 7;
	static final int BREAKER_PROJECTILE = 8;
	static final int TRACKER_PROJECTILE = 9;
	static final int HORIZON_PROJECTILE = 10;
	static final int FLOWER_PROJECTILE = 11;
	static final int STREAM_PROJECTILE = 12;
	static final int SNIPER_PROJECTILE = 13;
	static final int NUKE_PROJECTILE = 14;
	static final int ARMAGEDDON_PROJECTILE = 15;
	static final int FOUNTAIN_PROJECTILE = 16;
	
	public Projectile (Terrain terrain, double x, double y, int power, double angle){
		this.x = x;
		this.y = y - 5;
		//power multiplier
		power = power * 3;
		//trignometry
		this.velocityX = (int)(Math.cos(angle*Terrain.RADS)*power);
		this.velocityY = (int)(Math.sin(angle*Terrain.RADS)*power);
		//give each projectile a related terrain field
		this.terrain = terrain;
	}
	
	//constructor for projectiles not directly fired by tanks
	public Projectile (Terrain terrain, double x, double y){
		this.x = x;
		this.y = y -5;
		this.terrain = terrain;
	}
		
	public void moveProjectile (int elapsedTime){
		//make the projectile wait in place before moving to achieve effects
		if (delay > 0){
			delay -= elapsedTime;
		}

		else {
			//speed up the projectiles movement
			elapsedTime= (int)(elapsedTime*1.5);
			//projectile moves in constant velocity in x-axis
			this.x += velocityX * elapsedTime /Terrain.SECONDS;
			//y-velocity decreases linearly over time
			this.velocityY += Terrain.GRAVITY*elapsedTime/Terrain.SECONDS;
			this.y += (velocityY* elapsedTime /Terrain.SECONDS);
		}
	}
	
	public boolean isHit (){
		//check if the ball is below the ground level
		if (this.y >= terrain.getY((int)x) && x != Terrain.LENGTH) //!= Terrain.LENGTH bc of way we stored land
			return true;
		else
			return false; 
	}

	public boolean deleteOnSide(){
		//if the ball moves past the edge of screen
		if (x < 0 || x > Terrain.LENGTH)
			return true;
		else
			return false;
	}

	public boolean reflectOnSide() {
		//just before the ball moves past edge of screen, if it is close enough to ground, should reflect
		if (x < 5 && y > terrain.getY(0) - Terrain.REFLECT_BARRIER_HEIGHT){
			return true;
		}
		if (x > Terrain.LENGTH-5 && y > terrain.getY(Terrain.LENGTH-1) - Terrain.REFLECT_BARRIER_HEIGHT){
			System.out.println("reflect on right");
			return true;	
		}
		return false;
	}
	
	
	
}
