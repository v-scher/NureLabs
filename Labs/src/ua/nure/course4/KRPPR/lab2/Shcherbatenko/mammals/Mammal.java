package ua.nure.course4.KRPPR.lab2.Shcherbatenko.mammals;

import ua.nure.course4.KRPPR.lab2.Shcherbatenko.Animal;

public abstract class Mammal extends Animal {
	protected double deadLine;

	public Mammal(double mass, double normalBodyTemperature,double currentBodyTemperature, double deadLine, double environmentTemperature) {
		super(mass, normalBodyTemperature, currentBodyTemperature, environmentTemperature);
		this.deadLine = deadLine;
	}

	@Override
	public void update() {
		if(isAlive){
			currentBodyTemperature += (environmentTemperature - currentBodyTemperature ) / (mass * 9);
			
			if( currentBodyTemperature - normalBodyTemperature > deadLine
			|| currentBodyTemperature - normalBodyTemperature < -deadLine){
				isAlive = false;
			}
		}
	}
}
