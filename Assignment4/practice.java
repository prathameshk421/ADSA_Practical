import java.util.*;

class navigation_system{
    HashMap<String,ArrayList<String>> adj_list=new HashMap<>();
    public String dfs_src,bfs_src;
    public void input(Scanner sc){
        System.out.println("Enter the No. of Vertices:");
        int V=sc.nextInt();
        System.out.println("Enter No. of Edges:");
        int E=sc.nextInt();
        sc.nextLine();
        if(2*E>(V*(V-1))){
            System.out.println("Edge exceed maximum no. of edges!");
            System.exit(0);
        }
        for(int i=0;i<V;i++){
            System.out.println("Enter name of city:");
            String city=sc.nextLine();
            if(adj_list.containsKey(city)){
                System.out.println("City already exists!!");
                i--;
                continue;
            }
            adj_list.put(city,new ArrayList<>());
        }
        for(int i=0;i<E;i++){
            System.out.println("Enter Source City for Edge "+(i+1)+"=");
            String source=sc.nextLine();
            System.out.println("Enter Destination City for Edge "+(i+1)+"=");
            String destination=sc.nextLine();
            if(!adj_list.containsKey(source)){
                System.out.println("Source City doesn't exist!!");
                i--;
            }
            else if(!adj_list.containsKey(destination)){
                System.out.println("Destination City doesn't exist!!");
                i--;
            }
            else if(adj_list.get(source).contains(destination)){
                System.out.println("Edge already exists!!");
                i--;
            }
            else{
                adj_list.get(source).add(destination);
                adj_list.get(destination).add(source);
            }
                

        }
        System.out.println("Enter Source Node for DFS:");
        dfs_src=sc.nextLine();
        System.out.println("Enter Destination Node for BFS:");
        bfs_src=sc.nextLine();
    }
    public void dfs(String cur,HashMap<String,Boolean> visited){
        if(!adj_list.containsKey(cur)){
            System.out.println("Not a city!!");
            return;
        }
        System.out.print(cur+" ");
        visited.put(cur,true);
        for(String child:adj_list.get(cur)){
            if(!visited.containsKey(child)){
                dfs(child,visited);
            }
        }
    }

    public void bfs(String src){
        if(!adj_list.containsKey(src)){
            System.out.println("Not a city!!");
            return;
        }
        Queue<String> q=new LinkedList<>();
        HashMap<String,Boolean> visited=new HashMap<>();
        q.add(src);
        visited.put(src,true);
        System.out.println("BFS Traversal:");
        while(!q.isEmpty()){
            String cur=q.poll();
            visited.put(cur,true);
            System.out.print(cur+" ");
            for(String child:adj_list.get(cur)){
                if(!visited.containsKey(child)){
                    q.add(child);
                    visited.put(child,true);
                }
            }
        }
    }
}
public class practice {
    public static void main(String args[]){
        navigation_system nav=new navigation_system();
        Scanner sc=new Scanner(System.in);
        nav.input(sc);
        System.out.println("DFS:");
        HashMap<String,Boolean> visited=new HashMap<>();
        nav.dfs(nav.dfs_src,visited);
        System.out.println();
        nav.bfs(nav.bfs_src);
    }   

}
