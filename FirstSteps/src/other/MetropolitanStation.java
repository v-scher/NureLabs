package other;

// ������ ����
class Hour {
	private int numberOfPassengers;
	private String comment;
	
	Hour (int number, String comment) {
		this.numberOfPassengers = number;
		this.comment = comment;
	}
	
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}

// ������ �����
class MetropolitanStation {
	private String name;
	private int year;
	// ����� ����� (�������� ������� �����)
	private Hour hours[];
	
	MetropolitanStation(String name, int year, Hour[] hours) {
		this.setName(name);
		this.setYear(year);
		this.hours = hours;
	}
	
	public int sumOfPassengers() {
		// �������� ����� ��� ���������� ���� ������� ��������
		int sum = 0;
		// � ���� �� ������� (���������) �� ���������� (������� ����� - 1) ���������� �������� ������� ����� 
		for (int i = 0; i < this.hours.length; i++)
			// ������ ������� �������� � ����� ������ �� ������ sum
			sum += this.hours[i].getNumberOfPassengers();
		
		// ���� ����������� ������� ����� "���������" ����
		return sum;
	}
	
	public Hour minPassengersHour() {
		// ���������� ������ ������ � ��������� ������� ��������
		// ������� � ��������� ��������
		
		Hour min = this.hours[0];
		
		// � ���� ���������� �� ������ "�������", ��� ��� ��������� �� ������� - this.hours
		for (int i = 1; i < this.hours.length; i++)
			// ���� � ����� (this) ��'��� MetropolitanStation 
			// � ��'���� Hour � ������� "i" (hours[ i ])
			// ������� �������� (getNumberOfPassengers()) ����� ( < ), ��� ������� ��������
			// ������ min
			if (this.hours[i].getNumberOfPassengers() < min.getNumberOfPassengers())
				// ������� ������� (hours[i]) ������ ����� �������
				min = this.hours[i];
		
		// ���� ������� �������� ��������� "������" � ��������� ������� ��������
		return min;
	}

	public Hour longestCommentHour() {
		// ��������� ������ � ��������� ���������� ������ ���������
		// ������� � �������
		Hour longest = this.hours[0];
		
		// ���������� �Ѳ ������ �� 1 (������� �� �������� ����� ��������� � longest) �� ��������
		for (int i = 1; i < this.hours.length; i++)
			// � ��'���� MetropolitanStation (this) 
			// ������� ������� ���������� ������ (hours[i]) ����� Hour
			// ��� �������� Hour ��������� ����� getComment(), ���� ������� ��'��� ����� String - �������� �� ������
			// ��� ��������� - ��'���� ����� String - ��������� ������� Split(), ��� ������� ����� ��'���� String
			// �� �� ����������� ��������� ���������� ����� ��������� String - �� ������ ����� �����
			// � ������, ���� ������� split(), ���������� ���� lenght, ��� ������� ������� �������� String - ������� ��� ���������
			// � ��������� � ���������� ���������� ������, ��� �� �������� � longest - ����� ��� ������ � ��������� ����������
			if (this.hours[i].getComment().split(" ").length > longest.getComment().split(" ").length)
				// ���� ������� (hours[i]) ������ �� ������ �������� - �������� �� � longest
				longest = this.hours[i];
		
		//���� ������� �������� ��������� ������ � ��������� ����������
		return longest;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setHourse(Hour hourse[]) {
		this.hours = hourse;
	}
	
	public Hour[] getHours() {
		return this.hours;
	}
	
	public static void main(String args[]) {
		// ��������� ��'��� ����� MetropolitanStation
		// ���������� ���� 
		// ����� - name = "123"
		// �� �������� - year = 1993
		// ����� "�����", �� �������� ������ ������� ����� ������� �� �� �� ����������, ���� - hours = null
		MetropolitanStation MS = new MetropolitanStation("123", 1993, null);
		// ������������ ��'���� MS ���� ���� name = "�������"
		MS.setName("�������");
		// ��������.��� ��'���� MS ���� ���� year = 1990
		MS.setYear(1990);
		
		// ��������� ����� "�����" 
		// ��� ������� �������� ���'��� ��� ���� �������� Hour
		Hour hourse[] = new Hour[7];
		// ��������� ���� ������ (new Hour()) � ������� � ������ hours[0]
		// ���������� ���� ������ ��'���� 
		// numberOfPassengers = 1000
		// comment = "ͳ���� �� ����������"
		hourse[0] = new Hour(1000, "ͳ���� �� ����������");
		// ��� �� �����㳺�
		hourse[1] = new Hour(2000, "�������� ����������");
		hourse[2] = new Hour(3000, "ʳ���� �������� ���");
		hourse[3] = new Hour(4000, "ʳ���� �������� Bon Jovi �� �����");
		hourse[4] = new Hour(600, "ͳ���� �� ����������");
		hourse[5] = new Hour(2000, "ͳ���� �� ����������");
		hourse[5].setComment("�������� � ���������");
		hourse[6] = new Hour(1000, "ͳ���� �� ����������");
		// ���� �� ������ ������ hours[] ���������, ������������ � ���� hours ��'���� MS ����� ��������� ����� � ��'�� hours (��������� ������)
		MS.setHourse(hourse);
		
		// �������� ��� ��'���� MS ���� name �� year �� ��������� get-������ ("�������" �� �� ��� ��������)
		System.out.println("�������: " + MS.getName() + ", ��: " + MS.getYear());
		// ���������� � �������� �� ���������� ������ sumOfPassengers() ���� ��������
		System.out.println("������� ������� ��������: " + MS.sumOfPassengers());
		// ��������� � �����-���� hours[] ��'���� MS ������ � ��������� ������� �������� �� ��������� ������ minPassengersHour()
		// ��� ��'���� Hour, ���� ������� ������� minPassengersHour() ��������� ����� getNumberOfPassengers(), ���� ������� ʲ��ʲ��� ��������
		// � �������� �� �����
		System.out.println("�������� ������� ��������: " + MS.minPassengersHour().getNumberOfPassengers());
		
		// ��� ��'���� MS ��������� ������ � ��������� ���������� �� ��������� ������ longestCommentHour()
		// ��� ��'���� Hour, ���� �������� ����� longestCommentHour(), ��������� ����� getComment(), ���� ������� ��� �������� ���� ������
		// � �������� �� ���� �� �����
		System.out.println("��������� ��������: " + MS.longestCommentHour().getComment());
	}
}
