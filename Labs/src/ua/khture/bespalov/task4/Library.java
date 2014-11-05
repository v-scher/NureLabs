package ua.khture.bespalov.task4;

import java.util.ArrayList;
import java.util.Date;

public class Library {
	ArrayList<Element> m_elements = new ArrayList<>();
	
	public static void main(String args[])
	{
		Library lib = new Library();
		lib.addElement(new Book("����� 1", "���", 1993, "R.M. Allen"));
		lib.addElement(new Book("ʳ���� ������", "���", 1993, "R.M. Allen"));
		lib.addElement(new Book("������� �����", "���", 1995, "R.M. Allen"));
		lib.addElement(new Methodics("�� � ���", "������", "�����"));
		lib.addElement(new Magazine("Maxim", "������", new Date(14, 4, 20)));
		lib.addElement(new Periodic("����������", "������", new Date(14, 4, 23), 3));
		lib.addElement(new Periodic("����������", "������", new Date(14, 5, 1), 4));
		lib.addElement(new Periodic("����������", "������", new Date(14, 5, 8), 5));
		lib.addElement(new Periodic("����������", "������", new Date(14, 5, 15), 6));
		lib.addElement(new Periodic("����������", "������", new Date(14, 5, 22), 7));
		lib.addElement(new Book("��������� �����", "�������", 2000, "�� ���� �������"));

		System.out.println("\t������ ���� ��������:");
		showElements(lib.m_elements);
		
		System.out.println();
		System.out.println("\t������ �� 1993 ��, ������ \"R.M. Allen\":");
		showElements(lib.getBooksByAuthorAndYear("R.M. Allen", 1993));
		
		System.out.println();
		System.out.println("\t������� �� 4 ����� 2014-�� ����: ");
		showElements(lib.getMagazinesByMonthAndYear(4, 14));
		
		System.out.println();
		System.out.println("\t���������� ������ �� 4 �����: " + lib.getPeriodicsByMonthAndYear(4, 14).size());
		
		System.out.println();
		System.out.println("\t���������� ������ �� 5 �����: " + lib.getPeriodicsByMonthAndYear(5, 14).size());
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
