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

// класс Disk спадкує клас Circle
class Disk extends Circle{
	// створили константу
	public final static String	name = "Круг/Disk";
	
	// конструктор
	Disk(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	// деструктор
	@Override
	protected void finalize() throws Throwable {
		// виконуємо свої дії перед видаленням об'єкта
		System.out.println("Круг знищено.");
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

	// перевірка розташування точки
	@Override
	public boolean contains(Point p) {
		// ділянка, де може трапитись виняток
		try {
			// обчислюємо дистанцію між центром і заданою точкою
			double distance = Math.pow((p.x - center.x), 2) + Math.pow((p.y - center.y), 2);
			// якщо ця відстань менше радіуса
			if ( distance < radius)
				// точка належить кругу
				return true;
		} catch (NullPointerException e) {
			// реакція на виняток
			System.out.println("Центр не задано!");
		}
		// точка не належить кругу
		return false;
	}

	@Override
	public void show() {
		try {
			System.out.println("Центр: (" + this.center.x + ", " + this.center.y + "), радіус: " + this.radius);
		} catch (NullPointerException e) {
			System.out.println("Центр не задано!");
		}
	}
	
	public static void main(String args[]) {
		System.out.println("Створили об'єкт класу круг:");
		Disk disk = new Disk(new Point(12,12), 12);
		disk.show();
		
		System.out.println("Змінили центр:");
		disk.setCenter(new Point(1,1));
		disk.show();
		
		System.out.println("Змінили радіус:");
		disk.setRadius(10);
		disk.show();
		
		if (disk.contains(new Point(1,1))) {
			System.out.println("Даний круг містить точку (1,1)");
		} else {
			System.out.println("Даний круг не містить точки (1,1)");
		}
		
		System.out.println("Виняток:");
		// записуємо в disk.center вказівник на null
		disk.setCenter(null);
		// метод шоу не зможе використати поля X та Y за відсутності точки центра
		// і викине виняток
		disk.show();
	}
}
