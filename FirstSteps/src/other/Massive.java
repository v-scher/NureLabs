package other;

abstract class Massive {
	// масив ДОВІЛЬНИХ чисел (double/int)
	Number[] mass1;
	
	public abstract void show();
	
	public abstract Massive add(Massive mass2);
	
	public abstract Massive sub(Massive mass2);
	
	public abstract Massive mult(Massive mass2);

	public static void main(String args[]) {
		// створюємо масив чисел для ініціалізації наших об'єктів
		Number[] tmpMass = new Number[10];
		// заповнюємо цей масив
		for (int i = 0; i < 10; i++)
			tmpMass[i] = i;
		
		// заповнюємо два масиви однаковими даними
		Massive1D m1 = new Massive1D(tmpMass);
		Massive m2 = new Massive1D(tmpMass);
		
		System.out.println("Результат використання add():");
		m1.add(m2).show();
		System.out.println("Результат використання sub():");
		m1.sub(m2).show();
		System.out.println("Результат використання mult():");
		m1.mult(m2).show();
		
		System.out.println("Перевірка константи.\nКількість вимірів m1: " + m1.dimensions);
		
		System.out.println("Зараз ми викличемо виняток");
		// записуємо в excMass.mass1 вказівник на null
		Massive1D excMass = new Massive1D(null);
		// метод show() звернеться до mass1 і викене виняток
		excMass.show();
	}
}

// Massive1D спадкує поля та методи від класу Massive
class Massive1D extends Massive {
	// створили константу
	public final static int dimensions = 1;
	
	// Конструктор
	Massive1D(Number[] massive) {
		this.mass1 = massive;
	}
	
	// деструктор
	@Override
	protected void finalize() throws Throwable {
		// виконуємо свої дії перед видаленням об'єкта
		System.out.println("Це був славний масив!");
		super.finalize();
	}

	@Override
	public void show() {
		// ділянка, де може трапитись виняток
		try {
			for (int i = 0; i < this.mass1.length; i++)
				System.out.println(mass1[i]);
		} catch (NullPointerException e ) {
			// обробка винятку
			System.out.println("Трапилась виняткова подія! Імовірно ви забули передати масив в конструктор");
		}
		
	}

	@Override
	public Massive add(Massive mass2) {
		// створюємо тимчасовий масив такого ж розміру, як найбільший з двох заданих
		Number[] tmpMass = new Number[(this.mass1.length > mass2.mass1.length) ? this.mass1.length : mass2.mass1.length];
		
		try {
			int i;
			// додаємо всі елементи ДРУГОГО масиву до елементів першого
			for (i = 0; i < tmpMass.length; i++)
				tmpMass[i] = this.mass1[i].doubleValue() + mass2.mass1[i].doubleValue();
			
			// Додаємо решту елементів більшого масиву в тимчасовий
			if (this.mass1.length > mass2.mass1.length) {
				// Якщо перший масив більший, то додаємо його елементи
				for (int q = i; q < this.mass1.length; q++)
					tmpMass[q] = this.mass1[q];
			}
			// аналогічно попередньому if
			if (this.mass1.length < mass2.mass1.length) {
				// Якщо другий масив більший, то додаємо його елементи
				for (int q = i; q < mass2.mass1.length; q++)
					tmpMass[q] = mass2.mass1[q];
			}
		} catch (NullPointerException e ) {
			System.out.println("Трапилась виняткова подія! Імовірно ви забули передати масив в конструктор");
		}
		return new Massive1D(tmpMass);
	}

	@Override
	public Massive sub(Massive mass2) {
		// створюємо тимчасовий масив такого ж розміру, як найменший з двох заданих
		Number[] tmpMass = new Number[(this.mass1.length < mass2.mass1.length) ? this.mass1.length : mass2.mass1.length];
		
		try {
			// віднімаємо всі елементи другого масиву від елементів першого
			for (int i = 0; i < tmpMass.length; i++)
				tmpMass[i] = this.mass1[i].doubleValue() - mass2.mass1[i].doubleValue();
			
		} catch (NullPointerException e ) {
			System.out.println("Трапилась виняткова подія! Імовірно ви забули передати масив в конструктор");
		}
		return new Massive1D(tmpMass);
	}

	@Override
	public Massive mult(Massive mass2) {
		// створюємо тимчасовий масив такого ж розміру, як найменший з двох заданих
		Number[] tmpMass = new Number[(this.mass1.length < mass2.mass1.length) ? this.mass1.length : mass2.mass1.length];
		
		try {
			// перемножаємо всі елементи першого масиву на елементи другого
			for (int i = 0; i < tmpMass.length; i++)
				tmpMass[i] = this.mass1[i].doubleValue() * mass2.mass1[i].doubleValue();
			
		} catch (NullPointerException e ) {
			System.out.println("Трапилась виняткова подія! Імовірно ви забули передати масив в конструктор");
		}
		return new Massive1D(tmpMass);
	}
}