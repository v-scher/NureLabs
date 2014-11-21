package ua.nure.shcherbatenko.decoder;

import ua.nure.shcherbatenko.decoder.Decoder.CharsetLanguageID;



public class Main {
	
	public static void mainq(String[] args) {
		byte[] data = Util.getInput(args[0]);
		System.out.println(args[0]);
		CharsetLanguageID id = Decoder.detectCharsetAndLanguage(data);
		System.out.println(id);
		System.out.println(id.text);
		System.out.println();
	}

}
