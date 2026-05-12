import java.util.Scanner;

// Job class
class Job{
    String name;
    int time;

    Job(String name,int time){
        this.name = name;
        this.time = time;
    }
}

// Min Heap
class MinHeap{

    int capacity = 100;
    Job[] arr = new Job[capacity];
    int size = -1;

    // Insert job in Min Heap
    public void insert(Job obj){

        size++;
        arr[size] = obj;

        int i = size;
        int parent = (i-1)/2;

        // Heapify up
        while(i>0 && arr[i].time < arr[parent].time){

            Job temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;

            i = parent;
            parent = (i-1)/2;
        }
    }

    // Delete minimum job
    public Job deleteMin(){

        if(size==-1) return null;

        Job min = arr[0];
        arr[0] = arr[size];
        size--;

        int i = 0;

        // Heapify down
        while(true){

            int left = 2*i+1;
            int right = 2*i+2;
            int smallest = i;

            if(left<=size && arr[left].time < arr[smallest].time)
                smallest = left;

            if(right<=size && arr[right].time < arr[smallest].time)
                smallest = right;

            if(smallest==i) break;

            Job temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            i = smallest;
        }

        return min;
    }

    // Display all jobs
    public void display(){

        if(size==-1){
            System.out.println("No Jobs Available");
            return;
        }

        System.out.println("\nJobs in Scheduler:");

        for(int i=0;i<=size;i++){
            System.out.println("Name: "+arr[i].name+"  Time: "+arr[i].time);
        }
    }
}

// Max Heap
class MaxHeap{

    int capacity = 100;
    Job[] arr = new Job[capacity];
    int size = -1;

    // Insert job in Max Heap
    public void insert(Job obj){

        size++;
        arr[size] = obj;

        int i = size;
        int parent = (i-1)/2;

        // Heapify up
        while(i>0 && arr[i].time > arr[parent].time){

            Job temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;

            i = parent;
            parent = (i-1)/2;
        }
    }

    // Delete maximum job
    public Job deleteMax(){

        if(size==-1) return null;

        Job max = arr[0];
        arr[0] = arr[size];
        size--;

        int i = 0;

        // Heapify down
        while(true){

            int left = 2*i+1;
            int right = 2*i+2;
            int largest = i;

            if(left<=size && arr[left].time > arr[largest].time)
                largest = left;

            if(right<=size && arr[right].time > arr[largest].time)
                largest = right;

            if(largest==i) break;

            Job temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            i = largest;
        }

        return max;
    }
}

public class Assignment2 {

    // Function to take job input
    public static Job input(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Job Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Job Time: ");
        int time = sc.nextInt();

        return new Job(name,time);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();

        int choice;

        do{

            System.out.println("\n===== JOB SCHEDULER =====");
            System.out.println("1.Insert Job");
            System.out.println("2.Complete Job (Smallest Time)");
            System.out.println("3.Complete Job (Largest Time)");
            System.out.println("4.Display Jobs");
            System.out.println("5.Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:

                    Job j = input();

                    minHeap.insert(j);
                    maxHeap.insert(j);

                    System.out.println("Job Added!");
                    break;

                case 2:

                    Job min = minHeap.deleteMin();

                    if(min!=null)
                        System.out.println("Completed Job: "+min.name);
                    else
                        System.out.println("No Jobs");

                    break;

                case 3:

                    Job max = maxHeap.deleteMax();

                    if(max!=null)
                        System.out.println("Completed Job: "+max.name);
                    else
                        System.out.println("No Jobs");

                    break;

                case 4:

                    minHeap.display();
                    break;

            }

        }while(choice!=5);

        sc.close();
    }
}
/*
===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 1
Enter Job Name: Chrome
Enter Job Time: 10
Job Added!

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 1
Enter Job Name: VSCode
Enter Job Time: 5
Job Added!

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 1
Enter Job Name: Spotify
Enter Job Time: 15
Job Added!

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 4

Jobs in Scheduler:
Name: VSCode  Time: 5
Name: Chrome  Time: 10
Name: Spotify  Time: 15

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 2
Completed Job: VSCode

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 4

Jobs in Scheduler:
Name: Chrome  Time: 10
Name: Spotify  Time: 15

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 3
Completed Job: Spotify

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 4

Jobs in Scheduler:
Name: Chrome  Time: 10
Name: Spotify  Time: 15

===== JOB SCHEDULER =====
1.Insert Job
2.Complete Job (Smallest Time)
3.Complete Job (Largest Time)
4.Display Jobs
5.Exit
Enter choice: 5

 */