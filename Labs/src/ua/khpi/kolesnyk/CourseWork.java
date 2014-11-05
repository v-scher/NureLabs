package ua.khpi.kolesnyk;

import java.awt.FontMetrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseWork {
    static TextWindow wnd = new TextWindow();// ������� ���� ����������
    
	static String vowels = "������������������ި";
	static String consonant = "������������������������������������������";
	static String signs = "��";
	static char y = '�';
	static String prefix = "���";
	
	private static boolean isVowel(char letter){//��������� �������� ��  ����� �������
		return vowels.contains("" + letter);// ��������� �������� �� ������ ��� �����
	}
	
	private static boolean isConsonant(char letter){//��������� �������� �� ���������
		return consonant.contains("" + letter);
	}
	
	private static boolean isSign(char letter){
		return signs.contains("" + letter);
	}
	
	//������ ������ �� �����( ������� ������� ������� � ������)
	private static String[] getSyllsFromWord(String word) {
		// ----------------- ������ ��˲���� �ʲ���� �������� � ���² (==������)
		int syllCounter = 0;// ������� ��� ������
		for(int i = 0; i < word.length(); i++){// ������ �� �����
			char tmpChar = word.charAt(i);// ������� ����� ������� �� ����� ���������
			if(isVowel(tmpChar))
				syllCounter++;
		}
		
		// ---------------- ���� ����� ������ί, �� ��������� ������ ����� - ��� ����� 
		if (syllCounter == 0){ // ���� ��� �������
			String[] res = new String[1];// ������ ��� ������ �������
			res[0] = word;//���� ��� ������� �� �� �����
			return res;//������� �����
		}
		
		String[] sylls = new String[syllCounter];// ������ ��� �������� ������

		syllCounter = 0;
		
		// ----------------- ����² �̲�Ͳ ��� ������������� ������� �������� ����� 
		boolean firstSyll = true;
		boolean lastSyll = false;
		boolean vowelIsAlreadyThere = false;// ������� ��� �� ������

		// ----------------- �������� � ʲ������ ����� �����Ͳ ����� (��� �������)
		for( int i = 0; i < sylls.length; i++)
			sylls[i] = "";
		
		// ----------------- ����Ӫ�� ��� ����� �� ˲�����
		for(int i = 0; i < word.length(); i++){// ��������� ����� � �����
			char letter = word.charAt(i);
			
			try{
				// ------------- �����Ͳ ����������:
				if( isVowel(letter) ){// ���� ������� ����� ������� 
					if( vowelIsAlreadyThere){// � � ������ ����� ������� ��� ����
						// ----- ������� ������ ������, ���� � ��������� ��� � �������
						syllCounter++;// ������� ���� ������
						firstSyll = false;// � ������ ���� �������� � ������ �������
						vowelIsAlreadyThere = true;//  ������� ��� � ����� ����� ���� �������
						sylls[syllCounter] += letter;//�������� ��� ������� � ���������(� ��� � �������) �����
					} else { // ���� ������� � ����� ��� ���
						// ----- ����� ����ί ������ί � ����Ĳ
						// ----- ��� ��������� �� ����� "��" ������� ������� (��� ������� � ���)
						sylls[syllCounter] += letter;// ���������� ������� ������� � ����
						vowelIsAlreadyThere = true;// � ������� ���-���� ������������
					}
				} else if (isConsonant(letter)) {// ���� ���������
					// ------------- ��������Ͳ ����������:
					if( vowelIsAlreadyThere ){// ���� ��� ���� �������
						if( i+1 < word.length() ){// ���������� �� ������� ���������� �������� �����
							//�� ��������� ��������� � ���� ��� ��������� ���� ���� 
							if( isConsonant(word.charAt(i + 1)) || isSign(word.charAt(i + 1)) ){
								// ------ ������ ̲� ��������, ���� 2 ��������Ͳ �����
								// ------ � � ������� � ��� ��� � �������
								sylls[syllCounter] += letter;// ��������� ������� � � �������� �����
								
								// ------ ���� "����� �����" �� ���� � ������ �� �� ����������
								// ------ �� ���������� �� ����������
								if( ! isSign(word.charAt(i + 1)) && ! lastSyll)// ���� ��������� ����� �� ���� � ��� �� ��������� ���� �� ��������� � ���������� �����
									syllCounter++;
								
								firstSyll = false;// ��������� ��� ��� �� ����� ����
								vowelIsAlreadyThere = false;// �� ��� ��� ��� �������
							} else {
								// ------ ʲ���� ������ (������� � ����� ��� �, ����� ����� - ��������)
								if( firstSyll 
								&& (sylls[syllCounter] + letter).equals(prefix) ){
									// ------ �������� �� ������� - ��� �� ��������
									sylls[syllCounter] += letter;
									if ( !lastSyll) syllCounter++;
									firstSyll = false;
									vowelIsAlreadyThere = false;
								} else {
									if ( !lastSyll) syllCounter++;
									firstSyll = false;
									vowelIsAlreadyThere = false;
									sylls[syllCounter] += letter;
								}
							}
						} else {
							// ---- � �Ѳ� ����� �������� ������ �����Ӫ�� ˲���� � ������
							sylls[syllCounter] += letter;
						}
					} else {
						sylls[syllCounter] += letter;
					}
				} else if( isSign(letter) ){
					// ���� ������� ˲���� ���� - ������� � ����� (�� �������) � ���������� �� ����������
					sylls[syllCounter] += letter;
					
					if ( !lastSyll) syllCounter++;
					
					firstSyll = false;
					vowelIsAlreadyThere = false;
				}
				
				if( syllCounter == sylls.length-1 )
					lastSyll = true;

			}catch(ArrayIndexOutOfBoundsException e){
				sylls[sylls.length-1] += letter;
			}
		}
		
		return sylls;
	}
	// ��������� ����� ����� �� ������!���������� ������ �������
	private static String[] prepareWord(String word){
		if ( word.length() < 4){// ���� ���� ������ ���� ���� �� �� � ���� �������
			String[] res = new String[1];// ������ ��� ������ ������
			res[0] = word;// ������ ���� ������������ ���� ����� �������
			return res;// � ������� ���
		}
		
		String[] sylls = getSyllsFromWord(word);// ��������� �� �����!
		if ( sylls.length == 1){// ���� ���� ������ ���� ���� �� �� � ���� �������
			String[] res = new String[1];// ������ ��� ������ ������
			res[0] = word;// ������ ���� ������������ ���� ����� �������
			return res;// � ������� ���
		}
			// ������� ������� � ������� ����� � ������ ���� ������ ���� ��� ���� �����
		ArrayList<String> domens = new ArrayList<>();
		
		int start = 0;//������� ����� ������� ������
		// ����  ������ ���� �� ����� ����� �
		if (sylls[0].length() == 1 &&
			sylls.length > 1){// � ���� ������ ������ ������
			domens.add(sylls[0] + sylls[1] + '-');// ���� ������ ���� � ����� ����� �� ������������ ��� � ����������
			//����� ��� �����
			
			start += 2;//���� ����� ��� ������ ����� �� �������� ���� � ��������(��� ����� 2)
		} else {
			domens.add(sylls[0] + '-');// ���� ����� ���� �� � ����� ����� �� ������ ��� ��������� �������
			start += 1;// ������� ����� �������� �� ������� �����
		}
		//
		String lastDomen;
		// ��������� ����� ������������� ��������� ����
		int end = sylls.length - 1;
		//���� ��������� ���� � ����� �����
		if (sylls[sylls.length-1].length() == 1){
			// �� ��������� ����� ����������� ����� �������
			lastDomen = sylls[sylls.length-2] + sylls[sylls.length-1];
			//�� ����� ������������� ����� ������ �� ������ ������� � ����� �����
			end -= 2;
		} else {
			lastDomen = sylls[sylls.length-1];// ���� ����� �� ��������� �� ��������� ������� ����� ��������� ����
			end -= 1;
		}
		//������������ ��� ����� ����� ������ � ��������� ��������
		for (int i = start; i <= end; i++)
			domens.add(sylls[i] + '-');// ���������� � ��������� �����
		
		domens.add(lastDomen);
		
		return domens.toArray(new String[0]);// ���������� ��������� ������� � ������
	}
	// �������� ��� ����� � ���������� ������ ������� ���������� �������
	public static ArrayList<String> prepareText(String text){
		//����� ��� ���������� �����(�� ������ �����)
		int firstLetter = 0;
		//� ������ �������� �������� �����,����������� ������� �����
		while (!Character.isLetter(text.charAt(firstLetter)) ) 
			firstLetter++;
		
        // �������� ����� ���� ������� ���� �� �������
		text = text.substring(firstLetter);
		
		ArrayList<String> domens = new ArrayList<>();
		//����� ����� � ������� ����������� ������
		Scanner sc = new Scanner(text);
      	//��������� �������� ����� ������( �� ��� ����� ���������) ��������� �� ��
		while (sc.hasNext()){
		//
			String word = sc.next();
			//
			String newWord = "";
			for (int i = 0; i < word.length(); i++)
			//���� ����� ����� ������ ������ �������(�������)
				if (Character.isLetter(word.charAt(i)))//�������� �� ������,������ ������� ������� �������
				//� ���������� � ����� ����� ������ ������ �������(�����)
					newWord += word.charAt(i);
			// ��������� ����� � ������ ������
			String[]  sylls = prepareWord(newWord);
			for (String syll : sylls){
				//��������� ����� � ������ � �������� �������
				domens.add(syll);
			}
			domens.add(" ");// � ��������� ������ ����� ������� �����
		}
		sc.close();
		
		return domens;
	}
    //�������. ��������� � ��������� ���� �� ��������(������ � ����� ���� ��� �������� � ������)�� ������ ����
	private static void setTextIntoTheWindow(String text) {
		//��������� � ���������� ��������(���������� �����,����� � ��������,������� ��� ��������� ���� ��� ������)
		if (text.equals("")) return;
		ArrayList<String> domens = prepareText(text);
		//���������� � ������������� ��� ������(��������� � ����)
		FontMetrics fm = wnd.getFM();
		String resultText = "";
		String line = "";//������ ������� �� ������
		//�������� �� ����� �������
		for (int i = 0; i < domens.size(); i++){
			//��������� ���� ������ �������� ����� � ������� ������� �� �������� �� ������ ������ �����
			if (fm.stringWidth(line + domens.get(i)) > wnd.textMaxWidth()){
				line += "\n";//����������� ������
				// � �������������� ����� ��������� ����������� ������
				resultText += line;
				//�������� ��������� ����� ������
				line = domens.get(i);
			} else {// ���� �� ������� �� ����� ������ �� ��������� � ���������� ��� ��� ������
				line += domens.get(i);
			}
		}
		resultText += line;
		
		wnd.setText(resultText);
	}
	
	public static void main(String args[]) throws IOException{
		// ��������� ���� � ����
	    File file = new File("C:\\Users\\���������\\Downloads\\�������� ����.txt");
	    //����� ��� ������� �����
		BufferedReader reader = new BufferedReader(new FileReader(file));
		//���� ����� �� ������� ����������� � ���
		String text = "";
		String tmp;//� ��� ������ � �������(������ ���� �����)
		//
		while ( (tmp = reader.readLine()) != null)//�� ����� �����
			//� ����� ��������� ��������� � ������� ������
			text += tmp + '\n';
		reader.close();//��������� �����
		//���������� � ������
		setTextIntoTheWindow(text);
		//���� � �������
		System.out.println("����� �����! (��� \"�����\")");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		//�� ��� �������� � ������� 
		String command;
		//���� ��� �� ����� "�����" ���������� ������� �����
		while ( ! (command = console.readLine()).equalsIgnoreCase("�����"))
			setTextIntoTheWindow(command);
		
		System.exit(0);//����� � ��������� ���� �������
	}
}