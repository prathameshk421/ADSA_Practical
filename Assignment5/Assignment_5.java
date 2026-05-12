import java.util.*;

class Graph {

    int V;
    String[] cities;

    ArrayList<ArrayList<Pair>> graph;

    HashSet<String> edges;

    class Pair {

        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    class Edge {

        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public void get_input() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of cities:");
        V = sc.nextInt();

        System.out.println("Enter number of paths:");
        int e = sc.nextInt();

        int maxEdges = V * (V - 1) / 2;

        if (e > maxEdges) {
            System.out.println("Too many edges! Max = " + maxEdges);
            return;
        }

        cities = new String[V];

        graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        edges = new HashSet<>();

        System.out.println("Enter city names:");

        for (int i = 0; i < V; i++) {
            cities[i] = sc.next();
        }

        System.out.println("\nCity Index Mapping:");

        for (int i = 0; i < V; i++) {
            System.out.println(i + " -> " + cities[i]);
        }

        System.out.println("\nEnter edges (index1 index2 distance):");

        for (int i = 0; i < e; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (u < 0 || u >= V || v < 0 || v >= V) {
                System.out.println("Invalid index! Try again.");
                i--;
                continue;
            }

            if (u == v) {
                System.out.println("Self loop not allowed!");
                i--;
                continue;
            }

            String edge1 = u + "-" + v;
            String edge2 = v + "-" + u;

            if (edges.contains(edge1) || edges.contains(edge2)) {
                System.out.println("Edge already exists!");
                i--;
                continue;
            }

            graph.get(u).add(new Pair(v, w));
            graph.get(v).add(new Pair(u, w));

            edges.add(edge1);
            edges.add(edge2);
        }
    }

    public void display() {

        System.out.println("\nAdjacency List:");

        for (int i = 0; i < V; i++) {

            System.out.print(cities[i] + " -> ");

            for (Pair p : graph.get(i)) {

                System.out.print(
                        cities[p.vertex] + "(" + p.weight + ") "
                );
            }

            System.out.println();
        }
    }

    public void prims() {

        boolean[] visited = new boolean[V];

        int[] parent = new int[V];
        int[] key = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

        key[0] = 0;
        parent[0] = -1;

        pq.add(new Pair(0, 0));

        int total = 0;

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int u = curr.vertex;

            if (visited[u]) continue;

            visited[u] = true;

            total += curr.weight;

            for (Pair nbr : graph.get(u)) {

                int v = nbr.vertex;
                int w = nbr.weight;

                if (!visited[v] && w < key[v]) {

                    key[v] = w;
                    parent[v] = u;

                    pq.add(new Pair(v, key[v]));
                }
            }
        }

        for (int i = 0; i < V; i++) {

            if (!visited[i]) {
                System.out.println("Graph is disconnected!");
                return;
            }
        }

        System.out.println("\nPrim's MST:");

        for (int i = 1; i < V; i++) {

            System.out.println(
                    cities[parent[i]] + " - "
                            + cities[i] + " : "
                            + key[i]
            );
        }

        System.out.println("Total Cost: " + total);
    }

    public void kruskal() {

        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < V; i++) {

            for (Pair p : graph.get(i)) {

                if (i < p.vertex) {

                    edgeList.add(
                            new Edge(i, p.vertex, p.weight)
                    );
                }
            }
        }

        edgeList.sort(Comparator.comparingInt(e -> e.w));

        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int total = 0;
        int count = 0;

        System.out.println("\nKruskal's MST:");

        for (Edge e : edgeList) {

            int pu = find(parent, e.u);
            int pv = find(parent, e.v);

            if (pu != pv) {

                union(parent, pu, pv);

                System.out.println(
                        cities[e.u] + " - "
                                + cities[e.v] + " : "
                                + e.w
                );

                total += e.w;

                count++;

                if (count == V - 1) break;
            }
        }

        if (count != V - 1) {
            System.out.println("Graph is disconnected!");
            return;
        }

        System.out.println("Total Cost: " + total);
    }

    private int find(int[] parent, int x) {

        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }

    private void union(int[] parent, int x, int y) {

        int px = find(parent, x);
        int py = find(parent, y);

        parent[py] = px;
    }
}

public class Assignment_5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Graph g = new Graph();

        int ch;

        do {

            System.out.println("\n1.Input Graph");
            System.out.println("2.Display");
            System.out.println("3.Prim's MST");
            System.out.println("4.Kruskal's MST");
            System.out.println("5.Exit");
            System.out.print("Enter choice: ");

            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    g.get_input();
                    break;

                case 2:
                    g.display();
                    break;

                case 3:
                    g.prims();
                    break;

                case 4:
                    g.kruskal();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (ch != 5);
    }
}   