package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;

//max
public class MenuScreen extends JPanel implements MouseMotionListener, MouseListener {
	// Mouse X and Y coordinates
	static int mouseX = 0; static int mouseY = 0;

	static int leftArmAngle = 250;


	static final int tankX = 725; static final int tankY = 85;
	static final int angleTank = 335;
	static final int tankHeight = 18;
	static final int tankArmAngle = 325;

	static final int landDrawY = 400;

	static final int titleFontSize = 100;

	static final int gameTitleTankerX = 150; static final int gameTitleTankerY = 40;
	static final int gameTitleManzX = 500; static final int gameTitleManzY = 40;

	static final int gameTitleTankerLength = 700; static final int gameTitleTankerHeight = 100;
	static final int gameTitleManzLength = 300;	static final int gameTitleManzHeight = 100;

	static final int optionsTextSize = 50;

	static final int playButtonX = 325;	static final int playButtonY = 150;
	static final int playButtonLength = 245;
	static boolean inPlayButton = false;

	static final int instructionsButtonX = 190;	static final int instructionsButtonY = 225;
	static final int instructionsButtonLength = 535;
	static boolean inInstructionButton = false;

	static final int creditsButtonX = 290;	static final int creditsButtonY = 300;
	static final int creditsButtonLength = 305;
	static boolean inCreditsButton = false;

	static final int buttonHeight = 75;

	static JLabel gameTitleTanker;
	static JLabel gameTitleManz;
	static JLabel play;
	static JLabel instructions;
	static JLabel credits;

	private boolean startGame = false;

