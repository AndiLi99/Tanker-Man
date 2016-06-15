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
	static final int DELAY = 5;
	static final int MENU_SCREEN = 1;
	static final int GAME_SCREEN = 4;
	static final int CREDIT_SCREEN = 3;
	static final int INSTRUCTION_SCREEN = 2;
	
	
	static JFrame myWindow = new JFrame ();
	static Timer animationTimer;
	static int numPlayers = 3;
	static boolean startGame;
	private static int gameMode;
	
	static final int FREE_FOR_ALL = 1;
	static final int TEAM = 2;
	protected static final int GAME_CUSTOMIZER_SCREEN = 5;
	
	static JPanel mainScreen;	
	
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
		gameScreen = new GameScreen(startMap, numPlayers, gameMode);
		
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
		JFrame frame = new JFrame ("Tanker Manz");
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
		myWindow.dispose();
		startGame = true;
	}
	
	public static void changeScreen (int window){
		CardLayout c1 = (CardLayout)(mainScreen.getLayout());
		c1.show(mainScreen, String.valueOf(window));
		
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
