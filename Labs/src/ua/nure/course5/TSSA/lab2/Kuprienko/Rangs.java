package ua.nure.course5.TSSA.lab2.Kuprienko;


public class Rangs {

	public static double[][] spearman(double[][] groupRanks) {
		int systems = groupRanks.length;
		int experts = groupRanks[0].length;
		double[][] corr = new double[experts][experts];

		for (int i = 0; i < corr.length; i++) {
			for (int j = 0; j < corr.length; j++) {
				double sum = 0;
				for (int s = 0; s < systems; s++)
					sum += Math.abs(groupRanks[s][i] - groupRanks[s][j]);
				corr[i][j] = 1D - (6.0 * sum / (systems * (systems * systems - 1)));
			}
		}
		return corr;
	}

	public static double[] deviation(double[][] mtx, double[] avg) {
		double[] dev = new double[mtx[0].length];
		for (int i = 0; i < mtx[0].length; i++) {
			dev[i] = 0;
			for (int j = 0; j < mtx.length; j++) {
				dev[i] += Math.pow(mtx[j][i]-avg[j], 2);
			}
			dev[i] = Math.sqrt(dev[i]/mtx[0].length);
		}
		return dev;
	}

	public static double[] srednRangi(double[][] groupRanks) {
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

	public static int[] novieRangi(double[] avgRanks) {
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
