package ua.nure.scherbyna.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddCriteriaFrame extends JDialog{
	private Dimension WND_SIZE = new Dimension(300, 180);
	private JTextField m_textField = new JTextField();
	
	public AddCriteriaFrame(Window _wnd)
	{
		super(_wnd.getFrame(), true);
		setLayout(null);
		Dimension screenBounds = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				(screenBounds.width - WND_SIZE.width)/2,
				(screenBounds.height - WND_SIZE.height)/2
			);
		setSize(WND_SIZE);
		setTitle("Добавление критерия");
		JLabel text = new JLabel("Введите название критерия: ");
		text.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		text.setBounds(10, 10, 280, 20);
		m_textField.setBounds(10, 40, 264, 35);
		m_textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		m_textField.setMargin(new Insets(5, 5, 5, 5));
		m_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER)
					AddCriteriaFrame.this.dispose();
				
				if (arg0.getKeyChar() == KeyEvent.VK_ESCAPE)
				{
					m_textField.setText("");
					dispose();
				}
			}
		});
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
				m_textField.setText("");
				dispose();
	        }
	    });
		ButtonTemplate button = new ButtonTemplate("Добавить критерий", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setBounds(10, 80, 264, 35);
		
		add(text);
		add(m_textField);
		add(button);
		setVisible(true);
	}
	
	public String getText()
	{
		return m_textField.getText();
	}
}
