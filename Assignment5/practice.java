import java.util.*;

class Pair{
    int vertex,weight;
    Pair(int vertex,int weight){
        this.vertex=vertex;
        this.weight=weight;
    }
}
class Edge{
    int u,v,w;
    Edge(int u,int v,int w){
        this.u=u;
        this.v=v;
        this.w=w;
    }
}
class Graph{
    ArrayList<ArrayList<Pair>> adj_list;
    int V, E;
    HashSet<String> edges;
    String[] cities;

    public void get_input(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter No. of Cities:");
        V=sc.nextInt();
        sc.nextLine();
        cities=new String[V];
        for(int i=0;i<V;i++){
            System.out.print("Enter Name of City"+(i+1)+":");
            cities[i]=sc.nextLine();
            if(cities[i].isEmpty()){
                System.out.println("City name cannot be empty!");
                i--;
            }
        }
        boolean is_valid=false;
        do{
            System.out.print("Enter No. of Edges:");
            E=sc.nextInt();
            int max_E=(V*(V-1))/2;
            is_valid=true;
            if(E>max_E){
                System.out.println("Edge count exceeds maximum!");
                System.out.println("Max Edge Count:"+max_E);
                is_valid=false;
            }
        }while(!is_valid);

        System.out.println("City Index Mapping:");
        adj_list=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj_list.add(new ArrayList<>());
            System.out.println(cities[i]+":"+i);
        }
        edges=new HashSet<>();
        System.out.println("\nEnter edges (index1 index2 distance):");
        for (int i = 0; i < E; i++) {

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

            adj_list.get(u).add(new Pair(v, w));
            adj_list.get(v).add(new Pair(u, w));

            edges.add(edge1);
            edges.add(edge2);
        }
        System.out.println();
    }

    public void display() {

        System.out.println("\nAdjacency List:");

        for (int i = 0; i < V; i++) {

            System.out.print(cities[i] + " -> ");

            for (Pair p : adj_list.get(i)) {
                System.out.print(cities[p.vertex] + "(" + p.weight + ") ");
            }
        }

        System.out.println();
    }

    public void prims(){
        boolean visited[]=new boolean[V];
        int key[]=new int[V];
        int parent[]=new int[V];

        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.weight-b.weight);
        pq.add(new Pair(0,0));
        for(int i=0;i<V;i++){
            key[i]=Integer.MAX_VALUE;
        }
        parent[0]=-1;
        key[0]=0;
        int total=0;
        while(!pq.isEmpty()){
            Pair cur=pq.poll();
            int u=cur.vertex;

            if(visited[u])continue;
            visited[u]=true;
            total+=cur.weight;
            for(Pair p:adj_list.get(u)){
                int v=p.vertex;
                int w=p.weight;

                if(!visited[v] && w<key[v]){
                    key[v]=w;
                    parent[v]=u;
                    pq.add(new Pair(v,key[v]));
                }
            }
        }
        System.out.println("Prim's MST:");
        for(int i=1;i<V;i++){
            System.out.println(cities[parent[i]]+"->"+cities[i]+":"+key[i]);
        }
        System.out.println("Total Cost:"+total);
    }

    public void kruskal(){
        List<Edge> edges=new ArrayList<>();
        int parent[]=new int[V];
        for(int i=0;i<V;i++){
            parent[i]=i;
            for(Pair p:adj_list.get(i)){
                if(i<p.vertex){
                    edges.add(new Edge(i,p.vertex,p.weight));
                }
            }
        }
        edges.sort((a,b)->a.w-b.w);
        int count=0,total=0;
        for(Edge e:edges){
            int pu=find(parent,e.u);
            int pv=find(parent,e.v);

            if(pu!=pv){
                System.out.println(cities[e.u]+"->"+cities[e.v]+":"+e.w);
                parent[pv]=pu;
                
                count++;
                total+=e.w;
                if(count==V-1)break;
            }
        }
    }
    private int find(int parent[],int x){
        if(parent[x]!=x){
            parent[x]=find(parent,parent[x]);
        }
        return parent[x];
    }
}
public class practice {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Graph g = new Graph();

        int ch;

        do{

            System.out.println("\n===== GRAPH MENU =====");
            System.out.println("1. Input Graph");
            System.out.println("2. Display Graph");
            System.out.println("3. Prim's MST");
            System.out.println("4. Kruskal's MST");
            System.out.println("5. Exit");

            System.out.print("Enter Choice:");
            ch = sc.nextInt();

            switch(ch){

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
                    System.out.println("Invalid Choice!");
            }

        }while(ch != 5);
    }
}
