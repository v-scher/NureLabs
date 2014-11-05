package ua.khture.bespalov.pz4;


public class Student {
	String name;
	
	public Student(String _name) {
		name = _name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class WrongInputException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "WrongInput!";
	}
}
