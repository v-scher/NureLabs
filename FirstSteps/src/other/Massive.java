package other;

abstract class Massive {
	// ����� ��²����� ����� (double/int)
	Number[] mass1;
	
	public abstract void show();
	
	public abstract Massive add(Massive mass2);
	
	public abstract Massive sub(Massive mass2);
	
	public abstract Massive mult(Massive mass2);

	public static void main(String args[]) {
		// ��������� ����� ����� ��� ����������� ����� ��'����
		Number[] tmpMass = new Number[10];
		// ���������� ��� �����
		for (int i = 0; i < 10; i++)
			tmpMass[i] = i;
		
		// ���������� ��� ������ ���������� ������
		Massive1D m1 = new Massive1D(tmpMass);
		Massive m2 = new Massive1D(tmpMass);
		
		System.out.println("��������� ������������ add():");
		m1.add(m2).show();
		System.out.println("��������� ������������ sub():");
		m1.sub(m2).show();
		System.out.println("��������� ������������ mult():");
		m1.mult(m2).show();
		
		System.out.println("�������� ���������.\nʳ������ ����� m1: " + m1.dimensions);
		
		System.out.println("����� �� ��������� �������");
		// �������� � excMass.mass1 �������� �� null
		Massive1D excMass = new Massive1D(null);
		// ����� show() ���������� �� mass1 � ������ �������
		excMass.show();
	}
}

// Massive1D ������ ���� �� ������ �� ����� Massive
class Massive1D extends Massive {
	// �������� ���������
	public final static int dimensions = 1;
	
	// �����������
	Massive1D(Number[] massive) {
		this.mass1 = massive;
	}
	
	// ����������
	@Override
	protected void finalize() throws Throwable {
		// �������� ��� 䳿 ����� ���������� ��'����
		System.out.println("�� ��� ������� �����!");
		super.finalize();
	}

	@Override
	public void show() {
		// ������, �� ���� ��������� �������
		try {
			for (int i = 0; i < this.mass1.length; i++)
				System.out.println(mass1[i]);
		} catch (NullPointerException e ) {
			// ������� �������
			System.out.println("��������� ��������� ����! ������� �� ������ �������� ����� � �����������");
		}
		
	}

	@Override
	public Massive add(Massive mass2) {
		// ��������� ���������� ����� ������ � ������, �� ��������� � ���� �������
		Number[] tmpMass = new Number[(this.mass1.length > mass2.mass1.length) ? this.mass1.length : mass2.mass1.length];
		
		try {
			int i;
			// ������ �� �������� ������� ������ �� �������� �������
			for (i = 0; i < tmpMass.length; i++)
				tmpMass[i] = this.mass1[i].doubleValue() + mass2.mass1[i].doubleValue();
			
			// ������ ����� �������� ������� ������ � ����������
			if (this.mass1.length > mass2.mass1.length) {
				// ���� ������ ����� ������, �� ������ ���� ��������
				for (int q = i; q < this.mass1.length; q++)
					tmpMass[q] = this.mass1[q];
			}
			// ��������� ������������ if
			if (this.mass1.length < mass2.mass1.length) {
				// ���� ������ ����� ������, �� ������ ���� ��������
				for (int q = i; q < mass2.mass1.length; q++)
					tmpMass[q] = mass2.mass1[q];
			}
		} catch (NullPointerException e ) {
			System.out.println("��������� ��������� ����! ������� �� ������ �������� ����� � �����������");
		}
		return new Massive1D(tmpMass);
	}

	@Override
	public Massive sub(Massive mass2) {
		// ��������� ���������� ����� ������ � ������, �� ��������� � ���� �������
		Number[] tmpMass = new Number[(this.mass1.length < mass2.mass1.length) ? this.mass1.length : mass2.mass1.length];
		
		try {
			// ������� �� �������� ������� ������ �� �������� �������
			for (int i = 0; i < tmpMass.length; i++)
				tmpMass[i] = this.mass1[i].doubleValue() - mass2.mass1[i].doubleValue();
			
		} catch (NullPointerException e ) {
			System.out.println("��������� ��������� ����! ������� �� ������ �������� ����� � �����������");
		}
		return new Massive1D(tmpMass);
	}

	@Override
	public Massive mult(Massive mass2) {
		// ��������� ���������� ����� ������ � ������, �� ��������� � ���� �������
		Number[] tmpMass = new Number[(this.mass1.length < mass2.mass1.length) ? this.mass1.length : mass2.mass1.length];
		
		try {
			// ����������� �� �������� ������� ������ �� �������� �������
			for (int i = 0; i < tmpMass.length; i++)
				tmpMass[i] = this.mass1[i].doubleValue() * mass2.mass1[i].doubleValue();
			
		} catch (NullPointerException e ) {
			System.out.println("��������� ��������� ����! ������� �� ������ �������� ����� � �����������");
		}
		return new Massive1D(tmpMass);
	}
}