package ua.khture.scherbyna.containers;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Coeff implements Serializable{
	protected String m_name;
	public String m_metric;
	public double m_weight;
	protected double m_plan;
	protected double m_fact;
	
	public Coeff(
			String name,
			String metric,
			double weight,
			double plan,
			double fact
		)
	{
		this.m_name = name;
		this.m_metric = metric;
		this.m_weight = weight;
		this.m_plan = plan;
		this.m_fact = fact;
	}
	
	public String getName() {
		return m_name;
	}

	public double getPlan() {
		return m_plan;
	}

	public double getFact() {
		return m_fact;
	}
	
	public double getCompletionPercent()
	{
		return getFact() / getPlan();
	}
	
	public double getPaymentCoeff()
	{
		return m_weight * getCompletionPercent();
	}
}
