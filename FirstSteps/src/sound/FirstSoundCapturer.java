package sound;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class FirstSoundCapturer extends JFrame {

	boolean stopCapture = false;
	ByteArrayOutputStream byteArrayOutputStream;
	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	
	JButton captureBtn, stopBtn, playBtn;
	JPanel pane;
	JLabel vol;
	
	public static void main(String args[]) {
		new FirstSoundCapturer();
	}// end main

	class Pnl extends JPanel {

		Pnl() {
			add(captureBtn);
			add(stopBtn);
			add(playBtn);
			
			vol.setText("|");
			add(vol);
		}
		
        @Override
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Dimension s = getSize();
            int f = 32;
            g2d.setColor(Color.blue);
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 19));
            /*
            	int b = 0;
            	for (int k = 0; k < f; k++)
            		if (bytes[qq+q+k] > 0)
            			b += bytes[qq+q+k];
            		else
            			b -= bytes[qq+q+k];
            	b = (b * s.height)/( 128 * f );
            	int point = - b + s.height;
            	g2d.drawLine(q, point, q, point - 5);*/
        }
	}
	
	public FirstSoundCapturer() {// constructor
		captureBtn = new JButton("Capture");
		stopBtn = new JButton("Stop");
		playBtn = new JButton("Playback");
		vol = new JLabel();
		vol.setBounds(10, 80, 450,50);
		
		captureBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		playBtn.setEnabled(false);

		// Register anonymous listeners
		captureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				captureBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				playBtn.setEnabled(false);
				// Capture input data from the
				// microphone until the Stop
				// button is clicked.
				captureAudio();
			}// end actionPerformed
		});// end addActionListener()
		captureBtn.setBounds(10, 10, 80, 30);

		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				captureBtn.setEnabled(true);
				stopBtn.setEnabled(false);
				playBtn.setEnabled(true);
				// Terminate the capturing of
				// input data from the
				// microphone.
				stopCapture = true;
			}// end actionPerformed
		});// end addActionListener()
		stopBtn.setBounds(100, 10, 80, 30);

		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Play back all of the data
				// that was saved during
				// capture.
				playAudio();
			}// end actionPerformed
		});// end addActionListener()
		playBtn.setBounds(190, 10, 80, 30);
		
		pane = new Pnl();
		pane.setLayout(null);
		
		add(pane);
		setTitle("Capture/Playback Demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 200);
		setVisible(true);
	}// end constructor

	// This method captures audio input
	// from a microphone and saves it in
	// a ByteArrayOutputStream object.
	private void captureAudio() {
		try {
			// Get everything set up for
			// capture
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(
					TargetDataLine.class, audioFormat);
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			targetDataLine.open(audioFormat);
			targetDataLine.start();

			// Create a thread to capture the
			// microphone data and start it
			// running. It will run until
			// the Stop button is clicked.
			Thread captureThread = new Thread(new CaptureThread());
			captureThread.start();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}// end catch
	}// end captureAudio method

	// This method plays back the audio
	// data that has been saved in the
	// ByteArrayOutputStream
	private void playAudio() {
		try {
			// Get everything set up for
			// playback.
			// Get the previously-saved data
			// into a byte array object.
			byte audioData[] = byteArrayOutputStream.toByteArray();
			// Get an input stream on the
			// byte array containing the data
			InputStream byteArrayInputStream = new ByteArrayInputStream(
					audioData);
			AudioFormat audioFormat = getAudioFormat();
			audioInputStream = new AudioInputStream(byteArrayInputStream,
					audioFormat, audioData.length / audioFormat.getFrameSize());
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			// Create a thread to play back
			// the data and start it
			// running. It will run until
			// all the data has been played
			// back.
			Thread playThread = new Thread(new PlayThread());
			playThread.start();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}// end catch
	}// end playAudio

	// This method creates and returns an
	// AudioFormat object for a given set
	// of format parameters. If these
	// parameters don't work well for
	// you, try some of the other
	// allowable parameter values, which
	// are shown in comments following
	// the declarations.
	private AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}// end getAudioFormat
		// ===================================//

	// Inner class to capture data from
	// microphone
	class CaptureThread extends Thread {
		// An arbitrary-size temporary holding
		// buffer
		byte tempBuffer[] = new byte[4 * 1024];

		public void run() {
			byteArrayOutputStream = new ByteArrayOutputStream();
			stopCapture = false;
			try {// Loop until stopCapture is set
					// by another thread that
					// services the Stop button.
				while (!stopCapture) {
					// Read data from the internal
					// buffer of the data line.
					int cnt = targetDataLine.read(tempBuffer, 0,
							tempBuffer.length);
					if (cnt > 0) {
						// Save data in output stream
						// object.
						int volume = 0;
						for(int i = 0; i < tempBuffer.length; i++) {
							if (tempBuffer[i] > 0)
								volume += tempBuffer[i];
							else
								volume -= tempBuffer[i];
						}
						volume /= 128 * 4 * 1024 / 400;
						String str = "";
						for (int i = 0; i < volume; i++)
							str += "|";
						
						vol.setText(str);
						byteArrayOutputStream.write(tempBuffer, 0, cnt);
					}// end if
				}// end while
				byteArrayOutputStream.close();
			} catch (Exception e) {
				System.out.println(e);
				System.exit(0);
			}// end catch
		}// end run
	}// end inner class CaptureThread
		// ===================================//
		// Inner class to play back the data
		// that was saved.

	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[10000];

		public void run() {
			try {
				int cnt;
				// Keep looping until the input
				// read method returns -1 for
				// empty stream.
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (cnt > 0) {
						// Write data to the internal
						// buffer of the data line
						// where it will be delivered
						// to the speaker.
						sourceDataLine.write(tempBuffer, 0, cnt);
					}// end if
				}// end while
					// Block and wait for internal
					// buffer of the data line to
					// empty.
				sourceDataLine.drain();
				sourceDataLine.close();
			} catch (Exception e) {
				System.out.println(e);
				System.exit(0);
			}// end catch
		}// end run
	}// end inner class PlayThread
		// ===================================//

}