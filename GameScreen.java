package tankermanz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	static int startMap = 3;
	static final int DELAY = 5;
	static Timer animationTimer;
	
	public GameScreen (int startMap, int numPlayers){
						
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Terrain.LENGTH,Terrain.HEIGHT);
		this.setResizable (false);
		
		terrain = new Terrain(startMap,numPlayers);
		this.add(terrain);
		
		animationTimer = new Timer(DELAY, animate);
		animationTimer.start();
		this.setVisible(true);

		gameLoop();
				
	}
	
	public static void gameLoop(){
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
//		System.out.println("before while");
		
		while (true) {
			int elapsedTime = (int)(System.currentTimeMillis() - currTime);
			currTime = System.currentTimeMillis();
//			System.out.println("before terrain update");
			terrain.updateGame(elapsedTime);
//			System.out.println("after terrain update");
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
