package org.tankermanz;

public class Projectile {
	double x;
	double y;
	int velocityX;
	double velocityY;
	
	int radius = 20;
	int damage = 10;
	int explosion = 70 ;
	
			
	
	
	public Projectile (double x, double y, int power, double angle){
		this.x = x;
		this.y = y;
		this.velocityX = (int)(Math.cos(angle*Terrain.RADS)*power);
		this.velocityY = (int)(Math.sin(angle*Terrain.RADS)*power);
		System.out.println("Starting y velocity" + this.velocityY);
	}
	
	//TODO FIX the y velocity, gravity make realistic shots
	
	public void moveProjectile (int elapsedTime){
		this.x += velocityX * elapsedTime /Terrain.SECONDS;
//		this.y += (velocityY* elapsedTime /Terrain.SECONDS) - (Terrain.GRAVITY*elapsedTime*elapsedTime/(Terrain.SECONDS*Terrain.SECONDS));
		this.velocityY += Terrain.GRAVITY*elapsedTime/Terrain.SECONDS;
		this.y += (velocityY* elapsedTime /Terrain.SECONDS);
		System.out.println("y velocity" + this.velocityY);
		System.out.println(this.y);
	}
	
	public boolean isHit (){
		if (this.y >= Terrain.getY((int)x) || this.x < 0 || this.x > Terrain.WIDTH) 
			return true;
		else
			return false; 
	}
	
	
	
}
