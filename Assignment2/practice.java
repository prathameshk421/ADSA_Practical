import java.util.*;

class Job{
    String name;
    int exec_time;

    Job(String name,int exec_time){
        this.name=name;
        this.exec_time=exec_time;
    }
}

class Max_Heap{

    int capacity=100;
    Job[] jobs=new Job[capacity];
    int size=-1;

    public void insert(Job new_job){

        if(size==capacity-1){
            System.out.println("Heap Capacity Reached!!");
            return;
        }

        size++;
        jobs[size]=new_job;

        int i=size;
        int parent=(i-1)/2;

        while(i>0 &&
              jobs[i].exec_time>jobs[parent].exec_time){

            Job temp=jobs[parent];
            jobs[parent]=jobs[i];
            jobs[i]=temp;

            i=parent;
            parent=(i-1)/2;
        }

        System.out.println("Successful Insertion in Max Heap!!");
    }

    public void delete_max(){

        if(size==-1){
            System.out.println("Heap is Empty!!");
            return;
        }

        jobs[0]=jobs[size];
        jobs[size]=null;
        size--;

        int i=0;

        while(i<=size){

            int left=2*i+1;
            int right=2*i+2;
            int largest=i;

            if(left<=size &&
               jobs[left].exec_time>jobs[largest].exec_time){

                largest=left;
            }

            if(right<=size &&
               jobs[right].exec_time>jobs[largest].exec_time){

                largest=right;
            }

            if(largest==i) break;

            Job temp=jobs[largest];
            jobs[largest]=jobs[i];
            jobs[i]=temp;

            i=largest;
        }

        System.out.println("Deleted Highest Priority Job from Max Heap");
    }

    public void peek(){

        if(size==-1){
            System.out.println("Heap is Empty!!");
        }
        else{
            System.out.println(
                "Highest Priority Job: "
                +jobs[0].name+
                " : "+
                jobs[0].exec_time
            );
        }
    }

    public void display(){

        if(size==-1){
            System.out.println("Heap is Empty!!");
            return;
        }

        System.out.println("\nMax Heap Jobs:");

        for(int i=0;i<=size;i++){

            System.out.println(
                jobs[i].name+
                " : "+
                jobs[i].exec_time
            );
        }
    }
}

class Min_Heap{

    int capacity=100;
    Job[] jobs=new Job[capacity];
    int size=-1;

    public void insert(Job new_job){

        if(size==capacity-1){
            System.out.println("Heap Capacity Reached!!");
            return;
        }

        size++;
        jobs[size]=new_job;

        int i=size;
        int parent=(i-1)/2;

        while(i>0 &&
              jobs[i].exec_time<jobs[parent].exec_time){

            Job temp=jobs[parent];
            jobs[parent]=jobs[i];
            jobs[i]=temp;

            i=parent;
            parent=(i-1)/2;
        }

        System.out.println("Successful Insertion in Min Heap!!");
    }

    public void delete_min(){

        if(size==-1){
            System.out.println("Heap is Empty!!");
            return;
        }

        jobs[0]=jobs[size];
        jobs[size]=null;
        size--;

        int i=0;

        while(i<=size){

            int left=2*i+1;
            int right=2*i+2;
            int smallest=i;

            if(left<=size &&
               jobs[left].exec_time<jobs[smallest].exec_time){

                smallest=left;
            }

            if(right<=size &&
               jobs[right].exec_time<jobs[smallest].exec_time){

                smallest=right;
            }

            if(smallest==i) break;

            Job temp=jobs[smallest];
            jobs[smallest]=jobs[i];
            jobs[i]=temp;

            i=smallest;
        }

        System.out.println("Deleted Highest Priority Job from Min Heap");
    }

    public void peek(){

        if(size==-1){
            System.out.println("Heap is Empty!!");
        }
        else{

            System.out.println(
                "Highest Priority Job: "
                +jobs[0].name+
                " : "+
                jobs[0].exec_time
            );
        }
    }

    public void display(){

        if(size==-1){
            System.out.println("Heap is Empty!!");
            return;
        }

        System.out.println("\nMin Heap Jobs:");

        for(int i=0;i<=size;i++){

            System.out.println(
                jobs[i].name+
                " : "+
                jobs[i].exec_time
            );
        }
    }
}

public class practice{

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        Max_Heap maxHeap=new Max_Heap();
        Min_Heap minHeap=new Min_Heap();

        int choice;

        do{

            System.out.println("\n===== JOB SCHEDULER USING HEAP =====");

            System.out.println("1. Insert Job in Min Heap");
            System.out.println("2. Delete Highest Priority Job from Min Heap");
            System.out.println("3. Peek Min Heap");
            System.out.println("4. Display Min Heap");

            System.out.println("5. Insert Job in Max Heap");
            System.out.println("6. Delete Highest Priority Job from Max Heap");
            System.out.println("7. Peek Max Heap");
            System.out.println("8. Display Max Heap");

            System.out.println("9. Exit");

            System.out.print("Enter Choice: ");
            choice=sc.nextInt();

            switch(choice){

                case 1:

                    System.out.print("Enter Job Name: ");
                    String minName=sc.next();

                    System.out.print("Enter Execution Time: ");
                    int minTime=sc.nextInt();

                    minHeap.insert(new Job(minName,minTime));
                    break;

                case 2:
                    minHeap.delete_min();
                    break;

                case 3:
                    minHeap.peek();
                    break;

                case 4:
                    minHeap.display();
                    break;

                case 5:

                    System.out.print("Enter Job Name: ");
                    String maxName=sc.next();

                    System.out.print("Enter Execution Time: ");
                    int maxTime=sc.nextInt();

                    maxHeap.insert(new Job(maxName,maxTime));
                    break;

                case 6:
                    maxHeap.delete_max();
                    break;

                case 7:
                    maxHeap.peek();
                    break;

                case 8:
                    maxHeap.display();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!!");
            }

        }while(choice!=9);

        sc.close();
    }
}