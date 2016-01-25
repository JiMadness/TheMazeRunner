package datastructures;


import model.GameStage;
import model.NoPathStage;
import model.Path;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Node<T extends GameStage> {
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T data = null;
    private int monsterIndex;

    public Node(T data) {
            monsterIndex = new Random().nextInt(3) + 1;
        this.data = data;
        Collections.addAll(this.getChildren(), null, null, null, null);
    }
    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
        Collections.addAll(this.getChildren(), null, null, null, null);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void addChild(T data, int index) {
        Node<T> child = new Node<>(data);
        child.setParent(this);
        this.children.set(index, child);
    }

    public void initialize() {
        switch (data.getParentCameFrom()) {
            case UP:
                this.getChildren().set(Path.DOWN.ordinal(), this.getParent());
                break;
            case DOWN:
                this.getChildren().set(Path.UP.ordinal(), this.getParent());
                break;
            case LEFT:
                this.getChildren().set(Path.RIGHT.ordinal(), this.getParent());
                break;
            case RIGHT:
                this.getChildren().set(Path.LEFT.ordinal(), this.getParent());
                break;
        }
    }
    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }

    public int getMonsterIndex() {
        return monsterIndex;
    }
}