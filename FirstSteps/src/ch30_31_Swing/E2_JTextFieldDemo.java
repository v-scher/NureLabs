package ch30_31_Swing;

//Demonstrate JTextField. 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
/* 
<applet code="JTextFieldDemo" width=300 height=50> 
</applet> 
*/ 

public class E2_JTextFieldDemo { 
	JTextField jtf; 
	JFrame jfrm;
	JLabel jl;
	E2_JTextFieldDemo() {
	    jfrm = new JFrame("Ex2"); 
	    jfrm.setSize(300, 100);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) screenSize.getWidth();
	    int height = (int) screenSize.getHeight();
	    jfrm.setLocation(width/2-150, height/2-50);
	    // Terminate the program when the user closes the application. 
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 // Change to flow layout. 
	    jfrm.setLayout(new FlowLayout()); 

		 // Add text field to content pane. 
		 jtf = new JTextField(15);
		 jfrm.add(jtf); 
		 jtf.addActionListener(new ActionListener() { 
		   public void actionPerformed(ActionEvent ae) { 
		     // Show text when user presses ENTER. 
			   jfrm.setTitle(jtf.getText()); 
			   jl.setText(jtf.getText());
		   } 
		 });
		 
		 jl = new JLabel(""); 
		 jfrm.add(jl); 
		    
		 jfrm.setVisible(true); 
	}
   
	public static void main(String args[]) { 
		// Create the frame on the event dispatching thread. 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new E2_JTextFieldDemo(); 
			} 
		}); 
	}
}