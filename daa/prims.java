import java.util.Scanner;

public class prims {

    static final int INF = 99999;

    // Function to find minimum key vertex
    static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Function to print MST
    static void printMST(int[] parent, int[][] graph, int V) {
        int totalCost = 0;

        System.out.println("\nEdges in Minimum Cost Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " -- " + i + " == " + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }

        System.out.println("\nMinimum Cost of MST = " + totalCost);
    }

    // Prim's Algorithm
    static void primMST(int[][] graph, int V) {

        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        // Initialize all keys as infinite
        for (int i = 0; i < V; i++) {
            key[i] = INF;
            mstSet[i] = false;
        }

        // Start from first vertex
        key[0] = 0;
        parent[0] = -1;

        // MST will have V vertices
        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            // Update key values of adjacent vertices
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph, V);
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix (0 if no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        primMST(graph, V);

        sc.close();
    }
}
