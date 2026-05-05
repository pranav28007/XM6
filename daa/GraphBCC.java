import java.util.*;

public class GraphBCC {

    static class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public String toString() {
            return "(" + u + "," + v + ")";
        }
    }

    private int V;
    private ArrayList<Integer>[] adj;
    private int time;

    private int[] disc, low, parent;
    private boolean[] visited;
    private boolean[] articulation;
    private Stack<Edge> edgeStack;

    GraphBCC(int V) {
        this.V = V;
        adj = new ArrayList[V];

        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();

        disc = new int[V];
        low = new int[V];
        parent = new int[V];
        visited = new boolean[V];
        articulation = new boolean[V];
        edgeStack = new Stack<>();

        Arrays.fill(parent, -1);
        time = 0;
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    void DFS(int u) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        int children = 0;

        for (int v : adj[u]) {

            if (!visited[v]) {
                children++;
                parent[v] = u;

                edgeStack.push(new Edge(u, v));

                DFS(v);

                low[u] = Math.min(low[u], low[v]);

                // Articulation Point Condition
                if ((parent[u] == -1 && children > 1) ||
                        (parent[u] != -1 && low[v] >= disc[u])) {

                    articulation[u] = true;

                    // Print Bi-connected Component
                    System.out.print("Biconnected Component: ");
                    while (!edgeStack.isEmpty()) {
                        Edge e = edgeStack.pop();
                        System.out.print(e + " ");

                        if (e.u == u && e.v == v)
                            break;
                    }
                    System.out.println();
                }
            }

            // Back Edge Case
            else if (v != parent[u] && disc[v] < disc[u]) {
                low[u] = Math.min(low[u], disc[v]);
                edgeStack.push(new Edge(u, v));
            }
        }
    }

    void findArticulationPointsAndBCC() {

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS(i);

                // Remaining edges in stack form a BCC
                if (!edgeStack.isEmpty()) {
                    System.out.print("Biconnected Component: ");
                    while (!edgeStack.isEmpty()) {
                        System.out.print(edgeStack.pop() + " ");
                    }
                    System.out.println();
                }
            }
        }

        System.out.println("\nArticulation Points are:");
        boolean found = false;

        for (int i = 0; i < V; i++) {
            if (articulation[i]) {
                System.out.print(i + " ");
                found = true;
            }
        }

        if (!found)
            System.out.println("No articulation points.");
        else
            System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        GraphBCC g = new GraphBCC(V);

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }

        System.out.println("\n--- Output ---");
        g.findArticulationPointsAndBCC();

        sc.close();
    }
}
