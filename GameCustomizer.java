package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

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
	int [] tankTeam = new int [Constants.MAX_PLAYERS];
	int [] tankTops = new int [Constants.MAX_PLAYERS];
	int [] tankTracks = new int [Constants.MAX_PLAYERS];
	int [] tankColor = new int [Constants.MAX_PLAYERS];

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

	static ArrayList <Scrollers> swapTankTop = new ArrayList <Scrollers> ();
	static ArrayList <Scrollers> swapTankTrack = new ArrayList <Scrollers>();

	static ArrayList <TextReferences> players = new ArrayList<TextReferences>();
	static String [] playerNames = { "Player 1", "Player 2", "Player 3", "Player 4" };

	static TextReferences HP;
	static ArrayList <TextReferences> HPs = new ArrayList <TextReferences>();
	static int [] HPamounts = { 100, 150, 200, 300, 500 };

	static TextReferences fuel;
	static ArrayList <TextReferences> fuels = new ArrayList <TextReferences>();
	static int [] fuelAmounts = { 50, 100, 150, 175 };

	static TextReferences battle;
	static ArrayList <TextReferences> battles = new ArrayList <TextReferences>();
	static String [] battleType = { "Free For All", "Team Battle" };

	static ArrayList <TextReferences> team = new ArrayList <TextReferences>();
	static ArrayList <TextReferences> teams = new ArrayList <TextReferences>();
	static ArrayList <Scrollers> teamOption = new ArrayList <Scrollers>();
	static int [] onTeam = { 0, 1, 2 };

	static ArrayList <ColorSelector> colors = new ArrayList <ColorSelector> ();

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

	static int teamBoxX = 320;
	static int teamBoxLength = 100;
	static int tankBoxX = 430;


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
		
		for (TextReferences t: players) add(t);
		for (TextReferences t: HPs) add(t);
		for (TextReferences t: fuels) add(t);
		for (TextReferences t: battles) add(t);
		for (TextReferences t: team) add(t);
		for (TextReferences t: teams) add(t);

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

		// Set all text other object JLabels
		Font fontBold = new Font("Arial", Font.BOLD, 20);
		Color color = Color.white;

		// Set HP label objects (part of selection menu)
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

		// Fuel options
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

		int playerNumberHeight = playerBoxHeight/5;
		int boxHeight = (playerBoxHeight - playerNumberHeight)/4;

		// Set objects for Player Numbers
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			Font playerFont = new Font("Arial", Font.BOLD, 35);
			int X = playerBoxX + 20;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight;
			String text = "Player " + Integer.toString(a + 1);
			TextReferences txt = new TextReferences(X, Y, text, playerBoxLength, playerBoxHeight/5, playerFont, color, a);
			txt.setAllignmentLeft();
			players.add(txt);
		}
		// Initialize objects to make the text TEAM in the team selector box
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			int Y = playerBoxY + playerNumberHeight + a*boxHeight;
			int X = teamBoxX;
			String text = "Team";
			TextReferences txt = new TextReferences(X, Y, text, teamBoxLength, playerBoxHeight/5, fontBold, color, a);
			txt.setAllignmentTop();
			team.add(txt);
		}

		// Draw scrollers for team options
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			int scrollerLength = 10;
			int scrollerHeight = 8;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight + boxHeight/2;
			int X1 = teamBoxX + teamBoxLength/4 - scrollerLength;
			int X2 = teamBoxX + 3*teamBoxLength/4;
			Scrollers scroll = new Scrollers(X1, Y, X2, Y, scrollerLength, scrollerHeight, a, 
					color, color);
			System.out.println(scroll.colorLeft);
			teamOption.add(scroll);
		}

		// Labels for team number
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			int Y = playerBoxY + playerNumberHeight + a*boxHeight + 5*boxHeight/12;
			int X = teamBoxX;
			String text = "?";
			TextReferences txt = new TextReferences(X, Y, text, teamBoxLength, playerBoxHeight/5, fontBold, color, a);
			txt.setAllignmentTop();
			teams.add(txt);
		}

		// Initialize scrollers to change tank top
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			int scrollerLength = 10;
			int scrollerHeight = 8;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight + boxHeight/4;
			int X1 = tankBoxX + teamBoxLength/5 - scrollerLength;
			int X2 = tankBoxX + 4*teamBoxLength/5;
			Scrollers scroll = new Scrollers(X1, Y, X2, Y, scrollerLength, scrollerHeight, a, 
					color, color);
			swapTankTop.add(scroll);
		}
		// Scrollers for tank bottom
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			int scrollerLength = 10;
			int scrollerHeight = 8;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight + boxHeight/2;
			int X1 = tankBoxX + teamBoxLength/5 - scrollerLength;
			int X2 = tankBoxX + 4*teamBoxLength/5;
			Scrollers scroll = new Scrollers(X1, Y, X2, Y, scrollerLength, scrollerHeight, a, 
					color, color);
			swapTankTrack.add(scroll);
		}

		// Initialize color boxes
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			for (int b = 0; b < Constants.NUM_COLORS - 1; b++) {
				int colorBoxLength = 10;
				int X = tankBoxX + b*teamBoxLength/(Constants.NUM_COLORS - 1) + 
						teamBoxLength/(2*(Constants.NUM_COLORS - 1)) - colorBoxLength/2;
				int Y = playerBoxY + playerNumberHeight + a*boxHeight;

				Color boxColor;
				if (a > numPlayers - 1 || gameType == Constants.TEAM) {
					boxColor = new Color (40, 40, 40);
				}
				else {
					if (b == Constants.TANK_COLOR_GREEN) DrawTank.colorGreen();
					else if (b == Constants.TANK_COLOR_RED) DrawTank.colorRed();
					else if (b == Constants.TANK_COLOR_BLUE) DrawTank.colorBlue();
					else if (b == Constants.TANK_COLOR_PINK) DrawTank.colorPink(); 

					boxColor = DrawTank.tankTrackTop;
				}

				ColorSelector select = new ColorSelector(X, Y, colorBoxLength, 
						colorBoxLength, boxColor, b, a);
				colors.add(select);
			}
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
		for (TextReferences t: HPs) 
			t.setMouseIsIn(mouseX, mouseY);
		for (TextReferences t: fuels) 
			t.setMouseIsIn(mouseX, mouseY);
		for (TextReferences t: battles) 
			t.setMouseIsIn(mouseX, mouseY);
		for (Scrollers s: teamOption)
			s.setIsInScroller(mouseX, mouseY);
		for (Scrollers s: swapTankTop)
			s.setIsInScroller(mouseX, mouseY);
		for (Scrollers s: swapTankTrack)
			s.setIsInScroller(mouseX, mouseY);
		for (ColorSelector c: colors)
			c.setMouseIsIn(mouseX, mouseY);
	}

	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw background
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, 
				Terrain.HEIGHT, new Color (4, 8, 55)));	
		g.fillRect(0, 0, Terrain.LENGTH, Terrain.HEIGHT);

		// Draw land
		g2.setPaint(new GradientPaint(0, landDrawY, new Color (124, 203, 255), 
				0, Terrain.HEIGHT, new Color (0, 0, 75)));
		g2.fillRect(0, landDrawY, Terrain.LENGTH, Terrain.HEIGHT - landDrawY);


		drawPlayerBox(g);
		drawMapOption(g);
		drawStartButton(g);
		// Draw scrollers for map
		drawScrollers(g);
		drawColorSelectors(g);
		drawTanks(g);
		// Checks if mouse is in any of the text reference objects
		updateGameOptions();
	}

	public void drawScrollers (Graphics g) {
		for (Scrollers s: teamOption)
			s.drawScrollers(g);
		for (Scrollers s: swapTankTop)
			s.drawScrollers(g);
		for (Scrollers s: swapTankTrack)
			s.drawScrollers(g);
	}
	public void drawColorSelectors (Graphics g) {
		for (ColorSelector c: colors) {
			if (c.tankNum > numPlayers - 1 || gameType == Constants.TEAM)
				c.setBoxColor(new Color (40,40,40));
			else {
				if (c.colorID == Constants.TANK_COLOR_GREEN) DrawTank.colorGreen();
				else if (c.colorID == Constants.TANK_COLOR_RED) DrawTank.colorRed();
				else if (c.colorID == Constants.TANK_COLOR_BLUE) DrawTank.colorBlue();
				else if (c.colorID == Constants.TANK_COLOR_PINK) DrawTank.colorPink(); 
				c.setBoxColor(DrawTank.tankTrackTop);
			}
			c.drawColorSelector(g);
		}
	}

	public void drawTanks (Graphics g) {
		for (int a = 0; a < Constants.MAX_PLAYERS; a++) {
			int playerNumberHeight = playerBoxHeight/5;
			int boxHeight = (playerBoxHeight - playerNumberHeight)/4;
			int Y = playerBoxY + playerNumberHeight + a*boxHeight + 5*boxHeight/8;
			int X = tankBoxX + teamBoxLength/2;
			if (a > numPlayers - 1) DrawTank.colorGrey();
			else {
				if (tankColor[a] == Constants.TANK_COLOR_GREEN) DrawTank.colorGreen();
				else if (tankColor[a] == Constants.TANK_COLOR_RED) DrawTank.colorRed();
				else if (tankColor[a] == Constants.TANK_COLOR_BLUE) DrawTank.colorBlue();
				else if (tankColor[a] == Constants.TANK_COLOR_PINK) DrawTank.colorPink();
			}
			DrawTank.drawCustomTank(g, X, Y, Constants.CUSTOM_TANK_HEIGHT, 0, 340, tankTops[a], tankTracks[a]);
		}
	}
	public void drawPlayerBox (Graphics g) {
		// Spaces between each row
		int openSpace = 10;

		int playerNumberHeight = playerBoxHeight/5;
		int playersOptionsX = playerBoxX + openSpace;
		int playersOptionsLength = 275;
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
			else {
				g2.setPaint(new GradientPaint(0, Y, new Color (1, 1, 13), 0,
						Y + boxHeight, new Color (4, 8, 55)));
			}
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

		// If team is a valid option, then set text to white, else grey
		for (TextReferences t: team) {
			if (gameType == Constants.FFA) t.setTextColor(new Color(80, 80, 80));
			else {
				if (t.textReferenceID > numPlayers - 1) t.setTextColor(new Color(80, 80, 80));
				else t.setTextColor(Color.white);
			}
		}
		for (TextReferences t: teams) {
			if (gameType == Constants.FFA) t.setTextColor(new Color(80, 80, 80));
			else {
				if (t.textReferenceID > numPlayers - 1) t.setTextColor(new Color(80, 80, 80));
				else t.setTextColor(Color.white);
			}
		}
		// Check number of players in order to grey out correct ones
		for (TextReferences t: players) {
			if (t.textReferenceID > numPlayers - 1) t.setTextColor(new Color(80, 80, 80));
			else t.setTextColor(Color.white);
		}
		for (Scrollers s: teamOption) {
			if (gameType == Constants.FFA) s.setColors((new Color(80, 80, 80)), (new Color(80, 80, 80)));
			else {
				if (s.scrollerID > numPlayers - 1) s.setColors((new Color(80, 80, 80)), (new Color(80, 80, 80)));
				else s.setColors(Color.white, Color.white);
			}
		}

		for (Scrollers s: swapTankTop) {
			if (s.scrollerID > numPlayers - 1) s.setColors((new Color(80, 80, 80)), (new Color(80, 80, 80)));
			else s.setColors(Color.white, Color.white);
		}
		for (Scrollers s: swapTankTrack) {
			if (s.scrollerID > numPlayers - 1) s.setColors((new Color(80, 80, 80)), (new Color(80, 80, 80)));
			else s.setColors(Color.white, Color.white);
		}

		drawPlayerNumScroller(g2, 20, 10);

	}

	public void drawMapOption (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw background
		g2.setPaint(new GradientPaint(0, mapBoxY, new Color (44, 44, 44), 0, 
				mapBoxHeight + mapBoxY, new Color (24, 24, 24)));	
		g.fillRect(mapBoxX, mapBoxY, mapBoxLength, mapBoxHeight);

		// Calculate height of display map
		int mapHeight = mapDisplayLength*Control.controlPanelY/Terrain.LENGTH;

		// Draw sky for mini map
		g2.setPaint(new GradientPaint(0, mapDisplayY, new Color (1, 1, 13), 0, 
				mapDisplayY + mapHeight, new Color (4, 8, 55)));
		g.fillRect(mapDisplayX, mapDisplayY, mapDisplayLength, mapHeight);

		Terrain terrain = new Terrain(mapNum);
		int [] tempLandY = terrain.getLand(mapNum);
		int [] landY = new int [952];

		for (int i = 0; i < landY.length; i ++) landY [i] = tempLandY [i];

		landY = resizeLandY(landY, mapHeight);

		int [] landX = new int [952];
		landX = miniMapLandX(mapHeight);

		g2.setPaint(new GradientPaint(0, mapDisplayY, new Color (124, 203, 255), 0, 
				mapDisplayY + mapHeight, new Color (0, 0, 75)));
		g2.fillPolygon(landX, landY, 952);

		drawMapScrollers(g2, mapHeight);
		mapNameLabel();
	}

	public void drawPlayerNumScroller (Graphics g, int height, int distance) {

		// Left arrow
		int [] triangleX1 = {numPlayersTitleX - height - distance, 
				numPlayersTitleX - distance, numPlayersTitleX - distance };
		int [] triangleY = {numPlayersTitleY + playerTitleHeight/2, 
				numPlayersTitleY + playerTitleHeight/2 + height/2, 
				numPlayersTitleY + playerTitleHeight/2 - height/2};

		if (numPlayers == Constants.MIN_PLAYERS)
			g.setColor(new Color (64,64,64));
		else 
			g.setColor(Color.white);

		g.fillPolygon(triangleX1, triangleY, 3);

		int [] triangleX2 = {numPlayersTitleX + numPlayersTitleLength + distance + height, 
				numPlayersTitleX + numPlayersTitleLength + distance, 
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
		int [] triangleX1 = {centerX1 - mapScrollerLength/2, 
				centerX1 + mapScrollerLength/2, centerX1 + mapScrollerLength/2 };
		int [] triangleY1 = {centerY, centerY - mapScrollerHeight/2, centerY + mapScrollerHeight/2};
		g.fillPolygon(triangleX1, triangleY1, 3);

		// Draw right scroller arrow
		int centerX2 = mapBoxX+mapBoxLength-distanceBetweenBoxAndMiniMap/2;
		int [] triangleX2 = {centerX2 + mapScrollerLength/2, 
				centerX2 - mapScrollerLength/2, centerX2 - mapScrollerLength/2};
		g.fillPolygon(triangleX2, triangleY1, 3);

		setInMapScroller(mouseX, mouseY, centerY, centerX1, centerX2);
		setIsInStartGame(mouseX, mouseY);
	}

	public void drawStartButton (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw button background
		if (inStartGame)
			g2.setPaint(new GradientPaint(0, startGameY, new Color (205, 20, 0), 
					0, startGameY+startGameHeight, new Color (121, 12, 0)));
		else
			g2.setPaint(new GradientPaint(0, startGameY, new Color (206, 192, 0), 
					0, startGameY+startGameHeight, new Color (152, 92, 2)));
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
		if (inStartGame && (teamsBalanced()|| gameType== Constants.FFA)) {
			Screen.startGame();
			Screen.setMaxHealth(maxHP);
			Screen.setMaxFuel(maxFuel);
			Screen.setGameMode(gameType);
			Screen.setMap(mapNum);
			Screen.setPlayers(numPlayers);
			Screen.setTankColor(tankColor);
			Screen.setTankTeam(tankTeam);
			Screen.setTankTops(tankTops);
			Screen.setTankTracks(tankTracks);
		}
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

		// Set false to check for other clicks
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

		// Update scrollers
		if (gameType != Constants.FFA) {
			for (Scrollers s: teamOption) {
				if (s.getIsInLeftScroller() && s.scrollerID < numPlayers) {
					tankTeam[s.scrollerID]--;
					if (tankTeam[s.scrollerID] < 0) 
						tankTeam[s.scrollerID] = onTeam.length - 1;

					if (tankTeam[s.scrollerID] == 1) tankColor[s.scrollerID] = Constants.TANK_COLOR_GREEN;
					else if (tankTeam[s.scrollerID] == 2) tankColor[s.scrollerID] = Constants.TANK_COLOR_RED;
				}
				else if (s.getIsInRightScroller() && s.scrollerID < numPlayers) {
					tankTeam[s.scrollerID]++;
					tankTeam[s.scrollerID] = tankTeam[s.scrollerID]%onTeam.length;

					if (tankTeam[s.scrollerID] == 1) tankColor[s.scrollerID] = Constants.TANK_COLOR_GREEN;
					else if (tankTeam[s.scrollerID] == 2) tankColor[s.scrollerID] = Constants.TANK_COLOR_RED;
				}
			}
			for (TextReferences t: teams) {
				String txt;
				if (tankTeam[t.textReferenceID] == 0)
					txt = "?";
				else 
					txt = Integer.toString(tankTeam[t.textReferenceID]);
				t.setLabelText(txt);
			}
		}

		// Scrollers for customizing tanks
		for (Scrollers s: swapTankTop) {
			if (s.getIsInLeftScroller() && s.scrollerID < numPlayers) {
				tankTops[s.scrollerID]--;
				if (tankTops[s.scrollerID] < 0) 
					tankTops[s.scrollerID] = Constants.TANK_TOP_CIRCLE;
			}
			else if (s.getIsInRightScroller() && s.scrollerID < numPlayers) {
				tankTops[s.scrollerID]++;
				tankTops[s.scrollerID] = tankTops[s.scrollerID]%(Constants.TANK_TOP_CIRCLE+1);
			}
		}
		for (Scrollers s: swapTankTrack) {
			if (s.getIsInLeftScroller() && s.scrollerID < numPlayers) {
				tankTracks[s.scrollerID]--;
				if (tankTracks[s.scrollerID] < 0) 
					tankTracks[s.scrollerID] = Constants.TANK_TRACK_MODERN;
			}
			else if (s.getIsInRightScroller() && s.scrollerID < numPlayers) {
				tankTracks[s.scrollerID]++;
				tankTracks[s.scrollerID] = tankTracks[s.scrollerID]%(Constants.TANK_TRACK_MODERN+1);
			}
		}

		// Colors for customizing tanks
		for (ColorSelector c: colors) {
			if (gameType == Constants.FFA) {
				if (c.getMouseIsIn() && c.tankNum < numPlayers) tankColor[c.tankNum] = c.colorID;
			}
			else {
				if (tankTeam[c.tankNum] == 1) tankColor[c.tankNum] = Constants.TANK_COLOR_RED;
				else tankColor[c.tankNum] = Constants.TANK_COLOR_GREEN;
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
	
	private boolean teamsBalanced() {
		int team = tankTeam [0];
		for (int i = 0; i < tankTeam.length; i++){
			if (tankTeam [i] != team || tankTeam[i] == 0)
				return true;
			}
		return false;
		}
}