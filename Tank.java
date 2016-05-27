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
	static final double SPEED = 1.0;
	static final double CANNONSPEED = 0.5;
	static final int LENGTH = 20;
	static final int HEIGHT = 10;

	public Tank (int x){
		fuel = 100;
		aimAngle = 0;
		power = 100;
		this.x = x;
		this.y = Terrain.getY(x);
		weapons = new int[16];
		playerID =1 ;
	}



	public void changeTankAngleCW (){
		if (aimAngle + CANNONSPEED < 360)
			aimAngle += CANNONSPEED;
		else
			aimAngle = CANNONSPEED;
	}

	public void changeTankAngleCCW (){
		if (aimAngle - CANNONSPEED > 0)
			aimAngle -= CANNONSPEED;
		else
			aimAngle = -CANNONSPEED;
	}

	public void changeTankAngle (){
		System.out.println("ehl");


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

	//	public class mouse implements MouseListener () {
	//		
	//	}

	//	public void mouseClicked(MouseEvent arg0) {
	//		locX = arg0.getX();
	//		locY = arg0.getY();
	//		
	//		if (check on screen)
	//			
	//	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}






}

