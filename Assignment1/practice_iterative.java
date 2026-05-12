import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}

class BST{
    Node root=null;
    
    public Node get_node(int data){
        return new Node(data);
    }
    
    public void insert(int key){
        Node cur=root;
        Node parent=null;
        if(root==null){
            root=get_node(key);
        }
        else {
            while(cur!=null){
                parent=cur;
                if(key<cur.data){
                    cur=cur.left;
                }
                else if(key>cur.data){
                    cur=cur.right;
                }
                else{
                    System.out.println("Duplicate insertion not allowed!!");
                    return;
                }
            }

            if(key<parent.data){
                parent.left=get_node(key);
            }
            else{
                parent.right=get_node(key);
            }
        }
        System.out.println("successfully inserted!");
    }

    public void inorder(){
        Node cur=root;
        if(cur==null){
            System.out.println("BST is empty!");
            return;
        }
        Stack<Node> st=new Stack<>();
        System.out.println("Inorder:");
        while(cur!=null || !st.isEmpty()){

            while(cur!=null){
                st.push(cur);
                cur=cur.left;    
            }
            cur=st.pop();
            System.out.print(cur.data+" ");
            cur=cur.right;
        }

    }

    public void preorder(){
        if(root==null){
            System.out.println("BST is empty!!");
            return;
        }
    
        Stack<Node> st=new Stack<>();
        st.push(root);
        System.out.println("Preorder:");
        while(!st.isEmpty()){
            Node cur=st.pop();
            System.out.print(cur.data+" ");

            if(cur.right!=null){
                st.push(cur.right);
            }
            if(cur.left!=null){
                st.push(cur.left);
            }
        }

    }

    public void postorder(){
        if(root==null){
            System.out.println("BST is empty!!");
            return;
        }
        Stack<Node> st1=new Stack<>();
        Stack<Node> st2=new Stack<>();
        st1.push(root);
        while(!st1.isEmpty()){
            Node cur=st1.pop();
            st2.push(cur);
            if(cur.left!=null){
                st1.push(cur.left);
            }
            if(cur.right!=null){
                st1.push(cur.right);
            }
        }
        while(!st2.isEmpty()){
            Node cur=st2.pop();
            System.out.print(cur.data+" ");
        }
        System.out.println();
    }
    public void delete(int key){
        Node cur=root;
        Node parent=null;
        while(cur!=null && cur.data!=key){
            parent=cur;
            if(key<cur.data){
                cur=cur.left;
            }
            if(key>cur.data){
                cur=cur.right;
            }
        }
        if(cur==null){
            System.out.println("key not found!");
            return;
        }
        if(cur.left==null || cur.right==null){
            Node successor=(cur.left==null) ? cur.right:cur.left;
            if(parent==null){ // IMP
                root=successor;
            }
            else if(parent.left==cur){
                parent.left=successor;
            }
            else{
                parent.right=successor;
            }
        }
        else{
            Node successor=cur.right;
            Node successorParent=cur;
            while(successor.left!=null){ 
                successorParent=successor;
                successor=successor.left;
            }

            cur.data=successor.data;

            if(successorParent.left==successor){
                successorParent.left=successor.right;
            }
            else{
                successorParent.right=successor.right;
            }
        }
        System.out.println("Deleted successfully");
    }
}
public class practice_iterative {

    public static void main(String[] args){

        BST obj=new BST();

        // INSERTION
        obj.insert(50);
        obj.insert(30);
        obj.insert(70);
        obj.insert(20);
        obj.insert(40);
        obj.insert(60);
        obj.insert(80);

        // DUPLICATE
        obj.insert(70);

        System.out.println();

        // TRAVERSALS
        obj.inorder();

        System.out.println();

        obj.preorder();

        System.out.println();

        obj.postorder();

        System.out.println();

        // DELETE LEAF NODE
        System.out.println("Deleting 20...");
        obj.delete(20);

        obj.inorder();

        System.out.println();

        // DELETE NODE WITH ONE CHILD
        System.out.println("Deleting 30...");
        obj.delete(30);

        obj.inorder();

        System.out.println();

        // DELETE NODE WITH TWO CHILDREN
        System.out.println("Deleting 50...");
        obj.delete(50);

        obj.inorder();

        System.out.println();

        // DELETE NON-EXISTING NODE
        System.out.println("Deleting 100...");
        obj.delete(100);

        System.out.println();

        // FINAL PREORDER
        obj.preorder();

        System.out.println();

        // FINAL POSTORDER
        obj.postorder();
    }
}
