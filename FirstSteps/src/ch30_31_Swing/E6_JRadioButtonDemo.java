package ch30_31_Swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;


public class E6_JRadioButtonDemo implements ActionListener { 
		JFrame jfrm;
		JLabel jlab; 
		JToggleButton jtbn;
		
		E6_JRadioButtonDemo() {
		    jfrm = new JFrame("Ex6"); 
		    jfrm.setSize(100, 150);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int width = (int) screenSize.getWidth();
		    int height = (int) screenSize.getHeight();
		    jfrm.setLocation(width/2-150, height/2-50);
		    // Terminate the program when the user closes the application. 
		    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			// Change to flow layout. 

		    jfrm.setLayout(new FlowLayout()); 
		    
		    // Create radio buttons and add them to content pane. 
		    JRadioButton b1 = new JRadioButton("A"); 
		    b1.addActionListener(this); 
		    jfrm.add(b1); 
		 
		    JRadioButton b2 = new JRadioButton("B"); 
		    b2.addActionListener(this); 
		    jfrm.add(b2); 
		 
		    JRadioButton b3 = new JRadioButton("C"); 
		    b3.addActionListener(this); 
		    jfrm.add(b3); 
		       
		    // Define a button group. 
		    ButtonGroup bg = new ButtonGroup(); 
		    bg.add(b1); 
		    bg.add(b2); 
		    bg.add(b3); 
		 
		    // Create a label and add it to the content pane. 
		    jlab = new JLabel("Select One"); 
		    jfrm.add(jlab); 
		    
		    jfrm.setVisible(true);
		}
		
		  // Handle button selection. 
		  public void actionPerformed(ActionEvent ae) { 
		    jlab.setText("You selected " + ae.getActionCommand()); 
		  } 
		  
		public static void main(String args[]) { 
			// Create the frame on the event dispatching thread. 
			SwingUtilities.invokeLater(new Runnable() { 
				public void run() { 
					new E6_JRadioButtonDemo(); 
				} 
			}); 
		}
	}