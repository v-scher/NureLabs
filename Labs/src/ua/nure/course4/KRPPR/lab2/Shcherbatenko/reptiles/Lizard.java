package ua.nure.course4.KRPPR.lab2.Shcherbatenko.reptiles;

public class Lizard extends Reptilian{

	public Lizard(double cur) {
		super(1, 37, cur, 5, 40, 37);
		this.name = "Lizard";
	}
	
	@Override
	public String toString(){
		return "I'm a " + name + " and " + super.toString();
	}
}