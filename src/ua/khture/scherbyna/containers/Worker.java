package ua.khture.scherbyna.containers;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Worker implements Serializable{
	public String name;
	public String appointment;
	public double payment;
	
	public Worker(String name, String appointment, double payment)
	{
		this.name = name;
		this.appointment = appointment;
		this.payment = payment;
	}
	
	public Worker(String name, String appointment, double payment, Coeff[] _KEIs)
	{
		this.name = name;
		this.appointment = appointment;
		this.payment = payment;
	}
}
