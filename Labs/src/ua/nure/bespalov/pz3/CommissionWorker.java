package ua.nure.bespalov.pz3;

public class CommissionWorker extends Employee {
	double basePayment = 0;
	double sales = 0;
	double salesPaymentPercentage = 0;
	
	public CommissionWorker(String _name, String _lastName, double _basePayment, double _sales, double _salesPaymentPercentage) {
		super(_name, _lastName);
		setBasePayment(_basePayment);
		setSales(_sales);
		setSalesPaymentPercentage(_salesPaymentPercentage);
	}
	
	public double getBasePayment() {
		return basePayment;
	}

	public double getSales() {
		return sales;
	}

	public double getSalesPaymentPercentage() {
		return salesPaymentPercentage;
	}

	public void setBasePayment(double basePayment) {
		if (basePayment < 0)
			return;
		
		this.basePayment = basePayment;
	}

	public void setSales(double sales) {
		if (sales < 0)
			return;
		
		this.sales = sales;
	}

	public void setSalesPaymentPercentage(double salesPaymentPercentage) {
		if (salesPaymentPercentage > 1)
			return;
		
		this.salesPaymentPercentage = salesPaymentPercentage;
	}

	@Override
	public double getPayment() {
		return basePayment + sales * salesPaymentPercentage;
	}

	@Override
	public String getFullInfo() {
		return super.getFullInfo() + ", basePayment: " + getBasePayment() + ", sales: " + getSales() + ", sales payment percentage: " + getSalesPaymentPercentage();
	}
}
