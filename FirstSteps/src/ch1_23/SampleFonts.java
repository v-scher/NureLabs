package ch1_23;
// Show fonts.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/*
  <applet code="SampleFonts" width=200 height=100>
  </applet>
*/
 
public class SampleFonts extends Applet {
	int next = 0;
	Font f;
	String msg;
	public void init() {
		f = new Font("Dialog", Font.PLAIN, 12);
		msg = "Dialog";
		setFont(f);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
	    	    // Switch fonts with each mouse click.
	    	    next++;
	    	    switch(next) {
	    	    case 0:
	    	    	f = new Font("Dialog", Font.PLAIN, 12);
	    	    	msg = "Dialog";
	    	    	break;
	    	    case 1:
	    	    	f = new Font("DialogInput", Font.PLAIN, 12);
	    	    	msg = "DialogInput";
	    	    	break;
	    	    case 2:
	    	    	f = new Font("SansSerif", Font.PLAIN, 12);
	    	    	msg = "SansSerif";
	    	    	break;
	    	    case 3:
	    	    	f = new Font("Serif", Font.PLAIN, 12);
	    	    	msg = "Serif";
	    	    	break;
	    	    case 4:
	    	    	f = new Font("Monospaced", Font.PLAIN, 12);
	    	    	msg = "Monospaced";
	    	    	break;
	    	    }
	    	    if(next == 4) next = -1;
	    	    setFont(f);
	    	    repaint();
			}
		});
	}
  
	public void paint(Graphics g) {
		g.drawString(msg, 4, 20);
	}
}