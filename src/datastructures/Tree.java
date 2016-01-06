package datastructures;

public class Tree<T>{
    private Node<T> root;
    public Tree(){
        this.setRoot(null);
    }
    public Node<T> getRoot() {
        return root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }
}
