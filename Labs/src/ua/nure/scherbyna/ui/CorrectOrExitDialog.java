package ua.nure.scherbyna.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CorrectOrExitDialog extends JDialog{
	private Dimension WND_SIZE = new Dimension(300, 180);
	private boolean shouldExit = false;
	
	public CorrectOrExitDialog(Window _wnd, String _message, String _button1, String _button2)
	{
		super((_wnd == null) ? null : _wnd.getFrame(), true);
		setLayout(null);
		Dimension screenBounds = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				(screenBounds.width - WND_SIZE.width)/2,
				(screenBounds.height - WND_SIZE.height)/2
			);
		setSize(WND_SIZE);
		setTitle("Ошибка ввода");
		JLabel text = new JLabel(_message);
		text.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		text.setBounds(10, 10, 280, 20);
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
				dispose();
	        }
	    });
		ButtonTemplate button = new ButtonTemplate(_button1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shouldExit = true;
				dispose();
			}
		});
		button.setBounds(10, 40, 264, 35);
		ButtonTemplate button1 = new ButtonTemplate(_button2, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button1.setBounds(10, 80, 264, 35);
		
		add(text);
		add(button);
		add(button1);
		setVisible(true);
	}
	
	public boolean shouldExitWithoutSaving()
	{
		return shouldExit;
	}
}