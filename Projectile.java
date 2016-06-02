package org.tankermanz;

public class Projectile {
	double x;
	double y;
	int velocityX;
	double velocityY;
	
	int radius = 10;
	int damage = 10;
	int explosion = 15 ;
	
			
	
	
	public Projectile (double x, double y, int power, double angle){
		this.x = x;
		this.y = y;
		this.velocityX = (int)(Math.cos(angle*Terrain.RADS)*power);
		this.velocityY = (int)(Math.sin(angle*Terrain.RADS)*power);
		System.out.println("Starting y velocity" + this.velocityY);
	}
	
	//TODO FIX the y velocity, gravity make realistic shots
	
	public void moveProjectile (int elapsedTime){
		elapsedTime= (int)(elapsedTime*1.5);
		this.x += velocityX * elapsedTime /Terrain.SECONDS;
//		this.y += (velocityY* elapsedTime /Terrain.SECONDS) - (Terrain.GRAVITY*elapsedTime*elapsedTime/(Terrain.SECONDS*Terrain.SECONDS));
		this.velocityY += Terrain.GRAVITY*elapsedTime/Terrain.SECONDS;
		this.y += (velocityY* elapsedTime /Terrain.SECONDS);
		System.out.println("y velocity" + this.velocityY);
		System.out.println(this.y);
	}
	
	public boolean isHit (){
		if (this.y >= Terrain.getY((int)x)) 
			return true;
		else
			return false; 
	}
	
	public boolean deleteOnSide(){
		if (x < 0 && y > Terrain.getY(0) - Terrain.REFLECT_BARRIER_HEIGHT){
			velocityX = -1*velocityX;

			System.out.println("trying to reflect");
			return false;
		}
		if (x > Terrain.WIDTH && y > Terrain.getY(Terrain.WIDTH) - Terrain.REFLECT_BARRIER_HEIGHT){
			velocityX = -1*velocityX;
			
			System.out.println("Trying to reflect");
			return false;	
		}
		
		if (x < 0 || x > Terrain.WIDTH)
			return true;
		else
			return false;
	}
	
	
	
}
