package ua.nure.scherbyna.containers;

@SuppressWarnings("serial")
public class ExpertRates extends Coeff{
	private int[][] m_rating = null;
	private String[] m_expertNames = null;
	private String[] m_criterias = null;

	public ExpertRates(double weight, String[] _expertNames, String[] _criteria, int[][] _rating)
	{
		super("Ёкспертные оценки", "балл", weight, -1, -1);
		
		m_rating = _rating;
		m_expertNames = _expertNames;
		m_criterias = _criteria;
		
		m_plan = 3;
		
		double average = 0;
		int ratingSize = m_rating[0].length * m_rating.length;
		for (int i = 0; i < m_rating.length; i++)
			for (int j = 0; j < m_rating[0].length; j++)
				average += m_rating[i][j];
		m_fact = average / ratingSize;
	}

	public String[] getExpertNames() {
		return m_expertNames;
	}

	public String[] getCriterias() {
		return m_criterias;
	}

	public int[][] getRates() {
		return m_rating;
	}
}
