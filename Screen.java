package tankermanz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


//andi
public class Screen {
	/*	Map: 
	 * 	#1 - Hill
	 *  #2 - Exponential
	 *  #3 - Curved Flat Plain
	 *  #4 - Double Stair Step
	 *  #5 - Empire
	 */
	static GameScreen gameScreen;
//	static Control control;
	static MenuScreen menuScreen;
	static CreditsScreen creditsScreen;
	static GameCustomizer gameCustomizer;
	
	static int currentScreen;
	static int startMap = 3;
	static int maxFuel= 100;
	static int maxHealth = 300;
	
	
	
	
	static final int DELAY = 5;
	static final int MENU_SCREEN = 1;
	static final int GAME_SCREEN = 4;
	static final int CREDIT_SCREEN = 3;
	static final int INSTRUCTION_SCREEN = 2;
	
	
	static JFrame frame;
	static Timer animationTimer;
	static int numPlayers = 3;
	static boolean startGame;
	private static int gameMode = Constants.TEAM;
	
	static final int FREE_FOR_ALL = 1;
	static final int TEAM = 2;
	protected static final int GAME_CUSTOMIZER_SCREEN = 5;
	
	static JPanel mainScreen;
	private static int[] tankTeam;
	private static int[] tankTops;
	private static int[] tankTracks;
	private static int[] tankColor;	
	
	public static void main (String args[]){

		createAndShowGUI();

		
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (startGame){
				break;
			}
		}
		
		System.out.println("starting game");
		gameScreen = new GameScreen(startMap, numPlayers, gameMode, maxHealth, maxFuel, tankTeam, tankTops, tankTracks, tankColor);
		
	}
	
	  public void addComponentToPane(Container pane) {
		  //create the cards
		  menuScreen = new MenuScreen();
		  gameCustomizer = new GameCustomizer();

		  //create the panel to hold the cards
		  mainScreen = new JPanel(new CardLayout());
		  mainScreen.add(menuScreen, String.valueOf (MENU_SCREEN));
		  mainScreen.add(gameCustomizer, String.valueOf (GAME_CUSTOMIZER_SCREEN));
		  
		  pane.add(mainScreen, BorderLayout.CENTER);
	  }
	
	private static void createAndShowGUI(){
		frame = new JFrame ("Tanker Manz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Terrain.LENGTH,Terrain.HEIGHT);
		frame.setResizable (false);
		
		currentScreen = MENU_SCREEN;
		
		Screen screen = new Screen();
		screen.addComponentToPane (frame.getContentPane());
		
		animationTimer = new Timer(DELAY, animate);
		animationTimer.start();
//		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	public static void startGame (){
		frame.setVisible(false);
		changeScreen (MENU_SCREEN);
		startGame = true;
	}
	
	public static void changeScreen (int window){
		CardLayout c1 = (CardLayout)(mainScreen.getLayout());
		c1.show(mainScreen, String.valueOf(window));
		currentScreen = window;
	}
	
	public static void setMap(int startMap){
		Screen.startMap = startMap;
	}
	
	public static void setPlayers(int numPlayers){
		Screen.numPlayers = numPlayers;
	}
	
	public static void setGameMode (int gameMode){
		Screen.gameMode = gameMode;
	}
	
	public static void setMaxHealth (int maxHealth){
		Screen.maxHealth = maxHealth;
	}
	
	public static void setMaxFuel (int maxFuel){
		Screen.maxFuel = maxFuel;
	}
	
	public static void setTankTeam (int [] tankTeam){
		Screen.tankTeam = tankTeam;
	}
	
	public static void setTankTops(int [] tankTops){
		Screen.tankTops = tankTops;
	}
	
	public static void setTankTracks(int [] tankTracks){
		Screen.tankTracks = tankTracks;
	}
	
	public static void setTankColor (int [] tankColor){
		Screen.tankColor = tankColor;
	}
	
	static ActionListener animate = new ActionListener(){
	public void actionPerformed(ActionEvent arg0) {
		if (currentScreen == MENU_SCREEN)
			menuScreen.repaint();
		else if (currentScreen == CREDIT_SCREEN)
			creditsScreen.repaint();
//		System.out.println("repainting");
		else if (currentScreen == GAME_CUSTOMIZER_SCREEN)
			gameCustomizer.repaint();
		}
	};

}
