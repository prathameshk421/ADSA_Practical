import java.util.*;

class Node{
    public int val;
    public Node left;
    public Node right;
    public boolean inp=false;
    public boolean ins=false;
}
class TBT{
    public Node root=null;
    public void insert(int val){
        Node par=null;
        Node ptr=root;
        while(ptr!=null){
            par=ptr;
            if(val<ptr.val){
                if(!ptr.inp)ptr=ptr.left;
                else break;
            }
            else if(val>ptr.val){
                if(!ptr.ins)ptr=ptr.right;
                else break;
            }
            else{
                System.out.println("Duplicate value not allowed!!");
                return;
            }
        }
        Node temp=new Node();
        temp.val=val;
        temp.ins=true;
        temp.inp=true;
        if(par==null){
            root=temp;            
        }
        else if(val<par.val){
            temp.left=par.left;
            temp.right=par;
            par.inp=false;
            par.left=temp;
        }
        else{
            temp.right=par.right;
            temp.left=par;
            par.ins=false;
            par.right=temp;
        }
    }

    public void inorder(){
        Node ptr=root;
        if(ptr==null){
            System.out.println("TBT is empty!!");
            return;
        }
        while(ptr!=null && !ptr.inp)ptr=ptr.left;
        System.out.println("Inorder Traversal:");
        while(ptr!=null){
            System.out.print(ptr.val+" ");
            if(ptr.ins)ptr=ptr.right;
            else{
                ptr=ptr.right;
                while(ptr!=null && !ptr.inp)ptr=ptr.left;
            }
        }
    }
    
    public void preorder(){
        Node ptr=root;
        if(ptr==null){
            System.out.println("TBT is empty!!");
            return;
        }
        System.out.println("Preorder Traversal:");
        while(ptr!=null){
            System.out.print(ptr.val+" ");
            if(!ptr.inp)ptr=ptr.left;
            else{
                while(ptr!=null && ptr.ins)ptr=ptr.right;
                if(ptr!=null)ptr=ptr.right;
            }
        }
    }
}
public class practice {
    public static void main(String[] args){

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
                    tree.inorder();
                    break;

                case 3:
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
