import java.util.Scanner;

public class subset {

    static int[] set;
    static int n, d;
    static boolean found = false;

    // Function to print subset
    static void printSubset(int[] subset, int size) {
        System.out.print("{ ");
        for (int i = 0; i < size; i++) {
            System.out.print(subset[i] + " ");
        }
        System.out.println("}");
    }

    // Backtracking function
    static void subsetSum(int index, int currentSum, int[] subset, int subsetSize) {

        // If sum equals target
        if (currentSum == d) {
            found = true;
            printSubset(subset, subsetSize);
            return;
        }

        // If index out of range or sum exceeds target
        if (index == n || currentSum > d) {
            return;
        }

        // Include current element
        subset[subsetSize] = set[index];
        subsetSum(index + 1, currentSum + set[index], subset, subsetSize + 1);

        // Exclude current element
        subsetSum(index + 1, currentSum, subset, subsetSize);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        n = sc.nextInt();

        set = new int[n];

        System.out.println("Enter elements of set:");
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
        }

        System.out.print("Enter target sum (d): ");
        d = sc.nextInt();

        int[] subset = new int[n];

        System.out.println("Subsets with sum " + d + " are:");
        subsetSum(0, 0, subset, 0);

        if (!found) {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}
