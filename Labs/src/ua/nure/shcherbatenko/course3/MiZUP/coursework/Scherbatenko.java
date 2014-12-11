package ua.nure.shcherbatenko.course3.MiZUP.coursework;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Scherbatenko implements ActionListener {
	CardLayout cards;
	JFrame jfrm;
	JScrollPane jsp1, jsp2, jsp3, jsp4;
	JPanel mainPan, criteriaPan, tablePan, startPanel, setPan, resultPan;
	
	JTable tableCr, tablePoints;
	JTextField experts, criteria;
	JLabel hi1, hi2;
	JButton Start, goTable1, goTable2, goCriteria, calculate, again;
	JTree tree; 
	JLabel jlab; 
	  
	void init() {
		int x = 500;
		int y = 500;

	    jfrm = new JFrame("MS Project free parody"); 
	    jfrm.setSize(x, y);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    jfrm.setLocation((int) screenSize.getWidth()/2-(x/2), (int) screenSize.getHeight()/2-(y/2));
	    jfrm.setMinimumSize(new Dimension(x,y));
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	mainPan = new JPanel(); 
	    mainPan.setLayout(cards = new CardLayout());
	    mainPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	startPanel = new JPanel();
	    startPanel.setLayout(null);
	    /*
	    startPanel.add(hi1 = new JLabel("Вас вітає курсова робота", JLabel.CENTER));
	    hi1.setBounds(0, 100, 464, 20);
	    startPanel.add(hi2 = new JLabel("Алістратова Валерія Юрійовича!", JLabel.CENTER));
	    hi2.setBounds(0, 140, 464, 20);
	    startPanel.add(Start = new JButton("Далі"));
	    Start.setBounds(0, 300, 464, 142);
	    Start.addActionListener(this);
	    */

	    //---------------------------------------
	 // Create top node of tree. 
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("Options");
	 
	    // Create subtree of "A".
	    DefaultMutableTreeNode a = new DefaultMutableTreeNode("A");
	    top.add(a);
	    DefaultMutableTreeNode a1 = new DefaultMutableTreeNode("A1");
	    a.add(a1);
	    DefaultMutableTreeNode a2 = new DefaultMutableTreeNode("A2");
	    a.add(a2);
	 
	    // Create subtree of "B".
	    DefaultMutableTreeNode b = new DefaultMutableTreeNode("B");
	    top.add(b);
	    DefaultMutableTreeNode b1 = new DefaultMutableTreeNode("B1");
	    b.add(b1);
	    DefaultMutableTreeNode b2 = new DefaultMutableTreeNode("B2");
	    b.add(b2);
	    DefaultMutableTreeNode b3 = new DefaultMutableTreeNode("B3");
	    b.add(b3);
	    
	    DefaultMutableTreeNode c = new DefaultMutableTreeNode("C");
	    top.add(c);
	 
	    // Create the tree. 
	    tree = new JTree(top);
	 
	    // Add the tree to a scroll pane. 
	    JScrollPane jsp = new JScrollPane(tree);
	 
	    // Add the scroll pane to the content pane. 
	    jfrm.add(jsp);
	    jsp.setBounds(0, 0, 200, 200);
	 
	    // Handle tree selection events.
	    tree.addTreeSelectionListener(new TreeSelectionListener() {
	      public void valueChanged(TreeSelectionEvent tse) {
	        //jlab.setText("Selection is " + tse.getPath());
	      }
	    });
	    //---------------------------------------
	setPan = new JPanel(null);
	criteriaPan = new JPanel(null);
	tablePan = new JPanel(null);
	resultPan = new JPanel(null);
	
	    mainPan.add(startPanel, "Start");
	    mainPan.add(setPan, "Settings");
	    mainPan.add(criteriaPan, "Criteria");
	    mainPan.add(tablePan, "Table");
	    mainPan.add(resultPan, "Result");
	    
	    jfrm.add(mainPan);
	    jfrm.setVisible(true);
	}
	
	Scherbatenko() {
		init();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Scherbatenko();
			}
		});
	}
}
