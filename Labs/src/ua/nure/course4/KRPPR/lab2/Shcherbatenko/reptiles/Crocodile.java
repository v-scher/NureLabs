package ua.nure.course4.KRPPR.lab2.Shcherbatenko.reptiles;

public class Crocodile extends Reptilian{

	public Crocodile(double cur) {
		super(100, 30, cur, 10, 40, 30);
		this.name = "Crocodile";
	}
	
	@Override
	public String toString(){
		return "I'm a " + name + " and " + super.toString();
	}
}