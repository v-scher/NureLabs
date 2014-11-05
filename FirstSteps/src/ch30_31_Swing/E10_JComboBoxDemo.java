package ch30_31_Swing;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class E10_JComboBoxDemo extends JApplet { 
	  JLabel jlab; 
	  ImageIcon france, germany, italy, japan; 
	  JComboBox<String> jcb; 
		 
	  String flags[] = { "france", "germany", "italy", "japan" }; 
	 
	  public void init() { 
	    try { 
	      SwingUtilities.invokeAndWait( 
	        new Runnable() { 
	          public void run() { 
	            makeGUI(); 
	          } 
	        } 
	      ); 
	    } catch (Exception exc) { 
	      System.out.println("Can't create because of " + exc); 
	    } 
	  }     
	 
	  private void makeGUI() { 
	 
	    // Change to flow layout. 
	    setLayout(new FlowLayout()); 
	 
	    // Instantiate a combo box and add it to the content pane. 
	    jcb = new JComboBox<String>(flags); 
	    add(jcb); 
	 
	    // Handle selections. 
	    jcb.addActionListener(new ActionListener() { 
	      public void actionPerformed(ActionEvent ae) { 
	        jlab.setIcon(new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\" 
	        		+ jcb.getSelectedItem() + ".gif"));
	      } 
	    });
	 
	    // Create a label and add it to the content pane. 
	    jlab = new JLabel(new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\france.gif")); 
	    add(jlab); 
	  }
}