package ua.khture.scherbyna.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonTemplate extends JButton {
	public ButtonTemplate(String text, ActionListener listener)
	{
		setText(text);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setMargin(new Insets(2, 2, 2, 2));
		addActionListener(listener);
	}
}
