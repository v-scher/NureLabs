package other;

// Перший клас
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

// Другий класс
class MetropolitanStation {
	private String name;
	private int year;
	// масив годин (елементів першого класу)
	private Hour hours[];
	
	MetropolitanStation(String name, int year, Hour[] hours) {
		this.setName(name);
		this.setYear(year);
		this.hours = hours;
	}
	
	public int sumOfPassengers() {
		// створили змінну для збереження суми кількості пасажирів
		int sum = 0;
		// в циклі від першого (нульового) до останнього (кількість годин - 1) перебираємо елементи першого класу 
		for (int i = 0; i < this.hours.length; i++)
			// додаємо кількість пасажирів з кожної години до зміннох sum
			sum += this.hours[i].getNumberOfPassengers();
		
		// після проходження повного циклу "повертаємо" суму
		return sum;
	}
	
	public Hour minPassengersHour() {
		// пееребором шукаємо годину з найменшою кількістю пасажирів
		// повнемо з нульового елемента
		
		Hour min = this.hours[0];
		
		// в циклі перебираємо усі години "станції", для якої викликана ця функція - this.hours
		for (int i = 1; i < this.hours.length; i++)
			// якщо в ЦЬОМУ (this) об'єкті MetropolitanStation 
			// у об'єкта Hour з номером "i" (hours[ i ])
			// кількість пасажирів (getNumberOfPassengers()) менша ( < ), аніж кількість пасажирів
			// години min
			if (this.hours[i].getNumberOfPassengers() < min.getNumberOfPassengers())
				// вважаємо поточну (hours[i]) годину новим мінімумом
				min = this.hours[i];
		
		// після повного перебору повертаємо "годину" з мінімальною кількістю пасажирів
		return min;
	}

	public Hour longestCommentHour() {
		// визначимо годину з найдовшим коментарем повним перебором
		// почнемо з нульової
		Hour longest = this.hours[0];
		
		// перебираємо ВСІ години від 1 (нульова як відправна точка збережена в longest) до останньої
		for (int i = 1; i < this.hours.length; i++)
			// з об'єкту MetropolitanStation (this) 
			// обираємо елемент вкладеного масива (hours[i]) класу Hour
			// для елементу Hour викликаємо метод getComment(), який повертає об'єкт класу String - коментар до години
			// для коментаря - об'єкта класу String - викликаємо функцію Split(), яка повертає МАСИВ об'єктів String
			// на які розбивається ПРОБІЛАМИ початковий текст коментаря String - це будуть окремі слова
			// у масива, який повертає split(), перевіряємо поле lenght, яка відповідає кількості елементів String - окремих слів коментаря
			// і порівнюємо з аналогічним параметром години, яку ми зберегли в longest - змінній для години з найдовшим коментарем
			if (this.hours[i].getComment().split(" ").length > longest.getComment().split(" ").length)
				// якщо поточна (hours[i]) година має довший коментар - зберігаємо її в longest
				longest = this.hours[i];
		
		//після повного перебору повертаємо годину з найдовшим коментарем
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
		// створюємо об'єкт класу MetropolitanStation
		// заповнюємо поля 
		// назва - name = "123"
		// рік відкриття - year = 1993
		// масив "годин", які описують певний робочий період станції ми ще не сформували, тому - hours = null
		MetropolitanStation MS = new MetropolitanStation("123", 1993, null);
		// встановлюємо об'єкту MS нове поле name = "Київська"
		MS.setName("Київська");
		// встановл.ємо об'єкту MS нове поле year = 1990
		MS.setYear(1990);
		
		// створюємо масив "годин" 
		// для початку виділяємо пам'ять для семи елементів Hour
		Hour hourse[] = new Hour[7];
		// створюємо нову годину (new Hour()) і поміщаємо в комірку hours[0]
		// заповнюючи поля нового об'єкта 
		// numberOfPassengers = 1000
		// comment = "Нічого не відбувалось"
		hourse[0] = new Hour(1000, "Нічого не відбувалось");
		// далі за анологією
		hourse[1] = new Hour(2000, "Виконали прибирання");
		hourse[2] = new Hour(3000, "Кінець робочого дня");
		hourse[3] = new Hour(4000, "Кінець концерту Bon Jovi на площі");
		hourse[4] = new Hour(600, "Нічого не відбувалось");
		hourse[5] = new Hour(2000, "Нічого не відбувалось");
		hourse[5].setComment("Проблеми з живленням");
		hourse[6] = new Hour(1000, "Нічого не відбувалось");
		// коли всі комірки масиву hours[] заповнено, встановлюємо в поле hours об'єкта MS щойно створений масив з ім'ям hours (тавтологія вийшла)
		MS.setHourse(hourse);
		
		// виводимо для об'єкту MS поля name та year за допомогою get-методів ("геттерів" чи як вам завгодно)
		System.out.println("Станція: " + MS.getName() + ", рік: " + MS.getYear());
		// обчислюємо і виводимо за допомогодю метода sumOfPassengers() суму пасажирів
		System.out.println("Сумарна кількість пасажирів: " + MS.sumOfPassengers());
		// знаходимо в масиві-члені hours[] об'єкта MS ГОДИНУ з мінімальною кількістю пасажирів за допомогою метода minPassengersHour()
		// для об'єкта Hour, який повертає функція minPassengersHour() викликаємо метод getNumberOfPassengers(), який повертає КІЛЬКІСТЬ пасажирів
		// і виводимо на екран
		System.out.println("Найменша кількість пасажирів: " + MS.minPassengersHour().getNumberOfPassengers());
		
		// для об'єкта MS знаходимо ГОДИНУ з найдовшим коментарем за допомогою метода longestCommentHour()
		// для ою'єкта Hour, який повернув метод longestCommentHour(), викликаємо метод getComment(), який повертає нам коментар цієї години
		// і виводимо цю інфу на екран
		System.out.println("Найдовший коментар: " + MS.longestCommentHour().getComment());
	}
}
