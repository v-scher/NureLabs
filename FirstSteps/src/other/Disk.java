package other;

class Point {
	double x;
	double y;
	
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

abstract class Circle {
	Point center;
	double radius;
	
	public abstract void setCenter(Point center);
	
	public abstract void setRadius(double radius);
	
	public abstract boolean contains(Point point);
	
	public abstract void show();
}

// ����� Disk ������ ���� Circle
class Disk extends Circle{
	// �������� ���������
	public final static String	name = "����/Disk";
	
	// �����������
	Disk(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	// ����������
	@Override
	protected void finalize() throws Throwable {
		// �������� ��� 䳿 ����� ���������� ��'����
		System.out.println("���� �������.");
		super.finalize();
	}
	
	@Override
	public void setCenter(Point center) {
		this.center = center;
		
	}

	@Override
	public void setRadius(double radius) {
		this.radius = radius;
	}

	// �������� ������������ �����
	@Override
	public boolean contains(Point p) {
		// ������, �� ���� ��������� �������
		try {
			// ���������� ��������� �� ������� � ������� ������
			double distance = Math.pow((p.x - center.x), 2) + Math.pow((p.y - center.y), 2);
			// ���� �� ������� ����� ������
			if ( distance < radius)
				// ����� �������� �����
				return true;
		} catch (NullPointerException e) {
			// ������� �� �������
			System.out.println("����� �� ������!");
		}
		// ����� �� �������� �����
		return false;
	}

	@Override
	public void show() {
		try {
			System.out.println("�����: (" + this.center.x + ", " + this.center.y + "), �����: " + this.radius);
		} catch (NullPointerException e) {
			System.out.println("����� �� ������!");
		}
	}
	
	public static void main(String args[]) {
		System.out.println("�������� ��'��� ����� ����:");
		Disk disk = new Disk(new Point(12,12), 12);
		disk.show();
		
		System.out.println("������ �����:");
		disk.setCenter(new Point(1,1));
		disk.show();
		
		System.out.println("������ �����:");
		disk.setRadius(10);
		disk.show();
		
		if (disk.contains(new Point(1,1))) {
			System.out.println("����� ���� ������ ����� (1,1)");
		} else {
			System.out.println("����� ���� �� ������ ����� (1,1)");
		}
		
		System.out.println("�������:");
		// �������� � disk.center �������� �� null
		disk.setCenter(null);
		// ����� ��� �� ����� ����������� ���� X �� Y �� ��������� ����� ������
		// � ������ �������
		disk.show();
	}
}
