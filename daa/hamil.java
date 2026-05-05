import java.util.Scanner;

public class hamil {

    static int V;
    static int[] path;

    // Function to check if vertex v can be added at position pos
    static boolean isSafe(int v, int[][] graph, int[] path, int pos) {

        // Check if this vertex is adjacent to previous vertex
        if (graph[path[pos - 1]][v] == 0)
            return false;

        // Check if vertex already exists in path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v)
                return false;
        }

        return true;
    }

    // Recursive utility function to solve Hamiltonian Cycle
    static boolean hamCycleUtil(int[][] graph, int[] path, int pos) {

        // If all vertices are included in path
        if (pos == V) {
            // Check if last vertex is connected to first vertex
            return graph[path[pos - 1]][path[0]] == 1;
        }

        // Try different vertices as next candidate
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;

                if (hamCycleUtil(graph, path, pos + 1))
                    return true;

                // Backtrack
                path[pos] = -1;
            }
        }

        return false;
    }

    // Main function to solve Hamiltonian Cycle problem
    static void hamCycle(int[][] graph) {

        path = new int[V];

        // Initialize all positions as -1
        for (int i = 0; i < V; i++)
            path[i] = -1;

        // Start from vertex 0
        path[0] = 0;

        if (!hamCycleUtil(graph, path, 1)) {
            System.out.println("No Hamiltonian Cycle exists.");
            return;
        }

        printSolution(path);
    }

    // Print Hamiltonian Cycle
    static void printSolution(int[] path) {
        System.out.println("Hamiltonian Cycle exists:");
        for (int i = 0; i < V; i++)
            System.out.print(path[i] + " ");

        // Print starting vertex again to show cycle
        System.out.println(path[0]);
    }

    // Driver Code
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        hamCycle(graph);

        sc.close();
    }
}
