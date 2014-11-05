package ch30_31_Swing;

import java.awt.*;
import javax.swing.*;

public class E8_JScrollPaneDemo  { 
	JFrame jfrm;
	JLabel jlab; 
	JToggleButton jtbn;
	
	E8_JScrollPaneDemo() {
	    jfrm = new JFrame("Ex8"); 
	    jfrm.setSize(400, 400);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    // Add 400 buttons to a panel. 
	    JPanel jp = new JPanel(); 
	    jp.setLayout(new GridLayout(20, 20)); 
	    int b = 0; 
	    for(int i = 0; i < 20; i++) { 
	      for(int j = 0; j < 20; j++) { 
	        jp.add(new JButton("Button " + b)); 
	        ++b; 
	      } 
	    } 
	 
	    // Create the scroll pane. 
	    JScrollPane jsp = new JScrollPane(jp); 
	    // Add the scroll pane to the content pane. 
	    // Because the default border layout is used, 
	    // the scroll pane will be added to the center. 
	    jfrm.add(jsp, BorderLayout.CENTER); 
	    
	    jfrm.setVisible(true);
	}
	  
	public static void main(String args[]) { 
		// Create the frame on the event dispatching thread. 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new E8_JScrollPaneDemo(); 
			} 
		}); 
	}
}