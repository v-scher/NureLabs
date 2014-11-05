package ch1_23;
// Display Fonts
/*
<applet code="ShowFonts" width=550 height=60>
</applet>
*/
import java.applet.*;
import java.awt.*;

public class ShowFonts extends Applet {
  public void paint(Graphics g) {
    String msg = "";
    String FontList[];

    FontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    for (int q = 0; q < FontList.length/5; q++) {
    	msg = "";
	    for(int i = 0; i < 5; i++)
	      msg += FontList[i*q] + " ";
	    g.drawString(msg, 4, 16 + 16*q);
    }

  }
}