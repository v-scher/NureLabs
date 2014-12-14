package ua.nure.bespalov.pz4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Group {
	ArrayList<Student> students = new ArrayList<>();
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	public static String readLine() throws WrongInputException {
		try {
			return console.readLine();
		} catch (IOException e) {
			throw new WrongInputException();
		}
	}

	public static void main(String[] args) {
		Group g = new Group();

		String in = "";

		System.out.println("Вводіть імена студентів і натискайте ENTER після кожного імені. Введіть \"!\" для завершення.");
		try {
			while ((in = readLine()) != null &&
					!in.equals("!") &&
					in != "" ){
				g.students.add(new Student(in));
			}
		} catch (WrongInputException e){
			System.out.println(e);
		}

		System.out.println();
		System.out.println("Всі студенти:");
		for (Student S : g.students)
			System.out.println(S);
	}
}
