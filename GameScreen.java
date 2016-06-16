package tankermanz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GameScreen extends JFrame {
	/*	Map: 
	 * 	#1 - Hill
	 *  #2 - Exponential
	 *  #3 - Curved Flat Plain
	 *  #4 - Double Stair Step
	 *  #5 - Empire
	 */
	static Terrain terrain;
	static int startMap;
	static final int DELAY = 5;
	static Timer animationTimer;
	static String message;

	public GameScreen (int startMap, int numPlayers, int gameMode, int maxHealth, int maxFuel, int[] tankTeam, int[] tankTops, int[] tankTracks, int[] tankColor){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Terrain.LENGTH,Terrain.HEIGHT);
		this.setResizable (false);

		terrain = new Terrain(startMap,numPlayers, gameMode, maxHealth, maxFuel, tankTeam, tankTops, tankTracks, tankColor);
		this.add(terrain);

		animationTimer = new Timer(DELAY, animate);
		animationTimer.start();
		this.setVisible(true);

		gameLoop();

		if (terrain.getWin()){
			Screen.changeScreen(Screen.VICTORY_SCREEN);

			if (Terrain.getCurrentPlayer() == null) message = "It's A Tie!";
			else if (terrain.gameMode == Constants.FFA) message = Terrain.getCurrentPlayer().name + " Wins!";
			else if (terrain.gameMode == Constants.TEAM && Terrain.getCurrentPlayer().team == 2)
				message = "Green Team Wins!";
			else if (terrain.gameMode == Constants.TEAM && Terrain.getCurrentPlayer().team == 1)
				message = "Red Team Wins!";	
			Screen.victory(message);
		}
		else Screen.changeScreen(Screen.MENU_SCREEN);

		Screen.frame.setVisible(true);
		this.dispose();
	}

	public static void gameLoop(){
		long startTime = System.currentTimeMillis();
		long currTime = startTime;

		while (!terrain.getWin() && !terrain.getExit()) {
			int elapsedTime = (int)(System.currentTimeMillis() - currTime);
			currTime = System.currentTimeMillis();
			terrain.updateGame(elapsedTime);
			try {
				Thread.sleep(DELAY);
			}catch (InterruptedException e){
			}
		}

	}
	static ActionListener animate = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			terrain.repaint();
		}
	};

}
