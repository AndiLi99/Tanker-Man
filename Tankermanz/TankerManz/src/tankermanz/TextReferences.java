package tankermanz;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TextReferences extends JLabel{
	int X;
	int	Y;
	String text;
	int length;
	int height;
	Font font;
	Color textColor;
	int textReferenceID;
	boolean mouseIsIn = false;
	boolean isSelected = false;

	public TextReferences (int X, int Y, String text, int length, int height, Font font, Color textColor, int textReferenceID) {
		this.X = X; this.Y = Y; 
		this.text = text;
		this.length = length; this.height = height; 
		this.font = font;
		this.textColor = textColor;
		this.textReferenceID = textReferenceID;
		setLabel();
	}

	public int getX () { return X; }
	public int getY () { return Y; }
	public boolean getMouseIsIn () { return mouseIsIn; }

	public void setLabel () {
		if (isSelected)
			setForeground(new Color (0, 153, 0));
		else
			setForeground(textColor);
		setFont(font);
		setText(text);
		setSize(length, height);
		setLocation(X, Y);
	}
	public void setMouseIsIn (int mouseX, int mouseY) {
		if (mouseX > X && mouseX < X + length) {
			if (mouseY > Y && mouseY < Y + height)
				mouseIsIn = true;
			else mouseIsIn = false;
		}
		else mouseIsIn = false;
	}
	public void setAllignmentLeft () {
		setHorizontalAlignment(SwingConstants.LEFT);
		setVerticalAlignment(SwingConstants.CENTER);
	}
	public void setAllignmentCenter () {
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
	}
	public void setAllignmentTop () {
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.TOP);
	}
	public void setLabelText (String text) {
		this.text = text;
		setText(text);
	}
	public void setColorGreen () {
		if (isSelected)
			setForeground(new Color (0, 153, 0));
		else
			setForeground(textColor);
	}
	public void setTextColor (Color textColor) {
		this.textColor = textColor;
		setForeground(textColor);
	}
}
