import java.util.*;

class Edge{
    int dest;
    int weight;
    Edge(int dest,int weight){
        this.dest=dest;
        this.weight=weight;
    }
}

class Node{
    int id,distance;
    Node(int src,int distance){
        this.id=src;
        this.distance=distance;
    }
}

class Input{
    public int input_V(Scanner sc){
        System.out.print("Enter No. of Cities: ");
        int V=sc.nextInt();
        sc.nextLine();
        return V;
    }
    public String[] input_cities(int V,Scanner sc){
        String[] cities=new String[V];
        for(int i=0;i<V;i++){
            System.out.print("Enter City "+(i+1)+" Name: ");
            cities[i]=sc.nextLine();
        }
        return cities;
    }
    public ArrayList<ArrayList<Edge>> input_adj_list(int V,String[] cities,Scanner sc){
        ArrayList<ArrayList<Edge>> adj_list=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj_list.add(new ArrayList<>());
        }
        System.out.println("\nCity Name : Index");
        for(int i=0;i<V;i++){
            System.out.println(cities[i]+" : "+i);
        }
        boolean done1=false;
        int E;
        do{
            System.out.print("\nEnter No. of Edges/Paths: ");
            E=sc.nextInt();
            done1=2*E <=(V*(V-1));
        }while(!done1);
        HashMap<String,Boolean> mp=new HashMap<>();
        for(int i=0;i<E;i++){
            System.out.println("\n--- Edge "+(i+1)+" ---");
            System.out.print("Enter Source City Index: ");
            int src=sc.nextInt();
            if(src>=V || src<0){
                System.out.println("Source Index does not exist!!");
                i--;
                continue;
            }
            System.out.print("Enter Destination City Index: ");
            int dest=sc.nextInt();
            if(dest>=V || dest<0){
                System.out.println("Destination Index does not exist!!");
                i--;
                continue;
            }
            if(src==dest){
                System.out.println("Self loop is not allowed!");
                i--;
                continue;
            }
            System.out.print("Enter Weight of Edge: ");
            int weight=sc.nextInt();
            if(weight<0){
                System.out.println("Negative Weights not allowed!");
                i--;
                continue;
            }
            if(mp.containsKey(src+"->"+dest)){
                System.out.println("Duplicate Edge not allowed!");
                i--;
                continue;
            }
            adj_list.get(src).add(new Edge(dest,weight));
            adj_list.get(dest).add(new Edge(src,weight)); 
            mp.put(src+"->"+dest,true);
            mp.put(dest+"->"+src,true);
        }
        return adj_list;
    }
}

class City{
    ArrayList<ArrayList<Edge>> adj_list;
    int V;
    String[] landmarks;
    City(int V,String[] landmarks,ArrayList<ArrayList<Edge>> adj_list){
        this.V=V;
        this.landmarks=landmarks;
        this.adj_list=adj_list;
    }
    public void print_distance(int src,int[] dist){
        System.out.println("\n=== Shortest Distances from "+landmarks[src]+" ===");
        for(int i=0;i<V;i++){
            if(dist[i]==Integer.MAX_VALUE){
                System.out.println(landmarks[src]+" -> "+landmarks[i]+" = UNREACHABLE");
            } else {
                System.out.println(landmarks[src]+" -> "+landmarks[i]+" = "+dist[i]);
            }
        }
    }
    public int[] Dijkstra(int src){
        int dist[]=new int[V];
        boolean visited[]=new boolean[V];

        PriorityQueue<Node> pq=new PriorityQueue<>((a,b)->a.distance-b.distance);
        pq.add(new Node(src,0));
        for(int i=0;i<V;i++)dist[i]=Integer.MAX_VALUE;
        dist[src]=0;

        while(!pq.isEmpty()){
            Node cur=pq.poll();
            int u=cur.id;
            if(visited[u])continue;
            visited[u]=true;

            for(Edge e:adj_list.get(u)){
                int v=e.dest;
                int w=e.weight;
                if(!visited[v] && dist[v]>dist[u]+w){
                    dist[v]=dist[u]+w;
                    pq.add(new Node(v,dist[v]));
                }
            }
        }
        return dist;
    }
}

public class practice {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Input in=new Input();
        
        // Step 1: Input number of cities
        int V=in.input_V(sc);
        
        // Step 2: Input city names
        String[] cities=in.input_cities(V,sc);
        
        // Step 3: Build adjacency list
        ArrayList<ArrayList<Edge>> adj_list=in.input_adj_list(V,cities,sc);
        
        // Step 4: Create City graph object
        City city=new City(V,cities,adj_list);
        
        // Step 5: Ask for source city and run Dijkstra
        System.out.print("\nEnter Source City Index to find shortest paths: ");
        int src=sc.nextInt();
        
        if(src<0 || src>=V){
            System.out.println("Invalid source index!");
        } else {
            int[] dist=city.Dijkstra(src);
            city.print_distance(src,dist);
        }
        
        sc.close();
    }
}