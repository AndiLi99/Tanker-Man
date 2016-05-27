package org.tankermanz;

public class Projectile {
	double x;
	double y;
	int velocityX;
	int velocityY;
	
	int radius = 1;
	int damage = 1;
	int explosion = 1;
	
	//ratio to convert to radians from degrees
	static final double RADS = 0.01745329251;
	
	
	
	public Projectile (double x, double y, int power, double angle){
		this.x = x;
		this.y = y;
		this.velocityX = (int)Math.cos(angle*RADS)*power;
		this.velocityY = (int)Math.sin(angle*RADS)*power;
	}
	
	public void moveProjectile (int elapsedTime){
		this.x += velocityX * elapsedTime /Terrain.SECONDS;
		this.y += (velocityY * elapsedTime /Terrain.SECONDS) + (Terrain.GRAVITY*elapsedTime*elapsedTime/(Terrain.SECONDS*Terrain.SECONDS));
	}
	
	public boolean isHit (){
		if (this.y < Terrain.getY((int)x)) return true;
		return false;
	}
	
	
	
}
