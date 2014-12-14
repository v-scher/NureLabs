package ua.nure.job.Bespalov.pz3;

public class HourlyWorker extends Employee {
	int hourseOnWork = 0;
	double paymentPerHour = 0;
	
	public HourlyWorker(String _name, String _lastName, int _hourseOnWork, double _paymentPerHour) {
		super(_name, _lastName);
		hourseOnWork = _hourseOnWork;
		paymentPerHour = _paymentPerHour;
	}
	
	public int getHourseOnWork() {
		return hourseOnWork;
	}

	public double getPaymentPerHour() {
		return paymentPerHour;
	}

	public void setHourseOnWork(int hourseOnWork) {
		this.hourseOnWork = hourseOnWork;
	}

	public void setPaymentPerHour(double paymentPerHour) {
		this.paymentPerHour = paymentPerHour;
	}

	@Override
	public double getPayment() {
		return hourseOnWork * paymentPerHour;
	}

	@Override
	public String getFullInfo() {
		return super.getFullInfo() + ", hourse on work: " + getHourseOnWork() + ", payment per hour: " + getPaymentPerHour();
	}
}
