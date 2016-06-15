package tankermanz;

import java.awt.BorderLayout;
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
	
	public static void main (String args[]){
		animationTimer = new Timer(DELAY, animate);

		myWindow = new JFrame();
		menuScreen = new MenuScreen();
		
		currentScreen = MENU_SCREEN;
		myWindow.add(menuScreen);
	
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setSize(Terrain.LENGTH,Terrain.HEIGHT);
		myWindow.setResizable (false);
		
		
		
		animationTimer.start();
		myWindow.setVisible(true);
		
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
	
	public static void startGame (){
		myWindow.dispose();
		startGame = true;
	}
	
	
	static ActionListener animate = new ActionListener(){
	public void actionPerformed(ActionEvent arg0) {
		if (currentScreen == MENU_SCREEN)
			menuScreen.repaint();
		else if (currentScreen == CREDIT_SCREEN)
			creditsScreen.repaint();
//		System.out.println("repainting");
		}
	};
}
