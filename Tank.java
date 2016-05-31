package org.tankermanz;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tank {
	double x;
	double y;
	int fuel;
	double aimAngle;
	int power;
	String name;
	int health;
	int [] weapons;
	int playerID;
	
	

	//how fast the tank can move
	static final double SPEED = 35.0;
	static final double CANNONSPEED = 1.0;
	static final int LENGTH = 20;
	static final int HEIGHT = 10;

	public Tank (int x){
		fuel = 100;
		aimAngle = 0;
		power = 250;
		this.x = x;
		this.y = Terrain.getY(x);
		weapons = new int[16];
		playerID =1 ;
	}

	public boolean canMove(){
	if (this.x < Terrain.WIDTH && this.x > 0  && Terrain.projectiles.size() == 0 && this.playerID == Terrain.turnPlayer){
		return true;
	}
	else
		return false;
}

	public void moveTankAngleCW (){
		if (aimAngle + CANNONSPEED < 360)
			aimAngle += CANNONSPEED;
		else
			aimAngle = CANNONSPEED;
		
		System.out.println(aimAngle);
	}

	public void moveTankAngleCCW (){
		if (aimAngle - CANNONSPEED > 0)
			aimAngle -= CANNONSPEED;
		else
			aimAngle = 360;
		System.out.println(aimAngle);
	}

	public void moveTankLeft (int elapsedTime){
		x -= SPEED*elapsedTime/Terrain.SECONDS;
		y = Terrain.getY((int)x);
		fuel -= SPEED * elapsedTime/Terrain.SECONDS;
		System.out.println(x);
	}

	public void moveTankRight (int elapsedTime){
		x += SPEED*elapsedTime/Terrain.SECONDS;
		y = Terrain.getY((int)x);
		fuel -= SPEED * elapsedTime/Terrain.SECONDS;
		System.out.println(x);
	}

	public void changePower (boolean increase){
		if (increase){
			power+= 1;
		}
		else
			power -=1;
	}






}

