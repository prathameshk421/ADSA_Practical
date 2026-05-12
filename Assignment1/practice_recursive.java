import java.util.*;

class Node{
    Node left,right;
    int data;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}
class BST{
    Node root=null;
    public Node get_node(int data){
        Node temp=new Node(data);
        return temp;
    }

    public Node insert(Node cur,int data){
        if(cur==null){
            return get_node(data);
        }
        else if(data<cur.data){
            cur.left=insert(cur.left,data);
        }
        else if(data>cur.data){
            cur.right=insert(cur.right,data);
        }
        else{
            System.out.println("Duplicate Node not allowed");
        }
        return cur;
    }

    public boolean search(Node cur,int key){
        if(cur==null){
            return false;
        }
        else if(key<cur.data){
            return search(cur.left,key);
        }
        else if(key>cur.data){
            return search(cur.right,key);
        }
        else{
            System.out.println("Key found!!");
            return true;
        }
    }

    public void inorder(Node cur){
        if(cur==null)return;
        inorder(cur.left);
        System.out.print(cur.data+" ");
        inorder(cur.right);
    }

    public void preorder(Node cur){
        if(cur==null)return;
        System.out.print(cur.data+" ");
        preorder(cur.left);
        preorder(cur.right);
    }

    public void postorder(Node cur){
        if(cur==null)return;
        postorder(cur.left);
        postorder(cur.right);
        System.out.print(cur.data+" ");
    }

    public void display_leaf(Node cur){
        if(cur==null)return; //IMP 
        if(cur.left==null && cur.right==null){
            System.out.print(cur.data+" ");
        }
        else{
            display_leaf(cur.left);
            display_leaf(cur.right);
        }
    }

    public int depth(Node cur){
        if(cur==null) return 0;
        return Math.max(depth(cur.right)+1,depth(cur.left)+1);
    }

    public Node delete(Node cur,int key){
        if(cur==null){
            System.out.println("Key to delete not found!!");
            return null;
        }
        else if(key<cur.data){
            cur.left=delete(cur.left,key);
        }
        else if(key>cur.data){
            cur.right=delete(cur.right,key);
        }
        else{
            if(cur.left==null && cur.right!=null){
                return cur.right;
            }
            else if(cur.left!=null && cur.right==null){
                return cur.left;
            }
            else if(cur.left==null && cur.right==null){
                return null;
            }
            else{
                Node successor=cur.right;
                while(successor.left!=null)successor=successor.left;
                cur.data=successor.data;
                cur.right=delete(cur.right,successor.data);
            }
        }
        return cur;
    }

    public void mirror(Node cur){
        if(cur==null)return;
        
        Node temp=cur.right;
        cur.right=cur.left;
        cur.left=temp;

        mirror(cur.left);
        mirror(cur.right);
    }

    public Node copy_tree(Node cur){
        if(cur==null)return null;
        Node new_node=new Node(cur.data);
        new_node.left=copy_tree(cur.left);
        new_node.right=copy_tree(cur.right);
        return new_node;
    }
    public void bfs(){
        if(root==null){
            System.out.println("BST is empty!!");
            return;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int sz=q.size();
            for(int i=0;i<sz;i++){
                Node cur=q.poll();
                System.out.print(cur.data+" ");
                if(cur.left!=null)q.add(cur.left);
                if(cur.right!=null)q.add(cur.right);
            }
            System.out.println();
        }
    }
}
public class practice_recursive {
    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);

        BST tree=new BST();

        int choice,data,key;

        do{

            System.out.println("\n===== BST MENU =====");
            System.out.println("1. Insert Node");
            System.out.println("2. Search Node");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Preorder Traversal");
            System.out.println("5. Postorder Traversal");
            System.out.println("6. Display Leaf Nodes");
            System.out.println("7. Display Height");
            System.out.println("8. Delete Node");
            System.out.println("9. Mirror Tree");
            System.out.println("10. Copy Tree");
            System.out.println("11. BFS Traversal");
            System.out.println("12. Exit");

            System.out.print("Enter choice: ");
            choice=sc.nextInt();

            switch(choice){

                case 1:
                    System.out.print("Enter data: ");
                    data=sc.nextInt();

                    tree.root=tree.insert(tree.root,data);

                    break;

                case 2:
                    System.out.print("Enter key to search: ");
                    key=sc.nextInt();

                    if(tree.search(tree.root,key)){
                        System.out.println("Key Found!");
                    }
                    else{
                        System.out.println("Key Not Found!");
                    }

                    break;

                case 3:
                    System.out.println("Inorder Traversal:");
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Preorder Traversal:");
                    tree.preorder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Postorder Traversal:");
                    tree.postorder(tree.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Leaf Nodes:");
                    tree.display_leaf(tree.root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Height of Tree: "
                            +tree.depth(tree.root));
                    break;

                case 8:
                    System.out.print("Enter key to delete: ");
                    key=sc.nextInt();

                    tree.root=tree.delete(tree.root,key);

                    break;

                case 9:
                    tree.mirror(tree.root);

                    System.out.println("Tree Mirrored Successfully!");
                    break;

                case 10:
                    BST copiedTree=new BST();

                    copiedTree.root=tree.copy_tree(tree.root);

                    System.out.println("Copied Tree Inorder:");
                    copiedTree.inorder(copiedTree.root);

                    System.out.println();

                    break;

                case 11:
                    System.out.println("BFS Traversal:");
                    tree.bfs();
                    break;

                case 12:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        }while(choice!=12);

        sc.close();
    }
}