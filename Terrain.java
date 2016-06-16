package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Terrain extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
	static CopyOnWriteArrayList <Projectile> projectiles = new CopyOnWriteArrayList <Projectile>();
	static CopyOnWriteArrayList <Tank> tanks = new CopyOnWriteArrayList <Tank>();

	static CopyOnWriteArrayList <SupplyPack> supplyPacks = new CopyOnWriteArrayList <SupplyPack>();
	static CopyOnWriteArrayList <Explosion> explosions = new CopyOnWriteArrayList <Explosion>();

	int [] landX;
	int [] landY;
	static Tank currentPlayer;
	static int currentMap;
	static int turnsTillEarthquake = 10;
	static int numTurns = 0;
	int gameMode;

	static final double GRAVITY = 100.0;
	static final double SECONDS = 1000.0;
	static final int LENGTH = 950;
	static final int HEIGHT = 500;
	static final double RADS = 0.01745329251;
	static final int MINUMUM_GROUND_HEIGHT = 10;
	static final int CIRCLE_DESTRUCTION = 0;
	public static final int REFLECT_BARRIER_HEIGHT = 100;

	static int numPlayers;
	static String status;

	private boolean rotateLeft;
	private boolean rotateRight;
	private boolean increasePower;
	private boolean decreasePower;
	private boolean moveLeft;
	private boolean moveRight;
	static boolean changeWeaponRight;
	static boolean changeWeaponLeft;
	static boolean fire;

	private boolean released;
	private boolean weaponFired;
	private boolean win;
	private boolean exit;

	static int mouseX = 0;
	static int mouseY = 0;

	static int backButtonX = 750;
	static int backButtonY = 360;
	static int backButtonLength = 185;
	static int backButtonHeight = 90;

	boolean inBackButton = false;
	final int TEXT_SIZE = 50;

	JLabel credits; 
	JLabel backButton;

	// constructor
	public Terrain (int mapNum, int numPlayers, int gameMode, int maxHealth, int maxFuel, int[] tankTeam, int[] tankTops, int[] tankTracks, int[] tankColor) {
		currentMap = mapNum;
		projectiles = new CopyOnWriteArrayList <Projectile>();
		tanks = new CopyOnWriteArrayList <Tank>();

		supplyPacks = new CopyOnWriteArrayList <SupplyPack>();
		explosions = new CopyOnWriteArrayList <Explosion>();

		this.gameMode = gameMode;
		Tank.MAX_FUEL= maxFuel;
		Tank.MAX_HEALTH = maxHealth;
		status = "Ready to Fire";
		initialMap (mapNum);

		Terrain.numPlayers = numPlayers;
		released = true;
		weaponFired = false;
		fire = false;

		for (int i = 0; i < tankTeam.length; i ++){
			if (tankTeam[i]== 0 && i +1< numPlayers){
				if (tankTeam [i+1] == 1)
					tankTeam [i] = 2;
				else if (tankTeam [i+1] == 1)
					tankTeam [i] = 1;

			}
			else if (tankTeam[i]== 0 && i+1 == numPlayers){
				if (tankTeam [0] == 1)
					tankTeam [i] = 2;
				else if (tankTeam [0] == 1)
					tankTeam [i] = 1;

			}

		}

		if (this.gameMode == Constants.TEAM){
			for (int i = 0; i < Terrain.numPlayers; i++){
				if (tankTeam[i] == 0)
					tankTeam[i] = (int)(Math.random()+0.5) + 1;

				Tank tank = new Tank(this, (i+1)*100, i, tankTeam[i], tankTops[i], tankTracks[i], tankColor[i]);
				tank.dropTank();
				tanks.add(tank);
			}
		}

		if (this.gameMode == Constants.FFA){
			for (int i = 0; i < Terrain.numPlayers; i++){
				Tank tank = new Tank(this, (i+1)*100, i, i, tankTops[i], tankTracks[i], tankColor[i]);
				tank.dropTank();
				tanks.add(tank);
			}
		}
		currentPlayer = tanks.get(0);

		addKeyListener(this);
		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
	}



	public Terrain(int startMap) {
		initialMap(startMap);
	}



	//max
	public void initialMap (int mapNum) {
		landY = new int [952];
		landX = new int [952];
		/*	Map Types: 
		 * 	#1 - Hill
		 *  #2 - Exponential
		 *  #3 - Curved Flat Plain
		 *  #4 - Double Stair Step
		 *  #5 - Empire
		 */
		if (mapNum == 1) 	// Draw hill map
			for (int a = 0; a < 950; a++) {
				if (a < 150)
					landY[a] = 300;
				else if (a < 180)
					landY[a] = 300 - ((a - 150)*(a - 150)) / 30;
				else if (a < 501)
					landY[a] = 140 + ((a - 301)*(a - 301)) / 110;
				else if (a < 621)
					landY[a] = 658 - ((a - 620)*(a - 620)) / 90;
				else 
					landY[a] = 658;

				landX[a] = a;
			}
		else if (mapNum == 2)	// Exponential map
			for (int a = 0; a < 950; a++) {
				landY[a] = 650 - (int)(Math.pow(1.02, a - 600));

				landX[a] = a;
			}
		else if (mapNum == 3)	// Flat Hill
			for (int a = 0; a < 950; a++) {
				if (a < 40)
					landY[a] = 305 + 5*a;
				else if (a < 910)
					landY[a] = 1400 - (int)Math.sqrt(1000000 - (a - 475)*(a - 475));
				else
					landY[a] = 500 - 5*(a - 910);

				landX[a] = a;
			}
		else if (mapNum == 4)	// Draw stair steps
			for (int a = 0; a < 950; a++) {
				if (a < 31)
					landY[a] = 100;
				else if (a < 41)
					landY[a] = 100 + 10*(a - 30);
				else if (a < 71)
					landY[a] = 200;
				else if (a < 81)
					landY[a] = 200 + 10*(a - 70);
				else if (a < 111)
					landY[a] = 300;
				else if (a < 121)
					landY[a] = 300 + 10*(a - 110);
				else if (a < 151)
					landY[a] = 400;
				else if (a < 161)
					landY[a] = 400 + 10*(a - 150);
				else if (a < 191)
					landY[a] = 500;
				else if (a < 201)
					landY[a] = 500 + 10*(a - 190);
				else if (a < 750)
					landY[a] = 600;
				else if (a < 760)
					landY[a] = 600 - 10*(a - 750);
				else if (a < 790)
					landY[a] = 500;
				else if (a < 800)
					landY[a] = 500 - 10*(a - 790);
				else if (a < 830)
					landY[a] = 400;
				else if (a < 840)
					landY[a] = 400 - 10*(a - 830);
				else if (a < 870)
					landY[a] = 300;
				else if (a < 880)
					landY[a] = 300 - 10*(a - 870);
				else if (a < 910)
					landY[a] = 200;
				else if (a < 920)
					landY[a] = 200 - 10*(a - 910);
				else
					landY[a] = 100;

				landX[a] = a;
			}
		else if (mapNum == 5)	// Draw empire map
			for (int a = 0; a < 950; a++) {
				if (a < 61)
					landY[a] = 300;
				else if (a < 66)
					landY[a] = 300 - 15*(a - 60);
				else if (a < 116)
					landY[a] = 225;
				else if (a < 121)
					landY[a] = 300 + 15*(a - 120);
				else if (a < 176)
					landY[a] = 300;
				else if (a < 181)
					landY[a] = 300 + 15*(a - 175);
				else if (a < 241)
					landY[a] = 375;
				else if (a < 246)
					landY[a] = 375 + 15*(a - 240);
				else if (a < 296)
					landY[a] = 450;
				else if (a < 304)
					landY[a] = 450 + 15*(a - 295);
				else if (a < 365)
					landY[a] = 570;
				else if (a < 373)
					landY[a] = 570 - 15*(a - 365);
				else if (a < 423)
					landY[a] = 450;
				else if (a < 446)
					landY[a] = 450 - 15*(a - 422);
				else if (a < 506)
					landY[a] = 105;
				else if (a < 529)
					landY[a] = 105 + 15*(a - 505);
				else if (a < 579)
					landY[a] = 450;
				else if (a < 587)
					landY[a] = 450 + 15*(a - 578);
				else if (a < 648)
					landY[a] = 570;
				else if (a < 656)
					landY[a] = 570 - 15*(a - 647);
				else if (a < 706)
					landY[a] = 450;
				else if (a < 711)
					landY[a] = 450 - 15*(a - 705);
				else if (a < 770)
					landY[a] = 375;
				else if (a < 775)
					landY[a] = 375 - 15*(a - 769);
				else if (a < 830)
					landY[a] = 300;
				else if (a < 835)
					landY[a] = 300 - 15*(a - 829);
				else if (a < 885)
					landY[a] = 225;
				else if (a < 890)
					landY[a] = 300 + 15*(a - 889);
				else 
					landY[a] = 300;

				landX[a] = a;
			}

		for (int a = 0; a < 950; a++) {
			landY[a] = landY[a]/2;
		}
		landY[950] = 730;
		landY[951] = 730;
		landX[950] = 950;
		landX[951] = 0;
	}


	//max
	public void terrainDestruction (int locationX, int power, int type) {
		int yMid = landY[locationX];	// Middle of attack

		if (type == 0) { // Circular destruction
			//****************** make it so that it does not destroy steep walls completely 

			for (int a = locationX - power; a <= locationX + power; a++) {
				if (a < 0)	// set drawing start to 0
					a = 0;
				if (a >= 950) // when array reaches over 1000, stop redrawing terrain
					break;


				else {
					int yPos = yMid + (int)Math.sqrt(power*power - (a - locationX)*(a - locationX)); 
					// Find y destruction value (creates downwards semicircle)
					if (yPos > landY[a]) {
						landY[a] = yPos;	// Reset current land value if previous is greater (above) new one
					}
					if (landY[a] > Terrain.HEIGHT - Control.controlPanelHeight - MINUMUM_GROUND_HEIGHT)	// Set minimum ground level
						landY[a] = Terrain.HEIGHT - Control.controlPanelHeight - MINUMUM_GROUND_HEIGHT;
				}
			}

			for (int a = locationX - 4; a >= locationX - power - 125; a--) {	// Loop for area close to destruction
				if (a < 0)
					break;
				int slope = -(landY[a] - landY[a + 3]);	// ABS value of slope on left side of hole

				if (slope < 0)							// Once no longer part of hole, break;
					break;
				else if (slope > 96)							// If slope is too great, lessen it
					landY[a] = landY[a + 1] - 32;
			}

			for (int a = locationX + 4; a <= locationX + power + 125; a++) {
				if (a > 949)
					break;

				int slope = -(landY[a] - landY[a - 3]);

				if (slope < 0)							// Once no longer part of hole, break;
					break;
				else if (slope > 96)
					landY[a] = landY[a - 1] - 32;
			}
		}
	}

	public static Tank getCurrentPlayer(){
		return currentPlayer;
	}

	public void fireProjectile (Tank t){


		if (t.weapons[t.getCurrentWeapon()] != 0){
			t.weapons[t.getCurrentWeapon()] --;
			weaponFired = true;

			System.out.println(Tank.weaponNames[t.getCurrentWeapon()] + "fired, left: "+t.weapons[t.getCurrentWeapon()]);

			if (t.getCurrentWeapon() == Projectile.BULLET_PROJECTILE){
				projectiles.add (new BulletProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
				t.weapons[t.getCurrentWeapon()] ++;
			}

			else if (t.getCurrentWeapon() == Projectile.BIG_BULLET_PROJECTILE){
				projectiles.add (new BigBulletProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.EXPLOSIVE_BULLET_PROJECTILE){
				projectiles.add (new ExplosiveBulletProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.SPRAY_PROJECTILE){
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()-5, t.aimAngle+1));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()-5, t.aimAngle));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()-15, t.aimAngle-3));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()+15, t.aimAngle+3));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()+15, t.aimAngle-4));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()+15, t.aimAngle+4));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()-5, t.aimAngle+4));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()+5, t.aimAngle-4));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()+5, t.aimAngle+3));
				projectiles.add (new SprayProjectile (this, t.x, t.y, t.getPower()+5, t.aimAngle-3));
			}

			else if (t.getCurrentWeapon() == Projectile.TRIPLE_SHOT_PROJECTILE){
				projectiles.add (new TripleShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
				projectiles.add (new TripleShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle - 5));
				projectiles.add (new TripleShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle + 5));
			}

			else if (t.getCurrentWeapon() == Projectile.DOZEN_SHOT_PROJECTILE){
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle-1));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle-2));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle-3));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle-4));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle-5));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle+1));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle+2));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle+3));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle+4));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle+5));
				projectiles.add (new DozenShotProjectile (this, t.x, t.y, t.getPower(), t.aimAngle+6));
			}

			else if (t.getCurrentWeapon() == Projectile.AIR_STRIKE_PROJECTILE){
				projectiles.add (new AirStrikeSummonProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.SPLITTER_PROJECTILE){
				projectiles.add (new SplitterProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, false));
			}

			else if (t.getCurrentWeapon() == Projectile.BREAKER_PROJECTILE){
				projectiles.add (new BreakerProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, false));
			}

			else if (t.getCurrentWeapon() == Projectile.TRACKER_PROJECTILE){
				projectiles.add (new TrackerProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.HORIZON_PROJECTILE){
				projectiles.add (new HorizonProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.FLOWER_PROJECTILE){
				projectiles.add (new FlowerProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.STREAM_PROJECTILE){
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 0));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 100));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 200));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 300));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 400));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 500));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 600));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 700));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 800));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 900));
				projectiles.add (new StreamProjectile (this, t.x, t.y, t.getPower(), t.aimAngle, 1000));
			}

			else if (t.getCurrentWeapon() == Projectile.SNIPER_PROJECTILE){
				projectiles.add (new SniperProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.NUKE_PROJECTILE){
				projectiles.add (new NukeProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.ARMAGEDDON_PROJECTILE){
				projectiles.add (new ArmageddonProjectile (this, t.x, t.y, t.getPower(), t.aimAngle));
			}

			else if (t.getCurrentWeapon() == Projectile.FOUNTAIN_PROJECTILE){
				projectiles.add (new FountainProjectile(this, t.x, t.y, t.getPower(), t.aimAngle, false));
			}

		}

	}

	public void checkForNextTurn(){
		if (weaponFired && projectiles.size() == 0){
			nextTurn();
			weaponFired = false;
		}
	}

	public void moveProjectiles (int elapsedTime){
		for (int i = projectiles.size()-1; i >= 0; i--){
			Projectile p = projectiles.get(i);

			p.moveProjectile(elapsedTime);		


			if (p.deleteOnSide()){
				projectiles.remove(p);
			}

			else if (p.reflectOnSide()){
				p.velocityX = -1*p.velocityX;
			}

			else if (p.isHit() || isTankHit(p)){
				explodeProjectile(p, isTankHit(p));
			}

			if (p.projectileID == Projectile.SPLITTER_PROJECTILE){
				SplitterProjectile s = (SplitterProjectile) p;
				if (s.y + 100 > getY ((int)s.x) && s.velocityY > 0 && s.split == false){
					projectiles.add(new SplitterProjectile(this, s.x, s.y, 65, true));
					projectiles.add(new SplitterProjectile(this, s.x, s.y, -65, true));
					System.out.println("splitting");
					projectiles.remove(p);
				}
			}

			if (p.projectileID == Projectile.TRACKER_PROJECTILE){
				TrackerProjectile s = (TrackerProjectile)p;
				if (!s.activated){
					for (Tank t: tanks){
						if (p.x > t.x - 3 && p.x < t.x + 3 && t.team != getCurrentPlayer().team){
							s.velocityX = 0;
							s.velocityY = 0;
							s.activate();
						}
					}

				}
			}

		}
	}

	public void nextTurn(){
		status = currentPlayer.name + " dealt "+ currentPlayer.getDamageDealt() + " damage with " + Tank.weaponNames[currentPlayer.getCurrentWeapon()] ;
		int indexOfDestroyedTank = tanks.indexOf(currentPlayer);
		boolean win = true;

		for (int i = tanks.size() -1; i >= 0; i --){
			if (tanks.get(i).destroyed){
				tanks.remove(i);
			}
		}


		if (tanks.size() > 0){
			int team = tanks.get(0).team;
			for (int i = tanks.size()-1; i >= 0; i--){
				if (tanks.get(i).team != team){
					win = false;
				}
			}

			this.win = win;


			int index = tanks.indexOf(currentPlayer);
			if (index == -1)
				index = indexOfDestroyedTank -1;

			if (index + 1  == tanks.size()){
				currentPlayer = tanks.get(0);
			}
			else {
				currentPlayer = tanks.get(index+1 );
			}
			currentPlayer.setDamageDealt(0);
			currentPlayer.setFuel(Tank.MAX_FUEL);
			fire = false;
			numTurns++;
			if (numTurns >= turnsTillEarthquake){
				earthquake();
				status = "Earthquake!";
				numTurns = 0;
				for (Tank t: tanks) {
					t.dropTank();
				}
				for (SupplyPack s: supplyPacks){
					s.resetY();
				}
			}
			spawnSupplyPack();
		}
		else
			this.win = true;

		if (win && gameMode == Constants.TEAM){
			status = "Team " + (currentPlayer.team+1) + " has won!";
		}
		else if (win){
			status = currentPlayer.name + " has won!";
		}


	}

	public boolean isTankHit(Projectile p){
		for (Tank t: tanks){
			if (distanceBetween(t.x, t.y, p.x, p.y) < Tank.HIT_RADIUS && t.playerID != currentPlayer.playerID){
				return true;
			}
		}
		return false;
	}


	public void moveTanks (int elapsedTime){
		for (Tank t: tanks){
			if (t.canMove()){
				if (moveLeft){
					t.moveTank(elapsedTime, true);
					System.out.println("trying to move left");	
				}
				else if (moveRight){
					t.moveTank(elapsedTime, false);
					System.out.println("trying to move right");		
				}

				if (rotateLeft){
					t.moveTankAngle(elapsedTime,false);
					System.out.println("trying to rotate left");
				}
				else if (rotateRight){
					t.moveTankAngle(elapsedTime, true);
				}

				if (fire){
					System.out.println("Weapon fired");
					fireProjectile (t);
				}

				if (increasePower){
					//true means power is increased
					t.changePower(elapsedTime, true);
					System.out.println("Power = " + t.getPower());
				}

				else if (decreasePower){
					// false means power is decreased
					t.changePower(elapsedTime, false);
					System.out.println("Power = " + t.getPower());
				}

				if (changeWeaponRight){
					fire = false;
					t.changeWeapon(true);
					changeWeaponRight = false;
				}
				else if (changeWeaponLeft){
					fire = false;
					t.changeWeapon(false);
					changeWeaponLeft = false;
				}


			}
		}
	}

	public void moveSupplyPacks (int elapsedTime){
		for (int i = supplyPacks.size() -1; i >= 0; i --){
			if (!supplyPacks.get(i).isLanded())
				supplyPacks.get(i).moveSupplyPack(elapsedTime);

			for (Tank aTank : tanks) {
				if (distanceBetween (aTank.x, aTank.y, supplyPacks.get(i).x, supplyPacks.get(i).y) < SupplyPack.RADIUS){
					aTank.pickUpSupplyPack(supplyPacks.get(i));
					supplyPacks.remove(i);
					break;
				}
			}
		}
	}


	public void spawnSupplyPack (){
		int xLocation = (int)(Math.random()*Terrain.LENGTH);

		supplyPacks.add (new SupplyPack (this, xLocation));

	}

	//get Y value of terrain at a given x value
	public int getY (int x){
		for (int i = 0; i < landX.length; i++) {
			if (landX [i] == x) return landY [i];
		}
		return -1;
	}

	//damages tank within an area
	public void explodeProjectile (Projectile p, boolean directHit){
		if (p.projectileID == Projectile.BULLET_PROJECTILE ||
				p.projectileID == Projectile.BIG_BULLET_PROJECTILE ||
				p.projectileID == Projectile.EXPLOSIVE_BULLET_PROJECTILE ||
				p.projectileID == Projectile.SPRAY_PROJECTILE ||
				p.projectileID == Projectile.SPLITTER_PROJECTILE ||
				p.projectileID == Projectile.TRIPLE_SHOT_PROJECTILE ||
				p.projectileID == Projectile.DOZEN_SHOT_PROJECTILE ||
				p.projectileID == Projectile.SPLITTER_PROJECTILE ||
				p.projectileID == Projectile.TRACKER_PROJECTILE ||
				p.projectileID == Projectile.STREAM_PROJECTILE ||
				p.projectileID == Projectile.SNIPER_PROJECTILE ||
				p.projectileID == Projectile.NUKE_PROJECTILE ||
				p.projectileID == Projectile.ARMAGEDDON_PROJECTILE
				){
			Explosion e = new Explosion (this, p);
			explosions.add (e);
		}

		else if (p.projectileID == Projectile.BREAKER_PROJECTILE){
			BreakerProjectile b = (BreakerProjectile)p;

			if (!b.split){
				projectiles.add(new BreakerProjectile(this, p.x, p.y, 55, true));
				projectiles.add(new BreakerProjectile(this, p.x, p.y, -55, true));
			}
			else if (b.split){
				Explosion e = new Explosion (this, p);
				explosions.add (e);
			}
		}
		else if (p.projectileID == Projectile.AIR_STRIKE_PROJECTILE){
			projectiles.add(new AirStrikeProjectile(this, p.x, 10, 0, 0, 0));
			projectiles.add(new AirStrikeProjectile(this, p.x - 20, 10, 0, 0, 100));
			projectiles.add(new AirStrikeProjectile(this, p.x + 20, 10, 0,0, 200));
		}

		else if (p.projectileID == Projectile.HORIZON_PROJECTILE){
			explosions.add (new Explosion (this, p, (int)p.x, (int)p.y));
			explosions.add (new Explosion (this, p, (int)p.x+15, (int)p.y));
			explosions.add (new Explosion (this, p, (int)p.x-15, (int)p.y));
			explosions.add (new Explosion (this, p, (int)p.x+30, (int)p.y));
			explosions.add (new Explosion (this, p, (int)p.x-30, (int)p.y));
		}

		else if (p.projectileID == Projectile.FLOWER_PROJECTILE){
			explosions.add (new Explosion(this, p, (int)p.x + 30, (int)p.y));
			explosions.add (new Explosion(this, p, (int)p.x - 30, (int)p.y));
			explosions.add (new Explosion(this, p, (int)p.x, (int)p.y - 30));
			explosions.add (new Explosion(this, p, (int)p.x, (int)p.y + 30));
			explosions.add (new Explosion(this, p, (int)p.x + 21, (int)p.y - 21));
			explosions.add (new Explosion(this, p, (int)p.x +21, (int)p.y + 21));
			explosions.add (new Explosion(this, p, (int)p.x -21, (int)p.y - 21));
			explosions.add (new Explosion(this, p, (int)p.x -21, (int)p.y + 21));
		}

		else if (p.projectileID == Projectile.FOUNTAIN_PROJECTILE){
			FountainProjectile f = (FountainProjectile)p;
			if (f.activated){
				explosions.add (new Explosion (this, p));
			}
			else {
				projectiles.add (new FountainProjectile(this, p.x, p.y, 60, 270, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 55, 271, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 55, 272, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 50, 269, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 52, 271, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 50, 266, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 45, 276, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 30, 265, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 50, 265, true));
				projectiles.add (new FountainProjectile(this, p.x, p.y, 57, 274, true));
			}

		}
		projectiles.remove(p);
	}

	public void incrementExplosions (int elapsedTime){


		for (int i = explosions.size() - 1; i >= 0; i--){
			Explosion e = explosions.get(i);

			if (e.hasNotDamaged()){
				for (Tank aTank : tanks) {
					aTank.dropTank();
					if (distanceBetween (aTank.x, aTank.y, e.x, e.y) < e.radius+Tank.HIT_RADIUS){
						aTank.dealDamage (e.damage);
						System.out.println("Tank hit!");


						if (aTank.team != getCurrentPlayer().team){
							//add damage dealt to player
							getCurrentPlayer().increaseDamageDealt(e.damage);
						}
						else
							getCurrentPlayer().increaseDamageDealt(-e.damage);
					}
				}
			}

			e.incrementTime(elapsedTime);

			if (e.shouldRemove())
				explosions.remove(i);
		}

	}

	//find the distance between two points
	public double distanceBetween (double x1, double y1, double x2, double y2){
		return Math.sqrt ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}

	//find all tanks within a certain radius
	public ArrayList <Tank> getTanksHit (Explosion e){
		ArrayList <Tank> result = new ArrayList <Tank>();

		for (Tank aTank : tanks) {
			if (distanceBetween (aTank.x, aTank.y, e.x, e.y) < e.radius)
				result.add (aTank);
		}
		return result;		
	}

	public int slope (double x){
		return (int)((getY((int)x-4)-getY((int)x+4))/9.0);
	}


	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		//draw sky
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, Control.controlPanelY, new Color (4, 8, 55)));	
		g.fillRect(0, 0, 950, 500); // Draw dark sky background

		//draws land
		g2.setPaint(new GradientPaint(0, 0,  new Color (124, 203, 255), 0, Control.controlPanelY, new Color (11, 50, 75)));
		g2.fillPolygon(landX, landY, 952);	// Draw polygon containing land

		//draw projectiles
		g.setColor(Color.white);
		for (Projectile p: projectiles){
			DrawProjectile.drawProjectile(g2, p.projectileID, (int)p.x, (int)p.y);
		}

		//draws aimer
		if (tanks.size() > 0){
			DrawPowerAndAimer.drawPowerAndAimer(g, (int) getCurrentPlayer().x, (int) getCurrentPlayer().y,
					getCurrentPlayer().aimAngle, getCurrentPlayer().getPower());
		}
		//draw tanks
		for (Tank t: tanks){
			int dy;
			double angle;

			//calculates angle for tank body
			dy = getY((int)t.x + 2) - getY((int)t.x - 2) ;
			angle = Math.atan(dy/4);
			angle = angle/RADS;

			if (gameMode == Constants.FFA){
				if (t.tankColor == Constants.TANK_COLOR_GREEN) DrawTank.colorGreen();
				else if (t.tankColor == Constants.TANK_COLOR_RED) DrawTank.colorRed();
				else if (t.tankColor == Constants.TANK_COLOR_PINK) DrawTank.colorPink();
				else if (t.tankColor == Constants.TANK_COLOR_BLUE) DrawTank.colorBlue();
				DrawTank.drawCustomTank(g, (int)t.x, (int)t.y, Constants.IN_GAME_TANK_HEIGHT, (int)angle, (int)t.aimAngle, t.tankTops, t.tankTracks);
			}
			else{
				if (t.team == 1) DrawTank.colorGreen();
				else if (t.team == 2) DrawTank.colorRed();
				DrawTank.drawCustomTank(g, (int)t.x, (int)t.y, Constants.IN_GAME_TANK_HEIGHT, (int)angle, (int)t.aimAngle, t.tankTops, t.tankTracks);

			}

			g2.setTransform(resetForm);
			//draws red background of hp bar
			g2.setColor(Color.red);
			g2.fillRect((int)t.x-Tank.HPLENGTH/2, (int)t.y-40, Tank.HPLENGTH, Tank.HPHEIGHT);	

			//draws green portion of hp bar
			g2.setColor(Color.green);
			g2.fillRect((int)t.x-Tank.HPLENGTH/2, (int)t.y-40, (int)(Tank.HPLENGTH*((double)t.health/(double)Tank.MAX_HEALTH)), Tank.HPHEIGHT);

			DrawTank.drawTankInfo(g, (int)t.x, (int)t.y, (int)t.power, (int)(t.aimAngle), t.name);
		}

		g2.setTransform(resetForm);
		g.setColor(Color.red);
		for (Explosion e: explosions){
			g.fillOval(e.x-e.radius, e.y-e.radius, e.radius*2, e.radius*2);
		}

		g.setColor(Color.white);
		for (SupplyPack s: supplyPacks){
			g.fillRect(s.x, (int)s.y,SupplyPack.WIDTH, SupplyPack.HEIGHT);
		}

		g2.setTransform(resetForm);
		if (tanks.size() > 0)
			drawControl(g);

		g.setFont(new Font ("Arial", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString(getCurrentPlayer().name + "'s turn", Terrain.LENGTH/2 - 60, 30);	
		g.drawString(status, Terrain.LENGTH/2-100, 50);
	}


	public void updateGame (int elapsedTime){
		moveProjectiles (elapsedTime);
		moveSupplyPacks (elapsedTime);
		incrementExplosions(elapsedTime);
		moveTanks(elapsedTime);
		checkForNextTurn();

	}

	public boolean tankCanMove (){
		if (projectiles.size() == 0 && explosions.size() == 0){
			return true;
		}
		return false;
	}

	public void keyPressed(KeyEvent e) {
		if (tankCanMove()){
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				rotateLeft = true;
				rotateRight = false;
			}

			if (key == KeyEvent.VK_RIGHT) {
				rotateRight = true;
				rotateLeft = false;
			}

			if (key == KeyEvent.VK_UP) {
				increasePower = true;
				decreasePower = false;
			}

			if (key == KeyEvent.VK_DOWN) {
				decreasePower = true;
				increasePower = false;
			}

			if (key == KeyEvent.VK_A){
				moveLeft = true;
				moveRight = false;
			}

			if (key == KeyEvent.VK_D){
				moveRight = true;
				moveLeft = false;
			}

			if (key == KeyEvent.VK_SPACE){
				fire = true;
			}

			if (key == KeyEvent.VK_W && released){
				changeWeaponRight = true;
				released = false;
			}


			if (key == KeyEvent.VK_S && released){
				changeWeaponLeft = true;
				released = false;
			}

		}
	}

	public void keyTyped (KeyEvent e){
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			rotateLeft = false;
		}

		if (key == KeyEvent.VK_RIGHT) {
			rotateRight = false;
		}

		if (key == KeyEvent.VK_UP) {
			increasePower = false;
		}

		if (key == KeyEvent.VK_DOWN) {
			decreasePower = false;
		}

		if (key == KeyEvent.VK_A){
			moveLeft = false;
		}

		if (key == KeyEvent.VK_D){
			moveRight = false;
		}

		if (key == KeyEvent.VK_W){
			changeWeaponRight = false;
			released = true;
		}

		if (key == KeyEvent.VK_S){
			changeWeaponLeft = false;
			released = true;
		}

		if (key == KeyEvent.VK_SPACE){
			fire = false;
		}

	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		if (Control.getClickFire() && tankCanMove()){
			fire = true;
		}
		else
			fire = false;
		if (Control.inWeaponChangerLeft){
			changeWeaponLeft = true;
		}
		else if (Control.inWeaponChangerRight){
			changeWeaponRight = true;
		}
		else if (inBackButton){
			exit = true;
		}
	}

	public void earthquake () {
		int[] copyLandY = new int [952];
		System.arraycopy(landY, 0, copyLandY, 0, 952);
		initialMap(currentMap);
		
		for (int a = 0; a < LENGTH; a++) {
			int difference = copyLandY[a] - landY[a];
			landY[a] += (int)(0.3*difference);
		}
	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		setMouseXY(mouseX, mouseY);
		setInBackButton(mouseX, mouseY);
		setInBackButton(mouseX, mouseY);
	}


	private void drawControl(Graphics g) {
		Control.drawBar(g);
		Control.drawFireButton(g);
		Control.drawHealthBox(g, getCurrentPlayer().health, Tank.MAX_HEALTH, getCurrentPlayer().team);
		Control.drawFuelBox(g, (int)getCurrentPlayer().fuel, Tank.MAX_FUEL);
		Control.drawWeaponBox(g, mouseX, mouseY);
		drawBackButton(g);
	}

	public void setMouseXY (int mouseX, int mouseY) {
		Terrain.mouseX = mouseX;
		Terrain.mouseY = mouseY;
	}
	public static int getMouseX () {
		return mouseX;
	}
	public static int getMouseY () {
		return mouseY;
	}

	public int [] getLand (int mapNum) {
		return landY;
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean getWin (){
		return win;
	}

	public static void setStatus(String status){
		Terrain.status = status;
	}

	public boolean getExit() {
		return exit;
	}


	public void drawBackButton (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (inBackButton) {
			g2.setPaint(new GradientPaint(0, backButtonY,  new Color (199, 187, 107), 
					0, backButtonY + backButtonHeight, new Color (151, 105, 36)));
		}
		else {
			g2.setPaint(new GradientPaint(0, backButtonY,  new Color (165, 132, 0), 
					0, backButtonY + backButtonHeight, new Color (108, 61, 0)));
		}
		g.fillRect(backButtonX, backButtonY, backButtonLength, backButtonHeight);
		
		String text = "Quit Game";
		int approxLength = text.length()*19;
		int approxHeight = 50;
		g.setColor(new Color (11, 8, 3));
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.drawString(text, backButtonX + backButtonLength/2 - approxLength/2, backButtonY + approxHeight);
	}
	
	public void setInBackButton (int mouseX, int mouseY) {
		if (mouseX > backButtonX && mouseX < backButtonX + backButtonLength) {
			if (mouseY > backButtonY && mouseY < backButtonY + backButtonHeight)
				inBackButton = true;
			else 
				inBackButton = false;
		}
		else 
			inBackButton = false;
	}
}
