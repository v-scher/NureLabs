package ch24_25_AWT;
// Demonstrate text field.  
import java.awt.*; 
import java.awt.event.*; 
import java.applet.*; 
/* 
 
  <applet code="TextFieldDemo" width=380 height=150> 
  </applet> 
*/ 
 
public class TextFieldDemo extends Applet implements ActionListener { 
  TextField name, pass; 
 
  public void init() { 
	setSize(250, 200);
    Label namep = new Label("Name: ", Label.RIGHT); 
    Label passp = new Label("Password: ", Label.RIGHT); 
    name = new TextField(12); 
    pass = new TextField(12); 
    pass.setEchoChar('*');
 
    add(namep); 
    add(name); 
    add(passp); 
    add(pass); 
 
    // register to receive action events 
    name.addActionListener(this); 
    pass.addActionListener(this); 
  } 
 
  // User pressed Enter. 
  public void actionPerformed(ActionEvent ae) { 
    repaint(); 
  } 
 
  public void paint(Graphics g) { 
     g.drawString("Name: " + name.getText(), 6, 100); 
     g.drawString("Selected text in name: " 
                  + name.getSelectedText(), 6, 120); 
     g.drawString("Password: " + pass.getText(), 6, 140); 
  } 
}