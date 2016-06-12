package tankermanz;

//andi
public class SupplyPack {
	int x;
	double y;
	double dy = 150;
	int powerUpID;
	int ammo;
	
	
	static final int WIDTH = 15;
	static final int HEIGHT = 10;
	static final int RADIUS = 30;
	static final int SPAWNY = 30;
	
	public SupplyPack (int x, int powerUpID, int ammo){
		this.x = x;
		y = SPAWNY;
		this.powerUpID = powerUpID;
		this.ammo = ammo;
		
	}
	
	public void moveSupplyPack (int elapsedTime){
		y += dy*elapsedTime/Terrain.SECONDS;
	}
	
	public boolean isLanded (){
		//check if the supply pack hit the ground
		if (y > Terrain.getY(x)) 
			return true;
		return false;
	}
	
}
