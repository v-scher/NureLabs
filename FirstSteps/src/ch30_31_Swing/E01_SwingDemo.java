// A simple Swing application. 
package ch30_31_Swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*; 
  
class E01_SwingDemo {
	E01_SwingDemo() { 
	    // Create a new JFrame container. 
	    JFrame jfrm = new JFrame("A Simple Swing Application"); 
	    jfrm.setSize(300, 100);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	 
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 
	    // Create a text-based label. 
	    JLabel jlab = new JLabel(" Swing means powerful GUIs."); 
	 
	    // Add the label to the content pane. 
	    jfrm.add(jlab); 
	 
	    // Display the frame. 
	    jfrm.setVisible(true); 
	}
 
	public static void main(String args[]) { 
		// Create the frame on the event dispatching thread. 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new E01_SwingDemo(); 
			} 
		}); 
	} 
}