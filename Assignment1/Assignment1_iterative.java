import java.util.*;

class BSTNode {
    int data;
    BSTNode left;
    BSTNode right;

    BSTNode(int data) {
        this.data = data;
        left = right = null;
    }
}

class IterativeBST {

    public BSTNode root;

    // Insert into BST
    public void insert(int data) {
        BSTNode newNode = new BSTNode(data);

        if (root == null) {
            root = newNode;
            return;
        }

        BSTNode current = root;

        while (true) {
            if (data < current.data) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                }
                current = current.left;
            } else if (data > current.data) {
                if (current.right == null) {
                    current.right = newNode;
                    return;
                }
                current = current.right;
            } else {
                System.out.println("Duplicate entry found");
                return;
            }
        }
    }

    // Inorder traversal
    public void inorder() {
        Stack<BSTNode> stack = new Stack<>();
        BSTNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
        System.out.println();
    }

    // Preorder traversal
    public void preorder() {
        if (root == null) return;

        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BSTNode current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        System.out.println();
    }

    // Postorder traversal
    public void postorder() {
        if (root == null) return;

        Stack<BSTNode> s1 = new Stack<>();
        Stack<BSTNode> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty()) {
            BSTNode current = s1.pop();
            s2.push(current);

            if (current.left != null) s1.push(current.left);
            if (current.right != null) s1.push(current.right);
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
        System.out.println();
    }

    // BST search
    public boolean search(int key) {
        BSTNode current = root;

        while (current != null) {
            if (current.data == key) return true;
            if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }
        return false;
    }

    // Depth / height of tree
    public int depth() {
        if (root == null) return 0;

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;

            for (int i = 0; i < size; i++) {
                BSTNode current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return height;
    }

    // Mirror the tree
    public void mirror() {
        if (root == null) return;

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BSTNode current = queue.poll();

            BSTNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    // Copy BST
    public IterativeBST copy() {
        IterativeBST newTree = new IterativeBST();
        if (root == null) return newTree;

        newTree.root = new BSTNode(root.data);

        Queue<BSTNode> q1 = new LinkedList<>();
        Queue<BSTNode> q2 = new LinkedList<>();

        q1.add(root);
        q2.add(newTree.root);

        while (!q1.isEmpty()) {
            BSTNode original = q1.poll();
            BSTNode copied = q2.poll();

            if (original.left != null) {
                copied.left = new BSTNode(original.left.data);
                q1.add(original.left);
                q2.add(copied.left);
            }

            if (original.right != null) {
                copied.right = new BSTNode(original.right.data);
                q1.add(original.right);
                q2.add(copied.right);
            }
        }
        return newTree;
    }

    // Delete node
    public void delete(int key) {
        BSTNode parent = null;
        BSTNode current = root;

        while (current != null && current.data != key) {
            parent = current;
            if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }

        if (current == null) {
            System.out.println("Key not found");
            return;
        }

        if (current.left != null && current.right != null) {
            BSTNode successorParent = current;
            BSTNode successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.data = successor.data;
            current = successor;
            parent = successorParent;
        }

        BSTNode child = (current.left != null) ? current.left : current.right;

        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // BFS traversal
    public void bfs() {
        if (root == null) return;

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BSTNode current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        System.out.println();
    }

    // Display leaf nodes
    public void displayLeaves() {
        if (root == null) return;

        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BSTNode current = stack.pop();

            if (current.left == null && current.right == null) {
                System.out.print(current.data + " ");
            }

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        System.out.println();
    }
}

public class Assignment1_iterative {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IterativeBST tree = new IterativeBST();
        IterativeBST copyTree;

        int choice, data, key;

        do {
            System.out.println("\n--- Binary Search Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Preorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Find Depth of Tree");
            System.out.println("6. Search Element");
            System.out.println("7. Mirror Tree");
            System.out.println("8. Copy Tree");
            System.out.println("9. BFS Traversal");
            System.out.println("10. Display Leaf Nodes");
            System.out.println("11. Delete Node");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value to insert: ");
                    data = sc.nextInt();
                    tree.insert(data);
                    break;

                case 2:
                    System.out.print("Inorder Traversal: ");
                    tree.inorder();
                    break;

                case 3:
                    System.out.print("Preorder Traversal: ");
                    tree.preorder();
                    break;

                case 4:
                    System.out.print("Postorder Traversal: ");
                    tree.postorder();
                    break;

                case 5:
                    System.out.println("Depth of tree: " + tree.depth());
                    break;

                case 6:
                    System.out.print("Enter key to search: ");
                    key = sc.nextInt();
                    System.out.println(tree.search(key) ? "Key Found!" : "Key Not Found!");
                    break;

                case 7:
                    tree.mirror();
                    System.out.println("Tree mirrored successfully");
                    break;

                case 8:
                    System.out.println("Copied tree inorder traversal:");
                    copyTree = tree.copy();
                    copyTree.inorder();
                    break;

                case 9:
                    System.out.print("BFS Traversal: ");
                    tree.bfs();
                    break;

                case 10:
                    System.out.print("Leaf nodes: ");
                    tree.displayLeaves();
                    break;

                case 11:
                    System.out.print("Enter key to delete: ");
                    key = sc.nextInt();
                    tree.delete(key);
                    System.out.println("Delete operation completed");
                    break;

                case 12:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 12);

        sc.close();
    }
}

/*
--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 8

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 3

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 10

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 1

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 6

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 4

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 7

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 14

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 1
Enter data: 13

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 2
1 3 4 6 7 8 10 13 14

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 3
8 3 1 6 4 7 10 14 13

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 4
1 4 7 6 3 13 14 10 8

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 5
Depth: 4

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 6
Enter key: 13
Key Found!

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 6
Enter key: 8
Key Found!

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 6
Enter key: 7
Key Found!

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 6
Enter key: 100
Key Not Found!

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 7
Tree mirrored

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 2
14 13 10 8 7 6 4 3 1

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 7
Tree mirrored

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 2
1 3 4 6 7 8 10 13 14

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 9
8
3 10
1 6 14
4 7 13

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 10
1 4 7 13

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 11
Enter key to delete: 6
Node deleted

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 2
1 3 4 7 8 10 13 14

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 9
8
3 10
1 7 14
4 13

--- Binary Search Tree Menu ---
1. Insert
2. Inorder Traversal
3. Preorder Traversal
4. Postorder Traversal
5. Find Depth of Tree
6. Search Element
7. Mirror Tree
8. Copy Tree
9. BFS Traversal
10. Display Leaf Nodes
11. Delete Node
12. Exit
Enter your choice: 12
Exiting...

Process finished with exit code 0
*/