package ua.khpi.olifenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Game {
	static int delay = 2;
	Random r = new Random();
	static BufferedReader c;
	
	public Game(BufferedReader console){
		c = console;
	}
	
	int max = 10;
	int subLevel = 3;
	int[] randoms;
	int[] usersRandoms;
	
	public void chooseLevel(){
		Game.print("Вітання!");
		Game.print("Оберіть рівень складності (1,2,3):");
		String command = null;
		try {
			command = c.readLine();
		} catch (IOException e) {}
		
		switch( command ){
		case "1":
			max = 10;
			break;
				
		case "2":
			max = 100;
			break;
				
		case "3":
			max = 1000;
			break;
			
		default:
			print("Ти дурник і я оберу за тебе (рівень 1)");
			max = 10;
		}
		
	}
	
	public void start(){
		clearDelayedPrint("Готуйся запам'ятовувати!");
		clearDelayedPrint(5);
		delay = 1;
		clearDelayedPrint(4);
		clearDelayedPrint("3 готовий?");
		clearDelayedPrint(2);
		clearDelayedPrint(1);
		clearDelayedPrint("Старт!");
		delay = 2;
		randoms = new int[subLevel];
		
		for(int i = 0; i < subLevel; i++){
			randoms[i] = r.nextInt(max);
			clearDelayedPrint(randoms[i]);
		}
	}

	public void writeResults(){
		clearDelayedPrint("Запам'ятав?");
		clearDelayedPrint("Спробуй відтворити (натискай Enter після кожного числа):");

		usersRandoms = new int[subLevel];
		for(int i = 0; i < subLevel; i++){
			try{
				// конструктор Integer(String text) перетворює текст в число (int)
				usersRandoms[i] = new Integer(c.readLine());
			} catch(Exception e){}
		}
	}
	
	public boolean isWinner(){
		// randoms.length - точний розмір масиву, а значення subLevels можна змінити руками
		for(int i = 0; i < randoms.length; i++)
			if( randoms[i] != usersRandoms[i])
				return false;
		
		return true;
	}
	
	public static void print(Object s){
		System.out.println(s);
	}
	
	public static void delayed(Object s){
		try{
			Thread.sleep(delay*1000);
		} catch(InterruptedException e){}
		print(s);
	}
	
	public static void clearDelayedPrint(Object s){
		for(int i = 0; i < 20; i++)
			print("");
		delayed(s);
	}
	
	public static void clearPrint(Object s){
		for(int i = 0; i < 20; i++)
			print("");
		print(s);
	}
}
