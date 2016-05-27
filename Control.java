package org.tankermanz;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Control extends JPanel{

	JPanel controlPanel;
	
	JButton fireButton;
	
	JPanel weaponSelectPanel;
	JButton weaponSelectLeft;
	JButton weaponSelectRight;
	JLabel weaponSelectLabel;
	
	JButton menu;
	
	JPanel powerAnglePanel;
	JLabel powerLabel;
	JLabel angleLabel;
	
	JPanel fuelPanel;
	JLabel fuelLabel;
	JProgressBar fuelAvailable;
	
	ImageIcon weaponImage1;
	
	
	public Control (){
		controlPanel = new JPanel();
		
		fuelLabel = new JLabel("Fuel 100%");
		fuelAvailable = new JProgressBar();
		fuelPanel = new JPanel();
		fuelPanel.setLayout(new GridLayout(1,2));
		
		fuelPanel.add(fuelLabel);
		fuelPanel.add(fuelAvailable);
		controlPanel.add(fuelPanel);
		
		weaponSelectLeft = new JButton ("<");
		weaponSelectRight = new JButton(">");
		weaponSelectLabel = new JLabel("Tank Round");
		weaponSelectPanel = new JPanel ();
		
		weaponSelectPanel.add(weaponSelectLeft);
		weaponSelectPanel.add(weaponSelectLabel);
		weaponSelectPanel.add(weaponSelectRight);
		//weaponSelectPanel.add(weaponImageIcon);
		controlPanel.add(weaponSelectPanel);
		
		powerLabel = new JLabel("Power: 100");
		angleLabel = new JLabel("Angle: 45");
		powerAnglePanel = new JPanel();
		powerAnglePanel.setLayout(new GridLayout(1, 2));
		powerAnglePanel.add(powerLabel);
		powerAnglePanel.add(angleLabel);
		controlPanel.add(powerAnglePanel);
				
		fireButton = new JButton ("Fire!");
		controlPanel.add(fireButton);
				
	}
}
