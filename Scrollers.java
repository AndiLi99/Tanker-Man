package tankermanz;

import java.awt.Color;
import java.awt.Graphics;

public class Scrollers {
	// X and Y coordinates of top left of triangle scroller
	int X1; 
	int Y1;
	int X2;
	int Y2;
	
	int length;
	int height;
	
	int scrollerID;
	
	Color colorLeft;
	Color colorRight;
	
	// Stores is the mouse is in a scroller
	boolean isInLeftScroller;
	boolean isInRightScroller;
	
	public Scrollers (int X1, int Y1, int X2, int Y2, int length, int height, int scrollerID, Color colorLeft, Color colorRight) {
		this.X1 = X1; this.Y1 = Y1;
		this.X2 = X2; this.Y2 = Y2;
		this.length = length;
		this.height = height;
		this.scrollerID = scrollerID;
		this.colorLeft = colorLeft;
		this.colorRight = colorRight;
	}
	
	public void drawScrollers (Graphics g) {
		// Draw left scroller
		g.setColor(colorLeft);
		int [] triangleX1 = { X1, X1 + length, X1 + length };
		int [] triangleY1 = { Y1 + height/2, Y1, Y1 + height };
		g.fillPolygon(triangleX1, triangleY1, 3);
		
		// Draw right scroller
		g.setColor(colorRight);
		int [] triangleX2 = { X2 + length, X2, X2 };
		int [] triangleY2 = { Y2 + height/2, Y2, Y2 + height };
		g.fillPolygon(triangleX2, triangleY2, 3);
	}
	
	public boolean getIsInLeftScroller () { return isInLeftScroller; }
	public boolean getIsInRightScroller () { return isInRightScroller; }
	
	public void setIsInScroller (int mouseX, int mouseY) {
		if (mouseX > X1 && mouseX < X1 + length) {
			if (mouseY > Y1 && mouseY < Y1 + height) isInLeftScroller = true;
			else isInLeftScroller = false;
		}
		else isInLeftScroller = false;
		
		if (mouseX > X2 && mouseX < X2 + length) {
			if (mouseY > Y2 && mouseY < Y2 + height) isInRightScroller = true;
			else isInRightScroller = false;
		}
		else isInRightScroller = false;
	}

	public void setColors (Color left, Color right) {
		this.colorLeft = left;
		this.colorRight = right;
	}
}
