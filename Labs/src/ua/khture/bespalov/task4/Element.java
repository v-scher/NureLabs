package ua.khture.bespalov.task4;

public abstract class Element {
	private String m_title; // �����
	private String m_country; // ����� �������

	protected Element(String _name, String _country)
	{
		m_title = _name;
		m_country = _country;
	}
	
	public String getCountry() {
		return m_country;
	}

	public void setCountry(String country) {
		this.m_country = country;
	}

	public String getName(){
		return m_title;
	}

	public String getInfo() {
		return "\"" + m_title + "\"";
	}
}
