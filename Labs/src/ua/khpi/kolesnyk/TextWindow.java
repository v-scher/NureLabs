package ua.khpi.kolesnyk;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class TextWindow {
	JFrame frm;
	JTextArea ta;

	TextWindow() {
		frm = new JFrame("Kolesnyk Course Work");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setBounds(50, 50, 300, 500);
		frm.setAlwaysOnTop(true);
		
		ta = new JTextArea();
		ta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ta.setMargin(new Insets(5,5,5,5));
		JScrollPane jsp = new JScrollPane(ta);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		frm.add(jsp, BorderLayout.CENTER);
		frm.setVisible(true);
	}

	public FontMetrics getFM(){
		return ta.getFontMetrics(ta.getFont());
	}
	
	public int textMaxWidth(){
		return ta.getWidth() - 8;
	}
	
	public void setText(String text) {
		ta.setText(text);
	}

	public String getText() {
		return ta.getText();
	}

	public void addText(String text) {
		ta.setText(ta.getText() + "\n" + text);
	}
}