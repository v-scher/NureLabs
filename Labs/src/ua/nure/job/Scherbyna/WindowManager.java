package ua.nure.job.Scherbyna;

import java.awt.Toolkit;
import java.util.Map;
import java.util.TreeMap;

import ua.nure.job.Scherbyna.containers.Coeff;
import ua.nure.job.Scherbyna.containers.ExpertRates;
import ua.nure.job.Scherbyna.containers.Worker;
import ua.nure.job.Scherbyna.ui.AddCriteriaFrame;
import ua.nure.job.Scherbyna.ui.CorrectOrExitDialog;
import ua.nure.job.Scherbyna.ui.Window;

public class WindowManager {
	private static Window m_wnd = new Window();
	private static int[] m_workersIDs = new int[1];
	private static int[] m_coeffsIDs = new int[1];
	private static int m_selectedWorkerID = -1;
	private static int m_minNewWorkerID = -1;
	private static int m_minNewCoeffID = -1;
	
	public static void init() {
		m_workersIDs[0] = -1;
		m_coeffsIDs[0] = -1;
	}

	public static void onClose()
	{
		m_wnd.onClose();
	}
	
	public static void showCoeffsPane(Map<Integer, Coeff> _coeffs)
	{
		System.out.println("showCoeffsPane() coeffs in args: " + _coeffs.size());
		m_minNewCoeffID = -1;
		int coeffsCount = (_coeffs.size() != 0) ? _coeffs.size() : 1;
		String[][] newCoeffsContent = new String[coeffsCount][Window.COEFFS_HEADER.length];
		m_coeffsIDs = new int[coeffsCount];
		m_coeffsIDs[0] = m_minNewCoeffID--;
		
		int row = 0;
		for (Integer cID : _coeffs.keySet())
		{
			Coeff coeff = _coeffs.get(cID);
			newCoeffsContent[row][0] = coeff.getName();
			newCoeffsContent[row][1] = coeff.m_metric;
			newCoeffsContent[row][2] = doubleToString(coeff.m_weight);
			newCoeffsContent[row][3] = doubleToString(coeff.getPlan());
			newCoeffsContent[row][4] = doubleToString(coeff.getFact());
			newCoeffsContent[row][5] = doubleToString(100 * coeff.getCompletionPercent());
			newCoeffsContent[row][6] = doubleToString(coeff.getPaymentCoeff());
			m_coeffsIDs[row++] = cID;
		}
		
		Map<Integer, Coeff> coeffsMap = MainClass.state.getWorkerCoeffs(m_selectedWorkerID);
		
		double paymentCoef= 0;
		for (Coeff coeff : coeffsMap.values())
		{
			paymentCoef += coeff.getPaymentCoeff();
			System.out.print(paymentCoef + " |");
		}
		
		Worker worker = MainClass.state.getWorker(m_selectedWorkerID);
		String text = "��������� ���: " + doubleToString(paymentCoef, 3) + ", � ������ " + worker.name + ": " + doubleToString(paymentCoef * worker.payment, 3);
		m_wnd.showCoeffsPane(newCoeffsContent, text);
	}
	
	public static void showWorkersPane(Map<Integer, Worker> _workers)
	{
		System.out.println("showWorkersPane() workers in args: " + _workers.size());
		m_minNewWorkerID = -1;
		int workersCount = (_workers.size() != 0) ? _workers.size() : 1;
		String[][] newWorkersContent = new String[workersCount][Window.WORKERS_HEADER.length];
		m_workersIDs = new int[workersCount];
		m_workersIDs[0] = m_minNewWorkerID--;
		
		int row = 0;
		// TODO sorting by name
		for (Integer wID : _workers.keySet())
		{
			Map<Integer, Coeff> coeffsMap = MainClass.state.getWorkerCoeffs(wID);
			
			double paymentCoef= 0;
			for (Coeff coeff : coeffsMap.values())
				paymentCoef += coeff.getPaymentCoeff();
			
			Worker worker = _workers.get(wID);
			newWorkersContent[row][0] = worker.name;
			newWorkersContent[row][1] = worker.appointment;
			newWorkersContent[row][2] = doubleToString(worker.payment, 3);
			newWorkersContent[row][3] = doubleToString(paymentCoef * worker.payment, 3);
			m_workersIDs[row++] = wID;
		}
		m_wnd.showWorkersPane(newWorkersContent);
	}
	
