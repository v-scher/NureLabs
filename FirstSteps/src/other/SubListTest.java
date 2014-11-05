package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubListTest {
	public static void p(String _s)
	{
		System.out.println(_s);
	}
	
	public static void p(List _list)
	{
		for (Object elem : _list)
			System.out.print(elem.toString() + " ");
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		p("List of 10 elements");
		ArrayList<Integer> list = new ArrayList<Integer>(
				Arrays.asList(0,1,2,3,4,5,6,7,8,9)
			);
		p(list);
		
		p("Sublist from 2 to 8");
		List<Integer> sublist = list.subList(2, 8);
		p(sublist);
		

		p("Sublist after removing 3,4");
		sublist.remove(1);
		sublist.remove(1);
		p(sublist);
		p("List after removing 3,4");
		p(list);

		p("Sublist after sorting descend");
		Collections.sort(sublist, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		p(sublist);
		p("List after sorting");
		p(list);
	}
}
