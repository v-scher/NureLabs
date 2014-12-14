package ua.nure.bespalov.task4;

import java.util.Date;

public class Magazine extends Element {
	Date m_date; // дата випуску

	protected Magazine(String _name, String _country, Date _date) {
		super(_name, _country);
		m_date = _date;
	}

	public Date getDate() {
		return m_date;
	}

	public void setDate(Date date) {
		this.m_date = date;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + ", " + getCountry() + ", " + m_date.getDate() + "." + m_date.getMonth() + "." + m_date.getYear();
	}
}
