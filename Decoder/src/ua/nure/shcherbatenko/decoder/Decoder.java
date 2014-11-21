package ua.nure.shcherbatenko.decoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Decoder {

	private static final String PATH_TO_MODELS = "lang";
	private static final String PREFIX = "lang_model_";
	private static final String PRIORITIES = "UTF-8_UTF-16_windows-1251";
	private static final double TRESHOLD = 0.01;

	private static Map<String, Map<Integer, Double>> languages = new TreeMap<>();
	
	static {
		loadLangModels();
	}
	
	static class CharsetLanguageID {
		
		public final String charsetName;
		public final String langName;
		public final double rate;
		public final String text;
		
		public CharsetLanguageID(String charsetName, String langName, String text, double rate) {
			this.charsetName = charsetName;
			this.langName = langName;
			this.text = text;
			this.rate = rate;
		}
		
		@Override
		public String toString() {
			return new StringBuilder(charsetName)
				.append(' ')
				.append(langName)
				.toString();
		}
		
	}
	
	public static String decode(byte[] data) {
		CharsetLanguageID id = detectCharsetAndLanguage(data);
		return id.text;
	}
	
	public static CharsetLanguageID detectCharsetAndLanguage(byte[] data) {
		CharsetLanguageID minId = new CharsetLanguageID("", "", "", Double.MAX_VALUE);
		
		for (Entry<String, Charset> charsetEntry : Charset.availableCharsets().entrySet()) {
			String text = new String(data, charsetEntry.getValue());
			Map<Integer, Double> currentModel = createModel(text);
			for (Entry<String, Map<Integer, Double>> langEntry : languages.entrySet()) {
				double rate = 0;
				Map<Integer, Double> currentCopy = new TreeMap<Integer, Double>(currentModel);
				for (Entry<Integer, Double> charEntry : langEntry.getValue().entrySet()) {
					Double cur = currentModel.get(charEntry.getKey());
					double charFreq = 0.0;
					if (cur != null) {
						charFreq = cur;
						currentCopy.remove(charEntry.getKey());
					}
					
					Double checking = charEntry.getValue();
					if (checking != null) {
						charFreq -= checking;
					}
					
					rate += (charFreq > 0.0) ? charFreq : (-1.0 * charFreq);
				}
				for (Entry<Integer, Double> charEntry : currentCopy.entrySet()) {
					double charFreq = charEntry.getValue();
					rate += charFreq;
				}
				String charsetName = charsetEntry.getKey();
				if (PRIORITIES.contains(charsetName)) {
					rate -= TRESHOLD * rate;
				}
				minId = (rate < minId.rate)
						? new CharsetLanguageID(charsetName , langEntry.getKey(), text, rate)
						: minId;
			}
		}
		
		return minId;
	}

	private static Map<Integer, Double> createModel(String text) {
		TreeMap<Integer, Double> langModel = new TreeMap<>();
		updateModel(langModel, text);
		return langModel;
	}
	
	public static void updateModel(Map<Integer, Double> model, String text) {
		int symbols = 0;
		
		for (int i = 0; i < text.length(); i++) {
			symbols++;
			Integer c = Character.codePointAt(text, i);
			Double old = model.get(c);
			model.put(c, (old == null) ? 1 : (old + 1));
		}
		
		for (Entry<Integer, Double> entry : model.entrySet()) {
			entry.setValue(entry.getValue() / symbols);
		}
	}
	
	private static Map<Integer, Double> parseModel(File file) {
		Map<Integer, Double> model = new TreeMap<Integer, Double>();

		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				Integer code = sc.nextInt();
				Double freq = sc.nextDouble();
				model.put(code, freq);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return model;
	}

	private static void loadLangModels() {
		File modelFileDir = new File(PATH_TO_MODELS);
		if (!modelFileDir.isDirectory()) {
			throw new RuntimeException("Language models not found!");
		}
		File[] files = modelFileDir.listFiles();
		for (File file : files) {
			String modelName = file.getName().substring(PREFIX.length(), PREFIX.length() + 3);
			languages.put(modelName, parseModel(file));
		}
	}
	
}
