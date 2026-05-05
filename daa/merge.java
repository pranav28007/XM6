import java.util.Scanner;

public class merge {

    // Merge function
    static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data into temporary arrays
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        // Merge the arrays
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Merge Sort function
    static void mergeSort(int[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            // Sort first half
            mergeSort(arr, left, mid);

            // Sort second half
            mergeSort(arr, mid + 1, right);

            // Merge both halves
            merge(arr, left, mid, right);
        }
    }

    // Print array function
    static void printArray(int[] arr) {
        for (int x : arr)
            System.out.print(x + " ");
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        mergeSort(arr, 0, n - 1);

        System.out.println("Sorted list:");
        printArray(arr);

        sc.close();
    }
}
