package ua.nure.course4.KRPPR.lab2.Shcherbatenko.reptiles;

public class Snake extends Reptilian{

	public Snake(double cur) {
		super(2, 34, cur, 5, 40, 34);
		this.name = "Snake";
	}
	
	@Override
	public String toString(){
		return "I'm a " + name + " and " + super.toString();
	}
}