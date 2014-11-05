package sound;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Dictaphone extends JFrame {
	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	final JButton captureBtn = new JButton("Capture");
	final JButton stopBtn = new JButton("Stop");
	
	final JPanel btnPanel = new JPanel();
	final ButtonGroup btnGroup = new ButtonGroup();
	final JRadioButton aifcBtn = new JRadioButton("AIFC");
	final JRadioButton aiffBtn = new JRadioButton("AIFF");
	final JRadioButton auBtn = new JRadioButton("AU",true);
	final JRadioButton sndBtn = new JRadioButton("SND");
	final JRadioButton waveBtn = new JRadioButton("WAVE");
	
	public Dictaphone() {
		captureBtn.setEnabled(true);
	    stopBtn.setEnabled(false);
	    captureBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				captureBtn.setEnabled(false);
		        stopBtn.setEnabled(true);
		        
		        captureAudio();
			}
		});
	    
	    stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				captureBtn.setEnabled(true);
		        stopBtn.setEnabled(false);
		        
		        targetDataLine.stop();
		        targetDataLine.close();  
			}
		});
	    
	    add(captureBtn);
	    add(stopBtn);
	    
	    btnGroup.add(aifcBtn);
	    btnGroup.add(aiffBtn);
	    btnGroup.add(auBtn);
	    btnGroup.add(sndBtn);
	    btnGroup.add(waveBtn);
	    
	    btnPanel.add(aifcBtn);
	    btnPanel.add(aiffBtn);
	    btnPanel.add(auBtn);
	    btnPanel.add(sndBtn);
	    btnPanel.add(waveBtn);
	    
	    add(btnPanel);
	    setLayout(new FlowLayout());
	    setTitle("Copyright 2003, R.G.Baldwin");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(300,120);
	    setVisible(true);
	}
	
	private void captureAudio(){
	    try {
	    	audioFormat = getAudioFormat();
	    	DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
	    	targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
	    	new CaptureThread().start();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        System.exit(0);
	    }
	}
	
	private AudioFormat getAudioFormat(){
	    float sampleRate = 44100.0F;
	    //8000,11025,16000,22050,44100
	    int sampleSizeInBits = 16;
	    //8,16
	    int channels = 1;
	    //1,2
	    boolean signed = true;
	    //true,false
	    boolean bigEndian = false;
	    //true,false
	    return new AudioFormat(sampleRate,
	                           sampleSizeInBits,
	                           channels,
	                           signed,
	                           bigEndian);
	}
	
	class CaptureThread extends Thread{
		public void run(){
			AudioFileFormat.Type fileType = null;
			File audioFile = null;
			
			if( aifcBtn.isSelected() ) {
				fileType = AudioFileFormat.Type.AIFC;
				audioFile = new File("junk.aifc");
			}else if( aiffBtn.isSelected() ) {
				fileType = AudioFileFormat.Type.AIFF;
				audioFile = new File("junk.aif");
			}else if( auBtn.isSelected() ) {
			    fileType = AudioFileFormat.Type.AU;
			    audioFile = new File("junk.au");
			}else if( sndBtn.isSelected() ) {
			    fileType = AudioFileFormat.Type.SND;
			    audioFile = new File("junk.snd");
			}else if( waveBtn.isSelected() ) {
			    fileType = AudioFileFormat.Type.WAVE;
			    audioFile = new File("junk.wav");
			}
			
			try{
				targetDataLine.open(audioFormat);
				targetDataLine.start();
				//-----------------------
				AudioSystem.write(
			            new AudioInputStream(targetDataLine),
			            fileType,
			            audioFile);
				// TODO: Для буферізації можна використовувати цикл та побайтовий запис!
				/*
				while(!stopCapture){
					//Read data from the internal buffer of
					// the data line.
					int cnt = targetDataLine.read(tempBuffer,
												0,
												tempBuffer.length);

					if(cnt > 0){
						//Save data in output stream object.
					    byteArrayOutputStream.write(tempBuffer,
					                                0,
					                                cnt);
					}
				}*/
				
				/*
				 In addition to its other features, the AudioSystem.write method knows how to detect 
				 that the stop method has been invoked on the TargetDataLine object (see Listing 7) 
				 and to close the output file when that happens.  Therefore, it was not necessary for me 
				 to monitor for a signal to stop data capture and close the output file.
				 */
				//---------------------------------
			} catch (IOException e ) {
				System.out.println("Помилка 2");
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				System.out.println("Помилка 3");
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Dictaphone();
		/*
		TargetDataLine line;
		AudioFormat format =  new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 4, 44100, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error ... 
			System.out.println("Все погано!");
		} else {
			try {
			    line = (TargetDataLine) AudioSystem.getLine(info);
			    line.open(format);
			} catch (LineUnavailableException ex) {
			    // Handle the error ... 
			}
		}*/
	}
}
