package ua.nure.course5.TSSA.lab2.Grishchuk;

import static ua.nure.course5.TSSA.lab2.Grishchuk.PairComparing.*;

public class TSSA_lab2 {

    private static double[][] mtx1 = new double[][] {
            {1.0,	3.0,	1.0,	6.0,	1D/7},
            {0.0,	1.0,	1D/3,	1D/2,	1D/9},
            {0.0,	0.0,	1.0,	8.0,	1D/4},
            {0.0,	0.0,	0.0,	1.0,	1D/9},
            {0.0,	0.0,	0.0,	0.0,	1.0}
    };

    private static double[][] mtx2 = new double[][] {
            {1.0,	8.0,	1.0,	6.0,	1D/7},
            {0.0,	1.0,	1D/3,	1D/2,	1D/9},
            {0.0,	0.0,	1.0,	3.0,	1D/4},
            {0.0,	0.0,	0.0,	1.0,	1D/9},
            {0.0,	0.0,	0.0,	0.0,	1.0}
    };

    private static double[][] rates = new double[][] {
            {3, 1, 2, 3, 2, 1},
            {1, 2, 1, 2, 1, 2},
            {3, 4, 5, 5, 5, 5},
            {5, 5, 4, 1, 4, 4},
            {3, 3, 3, 4, 3, 3}
    };
    static void part1() {
        System.out.println("Ranking:\n");

        int S = rates.length;
        int E = rates[0].length;
        double[] avgRates = new double[S];

        for (int s = 0; s < S; s++) {
            for (int r = 0; r < E; r++) {
                avgRates[s] += rates[s][r];
            }
            avgRates[s] /= E;
        }

        int[] ranks = new int[avgRates.length];

        for (int r = 1; r <= avgRates.length; r++) {
            double min = Double.MAX_VALUE;
            int minIdx = 0;
            for (int avgR = 0; avgR < avgRates.length; avgR++) {
                if (avgRates[avgR] < min && ranks[avgR] == 0) {
                    minIdx = avgR;
                    min = avgRates[avgR];
                }
            }
            ranks[minIdx] = r;
        }

        double[][] spearman = new double[E][E];

        for (int i = 0; i < spearman.length; i++) {
            for (int j = 0; j < spearman.length; j++) {
                double sum = 0;
                for (int s = 0; s < S; s++)
                    sum += Math.abs(rates[s][i] - rates[s][j]);
                spearman[i][j] = 1D - (6.0 * sum / (S * (S * S - 1)));
            }
        }

        System.out.println("S    \t\tRank\tAverage");
        for (int s = 0; s < rates.length; s++)
            System.out.println("S    " + (s+1) + "\t\t" + ranks[s] + "\t\t" + avgRates[s]);
        System.out.println(System.lineSeparator());


        System.out.println("Spearman coeffs:");
        System.out.print("\t\t");
        for (int i = 0; i < spearman.length; i++)
            System.out.print("Exp" + (i+1) + "\t");
        System.out.println();

        for (int i = 0; i < spearman.length; i++) {
            System.out.print("Exp" + (i+1) + "\t");
            for (int j = 0; j < spearman.length; j++) {
                String val = Double.toString(spearman[i][j]);
                String tab = "\t\t";
                if (val.length() > 4) {
                    val = val.substring(0, 4);
                    tab = tab.substring(0, 1);
                }
                System.out.print(val + tab);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void part2() {
        System.out.println("First matrix");
        findPriorities(mtx1);
        System.out.println("Second matrix");
        findPriorities(mtx2);
    }

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void findPriorities(double[][] pairComparings) {
        fillLowerTriangleMatrix(pairComparings);
        double[][] r = generateRandomTriangleMtx(pairComparings.length);
        fillLowerTriangleMatrix(r);
        double ratio = 1;

        do {
            double[] v = getV(pairComparings);
            double[] p = getP(pairComparings, v);
            double lambda = getLambda(pairComparings, p);

            double[] vr = getV(r);
            double[] pr = getP(r, vr);
            double lambda1 = getLambda(r, pr);
            ratio = normalize(lambda, pairComparings.length) / normalize(lambda1, r.length);

            printPairMatrix(pairComparings, p, v);
            System.out.println("Відношення індексів: " + ratio);
            System.out.println();

            if (ratio > 0.1) {
                System.out.println("Correcting:");
                correctMatrix(pairComparings, p);
            }
        } while (ratio > 0.1);
    }

    public static void printRanks(double[][] groupRanks, double[] avgRanks, int[] ranks) {
        System.out.println("System\t\tRank\tAvg. Rank");
        for (int s = 0; s < groupRanks.length; s++)
            System.out.println("Sys" + (s+1) + "\t\t" + ranks[s] + "\t\t" + avgRanks[s]);
        System.out.println(System.lineSeparator());
    }

    public static void printMatrix(
            double[][] corr,
            String colNamePrefix,
            String rowNamePrefix,
            String leadingMessage)
    {
        int colNameSuffixOffset = 1;
        int rowNameSuffixOffset = 1;
        String nameTab = "\t";
        String dataTab = "\t\t";

        System.out.println(leadingMessage);

        System.out.print(dataTab);
        for (int i = 0; i < corr.length; i++)
            System.out.print(colNamePrefix + (i+colNameSuffixOffset) + nameTab);
        System.out.println();

        for (int i = 0; i < corr.length; i++) {
            System.out.print(rowNamePrefix + (i+rowNameSuffixOffset) + nameTab);
            for (int j = 0; j < corr.length; j++) {
                String val = Double.toString(corr[i][j]);
                String tab = dataTab;
                if (val.length() > 4) {
                    val = val.substring(0, 4);
                    tab = tab.substring(0, 1);
                }
                System.out.print(val + tab);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printPairMatrix(
            double[][] mtx,
            double[] p,
            double[] v)
    {
        System.out.print("\t\t");
        for (int i = 0; i < mtx.length; i++)
            System.out.print("S" + (i+1) + "   \t");
        System.out.print("V" + "\t\t");
        System.out.print("P");
        System.out.println();

        for (int i = 0; i < mtx.length; i++) {
            System.out.print("S" + (i+1) + "   \t");
            for (int j = 0; j < mtx.length; j++) {
                String val = Double.toString(mtx[i][j]);
                String tab = "\t\t";
                if (val.length() > 4) {
                    val = val.substring(0, 4);
                    tab = tab.substring(0, 1);
                }
                System.out.print(val + tab);
            }
            String val = Double.toString(v[i]);
            String tab = "\t\t";
            if (val.length() > 4) {
                val = val.substring(0, 4);
                tab = tab.substring(0, 1);
            }
            System.out.print(val + tab);

            val = Double.toString(p[i]);
            tab = "\t\t";
            if (val.length() > 4) {
                val = val.substring(0, 4);
                tab = tab.substring(0, 1);
            }
            System.out.print(val + tab);
            System.out.println();
        }
        System.out.println();
    }

}
