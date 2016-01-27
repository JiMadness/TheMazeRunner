package datastructures;


import Monsters.Monster;
import model.GameLoop;
import model.GameStage;
import model.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node<T extends GameStage> {
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T data = null;
    private int MonsterIndex;
    private boolean winFlag;
    private int GiftsIndex = (int) Math.floor(Math.random()*8);
    public Node(T data) {
        this.data = data;
        Collections.addAll(this.getChildren(), null, null, null, null);
    }
    public Node(T data, Node<T> parent, int MonsterIndex, boolean WinFlag) {
        this.setMonsterIndex(MonsterIndex);
        this.setWinFlag(isWinFlag());
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

    public void addChild(T data, int index,int monsterID,boolean winFlag) {

        Node<T> child = new Node<>(data);
        child.setMonsterIndex(monsterID);
        child.setWinFlag(winFlag);
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
    public void addChild(Node<T> child,int monsterID,boolean winFlag) {
        child.setParent(this);
        child.setMonsterIndex(monsterID);
        child.setWinFlag(winFlag);
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
        return MonsterIndex;
    }
    public int getGiftsIndex(){return GiftsIndex;}
    public Monster getMonster(){
        return GameLoop.getInstance().getMonsters().get(getMonsterIndex());
    }

    public void setMonsterIndex(int monsterIndex) {
        MonsterIndex = monsterIndex;
    }

    public boolean isWinFlag() {
        return winFlag;
    }

    public void setWinFlag(boolean winFlag) {
        this.winFlag = winFlag;
    }
}