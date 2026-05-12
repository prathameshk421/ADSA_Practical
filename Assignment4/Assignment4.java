import java.util.*;


class navigation_system{
    HashMap<String,ArrayList<String>> map = new HashMap<>();
    public void get_input(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter No. of Cities:");
        int v =  input.nextInt();
        System.out.println("Enter No. of Routes:");
        int e = input.nextInt();
        int maxe=v*(v-1);
        if(maxe>e){
            System.out.println("Edges exceed maximum no. of edges!!!");
            System.exit(0);
        }
        for(int i=0;i<v;i++){
            System.out.println("Enter Name of City:");
            String city_name = input.next();
            map.put(city_name,new ArrayList<>());
        }
        for(int i=0;i<e;i++){
            System.out.println("Enter Source City for Edge "+(i+1)+":");
            String source_dest = input.next();
            System.out.println("Enter Destination City for Edge "+(i+1)+":");
            String destination_dest = input.next();
            if(source_dest.equals(destination_dest)){
                i--;
                System.out.println("Edge to oneself cannot exist!!");
            }
            if(map.get(source_dest).contains(destination_dest)){
                i--;
                System.out.println("Edge already exists!!");
            }
            map.get(source_dest).add(destination_dest);
        }
    }
    public void bfs(String source){
        Queue<String> queue = new LinkedList<>();
        HashMap<String,Boolean> visited = new HashMap<>();
        queue.add(source);
        System.out.println("BFS Traversal:");
        while(!queue.isEmpty()){
            String current = queue.poll();
            visited.put(current,true);
            System.out.println(current+" ");
            for(String city : map.get(current)){
                if(!visited.containsKey(city)){
                    queue.add(city);
                    visited.put(city,true);
                }
            }
        }
    }
    public void dfs(String cur,HashMap<String,Boolean> visited){
        visited.put(cur,true);
        System.out.println(cur+" ");
        for(String city : map.get(cur)) {
            if (!visited.containsKey(city)) {
                dfs(city, visited);
            }
        }
    }
}
public class Assignment4 {
    public static void main(String[] args) {

        navigation_system nav = new navigation_system();

        nav.get_input();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter source city for BFS:");
        String bfsSource = sc.next();

        nav.bfs(bfsSource);
        System.out.println("Enter source city for DFS:");
        String dfsSource = sc.next();

        HashMap<String,Boolean> visited = new HashMap<>();
        System.out.println("DFS Traversal:");
        nav.dfs(dfsSource, visited);

        sc.close();
    }
}

/*
Enter No. of Cities:
9
Enter No. of Routes:
12
Enter Name of City:
A
Enter Name of City:
B
Enter Name of City:
C
Enter Name of City:
D
Enter Name of City:
E
Enter Name of City:
F
Enter Name of City:
G
Enter Name of City:
H
Enter Name of City:
I
Enter Source City for Edge 1:
A
Enter Destination City for Edge 1:
B
Enter Source City for Edge 2:
A
Enter Destination City for Edge 2:
C
Enter Source City for Edge 3:
B
Enter Destination City for Edge 3:
D
Enter Source City for Edge 4:
B
Enter Destination City for Edge 4:
E
Enter Source City for Edge 5:
C
Enter Destination City for Edge 5:
F
Enter Source City for Edge 6:
C
Enter Destination City for Edge 6:
G
Enter Source City for Edge 7:
D
Enter Destination City for Edge 7:
H
Enter Source City for Edge 8:
E
Enter Destination City for Edge 8:
H
Enter Source City for Edge 9:
F
Enter Destination City for Edge 9:
I
Enter Source City for Edge 10:
G
Enter Destination City for Edge 10:
I
Enter Source City for Edge 11:
H
I
Enter Destination City for Edge 11:
Enter Source City for Edge 12:
I
Enter Destination City for Edge 12:
A
Enter source city for BFS:
A
BFS Traversal:
A
B
C
D
E
F
G
H
I
Enter source city for DFS:
A
A
B
D
H
I
E
C
F
G

*/