package ua.khture.bespalov.pz3;

public class Boss extends Employee {
	int weeksOnWork = 0;
	double paymentPerWeek = 0.0;
	
	public Boss(String _name, String _lastName, int _weeksOnWork, double _paymentPerWeek) {
		super(_name, _lastName);
		weeksOnWork = _weeksOnWork;
		paymentPerWeek = _paymentPerWeek;
	}
	
	public int getWeeksOnWork() {
		return weeksOnWork;
	}
	
	public double getPaymentPerWeek() {
		return paymentPerWeek;
	}
	
	public void setWeeksOnWork(int weeksOnWork) {
		this.weeksOnWork = weeksOnWork;
	}
	
	public void setPaymentPerWeek(double paymentPerWeek) {
		this.paymentPerWeek = paymentPerWeek;
	}
	
	@Override
	public double getPayment() {
		return weeksOnWork * paymentPerWeek;
	}

	@Override
	public String getFullInfo() {
		return super.getFullInfo() + ", weeks: " + getWeeksOnWork() + ", payment per week: " + getPaymentPerWeek();
	}
}
