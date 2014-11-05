package ch1_23;
import java.awt.event.*;
import java.applet.*;
/*
  <applet code="AdapterDemo" width=300 height=100>
  </applet>
*/

public class EventAdaptor extends Applet {
	public void init() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				showStatus("Mouse clicked");
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				showStatus("Mouse dragged " + me.getX() + " " + me.getY());
			} 
		});
	}
}
