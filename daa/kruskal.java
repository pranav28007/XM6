import java.util.*;

class kruskal {

    // Edge class
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        // Sort edges by weight
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    // Disjoint Set (Union-Find)
    static class DisjointSet {
        int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Find with path compression
        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        // Union by rank
        void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot)
                return;

            if (rank[xRoot] < rank[yRoot])
                parent[xRoot] = yRoot;
            else if (rank[xRoot] > rank[yRoot])
                parent[yRoot] = xRoot;
            else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Edge[] edges = new Edge[E];

        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(s, d, w);
        }

        // Sort edges
        Arrays.sort(edges);

        DisjointSet ds = new DisjointSet(V);

        int mstCost = 0;
        int edgeCount = 0;

        System.out.println("\nEdges in Minimum Cost Spanning Tree:");

        for (int i = 0; i < E && edgeCount < V - 1; i++) {
            Edge current = edges[i];

            int x = ds.find(current.src);
            int y = ds.find(current.dest);

            // If no cycle, include edge
            if (x != y) {
                System.out.println(current.src + " -- " + current.dest + " == " + current.weight);
                mstCost += current.weight;
                edgeCount++;

                ds.union(x, y);
            }
        }

        System.out.println("\nMinimum Cost of MST = " + mstCost);

        sc.close();
    }
}
