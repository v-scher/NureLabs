package ch1_23;
// %s - для String
// %S - для String, що буде виведений великими літерами
// %c - для окремого символа Char
// %n - для символа нового Рядка
// %% - для символа %
// %t - для час і дата
// %T - 
	    
// %d - для десяткового Цілого числа
// %o - для вісімкового Цілого числа
// %x - для шістнадцяткового Цілого
// %X
// %f - для десяткового float
// %e - float в науковій нотації
// %E - 
// %g - використовує %f або %e , дивлячись що коротше
// %G - 
	    
// %h - для Hash-коду аргумента
// %H -

import java.util.*; 
 
class FormatterDemo { 
	public static void main(String args[]) { 
System.out.println("Використовуємо елементарне форматування TRY-RESOURCE");
	try(Formatter fmt = new Formatter()){ 
		fmt.format("Рядок \"with Java\": %s \n"
				+ "десяткове ціле 10: %d \n"
				+ "float 98,6: %f \n"
				+ "вісімкове ціле 196: %o \n"
				+ "шістнадцяткове ціле 196: %X \n", 
				"with Java", 10, 98.6, 196, 196); 
		System.out.println(fmt); 
		}
		System.out.println();
		
System.out.println("Використовуємо %g - використовує %f або %e , дивлячись що коротше");
		Formatter fmt = new Formatter();  //не обов'язково робити так
		//множити 1000 на 100 (додавати по 2 нулі),
		//доки не сягне 1*(+10)^10 (десять нулів) невключно - 4 рази (3 нулі, 5, 7, 9)
		for(double i=1000; i < 1.0e+10; i *= 100) { 
			//"format" додає до попереднього рядка нове значення
			fmt.format("%g ", i); 
			System.out.println(fmt); 
		} 
	    System.out.println();
	    
System.out.println("Використовуємо %t");
		fmt = new Formatter(); 
		Calendar cal = Calendar.getInstance(); 
		
		// Display standard 12-hour time format. 
		fmt.format("Calendar %%tr:\n %tr", cal); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display complete time and date information. 
		fmt = new Formatter(); 
		fmt.format("Calendar %%tc:\n %tc", cal); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display just hour and minute. 
		fmt = new Formatter(); 
		fmt.format("Calendar %%tl:%%tM:\n %tl:%tM", cal, cal); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display month by name and number. 
		fmt = new Formatter(); 
		fmt.format("Calendar %%tB %%tb %%tm:\n %tB %tb %tm", cal, cal, cal); 
		System.out.println(fmt); 
		fmt.close();
	    System.out.println();
	    
System.out.println("Використовуємо вказівники мінімальної ширини поля");
		fmt = new Formatter(); 
		fmt.format("10.12345 %%f: |%f|%n" 
				+ "10.12345 %%12f: |%12f|%n" 
				+ "10.12345 %%012f: |%012f|", 
		           10.12345, 10.12345, 10.12345); 
		System.out.println(fmt); 
		fmt.close(); 
	    System.out.println();

System.out.println("Будемо виводити числа в поле шириною 4 (%4d), їх квадрати і куби");
		for(int i=1; i <= 10; i++) { 
			fmt = new Formatter(); 
		
		    fmt.format("%4d %4d %4d", i, i*i, i*i*i); 
		    System.out.println(fmt); 
		    fmt.close(); 
		}
	    System.out.println();
	    
System.out.println("Використовуємо вказівник точності (max кількості символів в комірці)");
		fmt = new Formatter(); 
		
		// Format 4 decimal places. 
		fmt.format("123.1234567 %%.4f :\n %.4f", 123.1234567); 
		System.out.println(fmt); 
		fmt.close();
		
		// Format to 2 decimal places in a 16 character field. 
		fmt = new Formatter(); 
		fmt.format("123.1234567 %%16.2e:\n %16.2e", 123.1234567); 
		System.out.println(fmt); 
		fmt.close();
		
		// Display at most 15 characters in a string. 
		fmt = new Formatter(); 
		fmt.format("\"Formatting with Java is now easy.\" %%.15s: \n %.15s", "Formatting with Java is now easy."); 
		System.out.println(fmt); 
		fmt.close();
	    System.out.println();

System.out.println("Використовуємо прапорці формату");
		fmt = new Formatter(); 
		
		// - вирівнювання ліворуч
		// # альтернативний формат перетворення
		// 0 виведення доповнюється нулями замість пробілів
		// пробіл додатнім числам передує пробіл
		// + додатнім числам передує +
		// , числові значення, що включають групові роздільними
		// ( від'ємні числа 
		
	System.out.println("Вирівнювання вправо за замовчуванням |%10.2f|");
		fmt.format("|%10.2f|", 123.123); 
		System.out.println(fmt); 
		fmt.close();
		
	System.out.println("Вирівнювання вліво |%-10.2f|");
		fmt = new Formatter(); 
		fmt.format("|%-10.2f|", 123.123); 
		System.out.println(fmt); 
		fmt.close();
		
	System.out.println("Додатнім числам передує пробіл % d");
		fmt = new Formatter(); 
	    fmt.format("% d", -100); 
	    System.out.println(fmt); 
	    fmt.close();
	 
	    fmt = new Formatter(); 
	    fmt.format("% d", 100); 
	    System.out.println(fmt); 
	    fmt.close();
	 
	    fmt = new Formatter(); 
	    fmt.format("% d", -200); 
	    System.out.println(fmt); 
	    fmt.close();
	 
	    fmt = new Formatter(); 
	    fmt.format("% d", 200); 
	    System.out.println(fmt); 
	    fmt.close();
	System.out.println("Використовуємо кому для розділення довгих числе ПРОБІЛАМИ %,.2f");
		fmt = new Formatter(); 
	    fmt.format("%,.2f", 423452345.34); 
	    System.out.println(fmt); 
	    fmt.close();
	    System.out.println();
	    
System.out.println("Використовуємо індекс аргументів\n" 
		+ "дозволяє використовувати аргумент двічі \n" 
		+ "вказується або номер аргумента або < для попереднього");
		fmt = new Formatter(); 
		cal = Calendar.getInstance(); 
		
		fmt.format("Сьогодні %te-е %<tB %<tY року", cal);
		//fmt.format("Сьогодні %te-е %1tB %1tY року", cal); //теж саме 
		System.out.println(fmt); 
		fmt.close();
	    System.out.println();
	} 
}