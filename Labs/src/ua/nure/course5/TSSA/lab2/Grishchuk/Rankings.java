package ua.nure.course5.TSSA.lab2.Grishchuk;


public class Rankings {

	public static double[][] calculateSpearmanCorrelationMtx(double[][] groupRanks) {
		int systems = groupRanks.length;
		int experts = groupRanks[0].length;
		double[][] correlation = new double[experts][experts];

		for (int i = 0; i < correlation.length; i++) {
			for (int j = 0; j < correlation.length; j++) {
				double sum = 0;
				for (int s = 0; s < systems; s++)
					sum += Math.abs(groupRanks[s][i] - groupRanks[s][j]);
				correlation[i][j] = 1D - (6.0 * sum / (systems * (systems * systems - 1)));
			}
		}
		return correlation;
	}

	public static double[] calculateAvgRanks(double[][] groupRanks) {
		int systems = groupRanks.length;
		int experts = groupRanks[0].length;
		double[] avgRanks = new double[systems];

		for (int s = 0; s < systems; s++) {
			for (int r = 0; r < experts; r++) {
				avgRanks[s] += groupRanks[s][r];
			}
			avgRanks[s] /= experts;
		}
		return avgRanks;
	}

	public static int[] calculateNewRanks(double[] avgRanks) {
		int[] ranks = new int[avgRanks.length];

		for (int r = 1; r <= avgRanks.length; r++) {
			double min = Double.MAX_VALUE;
			int minIdx = 0;
			for (int avgR = 0; avgR < avgRanks.length; avgR++) {
				if (avgRanks[avgR] < min && ranks[avgR] == 0) {
					minIdx = avgR;
					min = avgRanks[avgR];
				}
			}
			ranks[minIdx] = r;
		}
		return ranks;
	}

}