	public static void showExpertsPane(ExpertRates _expertRates)
	{
		Map<Integer, Coeff> coeffsMap = MainClass.state.getWorkerCoeffs(m_selectedWorkerID);
		
		double paymentCoef= 0.0;
		for (Coeff coeff : coeffsMap.values())
		{
			paymentCoef += coeff.getPaymentCoeff();
			System.out.print(paymentCoef + " |");
		}
		
		Worker worker = MainClass.state.getWorker(m_selectedWorkerID);
		String text = "��������� ���: " + doubleToString(paymentCoef, 3) + ", � ������ " + worker.name + ": " + doubleToString(paymentCoef * worker.payment, 3);
		
		if (_expertRates == null)
		{
			String[][] expContent = {{"��� ��������"}, {""}};
			m_wnd.showExpertsPane(expContent, text);
			if (!onCreateCriteriaButtonClicked(expContent))
				MainClass.showCoeffsPane(m_selectedWorkerID);
		} else {
			String[] criterias = _expertRates.getCriterias();
			String[] experts = _expertRates.getExpertNames();
			int[][] rates = _expertRates.getRates();
			
			String[][] content = new String[experts.length + 1][criterias.length + 1];
			
			content[0][0] = "��� ��������";
			for (int col = 1; col < content[0].length; col++)
				content[0][col] = criterias[col - 1];
				
			for (int row = 1; row < content.length; row++)
			{
				content[row][0] = experts[row - 1];
				for (int col = 1; col < content[0].length; col++)
					content[row][col] = "" + rates[row-1][col-1];
			}
					
			m_wnd.showExpertsPane(content, text);
		}
	}

	private static void onPaymentFormatError()
	{
		// TODO error sound
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onPaymentFormatError() ");
	}

