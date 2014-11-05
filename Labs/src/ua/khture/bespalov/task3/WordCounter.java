package ua.khture.bespalov.task3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {
	static String word = "смысл";
	static String testText = "Происхождение слова барокко вызывает больше споров, чем смысл названия всех \n"
			+ "остальных стилей. Существует несколько версий происхождения. Португальское barroco \n"
			+ "— жемчужина неправильной формы, смысл не имеющая оси вращения, такие смысл жемчужины были \n"
			+ "популярны в XVII веке. В итальянском baroco — ложный силлогизм, азиатская форма \n"
			+ "логики, приём софистики, основанный наметафоре. Как и жемчужины неправильной формы, \n"
			+ "силлогизмы смысл барокко, ложность смысл которых скрывалась их метафоричностью. \n"
			+ "Использование термина со стороны критиков и историков искусства берет начало \n"
			+ "со 2-й половины смысл XVIII и относится, в первое время, к фигуративному искусству и, \n"
			+ "последовательно, также к литературе. Вначале этот термин приобрел негативный смысл.";
	
	static void p(Object _text)
	{
		System.out.println(_text);
	}
	
	public static void main(String args[])
	{
		p("Words found: " + method1(testText));
		p("Words found: " + method1(getTextFromFile()));
		
		p("Words found: " + method2(testText));
		p("Words found: " + method2(getTextFromFile()));
	}
	
	public static String getTextFromFile()
	{
		StringBuilder builder = new StringBuilder();
		
		try (FileReader reader = new FileReader("baroko.txt"))
		{
			int symbol = reader.read();
			
			while (symbol != -1)
			{
				builder.append((char)symbol);
				symbol = reader.read();
			}
		} catch (Exception e1) {}
		
		return builder.toString();
	}
	
	public static int method1(String text)
	{
		int count = 0;
		int i = text.indexOf(word);
		
		while (i > 0)
		{
			count++;
			p(i + " " + word);
			i = text.indexOf(word, i + 1);
		}
		return count;
	}
	
	public static int method2(String text)
	{
		String[] parts = text.split(word);
		return (parts.length - 1);
	}
}
