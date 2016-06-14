package tankermanz;

public class Projectile {
	double x;
	double y;
	int velocityX;
	double velocityY;
	int projectileID;
	int delay;
	int radius = 3;
	int damage = 10;
	int explosion = 50 ;
	
	
	
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
	

	public Projectile (double x, double y, int power, double angle){
		this.x = x;
		this.y = y - 5;
		this.velocityX = (int)(Math.cos(angle*Terrain.RADS)*power);
		this.velocityY = (int)(Math.sin(angle*Terrain.RADS)*power);
	}
	
	public Projectile (double x, double y){
		this.x = x;
		this.y = y -5;
	}
	
	
	//TODO FIX the y velocity, gravity make realistic shots
	
	public void moveProjectile (int elapsedTime){
		if (delay > 0){
			delay -= elapsedTime;
		}

		else {
			elapsedTime= (int)(elapsedTime*1.5);
			this.x += velocityX * elapsedTime /Terrain.SECONDS;
			//		this.y += (velocityY* elapsedTime /Terrain.SECONDS) - (Terrain.GRAVITY*elapsedTime*elapsedTime/(Terrain.SECONDS*Terrain.SECONDS));
			this.velocityY += Terrain.GRAVITY*elapsedTime/Terrain.SECONDS;
			this.y += (velocityY* elapsedTime /Terrain.SECONDS);
			System.out.println("y velocity" + this.velocityY);
			System.out.println(this.y);
		}
	}
	
	public boolean isHit (){
		if (this.y >= Terrain.getY((int)x)) 
			return true;
		else
			return false; 
	}

	public boolean deleteOnSide(){
		if (x < 0 && y > Terrain.getY(0) - Terrain.REFLECT_BARRIER_HEIGHT){
			return false;	
		}
		if (x > Terrain.LENGTH && y > Terrain.getY(Terrain.LENGTH) - Terrain.REFLECT_BARRIER_HEIGHT){
			return false;	
		}

		if (x < 0 || x > Terrain.LENGTH)
			return true;
		else
			return false;
	}

	public boolean reflectOnSide() {
		if (x < 0 && y > Terrain.getY(0) - Terrain.REFLECT_BARRIER_HEIGHT){
			return true;
		}
		if (x > Terrain.LENGTH && y > Terrain.getY(945) - Terrain.REFLECT_BARRIER_HEIGHT){
			System.out.println("reflect on right");
			return true;	
		}
		return false;
	}
	
	
	
}
