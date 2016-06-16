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
	
	public SupplyPack (Terrain terrain, int x, int powerUpID, int ammo){
		this.x = x;
		y = SPAWNY;
		this.powerUpID = powerUpID;
		this.ammo = ammo;
		this.terrain = terrain;
	}
	
	public void moveSupplyPack (int elapsedTime){
		y += dy*elapsedTime/Terrain.SECONDS;
	}
	
	public boolean isLanded (){
		//check if the supply pack hit the ground
		if (y > terrain.getY(x)) 
			return true;
		return false;
	}
	
}
