package tankermanz;

//andi
public class SupplyPack {
	int x;
	double y;
	double dy = 150;
	int powerUpID;
	int ammo;
	Terrain terrain;
	
	static final int WIDTH = 15;
	static final int HEIGHT = 10;
	static final int RADIUS = 30;
	static final int SPAWNY = 30;
	
	public SupplyPack (Terrain terrain, int x){
		this.x = x;
		y = SPAWNY;
		
		//pick a random number from 0-16
		this.powerUpID = (int) (Math.random()*15)+1;
		//pick a random number for ammo
		this.ammo = (int)((Math.random())*10+0.5)+ 1;
		this.terrain = terrain;
	}
	
	public void moveSupplyPack (int elapsedTime){
		//move down in constant velocity
		y += dy*elapsedTime/Terrain.SECONDS;
	}
	
	public boolean isLanded (){
		//check if the supply pack hit the ground
		if (y > terrain.getY(x)) 
			return true;
		return false;
	}
	
	public void resetY(){ y = terrain.getY(x); }
	
}
