package sound;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;


public class SoundCapturer extends JFrame {
	boolean stopCapture = false;
	ByteArrayOutputStream byteArrayOutputStream;
	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	
	public SoundCapturer() {
		final JButton captureBtn = new JButton("Capture");
		final JButton stopBtn = new JButton("Stop");
		final JButton playBtn = new JButton("Playback");
		
		captureBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		playBtn.setEnabled(false);
		
		//Register anonymous listeners
		captureBtn.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				captureBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				playBtn.setEnabled(false);
				//Capture input data from the
				// microphone until the Stop button is
				// clicked.
				captureAudio();
			}//end actionPerformed
		}//end ActionListener
		);//end addActionListener()
		add(captureBtn);
		
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				captureBtn.setEnabled(true);
				stopBtn.setEnabled(false);
				playBtn.setEnabled(true);
				//Terminate the capturing of input data
				// from the microphone.
				stopCapture = true;
			}//end actionPerformed
		});//end addActionListener()
		add(stopBtn);
		
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play back all of the data that was
				// saved during capture.
				playAudio();
			}//end actionPerformed
		});//end addActionListener()
		add(playBtn);
		
		setLayout(new FlowLayout());
		setTitle("Capture/Playback Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,150);
		setLocation(500, 500);
		setVisible(true);
	}

	private void captureAudio() {
		try{
			Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
			System.out.println("Available mixers:");
			for(int cnt = 0; cnt < mixerInfo.length; cnt++) {
				System.out.println(mixerInfo[cnt].getName());
			}
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo =
                    new DataLine.Info(
                    TargetDataLine.class,
                    audioFormat);
			// -------------------------------------------------- ЗМІНИ МІКСЕР!
			Mixer mixer = AudioSystem.getMixer(mixerInfo[2]);
			targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);

		    targetDataLine.open(audioFormat);
		    targetDataLine.start();

		    Thread captureThread = new CaptureThread();
		    captureThread.start();
		} catch (LineUnavailableException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	//This method plays back the audio data that
	  // has been saved in the ByteArrayOutputStream
	private void playAudio() {
		try{
			//Get everything set up for playback.
			//Get the previously-saved data into a byte
			// array object.
			byte audioData[] = byteArrayOutputStream.toByteArray();
	        //Get an input stream on the byte array
			// containing the data
			InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
			AudioFormat audioFormat = getAudioFormat();
			audioInputStream = new AudioInputStream(
	                    byteArrayInputStream,
	                    audioFormat,
	                    audioData.length/audioFormat.
	                                 getFrameSize());
			
			DataLine.Info dataLineInfo = 
	                            new DataLine.Info(
	                            SourceDataLine.class,
	                            audioFormat);
			sourceDataLine = (SourceDataLine)
	               AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			//Create a thread to play back the data and
			// start it  running.  It will run until
			// all the data has been played back.
			Thread playThread = new PlayThread();
			playThread.start();
	    } catch (Exception e) {
	    	System.out.println(e);
	    	System.exit(0);
	    }
	}
	
	class PlayThread extends Thread{
		byte tempBuffer[] = new byte[10000];

		public void run(){
			try{
				int cnt;
		        //Keep looping until the input read method
		        // returns -1 for empty stream.
		        while((cnt = audioInputStream.read(
		      	              tempBuffer, 0,
		                      tempBuffer.length)) != -1){
		        	if(cnt > 0){
		        		//Write data to the internal buffer of
		        		// the data line where it will be
		        		// delivered to the speaker.
		        		sourceDataLine.write(tempBuffer,0,cnt);
		        	}//end if
		        }//end while
		        //Block and wait for internal buffer of the
		        // data line to empty.
		        sourceDataLine.drain();
		        sourceDataLine.close();
		    }catch (Exception e) {
		    	System.out.println(e);
		    	System.exit(0);
		    }//end catch
		}//end run
	}//end inner class PlayThread
	
	class CaptureThread extends Thread{
		byte tempBuffer[] = new byte[10000];
		public void run(){
			byteArrayOutputStream = new ByteArrayOutputStream();
			stopCapture = false;
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
			}
			targetDataLine.stop();
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				System.out.println("Не можу закрити потік в байтовий масив");
				e.printStackTrace();
			}
		}
	}
			
	private AudioFormat getAudioFormat(){
		float sampleRate = 44100.0F;
	    int sampleSizeInBits = 16;
	    int channels = 1;
	    boolean signed = true;
	    boolean bigEndian = false;

	    return new AudioFormat(sampleRate,
	                           sampleSizeInBits,
	                           channels,
	                           signed,
	                           bigEndian);
	}
	      
	public static void main(String[] args) {
		new SoundCapturer();
	}

}
