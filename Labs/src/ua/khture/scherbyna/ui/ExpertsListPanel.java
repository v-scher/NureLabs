package ua.khture.scherbyna.ui;

@SuppressWarnings("serial")
public class ExpertsListPanel extends ListPanelTemplate{

	public ExpertsListPanel() {
		super(new String[1]);
	}

	public void setContent(String[][] _content, String _text)
	{
		m_header = _content[0];
		String[][] newContent = new String[_content.length - 1][1];
		for (int i = 0; i < newContent.length; i++)
			newContent[i] = _content[i + 1];

		for (int i = 0; i < newContent.length; i++)
		{
			for (int j = 0; j < newContent[0].length; j++)
				System.out.print(newContent[i][j] + "-");

			System.out.print("-");
			System.out.println();
		}
		
		super.setContent(newContent, _text);
	}
	
	@Override
	public String[][] getContent()
	{
		String[][] oldContent = super.getContent();
		String[][] newContent = new String[oldContent.length + 1][1];
		newContent[0] = m_header;
		for (int i = 0; i < oldContent.length; i++)
			newContent[i + 1] = oldContent[i];
		
		return newContent;
	}
	
	public int getSelectedColumnNumber()
	{
		return m_table.getSelectedColumn();
	}
}
