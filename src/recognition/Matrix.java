package recognition;

import java.util.Random;

public class Matrix {
    
    private static final Random r = new Random();
    
    public static double[][] matrify(double[]... arrs) {
        if (arrs.length == 0) {
            throw new IllegalArgumentException("No array was passed as argument.");
        }
        int cols = 1;
        for(double[] arr : arrs) {
            cols = Math.max(cols, arr.length);
        }
        double[][] ans = new double[arrs.length][cols];
        for (int row = 0; row < ans.length; ++row) {
            for (int col = 0; col < ans[row].length; ++col) {
                if (col >= arrs[row].length) {
                    break;
                }
                ans[row][col] = arrs[row][col];
            }
        }
        return ans;
    }
    
    public static double[][] dot(double[][] m1, double[][] m2) {
        if (m1[0].length != m2.length) {
            throw new IllegalArgumentException(
                    "Cols from m1 and Rows from m2 should be equal.");
        }
        double[][] ans = new double[m1.length][m2[0].length];
        for (int i = 0; i < ans.length; ++i) {
            for (int j = 0; j < ans[i].length; ++j) {
                for (int k = 0; k < m2.length; ++k) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return ans;
    }
    
    public static double[][] dot(double n, double[][] m) {
        double[][] ans = new double[m.length][m[0].length];
        for (int row = 0; row < m.length; ++row) {
            for (int col = 0; col < m[row].length; ++col) {
                ans[row][col] = m[row][col] * n;
            }
        }
        return ans;
    }
    
    public static double[][] add(double[][] m1, double[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new IllegalArgumentException("Matrices should have equal dimensions.");
        }
        double[][] ans = new double[m1.length][m1[0].length];
        for (int row = 0; row < m1.length; ++row) {
            for (int col = 0; col < m1[row].length; ++col) {
                ans[row][col] = m1[row][col] + m2[row][col];
            }
        }
        return ans;
    }
    
    public static double[][] subtract(double[][] m1, double[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new IllegalArgumentException("Matrices should have equal dimensions.");
        }
        double[][] ans = new double[m1.length][m1[0].length];
        for (int row = 0; row < m1.length; ++row) {
            for (int col = 0; col < m1[row].length; ++col) {
                ans[row][col] = m1[row][col] - m2[row][col];
            }
        }
        return ans;
    }
    
    public static double[][] transpose(double[][] m) {
        double[][] ans = new double[m[0].length][m.length];
        for (int col = 0; col < m.length; ++col) {
            for (int row = 0; row < m[col].length; ++row) {
                ans[row][col] = m[col][row];
            }
        }
        return ans;
    }
    
    public static void gaussianRand(double[][] m) {
        for (int row = 0; row < m.length; ++row) {
            for (int col = 0; col < m[row].length; ++col) {
                m[row][col] = r.nextGaussian();
            }
        }
    }
    
    public static double[][] identity(int len) {
        double[][] ans = new double[len][len];
        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                if (row == col) {
                    ans[row][col] = 1;
                } else {
                    ans[row][col] = 0;
                }
            }
        }
        return ans;
    }
    
    public static double[][] sigmoid(double[][] m) {
        double[][] ans = new double[m.length][m[0].length];
        for (int row = 0; row < m.length; ++row) {
            for (int col = 0; col < m[row].length; ++col) {
                ans[row][col] = sigmoid(m[row][col]);
            }
        }
        return ans;
    }
    
    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
    
    public static void print(double[][] m) {
        for (double[] doubles : m) {
            System.out.print("[");
            for (int j = 0; j < doubles.length; ++j) {
                System.out.print(doubles[j]);
                if (j + 1 != doubles.length) {
                    System.out.print(",\t");
                }
            }
            System.out.println("]");
        }
    }
    
    public static double[][] addToCol(double[][] m, double[][] mCol, int col) {
        if (m.length != mCol.length) {
            throw new IllegalArgumentException("Rows of both matrices must be equal.");
        }
        if (mCol[0].length > 1) {
            throw new IllegalArgumentException("mCol is not an n by 1 matrix.");
        }
        if (col >= m[0].length) {
            throw new IllegalArgumentException("col argument out of bounds for matrix m.");
        }
        double[][] ans = new double[m.length][m[0].length];
        System.arraycopy(m, 0, ans, 0, m.length);
        for (int i = 0; i < m[0].length; ++i) {
            System.arraycopy(m[i], 0, ans[i], 0, m[i].length);
        }
        for (int row = 0; row < m.length; ++row) {
            ans[row][col] += mCol[row][0];
        }
        return ans;
    }
    
    public static double[][] getCol(double[][] m, int col) {
        double[][] ans = new double[m.length][1];
        for (int row = 0; row < m.length; ++row) {
            ans[row][0] = m[row][col];
        }
        return ans;
    }
    
    public static double[][] getRow(double[][] m, int row) {
        double[][] ans = new double[1][m[row].length];
        for (int col = 0; col < m[row].length; ++col) {
            ans[0][col] = m[row][col];
        }
        return ans;
    }
}
