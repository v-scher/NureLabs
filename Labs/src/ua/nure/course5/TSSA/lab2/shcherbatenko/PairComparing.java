package ua.nure.course5.TSSA.lab2.Shcherbatenko;

import java.util.Random;

/**
 * Created by Volodymyr on 12/12/2014.
 */
public class PairComparing {

    public static double[][] generateRandomTriangleMtx(int n) {
        double[][] r = new double[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            r[i][i] = 1D;
            for (int j = i+1; j < n; j++) {
                double k = 1.0 + random.nextInt(9);
                if (random.nextBoolean())
                    k = 1/k;
                r[i][j] = k;
            }
        }
        return r;
    }

    public static double normalize(double lambda, int n) {
        return (lambda - n) / (n - 1);
    }

    public static double[] getV(double[][] a) {
        double[] v = new double[a.length];

        for (int i = 0; i < v.length; i++) {
            double m = 1.0;
            for (int j = 0; j < v.length; j++)
                m *= a[i][j];
            v[i] = Math.pow(m, 1.0/v.length);
        }
        return v;
    }

    public static double[] getP(double[][] a, double[] v) {
        double[] p = new double[a.length];

        double vSum = 0;
        for (int i = 0; i < v.length; i++)
            vSum += v[i];
        for (int i = 0; i < v.length; i++) {
            p[i] = v[i] / vSum;
        }
        return p;
    }

    public static double getLambda(double[][] a, double[] p) {
        double lambda = 0;
        for (int i = 0; i < p.length; i++) {
            double colSum = 0;
            for (int j = 0; j < p.length; j++) {
                colSum += a[j][i];
            }
            lambda += colSum * p[i];
        }
        return lambda;
    }

    public static void fillLowerTriangleMatrix(double[][] p) {
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p[0].length; j++) {
                p[j][i] = 1/p[i][j];
            }
        }
    }

    public static void correctMatrix(double[][] mtx, double[] p) {
        int maxRow = 0;
        double maxS = 0;
        for (int row = 0; row< mtx.length; row++) {
            double S = 0;
            for (int col = row + 1; col < mtx.length; col++)
                S += Math.pow(mtx[row][col] - p[row]/p[col], 2);
            if (S > maxS) {
                maxRow = row;
                maxS = S;
            }
        }
        for (int col = 0; col < mtx.length; col++)
            mtx[maxRow][col] = p[maxRow]/p[col];

        fillLowerTriangleMatrix(mtx);
    }

}
