package ua.nure.job.Scherbyna;

import java.util.Map;

import ua.nure.job.Scherbyna.containers.Coeff;
import ua.nure.job.Scherbyna.containers.ExpertRates;
import ua.nure.job.Scherbyna.containers.State;
import ua.nure.job.Scherbyna.containers.Worker;


public class MainClass {
	public static State state = StateLoader.getState();
	
	public static void init()
	{
		WindowManager.init(); 
		showWorkersPane();
	}
	
	public static void onClose()
	{
		WindowManager.onClose();
		System.exit(0);
	}
	
	public static void showWorkersPane()
	{
		WindowManager.setTitle("Workers");
		WindowManager.showWorkersPane(state.getWorkers());
	}
	
	public static void showCoeffsPane(int _selectedWorkerID)
	{
		WindowManager.setTitle("Editing worker: " + state.getWorker(_selectedWorkerID).name);
		WindowManager.showCoeffsPane(state.getWorkerCoeffs(_selectedWorkerID));
	}
	
	public static void showWorkersExpertsPane(int _wID)
	{
		WindowManager.showExpertsPane(state.getExpertRates(_wID));
	}

	public static void updateWorkersInfo(Map<Integer, Worker> _workersMap)
	{
		for (Integer ID : _workersMap.keySet())
		{
			System.out.println("updateWorkersInfo() " + ID + " " + _workersMap.get(ID).name);
			state.updateWorker(ID, _workersMap.get(ID));
		}
		showWorkersPane();
	}

	public static void updateCoeffsInfo(int _wID, Map<Integer, Coeff> _coeffsMap)
	{
		for (Integer ID : _coeffsMap.keySet())
		{
			System.out.println("updateCoeffsInfo() " + ID + " " + _coeffsMap.get(ID).getName() + " " + _coeffsMap.get(ID).m_weight);
			state.updateCoeff(_wID, ID, _coeffsMap.get(ID));
		}
		showCoeffsPane(_wID);
	}

	public static void updateExpertsInfo(int _wID, ExpertRates _expRates)
	{
		state.updateExpertRates(_wID, _expRates);
		showWorkersExpertsPane(_wID);
	}
	
	@SuppressWarnings("static-access")
	public static void main(String args[]){ new MainClass().init(); }

	public static boolean removeCoeff(Integer _wID, Integer _cID)
	{
		return state.removeCoeff(_wID,_cID);
	}

	public static void removeWorker(Integer _wID)
	{
		state.removeWorker(_wID);
	}
	
	public static void saveDataOnDisk()
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				StateLoader.saveState(state);
			}
		}).run();
	}

	public static boolean removeExpertRates(int _selectedWorkerID) {
		boolean valToRet = removeCoeff(_selectedWorkerID, state.m_workerExpertsCoeffsIDsMap.get(_selectedWorkerID));
		showCoeffsPane(_selectedWorkerID);
		return valToRet;
	}
}
