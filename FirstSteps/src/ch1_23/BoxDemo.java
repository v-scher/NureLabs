package ch1_23;
class Box {
	double w;
	double h;
	double d;
	
	//������������ ������� ����
	public Box(double w, double h, double d){	
		this.w=w;
		this.h=h;
		this.d=d;
	}
	//���� ��'����
	public Box(Box q){			
		this.w=q.w;		//this "���� �������" �� ��'� �����-���������
		this.h=q.h;		//���� ��� �������� = new;
		this.d=q.d;
	}
	//��������� �������
	public Box(){
		w = -1;
		h = -1;
		d = -1;
	}
	//���
	public Box(double side){
		w = h = d = side;	//this(side,side,side);	//== ���� � ��������� ������������
	}
	
	public double vol(){
		return w*h*d;
	}
	
	public String toString() {
		return "�����: " + w + " �� " + 
				d + " �� " + h + ".";
	}
	  
	static {
		System.out.println("���� Box ����� ���� �����������.");
	}
}

class ColorBox extends Box{
	public String color;
	
	//������������ ������� ����
	public ColorBox(double w, double h, double d, String c){
		super.w=w;
		super.h=h;
		super.d=d;
		color = c;
	}
	//���� ��'����
	public ColorBox(ColorBox q){
		super(q);
		color = q.color;
	}
	//��������� �������
	public ColorBox(){
		super();
		color = "�����";
	}
	//���
	public ColorBox(double w, String c){
		super(w);
		color = c;
	}
}

class BoxDemo {
	public static void qmain(String args[]){
		
		System.out.println("BoxDemo ����� ����� ��� ���������.");
		
		System.out.println("����� ������ ������������� Box.");
		Box my1 = new Box(10,20,15);
		System.out.println("ϳ��� ������� ������������ Box.");
		ColorBox my2 = new ColorBox(11,21,16,"����");

		System.out.println("����� ������ " + my1.vol());
		my1 = my2;
		System.out.println("����� ������ " + my1.vol() + " ����: " + my2.color);
	}
}