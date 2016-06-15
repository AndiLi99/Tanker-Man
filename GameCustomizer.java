package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameCustomizer extends JPanel implements MouseMotionListener, MouseListener{
	/*	Map Types: 
	 * 	#1 - Hill
	 *  #2 - Exponential
	 *  #3 - Curved Flat Plain
	 *  #4 - Double Stair Step
	 *  #5 - Empire
	 */

	// These are variables that will be used to start the game
	int mapNum = 1;
	int gameType = Constants.FFA;
	static int maxHP = 300;
	static int maxFuel = 100;
	static int numPlayers = 2;

	// Tank Variables
	int teamTank1 = 0; int teamTank2 = 0; int teamTank3 = 0; int teamTank4 = 0;
	int tankType1 = 0; int tankType2 = 0; int tankType3 = 0; int tankType4 = 0;
	int tankColor1 = 0; int tankColor2 = 0; int tankColor3 = 0; int tankColor4 = 0;
	String tankName1; String tankName2; String tankName3; String tankName4;

	// Variables used in this class
	int mouseX = 0; int mouseY = 0;

	static final int playerBoxX = 25;
	static final int playerBoxY = 25;
	static final int startGameX = 580;
	static final int startGameY = 340;
	static final int mapBoxX = 580;
	static final int mapBoxY = 25;
	static final int mapDisplayX = 625;
	static final int mapDisplayY = 80;
	static final int mapNameY = 50;

	static final int playerBoxLength = 525;	// Width and height of player box
	static final int playerBoxHeight = 375;
	static final int startGameLength = 340;	// Width and height of start game button
	static final int startGameHeight = 60;
	static final int mapBoxLength = 340;	// Width and height of grey map box outline
	static final int mapBoxHeight = 300;
	static final int mapDisplayLength = 250;	// Length of mini map 
	static final int mapScrollerLength = 20;	// W & H of triangles to move between maps
	static final int mapScrollerHeight = 30;

	static final int landDrawY = 425;

	static boolean inMapScrollerLeft = false;
	static boolean inMapScrollerRight = false;
	static boolean inStartGame = false;

	static JLabel mapName;
	static JLabel STARTGAME;

	static TextReferences HP;
	static ArrayList <TextReferences> HPs = new ArrayList <TextReferences>();
	static int [] HPamounts = { 100, 150, 200, 300, 500 };

	static TextReferences fuel;
	static ArrayList <TextReferences> fuels = new ArrayList <TextReferences>();
	static int [] fuelAmounts = { 50, 100, 150, 175 };

	static TextReferences battle;
	static ArrayList <TextReferences> battles = new ArrayList <TextReferences>();
	static String [] battleType = { "Free For All", "Team Battle" };
	
	static TextReferences team;
	static ArrayList <TextReferences> teams = new ArrayList <TextReferences>();

	static final int SUBTITLE_LENGTH = 250;
	static final int SUBTITLE_HEIGHT = 20;
	static final int SUBTITLE_X = 625;
	static final int SUBTITLE_Y = 190;

	static final int optionLengths = 40;
	static final int battleOptionLengths = 100;

	static JLabel playerTitle;
	static JLabel numPlayersTitle;
	static final int playerTitleX = 150;
	static final int playerTitleY = 30;
	static final int playerTitleLength = 150;
	static final int playerTitleHeight = 60;
	static final int numPlayersTitleX = 360;
	static final int numPlayersTitleY = 30;
	static final int numPlayersTitleLength = 40;

	static boolean decreaseNumPlayers = false;
	static boolean increaseNumPlayers = false;


	public GameCustomizer () {
		setLayout(null);
		labelStyle();

		add(mapName);
		add(STARTGAME);
		add(playerTitle);
		add(numPlayersTitle);

		add(HP);
		add(fuel);
		add(battle);
		for (TextReferences t: HPs) {
			add(t);
		}
		for (TextReferences t: fuels) {
			add(t);
		}
		for (TextReferences t: battles) {
			add(t);
		}

		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void labelStyle () {
		mapName = new JLabel();
		mapName.setForeground(Color.white);
		mapName.setFont(new Font("Arial", Font.BOLD, 30));
		mapName.setHorizontalAlignment(SwingConstants.CENTER);
		mapName.setVerticalAlignment(SwingConstants.CENTER);
		mapName.setSize(mapBoxLength, mapDisplayY-mapBoxY);
		mapName.setLocation(mapBoxX, mapBoxY);

		playerTitle = new JLabel("Players");
		playerTitle.setForeground(Color.white);
		playerTitle.setFont(new Font("Arial", Font.BOLD, 40));
		playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		playerTitle.setVerticalAlignment(SwingConstants.CENTER);
		playerTitle.setSize(playerTitleLength, playerTitleHeight);
		playerTitle.setLocation(playerTitleX, playerTitleY);

		numPlayersTitle = new JLabel(Integer.toString(numPlayers));
		numPlayersTitle.setForeground(Color.white);
		numPlayersTitle.setFont(new Font("Arial", Font.BOLD, 40));
		numPlayersTitle.setHorizontalAlignment(SwingConstants.CENTER);
		numPlayersTitle.setVerticalAlignment(SwingConstants.CENTER);
		numPlayersTitle.setSize(numPlayersTitleLength, playerTitleHeight);
		numPlayersTitle.setLocation(numPlayersTitleX, numPlayersTitleY);

		STARTGAME = new JLabel("Start Game");
		STARTGAME.setForeground(Color.BLACK);
		STARTGAME.setFont(new Font("Arial", Font.BOLD, 50));
		STARTGAME.setHorizontalAlignment(SwingConstants.CENTER);
		STARTGAME.setVerticalAlignment(SwingConstants.CENTER);
		STARTGAME.setSize(startGameLength, startGameHeight);
		STARTGAME.setLocation(startGameX, startGameY);

		Font fontBold = new Font("Arial", Font.BOLD, 20);
		Color color = Color.white;
		HP = new TextReferences(SUBTITLE_X, SUBTITLE_Y, "Player HP: 300", SUBTITLE_LENGTH, SUBTITLE_HEIGHT, fontBold, color, -1);

		Font fontPlain = new Font("Arial", Font.PLAIN, 15);
		for (int a = 0; a < HPamounts.length; a++) {
			int X = SUBTITLE_X + a*mapDisplayLength/HPamounts.length + (mapDisplayLength/HPamounts.length - optionLengths)/2;
			int Y = SUBTITLE_Y + SUBTITLE_HEIGHT;
			String text = Integer.toString(HPamounts[a]);
			TextReferences txt = new TextReferences(X, Y, text, optionLengths, SUBTITLE_HEIGHT, fontPlain, color, a);
			txt.setAllignmentCenter();
			if (HPamounts[a] == 300) {
				txt.isSelected = true;
				txt.setColorGreen();
			}
			HPs.add(txt);
		}

		fuel = new TextReferences(SUBTITLE_X, SUBTITLE_Y + 2*SUBTITLE_HEIGHT, "Player Fuel: 100", SUBTITLE_LENGTH, SUBTITLE_HEIGHT, fontBold, color, -1);
		for (int a = 0; a < fuelAmounts.length; a++) {
			int X = SUBTITLE_X + a*mapDisplayLength/fuelAmounts.length + (mapDisplayLength/fuelAmounts.length - optionLengths)/2;
			int Y = SUBTITLE_Y + 3*SUBTITLE_HEIGHT; 
			String text = Integer.toString(fuelAmounts[a]);
			TextReferences txt = new TextReferences(X, Y, text, optionLengths, SUBTITLE_HEIGHT, fontPlain, color, a);
			txt.setAllignmentCenter();
			if (fuelAmounts[a] == 100) {
				txt.isSelected = true;
				txt.setColorGreen();
			}
			fuels.add(txt);
		}

		battle = new TextReferences(SUBTITLE_X, SUBTITLE_Y + 4*SUBTITLE_HEIGHT, "Game: Free For All", SUBTITLE_LENGTH, SUBTITLE_HEIGHT, fontBold, color, -1);
		for (int a = 0; a < battleType.length; a++) {
			int X = SUBTITLE_X + a*mapDisplayLength/battleType.length + (mapDisplayLength/battleType.length - battleOptionLengths)/2;
			int Y = SUBTITLE_Y + 5*SUBTITLE_HEIGHT; 
			String text = battleType[a];
			TextReferences txt = new TextReferences(X, Y, text, battleOptionLengths, SUBTITLE_HEIGHT, fontPlain, color, a);
			txt.setAllignmentCenter();
			if (a == 0) {
				txt.isSelected = true;
				txt.setColorGreen();
			}
			battles.add(txt);
		}
	}

	
	
	public void mapNameLabel () {
		if (mapNum == Constants.HILL) 
			mapName.setText("Hill");
		else if (mapNum == Constants.EXPONENTIAL) 
			mapName.setText("Exponential");
		else if (mapNum == Constants.CURVED_PLAIN) 
			mapName.setText("Curved Flat Plain");
		else if (mapNum == Constants.DBL_STAIR_STEP) 
			mapName.setText("Double Stair Steps");
		else if (mapNum == Constants.EMPIRE)
			mapName.setText("Empire");
	}

	public void updateGameOptions () {
		for (TextReferences t: HPs) {
			t.setMouseIsIn(mouseX, mouseY);
		}
		for (TextReferences t: fuels) {
			t.setMouseIsIn(mouseX, mouseY);
		}
		for (TextReferences t: battles) 
			t.setMouseIsIn(mouseX, mouseY);
	}

	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw background
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, Terrain.HEIGHT, new Color (4, 8, 55)));	
		g.fillRect(0, 0, Terrain.LENGTH, Terrain.HEIGHT);

		// Draw land
		g2.setPaint(new GradientPaint(0, landDrawY, new Color (124, 203, 255), 0, Terrain.HEIGHT, new Color (0, 0, 75)));
		g2.fillRect(0, landDrawY, Terrain.LENGTH, Terrain.HEIGHT - landDrawY);

		// Checks if mouse is in any of the text fields
		for (TextReferences t: HPs) {
			t.setMouseIsIn(mouseX, mouseY);
		}

		drawPlayerBox(g);
		drawMapOption(g);
		drawStartButton(g);
		updateGameOptions();
	}

	public void drawPlayerBox (Graphics g) {
		// Spaces between each row
		int openSpace = 10;

		int playerNumberHeight = playerBoxHeight/5;
		int playersOptionsX = playerBoxX + openSpace;
		int playersOptionsLength = 275;
		int teamBoxX = 320;
		int teamBoxLength = 100;
		int tankBoxX = 430;
		int tankBoxLength = 100;
		Graphics2D g2 = (Graphics2D) g;
		// Draw background
		g2.setPaint(new GradientPaint(0, playerBoxY, new Color (0, 38, 87), 0,
				playerBoxY + playerBoxHeight, new Color (8, 27, 51)));
		g.fillRect(playerBoxX, playerBoxY, playerBoxLength, playerBoxHeight);

		// Draws player box
		for (int a = 0; a < 4; a++) {
			int boxHeight = (playerBoxHeight - playerNumberHeight)/4;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight;
			if (a > numPlayers - 1) {
				g2.setPaint(new GradientPaint(0, Y, new Color (25, 25, 25), 0,
						Y + boxHeight, new Color (40, 40, 40)));
			}
			else 
				g2.setPaint(new GradientPaint(0, Y, new Color (1, 1, 13), 0,
						Y + boxHeight, new Color (4, 8, 55)));
			g.fillRect(playersOptionsX, Y - openSpace/2, playersOptionsLength, boxHeight - openSpace);
		}

		// Draws Team Box
		for (int a = 0; a < 4; a++) {
			int boxHeight = (playerBoxHeight - playerNumberHeight)/4;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight;
			if (a > numPlayers - 1 || gameType != Constants.TEAM) {
				g2.setPaint(new GradientPaint(0, Y, new Color (25, 25, 25), 0,
						Y + boxHeight, new Color (40, 40, 40)));
			}
			else 
				g2.setPaint(new GradientPaint(0, Y, new Color (1, 1, 13), 0,
						Y + boxHeight, new Color (4, 8, 55)));
			g.fillRect(teamBoxX, Y - openSpace/2, teamBoxLength, boxHeight - openSpace);
		}

		// Draws tank box
		for (int a = 0; a < 4; a++) {
			int boxHeight = (playerBoxHeight - playerNumberHeight)/4;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight;
			if (a > numPlayers - 1) {
				g2.setPaint(new GradientPaint(0, Y, new Color (25, 25, 25), 0,
						Y + boxHeight, new Color (40, 40, 40)));
			}
			else 
				g2.setPaint(new GradientPaint(0, Y, new Color (1, 1, 13), 0,
						Y + boxHeight, new Color (4, 8, 55)));
			g.fillRect(tankBoxX, Y - openSpace/2, tankBoxLength, boxHeight - openSpace);
		}

		drawPlayerNumScroller(g2, 20, 10);

	}

	public void drawMapOption (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw background
		g2.setPaint(new GradientPaint(0, mapBoxY, new Color (44, 44, 44), 0, mapBoxHeight + mapBoxY, new Color (24, 24, 24)));	
		g.fillRect(mapBoxX, mapBoxY, mapBoxLength, mapBoxHeight);

		// Calculate height of display map
		int mapHeight = mapDisplayLength*Control.controlPanelY/Terrain.LENGTH;

		// Draw sky for mini map
		g2.setPaint(new GradientPaint(0, mapDisplayY, new Color (1, 1, 13), 0, mapDisplayY + mapHeight, new Color (4, 8, 55)));
		g.fillRect(mapDisplayX, mapDisplayY, mapDisplayLength, mapHeight);

		Terrain terrain = new Terrain(mapNum);
		int [] tempLandY = terrain.getLand(mapNum);
		int [] landY = new int [952];
		
		for (int i = 0; i < landY.length; i ++) landY [i] = tempLandY [i];
		
		landY = resizeLandY(landY, mapHeight);

		int [] landX = new int [952];
		landX = miniMapLandX(mapHeight);

		g2.setPaint(new GradientPaint(0, mapDisplayY, new Color (124, 203, 255), 0, mapDisplayY + mapHeight, new Color (0, 0, 75)));
		g2.fillPolygon(landX, landY, 952);

		drawMapScrollers(g2, mapHeight);
		mapNameLabel();
	}

	public void drawPlayerNumScroller (Graphics g, int height, int distance) {
		
		// Left arrow
		int [] triangleX1 = {numPlayersTitleX - height - distance, numPlayersTitleX - distance, numPlayersTitleX - distance };
		int [] triangleY = {numPlayersTitleY + playerTitleHeight/2, numPlayersTitleY + playerTitleHeight/2 + height/2, numPlayersTitleY + playerTitleHeight/2 - height/2};

		if (numPlayers == Constants.MIN_PLAYERS)
			g.setColor(new Color (64,64,64));
		else 
			g.setColor(Color.white);

		g.fillPolygon(triangleX1, triangleY, 3);
		
		int [] triangleX2 = {numPlayersTitleX + numPlayersTitleLength + distance + height, numPlayersTitleX + numPlayersTitleLength + distance, 
				numPlayersTitleX + numPlayersTitleLength + distance};
		if (numPlayers == Constants.MAX_PLAYERS)
			g.setColor(new Color (64,64,64));
		else 
			g.setColor(Color.white);

		g.fillPolygon(triangleX2, triangleY, 3);
		
		isInNumPlayer(mouseX, mouseY, triangleX1[0], triangleX2[1], triangleY[2], height);
	}

	public void drawMapScrollers (Graphics g, int miniMapHeight) {
		int distanceBetweenBoxAndMiniMap = mapDisplayX - mapBoxX;
		int centerX1 = mapBoxX + distanceBetweenBoxAndMiniMap/2;
		int centerY = mapDisplayY + miniMapHeight/2;

		g.setColor(Color.white);

		// Draw left scroller arrow
		int [] triangleX1 = {centerX1 - mapScrollerLength/2, centerX1 + mapScrollerLength/2, centerX1 + mapScrollerLength/2 };
		int [] triangleY1 = {centerY, centerY - mapScrollerHeight/2, centerY + mapScrollerHeight/2};
		g.fillPolygon(triangleX1, triangleY1, 3);

		// Draw right scroller arrow
		int centerX2 = mapBoxX+mapBoxLength-distanceBetweenBoxAndMiniMap/2;
		int [] triangleX2 = {centerX2 + mapScrollerLength/2, centerX2 - mapScrollerLength/2, centerX2 - mapScrollerLength/2};
		g.fillPolygon(triangleX2, triangleY1, 3);

		setInMapScroller(mouseX, mouseY, centerY, centerX1, centerX2);
		setIsInStartGame(mouseX, mouseY);
	}

	public void drawStartButton (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw button background
		if (inStartGame)
			g2.setPaint(new GradientPaint(0, startGameY, new Color (205, 20, 0), 0, startGameY+startGameHeight, new Color (121, 12, 0)));
		else
			g2.setPaint(new GradientPaint(0, startGameY, new Color (206, 192, 0), 0, startGameY+startGameHeight, new Color (152, 92, 2)));
		g.fillRect(startGameX, startGameY, startGameLength, startGameHeight);
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
		// Draw mini maps
		if (inMapScrollerLeft) {
			mapNum = mapNum - 1;
			if (mapNum < 1)
				mapNum = 5;
		}
		else if (inMapScrollerRight) 
			mapNum = mapNum%5 + 1;

		// Check if user wants to start game
		if (inStartGame) 
			Screen.startGame();
		
		if (increaseNumPlayers && numPlayers < Constants.MAX_PLAYERS) numPlayers ++;
		else if (decreaseNumPlayers && numPlayers > Constants.MIN_PLAYERS) numPlayers --;
		numPlayersTitle.setText(Integer.toString(numPlayers));

		// Update text selected
		boolean ifClickedIn = false;
		for (TextReferences t: HPs) {
			if (t.mouseIsIn) 
				ifClickedIn = true;
		}

		if (ifClickedIn) { // If clicked in the actual selector
			for (TextReferences t: HPs) {
				if (t.mouseIsIn) t.isSelected = true;
				else t.isSelected = false;
				// Update which one was clicked by changing colors
				t.setColorGreen();
				if (t.isSelected) {
					HP.setLabelText("Player HP: " + Integer.toString(HPamounts[t.textReferenceID]));
					maxHP = HPamounts[t.textReferenceID];
				}
			}
		}

		// Set false to check for fuel clicks
		ifClickedIn = false;
		for (TextReferences t: fuels) {
			if (t.mouseIsIn) 
				ifClickedIn = true;
		}

		if (ifClickedIn) { // If clicked in the actual selector
			for (TextReferences t: fuels) {
				if (t.mouseIsIn) t.isSelected = true;
				else t.isSelected = false;
				// Update which one was clicked by changing colors
				t.setColorGreen();
				if (t.isSelected) {
					fuel.setLabelText("Player Fuel: " + Integer.toString(fuelAmounts[t.textReferenceID]));
					maxFuel = fuelAmounts[t.textReferenceID];
				}
			}
		}

		// Set false to check for fuel clicks
		ifClickedIn = false;
		for (TextReferences t: battles) {
			if (t.mouseIsIn) 
				ifClickedIn = true;
		}

		if (ifClickedIn) { // If clicked in the actual selector
			for (TextReferences t: battles) {
				if (t.mouseIsIn) t.isSelected = true;
				else t.isSelected = false;
				// Update which one was clicked by changing colors
				t.setColorGreen();
				if (t.isSelected) {
					battle.setLabelText("Game Type: " + battleType[t.textReferenceID]);
					gameType = t.textReferenceID;
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX(); int mouseY = e.getY();
		setMouseXY(mouseX, mouseY);
	}

	public static int [] resizeLandY (int [] landY, int mapDisplayHeight) {
		double sizeRatio = mapDisplayLength/(double)Terrain.LENGTH;
		for (int a = 0; a < 950; a++){
			landY[a] = (int) (landY[a]*sizeRatio + mapDisplayY);

			if (landY[a] < mapDisplayY)
				landY[a] = mapDisplayY;
		}
		landY[950] = mapDisplayY + mapDisplayHeight;
		landY[951] = mapDisplayY + mapDisplayHeight;
		return landY;
	}

	public static int [] miniMapLandX (int mapHeight) {
		double sizeRatio = mapDisplayLength/(double)Terrain.LENGTH;
		int [] landX = new int [952];
		for (int a = 0; a < 950; a++) {
			landX[a] = (int)(Math.ceil(mapDisplayX + a*sizeRatio));
		}

		landX[950] = mapDisplayX + mapDisplayLength;
		landX[951] = mapDisplayX;

		return landX;
	}

	// Setters
	public void setMouseXY (int mouseX, int mouseY) { this.mouseX = mouseX; this.mouseY = mouseY; }
	public void setInMapScroller (int mouseX, int mouseY, int centerY, int centerX1, int centerX2) {
		if (mouseY > centerY - mapScrollerHeight/2 && mouseY < centerY + mapScrollerHeight/2) {
			if (mouseX > centerX1 - mapScrollerLength/2 && mouseX < centerX1 + mapScrollerLength/2) {
				inMapScrollerLeft = true;
				inMapScrollerRight = false;
			}
			else if (mouseX > centerX2 - mapScrollerLength/2 && mouseX < centerX2 + mapScrollerLength/2) {
				inMapScrollerLeft = false;
				inMapScrollerRight = true;
			}
			else {
				inMapScrollerLeft = false;
				inMapScrollerRight = false;
			}
		}
		else {
			inMapScrollerLeft = false;
			inMapScrollerRight = false;
		}
	}
	public void setIsInStartGame (int mouseX, int mouseY) {
		if (mouseX > startGameX && mouseX < startGameX + startGameLength) {
			if (mouseY > startGameY && mouseY < startGameY + startGameHeight)
				inStartGame = true;
			else 
				inStartGame = false;
		}
		else 
			inStartGame = false;
	}


	public static void isInNumPlayer (int mouseX, int mouseY, int leftTriangleX, int rightTriangleX, int topY, int height) {
		if (mouseY >topY && mouseY < topY + height) {
			if (mouseX > leftTriangleX && mouseX < leftTriangleX + height) {
				increaseNumPlayers = false;
				decreaseNumPlayers = true;
			}
			else if (mouseX > rightTriangleX && mouseX < rightTriangleX + height) {
				increaseNumPlayers = true;
				decreaseNumPlayers = false;
			}
		}
		else {
			increaseNumPlayers = false;
			decreaseNumPlayers = false;
		}
	}
	
	
}