	// constructor
	public MenuScreen () {
		setLayout(null);
		
		setTitle(); add(gameTitleTanker); add(gameTitleManz);
		setOptions(); add(play); add(instructions); add(credits);
		
		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
	}
	


	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Draw background
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, Terrain.HEIGHT, new Color (4, 8, 55)));	
		g.fillRect(0, 0, Terrain.LENGTH, Terrain.HEIGHT);
		// Draw land
		g2.setPaint(new GradientPaint(0, landDrawY, new Color (124, 203, 255), 0, Terrain.HEIGHT, new Color (0, 0, 75)));
		g2.fillRect(0, landDrawY, Terrain.LENGTH, Terrain.HEIGHT - landDrawY);

		DrawTank.colorGreen();
		DrawTank.drawDefaultTank(g2, tankX, tankY, tankHeight, angleTank, tankArmAngle);
		drawSelector(g);

	}

	public static void setTitle () {
		gameTitleTanker = new JLabel("Tanker");
		gameTitleTanker.setFont(new Font("AR BERKLEY", Font.BOLD, titleFontSize));
		gameTitleTanker.setForeground(new Color (0, 111, 11));
		gameTitleTanker.setSize(gameTitleTankerLength, gameTitleTankerHeight);
		gameTitleManz = new JLabel("Manz");
		gameTitleManz.setFont(new Font("AR BERKLEY", Font.BOLD, titleFontSize));
		gameTitleManz.setForeground (new Color (111, 0, 0));
		gameTitleManz.setSize(gameTitleManzLength, gameTitleManzHeight);

		gameTitleTanker.setLocation(gameTitleTankerX, gameTitleTankerY);
		gameTitleManz.setLocation(gameTitleManzX, gameTitleManzY);
	}

	public void setOptions () {
		play = new JLabel("New Game");
		play.setFont(new Font("AR BERKLEY", Font.BOLD, optionsTextSize));
		play.setForeground(new Color (0, 105, 149));
		play.setSize(playButtonLength, buttonHeight);
		play.setLocation(playButtonX, playButtonY);

		instructions = new JLabel("Instructions & Controls");
		instructions.setFont(new Font("AR BERKLEY", Font.BOLD, optionsTextSize));
		instructions.setForeground(new Color (0, 105, 149));
		instructions.setSize(instructionsButtonLength, buttonHeight);
		instructions.setLocation(instructionsButtonX, instructionsButtonY);

		credits = new JLabel("Game Credits");
		credits.setFont(new Font("AR BERKLEY", Font.BOLD, optionsTextSize));
		credits.setForeground(new Color (0, 105, 149));
		credits.setSize(creditsButtonLength, buttonHeight);
		credits.setLocation(creditsButtonX, creditsButtonY);
	}

	public void drawSelector (Graphics g){
		if (inPlayButton) {
			drawLeftTank(g, playButtonX - 40, playButtonY + buttonHeight/2, buttonHeight - 40);
			drawRightTank(g, playButtonX + playButtonLength + 40, playButtonY + buttonHeight/2, buttonHeight - 40);
		}
		else if (inInstructionButton) {
			drawLeftTank(g, instructionsButtonX - 40, instructionsButtonY + buttonHeight/2, buttonHeight - 40);
			drawRightTank(g, instructionsButtonX + instructionsButtonLength + 40, instructionsButtonY + buttonHeight/2, buttonHeight - 40);

		}
		else if (inCreditsButton) {
			drawLeftTank(g, creditsButtonX - 40, creditsButtonY + buttonHeight/2, buttonHeight - 40);
			drawRightTank(g, creditsButtonX + creditsButtonLength + 40, creditsButtonY + buttonHeight/2, buttonHeight - 40);
		}

		setOnButton(mouseX, mouseY);
	}

	public void drawLeftTank (Graphics g, int rightX, int centerY, int height) {
		int length = 2*height;
		int leftX = rightX - length;
		int leftY = centerY - height/2;
		g.setColor(new Color (177,218,250));	

		// Draw body top of tank
		g.fillRect(leftX + length/4, leftY, length/2, height);
		// Draw tracks of tank
		g.fillRect(leftX + (int)Math.ceil(length/8.0), leftY + height/2, (int)Math.ceil(3*length/4.0), (int)Math.ceil(height/2.0));
		g.fillOval(leftX, leftY + height/2, (int)Math.ceil(length/4.0), (int)Math.ceil(length/4.0));
		g.fillOval(leftX + (int)Math.floor(3*length/4.0), leftY + height/2, (int)Math.ceil(length/4.0), (int)Math.ceil(length/4.0));
		// Draw side plates
		int [] xTriangle1 = { leftX + length/4, leftX + length/4, leftX + (int)Math.ceil(length/8.0) };
		int [] yTriangle1 = { leftY, leftY + height/2, leftY + height/2 };
		g.fillPolygon(xTriangle1, yTriangle1, 3);

		int [] xTriangle2 = { leftX + (int)Math.floor(3*length/4.0), leftX + (int)Math.floor(3*length/4.0), leftX + (int)Math.floor(7*length/8.0) };
		int [] yTriangle2 = { leftY, leftY + height/2, leftY + height/2 };
		g.fillPolygon(xTriangle2, yTriangle2, 3);

		// Draw Arm
		double radians = leftArmAngle*Terrain.RADS;

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		g2.setColor(new Color (177,218,250));
		g2.rotate(radians, leftX + length/2, centerY + height/4);
		g2.fillRect(leftX + length/2, centerY + height/4, 10, 45);
		g2.setTransform(resetForm);
	}

	public void drawRightTank (Graphics g, int leftX, int centerY, int height) {
		int length = 2*height;
		int leftY = centerY - height/2;
		g.setColor(new Color (177,218,250));	

		// Draw body top of tank
		g.fillRect(leftX + length/4, leftY, length/2, height);
		// Draw tracks of tank
		g.fillRect(leftX + (int)Math.ceil(length/8.0), leftY + height/2, (int)Math.ceil(3*length/4.0), (int)Math.ceil(height/2.0));
		g.fillOval(leftX, leftY + height/2, (int)Math.ceil(length/4.0), (int)Math.ceil(length/4.0));
		g.fillOval(leftX + (int)Math.floor(3*length/4.0), leftY + height/2, (int)Math.ceil(length/4.0), (int)Math.ceil(length/4.0));
		// Draw side plates
		int [] xTriangle1 = { leftX + length/4, leftX + length/4, leftX + (int)Math.ceil(length/8.0) };
		int [] yTriangle1 = { leftY, leftY + height/2, leftY + height/2 };
		g.fillPolygon(xTriangle1, yTriangle1, 3);

		int [] xTriangle2 = { leftX + (int)Math.floor(3*length/4.0), leftX + (int)Math.floor(3*length/4.0), leftX + (int)Math.floor(7*length/8.0) };
		int [] yTriangle2 = { leftY, leftY + height/2, leftY + height/2 };
		g.fillPolygon(xTriangle2, yTriangle2, 3);


		double radians = (leftArmAngle + 230)*Terrain.RADS;

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform resetForm = g2.getTransform();
		g2.setColor(new Color (177,218,250));
		g2.rotate(radians, leftX + length/2, centerY + height/4 - 5);
		g2.fillRect(leftX + length/2, centerY + height/4 - 5, 10, 45);
		g2.setTransform(resetForm);
	}


	public void mouseClicked(MouseEvent e) {
		if (inPlayButton){
			Screen.startGame();
		}
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

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX(); int mouseY = e.getY();
		setMouseXY(mouseX, mouseY);
	}

	// Getters
	public static boolean getInPlayButton () { return inPlayButton; }
	public static boolean getInInstructionButton () { return inInstructionButton; }
	public static boolean getInCreditsButton () { return inCreditsButton; }

	// Setters
	public void setMouseXY (int mouseX, int mouseY) { this.mouseX = mouseX; this.mouseY = mouseY; }
	public void setOnButton (int mouseX, int mouseY) { // Determine if mouse hovers over an option
		if (mouseY > playButtonY && mouseY < playButtonY + buttonHeight &&
				mouseX > playButtonX && mouseX < playButtonX + playButtonLength) {
			inPlayButton = true; inInstructionButton = false; inCreditsButton = false;
		}
		else if (mouseY > instructionsButtonY && mouseY < instructionsButtonY + buttonHeight &&
				mouseX > instructionsButtonX && mouseX < instructionsButtonX + instructionsButtonLength) {
			inPlayButton = false; inInstructionButton = true; inCreditsButton = false;
		}
		else if (mouseY > creditsButtonY && mouseY < creditsButtonY + buttonHeight &&
				mouseX > creditsButtonX && mouseX < creditsButtonX + creditsButtonLength) {
			inPlayButton = false; inInstructionButton = false; inCreditsButton = true;
		}
		else {
			inPlayButton = false; inInstructionButton = false; inCreditsButton = false;
		}
	}
}

