package ch24_25_AWT;
import java.awt.*; 
import java.awt.event.*;
import java.applet.*;
/*
  <applet code="AppletFrame" width=300 height=50>
  </applet>
*/

// Create a subclass of Frame.
class SampleFrame extends Frame 
	implements MouseListener, MouseMotionListener{
	String msg = "";
	int mouseX=10, 
		mouseY=40;
	int movX=0, 
		movY=0;
	  
	SampleFrame(String title) {
		super(title);
		
		// register this object to receive its own mouse events
	    addMouseListener(this);
	    addMouseMotionListener(this);
		
		// create an object to handle window events
		// register it to receive those events
		addWindowListener(new WindowAdapter () {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
	}
	
	// Handle mouse clicked.
	  public void mouseClicked(MouseEvent me) {
	  }
	  

	  // Handle mouse entered.
	  public void mouseEntered(MouseEvent evtObj) {
	    // save coordinates
	    mouseX = 10;
	    mouseY = 54;
	    msg = "Mouse just entered child.";
	    repaint();
	  }

	  // Handle mouse exited.
	  public void mouseExited(MouseEvent evtObj) {
	    // save coordinates
	    mouseX = 10;
	    mouseY = 54;
	    msg = "Mouse just left child window.";
	    repaint();
	  }

	  // Handle mouse pressed.
	  public void mousePressed(MouseEvent me) {
	    // save coordinates
	    mouseX = me.getX();
	    mouseY = me.getY();
	    msg = "Down";
	    repaint();
	  }

	  // Handle mouse released.
	  public void mouseReleased(MouseEvent me) {
	    // save coordinates
	    mouseX = me.getX();
	    mouseY = me.getY();
	    msg = "Up";
	    repaint();
	  }

	  // Handle mouse dragged.
	  public void mouseDragged(MouseEvent me) {
	    // save coordinates
	    mouseX = me.getX();
	    mouseY = me.getY();
	    movX = me.getX();
	    movY = me.getY();
	    msg = "*";
	    repaint();
	  }

	  // Handle mouse moved.
	  public void mouseMoved(MouseEvent me) {
	    // save coordinates
	    movX = me.getX();
	    movY = me.getY();
	    repaint(0, 0, 100, 60);
	  }

	  public void paint(Graphics g) {
		g.drawString(msg, mouseX, mouseY);
	    g.drawString("Mouse at " + movX + ", " + movY, 10, 40);
	}    
}

// Create frame window.
public class WindiwEvents extends Applet
	implements MouseListener, MouseMotionListener{
	
	Frame f;
	String msg = "";
	int mouseX=0, mouseY=10;
	int movX=0, movY=0;
	  
	public void init() {
		f = new SampleFrame("A Frame Window");
		f.setSize(250, 250);
		f.setVisible(true);
		addMouseListener(this);
	    addMouseMotionListener(this);
	}
	
	public void start() {
		f.setVisible(true);
	}
	
	public void stop() {
		f.setVisible(false);
	}  
	
	// Handle mouse clicked.
	  public void mouseClicked(MouseEvent me) { }

	  // Handle mouse entered.
	  public void mouseEntered(MouseEvent me) {
	    // save coordinates
	    mouseX = 0;
	    mouseY = 24;
	    msg = "Mouse just entered applet window.";
	    repaint();
	  }

	  // Handle mouse exited.
	  public void mouseExited(MouseEvent me) {
	    // save coordinates
	    mouseX = 0;
	    mouseY = 24;
	    msg = "Mouse just left applet window.";
	    repaint();
	  }

	  // Handle button pressed.
	  public void mousePressed(MouseEvent me) {
	    // save coordinates
	    mouseX = me.getX();
	    mouseY = me.getY();
	    msg = "Down";
	    repaint();
	  }

	  // Handle button released.
	  public void mouseReleased(MouseEvent me) {
	    // save coordinates
	    mouseX = me.getX();
	    mouseY = me.getY();
	    msg = "Up";
	    repaint();
	  }
	  
	  // Handle mouse dragged.
	  public void mouseDragged(MouseEvent me) {
	    // save coordinates
	    mouseX = me.getX();
	    mouseY = me.getY();
	    movX = me.getX();
	    movY = me.getY();
	    msg = "*";
	    repaint();
	  }

	  // Handle mouse moved.
	  public void mouseMoved(MouseEvent me) {
	    // save coordinates
	    movX = me.getX();
	    movY = me.getY();
	    repaint(0, 0, 100, 20);
	  }
	  
	  public void paint(Graphics g) {
		g.drawString(msg, mouseX, mouseY);
	    g.drawString("Mouse at " + movX + ", " + movY, 0, 10);
	}  
}