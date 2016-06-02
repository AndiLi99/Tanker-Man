package org.tankermanz;

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

import javax.swing.JPanel;

public class Terrain extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
	static ArrayList <Projectile> projectiles = new ArrayList <Projectile>();
	static ArrayList <Tank> tanks = new ArrayList <Tank>();
	static ArrayList <SupplyPack> supplyPacks = new ArrayList <SupplyPack>();
	static ArrayList <Explosion> explosions = new ArrayList <Explosion>();

	static int [] landX;
	static int [] landY;
	static int turnPlayer = 1;


	static final double GRAVITY = 100.0;
	static final double SECONDS = 1000.0;
	static final int WIDTH = 950;
	static final int HEIGHT = 500;
	static final double RADS = 0.01745329251;
	static int numPlayers;
	
	private boolean rotateLeft;
	private boolean rotateRight;
	private boolean increasePower;
	private boolean decreasePower;
	private boolean moveLeft;
	private boolean moveRight;
	static boolean fire;
	
	
	
	
	static int mouseX = 0;
	static int mouseY = 0;
	
	static final int CIRCLE_DESTRUCTION = 0;
	public static final int REFLECT_BARRIER_HEIGHT = 50;
	
	// constructor
	public Terrain (int mapNum, int numPlayers) {

		landY = new int [952];
		landX = new int [952];
		initialMap (mapNum);

		this.numPlayers = numPlayers;
		
		for (int i = 0; i < Terrain.numPlayers; i++){
			//(int xLocation, int playerID)
			Tank tank = new Tank((i+1)*100, i+1);
			tanks.add(tank);
			
		}
		
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
					if (landY[a] > 700)	// Set minimum ground level
						landY[a] = 700;
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

	public void fireProjectile (Tank t, int projectileID){
		//if ID = 1
		
		spawnProjectile(t);
		
		nextTurn();
	}

	public void moveProjectiles (int elapsedTime){
		for (int i = 0; i < projectiles.size(); i ++){
			Projectile p = projectiles.get(i);
			p.moveProjectile(elapsedTime);		

			if (p.deleteOnSide()){
				projectiles.remove(p);
				System.out.println("Delete projectile");
			}
			
			else if (p.isHit()){
				explodeProjectile(p);
				System.out.println("explode");
			}
			System.out.println("looping");
		}		
	}
	
	public void nextTurn (){
		if (turnPlayer +1 == numPlayers){
			turnPlayer = 0;

		}
		else
			turnPlayer ++;
		
		System.out.println("Turn Player: " + turnPlayer);
		
		tanks.get(turnPlayer).setFuel(Tank.MAX_FUEL);
		
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
					t.moveTankAngle(false);
					System.out.println("trying to rotate left");
				}
				else if (rotateRight){
					t.moveTankAngle(true);
				}
				
				if (fire){
					System.out.println("Weapon fired");
					fireProjectile (t, 1);
					fire = false;
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
				
			}
		}
	}
	
	
	public void spawnProjectile (Tank t){
		Projectile p = new Projectile (t.x, t.y, t.power, t.aimAngle);
		projectiles.add (p);
	}

	public void moveSupplyPacks (int elapsedTime){
		for (SupplyPack s: supplyPacks){
			if (!s.isLanded())
				s.moveSupplyPack(elapsedTime);
		}
	}


	public void spawnSupplyPack (){
		Random r = new Random();
		supplyPacks.add (new SupplyPack (100, 0, 1));

	}

	//get Y value of terrain at a given x value
	static public int getY (int x){
		for (int i = 0; i < landX.length; i++) {
			if (landX [i] == x) return landY [i];
		}
		return -1;
	}

	//damages tank within an area
	public void explodeProjectile (Projectile p){
		Explosion e = new Explosion (p);
		explosions.add (e);
		projectiles.remove(p);
		
		for (Tank aTank : tanks) {
			aTank.dropTank();
			if (distanceBetween (aTank.x, aTank.y, e.x, e.y) < e.radius){
				aTank.health -= e.damage;
				System.out.println("Tank hit!");
			}
		}
	}

	public void incrementExplosions (int elapsedTime){
		List <Explosion> list = new ArrayList<Explosion>();
		
		
		for (int i = 0; i < explosions.size(); i++){
			explosions.get(i).incrementTime(elapsedTime);
			System.out.println("Time Left for explosion: " + explosions.get(i).timeLeft);
			
			if (explosions.get(i).shouldRemove())
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

	//find all tanks within a certain radius
	public ArrayList <Tank> getSupplyPacksHit (SupplyPack s){
		ArrayList <Tank> result = new ArrayList <Tank>();

		for (Tank aTank : tanks) {
			if (distanceBetween (aTank.x, aTank.y, s.x, s.y) < SupplyPack.RADIUS)
				result.add (aTank);
		}
		return result;		
	}

	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		//draw sky
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, 730, new Color (4, 8, 55)));	
		g.fillRect(0, 0, 950, 500); // Draw dark sky background

		//draw land	
		g2.setPaint(new GradientPaint(0, 0,  new Color (124, 203, 255), 0, 730, new Color (11, 50, 75)));
		g2.fillPolygon(Terrain.landX, Terrain.landY, 952);	// Draw polygon containing land

		//draw projectiles
		g.setColor(Color.white);
		for (Projectile p: projectiles){
			g.fillOval((int)p.x-p.radius/2, (int)p.y-p.radius/2, p.radius, p.radius);
		}

		for (Tank t: tanks){
			int dy;
			double angle;
			
			g2.setTransform (resetForm);
			//draws the cannon arm
			g2.setColor (Color.white);
			g2.rotate(t.aimAngle*RADS, t.x, t.y);
			g2.fillRect((int)t.x, (int)t.y, 25, 4);
			
			
			//draws the tank body
			g2.setColor(Color.blue);
			dy = Terrain.getY((int)t.x + 2) - Terrain.getY((int)t.x - 2) ;
			angle = Math.atan(dy/4);

			g2.setTransform (resetForm);
			g2.rotate(angle,t.x,t.y);
			g2.fillRect((int)t.x-Tank.LENGTH/2, (int)t.y-Tank.HEIGHT/2, Tank.LENGTH, Tank.HEIGHT);
			//			g.fillRect((int)t.x-Tank.LENGTH/2, (int)t.y-Tank.HEIGHT/2, Tank.LENGTH, Tank.HEIGHT);
			g.setColor(Color.red);
			g.drawRect((int)t.x,(int)t.y,10,10);
		}
		
		
		g2.setTransform(resetForm);
		g.setColor(Color.red);
		for (Explosion e: explosions){
			g.fillOval(e.x-e.radius/2, e.y-e.radius/2, e.radius, e.radius);
		}

		g.setColor(Color.gray);
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
	}


	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		setMouseXY(mouseX, mouseY);
	}


	private void drawControl(Graphics g) {
		Control.drawBar(g);
		Control.drawFireButton(g);

	}

	public void setMouseXY (int mouseX, int mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	public static int getMouseX () {
		return mouseX;
	}
	public static int getMouseY () {
		return mouseY;
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
