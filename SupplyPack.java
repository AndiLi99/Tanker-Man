package org.tankermanz;

public class SupplyPack {
	int x;
	double y;
	double dy;
	int powerUpID;
	int ammo;
	
	
	static final int WIDTH = 10;
	static final int HEIGHT = 10;
	static final int RADIUS = 10;
	static final int SPAWNY = 800;
	
	public SupplyPack (int x, int powerUpID, int ammo){
		this.x = x;
		y = SPAWNY;
		this.powerUpID = powerUpID;
		this.ammo = ammo;
		
	}
	
	public void moveSupplyPack (int elapsedTime){
		y += (dy * elapsedTime /Terrain.SECONDS) + (Terrain.GRAVITY*elapsedTime*elapsedTime/(Terrain.SECONDS*Terrain.SECONDS));
	}
	
	public boolean isLanded (){
		//check if the supply pack hit the ground
		if (y < Terrain.getY(x)) 
			return true;
		return false;
	}
	
}
