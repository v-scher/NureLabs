package ch1_23;
import java.io.*;
import java.util.*;
//На диску необхідно створити файл txt з текстом в форматі ASCI

class ReadFile{
	public static void main(String args[]){
		//файл знаходиться у теці з проектом
		
		//String args1[] = new String[1];
		//args1[0] = "testReadFile.txt";
		System.out.println("Введіть шлях до файлу \"testReadFile.txt\"");
		Scanner qwe = new Scanner(System.in);
		
		//args1[0] = new String ("test ReadFile.txt");
		String args1 = new String (qwe.next());
		
		int i;
		
		//переконаємось, що ім'я файлу вказане
		//if(args1.length != 1){
		//	System.out.println("Вкажи ім'я файлу");
		//	return;
		//}
		
		//спроба відкриття файлу
		try(FileInputStream fin = new FileInputStream(args1)){
			//файл відкритий, час прочитати символи
			do{
				i = fin.read();
				if(i!=-1) System.out.print((char) i);
			} while ( i != -1);
			
		} catch(IOException e){
			System.out.println("Помилка " + e);
		}
	}
}