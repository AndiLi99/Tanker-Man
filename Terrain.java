package tankermanz;

import java.awt.Color;
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

import javax.swing.JPanel;

public class Terrain extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
	static CopyOnWriteArrayList <Projectile> projectiles = new CopyOnWriteArrayList <Projectile>();
	static ArrayList <Tank> tanks = new ArrayList <Tank>();
	
	static CopyOnWriteArrayList <SupplyPack> supplyPacks = new CopyOnWriteArrayList <SupplyPack>();
	static CopyOnWriteArrayList <Explosion> explosions = new CopyOnWriteArrayList <Explosion>();

	static int [] landX;
	static int [] landY;
	static Tank currentPlayer;

	static final double GRAVITY = 100.0;
	static final double SECONDS = 1000.0;
	static final int LENGTH = 950;
	static final int HEIGHT = 500;
	static final double RADS = 0.01745329251;
	static final int MINUMUM_GROUND_HEIGHT = 10;
	static int numPlayers;
	
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
	
	
	
	
	static int mouseX = 0;
	static int mouseY = 0;
	
	static final int CIRCLE_DESTRUCTION = 0;
	public static final int REFLECT_BARRIER_HEIGHT = 100;
	
	// constructor
	public Terrain (int mapNum, int numPlayers, int gameMode) {

		landY = new int [952];
		landX = new int [952];
		initialMap (mapNum);

		Terrain.numPlayers = numPlayers;
		released = true;
		weaponFired = false;
		
		for (int i = 0; i < Terrain.numPlayers; i++){
			//(int xLocation, int playerID)
			Tank tank = new Tank((i+1)*100, i, i%2);
			tanks.add(tank);
		}
		
		currentPlayer = tanks.get(0);
		
		addKeyListener(this);
		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	


	//max
	public static void initialMap (int mapNum) {
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
	public static void terrainDestruction (int locationX, int power, int type) {
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
			
			System.out.println(Tank.weaponNames[t.getCurrentWeapon()] + "fired");
			
			if (t.getCurrentWeapon() == Projectile.BULLET_PROJECTILE){
				projectiles.add (new BulletProjectile (t.x, t.y, t.power, t.aimAngle));
				t.weapons[t.getCurrentWeapon()] ++;
			}
			
			else if (t.getCurrentWeapon() == Projectile.BIG_BULLET_PROJECTILE){
				projectiles.add (new BigBulletProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.EXPLOSIVE_BULLET_PROJECTILE){
				projectiles.add (new ExplosiveBulletProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.SPRAY_PROJECTILE){
				projectiles.add (new SprayProjectile (t.x, t.y, t.power-5, t.aimAngle+1));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power-5, t.aimAngle));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power-15, t.aimAngle-3));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power+15, t.aimAngle+3));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power+15, t.aimAngle-4));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power+15, t.aimAngle+4));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power-5, t.aimAngle+4));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power+5, t.aimAngle-4));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power+5, t.aimAngle+3));
				projectiles.add (new SprayProjectile (t.x, t.y, t.power+5, t.aimAngle-3));
			}
			
			else if (t.getCurrentWeapon() == Projectile.TRIPLE_SHOT_PROJECTILE){
				projectiles.add (new TripleShotProjectile (t.x, t.y, t.power, t.aimAngle));
				projectiles.add (new TripleShotProjectile (t.x, t.y, t.power, t.aimAngle - 5));
				projectiles.add (new TripleShotProjectile (t.x, t.y, t.power, t.aimAngle + 5));
			}
			
			else if (t.getCurrentWeapon() == Projectile.DOZEN_SHOT_PROJECTILE){
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle-1));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle-2));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle-3));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle-4));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle-5));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle+1));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle+2));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle+3));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle+4));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle+5));
				projectiles.add (new DozenShotProjectile (t.x, t.y, t.power, t.aimAngle+6));
			}
			
			else if (t.getCurrentWeapon() == Projectile.AIR_STRIKE_PROJECTILE){
				projectiles.add (new AirStrikeSummonProjectile (t.x, t.y, t.power, t.aimAngle));
			}
		
			else if (t.getCurrentWeapon() == Projectile.SPLITTER_PROJECTILE){
				projectiles.add (new SplitterProjectile (t.x, t.y, t.power, t.aimAngle, false));
			}
			
			else if (t.getCurrentWeapon() == Projectile.BREAKER_PROJECTILE){
				projectiles.add (new BreakerProjectile (t.x, t.y, t.power, t.aimAngle, false));
			}
		
			else if (t.getCurrentWeapon() == Projectile.TRACKER_PROJECTILE){
				projectiles.add (new TrackerProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.HORIZON_PROJECTILE){
				projectiles.add (new HorizonProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.FLOWER_PROJECTILE){
				projectiles.add (new FlowerProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.STREAM_PROJECTILE){
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 0));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 100));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 200));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 300));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 400));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 500));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 600));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 700));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 800));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 900));
				projectiles.add (new StreamProjectile (t.x, t.y, t.power, t.aimAngle, 1000));
			}
			
			else if (t.getCurrentWeapon() == Projectile.SNIPER_PROJECTILE){
				projectiles.add (new SniperProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.NUKE_PROJECTILE){
				projectiles.add (new NukeProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.ARMAGEDDON_PROJECTILE){
				projectiles.add (new ArmageddonProjectile (t.x, t.y, t.power, t.aimAngle));
			}
			
			else if (t.getCurrentWeapon() == Projectile.FOUNTAIN_PROJECTILE){
				projectiles.add (new FountainProjectile(t.x, t.y, t.power, t.aimAngle, false));
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
				System.out.println("Delete projectile");
			}

			else if (p.reflectOnSide()){
				p.velocityX = -1*p.velocityX;
				System.out.println("reflecting");
			}

			else if (p.isHit() || isTankHit(p)){
				explodeProjectile(p, isTankHit(p));
				System.out.println("explode");
			}
			
			if (p.projectileID == Projectile.SPLITTER_PROJECTILE){
				SplitterProjectile s = (SplitterProjectile) p;
				if (s.y + 100 > getY ((int)s.x) && s.velocityY > 0 && s.split == false){
					projectiles.add(new SplitterProjectile(s.x, s.y, 65, true));
					projectiles.add(new SplitterProjectile(s.x, s.y, -65, true));
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

	public void nextTurn (){
		System.out.println("Player " + currentPlayer.name + " dealt "+ getCurrentPlayer().getDamageDealt() + " damage");
		int index = tanks.indexOf(currentPlayer);
		int tanksDestroyed = 0;
		
		for (int i = tanks.size() -1; i >= 0; i --){
			if (tanks.get(i).destroyed){
				tanks.remove(i);
				tanksDestroyed ++;
			}
		}
		
		
		if (index + 1 - tanksDestroyed == tanks.size()){
			currentPlayer = tanks.get(0);
		}
		else {
			currentPlayer = tanks.get(index+1 - tanksDestroyed);
		}

		currentPlayer.setDamageDealt(0);
		
		System.out.println("It is " + currentPlayer.name + "'s turn");
		
		currentPlayer.setFuel(Tank.MAX_FUEL);
		fire = false;
		spawnSupplyPack();
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
					t.changePower(true);
					System.out.println("Power = " + t.power);
				}
				
				else if (decreasePower){
					// false means power is decreased
					t.changePower(false);
					System.out.println("Power = " + t.power);
				}
				
				if (changeWeaponRight){
					t.changeWeapon(true);
					changeWeaponRight = false;
				}
				else if (changeWeaponLeft){
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
		
		supplyPacks.add (new SupplyPack (xLocation, 1, 10));
		
	}

	//get Y value of terrain at a given x value
	static public int getY (int x){
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
			Explosion e = new Explosion (p);
			explosions.add (e);
		}
		
		else if (p.projectileID == Projectile.BREAKER_PROJECTILE){
			BreakerProjectile b = (BreakerProjectile)p;

			if (!b.split){
				projectiles.add(new BreakerProjectile(p.x, p.y, 55, true));
				projectiles.add(new BreakerProjectile(p.x, p.y, -55, true));
			}
			else if (b.split){
				Explosion e = new Explosion (p);
				explosions.add (e);
			}
		}
		else if (p.projectileID == Projectile.AIR_STRIKE_PROJECTILE){
			projectiles.add(new AirStrikeProjectile(p.x, 10, 0, 0, 0));
			projectiles.add(new AirStrikeProjectile(p.x - 20, 10, 0, 0, 100));
			projectiles.add(new AirStrikeProjectile(p.x + 20, 10, 0,0, 200));
		}
		
		else if (p.projectileID == Projectile.HORIZON_PROJECTILE){
			explosions.add (new Explosion (p, (int)p.x, (int)p.y));
			explosions.add (new Explosion (p, (int)p.x+15, (int)p.y));
			explosions.add (new Explosion (p, (int)p.x-15, (int)p.y));
			explosions.add (new Explosion (p, (int)p.x+30, (int)p.y));
			explosions.add (new Explosion (p, (int)p.x-30, (int)p.y));
		}
		
		else if (p.projectileID == Projectile.FLOWER_PROJECTILE){
			explosions.add (new Explosion(p, (int)p.x + 30, (int)p.y));
			explosions.add (new Explosion(p, (int)p.x - 30, (int)p.y));
			explosions.add (new Explosion(p, (int)p.x, (int)p.y - 30));
			explosions.add (new Explosion(p, (int)p.x, (int)p.y + 30));
			explosions.add (new Explosion(p, (int)p.x + 21, (int)p.y - 21));
			explosions.add (new Explosion(p, (int)p.x +21, (int)p.y + 21));
			explosions.add (new Explosion(p, (int)p.x -21, (int)p.y - 21));
			explosions.add (new Explosion(p, (int)p.x -21, (int)p.y + 21));
		}
		
		else if (p.projectileID == Projectile.FOUNTAIN_PROJECTILE){
			FountainProjectile f = (FountainProjectile)p;
			if (f.activated){
				explosions.add (new Explosion (p));
			}
			else {
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 270, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 271, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 272, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 273, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 274, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 266, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 276, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 255, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 265, true));
				projectiles.add (new FountainProjectile(p.x, p.y, 150, 274, true));
				System.out.println("");
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


	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		//draw sky
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, Control.controlPanelY, new Color (4, 8, 55)));	
		g.fillRect(0, 0, 950, 500); // Draw dark sky background

		//draws land
		g2.setPaint(new GradientPaint(0, 0,  new Color (124, 203, 255), 0, Control.controlPanelY, new Color (11, 50, 75)));
		g2.fillPolygon(Terrain.landX, Terrain.landY, 952);	// Draw polygon containing land

		//draw projectiles
		g.setColor(Color.white);
		for (Projectile p: projectiles){
			DrawProjectile.drawProjectile(g2, p.projectileID, (int)p.x, (int)p.y);
		}

		//draws aimer
		DrawPowerAndAimer.drawPowerAndAimer(g, (int) getCurrentPlayer().x, (int) getCurrentPlayer().y,
				getCurrentPlayer().aimAngle, getCurrentPlayer().power);
		
		//draw tanks
		for (Tank t: tanks){
			int dy;
			double angle;

			//calculates angle for tank body
			dy = Terrain.getY((int)t.x + 2) - Terrain.getY((int)t.x - 2) ;
			angle = Math.atan(dy/4);
			angle = angle/RADS;
			
			if (t.team == 0){			
			DrawTank.colorGreen();
			DrawTank.drawDefaultTank(g, (int)t.x, (int)t.y, 15, (int)angle, (int)t.aimAngle);
			}
			else if (t.team == 1){
				DrawTank.colorRed();
				DrawTank.drawDefaultTank(g, (int)t.x, (int)t.y, 15, (int)angle, (int)t.aimAngle);
			}
					
			g2.setTransform(resetForm);
			//draws red background of hp bar
			g2.setColor(Color.red);
			g2.fillRect((int)t.x-Tank.HPLENGTH/2, (int)t.y-30, Tank.HPLENGTH, Tank.HPHEIGHT);	
			
			//draws green portion of hp bar
			g2.setColor(Color.green);
			g2.fillRect((int)t.x-Tank.HPLENGTH/2, (int)t.y-30, (int)(Tank.HPLENGTH*((double)t.health/(double)Tank.MAX_HEALTH)), Tank.HPHEIGHT);
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
		drawControl(g);
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
		if (Control.getClickFire() && tankCanMove())
			fire = true;
		if (Control.inWeaponChangerLeft)
			changeWeaponLeft = true;
		else if (Control.inWeaponChangerRight)
			changeWeaponRight = true;
	}


	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		setMouseXY(mouseX, mouseY);
	}


	private void drawControl(Graphics g) {
		Control.drawBar(g);
		Control.drawFireButton(g);
		Control.drawHealthBox(g, getCurrentPlayer().health, Tank.MAX_HEALTH, getCurrentPlayer().team);
		Control.drawFuelBox(g, getCurrentPlayer().fuel, Tank.MAX_FUEL);
		Control.drawWeaponBox(g, mouseX, mouseY);
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
	
	public static int [] getLand (int mapNum) {
		initialMap(mapNum);
		return landY;
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
