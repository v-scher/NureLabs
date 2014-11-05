package ua.khture.bespalov.task4;

public class Book extends Element {
	String m_author;
	int m_year = -1; // рік видання
	
	public Book(String _name, String _country, int _year, String _author) {
		super(_name, _country);
		m_year = _year;
		m_author = _author;
	}

	public int getYear() {
		return m_year;
	}

	public void setYear(int m_year) {
		this.m_year = m_year;
	}

	public String getAuthor() {
		return m_author;
	}

	public void setAuthor(String m_author) {
		this.m_author = m_author;
	}
	
	@Override
	public String getInfo() {
		return getAuthor() + ", " + super.getInfo() + ", " + getYear();
	}
}
