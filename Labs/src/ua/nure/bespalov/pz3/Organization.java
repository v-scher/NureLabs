package ua.nure.bespalov.pz3;

import java.util.ArrayList;

public class Organization {
	ArrayList<Employee> workers = new ArrayList<>();
	
	public static void main(String[] args) {
		Organization org = new Organization();
		
		org.add(new Boss("�����", "������", 4, 3000));
		org.add(new HourlyWorker("�������", "����", 160, 200));
		org.add(new PieceWorker("������", "���������", 5, 1000));
		org.add(new CommissionWorker("������", "������", 2000, 5000, 0.2));
		
		System.out.println("������� ��������� �� �����: " + org.getSummaryPayment());
	}
	
	void add(Employee e) {
		workers.add(e);
		System.out.println("������: " + e.getFullInfo());
		System.out.println("������ �� �����: " + e.getPayment());
	}

	private double getSummaryPayment() {
		double summary = 0;
		
		for (Employee E : workers)
			summary += E.getPayment();
		
		return summary;
	}
}
