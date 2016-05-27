package org.tankermanz;

public class Explosion {
	int radius;
	int timeLeft;
	int x;
	int y;
	
	public Explosion(Projectile p) {
		radius = p.explosion;
		timeLeft = 1000;
		this.x = (int)p.x;
		this.y = (int)p.y;
	}

	public void incrementTime (int elapsedTime){
		if (timeLeft - elapsedTime < 0){
			timeLeft = 0;
		}
		else
			timeLeft -= elapsedTime;
		
	}
	
	public boolean shouldRemove (){
		if (timeLeft < 0)
			return true;
		else
			return false;
	}
	
}
