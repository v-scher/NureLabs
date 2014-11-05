package ua.khture.scherbyna.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.khture.scherbyna.MainClass;
import ua.khture.scherbyna.WindowManager;

public class Window {
	public static final Dimension	WND_SIZE	= new Dimension(1000, 700);
	private static final String		WND_TITLE	= "1C for dummies";
	public static final String
		WORKERS_PANE_NAME	= "Workers",
		COEFFS_PANE_NAME	= "Efficiency",
		EXPERTS_PANE_NAME	= "Experts";
	
	public static final String[]
		WORKERS_HEADER		= {"ФИО", "Должность", "Базовая ЗП", "Итого к оплате"},
		COEFFS_HEADER	= {"Показатель", "Е.и.", "Вес", "План", "Факт", "Выполнено, %", "МВО"};

	private JFrame		m_wnd = new JFrame();
	private CardLayout	m_layoutManager = new CardLayout();
	private JPanel		m_cards = new JPanel();
	
	private ListPanelTemplate m_workersPane;
	private ListPanelTemplate m_coeffsPane;
	private ExpertsListPanel m_expertsPane;
	
	public JFrame getFrame()
	{
		return m_wnd;
	}
	
	public Window()
	{
		m_workersPane = new ListPanelTemplate(WORKERS_HEADER);
		m_coeffsPane = new ListPanelTemplate(COEFFS_HEADER);
		m_expertsPane = new ExpertsListPanel();
		m_wnd.setTitle(WND_TITLE);
		m_wnd.setVisible(true);
		m_wnd.setResizable(false);
		m_wnd.setMinimumSize(WND_SIZE);
		Dimension screenBounds = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		m_wnd.setLocation(
				(screenBounds.width - WND_SIZE.width)/2,
				(screenBounds.height - WND_SIZE.height)/2
			);
		
		m_wnd.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	MainClass.onClose();
	        }
	    });
		
		m_cards.setLayout(m_layoutManager);
		m_cards.add(m_workersPane, WORKERS_PANE_NAME);
		m_cards.add(m_coeffsPane, COEFFS_PANE_NAME);
		m_cards.add(m_expertsPane, EXPERTS_PANE_NAME);
		m_wnd.add(m_cards);
		
		initButtons();
	}
	
	private void initButtons()
	{
		m_workersPane.addButton("Сохранить", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m_workersPane.saveDataFromEditFields();
				WindowManager.onSaveButtonClicked(m_workersPane.getContent());
			}
		});
		m_workersPane.addButton("Пересчитать", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m_workersPane.saveDataFromEditFields();
				WindowManager.onRecalculateWorkersButtonClicked(m_workersPane.getContent());
			}
		});
		m_workersPane.addButton("Добавить", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_workersPane.saveDataFromEditFields();
				String[][] content = m_workersPane.getContent();
				WindowManager.onCreateWorkerButtonClicked(content);
			}
		});
		m_workersPane.addButton("Ключевые показатели", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_workersPane.saveDataFromEditFields();
				WindowManager.onEditWorkerButtonClicked(
						m_workersPane.getSelectedRowNumber(), 
						m_workersPane.getContent()
					);
			}
		});
		m_workersPane.addButton("Удалить", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_workersPane.saveDataFromEditFields();
				WindowManager.onRemoveWorkerButtonClicked(m_workersPane.getSelectedRowNumber(), m_workersPane.getContent());
			}
		});
		
		//------------------------------

		m_coeffsPane.addButton("Назад", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_coeffsPane.saveDataFromEditFields();
				WindowManager.onBackToWorkersButtonClicked(m_coeffsPane.getContent());
			}
		});
		m_coeffsPane.addButton("Пересчитать", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m_coeffsPane.saveDataFromEditFields();
				WindowManager.onRecalculateCoeffsButtonClicked(m_coeffsPane.getContent());
			}
		});
		m_coeffsPane.addButton("Добавить", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_coeffsPane.saveDataFromEditFields();
				WindowManager.onCreateCoeffButtonClicked(m_coeffsPane.getContent());
			}
		});
		m_coeffsPane.addButton("Оценки экспертов", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_coeffsPane.saveDataFromEditFields();
				WindowManager.onExpertsButtonClicked(m_coeffsPane.getContent());
			}
		});
		m_coeffsPane.addButton("Удалить", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_coeffsPane.saveDataFromEditFields();
				WindowManager.onRemoveCoeffButtonClicked(m_coeffsPane.getSelectedRowNumber(), m_coeffsPane.getContent());
			}
		});
		
		//------------------------------

		m_expertsPane.addButton("Назад", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_expertsPane.saveDataFromEditFields();
				WindowManager.onBackToCoeffsButtonClicked(m_expertsPane.getContent());
			}
		});
		m_expertsPane.addButton("Пересчитать", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_expertsPane.saveDataFromEditFields();
				WindowManager.onRecalculateExpertsButtonClicked(m_expertsPane.getContent());
			}
		});
		m_expertsPane.addButton("Добавить эксперта", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_expertsPane.saveDataFromEditFields();
				WindowManager.onCreateExpertButtonClicked(m_expertsPane.getContent());
			}
		});
		m_expertsPane.addButton("Добавить критерий", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_expertsPane.saveDataFromEditFields();
				WindowManager.onCreateCriteriaButtonClicked(m_expertsPane.getContent());
			}
		});
		m_expertsPane.addButton("Удалить эксперта", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_expertsPane.saveDataFromEditFields();
				WindowManager.onRemoveExpertButtonClicked(m_expertsPane.getSelectedRowNumber(), m_expertsPane.getContent());
			}
		});
		m_expertsPane.addButton("Удалить критерий", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_expertsPane.saveDataFromEditFields();
				WindowManager.onRemoveCriteriaButtonClicked(m_expertsPane.getSelectedColumnNumber(), m_expertsPane.getContent());
			}
		});
	}

	public void onClose()
	{
		m_wnd.setVisible(false);
	}
	
	public void showWorkersPane(String[][] _content)
	{
		m_layoutManager.show(m_cards, WORKERS_PANE_NAME);
		m_workersPane.setContent(_content);
	}
	
	public void showCoeffsPane(String[][] _content, String _text)
	{
		m_coeffsPane.setContent(_content, (_text == null) ? m_coeffsPane.getLabelText() : _text);
		m_layoutManager.show(m_cards, COEFFS_PANE_NAME);
	}
	
	public void showExpertsPane(String[][] _content, String _text)
	{
		m_expertsPane.setContent(_content, (_text == null) ? m_expertsPane.getLabelText() : _text);
		m_layoutManager.show(m_cards, EXPERTS_PANE_NAME);
	}

	public void setTitle(String _title) {
		m_wnd.setTitle(_title);
	}
}
