package ch30_31_Swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class E5_JCheckBoxDemo implements ItemListener{ 
	JFrame jfrm;
	JLabel jlab; 
	JToggleButton jtbn;
	
	E5_JCheckBoxDemo() {
	    jfrm = new JFrame("Ex3"); 
	    jfrm.setSize(100, 150);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Change to flow layout. 

	    jfrm.setLayout(new FlowLayout()); 
	    
	    // Add check boxes to the content pane. 
	    JCheckBox cb = new JCheckBox("C");  
	    cb.addItemListener(this); 
	    jfrm.add(cb); 
	 
	    cb = new JCheckBox("C++"); 
	    cb.addItemListener(this); 
	    jfrm.add(cb); 
	 
	    cb = new JCheckBox("Java"); 
	    cb.addItemListener(this); 
	    jfrm.add(cb); 
	 
	    cb = new JCheckBox("Perl"); 
	    cb.addItemListener(this); 
	    jfrm.add(cb); 
	 
	    // Create the label and add it to the content pane. 
	    jlab = new JLabel("Select languages"); 
	    jfrm.add(jlab);
	    
	    jfrm.setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent ie) { 
		JCheckBox cb = (JCheckBox)ie.getItem(); 
		 
		if(cb.isSelected()) 
			jlab.setText(cb.getText() + " is selected"); 
		else 
			jlab.setText(cb.getText() + " is cleared"); 
	}
	  
	public static void main(String args[]) { 
		// Create the frame on the event dispatching thread. 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new E5_JCheckBoxDemo(); 
			} 
		}); 
	}
}