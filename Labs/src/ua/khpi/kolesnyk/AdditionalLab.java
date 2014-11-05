package ua.khpi.kolesnyk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdditionalLab {
	public static void main(String args[]) throws IOException{
		BufferedReader file = new BufferedReader(new FileReader("123.txt"));
		ArrayList<String> rows = new ArrayList<>();
		String row;
		
		while ( (row = file.readLine()) != null)
			rows.add(row);
		
		Collections.sort(rows, new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.length() - arg1.length();
			}
		});
		for (String S : rows)
			System.out.println(S);
	}
}