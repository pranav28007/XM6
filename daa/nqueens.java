import java.util.Scanner;

public class nqueens {

    static int N;

    // Function to print board
    static void printBoard(int[][] board) {
        System.out.println("\nSolution:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    // Check if queen can be placed at board[row][col]
    static boolean isSafe(int[][] board, int row, int col) {

        int i, j;

        // Check this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal on left side
        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Solve N-Queens using backtracking
    static boolean solveNQueens(int[][] board, int col) {

        // If all queens are placed
        if (col >= N)
            return true;

        // Try placing queen in all rows of this column
        for (int i = 0; i < N; i++) {

            if (isSafe(board, i, col)) {

                board[i][col] = 1; // Place queen

                // Recur to place rest of queens
                if (solveNQueens(board, col + 1))
                    return true;

                // Backtrack
                board[i][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of queens (N): ");
        N = sc.nextInt();

        int[][] board = new int[N][N];

        if (solveNQueens(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists for " + N + " Queens.");
        }

        sc.close();
    }
}
