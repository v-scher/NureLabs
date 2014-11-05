package ch24_25_AWT;
// Display font info.
import java.applet.*;
import java.awt.*;


public class FontInfo extends Applet {
	public void paint(Graphics g) {
		Font f = g.getFont();
		int fontStyle = f.getStyle();

		String msg = "Family: " + f.getName();
		msg += ", Font: " + f.getFamily();
		msg += ", Size: " + f.getSize() + ", Style: ";
		if((fontStyle & Font.BOLD) == Font.BOLD)
			msg += "Bold ";
		if((fontStyle & Font.ITALIC) == Font.ITALIC)
			msg += "Italic ";
		if((fontStyle & Font.PLAIN) == Font.PLAIN)
			msg += "Plain ";

		g.drawString(msg, 4, 16);
	}
}