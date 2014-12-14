package ua.nure.course3.MiZUP.coursework.Alistratov;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class AlistratovCourseWork implements ActionListener {
	CardLayout cards;
	JFrame jfrm;
	JScrollPane jsp1, jsp2, jsp3, jsp4;
	JPanel mainPan, criteriaPan, tablePan, startPanel, setPan, resultPan;

	JTable tableCr, tablePoints;
	JTextField experts, criteria;
	JLabel hi1, hi2;
	JButton Start, goTable1, goTable2, goCriteria, calculate, again;
	double[][] rates;
	boolean ranging;
	String[] colHeads;
	Double[][] congruenty;

	void init() {
		int x = 500;
		int y = 500;

		jfrm = new JFrame("Ex2");
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

		startPanel.add(hi1 = new JLabel("Вас вітає курсова робота", JLabel.CENTER));
		hi1.setBounds(0, 100, 464, 20);
		startPanel.add(hi2 = new JLabel("Алістратова Валерія Юрійовича!", JLabel.CENTER));
		hi2.setBounds(0, 140, 464, 20);
		startPanel.add(Start = new JButton("Далі"));
		Start.setBounds(0, 300, 464, 142);
		Start.addActionListener(this);

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

	AlistratovCourseWork() {
		init();
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == Start) {
			JLabel qwe1,qwe2;
			setPan.add(qwe1 = new JLabel("Кількість експертів (до 14 включно)"));
			qwe1.setBounds(0, 100, 464, 20);
			setPan.add(experts = new JTextField("2"));
			experts.setBounds(0, 125, 464, 30);
			setPan.add(qwe2 = new JLabel("Кількість критеріїв оцінки (до 10 включно)"));
			qwe2.setBounds(0, 160, 464, 20);
			setPan.add(criteria = new JTextField("2"));
			criteria.setBounds(0, 185, 464, 30);
			setPan.add(goCriteria = new JButton("Далі"));
			goCriteria.setBounds(0, 300, 464, 142);

			goCriteria.addActionListener(this);
			Document doc = experts.getDocument();
			if (doc instanceof AbstractDocument) {
				AbstractDocument abDoc  = (AbstractDocument) doc;
				abDoc.setDocumentFilter(new DocumentInputFilter());
			}

			doc = criteria.getDocument();
			if (doc instanceof AbstractDocument) {
				AbstractDocument abDoc  = (AbstractDocument) doc;
				abDoc.setDocumentFilter(new DocumentInputFilter());
			}
			cards.show(mainPan, "Settings");
		}
		if(ae.getSource() == goCriteria) {
			try {
				if ((new Integer(experts.getText()) >= 2)
						&& (new Integer(experts.getText()) <= 14)
						&& (new Integer(criteria.getText()) <= 10)
						&& (new Integer(criteria.getText()) >= 2)) {
					String[] colHeads = { "Введіть назви критеріїв" };
					String[][] data = new String[new Integer(criteria.getText())][1];
					tableCr = new JTable(data, colHeads);
					jsp1 = new JScrollPane(tableCr);

					criteriaPan.add(jsp1);
					jsp1.setBounds(0, 0, 464, 295);

					criteriaPan.add(goTable1 = new JButton("Метод ранжування"));
					goTable1.setBounds(0, 300, 230, 142);

					criteriaPan.add(goTable2 = new JButton("Метод приписування балів"));
					goTable2.setBounds(234, 300, 230, 142);

					goTable1.addActionListener(this);
					goTable2.addActionListener(this);

					cards.show(mainPan, "Criteria");
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		if (ae.getSource() == goTable1
				|| ae.getSource() == goTable2) {
			tableCr.editCellAt(0, 0);
			colHeads = new String[new Integer(criteria.getText())];
			ranging = (ae.getSource() == goTable1);
			boolean toLong = (new Integer(criteria.getText()) > 5);

			for (int criter = 0; criter < colHeads.length; criter++) {
				if (tableCr.getValueAt(criter, 0) == null) {
					if (toLong)
						colHeads[criter] = "Кр. " + (criter + 1);
					else
						colHeads[criter] = "Критерій " + (criter + 1);
				} else
					colHeads[criter] = tableCr.getValueAt(criter, 0).toString();
			}

			Object[][] data = new Object[new Integer(experts.getText())][new Integer(criteria.getText())];

			tablePoints = new JTable(data, colHeads);

			if (ranging) {
				JLabel qwe0;
				tablePan.add(qwe0 = new JLabel("Введіть цілі коефіцієнти за принципом: 1 - найменш важливий критерій, "));
				qwe0.setBounds(0, 0, 434, 15);
				JLabel qwe1;
				tablePan.add(qwe1 = new JLabel(colHeads.length + " - найважливіший критерій. Значення не повинні повторюватись"));
				qwe1.setBounds(0, 15, 434, 15);
			} else {
				JLabel qwe0;
				tablePan.add(qwe0 = new JLabel("Введіть натуральні коефіцієнти від 0 до 10 за принципом: "));
				qwe0.setBounds(0, 0, 434, 15);
				JLabel qwe1;
				tablePan.add(qwe1 = new JLabel("10 - найважливіший критерій, 0 - найменш важливий критерій."));
				qwe1.setBounds(0, 15, 434, 15);
			}

			jsp2 = new JScrollPane(tablePoints);
			tablePan.add(jsp2);
			jsp2.setBounds(65, 45, 399, 19 + 16*data.length);

			JLabel[] qwe = new JLabel[data.length];

			for (int i = 0; i < data.length; i++) {
				tablePan.add(qwe[i] = new JLabel("Експерт " + (i+1)));
				qwe[i].setBounds(0, 60 + i*16, 65, 15);
			}

			tablePan.add(calculate = new JButton("Обчислити"));
			calculate.setBounds(0, 300, 464, 142);

			calculate.addActionListener(this);
			cards.show(mainPan, "Table");
		}
		if (ae.getSource() == calculate) {
			tablePoints.editCellAt(0, 0);
			rates = new double[new Integer(experts.getText())][new Integer(criteria.getText())];
			try {
				Double tmp;
				for (int ex = 0; ex < tablePoints.getRowCount(); ex++) {
					for (int cr = 0; cr < tablePoints.getColumnCount(); cr++) {
						tmp = new Double((String) tablePoints.getValueAt(ex, cr));
						// перевіряємо на НЕПОВТОРЮВАНІСТЬ оцінок для методу РАНЖУВАННЯ
						if (ranging) {
							boolean contain = false;
							for (int q = 0; q <= cr ; q++) {
								if (rates[ex][q] == tmp)
									contain = true;
							}
							if ( tmp >= 1
									&& tmp <= colHeads.length
									&& !contain) {
								rates[ex][cr] = tmp;
							} else {
								Toolkit.getDefaultToolkit().beep();
								return;
							}
						} else {
							if(tmp >= 0
									&& tmp <= 10) {
								rates[ex][cr] = tmp;
							} else {
								Toolkit.getDefaultToolkit().beep();
								return;
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				Toolkit.getDefaultToolkit().beep();
				return;
			} catch (NullPointerException e) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}

			Object[][] data = new Double[2][rates[0].length];
			//-------------------------------------------------------------
			data = makeMagic(rates);

			JTable resultTable = new JTable(data, colHeads);
			jsp3 = new JScrollPane(resultTable);

			JTable congrTable = new JTable(congruenty, colHeads);
			jsp4 = new JScrollPane(congrTable);

			JLabel qwe0, qwe2;
			resultPan.add(qwe0 = new JLabel("Вагові коефіцієнти критеріїв"));
			qwe0.setBounds(0, 0, 434, 15);

			resultPan.add(jsp3);
			jsp3.setBounds(0, 20, 464, 39);

			resultPan.add(qwe2 = new JLabel("Дисперсія оцінок експертів"));
			qwe2.setBounds(0, 70, 434, 15);

			resultPan.add(jsp4);
			jsp4.setBounds(0, 90, 464, 39);

			resultPan.add(again = new JButton("Ще раз !"));
			again.setBounds(0, 300, 464, 142);
			again.addActionListener(this);

			cards.show(mainPan, "Result");
		}
		if (ae.getSource() == again) {
			jfrm.dispose();
			init();
			JLabel qwe1,qwe2;
			setPan.add(qwe1 = new JLabel("Кількість експертів"));
			qwe1.setBounds(0, 100, 464, 20);
			setPan.add(experts = new JTextField("2"));
			experts.setBounds(0, 125, 464, 30);
			setPan.add(qwe2 = new JLabel("Кількість критеріїв оцінки"));
			qwe2.setBounds(0, 160, 464, 20);
			setPan.add(criteria = new JTextField("2"));
			criteria.setBounds(0, 185, 464, 30);
			setPan.add(goCriteria = new JButton("Далі"));
			goCriteria.setBounds(0, 300, 464, 142);

			goCriteria.addActionListener(this);
			Document doc = experts.getDocument();
			if (doc instanceof AbstractDocument) {
				AbstractDocument abDoc  = (AbstractDocument) doc;
				abDoc.setDocumentFilter(new DocumentInputFilter());
			}

			doc = criteria.getDocument();
			if (doc instanceof AbstractDocument) {
				AbstractDocument abDoc  = (AbstractDocument) doc;
				abDoc.setDocumentFilter(new DocumentInputFilter());
			}
			cards.show(mainPan, "Settings");
		}
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AlistratovCourseWork();
			}
		});
	}

	Double[][] makeMagic(double[][] rates) {
		Double[][] result = new Double[1][rates[0].length];
		double[] tmp = new double[rates[0].length];
		congruenty = new Double[1][rates[0].length];
		//------------------------------
		int sum = 0;

		for (int i = 0; i < rates[0].length; i++){
			result[0][i] = 0D;
			for (int j = 0; j < rates.length; j++)
				result[0][i] += rates[j][i];
			sum += result[0][i];
		}
		if (ranging) {
			for (int i = 0; i < rates[0].length; i++) {
				result[0][i] = (result[0][i]/sum);
			}
		} else {
			for (int i = 0; i < rates[0].length; i++) {
				result[0][i] = (result[0][i]/sum);
			}
		}

		// обчислюємо узгодженість

		for (int q = 0; q < rates[0].length; q++) {
			tmp[q] = 0D;
			for (int k = 0; k < rates.length; k++)
				tmp[q] += rates[k][q];

			tmp[q] = tmp[q]/rates.length;
		}
		// в result[1] отримано НОРМОВАНІ оцінки КРИТЕРІЇВ

		// крокуємо по критеріях
		for (int j = 0; j < rates[0].length; j++) {
			congruenty[0][j] = 0D;
			// крокуємо по експертах
			for (int i = 0; i < rates.length; i++) {
				congruenty[0][j] += ( tmp[j] -
						rates[i][j]
				) * ( tmp[j] - rates[i][j] );
			}
			congruenty[0][j] = congruenty[0][j]/rates.length;
		}
		//------------------------------
		return result;
	}

	class DocumentInputFilter extends DocumentFilter {
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet as) throws BadLocationException {
			int len = text.length();
			if (len > 0) {
				if (Character.isDigit(text.charAt(len - 1)))
					super.insertString(fb, offset, text, as);
				else
					Toolkit.getDefaultToolkit().beep();
			}
		}

		public void replace(FilterBypass fb, int offset , int length, String text, AttributeSet as) throws BadLocationException {
			int len = text.length();
			if (len > 0) {
				if (Character.isDigit(text.charAt(len - 1)))
					super.replace(fb, offset, length, text, as);
				else
					Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}