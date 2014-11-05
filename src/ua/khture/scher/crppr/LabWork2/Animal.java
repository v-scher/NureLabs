package ua.khture.scher.crppr.LabWork2;

import java.util.ArrayList;

import ua.khture.scher.crppr.LabWork2.mammals.*;
import ua.khture.scher.crppr.LabWork2.reptiles.*;

public abstract class Animal implements Comparable<Animal> {
	protected String name;
	
	protected double normalBodyTemperature;
	protected double currentBodyTemperature;
	
	protected double environmentTemperature;
	protected double mass;
	
	protected boolean isAlive;
	
	public Animal(double mass, double normalBodyTemperature, double currentBodyTemperature, double environmentTemperature){
		isAlive = true;
		this.mass = mass;
		this.normalBodyTemperature = normalBodyTemperature;
		this.currentBodyTemperature = currentBodyTemperature;
		
		this.environmentTemperature = environmentTemperature;
	}

	public boolean isCold(){
		return currentBodyTemperature < normalBodyTemperature;
	}
	
	public boolean isHot(){
		return currentBodyTemperature > normalBodyTemperature;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setConditions(double environmentTemp){
		environmentTemperature = environmentTemp;
	}
	
	public abstract void update();
	
	@Override
	public String toString(){
		return "My mass is " +
				mass + " kilos and " +
				"I'm " + ((isAlive)
				? (isCold()
					? "COLD !"
					: (isHot()
						? "HOT"
						: "OK"
						)
				)
				:" DEAD");
	}
	
	@Override
	public int compareTo(Animal a){
		return (int) (mass - a.mass);
	}
	
	public static void main(String[] args){
		double temp = 40;
		
		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(new Cow(temp));
		animals.add(new Dog(temp));
		animals.add(new Elephant(temp));
		animals.add(new Crocodile(temp));
		animals.add(new Lizard(temp));
		animals.add(new Snake(temp));
		
		for(int hoursLeft = 0; hoursLeft< 100; hoursLeft++){
			temp -= 0.9;
			
			System.out.println("Hour " + hoursLeft + "; temp = " + temp);
			for(Animal A : animals){
				A.setConditions(temp);
				A.update();
				System.out.println("  " + A.toString() + " " + A.currentBodyTemperature);
			}
			System.out.println();
		}
	}
}
