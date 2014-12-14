package ua.nure.job.Bespalov.task4;

public class Methodics extends Element {
	String m_school;
	
	public String getSchool() {
		return m_school;
	}

	public void setSchool(String school) {
		this.m_school = school;
	}

	protected Methodics(String _name, String _country, String _school) {
		super(_name, _country);
		m_school = _school;
	}

	@Override
	public String getInfo() {
		return super.getInfo() + ", " + getSchool();
	}
}
