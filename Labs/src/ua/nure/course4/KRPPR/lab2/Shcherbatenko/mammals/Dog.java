package ua.nure.course4.KRPPR.lab2.Shcherbatenko.mammals;

public class Dog extends Mammal{

	public Dog(double cur) {
		super(10, 37, cur, 5, 37);
		this.name = "Dog";
	}
	
	@Override
	public String toString(){
		return "I'm a " + name + " and " + super.toString();
	}
}
