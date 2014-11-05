package ua.khpi.kolesnyk;

import java.awt.FontMetrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseWork {
    static TextWindow wnd = new TextWindow();// сделали окно статитесим
    
	static String vowels = "уеыаоэяиюёУЕЫАОЭЯИЮЁ";
	static String consonant = "йцкнгшщзхфвпрлджчсмтбЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
	static String signs = "ьъ";
	static char y = 'й';
	static String prefix = "раз";
	
	private static boolean isVowel(char letter){//проверяет является ли  буква гласной
		return vowels.contains("" + letter);// проверяет содержит ли массив эту букву
	}
	
	private static boolean isConsonant(char letter){//проверяет является ли согласной
		return consonant.contains("" + letter);
	}
	
	private static boolean isSign(char letter){
		return signs.contains("" + letter);
	}
	
	//массив слогов со слова( СКОЛЬКО ГЛАСНЫХ СТОЛЬКО И СЛОГОВ)
	private static String[] getSyllsFromWord(String word) {
		// ----------------- ПРОСТО ПОЛІЧИЛИ СКІЛЬКИ ГОЛОСНИХ У СЛОВІ (==складів)
		int syllCounter = 0;// счетчик для слогов
		for(int i = 0; i < word.length(); i++){// пройти по слову
			char tmpChar = word.charAt(i);// заводим букву которую мы сечас проверяем
			if(isVowel(tmpChar))
				syllCounter++;
		}
		
		// ---------------- ЯКЩО НЕМАЄ ГОЛОСНОЇ, ТО ПОВЕРНУТИ ЄДИНИЙ СКЛАД - ВСЕ СЛОВО 
		if (syllCounter == 0){ // если нет гласной
			String[] res = new String[1];// массив для одного символа
			res[0] = word;//если нет гласной то не слога
			return res;//выводим слово
		}
		
		String[] sylls = new String[syllCounter];// массив для хранения слогов

		syllCounter = 0;
		
		// ----------------- БУЛЕВІ ЗМІННІ ДЛЯ КОНТРОЛЮВАННЯ ПРОЦЕСУ РОЗБИТТЯ СЛОВА 
		boolean firstSyll = true;
		boolean lastSyll = false;
		boolean vowelIsAlreadyThere = false;// гласный еще не найден

		// ----------------- ЗАПИСАЛИ В КІНЦЕВИЙ МАСИВ ПОРОЖНІ РЯДКИ (для початку)
		for( int i = 0; i < sylls.length; i++)
			sylls[i] = "";
		
		// ----------------- СКАНУЄМО ВСЕ СЛОВО ПО ЛІТЕРАХ
		for(int i = 0; i < word.length(); i++){// сканируем буквы в слове
			char letter = word.charAt(i);
			
			try{
				// ------------- ГОЛОСНІ ВИЗНАЧАЮТЬ:
				if( isVowel(letter) ){// если текущая буква гласная 
					if( vowelIsAlreadyThere){// а в текщем слоге гласная уже есть
						// ----- ПОЧАТОК НОВОГО СКЛАДУ, ЯКЩО В ПОТОЧНОМУ ВЖЕ Є ГОЛОСНА
						syllCounter++;// счетчик идет дальше
						firstSyll = false;// и первый слог закончен с первой гласной
						vowelIsAlreadyThere = true;//  отметим что в новом слоге есть гласная
						sylls[syllCounter] += letter;//добавлем эту гласную в следующий(и уже в текущий) слогу
					} else { // если гласной в слоге еще нет
						// ----- ПОЯВУ ПЕРШОЇ ГОЛОСНОЇ В СКЛАДІ
						// ----- щоб визначити чи можна "це" вважати складом (без голосної ж ніяк)
						sylls[syllCounter] += letter;// дописываем текущую гласную в слог
						vowelIsAlreadyThere = true;// и гласная все-таки присутствует
					}
				} else if (isConsonant(letter)) {// если согласная
					// ------------- ПРИГОЛОСНІ ВИЗНАЧАЮТЬ:
					if( vowelIsAlreadyThere ){// если уже есть гласная
						if( i+1 < word.length() ){// перевіряємо чи можливо пперевірити наступну літеру
							//то проверяем следующую и если она согласная либо знак 
							if( isConsonant(word.charAt(i + 1)) || isSign(word.charAt(i + 1)) ){
								// ------ РОЗРИВ МІЖ СКЛАДАМИ, ЯКЩО 2 ПРИГОЛОСНІ ПОРУЧ
								// ------ і в першому з них ВЖЕ Є голосна
								sylls[syllCounter] += letter;// добавляем текущую к к текущему слогу
								
								// ------ якщо "друга літера" не знак і склади ще не закінчилися
								// ------ то переходимо до наступного
								if( ! isSign(word.charAt(i + 1)) && ! lastSyll)// если следующая буква не знак и это не последний слог то переходим к следующему слогу
									syllCounter++;
								
								firstSyll = false;// указываем что это не первй слог
								vowelIsAlreadyThere = false;// до сих пор нет гласной
							} else {
								// ------ КІНЕЦЬ СКЛАДУ (голосна в ньому вже є, тобто склад - закритий)
								if( firstSyll 
								&& (sylls[syllCounter] + letter).equals(prefix) ){
									// ------ перевірка на префікс - щоб не розірвати
									sylls[syllCounter] += letter;
									if ( !lastSyll) syllCounter++;
									firstSyll = false;
									vowelIsAlreadyThere = false;
								} else {
									if ( !lastSyll) syllCounter++;
									firstSyll = false;
									vowelIsAlreadyThere = false;
									sylls[syllCounter] += letter;
								}
							}
						} else {
							// ---- В УСІХ ІНШИХ ВИПАДКАХ ПРОСТО ДОПИСУЄМО ЛІТЕРИ В СКЛАДИ
							sylls[syllCounter] += letter;
						}
					} else {
						sylls[syllCounter] += letter;
					}
				} else if( isSign(letter) ){
					// ЯКЩО ПОТОЧНА ЛІТЕРА ЗНАК - ДОДАЄМО В СКЛАД (як останню) і ПЕРЕХОДИМО ДО НАСТУПНОГО
					sylls[syllCounter] += letter;
					
					if ( !lastSyll) syllCounter++;
					
					firstSyll = false;
					vowelIsAlreadyThere = false;
				}
				
				if( syllCounter == sylls.length-1 )
					lastSyll = true;

			}catch(ArrayIndexOutOfBoundsException e){
				sylls[sylls.length-1] += letter;
			}
		}
		
		return sylls;
	}
	// разбивает слова сразу на домены!возвращает массив доменов
	private static String[] prepareWord(String word){
		if ( word.length() < 4){// если есть только один слог то он и есть доменом
			String[] res = new String[1];// массив для одного домена
			res[0] = word;// делаем этот единственный слог целым доменом
			return res;// и выводим его
		}
		
		String[] sylls = getSyllsFromWord(word);// разбивает на слоги!
		if ( sylls.length == 1){// если есть только один слог то он и есть доменом
			String[] res = new String[1];// массив для одного домена
			res[0] = word;// делаем этот единственный слог целым доменом
			return res;// и выводим его
		}
			// слияние первого и второго слога в случае если первый слог это одна буква
		ArrayList<String> domens = new ArrayList<>();
		
		int start = 0;//позиция после первого домена
		// если  первый слог из одной буквы и
		if (sylls[0].length() == 1 &&
			sylls.length > 1){// и если слогов больше одного
			domens.add(sylls[0] + sylls[1] + '-');// если найден слог с одной буквы то присоеденяем его к следующему
			//после них дефис
			
			start += 2;//если слили два первых слога то начинать надо с третьего(его номер 2)
		} else {
			domens.add(sylls[0] + '-');// если первй слог НЕ с одной буквы то делаем его отдельным доменом
			start += 1;// поэтому нужно начинать со второго слога
		}
		//
		String lastDomen;
		// последним будем просматривать последний слог
		int end = sylls.length - 1;
		//если последний слог с одной буквы
		if (sylls[sylls.length-1].length() == 1){
			// то последний домен формируется двумя слогами
			lastDomen = sylls[sylls.length-2] + sylls[sylls.length-1];
			//не нужно просматривать слово дальше со второй позиции с конца слова
			end -= 2;
		} else {
			lastDomen = sylls[sylls.length-1];// если слоги не соеденили то последним доменом будет последний слог
			end -= 1;
		}
		//переписываем все слоги между первый и последний доменами
		for (int i = start; i <= end; i++)
			domens.add(sylls[i] + '-');// записываем в коллекцию домен
		
		domens.add(lastDomen);
		
		return domens.toArray(new String[0]);// превратили коллекцию доменов в массив
	}
	// передаем ему текст а возвращает домены которые необходимо вывести
	public static ArrayList<String> prepareText(String text){
		//поиск где начинается текст(по первой букве)
		int firstLetter = 0;
		//в классе керектер вызываем метод,проверяющий позицию буквы
		while (!Character.isLetter(text.charAt(firstLetter)) ) 
			firstLetter++;
		
        // обрезаем текст если сначала идут не символы
		text = text.substring(firstLetter);
		
		ArrayList<String> domens = new ArrayList<>();
		//делит текст с помощью разделителя пробел
		Scanner sc = new Scanner(text);
      	//проверяет следущий кусок текста( то что между пробелами) последний ли он
		while (sc.hasNext()){
		//
			String word = sc.next();
			//
			String newWord = "";
			for (int i = 0; i < word.length(); i++)
			//цыкл нужен чтобы убрать лишние символы(запятые)
				if (Character.isLetter(word.charAt(i)))//проходит по словам,узнает позицию нужного символа
				//и записывает в новое слово только нужные символы(буквы)
					newWord += word.charAt(i);
			// превращет слово в массив слогов
			String[]  sylls = prepareWord(newWord);
			for (String syll : sylls){
				//добавляем слова в массив в качестве доменов
				domens.add(syll);
			}
			domens.add(" ");// и добавляем пробел после каждого слова
		}
		sc.close();
		
		return domens;
	}
    //ПЕРЕНОС. добавляет в текстовое окно по принципу(строка в новый абзц или остается в старом)по ширине окна
	private static void setTextIntoTheWindow(String text) {
		//коллекция с отдельными доменами(малекнькие слова,слова с дефисами,пробелы или последний слог без дефиса)
		if (text.equals("")) return;
		ArrayList<String> domens = prepareText(text);
		//переменная с особенностями для шрфита(находится в Окне)
		FontMetrics fm = wnd.getFM();
		String resultText = "";
		String line = "";//строку которую мы вводим
		//проходим по цыклу доменов
		for (int i = 0; i < domens.size(); i++){
			//проверяем если ширина текущего рядка с доменом выходит за максимум то просто делаем энтер
			if (fm.stringWidth(line + domens.get(i)) > wnd.textMaxWidth()){
				line += "\n";//заканчиваем строку
				// в результирующий текст добавляем проверенную строку
				resultText += line;
				//начинаем заполнять новую строку
				line = domens.get(i);
			} else {// если не выходит за рамки ширину та добавляем в предыдущий ряд без абзаца
				line += domens.get(i);
			}
		}
		resultText += line;
		
		wnd.setText(resultText);
	}
	
	public static void main(String args[]) throws IOException{
		// указываем путь к нему
	    File file = new File("C:\\Users\\Володимир\\Downloads\\Колесник тест.txt");
	    //поток для чтиения файла
		BufferedReader reader = new BufferedReader(new FileReader(file));
		//весь текст по абзацам добавляется в тхт
		String text = "";
		String tmp;//в нее читаем с консоли(хранит один абзац)
		//
		while ( (tmp = reader.readLine()) != null)//до конца файла
			//в текст добавляем считанные с консоли абзацы
			text += tmp + '\n';
		reader.close();//закрываем поток
		//записывает в окошко
		setTextIntoTheWindow(text);
		//ввод с консоля
		System.out.println("Введи текст! (Или \"выход\")");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		//то что получили с консоли 
		String command;
		//пока она НЕ равна "выход" продолжаем вводить текст
		while ( ! (command = console.readLine()).equalsIgnoreCase("выход"))
			setTextIntoTheWindow(command);
		
		System.exit(0);//выход с удалением всех потоков
	}
}