import java.util.*;

class Node {
    public int val;
    public boolean inp=false;   // true if left pointer is thread
    public Node left=null;
    public Node right=null;
    public boolean ins=false;   // true if right pointer is thread
}

class TBT{
    public Node root=null;

    // Create a new node
    public Node get_node(int val){
        Node temp=new Node();
        temp.val=val;
        return temp;
    }

    // Insert value into threaded binary tree
    public void insert(int val){
        Node par=null;
        Node ptr=root;

        // Find insertion position
        while(ptr!=null){
            par=ptr;
            if(val<ptr.val){
                if(!ptr.inp) ptr=ptr.left;
                else break;
            }
            else if(val>ptr.val){
                if(!ptr.ins) ptr=ptr.right;
                else break;
            }
            else{
                System.out.println("Duplicate value insertion not allowed!");
                return;
            }
        }

        Node temp=get_node(val);
        temp.ins=true;
        temp.inp=true;

        if(par==null){
            root=temp;
        }
        else if(val<par.val){   // Insert as left child
            temp.left=par.left;
            temp.right=par;
            par.inp=false;
            par.left=temp;
        }
        else {                  // Insert as right child
            temp.right=par.right;
            temp.left=par;
            par.ins=false;
            par.right = temp;
        }
    }

    // Find leftmost node
    public Node left_most(Node cur_root){
        Node temp=cur_root;
        while(!temp.inp) temp=temp.left;
        return temp;
    }

    // Inorder traversal using threads
    public void inorder(){
        Node ptr=left_most(root);
        if(ptr==null){
            System.out.println("Threaded Binary Tree is empty!\n");
            return;
        }

        while(ptr!=null){
            System.out.print(ptr.val+" ");
            if(ptr.ins) ptr=ptr.right;
            else{
                ptr=ptr.right;
                while(!ptr.inp) ptr=ptr.left;
            }
        }
    }

    // Preorder traversal
    public void preorder(){
        Node ptr=root;
        if(ptr==null){
            System.out.println("Threaded Binary Tree is empty!\n");
            return;
        }

        while(ptr!=null){
            System.out.print(ptr.val+" ");
            if(!ptr.inp) ptr=ptr.left;
            else{
                while(ptr!=null && ptr.ins) ptr=ptr.right;
                if(ptr!=null) ptr=ptr.right;
            }
        }
    }
}

public class Assignment3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TBT tree = new TBT();

        int choice, value;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Insert");
            System.out.println("2. Display (Inorder)");
            System.out.println("3. Display (Preorder)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value to insert: ");
                    value = sc.nextInt();
                    tree.insert(value);
                    break;

                case 2:
                    System.out.println("Inorder Traversal:");
                    tree.inorder();
                    break;

                case 3:
                    System.out.println("Preorder Traversal:");
                    tree.preorder();
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
/*
===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 50

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 30

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 70

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 20

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 40

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 60

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 1
Enter value to insert: 80

===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 2
Inorder Traversal:
20 30 40 50 60 70 80
===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 3
Preorder Traversal:
50 30 20 40 70 60 80
===== MENU =====
1. Insert
2. Display (Inorder)
3. Display (Preorder)
4. Exit
Enter your choice: 4
Exiting program...

 */