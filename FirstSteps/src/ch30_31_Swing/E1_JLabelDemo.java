package ch30_31_Swing;

//Demonstrate JLabel and ImageIcon. 
import java.awt.*; 
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*; 


public class E1_JLabelDemo { 
	E1_JLabelDemo() { 
	    // Create a new JFrame container. 
	    JFrame jfrm = new JFrame("Swing Application"); 
	    jfrm.setSize(300, 300);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 
	    // Create a text-based label. 
	    ImageIcon ii = null;
	    try {
	    	ii = new ImageIcon(new URL("http://mandarin-tours.com.ua/sites/default/files/flag/2011/07/6017.gif?1319733196"));
	    } catch (MalformedURLException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }

	    ImageIcon ii1 = new ImageIcon("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\france.gif");

	    // Create a label. 
	    jfrm.setLayout(new FlowLayout());
	    JLabel jl = new JLabel("France", ii, JLabel.LEFT); 
	    JLabel jl1 = new JLabel("France1", ii1, JLabel.RIGHT);
	    // Add the label to the content pane. 
	    jfrm.add(jl); 
	    jfrm.add(jl1);
	    
	    // Display the frame. 
	    jfrm.setVisible(true); 
	}
 
	public static void main(String args[]) { 
		// Create the frame on the event dispatching thread. 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new E1_JLabelDemo(); 
			} 
		}); 
	}
}