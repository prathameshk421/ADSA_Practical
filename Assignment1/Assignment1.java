import java.util.*;

// Node class representing each node of the BST
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

// Binary Search Tree class containing all BST operations
class BinarySearchTree {

    public Node root;

    // Inserts a value into the BST
    public Node insert(Node currentRoot, int data) {
        if (currentRoot == null) {
            return new Node(data);
        }
        if (data < currentRoot.data) {
            currentRoot.left = insert(currentRoot.left, data);
        }
        else if (data > currentRoot.data) {
            currentRoot.right = insert(currentRoot.right, data);
        }
        else {
            System.out.println("Duplicate entry found");
        }
        return currentRoot;
    }

    // Inorder traversal (Left → Root → Right)
    public void inorder(Node currentRoot) {
        if (currentRoot == null) return;
        inorder(currentRoot.left);
        System.out.print(currentRoot.data + " ");
        inorder(currentRoot.right);
    }

    // Preorder traversal (Root → Left → Right)
    public void preorder(Node currentRoot) {
        if (currentRoot == null) return;
        System.out.print(currentRoot.data + " ");
        preorder(currentRoot.left);
        preorder(currentRoot.right);
    }

    // Postorder traversal (Left → Right → Root)
    public void postorder(Node currentRoot) {
        if (currentRoot == null) return;
        postorder(currentRoot.left);
        postorder(currentRoot.right);
        System.out.print(currentRoot.data + " ");
    }

    // Searches a key in the BST
    public boolean search(Node currentRoot, int key) {
        if (currentRoot == null) return false;
        if (currentRoot.data == key) return true;
        if (key < currentRoot.data)
            return search(currentRoot.left, key);
        return search(currentRoot.right, key);
    }

    // Calculates depth (height) of the tree
    public int depth(Node currentRoot) {
        if (currentRoot == null) return 0;
        return Math.max(depth(currentRoot.left), depth(currentRoot.right)) + 1;
    }

    // Converts the tree into its mirror image
    public void mirror(Node currentRoot) {
        if (currentRoot == null) return;

        Node temp = currentRoot.left;
        currentRoot.left = currentRoot.right;
        currentRoot.right = temp;

        mirror(currentRoot.left);
        mirror(currentRoot.right);
    }

    // Creates and returns a deep copy of the tree
    public Node copy(Node currentRoot) {
        if (currentRoot == null) return null;
        Node newNode = new Node(currentRoot.data);
        newNode.left = copy(currentRoot.left);
        newNode.right = copy(currentRoot.right);

        return newNode;
    }

    // Deletes a node from the BST
    public Node delete(Node currentRoot, int key) {
        if (currentRoot == null) return null;
        if (key < currentRoot.data) {
            currentRoot.left = delete(currentRoot.left, key);
        }
        else if (key > currentRoot.data) {
            currentRoot.right = delete(currentRoot.right, key);
        }
        else {
            // Case 1: No child
            if (currentRoot.left == null && currentRoot.right == null) {
                return null;
            }
            // Case 2: One child
            else if (currentRoot.left == null) {
                return currentRoot.right;
            }
            else if (currentRoot.right == null) {
                return currentRoot.left;
            }
            // Case 3: Two children
            else {
                Node successor = currentRoot.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                currentRoot.data = successor.data;
                currentRoot.right = delete(currentRoot.right, successor.data);
            }
        }
        return currentRoot;
    }

    // Breadth First Search (Level Order Traversal)
    public void bfs() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                System.out.print(node.data + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }

    // Displays all leaf nodes of the tree
    public void displayLeaves(Node currentRoot) {
        if (currentRoot == null) return;

        if (currentRoot.left == null && currentRoot.right == null) {
            System.out.print(currentRoot.data + " ");
            return;
        }
        displayLeaves(currentRoot.left);
        displayLeaves(currentRoot.right);
    }
}

// Main class containing menu-driven program
public class Assignment1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        BinarySearchTree copyTree;

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

                case 1: // Insert a node into BST
                    System.out.print("Enter data: ");
                    data = sc.nextInt();
                    tree.root = tree.insert(tree.root, data);
                    break;

                case 2: // Display inorder traversal
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 3: // Display preorder traversal
                    tree.preorder(tree.root);
                    System.out.println();
                    break;

                case 4: // Display postorder traversal
                    tree.postorder(tree.root);
                    System.out.println();
                    break;

                case 5: // Display depth of tree
                    System.out.println("Depth: " + tree.depth(tree.root));
                    break;

                case 6: // Search an element
                    System.out.print("Enter key: ");
                    key = sc.nextInt();
                    System.out.println(tree.search(tree.root, key) ? "Key Found!" : "Key Not Found!");
                    break;

                case 7: // Mirror the BST
                    tree.mirror(tree.root);
                    System.out.println("Tree mirrored");
                    break;

                case 8: // Create and display copy of BST
                    copyTree = new BinarySearchTree();
                    copyTree.root = tree.copy(tree.root);
                    copyTree.inorder(copyTree.root);
                    System.out.println();
                    break;

                case 9: // Perform BFS traversal
                    tree.bfs();
                    break;

                case 10: // Display leaf nodes
                    tree.displayLeaves(tree.root);
                    System.out.println();
                    break;

                case 11: // Delete a node
                    System.out.print("Enter key to delete: ");
                    key = sc.nextInt();
                    tree.root = tree.delete(tree.root, key);
                    System.out.println("Node deleted");
                    break;

                case 12: // Exit program
                    System.out.println("Exiting...");
                    break;

                default: // Invalid menu choice
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
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 100

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 90

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 150

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 80

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 95

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 120

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 1
    Enter data to insert: 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 2
    Inorder Traversal: 80 90 95 100 120 150 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 3
    Preorder Traversal: 100 90 80 95 150 120 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 4
    Postorder Traversal: 80 95 90 120 160 150 100

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 5
    Depth of the tree: 3

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 6
    Enter key to search: 100
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
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 7
    Tree mirrored successfully!

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 2
    Inorder Traversal: 160 150 120 100 95 90 80

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 7
    Tree mirrored successfully!

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 8
    Inorder of Copied Tree: 80 90 95 100 120 150 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 9
    BFS Traversal:
     100
     90 150
     80 95 120 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 10
    Leaf Nodes: 80 95 120 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 11
    Enter key to delete: 120
    Node with data 120 deleted

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 9
    BFS Traversal:
     100
     90 150
     80 95 160

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 11
    Enter key to delete: 100
    Node with data 100 deleted

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 9
    BFS Traversal:
     150
     90 160
     80 95

    --- Binary Search Tree Menu ---
    1. Insert
    2. Inorder Traversal
    3. Preorder Traversal
    4. Postorder Traversal
    5. Find Depth of Tree
    6. Search Element
    7. Mirror Tree
    8. Copy Tree
    9. BFS Traversal / Level Wise Traversal
    10. Display Leaf Nodes
    11. Delete Node
    12. Exit
    Enter your choice: 12
    Exiting program...

    Process finished with exit code 0
    */

    /*
    Extra q:
    Merge 2 bsts and print elements in sorted order
    Dictionary Implementation(Store word lexographically using BST)
    Check if a binary tree is BST or not
    Count total nodes
     */