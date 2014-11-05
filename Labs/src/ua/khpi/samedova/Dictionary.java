package ua.khpi.samedova;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {
	TreeMap<String, Article> words = new TreeMap<>();
	
	public void addDefinition(String word, String definition){
		if( ! words.containsKey(word) ){
			words.put(word, new Article(definition));
		}else{
			words.get(word).definition = definition;
		}
	}
	
	public Article get(String keyWord){
		return words.get(keyWord);
	}
	
	public Set<String> keySet(){
		return words.keySet();
	}
	
	private void addSynonym(String word1, String word2){
		if( words.containsKey(word1) ){
			words.get(word1).add(word2);
		} else {
			Article article = new Article();
			article.add(word2);
			words.put(word1, article);
		}
		
		if( words.containsKey(word2) ){
			words.get(word2).add(word1);
		} else {
			Article article = new Article();
			article.add(word1);
			words.put(word2, article);
		}
	}
	
	public void removeWord(String word){
		if( ! words.containsKey(word)) return;
		
		ArrayList<String> synonyms = words.remove(word).synonyms;
		
		// removing THIS WORD from all synonym articles
		for( String S : synonyms)
			words.get(S).removeWord(word);
	}
	
	public void removeSynonym(String word1, String word2){
		if( ! words.containsKey(word1)
		|| ! words.containsKey(word2)) return;
		
		words.get(word1).removeWord(word2);
		words.get(word2).removeWord(word1);
	}

	public void addAllSynonyms(String word, String ... synonyms ){
		for(String S : synonyms)
			addSynonym(word, S);
	}
	
	public static void main(String args[]){
		Dictionary d = new Dictionary();
		d.addDefinition("лелека", "Птаха, яка приносить дітей.");
		d.addDefinition("гуртом", "Коли багато людей задіяні одночасно в одній справі");
		
		d.addAllSynonyms("лелека", "бусол", "чорногуз", "бузько", "буслиха");
		d.addAllSynonyms("лестити", "леститися", "благоговіти", "вислужуватися", "гнутися", "колінкуватися");
		d.addAllSynonyms("одностайно", "гуртом", "дружно", "злагоджено", "одноголосно", "однодушно", "разом");
		
		for( String W : d.keySet())
			System.out.println(W + "\n" + d.get(W) + "\n");
		
		d.removeSynonym("чорногуз", "лелека");
		
		for( String W : d.keySet())
			System.out.println(W + "\n" + d.get(W) + "\n");
	}
}

class Article{
	ArrayList<String> synonyms = new ArrayList<>();
	String definition;
	
	public Article(){	}
	
	public Article(String definition){
		this.definition = definition;
	}
	
	public void add(String word){
		if( ! synonyms.contains(word))
			synonyms.add(word);
	}
	
	public void removeWord(String word){
		synonyms.remove(word);
	}
	
	public String toString(){
		StringBuffer toShow = new StringBuffer();
		if( definition != null)
			toShow.append("Значення: " + definition);
		
		if( synonyms.size() > 0){
			if(definition != null)
				toShow.append("\n");
			
			toShow.append("Синоніми: ");
			toShow.append(synonyms.get(0));
			for(int i = 1; i < synonyms.size(); i++){
				toShow.append(", " + synonyms.get(i));
			}
			toShow.append('.');
		}
		return toShow.toString();
	}
}