import java.util.*;

class Edge {
    int dest, weight;

    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class InputHelper {

    static int readInt(Scanner sc, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.println("Enter value >= " + min);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }

    static String readName(Scanner sc) {
        while (true) {
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Name cannot be empty");
        }
    }
}

class Graph {

    static final int INF = Integer.MAX_VALUE;

    int V;
    String[] names;
    List<List<Edge>> adjList;

    Graph(int v, String[] landmarkNames) {
        V = v;
        names = landmarkNames;

        adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) adjList.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Edge(v, weight));
        adjList.get(v).add(new Edge(u, weight));
    }



    void printAdjList() {
        System.out.println("\nAdjacency List");
        for (int i = 0; i < V; i++) {
            System.out.print(names[i] + " -> ");
            for (Edge e : adjList.get(i))
                System.out.print(names[e.dest] + "(" + e.weight + ") ");
            System.out.println();
        }
    }

    private static class Node {
        int id, distance;
        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }



    private void printResult(int src, int[] dist) {
        System.out.println("\nShortest Paths from " + names[src]);
        System.out.println("ID   Name        Dist");
        System.out.println("-------------------------");

        for (int i = 0; i < V; i++) {
            String d = dist[i] == INF ? "INF" : String.valueOf(dist[i]);
            System.out.println(i + "    " + names[i] + "    " + d);
        }
    }

    void dijkstra(int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;
            
            if (visited[u]) continue;
            visited[u] = true;

            for (Edge edge : adjList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!visited[v] &&  dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        printResult(src, dist);
    }
}

public class Assignment6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of landmarks (vertices): ");
        int V = InputHelper.readInt(sc, 2, Integer.MAX_VALUE);

        String[] names = new String[V];
        Set<String> seen = new HashSet<>();

        System.out.println("\nEnter names of landmarks:");
        for (int i = 0; i < V; i++) {
            while (true) {
                System.out.print("Landmark " + i + ": ");
                String name = InputHelper.readName(sc);

                if (seen.contains(name.toLowerCase())) {
                    System.out.println("Duplicate name, try again.");
                } else {
                    names[i] = name;
                    seen.add(name.toLowerCase());
                    break;
                }
            }
        }

        Graph graph = new Graph(V, names);

        int maxEdges = V * (V - 1) / 2;
        System.out.print("\nEnter number of edges: ");
        int E = InputHelper.readInt(sc, 1, maxEdges);

        Set<String> added = new HashSet<>();
        int count = 0;

        System.out.println("\nEnter edges (source index, destination index, weight):");
        while (count < E) {
            System.out.print("\nEdge " + (count + 1) + ":\n");

            System.out.print("  Source (0 to " + (V - 1) + "): ");
            int u = InputHelper.readInt(sc, 0, V - 1);

            System.out.print("  Destination (0 to " + (V - 1) + "): ");
            int v = InputHelper.readInt(sc, 0, V - 1);

            if (u == v) {
                System.out.println("No self loop allowed.");
                continue;
            }

            String key = Math.min(u, v) + "-" + Math.max(u, v);
            if (added.contains(key)) {
                System.out.println("Edge already exists.");
                continue;
            }

            System.out.print("  Weight: ");
            int w = InputHelper.readInt(sc, 1, Integer.MAX_VALUE);

            graph.addEdge(u, v, w);
            added.add(key);
            count++;
        }

        graph.printAdjList();

        System.out.print("\nEnter source vertex (0 to " + (V - 1) + "): ");
        int src = InputHelper.readInt(sc, 0, V - 1);

        graph.dijkstra(src);

        System.out.println("\nTime: O(E log V), Space: O(V + E)");

        sc.close();
    }
}


/*
output:
Enter number of landmarks (vertices): 3

Enter names of landmarks:
Landmark 0: bhs
Landmark 1: sarasbaug
Landmark 2: phenoix

Enter number of edges: 3

Enter edges (source index, destination index, weight):

Edge 1:
  Source (0 to 2): 0
  Destination (0 to 2): 1
  Weight: 5

Edge 2:
  Source (0 to 2): 1
  Destination (0 to 2): 2
  Weight: 3

Edge 3:
  Source (0 to 2): 0
  Destination (0 to 2): 2
  Weight: 10

Adjacency Matrix
        bhs    sarasbaug    phenoix
bhs    0      5      10
sarasbaug    5      0      3
phenoix    10      3      0

Adjacency List
bhs -> sarasbaug(5) phenoix(10)
sarasbaug -> bhs(5) phenoix(3)
phenoix -> sarasbaug(3) bhs(10)

Enter source vertex (0 to 2): 0

Shortest Paths from bhs
ID   Name        Dist     Path
------------------------------------------
0    bhs    0    bhs
1    sarasbaug    5    bhs -> sarasbaug
2    phenoix    8    bhs -> sarasbaug -> phenoix

Time: O(V^2), Space: O(V^2)

Process finished with exit code 0

 */
