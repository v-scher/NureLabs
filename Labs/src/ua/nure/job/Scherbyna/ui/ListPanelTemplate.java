package ua.nure.job.Scherbyna.ui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ListPanelTemplate extends JPanel {
	private static final int 
	BUTTON_MARGIN = 18;
	
	private JScrollPane m_scrollPane;
	protected JTable m_table;
	private JLabel m_label = new JLabel("");
	protected String[] m_header;
	private ArrayList<ButtonTemplate> m_buttons = new ArrayList<>();
	
	public void addButton(String _name, ActionListener _listener)
	{
		ButtonTemplate button = new ButtonTemplate(_name, _listener);
		m_buttons.add(button);

		int widthSpace = Window.WND_SIZE.width - 8 - BUTTON_MARGIN;
		int widthSpaceForButtons = widthSpace - BUTTON_MARGIN * m_buttons.size();
		int buttonWidth = widthSpaceForButtons / m_buttons.size();
		
		for (int i = 0; i < m_buttons.size(); i++)
		{
			int x = BUTTON_MARGIN + (BUTTON_MARGIN + buttonWidth) * i;
			m_buttons.get(i).setBounds(x, BUTTON_MARGIN, buttonWidth, 80);
		}
		
		add(button);
	}
	
	public ListPanelTemplate(String[] _header)
	{
		this.m_header = _header;
		m_label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setLayout(null);
	}

	public String[][] getContent()
	{
		int rows = m_table.getRowCount();
		int cols = m_table.getColumnCount();
		
		Object[][] content = new String[rows][cols];
		
		for (int row = 0; row < rows; row++)
			if (m_table.getValueAt(row, 0) != null)
				for (int col = 0; col < cols; col++)
					content[row][col] = m_table.getValueAt(row, col);
		
		return (String[][]) content;
	}
	
	public void saveDataFromEditFields()
	{
		if (m_table != null)
			m_table.editCellAt(0, 0);
	}
	
	public void setContent(String[][] _content)
	{
		setContent(_content, null);
	}
	
	public void setContent(String[][] _content, String _text)
	{
		if (m_scrollPane != null)
			remove(m_scrollPane);

		if (_content.length == 0)
		{
			_content = new String[1][m_header.length];
		}
		
		m_table = new JTable(_content, m_header);
		m_table.setRowHeight(25);
		m_table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		m_table.setCellSelectionEnabled(false);
		
		m_scrollPane = new JScrollPane(m_table);
		if (_text == null)
		{
			m_scrollPane.setBounds(18, 128, 956, 520);
			remove(m_label);
		} else {
			m_scrollPane.setBounds(18, 128, 956, 470);
			m_label.setText(_text);
			m_label.setBounds(18, 600, 956, 30);
			add(m_label);
			
		}
		add(m_scrollPane);
	}
	
	public String getLabelText()
	{
		return m_label.getText();
	}
	
	public int getSelectedRowNumber()
	{
		return m_table.getSelectedRow();
	}
}
