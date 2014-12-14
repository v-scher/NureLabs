package ua.nure.bespalov.task4;

import java.util.Date;

public class Periodic extends Magazine {
	int m_number; // випуск

	protected Periodic(String _name, String _country, Date _date, int _number) {
		super(_name, _country, _date);
		m_number = _number;
	}

	public int getNumber() {
		return m_number;
	}

	public void setNumber(int number) {
		this.m_number = number;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + ", випуск " + getNumber();
	}
}
