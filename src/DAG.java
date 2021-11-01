import java.lang.reflect.Array;
import java.util.*;

public class DAG<T> {
    private Map<Integer, Node> nodes;
    private List<Node> roots;

    public DAG() {
        nodes = new LinkedHashMap<>();
        roots = new ArrayList<>();
    }

    public Node createNode(int object) {
        Node node = new Node(object);
        nodes.put(object, node);
        return node;
    }

    public void addEdge(int parent, int child) {
        Node parentNode = getNode(parent);
        Node childNode = getNode(child);
        if (parentNode == null) parentNode = createNode(parent);
        if (childNode == null) childNode = createNode(child);
        parentNode.addChild(childNode);
    }

    public Node getNode(int key) {
        return nodes.get(key);
    }

    public void update(){
        roots.clear();
        findRoots();
    }

    private void findRoots() {
        for (Node n : nodes.values()) {
            if (n.getParents().size() == 0)
                roots.add(n);
        }
    }

    public ArrayList<Integer> lowestCommonAncestor(int a, int b){
        ArrayList<Integer> lowestCommonAncestors = new ArrayList<>();
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();
        for(int i = 0; i< roots.size(); i++){
            path1.clear();
            path2.clear();
            int lca = findLCA(roots.get(i), a, b, path1, path2);
            if(lca != -1)
                lowestCommonAncestors.add(lca);
        }
        return lowestCommonAncestors;
    }

    private int findLCA(Node root, int v, int w, ArrayList<Integer> path1, ArrayList<Integer> path2) {
        if (!findPath(root, v, path1) || !findPath(root, w, path2)) {
            return -1;
        }
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i)))
                break;
        }
        return path1.get(i-1);
    }

    private boolean findPath(Node root, int n, List<Integer> path)
    {
        if(n<0){//will not allow negative numbers in BT
            return false;
        }
        if (root == null) {
            return false;
        }
        path.add(root.data);
        if (root.data == n) {
            return true;
        }
        for(int i = 0; i<root.children.size(); i++){
            if(findPath(root.children.get(i), n, path))
                return true;
        }
        path.remove(path.size()-1);
        return false;
    }

    public static class Node{
        private List<Node> parents;
        private List<Node> children;
        private int data;

        protected Node(int data){
            this.data = data;
            parents = new LinkedList<>();
            children = new LinkedList<>();
        }

        public int getData() {
            return data;
        }

        List<Node> getParents() {
            return parents;
        }

        List<Node> getChildren() {
            return children;
        }

        public void addParents(Node... par) {
            for (Node n : par) {
                addParent(n);
            }
        }

        public void addParent(Node parent) {
            parents.add(parent);
            if (parent.getChildren().contains(this)) return;
            parent.addChild(this);
        }

        public void addChild(Node child) {
            children.add(child);
            if (child.getParents().contains(this)) return;
            child.addParent(this);
        }
    }
}
