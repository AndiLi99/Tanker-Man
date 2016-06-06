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
	static final double CANNONSPEED = 50.0;
	static final int LENGTH = 20;
	static final int HEIGHT = 10;
	public static final int MAX_FUEL = 100;
	public static final int MAX_POWER = 300;

	public Tank (int x, int playerID){
		fuel = MAX_FUEL;
		aimAngle = 0;
		power = 300;
		this.x = x;
		this.y = Terrain.getY(x);
		weapons = new int[16];
		this.playerID = playerID;
	}

	public boolean canMove(){
	if (Terrain.projectiles.size() == 0 && Terrain.explosions.size() == 0 && this.playerID == Terrain.turnPlayer){
		return true;
	}
	else
		return false;
}

	public void moveTankAngle (int elapsedTime, boolean CW){
		if (CW){
			if (aimAngle + CANNONSPEED*elapsedTime/Terrain.SECONDS < 360)
				aimAngle += CANNONSPEED*elapsedTime/Terrain.SECONDS;
			else
				aimAngle = CANNONSPEED;
		}
		else if (!CW){
			if (aimAngle - CANNONSPEED*elapsedTime/Terrain.SECONDS > 0)
				aimAngle -= CANNONSPEED*elapsedTime/Terrain.SECONDS;
			else
				aimAngle = 360;
			System.out.println(aimAngle);
		}

		System.out.println(aimAngle);
	}

	public void dropTank (){
		y = Terrain.getY((int)x);
	}
	
	private boolean canMoveLeft(){
		if (this.x - LENGTH/2 > 0){
			return true;
		}
		else
			return false;
	}
	
	private boolean canMoveRight(){
		if (this.x + LENGTH/2 < Terrain.LENGTH){
			return true;
		}
		else
			return false;
	}

	public void moveTank (int elapsedTime, boolean left){
		dropTank();
		fuel -= SPEED * elapsedTime/Terrain.SECONDS;
		if (left && canMoveLeft()){
			x -= SPEED*elapsedTime/Terrain.SECONDS;
		}
		else if (!left && canMoveRight()){
			x += SPEED*elapsedTime/Terrain.SECONDS;
		}
		System.out.println("x of tank: "+ x);

	}

	public void changePower (boolean increase){
		if (increase){
			power+= 1;
		}
		else
			power -=1;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;		
	}






}

