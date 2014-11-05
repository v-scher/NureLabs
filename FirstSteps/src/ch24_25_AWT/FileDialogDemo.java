package ch24_25_AWT;
/* Demonstrate File Dialog box.   
  
   This is an application, not an applet.  
*/  
import java.awt.*;  
import java.awt.event.*;  
  
// Create a subclass of Frame  
class SampleFrame2 extends Frame {  
  SampleFrame2(String title) {  
    super(title);  
 
    // Remove the window when closed. 
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent we) { 
        System.exit(0); 
      } 
    }); 
 
  }  
}  
 
// Demonstrate FileDialog. 
class FileDialogDemo {
  public static void main(String args[]) {  
    // Create a frame that owns the dialog. 
    Frame f = new SampleFrame2("File Dialog Demo");  
    f.setVisible(true);  
    f.setSize(300, 100);  
 
    FileDialog fd = new FileDialog(f, "File Dialog");
    fd.setDirectory("E:\\Dropbox\\1 Java\\level2\\JFirstJavaPr\\");
    fd.setFile("*.txt");
    fd.setVisible(true);
    
    if (fd.getFile() == null)
    		f.setTitle("You cancelled the choice");
    	else
    		f.setTitle("You chose " + fd.getFile());
  }  
}