package datastructures;

import model.GameStage;

public class Tree<T extends GameStage>{
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
