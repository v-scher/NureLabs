package ua.nure.shcherbatenko.decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Util {

	public static byte[] getInput(String fileName) {
		ArrayList<byte[]> buffers = new ArrayList<>();
		int totalBytes = 0;
		byte[] tmp = new byte[1024];
		
        try (FileInputStream input = new FileInputStream(new File(fileName))) {
        	int read = 0;
        	while ((read = input.read(tmp)) > 0) {
        		byte[] buffer = new byte[read];
        		System.arraycopy(tmp, 0, buffer, 0, read);
        		buffers.add(buffer);
        		totalBytes += read;
        	}
        } catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
        
        byte[] data = new byte[totalBytes];
        
        int written = 0;
        for (byte[] b : buffers) {
        	System.arraycopy(b, 0, data, written, b.length);
        	written += b.length;
        }
        
        return data;
	}

}
