package ua.nure.shcherbatenko.course5.TSSA.lab2;


public class Rankings {
	
	private static final int[][] R = new int[][] {
		{3,	4,	5,	5,	5,	5},
		{3,	3,	3,	4,	3,	3},
		{3,	1,	2,	3,	2,	1},
		{5,	5,	4,	1,	4,	4},
		{1,	2,	1,	2,	1,	2}
	};
	private static final int S = R.length;
	private static final int E = R[0].length;
	
	public static void main(String[] args) {
		System.out.println("============================ PART 1 ============================");
		System.out.println();
		calculateRanks();
		calculateSpearmanCorrelation();

		System.out.println("============================ PART 2 ============================");
		System.out.println();
	}

	private static void calculateSpearmanCorrelation() {
		double[][] corr = new double[E][E];
		for (int i = 0; i < corr.length; i++) {
			for (int j = 0; j < corr.length; j++) {
				double sum = 0;
				for (int s = 0; s < S; s++)
					sum += Math.abs(R[s][i] - R[s][j]);
				corr[i][j] = 1.0 - (6.0 * sum / (S * (S*S - 1)));
			}
		}
		
		// ======================= OUTPUT ======================= 
		
		System.out.println("======= Expert Spearman correlation coefficients =======");
		System.out.println();
		System.out.print("\t\t");
		for (int i = 0; i < corr.length; i++)
			System.out.print("Exp" + (i+1) + "\t");
		System.out.println();
		
		for (int i = 0; i < corr.length; i++) {
			System.out.print("Exp" + (i+1) + "\t");
			for (int j = 0; j < corr.length; j++) {
				System.out.print(corr[i][j] + "\t\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void calculateRanks() {
		double[] avgRanks = new double[S];
		for (int s = 0; s < S; s++) {
			for (int r = 0; r < E; r++) {
				avgRanks[s] += R[s][r];
			}
			avgRanks[s] /= E;
		}
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

		// ======================= OUTPUT ======================= 
		System.out.println("=============== Ranking ===============");
		System.out.println();
		System.out.println("System\t\tRank\tAvg. Rank");
		for (int s = 0; s < S; s++)
			System.out.println("Sys" + (s+1) + "\t\t" + ranks[s] + "\t\t" + avgRanks[s]);
		System.out.println(System.lineSeparator());
	}
	
}