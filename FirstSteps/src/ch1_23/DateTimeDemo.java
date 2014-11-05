package ch1_23;
import java.util.BitSet;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

class DateTimeDemo {
	public static void main(String args[]) {

System.out.println("Використання Date");
		Date date = new Date();
	// display time and date using toString()
		System.out.println(date);
	// Display number of milliseconds since midnight, January 1, 1970 GMT
System.out.println("Пройшло мілісекунд з 1 січня 1970 року = " + date.getTime());
		System.out.println();
		
System.out.println("Використання Calendar - абстрактний клас (не містить конструкторів),\n" +
					"проте дозволяє створювати об'єкти(?!)");
		String months[] = {
		        "Січня", "Лютого", "Березня", "Квітеня", 
		        "Травня", "Червня", "Липня", "Серпня",
		        "Вересеня", "Жовтеня", "Листопада", "Груденя"};
		
	// Створити календар з поточним часом і датою
		Calendar calendar = Calendar.getInstance();
		
	// Відобразити час і дату, які ми щойно встановили
		System.out.println("Date: " + calendar.get(Calendar.DATE) +
							" " + months[calendar.get(Calendar.MONTH)] + 
							" " + calendar.get(Calendar.YEAR));
		
		System.out.println("Time: " + calendar.get(Calendar.HOUR) + 
							":" + calendar.get(Calendar.MINUTE) + 
							":" + calendar.get(Calendar.SECOND));
		
	// Set the time and date information and display it.
		calendar.set(Calendar.HOUR, 10);
		calendar.set(Calendar.MINUTE, 29);
		calendar.set(Calendar.SECOND, 22);
		
		System.out.println("Щойно встановлений час: " + calendar.get(Calendar.HOUR) + 
												":" + calendar.get(Calendar.MINUTE) + 
												":" + calendar.get(Calendar.SECOND));
		System.out.println();
		
	System.out.println("Використання GregorianCalendar - конкретна реалізація Calendar.");
		GregorianCalendar gcalendar = new GregorianCalendar();
	// Відобразити час і дату, які ми щойно встановили
		System.out.println("Date: " + calendar.get(Calendar.DATE) +
							" " + months[calendar.get(Calendar.MONTH)] + 
							" " + calendar.get(Calendar.YEAR));
			
		System.out.println("Time: " + calendar.get(Calendar.HOUR) + 
							":" + calendar.get(Calendar.MINUTE) + 
							":" + calendar.get(Calendar.SECOND));
			
	// Test if the current year is a leap year
	    if(gcalendar.isLeapYear(gcalendar.get(Calendar.YEAR))) {
	    	System.out.println("Зараз високосний рік");
	    } else {
	    	System.out.println("Зараз не високосний рік");
	    }
		System.out.println();
	}
}