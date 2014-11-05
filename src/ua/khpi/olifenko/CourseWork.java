package ua.khpi.olifenko;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CourseWork {
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	static int gamesPlayed = 0;
	
	public static void main(String args[]) throws Exception{
		Game game =  new Game(console);
		game.chooseLevel();
		
		while(true){
			game.start();
			game.writeResults();
			gamesPlayed++;
			
			if (game.isWinner()){
				Game.print( "�� ����������!!!");
				if (game.subLevel == 8){
					if(game.max < 1000){
						game.max *= 10;
						game.subLevel = 3;
					}
				} else {
					game.subLevel++;
				}
			} else {
				Game.print( "�� �������� :(");
			}
			
			if(gamesPlayed % 3 == 0){
				Game.print("ǳ����� ��?");
				String command = console.readLine();
				if( !command.equalsIgnoreCase("���"))
					break;
			}	
		}
		Game.print("� ���� ������ �����!");
	}
}
