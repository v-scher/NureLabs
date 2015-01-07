package ua.khpi.kolesnyksfriend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CorseWork {
	public static void main(String[] args) throws IOException {
		// відкриваємо теку з текстовими файлами (об'єкт dir)
		File dir = new File("C:\\Users\\Володимир\\Downloads");
		// витягаємо з теки всі файли ...
		File[] files = dir.listFiles(
			// за допомогою фільтру файлів (анонімний клас) 
			new FileFilter() {
				// вказуємо у функції accept() за якою ознакою вибирати файли з теки
				@Override
				public boolean accept(File file) { 
					// цей рядок повертає TRUE, якщо ім'я файлу закінчується на ".txt"
					return file.getName().endsWith(".txt");
				}
		});
		
		// для читання тексту з консолі створили потік і назвали його console
		// з його допомогою можна читати текст, введений в консоль з клавіатури
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		// пропонуємо користувачу ввести слова в консоль
		System.out.println("Enter words to search divided by spaces:");
		// зчитали з консолі рядок тексту з введеними термінами 
		String terms = console.readLine();
		//функція split() приймає роздільник (пробіл) і розбиває введений рядок з термінами на окремі слова
		String[] words = terms.split(" ");
		
		// HashMap зберігає пари ключ-значення. В якості ключів обрано файли,
		// а кожному ключу (файлу) відповідає колекція ArrayList<String>, яка зберігає
		// введені з консолі терміни, які знайшлися у файлі.
		// фактично це і є двовимірна структура (матриця - масив масивів) - матриця інцедентності
		HashMap<File, ArrayList<String>> matches = new HashMap<>();

		// записуємо у циклі всі знайдені файли у матрицю інцедентності і створюємо відповідні
		// але поки що порожні масиви термінів
		for (File file : files)
			matches.put(file, new ArrayList<String>());
		
		// проходимо в циклі через всі файли (files) і для кожного файлу (txtFile)
		for (File txtFile : files){
			// створюємо потік reader для зчитування файлу 
			BufferedReader reader = new BufferedReader(new FileReader(txtFile));
			// створюємо буфер в пам'яті у який запишемо інформацію з файлу
			char[] buffer = new char[10240];
			// виконуємо читання файлу за допомогою потоку і записуємо його вміст в буфер
			reader.read(buffer);
			// створюємо з буфера текстовий рядок (textFromFile)
			String textFromFile = new String(buffer);
			
			// проходимо в циклі всі терміни (масив words) і для кожного з них (term)
			for (String term : words){
				// для перевірки кожного терміна (term) створюємо новий сканер для кожного файла
				// створюємо об'єкт Scanner, який дозволяє розбивати текст (textFromFile) на слова
				// (за пробілами)
				Scanner sc = new Scanner(textFromFile);
				// tmp - тимчасова змінна для зберігання відсканованого слова
				String tmp;
				// якщо в джерелі тексту (textFromFile, який переданий в сканер sc)
				// ще є невідсканований текст
				while (sc.hasNext()){
					// тоді зчитуємо наступне слово і записуємо в тимчасову змінну (tmp)
					tmp = sc.next();
					// якщо є збіг (термін == відскановане слово)
					if (tmp.equals(term)){
						// то записуємо в масив збігів слово
						// для цього виймаємо потрібний (відповідний файлу) масив
						ArrayList<String> termsFounded = matches.get(txtFile);
						// і записуємо туди термін, який підійшов
						termsFounded.add(term);
						// припиняємо сканування (перериваємо цикл while)
						break;
					}
				}
				sc.close();
			}
			// закриваємо файл
			reader.close();
		}

		// вирівнюємо "шапку" таблиці
		System.out.print("\t\t\t");
		// виводимо всі слова з табуляцією
		for (String term : words)
			System.out.print(term + "\t\t");
		System.out.println();

		// для кожного файлу (currentFile)
		for (File currentFile : files){
			// записуємо у ліву колонку (перший запис в кожному рядку) назву файлу + вирівнювання
			System.out.print(currentFile.getName() + "\t\t");
			// проходимо в циклі всі терміни (words)
			for (String term : words){
				// перевіряємо масив збігів (termins)
				ArrayList<String> termins = matches.get(currentFile);
				// і якщо збіг є - виводимо одиничку
				if (termins.contains(term))
					System.out.print("1\t\t");
				else
					// інакше - двійку
					System.out.print("0\t\t");
						
			}
			System.out.println();
		}
		
		// закриваємо потік введення з консолі
		console.close();
	}
}