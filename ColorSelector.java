package tankermanz;

import java.awt.Color;
import java.awt.Graphics;

public class ColorSelector {
	int X; int Y;
	int length; int height;
	int colorID;
	int tankNum;
	Color color;
	
	boolean mouseIsIn = false;
	
	public ColorSelector (int X, int Y, int length, int height, Color color, int colorID, int tankNum) {
		// Set object variables
		this.X = X; this.Y = Y;
		this.length = length; this.height = height;
		this.color = color;
		this.colorID = colorID;
		this.tankNum = tankNum;
	}
	
	public void drawColorSelector (Graphics g) {
		g.setColor(color);
		g.fillRect(X, Y, length, height);
	}
	
	// Getter
	public boolean getMouseIsIn () { return mouseIsIn; }
	
	// Setters
	public void setMouseIsIn (int mouseX, int mouseY) {
		if (mouseX >= X && mouseX <= X + length) {
			if (mouseY >= Y && mouseY <= Y + height) mouseIsIn = true; // If mouse is in box
			else mouseIsIn = false;
		}
		else mouseIsIn = false;
	}
	public void setBoxColor (Color color) { this.color = color; }
}