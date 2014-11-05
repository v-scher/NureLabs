package ua.nure.shcherbatenko.crppr.LabWork2.reptiles;

import ua.nure.shcherbatenko.crppr.LabWork2.Animal;

public abstract class Reptilian extends Animal{
	protected double minBodyTemperature;
	protected double maxBodyTemperature;
	
	public Reptilian(double mass, double normalBodyTemperature, double currentBodyTemperature, double min, double max, double environmentTemperature) {
		super(mass, normalBodyTemperature, currentBodyTemperature, environmentTemperature);
		minBodyTemperature = min;
		maxBodyTemperature = max;
	}
	
	@Override
	public void update() {
		if(isAlive){
			currentBodyTemperature += (environmentTemperature - currentBodyTemperature ) / mass;
			
			if( currentBodyTemperature > maxBodyTemperature
			|| currentBodyTemperature < minBodyTemperature){
				isAlive = false;
			}
		}
	}
}
