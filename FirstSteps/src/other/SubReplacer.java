package other;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SubReplacer extends JFrame {
	
	JLabel labe1, labe2;
	JTextField tf1, tf2;
	JButton b1, b2, work;
	
	File subs1, subs2;
	
	public SubReplacer() {
		setBounds(400, 200, 400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		
		labe1 = new JLabel("Source subs file:");
		labe1.setBounds(10, 20, 100, 30);
		add(labe1);
		
		tf1 = new JTextField();
		tf1.setBounds(10, 50, 370, 30);
		add(tf1);

		labe2 = new JLabel("Translating donor file:");
		labe2.setBounds(10, 100, 200, 30);
		add(labe2);
		
		tf2 = new JTextField();
		tf2.setBounds(10, 130, 370, 30);
		add(tf2);
		
		b1 = new JButton("Set source");
		b1.setBounds(10, 170, 180, 110);
		b1.setActionCommand("s");
		b1.addActionListener(new ButAL(this));
		add(b1);

		b2 = new JButton("Set translation donor");
		b2.setBounds(200, 170, 180, 110);
		b2.setActionCommand("d");
		b2.addActionListener(new ButAL(this));
		add(b2);
		
		work = new JButton("Work!");
		work.setBounds(10, 290, 370, 60);
		work.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try ( FileWriter fw = new FileWriter("result subs.srt");
					  FileReader fr1 = new FileReader(subs1);
					  FileReader fr2 = new FileReader(subs2) ) {
					
					int c = 0;
					int inc = 0;
					while((c = fr1.read()) != -1 ){
			    		System.out.print((char)c);
			    		if (inc == 100) {
			    			inc = 0;
			    			break;
			    		}
			    		inc++;
			    	}
					
		    		System.out.println ();
		    		System.out.println ("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		    		System.out.println ();
		    		
					while((c = fr2.read()) != -1 ){
			    		System.out.print((char)c);
			    		if (inc == 100) {
			    			inc = 0;
			    			break;
			    		}
			    		inc++;
			    	}
					
				} catch(IOException qq) { 
					System.out.println("An I/O Error Occured"); 
				}
				
			}
		});
		work.setEnabled(false);
		add(work);
	}

	public static void main(String[] args) {
		new SubReplacer();
	}
}

class ButAL implements ActionListener {
	SubReplacer qwer;
	boolean source;
	
	ButAL(SubReplacer qwe) {
		qwer = qwe;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		source = e.getActionCommand().equals("s");
		FileDialog fd = new FileDialog(qwer, "Set source" , FileDialog.LOAD);
		fd.setModal(true);
		fd.setVisible(true);
		
		if( ! fd.getFile().endsWith(".srt")
		&& ! fd.getFile().endsWith(".txt") ) {
			return;
		}
		
		if (source) {
			qwer.subs1 = new File(fd.getDirectory() + "//" + fd.getFile());
			qwer.tf1.setText(fd.getFile());
		} else {
			qwer.subs2 = new File(fd.getDirectory() + "//" + fd.getFile());
			qwer.tf2.setText(fd.getFile());
		}
		
		if (qwer.subs1 != null
		&& qwer.subs2 != null)
			qwer.work.setEnabled(true);
	}
}
