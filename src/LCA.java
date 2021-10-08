public class LCA {
    // A Binary Tree Node
    static class Node {
        int data;
        Node left, right;

        Node(int value) {
            data = value;
            left = right = null;
        }
    }

    // A Binary Tree
    public static class BinaryTree{
        Node root;

        private Node addRecursive(Node current, int value) {
            if (current == null) {
                return new Node(value);
            }

            if (value < current.data) {
                current.left = addRecursive(current.left, value);
            } else if (value > current.data) {
                current.right = addRecursive(current.right, value);
            } else {
                // value already exists
                return current;
            }

            return current;
        }

        public void add(int value) {
            root = addRecursive(root, value);
        }

        private Node containsNodeRecursive(Node current, int value) {
            if (current == null) {
                return null;
            }
            if (value == current.data) {
                return current;
            }
            return value < current.data
                    ? containsNodeRecursive(current.left, value)
                    : containsNodeRecursive(current.right, value);
        }

        public Node containsNode(int value) {
            return containsNodeRecursive(root, value);
        }

        public Node lowestCommonAncestor(Node root, Node a, Node b) {
            if(root == null)
                return null;
            if(a == null || b == null)
                return null;
            if(root.data == a.data || root.data == b.data )
                return root;

            Node left=lowestCommonAncestor(root.left,a,b);
            Node right=lowestCommonAncestor(root.right,a,b);

            // If we get left and right not null , it is lca for a and b
            if(left!=null && right!=null)
                return root;
            if(left== null)
                return right;
            else
                return left;

        }
    }

    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        tree.add(40);
        tree.add(20);
        tree.add(10);
        tree.add(30);
        tree.add(60);
        tree.add(50);
        tree.add(70);
        tree.add(5);
        tree.add(45);
        tree.add(55);

        /*
                    40
                 /      \
                20       60
               /  \     /  \
              10   30  50   70
             /          \
            5            55
         */
        int firstNumber = 5;
        int secondNumber = 30;
        Node lowestCommonAncestorNode = tree.lowestCommonAncestor(tree.root, tree.containsNode(firstNumber),
                tree.containsNode(secondNumber));
        if(lowestCommonAncestorNode == null)
            System.out.print("The value entered does not exist in the binary tree.\n");
        else
            System.out.print("Lowest Common Ancestor of " + firstNumber + " and "+ secondNumber + " is: "
                    + lowestCommonAncestorNode.data);
    }
}
