package ch24_25_AWT;
// Demonstrate multiline output.
import java.applet.*;
import java.awt.*;
/*
<applet code="MultiLine" width=300 height=100>
</applet>
*/

public class Multiline extends Applet {
	int curX = 0;
	int curY = 0; // current position

	public void init() {
		setFont(new Font("SansSerif", Font.PLAIN, 12));
	}
	
	public void paint(Graphics g) {
	    nextLine("This is on line one.", g);
	    nextLine("This is on line two.", g);
	    sameLine(" This is on same line.", g);
	    sameLine(" This, too.", g);
	    nextLine("This is on line three.", g);
	    curX = 0;
	    curY = 0; // reset the coordinates for each repaint
	}

	// Advance to next line.
	void nextLine(String s, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	
	    curY += fm.getHeight(); // advance to next line
	    curX = 0;
	    g.drawString(s, curX, curY);
	    curX = fm.stringWidth(s); // advance to end of line
	}

	// Display on same line.
	void sameLine(String s, Graphics g) {
    FontMetrics fm = g.getFontMetrics();

    g.drawString(s, curX, curY);
    curX += fm.stringWidth(s); // advance to end of line
  }
}