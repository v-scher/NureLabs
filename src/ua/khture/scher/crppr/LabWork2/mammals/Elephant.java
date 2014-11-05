package ua.khture.scher.crppr.LabWork2.mammals;

public class Elephant extends Mammal{

	public Elephant(double cur) {
		super(200, 36, cur, 4, 34);
		this.name = "Elephant";
	}
	
	@Override
	public String toString(){
		return "I'm an " + name + " and " + super.toString();
	}
}
