package ua.khpi.kolesnyksfriend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CorseWork {
	public static void main(String[] args) throws IOException {
		// ��������� ���� � ���������� ������� (��'��� dir)
		File dir = new File("C:\\Users\\���������\\Downloads");
		// �������� � ���� �� ����� ...
		File[] files = dir.listFiles(
			// �� ��������� ������� ����� (�������� ����) 
			new FileFilter() {
				// ������� � ������� accept() �� ���� ������� �������� ����� � ����
				@Override
				public boolean accept(File file) { 
					// ��� ����� ������� TRUE, ���� ��'� ����� ���������� �� ".txt"
					return file.getName().endsWith(".txt");
				}
		});
		
		// ��� ������� ������ � ������ �������� ���� � ������� ���� console
		// � ���� ��������� ����� ������ �����, �������� � ������� � ���������
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		// ��������� ����������� ������ ����� � �������
		System.out.println("Enter words to search divided by spaces:");
		// ������� � ������ ����� ������ � ��������� �������� 
		String terms = console.readLine();
		//������� split() ������ ��������� (�����) � ������� �������� ����� � �������� �� ����� �����
		String[] words = terms.split(" ");
		
		// HashMap ������ ���� ����-��������. � ����� ������ ������ �����,
		// � ������� ����� (�����) ������� �������� ArrayList<String>, ��� ������
		// ������ � ������ ������, �� ��������� � ����.
		// �������� �� � � ��������� ��������� (������� - ����� ������) - ������� ������������
		HashMap<File, ArrayList<String>> matches = new HashMap<>();

		// �������� � ���� �� ������� ����� � ������� ������������ � ��������� �������
		// ��� ���� �� ������ ������ ������
		for (File file : files)
			matches.put(file, new ArrayList<String>());
		
		// ��������� � ���� ����� �� ����� (files) � ��� ������� ����� (txtFile)
		for (File txtFile : files){
			// ��������� ���� reader ��� ���������� ����� 
			BufferedReader reader = new BufferedReader(new FileReader(txtFile));
			// ��������� ����� � ���'�� � ���� �������� ���������� � �����
			char[] buffer = new char[10240];
			// �������� ������� ����� �� ��������� ������ � �������� ���� ���� � �����
			reader.read(buffer);
			// ��������� � ������ ��������� ����� (textFromFile)
			String textFromFile = new String(buffer);
			
			// ��������� � ���� �� ������ (����� words) � ��� ������� � ��� (term)
			for (String term : words){
				// ��� �������� ������� ������ (term) ��������� ����� ������ ��� ������� �����
				// ��������� ��'��� Scanner, ���� �������� ��������� ����� (textFromFile) �� �����
				// (�� ��������)
				Scanner sc = new Scanner(textFromFile);
				// tmp - ��������� ����� ��� ��������� ������������� �����
				String tmp;
				// ���� � ������ ������ (textFromFile, ���� ��������� � ������ sc)
				// �� � �������������� �����
				while (sc.hasNext()){
					// ��� ������� �������� ����� � �������� � ��������� ����� (tmp)
					tmp = sc.next();
					// ���� � ��� (����� == ����������� �����)
					if (tmp.equals(term)){
						// �� �������� � ����� ���� �����
						// ��� ����� ������� �������� (��������� �����) �����
						ArrayList<String> termsFounded = matches.get(txtFile);
						// � �������� ���� �����, ���� ������
						termsFounded.add(term);
						// ���������� ���������� (���������� ���� while)
						break;
					}
				}
				sc.close();
			}
			// ��������� ����
			reader.close();
		}

		// ��������� "�����" �������
		System.out.print("\t\t\t");
		// �������� �� ����� � ����������
		for (String term : words)
			System.out.print(term + "\t\t");
		System.out.println();

		// ��� ������� ����� (currentFile)
		for (File currentFile : files){
			// �������� � ��� ������� (������ ����� � ������� �����) ����� ����� + �����������
			System.out.print(currentFile.getName() + "\t\t");
			// ��������� � ���� �� ������ (words)
			for (String term : words){
				// ���������� ����� ���� (termins)
				ArrayList<String> termins = matches.get(currentFile);
				// � ���� ��� � - �������� ��������
				if (termins.contains(term))
					System.out.print("1\t\t");
				else
					// ������ - �����
					System.out.print("0\t\t");
						
			}
			System.out.println();
		}
		
		// ��������� ���� �������� � ������
		console.close();
	}
}