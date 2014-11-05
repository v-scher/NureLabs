package other;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class text implements ActionListener {
	JFrame jfrm;
	JTextField field;
	JButton butt;
	
	text() {
		int x = 250;
		int y = 100;
		
		jfrm = new JFrame("¬веди hex в поле"); 
	    jfrm.setSize(x, y);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    jfrm.setLocation((int) screenSize.getWidth()/2-(x/2), (int) screenSize.getHeight()/2-(y/2));
	    jfrm.setMinimumSize(new Dimension(x,y));
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jfrm.setLayout(null);
	    
	    jfrm.add(field = new JTextField());
	    field.setBounds(0, 0, 153, 62);
	    
	    jfrm.add(butt = new JButton("клац"));
	    butt.setBounds(153, 0, 80, 61);
	    butt.addActionListener(this);
	    jfrm.setVisible(true);
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new text();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == butt) {
			try (Scanner qwe = new Scanner(field.getText())) {
			String result = "";
			while (qwe.hasNext())
				result += (char) Integer.parseInt(qwe.next(), 16);
	
			if (result.length() == 0) {
				JOptionPane.showMessageDialog(null, "¬вед≥ть ш≥стнадц€ткове число!", "ѕомилка!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(null, result, "ѕереклад", JOptionPane.NO_OPTION);
			} catch ( NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "¬вед≥ть ш≥стнадц€ткове число!", "ѕомилка!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}