package ch30_31_Swing;

import java.awt.*;

import javax.swing.*;

public class E7_JTabbedPaneDemo { 
		JFrame jfrm;
		JLabel jlab; 
		JToggleButton jtbn;
		
		E7_JTabbedPaneDemo() {
		    jfrm = new JFrame("Ex7"); 
		    jfrm.setSize(400, 300);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int width = (int) screenSize.getWidth();
		    int height = (int) screenSize.getHeight();
		    jfrm.setLocation(width/2-150, height/2-50);
		    // Terminate the program when the user closes the application. 
		    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			// Change to flow layout. 

		    jfrm.setLayout(new FlowLayout()); 
		    
		    JTabbedPane jtp = new JTabbedPane(); 
		    jtp.addTab("Cities", new CitiesPanel()); 
		    jtp.addTab("Colors", new ColorsPanel()); 
		    jtp.addTab("Flavors", new FlavorsPanel()); 
		    jfrm.add(jtp);
		    
		    jfrm.setVisible(true);
		}
		  
		public static void main(String args[]) { 
			// Create the frame on the event dispatching thread. 
			SwingUtilities.invokeLater(new Runnable() { 
				public void run() { 
					new E7_JTabbedPaneDemo(); 
				} 
			}); 
		}
		
		// Make the panels that will be added to the tabbed pane. 
		class CitiesPanel extends JPanel {
		  public CitiesPanel() {
		    JButton b1 = new JButton("New York"); 
		    add(b1); 
		    JButton b2 = new JButton("London"); 
		    add(b2); 
		    JButton b3 = new JButton("Hong Kong"); 
		    add(b3); 
		    JButton b4 = new JButton("Tokyo"); 
		    add(b4); 
		  }
		}
		 
		class ColorsPanel extends JPanel {
		  public ColorsPanel() { 
		    JCheckBox cb1 = new JCheckBox("Red"); 
		    add(cb1); 
		    JCheckBox cb2 = new JCheckBox("Green"); 
		    add(cb2); 
		    JCheckBox cb3 = new JCheckBox("Blue"); 
		    add(cb3); 
		  } 
		} 
		 
		class FlavorsPanel extends JPanel {
		  public FlavorsPanel() { 
		    JComboBox<String> jcb = new JComboBox<String>(); 
		    jcb.addItem("Vanilla"); 
		    jcb.addItem("Chocolate"); 
		    jcb.addItem("Strawberry"); 
		    add(jcb); 
		  } 
		}
	}