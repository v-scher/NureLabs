package ch30_31_Swing;

//Demonstrate an icon-based JButton. 
import java.awt.*; 
import java.awt.event.*; 

import javax.swing.*; 
/* 
<applet code="JButtonDemo" width=250 height=450> 
</applet> 
*/ 
public class E3_JButtonDemo implements ActionListener{ 
	JFrame jfrm;
	JLabel jlab;
	
	E3_JButtonDemo() {
	    jfrm = new JFrame("Ex3"); 
	    jfrm.setSize(100, 300);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Change to flow layout. 
	    //jfrm.setLayout(new FlowLayout()); 

	    // Add buttons to content pane. 
	    ImageIcon france = new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\france.gif"); 
	    JButton jb = new JButton(france); 
	    jb.setSize(40, 30);
	    jb.setLocation(5, 5);
	    jb.setActionCommand("France"); 
	    jb.addActionListener(this); 
	    jfrm.add(jb); 

	    ImageIcon germany = new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\germany.gif"); 
	    jb = new JButton(germany); 
	    jb.setSize(40, 30);
	    jb.setLocation(50, 5);
	    jb.setActionCommand("Germany"); 
	    jb.addActionListener(this); 
	    jfrm.add(jb); 

	    ImageIcon italy = new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\italy.gif"); 
	    jb = new JButton(italy); 
	    jb.setSize(40, 30);
	    jb.setLocation(5, 50);
	    jb.setActionCommand("Italy"); 
	    jb.addActionListener(this); 
	    jfrm.add(jb);

	    ImageIcon japan = new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\japan.gif"); 
	    jb = new JButton(japan); 
	    jb.setSize(40, 30);
	    jb.setLocation(50, 50);
	    jb.setActionCommand("Japan"); 
	    jb.addActionListener(this); 
	    jfrm.add(jb); 

	    // Create and add the label to content pane. 
	    jlab = new JLabel("Choose a Flag"); 
	    jlab.setLocation(5, 10);
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
				new E3_JButtonDemo(); 
			} 
		}); 
	}
}