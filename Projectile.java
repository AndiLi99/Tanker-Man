package org.tankermanz;

public class Projectile {
	double x;
	double y;
	int velocityX;
	int velocityY;
	
	int radius = 20;
	int damage = 10;
	int explosion = 50;
	
			
	
	
	public Projectile (double x, double y, int power, double angle){
		this.x = x;
		this.y = y;
		this.velocityX = (int)Math.cos(angle*Terrain.RADS)*power;
		this.velocityY = (int)Math.sin(angle*Terrain.RADS)*power;
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
