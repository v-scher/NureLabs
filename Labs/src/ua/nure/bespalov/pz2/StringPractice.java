package ua.nure.bespalov.pz2;

public class StringPractice {
	static String first = "first";
	static String second = "second";
	static String odd = "abcdcba";
	
	static void p(Object o){
		System.out.println(o);
	}
	
	public static void main(String[] args) {
		easy();
		System.out.println();
		middle();
		System.out.println();
		hard();
	}

	private static void easy() {
		String s = first.substring(1) + second.substring(1);
		p("1. ��'������� ����� ��� ������ �������: " + s);
		
		int begin = (odd.length() - 3) / 2;
		s = odd.substring(begin, begin + 3);
		p("2. ������ ��� ����� � �����: " + odd + " -> " + s);
		begin = (first.length() - 3) / 2;
		s = first.substring(begin, begin + 3);
		p("   ������ ��� ����� � �����: " + first + " -> " + s);
		
		s =  first.substring(first.length() - 2, first.length()) + first.substring(0, first.length() - 2);
		p("3. �� ������ ����� ��������� �� �������: " + first + " -> " + s);
		s =  odd.substring(odd.length() - 2, odd.length()) + odd.substring(0, odd.length() - 2);
		p("   �� ������ ����� ��������� �� �������: " + odd + " -> " + s);
	}

	private static void middle() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < first.length(); i++)
			b.append(first.charAt(i)).append(first.charAt(i));
		p("1. ������ ������ ������������� �����: " + first + " -> " + b);
		
		int c = 0;
		String sentance = "bob is bab";
		for (int i = 0; i < sentance.length() - 2; i++)
			if (sentance.charAt(i) == 'b' && sentance.charAt(i + 2) == 'b')
				c++;
		p("2. ʳ������ �������� \"b*b\" � �������: " + sentance + " -> " + c);
		
		String sentance2 = "th*is is sum*mer";
		b = new StringBuilder();
		//sentance2 = sentance2.replaceAll("*\**", replacement)
		int begin = 0;
		int end = 0;
		for (int i = 0; i < sentance2.length(); i++)
			if (sentance2.charAt(i) == '*')
			{
				begin = i - 1;
				if (begin > 0)
					b.append(sentance2.substring(end, begin));
				end = i + 2;
			}
		b.append(sentance2.substring(end));
		p("3. � ������� ���� � \"*\" ������� ����� ���� �� ������ �������: " + sentance2 + " -> " + b);
	}

	private static void hard() {
		String sentance = "qqqa qqqS qqqA qqqq wwww qqqw qqqs qqqA";
		p("1. ʳ������ ���, �� ����������� �� A ��� S ��������� �� �������: " + sentance + " -> " + (getSuffixNumber(sentance, 'a') + 
				+ getSuffixNumber(sentance, 'A') +
				+ getSuffixNumber(sentance, 's') + 
				+ getSuffixNumber(sentance, 'S')));
		
		String str = "qqq";
		p("2. �����: " + sentance + ", � ����� ������ ������� �� ���������: " + str + " -> " + sentance.replaceAll(str, ""));
	}
	
	static int getSuffixNumber(String sentance, char lastLetter){
		int i = sentance.indexOf(lastLetter + " ");
		int c = 0;
		
		while (i > 0)
		{
			c++;
			i = sentance.indexOf(lastLetter + " ", ++i);
		}
		
		if (sentance.endsWith(lastLetter + ""))
			c++;
		
		return c;
	}
}