	private static void onYouShouldSelectWorkerFirstError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onYouShouldSaveWorkersFirstError() ");
	}

	private static void onWeightFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onWeightFormatError() ");
	}

	private static void onPlanFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onPlanFormatError() ");
	}

	private static void onFactFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onFactFormatError() ");
	}
	
	private static void onRatesFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onRatesFormatError() ");
	}

	private static void onExpertFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onExpertFormatError() ");
	}

	private static void onCriteriaFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onCriteriaFormatError() ");
	}
	
	private static void onMetricsFormatError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onMetricsFormatError() ");
	}

	private static void onWeightSummError() {
       	Toolkit.getDefaultToolkit().beep();
		System.out.println("onWeightSummError() ");
	}

	private static String doubleToString(double _num)
	{
		return doubleToString(_num, 4);
	}
	
	private static String doubleToString(double _num, int _numsAfterPoint)
	{
		String str = Double.toString(_num);
		int len = str.length();
		int pos = str.indexOf(".");
		if (len - pos > _numsAfterPoint)
			str = str.substring(0, pos + _numsAfterPoint);
		return str;
	}
	
	private static double getNumFromString(String _dataString)
	{
		double number = -1;
		try{
			number = new Integer(_dataString);
		} catch (Exception e) {
			try{
				_dataString = _dataString.replace(",", ".");
				number = new Double(_dataString);
			} catch (Exception e1) {}
		}
		return number;
	}
	
	public static void setTitle(String _title)
	{
		m_wnd.setTitle(_title);
	}

	public static void onSaveButtonClicked(String[][] _content)
	{
		if (saveAndUpdateWorkersInfo(_content))
			MainClass.saveDataOnDisk();
	}
	
	public static boolean saveAndUpdateWorkersInfo(String[][] _content)
	{
		TreeMap<Integer, Worker> newWorkersMap = new TreeMap<>();
		
		for (int row = 0; row < _content.length; row++)
		{
			String name = _content[row][0];
			String appoinment = _content[row][1];
			double payment = 0.0;
			
			if (	name == null || 
					appoinment == null || 
					name.equals("") ||
					appoinment.equals(""))
				continue;

			if ((payment = getNumFromString(_content[row][2])) < 0)
			{
				onPaymentFormatError();
				return false;
			}

			newWorkersMap.put(
					m_workersIDs[row],
					new Worker(
							name,
							appoinment,
							payment
					)
			);
		}
		
		for (Integer ID : newWorkersMap.keySet())
			System.out.println("onSaveWorkersButtonClicked() " + ID + " " + newWorkersMap.get(ID).name + " " + newWorkersMap.get(ID).appointment);
		
		MainClass.updateWorkersInfo(newWorkersMap);
		return true;
	}
	
	public static void onRecalculateWorkersButtonClicked(String[][] _content)
	{
		saveAndUpdateWorkersInfo(_content);
	}

	public static void onCreateWorkerButtonClicked(String[][] _content)
	{
		int[] newWorkersIDs = new int[m_workersIDs.length + 1];
		String[][] newContent = new String[_content.length + 1][Window.WORKERS_HEADER.length];
		
		for (int row = 0; row < m_workersIDs.length; row++)
		{
			String[] rowStr = _content[row];
			newWorkersIDs[row] = m_workersIDs[row];
			newContent[row] = ( rowStr == null) ? 
					new String[Window.WORKERS_HEADER.length] 
					: rowStr;
			System.out.println("onCreateWorkerButtonClicked() " + m_workersIDs[row] + " " + rowStr[0]);
		}
		
		newWorkersIDs[newWorkersIDs.length - 1] = m_minNewWorkerID--;
		System.out.println("onCreateWorkerButtonClicked() " + newWorkersIDs[newWorkersIDs.length - 1]);
		newContent[newContent.length - 1] = new String[Window.WORKERS_HEADER.length];
		
		m_workersIDs = newWorkersIDs;
		for (int i = 0; i < newContent.length; i++)
			for (int j = 0; j < newContent[0].length; j++)
				if (newContent[i][j] == null)
					newContent[i][j] = "";
		
		m_wnd.showWorkersPane(newContent);
	}

	public static void onEditWorkerButtonClicked(int _selectedRow, String[][] _content)
	{
		if (!saveAndUpdateWorkersInfo(_content))
			return;
		
		if (_selectedRow < 0)
			return;
		
		m_selectedWorkerID = m_workersIDs[_selectedRow];
		if (m_selectedWorkerID < 0)
			onYouShouldSelectWorkerFirstError();
		else
			MainClass.showCoeffsPane(m_selectedWorkerID);
	}

	public static void onRemoveWorkerButtonClicked(int _row, String[][] _content)
	{
		if (m_workersIDs[_row] > -1)
			MainClass.removeWorker(m_workersIDs[_row]);
		
		String[][] newContent = new String[_content.length - 1][Window.WORKERS_HEADER.length];
		
		if (_content.length > 1)
		{
			int newRow = 0;
			int[] newWorkersIDs = new int[m_workersIDs.length - 1];
			for (int row = 0; row < m_workersIDs.length; row++)
			{
				if (row == _row)
					continue;
				String[] rowStr = _content[row];
				newWorkersIDs[newRow] = m_workersIDs[row];
				newContent[newRow] = ( rowStr == null) ? 
						new String[Window.WORKERS_HEADER.length] 
						: rowStr;
				newRow++;
			}
			m_workersIDs = newWorkersIDs;
		} else {
			m_workersIDs[0] = -1;
		}
		
		m_wnd.showWorkersPane(newContent);
	}
	//-------------------------------------------

	public static boolean saveAndUpdateCoeffsInfo(String[][] _content) 
	{
		TreeMap<Integer, Coeff> newCoeffsMap = new TreeMap<>();
		
		double sumWeight = 0.0;
		for (int row = 0; row < _content.length; row++)
		{
			String name = _content[row][0];
			String metr = _content[row][1];
			double weight = 0.0;
			double fact = 0.0;
			double plan = 0.0;

			if (	name == null || 
					name.equals(""))
				continue;
			
			if (	metr == null || 
					metr.equals(""))
			{
				onMetricsFormatError();
				return false;
			}
			
			if ((weight = getNumFromString(_content[row][2])) < 0)
			{
				onWeightFormatError();
				return false;
			}
			sumWeight += weight;
			if ((plan = getNumFromString(_content[row][3])) < 0)
			{
				onPlanFormatError();
				return false;
			}
			if ((fact = getNumFromString(_content[row][4])) < 0)
			{
				onFactFormatError();
				return false;
			}
			
			newCoeffsMap.put(m_coeffsIDs[row],new Coeff(name, metr, weight,	plan, fact));
		}
		
		if (sumWeight > 1)
		{
			onWeightSummError();
			return false;
		}

		for (Integer ID : newCoeffsMap.keySet())
			System.out.println("onSaveCoeffsButtonClicked() " + ID + " " + newCoeffsMap.get(ID).getName() + " " + newCoeffsMap.get(ID).m_metric + " " + newCoeffsMap.get(ID).m_weight);

		MainClass.updateCoeffsInfo(m_selectedWorkerID, newCoeffsMap);
		return true;
	}

	public static void onRecalculateCoeffsButtonClicked(String[][] _content) 
	{
		saveAndUpdateCoeffsInfo(_content);
	}
	
	public static void onCreateCoeffButtonClicked(String[][] _content) 
	{
		int[] newCoeffsIDs = new int[m_coeffsIDs.length + 1];
		String[][] newContent = new String[_content.length + 1][Window.COEFFS_HEADER.length];
		
		for (int row = 0; row < m_coeffsIDs.length; row++)
		{
			String[] rowStr = _content[row];
			newCoeffsIDs[row] = m_coeffsIDs[row];
			newContent[row] = ( rowStr == null) ? 
					new String[Window.COEFFS_HEADER.length] 
					: rowStr;
			System.out.println("onCreateCoeffButtonClicked() " + m_coeffsIDs[row] + " " + rowStr[0]);
		}
		
		newCoeffsIDs[newCoeffsIDs.length - 1] = m_minNewCoeffID--;
		System.out.println("onCreateWorkerButtonClicked() " + newCoeffsIDs[newCoeffsIDs.length - 1]);
		newContent[newContent.length - 1] = new String[Window.COEFFS_HEADER.length];
		
		m_coeffsIDs = newCoeffsIDs;
		for (int i = 0; i < newContent.length; i++)
			for (int j = 0; j < newContent[0].length; j++)
				if (newContent[i][j] == null)
					newContent[i][j] = "";
		
		m_wnd.showCoeffsPane(newContent, null);
	}
	
	public static void onExpertsButtonClicked(String[][] _content) 
	{
		System.out.println("-------onExpertsButtonClicked() 1");
		if (!saveAndUpdateCoeffsInfo(_content))
			return;
		System.out.println("-------onExpertsButtonClicked() 2");
		
		MainClass.showWorkersExpertsPane(m_selectedWorkerID);
	}

	public static void onBackToWorkersButtonClicked(String[][] _content) 
	{
		if (saveAndUpdateCoeffsInfo(_content))
		{
			m_selectedWorkerID = -1;
			MainClass.showWorkersPane();
		}
		else
		{
			CorrectOrExitDialog dialog = new CorrectOrExitDialog(m_wnd, "���� �����, ��������� �� ����������", "��� ����� �����", "������");
			if (dialog.shouldExitWithoutSaving())
			{
			    m_selectedWorkerID = -1;
			    MainClass.showWorkersPane();
			}
		}
	}

	public static void onRemoveCoeffButtonClicked(int _row, String[][] _content)
	{
		if (m_coeffsIDs[_row] > -1)
			MainClass.removeCoeff(m_selectedWorkerID, m_coeffsIDs[_row]);
		
		String[][] newContent = new String[_content.length - 1][Window.COEFFS_HEADER.length];

		if (_content.length > 1)
		{
			int[] newCoeffsIDs = new int[m_coeffsIDs.length - 1];
			int newRow = 0;
			for (int row = 0; row < m_coeffsIDs.length; row++)
			{
				if (row == _row)
					continue;
				String[] rowStr = _content[row];
				newCoeffsIDs[newRow] = m_coeffsIDs[row];
				newContent[newRow] = ( rowStr == null) ? 
						new String[Window.COEFFS_HEADER.length] 
						: rowStr;
				newRow++;
			}
			
			m_coeffsIDs = newCoeffsIDs;
		} else {
			m_coeffsIDs[0] = -1;
		}
		
		m_wnd.showCoeffsPane(newContent, null);
	}

	//--------------------------------------------------------

	public static boolean saveAndUpdateExpertsInfo(String[][] _content) 
	{
		String[] experts = new String[_content.length - 1];
		String[] criterias = new String[_content[0].length - 1];
		int rates[][] = new int[_content.length - 1][_content[0].length - 1];
		
		for (int col = 1; col < _content[0].length; col++)
		{
			if (_content[0][col] == null || _content[0][col].equals(""))
			{
				onCriteriaFormatError();
				return false;
			}
			criterias[col - 1] = _content[0][col]; 
		}
		
		for (int row = 1; row < _content.length; row++)
		{
			if (_content[row][0] == null || _content[row][0].equals(""))
			{
				onExpertFormatError();
				return false;
			}
			experts[row - 1] = _content[row][0];
			
			for (int col = 1; col < _content[0].length; col++)
			{
				int rate = (int) getNumFromString(_content[row][col]);
				if (rate < 0 || rate > 4)
				{
					onRatesFormatError();
					return false;
				}
				rates[row-1][col-1] = rate;
				System.out.println("saveAndUpdateExpertsInfo " + rate);
			}
		}

		MainClass.updateExpertsInfo(m_selectedWorkerID, new ExpertRates(0, experts, criterias, rates));
		return true;
	}

	public static void onBackToCoeffsButtonClicked(String[][] _content) 
	{
		if (saveAndUpdateExpertsInfo(_content))
		{
			MainClass.showCoeffsPane(m_selectedWorkerID);
		}
		else
		{
			CorrectOrExitDialog dialog = new CorrectOrExitDialog(m_wnd, "���� �����, ��������� �� ����������", "��� ����� �����", "������");
			if (dialog.shouldExitWithoutSaving())
			{
				MainClass.showCoeffsPane(m_selectedWorkerID);
			}
		}
	}
	
	public static void onRecalculateExpertsButtonClicked(String[][] _content) 
	{
		saveAndUpdateExpertsInfo(_content);
	}
	
	public static void onCreateExpertButtonClicked(String[][] _content) 
	{
		String[][] newContent = new String[_content.length + 1][_content[0].length];
		
		for (int row = 0; row < _content.length; row++)
			for (int col = 0; col < _content[0].length; col++)
				newContent[row][col] = _content[row][col];

		for (int col = 0; col < _content[0].length; col++)
			newContent[newContent.length - 1][col] = "";
		
		m_wnd.showExpertsPane(newContent, null);
	}
	
	public static boolean onCreateCriteriaButtonClicked(String[][] _content) 
	{
		AddCriteriaFrame dialog = new AddCriteriaFrame(m_wnd);
		String newCriteriaName = dialog.getText();
		if (newCriteriaName.equals(""))
			return false;
		
		String[][] newContent = new String[_content.length][_content[0].length + 1];
		
		for (int row = 0; row < _content.length; row++)
		{
			for (int col = 0; col < _content[0].length; col++)
				newContent[row][col] = _content[row][col];
			
			newContent[row][newContent[0].length - 1] = "";
		}
		System.out.println(newCriteriaName);
		newContent[0][newContent[0].length - 1] = newCriteriaName;
		
		m_wnd.showExpertsPane(newContent, null);
		return true;
	}

	public static void onRemoveExpertButtonClicked(int _rowToRemove, String[][] _content)
	{
		if (_content.length == 2)
		{
			MainClass.removeExpertRates(m_selectedWorkerID);
			
			MainClass.showCoeffsPane(m_selectedWorkerID);
			return;
		}
		
		_rowToRemove++;
		String[][] newContent = new String[_content.length - 1][_content[0].length];
		
		int currentRow = 0;
		for (int row = 0; row < _content.length; row++)
		{
			if (row == _rowToRemove)
				continue;
			for (int col = 0; col < _content[0].length; col++)
			{
				System.out.println(_content[row][col]);
				newContent[currentRow][col] = _content[row][col];
			}
			currentRow++;
		}
		
		m_wnd.showExpertsPane(newContent, null);
	}

	public static void onRemoveCriteriaButtonClicked(int _colToRemove, String[][] _content)
	{
		if (_colToRemove == 0)
			return;
		if (_content[0].length == 2)
		{
			MainClass.removeExpertRates(m_selectedWorkerID);
			MainClass.showCoeffsPane(m_selectedWorkerID);
			return;
		}
		
		String[][] newContent = new String[_content.length][_content[0].length - 1];
		
		for (int row = 0; row < _content.length; row++)
		{
			newContent[row] = new String[newContent[0].length];
			for (int col = 0; col < newContent[0].length; col++)
				newContent[row][col] = _content[row][col];
		}
		
		m_wnd.showExpertsPane(newContent, null);
	}
}
