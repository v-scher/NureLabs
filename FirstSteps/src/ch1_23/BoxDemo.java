package ch1_23;
class Box {
	double w;
	double h;
	double d;
	
	//паралелепіпед заданий явно
	public Box(double w, double h, double d){	
		this.w=w;
		this.h=h;
		this.d=d;
	}
	//копія об'єкта
	public Box(Box q){			
		this.w=q.w;		//this "буде замінено" на ім'я змінної-вказівника
		this.h=q.h;		//після якої записано = new;
		this.d=q.d;
	}
	//загальний випадок
	public Box(){
		w = -1;
		h = -1;
		d = -1;
	}
	//куб
	public Box(double side){
		w = h = d = side;	//this(side,side,side);	//== один з попередніх конструкторів
	}
	
	public double vol(){
		return w*h*d;
	}
	
	public String toString() {
		return "Розмір: " + w + " на " + 
				d + " на " + h + ".";
	}
	  
	static {
		System.out.println("Клас Box щойно було завантажено.");
	}
}

class ColorBox extends Box{
	public String color;
	
	//паралелепіпед заданий явно
	public ColorBox(double w, double h, double d, String c){
		super.w=w;
		super.h=h;
		super.d=d;
		color = c;
	}
	//копія об'єкта
	public ColorBox(ColorBox q){
		super(q);
		color = q.color;
	}
	//загальний випадок
	public ColorBox(){
		super();
		color = "Білий";
	}
	//куб
	public ColorBox(double w, String c){
		super(w);
		color = c;
	}
}

class BoxDemo {
	public static void qmain(String args[]){
		
		System.out.println("BoxDemo Щойно почав своє виконання.");
		
		System.out.println("Перед першим використанням Box.");
		Box my1 = new Box(10,20,15);
		System.out.println("Після першого використання Box.");
		ColorBox my2 = new ColorBox(11,21,16,"Синій");

		System.out.println("Перша скриня " + my1.vol());
		my1 = my2;
		System.out.println("Друга скриня " + my1.vol() + " Колір: " + my2.color);
	}
}