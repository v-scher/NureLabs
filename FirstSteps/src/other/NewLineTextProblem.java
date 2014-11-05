package other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewLineTextProblem {

	public static void read(String  path, int symbols) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		StringBuffer text = new StringBuffer();
		
		String temp = in.readLine();
		int i = 0;
		while ( ! Character.isLetterOrDigit(temp.charAt(i))) {
			i++;
		}
		temp = temp.substring(i);
		
		while(temp != null) {
			text.append(temp);
			text.append("\r\n");
			temp = in.readLine();
		}
		in.close();
		
		FileWriter file = new FileWriter(path);
		file.write(text.toString());
		file.close();
	}
	
	public static void main(String[] args) throws IOException {
		read("H:\\Documents\\1 ���\\1 ��� ��������.txt", 400);
		read("H:\\Documents\\1 ���\\1 ��� ��������.txt", 400);
		read("H:\\Documents\\1 ����ϲ\\1 ����ϲ ��������.txt", 400);
		read("H:\\Documents\\1 �г��\\1 �г�� ��������.txt", 400);
		read("H:\\Documents\\1 Internet\\1 Internet ��������.txt", 400);
	}

}
