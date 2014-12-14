package ua.nure.course5.TSSA.lab2.Kuprienko;

import static ua.nure.course5.TSSA.lab2.Kuprienko.Rangs.*;
import static ua.nure.course5.TSSA.lab2.Kuprienko.Pair.*;

/**
 * Created by Volodymyr on 12/12/2014.
 */
public class Main {
    private static final double[][] data = new double[][]{
            {2.5, 1.5, 3, 2, 3},
            {7, 6, 7, 7, 7},
            {1, 1.5, 2, 2, 1},
            {2.5, 3, 1, 2, 2},
            {6, 7, 6, 6, 6},
            {4.5, 4, 4, 4, 4},
            {4.5, 5, 5, 5, 5},
            {10, 10, 10, 10, 10},
            {8, 8, 8, 8, 8},
            {9, 9, 9, 9, 9}
    };
    private static final double[][] m1 = new double[][] {
            {1.0,	3.0,	1.0,	6.0,	1D/7},
            {0.0,	1.0,	1D/3,	1D/2,	1D/9},
            {0.0,	0.0,	1.0,	8.0,	1D/4},
            {0.0,	0.0,	0.0,	1.0,	1D/9},
            {0.0,	0.0,	0.0,	0.0,	1.0}
    };
    private static final double[][] m2 = new double[][] {
            {1.0,	3.0,	1.0,	6.0,	1D/7},
            {0.0,	1.0,	1D/3,	1D/2,	9},
            {0.0,	0.0,	1.0,	8.0,	1D/4},
            {0.0,	0.0,	0.0,	1.0,	1D/9},
            {0.0,	0.0,	0.0,	0.0,	1.0}
    };

    public static void main(String[] args) {
        double[] srednieRangi = srednRangi(data);
        int[] newRanks = novieRangi(srednieRangi);
        double[][] correlation = spearman(data);
        double[] dev = deviation(data, srednieRangi);

        System.out.println("Ранжирование: \n");
        printColumn(newRanks, "Сист.", "Ранг", "Сист.");
        printColumn(dev, "Эксп.", "Отклонение", "Эксп.");
        printMatrix(correlation, "Эксп", "Эксп", "Спирман:");
        calcMatrix(m1, "Матрица попарных сравнений 1:");
        calcMatrix(m2, "Матрица попарных сравнений 2");
    }

    private static void calcMatrix(double[][] mtx, String message) {
        fillLowerTriangleMatrix(mtx);
        double[][] r = generateRandomTriangleMtx(mtx.length);
        fillLowerTriangleMatrix(r);
        double OS;

        System.out.println(message);

        do {
            double[] v = getV(mtx);
            double[] p = getP(mtx, v);
            double lambda = getLambda(mtx, p);

            double[] vr = getV(r);
            double[] pr = getP(r, vr);
            double lambda1 = getLambda(r, pr);

            OS = ((lambda - mtx.length) / (mtx.length - 1))
                    / ((lambda1 - r.length) / (r.length - 1));

            printPairMatrix(mtx, p, v);
            System.out.println("Отношение совместимости: " + OS);
            System.out.println();

            if (OS > 0.1) {
                System.out.println("Корректировака");
                correctMatrix(mtx, p);
            }
        } while (OS > 0.1);
    }

    public static void printColumn(int[] data, String h1, String h2, String row) {
        System.out.println(h1 + "\t\t" + h2);
        for (int s = 0; s < data.length; s++)
            System.out.println(row + (s+1) + "\t\t" + data[s]);
        System.out.println(System.lineSeparator());
    }

    public static void printColumn(double[] data, String h1, String h2, String row) {
        System.out.println(h1 + "\t\t" + h2);
        for (int s = 0; s < data.length; s++)
            System.out.println(row + (s+1) + "\t\t" + data[s]);
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
            System.out.print("Сист" + (i+1) + "\t");
        System.out.print("V" + "\t\t");
        System.out.print("Приоритеты");
        System.out.println();

        for (int i = 0; i < mtx.length; i++) {
            System.out.print("Сист" + (i+1) + "\t");
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
