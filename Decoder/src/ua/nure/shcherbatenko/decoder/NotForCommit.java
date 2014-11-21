package ua.nure.shcherbatenko.decoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class NotForCommit {
	static final String MODEL_LINE_SEPARATOR = "\r\n";
	static final String PATH_TO_MODELS = "lang";
	static final String PREFIX = "lang_model_";
	static final String POSTFIX = ".txt";
	
	public static String getInput(File file, String encoding) {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(file, encoding)) {
	        while (scanner.hasNextLine()) {
	            sb.append(scanner.nextLine()).append(System.lineSeparator());
	        }
        } catch (FileNotFoundException e) {
			System.out.println(e);
		}
        return sb.toString().trim();
	}

	static void createModelFilesFromTexts() {
		File textFiles = new File("feed");
		if (textFiles.isDirectory())
			System.out.println("0 Directory found!");
		File[] files = textFiles.listFiles();
		Map<String, Map<Integer, Double>> models = new TreeMap<>();
		
		for (File file : files) {
			System.out.println("1 file: " + file);
			String text = getInput(file, "Cp1251");
			
			String modelName = file.getName().substring(0, 3);
			Map<Integer, Double> model = models.get(modelName);
			if (model == null) {
				model = new TreeMap<>();
				models.put(modelName, model);
				System.out.println("1.1 new model: " + modelName);
			}
			Decoder.updateModel(model, text);
		}
		
		for (Entry<String, Map<Integer, Double>> entry : models.entrySet()) {
			System.out.println("2 model written: " + entry.getKey());
			writeModel(entry.getValue(), entry.getKey());
		}
	}
	
	public static void writeModel(Map<Integer, Double> model, String modelName) {
		String fullPath = PATH_TO_MODELS + 
				System.getProperties().getProperty("file.separator") + 
				PREFIX + 
				modelName + 
				POSTFIX;
		try (FileWriter out = new FileWriter(fullPath)) {
			for (Entry<Integer, Double> entry : model.entrySet()) {
				out.write(Integer.toString(entry.getKey()));
				out.write(' ');
				out.write(Double.toString(entry.getValue()));
				out.write(MODEL_LINE_SEPARATOR);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void printModel(Map<Integer, Double> currentModel) {
		for (Entry<Integer, Double> entry : currentModel.entrySet()) {
			char c = (char)(int)entry.getKey();
			if (c == '\n' || c == '\r')
				c = '_';
			System.out.print(c);
		}
		System.out.println();
	}
	
	public static void corruptFile(String fileName, String charset) {
		StringBuilder sb = new StringBuilder();
	    try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
	    	String tmp = input.readLine();
	    	if (tmp != null)
	    		sb.append(tmp);
	    	while ((tmp = input.readLine()) != null) {
	    		sb.append(tmp);
	    	};
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    try (FileOutputStream out = new FileOutputStream(new File(fileName))) {
	    	out.write(sb.toString().getBytes(Charset.forName(charset)));
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
