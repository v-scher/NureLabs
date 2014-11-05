package ch1_23;
enum Apple{
	Jon(10), Gol(9), Red(12), Win(15), Cor(8);
	
	private int price;
	
	Apple(int p){ price = p; }
	
	Apple(){ price = -1; }
	
	int getPrice() { return price; }
}

/* ������ ����� ���������� �� ��������� ==
 * ���� ����� ��������������� � �������� switch()
 * 
 * ������ - �� ����������� ����
 * 
 * 
 */
class EnumDemo{
	public static void main(String args[]){
		Apple ap;
		
		//�������� ����
		System.out.println("Win ����� " + Apple.Win.getPrice() + "\n");
		System.out.println("��������� Apple:");
		
		//�������� ��� ������� �� ��� � �������
		Apple allaps[] = Apple.values();
		for(Apple a: allaps)
			System.out.println(a + ", ����: " + a.getPrice());
		System.out.println();
		
		//�������� ������ ��������
		ap = Apple.valueOf("Red");
		System.out.println("ap ������ " + ap + "\n\n����� �������:\n");
		
		//�������� �� �������� ������
		System.out.println("�� �� ��������� �� �� �������� ������: ");
		for(Apple a:Apple.values())
			System.out.println(a + " " + a.ordinal());
		
		//compareTo(), equals() �� ==
		Apple ap1,ap2,ap3;
		ap1 = Apple.Red;
		ap2 = Apple.Gol;
		ap3 = Apple.Red;
		System.out.println();
		
		if(ap1.compareTo(ap2) < 0)
			System.out.println(ap1 + " ��� ����� " + ap2);
		
		if(ap1.compareTo(ap2) > 0)
			System.out.println(ap2 + " ��� ����� " + ap1);
		
		if(ap1.compareTo(ap3) == 0)
			System.out.println(ap + " ������� " + ap3);
		
		System.out.println();
		
		if(ap1.equals(ap2))
			System.out.println("Error!");
			
		if(ap1.equals(ap3))
			System.out.println(ap1 + " equals " + ap3);
			
		if(ap1 == ap3)
			System.out.println(ap1 + " == " + ap3);
	}
}