package ch30_31_Swing;

//Demonstrate JToggleButton. 
import java.awt.*; 
import java.awt.event.*; 

import javax.swing.*; 

public class E4_JToggleButtonDemo { 
	JFrame jfrm;
	JLabel jlab; 
	JToggleButton jtbn;
	
	E4_JToggleButtonDemo() {
	    jfrm = new JFrame("Ex3"); 
	    jfrm.setSize(100, 150);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Change to flow layout. 
	    //jfrm.setLayout(new FlowLayout()); 

	 // Change to flow layout. 
	    jfrm.setLayout(new FlowLayout()); 

	    // Create a label. 
	    jlab = new JLabel("Button is off.");  

	    // Make a toggle button. 
	    jtbn =  new JToggleButton("On/Off"); 

	    // Add an item listener for the toggle button. 
	    jtbn.addItemListener(new ItemListener() { 
	      public void itemStateChanged(ItemEvent ie) { 
	        if(jtbn.isSelected()) 
	          jlab.setText("Button is on."); 
	        else 
	          jlab.setText("Button is off."); 
	      } 

	    }); 

	    // Add the toggle button and label to the content pane.  
	    jfrm.add(jtbn);   
	    jfrm.add(jlab);
	    
	    jfrm.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) { 
		 jlab.setText("You selected " + ae.getActionCommand()); 
	}
	
	public static void main(String args[]) { 
		// Create the frame on the event dispatching thread. 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new E4_JToggleButtonDemo(); 
			} 
		}); 
	}
}