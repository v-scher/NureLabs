package ua.nure.job.Bespalov.task4;

import java.util.ArrayList;
import java.util.Date;

public class Library {
	ArrayList<Element> m_elements = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public static void main(String args[])
	{
		Library lib = new Library();
		lib.addElement(new Book("Книга 1", "США", 1993, "R.M. Allen"));
		lib.addElement(new Book("Кільце Харона", "США", 1993, "R.M. Allen"));
		lib.addElement(new Book("Розбита сфера", "США", 1995, "R.M. Allen"));
		lib.addElement(new Methodics("МВ з ПЗА", "Україна", "ХНУРЕ"));
		lib.addElement(new Magazine("Maxim", "Україна", new Date(14, 4, 20)));
		lib.addElement(new Periodic("ТелеНеделя", "Україна", new Date(14, 4, 23), 3));
		lib.addElement(new Periodic("ТелеНеделя", "Україна", new Date(14, 5, 1), 4));
		lib.addElement(new Periodic("ТелеНеделя", "Україна", new Date(14, 5, 8), 5));
		lib.addElement(new Periodic("ТелеНеделя", "Україна", new Date(14, 5, 15), 6));
		lib.addElement(new Periodic("ТелеНеделя", "Україна", new Date(14, 5, 22), 7));
		lib.addElement(new Book("Маленький принц", "Германія", 2000, "Де Сент Екзюпері"));

		System.out.println("\tПовний вміст бібліотеки:");
		showElements(lib.m_elements);

		System.out.println();
		System.out.println("\tКнижки за 1993 рік, автора \"R.M. Allen\":");
		showElements(lib.getBooksByAuthorAndYear("R.M. Allen", 1993));

		System.out.println();
		System.out.println("\tЖурнали за 4 місяць 2014-го року: ");
		showElements(lib.getMagazinesByMonthAndYear(4, 14));

		System.out.println();
		System.out.println("\tПеріодичних видань за 4 місяць: " + lib.getPeriodicsByMonthAndYear(4, 14).size());

		System.out.println();
		System.out.println("\tПеріодичних видань за 5 місяць: " + lib.getPeriodicsByMonthAndYear(5, 14).size());
	}

	public void addElement(Element _element)
	{
		m_elements.add(_element);
	}

	public Element getElement(int i)
	{
		return m_elements.get(i);
	}

	ArrayList<Element> getBooksByAuthorAndYear(String _author, int _year)
	{
		ArrayList<Element> elements = new ArrayList<>();

		for (Element E : m_elements)
			if (E instanceof Book &&
					((Book)E).getAuthor().equals(_author) &&
					((Book)E).getYear() == _year)
				elements.add(E);

		return elements;
	}

	@SuppressWarnings("deprecation")
	ArrayList<Magazine> getMagazinesByMonthAndYear(int _month, int _year)
	{
		ArrayList<Magazine> magazines = new ArrayList<>();

		for (Element E : m_elements)
			if (E instanceof Magazine &&
					((Magazine)E).getDate().getYear() == _year &&
					((Magazine)E).getDate().getMonth() == _month)
				magazines.add((Magazine)E);

		return magazines;
	}

	@SuppressWarnings("deprecation")
	ArrayList<Periodic> getPeriodicsByMonthAndYear(int _month, int _year)
	{
		ArrayList<Periodic> periodics = new ArrayList<>();

		for (Element E : m_elements)
			if (E instanceof Periodic &&
					((Periodic)E).getDate().getYear() == _year &&
					((Periodic)E).getDate().getMonth() == _month)
				periodics.add((Periodic)E);

		return periodics;
	}

	static void showElements(ArrayList<? extends Element> _elements)
	{
		for (Element E : _elements)
			System.out.println(E.getInfo());
	}
}
