import java.util.Scanner;

public class knap {

    // Function to solve 0/1 Knapsack problem
    static int knapsack(int W, int[] wt, int[] val, int n) {

        int[][] dp = new int[n + 1][W + 1];

        // Build dp table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]],
                                        dp[i - 1][w]);

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] wt = new int[n];
        int[] val = new int[n];

        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        System.out.println("Enter values of items:");
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();

        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        int maxProfit = knapsack(W, wt, val, n);

        System.out.println("Maximum Profit = " + maxProfit);

        sc.close();
    }
}
