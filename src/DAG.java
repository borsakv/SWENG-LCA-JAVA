import java.util.*;

public class DAG<T> {
    private Map<T, Node<T>> nodes;
    private List<Node<T>> roots;

    public DAG() {
        nodes = new LinkedHashMap<>();
        roots = new ArrayList<>();
    }

    public Node<T> createNode(T object) {
        Node<T> node = new Node<>(object);
        nodes.put(object, node);
        return node;
    }

    public Node<T> lowestCommonAncestor(Node a, Node b){
        // TODO
        return null;
    }
    public static class Node<T>{
        private List<Node<T>> parents;
        private List<Node<T>> children;
        private T data;

        protected Node(T data){
            this.data = data;
            parents = new LinkedList<>();
            children = new LinkedList<>();
        }

        public T getData() {
            return data;
        }

        List<Node<T>> getParents() {
            return parents;
        }

        List<Node<T>> getChildren() {
            return children;
        }

        public void addParents(Node<T>... par) {
            for (Node<T> n : par) {
                addParent(n);
            }
        }

        public void addParent(Node<T> parent) {
            parents.add(parent);
            if (parent.getChildren().contains(this)) return;
            parent.addChild(this);
        }

        public void addChild(Node<T> child) {
            children.add(child);
            if (child.getParents().contains(this)) return;
            child.addParent(this);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node)
                return data.equals(((Node<T>) obj).getData());
            else return false;
        }
    }
}
