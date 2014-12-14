package ua.nure.job.Bespalov.pz3;

public abstract class Employee {
	private String name;
	private String lastName;
	
	public Employee(String _name, String _lastName) {
		name = _name;
		lastName = _lastName;
	}
	
	public void printFullName(){
		System.out.println(name + " " + lastName);
	}
	
	public String getFullInfo() {
		return getName() + " " + getLastName();
	}
	
	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public abstract double getPayment();
}
