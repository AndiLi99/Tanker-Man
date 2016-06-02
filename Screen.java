package org.tankermanz;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Screen {
	/*	Map: 
	 * 	#1 - Hill
	 *  #2 - Exponential
	 *  #3 - Curved Flat Plain
	 *  #4 - Double Stair Step
	 *  #5 - Empire
	 */
	static Terrain terrain;
	static Control control;
	
	static int startMap = 1;
	static final int DELAY = 20;
	
	public static void main (String args[]){
		Timer animationTimer = new Timer(DELAY, animate);
	
		
		JFrame myWindow = new JFrame ();
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setSize(Terrain.WIDTH,Terrain.HEIGHT);
		myWindow.setResizable (false);

		terrain = new Terrain(startMap, 3);
//		control = new Control();
		
		//JPanel myFrame = new JPanel();
		
	//	myFrame.add(terrain);
		myWindow.add(terrain);
//		myWindow.add(myFrame);
		myWindow.setVisible(true);

//		animationTimer.start();
		gameLoop();
		
		
	}
	
	public static void gameLoop(){
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		
		
		while (true) {
			int elapsedTime = (int)(System.currentTimeMillis() - currTime);
			currTime = System.currentTimeMillis();
			
			terrain.updateGame(elapsedTime);
			terrain.repaint();
			try {
				Thread.sleep(20);
			}catch (InterruptedException e){
			
			}
		}
		
	}
	
	static ActionListener animate = new ActionListener(){
	public void actionPerformed(ActionEvent arg0) {
		
		terrain.repaint();
		System.out.println ("repainting");
		}
	};
}
