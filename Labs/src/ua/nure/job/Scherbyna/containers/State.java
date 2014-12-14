package ua.nure.job.Scherbyna.containers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class State implements Serializable {
	Map<Integer, Worker> m_workers = new TreeMap<>(); // wID - Worker
	Map<Integer, Coeff> m_coeffs = new TreeMap<>(); // cID - Coeff
	Map<Integer, ArrayList<Integer>> m_workerCoeffsIDsMap = new TreeMap<>(); //wID - List<cID>
	public Map<Integer, Integer> m_workerExpertsCoeffsIDsMap = new TreeMap<>(); // wID - cID (ExpertsCoeff)
	Map<Integer, ExpertRates> m_workerExpertsCoeffsMap = new TreeMap<>(); // wID - ExpertsCoeff
	
	int maxWorkerID = 0;
	int maxCoeffID = 0;

	public Map<Integer, Coeff> getWorkerCoeffs(int _wID)
	{
		Map<Integer, Coeff> keys = new TreeMap<Integer, Coeff>();
		ArrayList<Integer> kIDs = m_workerCoeffsIDsMap.get(_wID);
		
		for (Integer ID : kIDs)
			keys.put(ID, m_coeffs.get(ID));
		
		return keys;
	}
	
	public Map<Integer, Worker> getWorkers()
	{
		return m_workers;
	}
	
	public Worker getWorker(int _wID)
	{
		return m_workers.get(_wID);
	}
	
	public void updateWorker(int _wID, Worker _worker)
	{
		if (_wID < 0)
		{
			m_workers.put(maxWorkerID, _worker);
			m_workerCoeffsIDsMap.put(maxWorkerID, new ArrayList<Integer>());
			maxWorkerID++;
		} else {
			m_workers.put(_wID, _worker);
		}
	}
	
	public int updateCoeff(int _wID, int _kID, Coeff _coeff)
	{
		System.out.println("State.updateCoeff() " + _kID + " " + _coeff.getName() + " " + _coeff.m_weight);
		if (_kID < 0)
		{
			m_coeffs.put(maxCoeffID, _coeff);
			m_workerCoeffsIDsMap.get(_wID).add(maxCoeffID);
			return maxCoeffID++;
		} else {
			m_coeffs.put(_kID, _coeff);
			return _kID;
		}
	}

	public void updateExpertRates(int _wID, ExpertRates _expRates) {
		int oldExpCoeffsID = (m_workerExpertsCoeffsIDsMap.containsKey(_wID)) ?
				m_workerExpertsCoeffsIDsMap.get(_wID) :
				-1;
				
		if (m_workerExpertsCoeffsIDsMap.containsKey(_wID))
			_expRates.m_weight = m_coeffs.get(m_workerExpertsCoeffsIDsMap.get(_wID)).m_weight;
		
		int newExpCoeffsID = updateCoeff(_wID, oldExpCoeffsID, _expRates);
		System.out.println("State.updateExpertRates() " + newExpCoeffsID);

		m_workerExpertsCoeffsIDsMap.put(_wID, newExpCoeffsID);
		
		m_workerExpertsCoeffsMap.put(_wID, _expRates);
	}
	
	public ExpertRates getExpertRates(int _wID)
	{
		return m_workerExpertsCoeffsMap.get(_wID);
	}

	public boolean removeCoeff(Integer _wID, Integer _cID)
	{
		if (_wID == null || _cID == null)
			return false;
		
		m_coeffs.remove(_cID);
		m_workerCoeffsIDsMap.get(_wID).remove(new Integer(_cID));
		if (m_workerExpertsCoeffsIDsMap.get(_wID) == _cID)
		{
			m_workerExpertsCoeffsIDsMap.remove(_wID);
			m_workerExpertsCoeffsMap.remove(_wID);
		}
		
		return true;
	}

	public void removeWorker(int _wID)
	{
		m_workers.remove(_wID);
		ArrayList<Integer> coeffsIDs = m_workerCoeffsIDsMap.remove(_wID);
		for (Integer cID : coeffsIDs)
			m_coeffs.remove(cID);
		
		if (m_workerExpertsCoeffsIDsMap.remove(_wID) != null)
			m_workerExpertsCoeffsMap.remove(_wID);
	}
}
