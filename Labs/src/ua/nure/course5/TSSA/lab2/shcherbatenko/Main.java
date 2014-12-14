package ua.nure.course5.TSSA.lab2.Shcherbatenko;

import static ua.nure.course5.TSSA.lab2.Shcherbatenko.PairComparing.*;
import static ua.nure.course5.TSSA.lab2.Shcherbatenko.Rankings.*;

/**
 * Created by Volodymyr on 12/12/2014.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("============================ PART 1 ============================\n");

        double[][] groupRanks = new double[][]{
                {3, 4, 5, 5, 5, 5},
                {3, 3, 3, 4, 3, 3},
                {3, 1, 2, 3, 2, 1},
                {5, 5, 4, 1, 4, 4},
                {1, 2, 1, 2, 1, 2}
        };
        double[] avgRanks = calculateAvgRanks(groupRanks);
        int[] ranks = calculateNewRanks(avgRanks);
        double[][] correlation = calculateSpearmanCorrelationMtx(groupRanks);

        System.out.println("=============== Ranking ===============\n");
        printRanks(groupRanks, avgRanks, ranks);
        printMatrix(correlation, "Exp", "Exp", "======= Expert Spearman correlation coefficients =======");

        System.out.println("============================ PART 2 ============================\n");

        double[][] pairComparings = new double[][] {
                {1.0,	3.0,	1.0,	6.0,	1D/7},
                {0.0,	1.0,	1D/3,	1D/2,	1D/9},	// <======= !!!
                {0.0,	0.0,	1.0,	8.0,	1D/4},
                {0.0,	0.0,	0.0,	1.0,	1D/9},
                {0.0,	0.0,	0.0,	0.0,	1.0}
        };

        double[][] brokenPairComparings = new double[][] {
                {1.0,	3.0,	1.0,	6.0,	1D/7},
                {0.0,	1.0,	1D/3,	1D/2,	9},		// <======= !!!
                {0.0,	0.0,	1.0,	8.0,	1D/4},
                {0.0,	0.0,	0.0,	1.0,	1D/9},
                {0.0,	0.0,	0.0,	0.0,	1.0}
        };
        calculatePairComparingMatrix(pairComparings, "======= INPUT  pair comparing matrix =======", true);
        calculatePairComparingMatrix(brokenPairComparings, "======= BROKEN pair comparing matrix =======", true);
    }

    private static void calculatePairComparingMatrix(double[][] mtx, String message, boolean correction) {
        fillLowerTriangleMatrix(mtx);
        double[][] r = generateRandomTriangleMtx(mtx.length);
        fillLowerTriangleMatrix(r);
        double ratio = 1;

        System.out.println(message);

        do {
            double[] v = getV(mtx);
            double[] p = getP(mtx, v);
            double lambda = getLambda(mtx, p);

            double[] vr = getV(r);
            double[] pr = getP(r, vr);
            double lambda1 = getLambda(r, pr);
            ratio = normalize(lambda, mtx.length) / normalize(lambda1, r.length);

            printPairMatrix(mtx, p, v);
            System.out.println("Lambda input : " + lambda);
            System.out.println("Lambda random: " + lambda1);
            System.out.println("Ratio: " + ratio);
            System.out.println();

            if (correction && ratio > 0.1) {
                System.out.println("========= CORRECTING ========");
                correctMatrix(mtx, p);
            }
        } while (correction && ratio > 0.1);
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
            System.out.print("Sys_" + (i+1) + "\t");
        System.out.print("V" + "\t\t");
        System.out.print("P");
        System.out.println();

        for (int i = 0; i < mtx.length; i++) {
            System.out.print("Sys_" + (i+1) + "\t");
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
