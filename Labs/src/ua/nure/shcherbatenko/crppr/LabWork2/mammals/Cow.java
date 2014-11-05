package ua.nure.shcherbatenko.crppr.LabWork2.mammals;

public class Cow extends Mammal{

	public Cow(double cur) {
		super(300, 37, cur, 3, 34);
		this.name = "Cow";
	}
	
	@Override
	public String toString(){
		return "I'm a " + name + " and " + super.toString();
	}
}
