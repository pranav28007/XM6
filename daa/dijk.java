import java.util.Scanner;

public class dijk {

    static final int INF = 99999;

    // Function to find vertex with minimum distance
    static int minDistance(int[] dist, boolean[] visited, int V) {
        int min = INF, minIndex = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Function to implement Dijkstra Algorithm
    static void dijkstra(int[][] graph, int src, int V) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        // Initialize distances
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            visited[i] = false;
        }

        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited, V);
            visited[u] = true;

            // Update distance of adjacent vertices
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != INF &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print result
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter adjacency matrix (0 if no edge): ");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        dijkstra(graph, src, V);

        sc.close();
    }
}
