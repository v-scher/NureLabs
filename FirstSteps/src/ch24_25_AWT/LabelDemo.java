package ch24_25_AWT;
// Demonstrate Labels 
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*; 
/* 
<applet code="LabelDemo" width=300 height=200> 
</applet> 
*/ 
 
public class LabelDemo extends Applet implements ActionListener { 
	  String msg = ""; 
	  Button yes, no, maybe; 
	  
  public void init() { 
	    yes = new Button("Yes"); 
	    no = new Button("No"); 
	    maybe = new Button("Undecided"); 
	    
	    add(yes); 
	    add(no); 
	    add(maybe); 
	 
	    yes.addActionListener(this); 
	    no.addActionListener(this); 
	    maybe.addActionListener(this); 
	    
    Label one = new Label("One"); 
    Label two = new Label("Two"); 
    Label three = new Label("Three"); 
 
    // add labels to applet window 
    add(one); 
    add(two); 
    add(three); 
  }

  public void actionPerformed(ActionEvent ae) { 
	    String str = ae.getActionCommand(); 
	    if(str.equals("Yes")) { 
	      msg = "You pressed Yes."; 
	    } 
	    else if(str.equals("No")) { 
	      msg = "You pressed No."; 
	    } 
	    else { 
	      msg = "You pressed Undecided."; 
	    } 
	 
	    repaint(); 
	  } 
  public void paint(Graphics g) { 
	     g.drawString(msg, 6, 100); 
	  } 
}