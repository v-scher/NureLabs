package other;

import java.util.ArrayList;

public class KostyaMerzotnyk {
	
	public static void combination(ArrayList<Character> left, ArrayList<Character> right) {		
		if (right.size() == 1) {
			left.add(right.get(0));
			System.out.println(left);
		} else {
			for (int i = 0; i < right.size(); i++) {
				ArrayList<Character> newLeft = (ArrayList<Character>) left.clone();
				newLeft.add(right.get(i));
				ArrayList<Character> newRight = (ArrayList<Character>) right.clone();
				newRight.remove(i);
				combination(newLeft, newRight);
			}
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Character> left = new ArrayList<Character>();
		ArrayList<Character> right = new ArrayList<Character>();
		String text = "qwerty";
		for (int i = 0; i < text.length(); i++)
			right.add(text.charAt(i));
		combination(left, right); 
	}
}