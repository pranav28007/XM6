import java.util.Scanner;

public class optimal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of identifiers (n): ");
        int n = sc.nextInt();

        double[] p = new double[n + 1]; // 1..n
        double[] q = new double[n + 1]; // 0..n

        System.out.println("Enter successful search probabilities p[1..n]:");
        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextDouble();
        }

        System.out.println("Enter unsuccessful search probabilities q[0..n]:");
        for (int i = 0; i <= n; i++) {
            q[i] = sc.nextDouble();
        }

        double[][] w = new double[n + 1][n + 1];
        double[][] c = new double[n + 1][n + 1];
        int[][] r = new int[n + 1][n + 1];

        // Initialization
        for (int i = 0; i <= n; i++) {
            w[i][i] = q[i];
            c[i][i] = 0;
            r[i][i] = 0;
        }

        // DP Computation
        for (int length = 1; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length;

                w[i][j] = w[i][j - 1] + p[j] + q[j];

                c[i][j] = Double.MAX_VALUE;

                for (int k = i + 1; k <= j; k++) {
                    double cost = c[i][k - 1] + c[k][j] + w[i][j];

                    if (cost < c[i][j]) {
                        c[i][j] = cost;
                        r[i][j] = k;
                    }
                }
            }
        }

        // Output
        System.out.println("\nWeight Matrix (w):");
        printMatrix(w, n);

        System.out.println("\nCost Matrix (c):");
        printMatrix(c, n);

        System.out.println("\nRoot Matrix (r):");
        printRootMatrix(r, n);

        System.out.println("\nMinimum Cost of Optimal BST = " + c[0][n]);
        System.out.println("Root of OBST = Key " + r[0][n]);

        sc.close();
    }

    // Print double matrix
    static void printMatrix(double[][] mat, int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%8.2f ", mat[i][j]);
            }
            System.out.println();
        }
    }

    // Print root matrix
    static void printRootMatrix(int[][] mat, int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%4d ", mat[i][j]);
            }
            System.out.println();
        }
    }
}
