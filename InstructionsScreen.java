package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsScreen extends JPanel implements MouseMotionListener, MouseListener{
	Font ARBERKLEY;
	
	static final int tankX = 725; static final int tankY = 85;
	static final int angleTank = 335;
	static final int tankHeight = 18;
	static final int tankArmAngle = 325;
	static final int landDrawY = 400;
	int backLabelLength = 120; int backLabelHeight = 50;
	int backLabelX = 815; int backLabelY = 405;
	final int TEXT_SIZE = 50;
	boolean inBackButton = false;
	JLabel backButton;
	
	public InstructionsScreen() {
		try {
			ARBERKLEY = Font.createFont(Font.TRUETYPE_FONT, new File ("ARBERKLEY.ttf"));
			GraphicsEnvironment ge = 
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(ARBERKLEY);
		} catch (FontFormatException e) {
			System.out.println("no font found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(null);

		MenuScreen.setTitle();
		add(MenuScreen.gameTitleTanker);
		add(MenuScreen.gameTitleManz);
		
		setBackButton();
		add(backButton);
		
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

		g.setColor(Color.WHITE);
		g.setFont(new Font ("AR BERKLEY", Font.PLAIN, 40));
		g.setColor(new Color (0, 105, 149));
		g.drawString("A,D - Move Tank", 160, 180);
		g.drawString("W,S - Change Weapon", 160, 230);
		g.drawString("Left Right Arrows - Move Tank Arm", 160, 280);
		g.drawString("Up Down Arrows - Change Power", 160, 330);
		g.drawString("Space - Fire Weapon", 160, 380);
	}
	

	public void setBackButton () {
		backButton = new JLabel("Back");
		backButton.setFont(new Font("AR BERKLEY", Font.BOLD, TEXT_SIZE));
		backButton.setForeground(new Color (2, 10, 33));
		backButton.setSize(backLabelLength, backLabelHeight);
		backButton.setLocation(backLabelX, backLabelY);
	}

	public void setColorBackButton () {
		if (inBackButton)
			backButton.setForeground(new Color (191, 208, 255));
		else
			backButton.setForeground(new Color (2, 10, 33));
	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX(); int mouseY = e.getY();
		setInBackButton(mouseX, mouseY);
		setColorBackButton();
	}
	
	public void mouseReleased(MouseEvent e) {
		if (inBackButton)
			Screen.changeScreen(Screen.MENU_SCREEN);
	}

	// Getters
	public boolean getInBackButton () { return inBackButton; }

	// Setters
	public void setInBackButton (int mouseX, int mouseY) {
		if (mouseX > backLabelX && mouseX < backLabelX + backLabelLength) {
			if (mouseY > backLabelY && mouseY < backLabelY + backLabelHeight)
				inBackButton = true;
			else 
				inBackButton = false;
		}
		else 
			inBackButton = false;
	}
	
	// Unused auto generated methods
	public void mouseClicked(MouseEvent e) {
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
